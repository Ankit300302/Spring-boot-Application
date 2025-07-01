package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.apiRequest.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;
    private static final String API_TEMPLATE = "http://api.weatherstack.com/current?access_key=%s&query=%s";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        try {
            String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
            String finalURL = String.format(API_TEMPLATE, apikey, encodedCity);
            System.out.println("Calling Weather API: " + finalURL);

            ResponseEntity<WeatherResponse> response = restTemplate.exchange(
                    finalURL, HttpMethod.GET, null, WeatherResponse.class);

            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}




