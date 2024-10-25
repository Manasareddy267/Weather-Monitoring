package com.example.weather;

import com.example.weather.model.DailySummary;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.DailySummaryRepository;
import com.example.weather.repository.WeatherDataRepository;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.when;

public class DailyWeatherSummaryTest {

    @InjectMocks
    private WeatherService weatherService;

    @Mock
    private WeatherDataRepository weatherDataRepository;

    @Mock
    private DailySummaryRepository dailySummaryRepository;

    @Test
    public void testAggregateDailyWeather() {
        WeatherData weatherData1 = new WeatherData();
        weatherData1.setTemperature(25);
        weatherData1.setMainCondition("Clear");

        WeatherData weatherData2 = new WeatherData();
        weatherData2.setTemperature(30);
        weatherData2.setMainCondition("Clear");

        when(weatherDataRepository.findAll()).thenReturn(Arrays.asList(weatherData1, weatherData2));

        weatherService.aggregateDailyWeather();

        Mockito.verify(dailySummaryRepository).save(Mockito.any(DailySummary.class));
    }
}