package com.app.Weather.beans;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;




@Entity
public class Weather {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double longitude;
    private double latitude;
    private LocalDateTime forecastTime;
    private double temperature;
    private double precipitation;

    public Weather() {}

  
    public Weather(double longitude, double latitude, LocalDateTime forecastTime, double temperature, double precipitation) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.forecastTime = forecastTime;
        this.temperature = temperature;
        this.precipitation = precipitation;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LocalDateTime getForecastTime() {
        return forecastTime;
    }

    public void setForecastTime(LocalDateTime forecastTime) {
        this.forecastTime = forecastTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

   
}