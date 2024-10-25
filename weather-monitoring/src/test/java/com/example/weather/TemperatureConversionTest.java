package com.example.weather;

import com.example.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TemperatureConversionTest {

    @Test
    public void testKelvinToCelsius() {
        WeatherService service = new WeatherService();
        double kelvin = 273.15;
        double celsius = service.kelvinToCelsius(kelvin);
        assertEquals(0.0, celsius, 0.01);
    }

    @Test
    public void testKelvinToFahrenheit() {
        WeatherService service = new WeatherService();
        double kelvin = 273.15;
        double fahrenheit = service.kelvinToFahrenheit(kelvin); // Assuming you have this method
        assertEquals(32.0, fahrenheit, 0.01);
    }
}
