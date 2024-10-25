package com.example.weather;

import com.example.weather.dto.WeatherResponse;
import com.example.weather.model.WeatherData;
import com.example.weather.repository.WeatherDataRepository;
import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class DataRetrievalTest {

    @Autowired
    private WeatherService weatherService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private WeatherDataRepository weatherDataRepository;

    @Test
    public void testFetchWeatherData() {
        String mockCity = "Delhi";
        WeatherResponse mockResponse = new WeatherResponse(); // Initialize mock response with data
        // Assume WeatherResponse has the required fields set

        String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s,IN&appid=%s", mockCity, "your_api_key");
        when(restTemplate.getForObject(url, WeatherResponse.class)).thenReturn(mockResponse);

        weatherService.fetchWeatherData(); // Call the method under test

        // Validate that the repository's save method was called with the expected WeatherData
        Mockito.verify(weatherDataRepository).save(Mockito.any(WeatherData.class));
    }
}
