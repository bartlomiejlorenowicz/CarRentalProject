package pl.rental.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.rental.model.Car;
import pl.rental.model.api.CarDataResponse;
import pl.rental.repository.CarRepository;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

@Service
public class CarDataService {

    private final CarRepository carRepository;
    public CarDataService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    @Value("${api.key}")
    private String apiKey;

    @Value("${api.host}")
    private String apiHost;

    @Value("{api.url")
    private String apiUrl;

    private static Logger logger = LoggerFactory.getLogger(CarDataService.class);

    public void fetchAndSaveCarData(Map<String, String> queryParams) {
        OkHttpClient client = new OkHttpClient();
        String url = buildUrl(queryParams);

        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("x-rapidapi-key", apiKey)
                .addHeader("x-rapidapi-host", apiHost)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                ObjectMapper mapper = new ObjectMapper();
                List<CarDataResponse> carDataList = mapper.readValue(response.body().string(), new TypeReference<List<CarDataResponse>>() {});

                carDataList.forEach(this::saveCarData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String buildUrl(Map<String, String> queryParams) {
        StringBuilder urlBuilder = new StringBuilder(apiUrl).append("?");

        queryParams.forEach((key, value) -> urlBuilder.append(key).append("=").append(value).append("&"));

        // Remove the last '&' character
        if (urlBuilder.length() > 0) {
            urlBuilder.setLength(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }

    private void saveCarData(CarDataResponse carData) {
        ObjectMapper mapper = new ObjectMapper();
        Car car = mapper.convertValue(carData, Car.class);

        car.setPrice(generateRandomPrice());

        carRepository.save(car);
    }

    private BigDecimal generateRandomPrice() {
        Random random = new Random();
        double randomPrice = random.nextDouble(100 - 30) + 30;
        return BigDecimal.valueOf(randomPrice).setScale(2, RoundingMode.HALF_UP);
    }

    public List<Car>getAllCars() {
        return carRepository.findAll();
    }

    public List<Car> searchCars(String make, String model, Integer year, BigDecimal price, String type) {
        if (type != null) {
            type = type.toLowerCase().trim();
        }
        List<Car> cars = carRepository.findCarsByCriteria(make, model, year, price, type);
        logger.info("found {} cars matching criteria", cars.size());
        return cars;
    }

    public Set<String> getAllUniqueCarTypes() {
        List<Car> cars = carRepository.findAll();
        Set<String> uniqueTypes = new HashSet<>();
        for (Car car : cars) {
            String[] types = car.getType().split(",");
            uniqueTypes.addAll(Arrays.asList(types));
        }
        return uniqueTypes;
    }

    public Car findById(Long id) {
        return carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
    }

    public Car getCarById(Long carId) {
        Optional<Car> carOptional = carRepository.findById(carId);
        if (carOptional.isPresent()) {
            return carOptional.get();
        } else {
            throw new RuntimeException("Car not found with id: " + carId);
        }
    }

    public Page<Car> getCarsPaginated(PageRequest page) {
        return carRepository.findAll(page);
    }
}
