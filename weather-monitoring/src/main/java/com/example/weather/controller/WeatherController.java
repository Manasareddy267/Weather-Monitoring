package com.example.weather.controller;

import com.example.weather.model.AlertThreshold;
import com.example.weather.model.DailySummary;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.AlertThresholdRepository;
import com.example.weather.repository.DailySummaryRepository;
import com.example.weather.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WeatherController {
    @Autowired
    private WeatherDataRepository repository;
    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Autowired
    private AlertThresholdRepository alertRepository;

    @GetMapping("/weather")
    public List<WeatherData> getWeatherData() {
        return repository.findAll();
    }
    @GetMapping("/daily-summary")
    public List<DailySummary> getDailySummaries() {
        return dailySummaryRepository.findAll();
    }

    @GetMapping("/alerts")
    public List<AlertThreshold> getAlerts() {
        return alertRepository.findAll(); // Assuming you have an Alert model
    }

}
