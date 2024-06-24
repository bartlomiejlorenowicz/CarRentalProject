package pl.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.rental.model.Car;
import pl.rental.service.CarDataService;
import pl.rental.service.ReviewService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class CarController {

    private CarDataService carDataService;
    private ReviewService reviewService;

    public CarController(CarDataService carDataService, ReviewService reviewService) {
        this.carDataService = carDataService;
        this.reviewService = reviewService;
    }

    @GetMapping("/cars/top-rated")
    public String showTopRatedCars(Model model) {
        model.addAttribute("topRatedCars", reviewService.getTopRatedCars());
        return "top-rated-cars";
    }

    @GetMapping("/{id}")
    public String showCarDetails(@PathVariable Long id, Model model) {
        Car car = carDataService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("reviews", reviewService.getReviewsByCarId(id));
        model.addAttribute("averageRating", reviewService.getAverageRating(car));
        return "car-details";
    }

    @PostMapping("/fetchCars")
    public void fetchCars(@RequestBody Map<String, String> queryParams) {
        carDataService.fetchAndSaveCarData(queryParams);
    }

    @GetMapping("/cars")
    public String getCars(Model model) {
        List<Car> allCars = carDataService.getAllCars();
        model.addAttribute("cars", allCars);
        return "all-cars";
    }

    @GetMapping("/")
    public String showHomePage(Model model) {
        Set<String> carTypes = carDataService.getAllUniqueCarTypes();
        model.addAttribute("carTypes", carTypes);
        return "index";
    }

    @GetMapping("/cars/review")
    public String showReviewForm(@RequestParam Long carId, Model model) {
        Car car = carDataService.getCarById(carId);
        model.addAttribute("car", car);
        return "car-review-form";
    }

    @GetMapping("/cars/{carId}")
    public String getCarDetails(@PathVariable Long carId, Model model) {
        Car car = carDataService.getCarById(carId);
        model.addAttribute("car", car);
        return "car-details";
    }
}
