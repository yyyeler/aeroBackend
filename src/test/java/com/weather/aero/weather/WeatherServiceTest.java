package com.weather.aero.weather;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @InjectMocks
    private WeatherService weatherService;

    public WeatherServiceTest()
    {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateWeather() throws Exception
    {
        Weather weather = createMockWeather();

        when(weatherRepository.save(weather)).thenReturn(weather);

        Weather dbWeather = weatherService.createWeather(weather);
        
        assertNotNull(dbWeather.getId());
        assertEquals("London, UK",dbWeather.getLocation());
        assertEquals("us",dbWeather.getType());
        assertEquals("2024-11-22",dbWeather.getDate());
        assertEquals("json",dbWeather.getResponse());
    
        verify(weatherRepository, times(1)).save(weather);
    }

    @Test
    void testGetWeather()
    {
        Weather weather = createMockWeather();

        weatherRepository.save(weather);

        when(weatherRepository.findWeatherByParams(weather.getLocation(), weather.getType(), weather.getDate())).thenReturn(weather);

        Weather dbWeather;

        try {
            dbWeather = weatherService.getWeather(weather.getLocation(), weather.getType());
        } catch (Exception e) {
            dbWeather = null;
        }
        
        assertNotNull(dbWeather);
        assertNotNull(dbWeather.getId());
        assertEquals("London, UK",dbWeather.getLocation());
        assertEquals("us",dbWeather.getType());
        assertEquals("2024-11-22",dbWeather.getDate());
        assertEquals("json",dbWeather.getResponse());
        verify(weatherRepository, times(1)).findWeatherByParams(weather.getLocation(), weather.getType(), weather.getDate());

    }

    public Weather createMockWeather()
    {
        Weather weather = new Weather("London, UK", "us","2024-11-22" ,"json");
        weather.setId(1L);

        return weather;
    }
}
