package com.capitaworld.service.loans.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;

public class ProductMasterClient {
	private static final String PING_PRODUCT_MASTER ="/product_master/ping";
	private static final String USERNAME_BY_PRODUCT_ID="/product_master/getUserNameByProductId";
	private String productMasterBaseUrl;
	private RestTemplate restTemplate;
	
	
	public ProductMasterClient(String productMasterBaseUrl) {
		this.productMasterBaseUrl = productMasterBaseUrl;
		restTemplate = new RestTemplate();

	}
	
	public String ping() {
		String url = productMasterBaseUrl.concat(PING_PRODUCT_MASTER);
		try {
			return restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			return  "product master service is not available";
		}
	}
	
	public LoansResponse getUserNameByProductId(Long request) throws  LoansException {
		String url = productMasterBaseUrl.concat(USERNAME_BY_PRODUCT_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
		    return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			/*return restTemplate.postForObject(url, request, LoansResponse.class);*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("product masteis not available");
		}
	}
	
}
