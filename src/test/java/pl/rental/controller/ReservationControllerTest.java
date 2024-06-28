package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import pl.rental.model.Car;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReservationService;
import pl.rental.service.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReservationControllerTest {

    private ReservationService reservationService;
    private CarDataService carDataService;
    private UserService userService;
    private ReservationController reservationController;
    private HttpSession session;
    private Model model;

    @BeforeEach
    void setup() {
        reservationService = Mockito.mock(ReservationService.class);
        carDataService = Mockito.mock(CarDataService.class);
        userService = Mockito.mock(UserService.class);
        session = Mockito.mock(HttpSession.class);
        model = Mockito.mock(Model.class);
        reservationController = new ReservationController(reservationService, carDataService, userService);
    }

    @Test
    void showUserReservations_UserLoggedIn() {
        User loggedInUser = new User();
        loggedInUser.setUserId(1L);
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        List<Reservation> reservations = Arrays.asList(new Reservation(), new Reservation());
        when(reservationService.getAllReservationByUser(loggedInUser.getUserId())).thenReturn(reservations);

        String viewName = reservationController.showUserReservations(session, model);

        verify(model).addAttribute("userReservations", reservations);
        assertEquals("show-all-reservation-logged-user", viewName);
    }

    @Test
    void showUserReservations_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = reservationController.showUserReservations(session, model);

        assertEquals("redirect:/users/login", viewName);
    }

    @Test
    void showReservationForm() {
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carDataService.getAllCars()).thenReturn(cars);

        String viewName = reservationController.showReservationForm(model);

        verify(model).addAttribute("reservation", new Reservation());
        verify(model).addAttribute("cars", cars);
        verify(model).addAttribute(eq("currentDateTime"), anyString());
        assertEquals("reservation-form", viewName);
    }

    @Test
    void saveReservation_UserLoggedIn_InvalidDateFormat() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        String invalidDate = "invalid-date";
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carDataService.getAllCars()).thenReturn(cars);

        String viewName = reservationController.saveReservation(1L, invalidDate, session, model);

        verify(model).addAttribute("errorMessage", "Invalid date format");
        verify(model).addAttribute("reservation", new Reservation());
        verify(model).addAttribute("cars", cars);
        assertEquals("reservation-form", viewName);
    }

    @Test
    void saveReservation_UserLoggedIn_PastReturnDate() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String pastDateString = pastDate.format(formatter);
        List<Car> cars = Arrays.asList(new Car(), new Car());
        when(carDataService.getAllCars()).thenReturn(cars);

        String viewName = reservationController.saveReservation(1L, pastDateString, session, model);

        verify(model).addAttribute("errorMessage", "Return date cannot be in the past");
        verify(model).addAttribute("reservation", new Reservation());
        verify(model).addAttribute("cars", cars);
        assertEquals("reservation-form", viewName);
    }

    @Test
    void saveReservation_UserLoggedIn_Successful() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        Car car = new Car();
        when(carDataService.getCarById(1L)).thenReturn(car);

        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        String futureDateString = futureDate.format(formatter);

        String viewName = reservationController.saveReservation(1L, futureDateString, session, model);

        verify(reservationService).saveReservation(any(Reservation.class), eq(loggedInUser));
        assertEquals("redirect:/reservations/user", viewName);
    }

    @Test
    void saveReservation_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        String viewName = reservationController.saveReservation(1L, "2024-12-31T12:00", session, model);

        assertEquals("redirect:/users/login", viewName);
    }
}
