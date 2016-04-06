package com.firmname.travel.common;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.firmname.travel.util.Logger;

public class HttpTestBase {
	private final String urlPrefix = "http://localhost:8080/travel/";
	private final RestTemplate restTemplate = new RestTemplate();
	
	protected <T> T getForObject(String urlAfterRoot, Class<T> responseType){
		return restTemplate.getForObject(prepareUrl(urlAfterRoot), responseType);
	}
	
	protected <T> ResponseEntity<T> getForEntity(String urlAfterRoot, Class<T> responseType){
		return restTemplate.getForEntity(prepareUrl(urlAfterRoot), responseType);
	}

	protected <T> ResponseEntity<T> postForEntity(String urlAfterRoot, Object request, Class<T> responseType){
		return restTemplate.postForEntity(prepareUrl(urlAfterRoot), request, responseType);
	}
	
	protected void put(String urlAfterRoot, Object request, Object... urlVariables){
		try{
			restTemplate.put(prepareUrl(urlAfterRoot), request, urlVariables);
		} catch(RestClientException e){ 
			Logger.error("fail to put request to server", e);
		}
	}
	
	protected HttpEntity<String> jsonHeader(Object requestBody){
		try{
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
			return new HttpEntity<String>(new ObjectMapper().writeValueAsString(requestBody), headers);
		} catch(JsonProcessingException e){
			Logger.error("fail to generate application/json header", e);
			return null;
		}
	}
	private String prepareUrl(String partialUrl){
		return urlPrefix + partialUrl;
	}
}
