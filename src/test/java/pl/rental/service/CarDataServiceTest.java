package pl.rental.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import pl.rental.model.Car;
import pl.rental.repository.CarRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class CarDataServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarDataService carDataService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllUniqueCarTypes() {
        Car car1 = new Car();
        car1.setType("SUV");

        Car car2 = new Car();
        car2.setType("Sedan");

        when(carRepository.findAll()).thenReturn(List.of(car1, car2));

        Set<String> uniqueCarTypes = carDataService.getAllUniqueCarTypes();

        assertEquals(2, uniqueCarTypes.size());
        Assertions.assertTrue(uniqueCarTypes.contains("SUV"));
        Assertions.assertTrue(uniqueCarTypes.contains("Sedan"));
    }

    @Test
    void testGetCarsPaginated() {
        Page<Car> carPage = mock(Page.class);
        when(carRepository.findAll(any(PageRequest.class))).thenReturn(carPage);

        PageRequest pageRequest = PageRequest.of(0, 10);
        Page<Car> result = carDataService.getCarsPaginated(pageRequest);

        assertEquals(carPage, result);
    }

    @Test
    void testFindById() {
        Car car = new Car();
        car.setMake("Toyota");
        car.setModel("Corolla");

        when(carRepository.findById(1L)).thenReturn(Optional.of(car));

        Car foundCar = carDataService.findById(1L);

        assertEquals("Toyota", foundCar.getMake());
        assertEquals("Corolla", foundCar.getModel());
    }

    @Test
    void testFindById_NotFound() {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            carDataService.findById(1L);
        });

        assertEquals("Car not found", exception.getMessage());
    }
}
