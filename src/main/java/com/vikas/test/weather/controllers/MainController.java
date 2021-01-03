package com.vikas.test.weather.controllers;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vikas.test.weather.models.OpenweatherapiJson;
import com.vikas.test.weather.models.Weatherdata;
import com.vikas.test.weather.services.WeatherDetailsService;

@RestController
public class MainController {
	
	@Autowired
	private WeatherDetailsService<Weatherdata, OpenweatherapiJson> weatherDetailsService;
	
	@RequestMapping(value="getReport/zip/{zip}", method=RequestMethod.GET, consumes = MediaType.APPLICATION_XML_VALUE, produces=MediaType.APPLICATION_XML_VALUE)
	public Weatherdata getWeatherDataJSON(@PathVariable("zip") String zip) throws URISyntaxException{
		return weatherDetailsService.getWeatherdataInXML(zip);
	}
	
	@RequestMapping(value="getReport/zip/{zip}", method=RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
	public OpenweatherapiJson getWeatherDataXML(@PathVariable("zip") String zip) throws URISyntaxException{
		return weatherDetailsService.getWeatherdataInJSON(zip);
	}

}
