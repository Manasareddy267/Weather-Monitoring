package com.example.weather.repository;

import com.example.weather.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
    // Additional query methods if necessary
}
