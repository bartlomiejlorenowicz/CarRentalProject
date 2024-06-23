package pl.rental.controller;

import com.oracle.wls.shaded.org.apache.xpath.operations.Mod;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.rental.model.Reservation;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReservationService;
import pl.rental.service.UserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getAllReservationForUser(HttpSession session, Model model) {
        User loggedUser = (User) session.getAttribute("user");
        if (loggedUser == null) {
            return "redirect:/users/login";
        }
        List<Reservation> allReservation = reservationService.getAllReservation();
        List<Reservation> userReservation = allReservation.stream()
                .filter(reservation -> reservation.getUser().getUserId().equals(loggedUser.getUserId()))
                .collect(Collectors.toList());
        model.addAttribute("userReservation", userReservation);
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
