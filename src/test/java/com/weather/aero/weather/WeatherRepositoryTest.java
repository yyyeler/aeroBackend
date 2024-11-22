package com.weather.aero.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherRepositoryTest {
    @Autowired
    private WeatherRepository weatherRepository;

    @Test
    void testFindWeatherByParams()
    {
        Weather weather = new Weather("London, UK", "us","2024-11-22" ,"json");
        weatherRepository.deleteAll();
        weatherRepository.save(weather);

        Weather dbWeather = weatherRepository.findWeatherByParams(weather.getLocation(),weather.getType(),weather.getDate());
        
        assertNotNull(dbWeather.getId());
        assertEquals("London, UK",dbWeather.getLocation());
        assertEquals("us",dbWeather.getType());
        assertEquals("2024-11-22",dbWeather.getDate());
        assertEquals("json",dbWeather.getResponse());
    }
}
