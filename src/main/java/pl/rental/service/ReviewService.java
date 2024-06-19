package pl.rental.service;

import org.springframework.stereotype.Service;
import pl.rental.model.Review;
import pl.rental.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getReviewsByCarId(Long carId) {
        return reviewRepository.findByCar_CarId(carId);
    }

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }
}
