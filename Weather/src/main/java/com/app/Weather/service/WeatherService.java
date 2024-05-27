package com.app.Weather.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.app.Weather.beans.Weather;
import com.app.Weather.beans.WeatherInsight;
import com.app.Weather.constants.Constants;
import com.app.Weather.repository.WeatherRepository;
/**
 * WeatherService is a service class responsible for providing weather insights based on provided parameters.
 */
@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository; 

    
    /**
     * Retrieves weather insights based on provided parameters.
     * 
     * @param lon       The longitude coordinate.
     * @param lat       The latitude coordinate.
     * @param condition The weather condition for which insights are requested.
     * @return List of WeatherInsight objects containing weather insights.
     */
    public List<WeatherInsight> getWeatherInsight(Double lon, Double lat, String condition) {
    	
    	ValidateParams(lon, lat, condition);
    	
        // Logic to query the database and process weather data
        List<Weather> weatherData = weatherRepository.findByLongitudeAndLatitude(lon, lat);
        return processWeatherData(weatherData, condition);
        
    }

    
    /**
     * Processes weather data to generate weather insights based on the provided condition.
     * 
     * @param weatherData The list of Weather objects to be processed.
     * @param condition   The weather condition for which insights are requested.
     * @return List of WeatherInsight objects containing weather insights.
     */
    private List<WeatherInsight> processWeatherData(List<Weather> weatherData, String condition) {
        List<WeatherInsight> insights = new ArrayList<>();
        for (Weather data : weatherData) {
            boolean metCondition = false;
            if (condition.equals(Constants.weather.VERY_HOT)) {
                metCondition = data.getTemperature() > 30;
            } else if (condition.equals(Constants.weather.RAINY_AND_COLD)) {
                metCondition = data.getTemperature() < 10 && data.getPrecipitation() > 0.5;
            }
            insights.add(new WeatherInsight(data.getForecastTime(), metCondition));
        }
        return insights;
    }
    
    /**
     * Validates the parameters provided for weather insights.
     * 
     * @param lon       The longitude coordinate.
     * @param lat       The latitude coordinate.
     * @param condition The weather condition for which insights are requested.
     * @throws ResponseStatusException if any parameter is invalid or the condition is unknown.
     */
    public void ValidateParams(Double lon, Double lat, String condition) {
    	// Validate parameters
    	if (lon < Integer.parseInt(Constants.weather.MIN_LONGITUDE) || lon > Integer.parseInt(Constants.weather.MAX_LONGITUDE)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.weather.INVALID_LONGITUDE + Constants.weather.MIN_LONGITUDE + " to " + Constants.weather.MAX_LONGITUDE);
        }
    	if (lat < Integer.parseInt(Constants.weather.MIN_LATITUDE) || lat > Integer.parseInt(Constants.weather.MAX_LATITUDE)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.weather.INVALID_LATITUDE + Constants.weather.MIN_LATITUDE + " to " + Constants.weather.MAX_LATITUDE);
         }
    	if (!condition.equals(Constants.weather.VERY_HOT) && !condition.equals(Constants.weather.RAINY_AND_COLD)) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, Constants.weather.INVALID_CONDITION);
         }
    	
		
    
    }
    
   
}
