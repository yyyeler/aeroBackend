package com.weather.aero.weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; 


public interface WeatherRepository extends JpaRepository<Weather,Long> {
    
    @Query(value = "SELECT * FROM WEATHER WHERE location = ?1 AND type = ?2 AND date = ?3 LIMIT 1", nativeQuery = true)
    Weather findWeatherByParams(String location, String type, String date);

}

