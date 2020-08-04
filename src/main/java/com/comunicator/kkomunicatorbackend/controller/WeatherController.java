package com.comunicator.kkomunicatorbackend.controller;

import com.comunicator.kkomunicatorbackend.domain.WeatherData;
import com.comunicator.kkomunicatorbackend.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherService service;

    @GetMapping("/{lat}/{lon}")
    public WeatherData get(@PathVariable Double lat, @PathVariable Double lon) {
        return service.get(lat, lon);
    }
}
