package com.vikas.test.weather.services.impl;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.vikas.test.weather.models.OpenweatherapiJson;
import com.vikas.test.weather.models.Weatherdata;
import com.vikas.test.weather.repositories.WeatherDataJSONRepo;
import com.vikas.test.weather.repositories.WeatherDataXMLRepo;
import com.vikas.test.weather.services.WeatherDetailsService;

@Service
public class WeatherDetailsServiceImpl<W extends Weatherdata, O extends OpenweatherapiJson> implements WeatherDetailsService<Weatherdata, OpenweatherapiJson>{

	@Autowired
	private WeatherDataJSONRepo<OpenweatherapiJson> weatherDataJSONRepo;
	
	@Autowired
	private WeatherDataXMLRepo<Weatherdata> weatherDataXMLRepo;
	
	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("Weatherdata")
	public W getWeatherdataInXML(String zip) throws URISyntaxException {
		// TODO Auto-generated method stub
		return (W) weatherDataXMLRepo.getWeatherData(zip);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Cacheable("OpenweatherapiJson")
	public O getWeatherdataInJSON(String zip) throws URISyntaxException{
		// TODO Auto-generated method stub
		return (O) weatherDataJSONRepo.getWeatherData(zip);
	}

}
