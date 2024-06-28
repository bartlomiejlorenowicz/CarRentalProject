package pl.rental.controller;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.rental.model.Car;
import pl.rental.model.Review;
import pl.rental.model.User;
import pl.rental.service.CarDataService;
import pl.rental.service.ReviewService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {

    private ReviewService reviewService;
    private CarDataService carDataService;
    private ReviewController reviewController;
    private HttpSession session;
    private Model model;

    @BeforeEach
    void setup() {
        reviewService = Mockito.mock(ReviewService.class);
        carDataService = Mockito.mock(CarDataService.class);
        session = Mockito.mock(HttpSession.class);
        model = Mockito.mock(Model.class);
        reviewController = new ReviewController(reviewService, carDataService);
    }

    @Test
    void addReview_UserLoggedIn() {
        User loggedInUser = new User();
        when(session.getAttribute("user")).thenReturn(loggedInUser);

        Long carId = 1L;
        int rating = 5;
        String comment = "Great car!";

        ModelAndView modelAndView = reviewController.addReview(carId, rating, comment, session);

        verify(reviewService).addReview(carId, loggedInUser, rating, comment);
        assertEquals("/cars/" + carId, ((RedirectView) modelAndView.getView()).getUrl());
    }

    @Test
    void addReview_UserNotLoggedIn() {
        when(session.getAttribute("user")).thenReturn(null);

        Long carId = 1L;
        int rating = 5;
        String comment = "Great car!";

        ModelAndView modelAndView = reviewController.addReview(carId, rating, comment, session);

        assertEquals("/users/login", ((RedirectView) modelAndView.getView()).getUrl());
    }

    @Test
    void showTopRatedCars() {
        List<Car> topRatedCars = Arrays.asList(new Car(), new Car());
        when(reviewService.getTopRatedCars()).thenReturn(topRatedCars);

        String viewName = reviewController.showTopRatedCars(model);

        verify(model).addAttribute("topRatedCars", topRatedCars);
        assertEquals("top-rated-cars", viewName);
    }

    @Test
    void getReviewsForCar() {
        Long carId = 1L;
        List<Review> reviews = Arrays.asList(new Review(), new Review());
        when(reviewService.getReviewsByCarId(carId)).thenReturn(reviews);

        ResponseEntity<List<Review>> responseEntity = reviewController.getReviewsForCar(carId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(reviews, responseEntity.getBody());
    }
}
