package pl.rental.controller;

import jakarta.persistence.GeneratedValue;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.rental.model.Car;
import pl.rental.model.Review;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;
    private final CarDataService carDataService;

    public ReviewController(ReviewService reviewService, CarDataService carDataService) {
        this.reviewService = reviewService;
        this.carDataService = carDataService;
    }

    @GetMapping("/cars/{carId}")
    public String showCarReviews(@PathVariable Long carId, Model model) {
        List<Review> reviews = reviewService.getReviewsByCarId(carId);
        model.addAttribute("reviews", reviews);
        return "car-reviews";
    }

    @PostMapping("/add")
    public ModelAndView addReview(@RequestParam Long carId,
                                  @RequestParam int rating,
                                  @RequestParam String comment,
                                  HttpSession session) {
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            return new ModelAndView(new RedirectView("/users/login"));
        }
        reviewService.addReview(carId, loggedInUser, rating, comment);
        return new ModelAndView(new RedirectView("/cars/" + carId));
    }

    @GetMapping("/top-rated")
    public String showTopRatedCars(Model model) {
        List<Car> topRatedCars = reviewService.getTopRatedCars();
        model.addAttribute("topRatedCars", topRatedCars);
        return "top-rated-cars";
    }

    @GetMapping("/api/car/{carId}")
    public ResponseEntity<List<Review>> getReviewsForCar(@PathVariable Long carId) {
        List<Review> reviews = reviewService.getReviewsByCarId(carId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
