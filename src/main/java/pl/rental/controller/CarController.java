package pl.rental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rental.model.Car;
import pl.rental.service.CarDataService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class CarController {

    private CarDataService carDataService;

    public CarController(CarDataService carDataService) {
        this.carDataService = carDataService;
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
}
