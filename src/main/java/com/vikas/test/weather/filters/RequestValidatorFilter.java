package com.vikas.test.weather.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
@Order(1)
public class RequestValidatorFilter extends  OncePerRequestFilter  {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String zip = "";
		String pathInfo = request.getRequestURI();
	    if (pathInfo != null) {
	        String[] parts = pathInfo.split("/");
	        int indexOfName = Arrays.asList(parts).indexOf("zip");
	        if (indexOfName != -1) {
	            Optional<String> zipO = Optional.of(parts[indexOfName + 1]);
	            zip = zipO.orElse("");
	        }

	    }
		try {
			if (zip.isEmpty() || zip.length()>5 || !isInteger(zip)) {
				throw new IllegalStateException();
			}
			filterChain.doFilter(request, response);
		} catch (IllegalStateException ie) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			response.getWriter().write(convertObjectToJson("Zip is invalid"));
		}
	}
	
	public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}


}
