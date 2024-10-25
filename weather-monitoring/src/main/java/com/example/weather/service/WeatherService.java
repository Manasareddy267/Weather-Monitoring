package com.example.weather.service;

import com.example.weather.dto.WeatherResponse;
import com.example.weather.model.AlertThreshold;
import com.example.weather.model.DailySummary;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.AlertThresholdRepository;
import com.example.weather.repository.DailySummaryRepository;
import com.example.weather.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {
    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    @Autowired
    private WeatherDataRepository repository;

    private final RestTemplate restTemplate = new RestTemplate();
    private double previousTemperature = Double.NaN;
    private boolean temperatureAlertSent = false;
    @Autowired
    private DailySummaryRepository dailySummaryRepository;

    @Autowired
    private AlertThresholdRepository alertThresholdRepository;

    @Scheduled(fixedRate = 300000) // Every 5 minutes
    public void fetchWeatherData() {
        String[] cities = {"Delhi", "Mumbai", "Chennai", "Bangalore", "Kolkata", "Hyderabad"};
        for (String city : cities) {
            String url = String.format("%s?q=%s,IN&appid=%s", apiUrl, city, apiKey);
            WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);
            if (response != null) {
                WeatherData weatherData = new WeatherData();
                weatherData.setMainCondition(response.getWeather()[0].getMain());
                weatherData.setTemperature(kelvinToCelsius(response.getMain().getTemp()));
                weatherData.setFeelsLike(kelvinToCelsius(response.getMain().getFeels_like()));
                weatherData.setHumidity(response.getMain().getHumidity()); // Set humidity
                weatherData.setWindSpeed(response.getWind().getSpeed()); // Set wind speed
                weatherData.setTimestamp(Instant.ofEpochSecond(response.getDt()));
                repository.save(weatherData);
            }
        }
    }


    public double kelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public double kelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9/5 + 32;
    }

    /*@Scheduled(cron = "0 0 0 * * ?") // At midnight every day
    public void scheduleDailyAggregation() {
        aggregateDailyWeather();
    }*/
    @Scheduled(cron = "0 0 0 * * ?") // At midnight every day
    public void scheduleDailyAggregation() {
        aggregateDailyWeather();
    }
    public void aggregateDailyWeather() {
        System.out.println("Daily aggregation started at " + LocalDateTime.now());
        List<WeatherData> todayWeatherData = repository.findAll(); // You might want to filter by date

        if (!todayWeatherData.isEmpty()) {
            DailySummary summary = new DailySummary();
            summary.setDate(LocalDate.now());

            double totalTemp = 0;
            double maxTemp = Double.MIN_VALUE;
            double minTemp = Double.MAX_VALUE;
            double totalHumidity = 0; // New variable for humidity
            double totalWindSpeed = 0; // New variable for wind speed
            Map<String, Integer> weatherCount = new HashMap<>();

            for (WeatherData data : todayWeatherData) {
                totalTemp += data.getTemperature();
                totalHumidity += data.getHumidity(); // Aggregate humidity
                totalWindSpeed += data.getWindSpeed(); // Aggregate wind speed

                if (data.getTemperature() > maxTemp) maxTemp = data.getTemperature();
                if (data.getTemperature() < minTemp) minTemp = data.getTemperature();

                weatherCount.put(data.getMainCondition(), weatherCount.getOrDefault(data.getMainCondition(), 0) + 1);
            }

            // Calculate averages
            summary.setAverageTemperature(totalTemp / todayWeatherData.size());
            summary.setAverageHumidity(totalHumidity / todayWeatherData.size()); // Average humidity
            summary.setAverageWindSpeed(totalWindSpeed / todayWeatherData.size()); // Average wind speed
            summary.setMaxTemperature(maxTemp);
            summary.setMinTemperature(minTemp);

            // Find dominant weather condition
            if (!weatherCount.isEmpty()) {
                String dominantWeather = weatherCount.entrySet().stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse("Unknown");
                summary.setDominantWeatherCondition(dominantWeather);
            } else {
                summary.setDominantWeatherCondition("No Data");
            }

            dailySummaryRepository.save(summary);
        }
    }



    public void checkAlerts(WeatherData latestData) {
        AlertThreshold threshold = alertThresholdRepository.findByCondition("temperature"); // Example for temperature

        if (latestData.getTemperature() > threshold.getThresholdValue()) {
            if (!temperatureAlertSent) {
                System.out.println("Alert: Temperature exceeded " + threshold.getThresholdValue());
                // Trigger email alert or other notifications here
                temperatureAlertSent = true;
            }
        } else {
            temperatureAlertSent = false; // Reset alert if temperature is back to normal
        }
    }


