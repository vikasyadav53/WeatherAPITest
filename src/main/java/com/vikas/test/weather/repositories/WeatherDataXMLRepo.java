package com.vikas.test.weather.repositories;

import java.net.URISyntaxException;

public interface WeatherDataXMLRepo <Weatherdata>{
	
	public Weatherdata getWeatherData(String z) throws URISyntaxException;

}
