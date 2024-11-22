package com.weather.aero.weather;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.springframework.lang.NonNull;



@Entity
@Table(name = "weather", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"location", "type", "date"}) 
})
public class Weather {
     
    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String location;
    
    @NonNull
    private String type;
    
    @NonNull
    private String date;

    @NonNull
    @Column(columnDefinition = "TEXT")
    private String response;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getResponse() {
        return response;
    }

    public Weather( ) 
    {}

    public Weather(String location , String type, String date , String response) 
    {
        this.location=location;
        this.type=type;
        this.date=date;
        this.response=response;
    }
}


