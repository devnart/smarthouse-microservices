package com.devnart.weather.controller;

import com.devnart.weather.entity.Weather;
import com.devnart.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping
    public Weather getWeather(@RequestParam String longitude, @RequestParam String latitude) {
        return weatherService.getWeather(longitude, latitude);
    }
}
