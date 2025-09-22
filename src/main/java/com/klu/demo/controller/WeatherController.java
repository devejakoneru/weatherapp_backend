package com.klu.demo.controller;
import java.util.List;

import com.klu.demo.model.User;
import com.klu.demo.model.WeatherRecord;
import com.klu.demo.repository.UserRepository;
import com.klu.demo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private UserRepository userRepository;

    // Add weather record
    @PostMapping("/weather")
    public WeatherRecord addWeather(@RequestBody WeatherRequest request) {
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) return null;

        WeatherRecord weather = new WeatherRecord();
        weather.setCity(request.getCity());
        weather.setTemperature(request.getTemperature());
        weather.setDescription(request.getDescription());
        weather.setUser(user);

        return weatherRepository.save(weather);
    }

    // Get all weather records
    @GetMapping("/weather")
    public List<WeatherRecord> getAllWeather() {
        return weatherRepository.findAll();
    }

    // DTO for request
    public static class WeatherRequest {
        private String city;
        private Float temperature;
        private String description;
        private Long userId;

        public String getCity() { return city; }
        public void setCity(String city) { this.city = city; }
        public Float getTemperature() { return temperature; }
        public void setTemperature(Float temperature) { this.temperature = temperature; }
        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
        public Long getUserId() { return userId; }
        public void setUserId(Long userId) { this.userId = userId; }
    }
}
