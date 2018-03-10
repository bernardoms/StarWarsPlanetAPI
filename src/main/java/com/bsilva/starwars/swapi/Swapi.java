package com.bsilva.starwars.swapi;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
public class Swapi {
	//Calling swapi api for get all the planets
	//NOT USED YET ON THE MY API
	public ResponseEntity<RequestWrapper>getAllPlanet() throws Exception{
		
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.add(HttpHeaders.USER_AGENT, "");
			    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			    HttpEntity<String> entity = new HttpEntity<String>(headers);
			    ResponseEntity<RequestWrapper> wrapper = null;
			    try {
			    wrapper = restTemplate.exchange("https://swapi.co/api/planets/",HttpMethod.GET,entity, RequestWrapper.class);
			    }catch(HttpStatusCodeException e) {
			    	throw new Exception("Error caling swapi: " + e);
			    }
			    return wrapper;
	}
	//Calling Swapi for search for a planet by name
	public ResponseEntity<RequestWrapper>getPlanetByName(String nome) throws Exception {
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
			    headers.add(HttpHeaders.USER_AGENT, "");
			    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			    HttpEntity<String> entity = new HttpEntity<String>(headers);
			    ResponseEntity<RequestWrapper> wrapper = null;
			    try {
			    wrapper = restTemplate.exchange("https://swapi.co/api/planets/?search=" + nome,HttpMethod.GET,entity, RequestWrapper.class);
			    }catch(HttpStatusCodeException e) {
			    	throw new Exception("Error caling swapi: " + e);
			    }
			    return wrapper;
		}
	
}

