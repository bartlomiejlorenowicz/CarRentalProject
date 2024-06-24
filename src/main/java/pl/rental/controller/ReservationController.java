package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReservationService;
import pl.rental.service.UserService;

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
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("cars", carDataService.getAllCars());
        return "reservation-form";
    }

    @PostMapping("/save")
    public String saveReservation(@ModelAttribute Reservation reservation, HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser != null) {
            reservationService.saveReservation(reservation, loggedInUser);
            return "redirect:/reservations/user";
        } else {
            return "redirect:/users/login";
        }
    }
}
