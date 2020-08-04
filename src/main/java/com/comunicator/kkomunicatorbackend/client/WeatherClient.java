package com.comunicator.kkomunicatorbackend.client;

import com.comunicator.kkomunicatorbackend.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
public class WeatherClient {

    private String exclude = "minutely,hourly,daily";
    private String apiKey = "072602db9cd1c10820dc55ee53a96f3e";

    @Autowired
    RestTemplate restTemplate;

    public WeatherData get(Double lat, Double lon) {
        URI getWeatherUrl = UriComponentsBuilder.fromHttpUrl("https://api.openweathermap.org/data/2.5/onecall")
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("exclude", exclude)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric").build().encode().toUri();
        WeatherData weatherData = restTemplate.getForObject(getWeatherUrl, WeatherData.class);
        return  weatherData;
    }
}
