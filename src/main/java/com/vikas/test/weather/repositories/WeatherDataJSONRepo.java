package com.vikas.test.weather.repositories;

import java.net.URISyntaxException;

public interface WeatherDataJSONRepo <OpenweatherapiJson>{
	
	public OpenweatherapiJson getWeatherData(String z) throws URISyntaxException;

}
