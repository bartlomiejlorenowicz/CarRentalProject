package pl.rental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.rental.service.CarDataService;

import java.util.Map;

@RestController
public class CarController {

    private CarDataService carDataService;

    public CarController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @PostMapping("/fetchCars")
    public void fetchCars(@RequestBody Map<String, String> queryParams) {
        carDataService.fetchAndSaveCarData(queryParams);
    }
}
