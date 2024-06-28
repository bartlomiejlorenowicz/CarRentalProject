package pl.rental.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.rental.model.Car;
import pl.rental.model.Review;
import pl.rental.model.User;
import pl.rental.repository.CarRepository;
import pl.rental.repository.ReviewRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetReviewsByCarId() {
        Long carId = 1L;
        Review review1 = new Review();
        Review review2 = new Review();
        List<Review> reviews = Arrays.asList(review1, review2);

        when(reviewRepository.findByCar_CarId(carId)).thenReturn(reviews);

        List<Review> actualReviews = reviewService.getReviewsByCarId(carId);

        assertEquals(reviews, actualReviews);
    }

    @Test
    public void testAddReview() {
        Long carId = 1L;
        User user = new User();
        user.setUserId(1L);
        int rating = 5;
        String comment = "Great car!";
        Car car = new Car();
        car.setCarId(carId);

        when(carRepository.findById(carId)).thenReturn(Optional.of(car));

        reviewService.addReview(carId, user, rating, comment);

        verify(reviewRepository).save(any(Review.class));
    }

    @Test
    public void testAddReviewCarNotFound() {
        Long carId = 1L;
        User user = new User();
        user.setUserId(1L);
        int rating = 5;
        String comment = "Great car!";

        when(carRepository.findById(carId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> reviewService.addReview(carId, user, rating, comment));
    }

    @Test
    public void testGetTopRatedCars() {
        Car car1 = new Car();
        car1.setCarId(1L);
        Review review1 = new Review();
        review1.setRating(5);
        car1.setReviews(Arrays.asList(review1));

        Car car2 = new Car();
        car2.setCarId(2L);
        Review review2 = new Review();
        review2.setRating(4);
        car2.setReviews(Arrays.asList(review2));

        List<Car> cars = Arrays.asList(car1, car2);

        when(carRepository.findAll()).thenReturn(cars);

        List<Car> topRatedCars = reviewService.getTopRatedCars();

        assertEquals(2, topRatedCars.size());
        assertEquals(car1, topRatedCars.get(0));
        assertEquals(car2, topRatedCars.get(1));
    }

    @Test
    public void testGetAverageRating() {
        Car car = new Car();
        Review review1 = new Review();
        review1.setRating(4);
        Review review2 = new Review();
        review2.setRating(5);
        car.setReviews(Arrays.asList(review1, review2));

        double averageRating = reviewService.getAverageRating(car);

        assertEquals(4.5, averageRating);
    }
}
