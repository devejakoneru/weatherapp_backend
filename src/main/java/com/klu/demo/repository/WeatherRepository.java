package com.klu.demo.repository;

import com.klu.demo.model.WeatherRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherRecord, Long> { }

