package pl.rental.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.rental.model.Car;
import pl.rental.service.CarDataService;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarSearchController {

    private CarDataService carDataService;
    private static Logger logger = LoggerFactory.getLogger(CarSearchController.class);

    public CarSearchController(CarDataService carDataService) {
        this.carDataService = carDataService;
    }

    @PostMapping("/search")
    public String searchCars(@RequestParam(required = false) String make,
                             @RequestParam(required = false) String model,
                             @RequestParam(required = false) Integer year,
                             @RequestParam(required = false) BigDecimal price,
                             @RequestParam(required = false) String type,
                             Model modelAttr) {
        logger.info("Search parameters: make={}, model={}, year={}, price={}, type={}", make, model, year, price, type);

        List<Car> cars = carDataService.searchCars(make, model, year, price, type);
        modelAttr.addAttribute("cars", cars);
        return "search-results";
    }
}
