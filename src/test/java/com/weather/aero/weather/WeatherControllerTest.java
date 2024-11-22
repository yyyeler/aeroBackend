package com.weather.aero.weather;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherService weatherService;

    @Test
    void testGetWeather() throws Exception
    {
        String location = "London, UK";        
        String type = "Fahrenheit";
        String response = "{\n" + //
                        "  \"queryCost\": 7,\n" + //
                        "  \"latitude\": 51.5064,\n" + //
                        "  \"longitude\": -0.12721,\n" + //
                        "  \"resolvedAddress\": \"London, England, United Kingdom\",\n" + //
                        "  \"address\": \"London, UK\",\n" + //
                        "  \"timezone\": \"Europe/London\",\n" + //
                        "  \"tzoffset\": 0.0,\n" + //
                        "  \"days\": [\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 49.4,\n" + //
                        "      \"tempmin\": 40.7,\n" + //
                        "      \"temp\": 46.6,\n" + //
                        "      \"conditions\": \"Partially cloudy\",\n" + //
                        "      \"icon\": \"partly-cloudy-day\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 51.9,\n" + //
                        "      \"tempmin\": 41.1,\n" + //
                        "      \"temp\": 47.0,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 52.4,\n" + //
                        "      \"tempmin\": 45.2,\n" + //
                        "      \"temp\": 47.8,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 47.4,\n" + //
                        "      \"tempmin\": 44.5,\n" + //
                        "      \"temp\": 45.8,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 53.5,\n" + //
                        "      \"tempmin\": 34.0,\n" + //
                        "      \"temp\": 40.4,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"snow\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 42.3,\n" + //
                        "      \"tempmin\": 31.5,\n" + //
                        "      \"temp\": 35.8,\n" + //
                        "      \"conditions\": \"Partially cloudy\",\n" + //
                        "      \"icon\": \"partly-cloudy-day\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 38.4,\n" + //
                        "      \"tempmin\": 31.7,\n" + //
                        "      \"temp\": 34.6,\n" + //
                        "      \"conditions\": \"Partially cloudy\",\n" + //
                        "      \"icon\": \"partly-cloudy-day\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 42.2,\n" + //
                        "      \"tempmin\": 32.1,\n" + //
                        "      \"temp\": 35.8,\n" + //
                        "      \"conditions\": \"Partially cloudy\",\n" + //
                        "      \"icon\": \"partly-cloudy-day\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 55.7,\n" + //
                        "      \"tempmin\": 33.9,\n" + //
                        "      \"temp\": 44.7,\n" + //
                        "      \"conditions\": \"Rain, Overcast\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 60.5,\n" + //
                        "      \"tempmin\": 56.2,\n" + //
                        "      \"temp\": 57.9,\n" + //
                        "      \"conditions\": \"Rain, Overcast\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 56.6,\n" + //
                        "      \"tempmin\": 46.3,\n" + //
                        "      \"temp\": 50.9,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 53.7,\n" + //
                        "      \"tempmin\": 44.1,\n" + //
                        "      \"temp\": 49.3,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 54.8,\n" + //
                        "      \"tempmin\": 47.3,\n" + //
                        "      \"temp\": 50.0,\n" + //
                        "      \"conditions\": \"Rain, Partially cloudy\",\n" + //
                        "      \"icon\": \"rain\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 49.2,\n" + //
                        "      \"tempmin\": 44.3,\n" + //
                        "      \"temp\": 46.2,\n" + //
                        "      \"conditions\": \"Partially cloudy\",\n" + //
                        "      \"icon\": \"partly-cloudy-day\"\n" + //
                        "    },\n" + //
                        "    {\n" + //
                        "      \"tempmax\": 55.1,\n" + //
                        "      \"tempmin\": 44.1,\n" + //
                        "      \"temp\": 50.5,\n" + //
                        "      \"conditions\": \"Overcast\",\n" + //
                        "      \"icon\": \"cloudy\"\n" + //
                        "    }\n" + //
                        "  ]\n" + //
                        "}";

        Weather weather = new Weather(location, "us",getDateString(LocalDate.now()) ,response);

        when(this.weatherService.getWeather(location,type)).thenReturn(weather);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/weather/{location}/{type}", location, type))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.location").value(location));
    }

    public String getDateString(LocalDate date)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return date.format(formatter);
    }
}
