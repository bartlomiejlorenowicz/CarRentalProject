package pl.rental.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import pl.rental.model.Car;
import pl.rental.service.CarDataService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CarSearchControllerTest {

    private CarDataService carDataService;
    private CarSearchController carSearchController;
    private Model model;

    @BeforeEach
    void setup() {
        carDataService = Mockito.mock(CarDataService.class);
        model = Mockito.mock(Model.class);
        carSearchController = new CarSearchController(carDataService);
    }

    @Test
    void searchCars() {
        String make = "Toyota";
        String modelParam = "Camry";
        Integer year = 2020;
        BigDecimal price = BigDecimal.valueOf(30000);
        String type = "Sedan";

        Car car1 = new Car();
        car1.setMake(make);
        car1.setModel(modelParam);
        car1.setYear(year);
        car1.setPrice(price);
        car1.setType(type);

        Car car2 = new Car();
        car2.setMake(make);
        car2.setModel("Corolla");
        car2.setYear(year);
        car2.setPrice(price);
        car2.setType(type);

        List<Car> expectedCars = Arrays.asList(car1, car2);
        when(carDataService.searchCars(make, modelParam, year, price, type)).thenReturn(expectedCars);

        String viewName = carSearchController.searchCars(make, modelParam, year, price, type, model);

        verify(model).addAttribute("cars", expectedCars);
        assertEquals("search-results", viewName);
    }

}
