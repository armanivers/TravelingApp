package com.deap.TravellingApp.api.openWeatherAPI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	@Value("${weatherAPIKey}")
	String weatherAPIKey;
	
	
	public WeatherResponse getCurrentWeather(String cityName) {
	    String url = "http://api.openweathermap.org/data/2.5/weather?q="+cityName+"&units=metric&appid="+weatherAPIKey;
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        return restTemplate.getForObject(url, WeatherResponse.class);
	    }
	    catch (HttpClientErrorException ex) {
	        WeatherResponse response = new WeatherResponse();
	        response.setName("Error loading/reading JSON Response object!");
	        return response;
	    } 	    
	}
	
	public ForecastResponse getForecast(String cityName) {
	    String url = "http://api.openweathermap.org/data/2.5/forecast?q="+cityName+"&units=metric&appid="+weatherAPIKey;
	    
	    RestTemplate restTemplate = new RestTemplate();
	    try {
	        return restTemplate.getForObject(url, ForecastResponse.class);
	    }
	    catch (HttpClientErrorException ex) {
	    	ForecastResponse response = new ForecastResponse();
	        response.setName("Error loading/reading JSON Response object!");
	        return response;
	    } 	    
	}
}
