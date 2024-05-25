package com.app.Weather.repository;




import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.Weather.beans.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
	
	List<Weather> findByLongitudeAndLatitude(Double longitude, Double latitude);
	
}
