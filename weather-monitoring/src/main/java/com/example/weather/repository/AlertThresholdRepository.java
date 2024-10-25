package com.example.weather.repository;

import com.example.weather.model.AlertThreshold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertThresholdRepository extends JpaRepository<AlertThreshold, Long> {
    AlertThreshold findByCondition(String condition);
}
