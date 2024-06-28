package pl.rental.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.ui.Model;
import pl.rental.model.Car;
import pl.rental.service.CarDataService;
import pl.rental.service.ReviewService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CarControllerTest {

    private CarDataService carDataService;
    private ReviewService reviewService;
    private CarController carController;
    private Model model;

    @BeforeEach
    void setup() {
        carDataService = Mockito.mock(CarDataService.class);
        reviewService = Mockito.mock(ReviewService.class);
        model = Mockito.mock(Model.class);
        carController = new CarController(carDataService, reviewService);
    }

    @Test
    void showTopRatedCars() {
        Car car1 = new Car();
        car1.setMake("Toyota");
        car1.setModel("Corolla");

        Car car2 = new Car();
        car2.setMake("Honda");
        car2.setModel("Civic");
        List<Car> topRatedCars = Arrays.asList(car1, car2);

        when(reviewService.getTopRatedCars()).thenReturn(topRatedCars);

        String viewName = carController.showTopRatedCars(model);

        verify(model).addAttribute("topRatedCars", topRatedCars);
        assertEquals("top-rated-cars", viewName);
    }

    @Test
    void showCarDetails() {
        Long carId = 1L;
        Car car = new Car();
        when(carDataService.findById(carId)).thenReturn(car);
        when(reviewService.getReviewsByCarId(carId)).thenReturn(Arrays.asList());
        when(reviewService.getAverageRating(car)).thenReturn(4.5);

        String viewName = carController.showCarDetails(carId, model);

        verify(model).addAttribute("car", car);
        verify(model).addAttribute("reviews", Arrays.asList());
        verify(model).addAttribute("averageRating", 4.5);
        assertEquals("car-details", viewName);
    }

    @Test
    void fetchCars() {
        Map<String, String> queryParams = Map.of("make", "Toyota", "model", "Camry");
        doNothing().when(carDataService).fetchAndSaveCarData(queryParams);

        carController.fetchCars(queryParams);

        verify(carDataService).fetchAndSaveCarData(queryParams);
    }

    @Test
    void getCars() {
        int page = 0;
        int size = 9;
        List<Car> cars = Arrays.asList(new Car(), new Car());
        Page<Car> carPage = new PageImpl<>(cars, PageRequest.of(page, size), cars.size());
        when(carDataService.getCarsPaginated(PageRequest.of(page, size))).thenReturn(carPage);

        String viewName = carController.getCars(page, size, model);

        verify(model).addAttribute("carPage", carPage);
        assertEquals("all-cars", viewName);
    }

    @Test
    void showHomePage() {
        Set<String> carTypes = new HashSet<>(Arrays.asList("SUV", "Sedan"));
        when(carDataService.getAllUniqueCarTypes()).thenReturn(carTypes);

        String viewName = carController.showHomePage(model);

        verify(model).addAttribute("carTypes", carTypes);
        assertEquals("index", viewName);
    }

    @Test
    void showReviewForm() {
        Long carId = 1L;
        Car car = new Car();
        when(carDataService.getCarById(carId)).thenReturn(car);

        String viewName = carController.showReviewForm(carId, model);

        verify(model).addAttribute("car", car);
        assertEquals("car-review-form", viewName);
    }

    @Test
    void getCarDetails() {
        Long carId = 1L;
        Car car = new Car();
        when(carDataService.getCarById(carId)).thenReturn(car);

        String viewName = carController.getCarDetails(carId, model);

        verify(model).addAttribute("car", car);
        assertEquals("car-details", viewName);
    }
}
