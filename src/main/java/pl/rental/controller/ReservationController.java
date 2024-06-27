package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rental.model.Car;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReservationService;
import pl.rental.service.UserService;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private ReservationService reservationService;
    private CarDataService carDataService;
    private UserService userService;

    public ReservationController(ReservationService reservationService, CarDataService carDataService, UserService userService) {
        this.reservationService = reservationService;
        this.carDataService = carDataService;
        this.userService = userService;
    }

    @GetMapping("/user")
    public String showUserReservations(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null) {
            return "redirect:/users/login";
        }
        List<Reservation> userReservations = reservationService.getAllReservationByUser(loggedUser.getUserId());
        model.addAttribute("userReservations", userReservations);
        return "show-all-reservation-logged-user";
    }

    @GetMapping("/new")
    public String showReservationForm(Model model) {
        List<Car> cars = carDataService.getAllCars();
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("cars", cars);

        String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        model.addAttribute("currentDateTime", currentDateTime);

        return "reservation-form";
    }

    @PostMapping("/save")
    public String saveReservation(@RequestParam Long carId, @RequestParam String returnDate, HttpSession session, Model model) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return "redirect:/users/login";
        }

        Car car = carDataService.getCarById(carId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime returnDateTime;

        try {
            returnDateTime = LocalDateTime.parse(returnDate, formatter);
        } catch (DateTimeParseException e) {
            model.addAttribute("errorMessage", "Invalid date format");
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("cars", carDataService.getAllCars());
            return "reservation-form";
        }

        if (returnDateTime.isBefore(LocalDateTime.now())) {
            model.addAttribute("errorMessage", "Return date cannot be in the past");
            model.addAttribute("reservation", new Reservation());
            model.addAttribute("cars", carDataService.getAllCars());
            return "reservation-form";
        }

        Reservation reservation = new Reservation();
        reservation.setCar(car);
        reservation.setReturnDate(returnDateTime);

        reservation.setReservationDate(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));

        reservationService.saveReservation(reservation, loggedInUser);

        return "redirect:/reservations/user";
    }
}
