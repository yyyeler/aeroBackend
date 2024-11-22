package com.weather.aero.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    WeatherService weatherService;

    @GetMapping("/{location}/{type}")
    public ResponseEntity<String> getWeather(@PathVariable String location, @PathVariable String type) {

        if (location.isEmpty() || type.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Location and type cannot be empty");
        }

        try 
        {
            return ResponseEntity.ok(weatherService.getWeather(location,(type.equals("Fahrenheit")?"us":"metric")).getResponse());
        } 
        catch (Exception e) 
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Can not produce weather data right now");
        }
    }
    
}
