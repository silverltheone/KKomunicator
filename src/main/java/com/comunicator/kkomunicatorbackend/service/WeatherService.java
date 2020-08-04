package com.comunicator.kkomunicatorbackend.service;

import com.comunicator.kkomunicatorbackend.client.WeatherClient;
import com.comunicator.kkomunicatorbackend.domain.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    @Autowired
    WeatherClient client;


    public WeatherData get(Double lat, Double lon) {
        return client.get(lat, lon);
    }


}
