package com.example.weather;

import com.example.weather.model.AlertThreshold;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.AlertThresholdRepository;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

public class AlertThresholdsTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private AlertThresholdRepository alertThresholdRepository;

    @Test
    public void testCheckAlerts() {
        AlertThreshold threshold = new AlertThreshold();
        threshold.setCondition("temperature");
        threshold.setThresholdValue(35.0);

        when(alertThresholdRepository.findByCondition("temperature")).thenReturn(threshold);

        WeatherData latestData = new WeatherData();
        latestData.setTemperature(36.0); // Simulate exceeding the threshold

        weatherService.checkAlerts(latestData);

        // Assert that the alert logic was triggered, you might need to implement an alerting mechanism
        // e.g., check a flag or log output to verify alert was sent
    }
}
