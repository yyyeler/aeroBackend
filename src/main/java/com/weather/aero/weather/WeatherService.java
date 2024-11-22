package com.weather.aero.weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class WeatherService {
    @Autowired
    WeatherRepository weatherRepository;

    @Value("${aero.weather.app.api.key}")
    private String key;

    private final WebClient.Builder webClientBuilder;

    public WeatherService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public Weather createWeather(Weather weather) throws Exception
    {
        return weatherRepository.save(weather);
    }

    public Weather getWeather(String location, String type) throws Exception
    {
        LocalDate today = LocalDate.now();
        Weather weather = weatherRepository.findWeatherByParams(location, type, getDateString(today));

        if(weather == null) 
        {
            Weather tempWeather = new Weather(location, type, getDateString(today), getWeatherDataFromExternalApi(location, type, today).block());
            weather = createWeather(tempWeather);
        }
        
        return weather;
    }

    public String getDateString(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }

    public Mono<String> getWeatherDataFromExternalApi(String location, String type, LocalDate today)
    {
        String date1 = getDateString(today.minusDays(7)); 
        String date2 = getDateString(today.plusDays(7)); 
        
        WebClient webClient = webClientBuilder
                                .baseUrl("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline")
                                .build();

        return webClient.get()
                        .uri(uriBuilder -> uriBuilder
                        .path("/{location}/{date1}/{date2}")
                        .queryParam("key", this.key)
                        .queryParam("include", "days")
                        .queryParam("unitGroup", type)
                        .queryParam("elements", "tempmax,tempmin,temp,icon,conditions")
                        .build(location, date1, date2))
                        .retrieve()  
                        .bodyToMono(String.class);  
                        
    }
}
