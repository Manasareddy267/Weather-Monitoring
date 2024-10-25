package com.example.weather.repository;

import com.example.weather.model.DailySummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailySummaryRepository extends JpaRepository<DailySummary, Long> {
    // Custom query methods (if any) can be defined here
}
