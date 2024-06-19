package pl.rental.controller;

import jakarta.persistence.GeneratedValue;
import org.springframework.web.bind.annotation.*;
import pl.rental.model.Review;
import pl.rental.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/car/{carId}")
    public List<Review> getReviewsByCarId(@PathVariable Long carId) {
        return reviewService.getReviewsByCarId(carId);
    }

    @PostMapping("/add")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }
}
