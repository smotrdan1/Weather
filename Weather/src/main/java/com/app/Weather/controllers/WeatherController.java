package com.app.Weather.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.app.Weather.beans.ValidationResponse;
import com.app.Weather.beans.WeatherInsight;
import com.app.Weather.constants.Constants;
import com.app.Weather.service.WeatherService;

@RestController
@RequestMapping(Constants.weather.WEATHER_PATH)
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping(Constants.weather.INSIGHT)
    public ResponseEntity<?> getWeatherInsight(
            @RequestParam Double lon,
            @RequestParam Double lat,
            @RequestParam String condition) {
        try {
            List<WeatherInsight> insights = weatherService.getWeatherInsight(lon, lat, condition);
            return ResponseEntity.ok(insights);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred.");
        }
    }

    
    
}
