package com.app.Weather.beans;

import java.time.LocalDateTime;

public class WeatherInsight {
	private LocalDateTime forecastTime;
    private boolean conditionMet;

    public WeatherInsight(LocalDateTime forecastTime, boolean conditionMet) {
        this.setForecastTime(forecastTime);
        this.setConditionMet(conditionMet);
    }

	public LocalDateTime getForecastTime() {
		return forecastTime;
	}

	public void setForecastTime(LocalDateTime forecastTime) {
		this.forecastTime = forecastTime;
	}

	public boolean isConditionMet() {
		return conditionMet;
	}

	public void setConditionMet(boolean conditionMet) {
		this.conditionMet = conditionMet;
	}
}
