package com.vikas.test.weather.services;

import java.net.URISyntaxException;

public interface WeatherDetailsService<Weatherdata, OpenweatherapiJson> {
	
	
	public Weatherdata getWeatherdataInXML(String z) throws URISyntaxException;
	
	public OpenweatherapiJson getWeatherdataInJSON(String z) throws URISyntaxException;
	
}
