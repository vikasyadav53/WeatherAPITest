package com.vikas.test.weather.repositories.impl;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.vikas.test.weather.models.OpenweatherapiJson;
import com.vikas.test.weather.repositories.WeatherDataJSONRepo;

@Repository
public class WeatherDataJSONRepoImpl<O extends OpenweatherapiJson> implements WeatherDataJSONRepo<OpenweatherapiJson>{
	
	@Value("${free.openweather.service.baseurl}")
	private String baseUrl;
	
	@Value("${free.openweather.service.appid}")
	private String APPID;
	
	@SuppressWarnings("unchecked")
	public O getWeatherData(String zip) throws URISyntaxException{
		O openweatherapiJson = null;
		URI uri = new URI(baseUrl+"?zip="+zip+",us&appid="+APPID);
		RestTemplate restemplate = new RestTemplate();
		ResponseEntity<OpenweatherapiJson> responseEntity = restemplate.getForEntity(uri,  OpenweatherapiJson.class);
		openweatherapiJson= responseEntity.getStatusCodeValue() == 200 ? (O)responseEntity.getBody() : null;
		return openweatherapiJson ;
	}

}
