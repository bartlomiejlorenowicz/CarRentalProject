package pl.rental.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import pl.rental.model.Car;
import pl.rental.model.Review;
import pl.rental.model.User;
import pl.rental.repository.CarRepository;
import pl.rental.repository.ReviewRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final CarRepository carRepository;

    public ReviewService(ReviewRepository reviewRepository, CarRepository carRepository) {
        this.reviewRepository = reviewRepository;
        this.carRepository = carRepository;
    }

    public List<Review> getReviewsByCarId(Long carId) {
        return reviewRepository.findByCar_CarId(carId);
    }

    @Transactional
    public void addReview(Long carId, User user, int rating, String comment) {
        Car car = carRepository.findById(carId).orElseThrow(() -> new RuntimeException("Car not found"));
        Review review = new Review();
        review.setCar(car);
        review.setUser(user);
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(LocalDateTime.now());
        reviewRepository.save(review);
    }

    public List<Car> getTopRatedCars() {
        return carRepository.findAll().stream()
                .sorted((car1, car2) -> Double.compare(getAverageRating(car2), getAverageRating(car1)))
                .limit(10)
                .collect(Collectors.toList());
    }

    public double getAverageRating(Car car) {
        return car.getReviews().stream().mapToInt(Review::getRating).average().orElse(0.0);
    }
}
