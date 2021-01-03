package com.vikas.test.weather.repositories.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.vikas.test.weather.models.Weatherdata;
import com.vikas.test.weather.repositories.WeatherDataXMLRepo;

@Repository
public class WeatherDataXMLRepoImpl<W extends Weatherdata> implements WeatherDataXMLRepo<Weatherdata>{
	
	@Value("${free.openweather.service.baseurl}")
	private String baseUrl;
	
	@Value("${free.openweather.service.appid}")
	private String APPID;
	
	@SuppressWarnings("unchecked")
	public W getWeatherData(String zip) throws URISyntaxException {
		W weatherData = null;
		URI uri = new URI(baseUrl+"?zip="+zip+",us&appid="+APPID+"&mode=xml");
		RestTemplate restemplate = new RestTemplate();
		ResponseEntity<Weatherdata> responseEntity = restemplate.getForEntity(uri,  Weatherdata.class);
		weatherData= responseEntity.getStatusCodeValue() == 200 ? (W)responseEntity.getBody() : null;
		return weatherData ;
	}

}
