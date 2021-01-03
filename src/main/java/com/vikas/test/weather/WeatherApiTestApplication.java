package com.vikas.test.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class WeatherApiTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApiTestApplication.class, args);
	}

}
