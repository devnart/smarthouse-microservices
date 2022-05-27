package com.devnart.weather.service;

import com.devnart.weather.entity.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WebClient webClient;

    public Weather getWeather(String lat, String lon) {
        String url = "https://api.open-meteo.com/v1/forecast";
        HashMap weather = webClient.get()
                .uri(url + "?latitude=" + lat + "&longitude=" + lon + "&current_weather=true")
                .retrieve()
                .bodyToMono(HashMap.class)
                .block();

        assert weather != null;
        HashMap currentWeather = (HashMap) weather.get("current_weather");

        Weather myWeather = new Weather();

        myWeather.setLatitude(weather.get("latitude").toString());
        myWeather.setLongitude(weather.get("longitude").toString());
        myWeather.setTemperature(currentWeather.get("temperature").toString());
        myWeather.setTime(currentWeather.get("time").toString());

        return myWeather;
    }
}
