package com.vikas.test.weather.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.vikas.test.weather.models.Weatherdata;
import com.vikas.test.weather.repositories.WeatherDataXMLRepo;
import com.vikas.test.weather.repositories.impl.WeatherDataXMLRepoImpl;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDetailsServiceTest {
	
	@Value("${free.openweather.service.baseurl}")
	private String baseUrl;
	
	@Value("${free.openweather.service.appid}")
	private String APPID;
	
	private Weatherdata weatherDataM;
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private WeatherDataXMLRepo<Weatherdata> weatherDataXMLRepo = new WeatherDataXMLRepoImpl<>();
	
	@Before
	public void createMockWeatherData() {
		weatherDataM = (Weatherdata)mockResponse("WeatherdataSampleResponse.xml");
	}
	
	@Test
	public void testXMLWeatherResponseByMockito_whenGetIsCalled_shouldReturnMockedObject() throws URISyntaxException{
		URI uri = new URI("http://api.openweathermap.org/data/2.5/forecast?zip=940401,us&appid=eec13ee843a58de6d2082a7964333175&mode=xml");
		Mockito.when(restTemplate.getForEntity(uri,  Weatherdata.class)).thenReturn(new ResponseEntity<>(weatherDataM, HttpStatus.OK));
	
		Weatherdata weatherData = (Weatherdata)weatherDataXMLRepo.getWeatherData("940401");
		
		assertEquals(weatherDataM.getLocation().getName(), weatherData.getLocation().getName());
	
	}
	
	private Object mockResponse( String responseFileName) {
		
		Object response = null; 
		
		try {
			JAXBContext jx = JAXBContext.newInstance("com.vikas.test.weather.models");
			Unmarshaller um = jx.createUnmarshaller();
			File file = new File(responseFileName);
			FileInputStream inputStream = new FileInputStream(file);
			response = um.unmarshal(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
		
	}

}
