package pl.rental.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.rental.model.Car;
import pl.rental.model.api.CarDataResponse;
import pl.rental.repository.CarRepository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CarDataService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.host}")
    private String apiHost;
    private final String apiUrl = "https://car-data.p.rapidapi.com/cars";

    private CarRepository carRepository;

    public CarDataService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

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
        Car car = new Car();
        car.setMake(carData.getMake());
        car.setModel(carData.getModel());
        car.setYear(carData.getYear());
        car.setPrice(carData.getPrice());
        car.setType(carData.getType());

        carRepository.save(car);
    }
}
