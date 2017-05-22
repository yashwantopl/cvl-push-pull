package com.capitaworld.service.loans.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;

public class ProductClient {
	private static final String PING_PRODUCT_MASTER ="/product_master/ping";
	private static final String LOAN_PRODUCT_DETAILS_BY_USER_ID ="/product_master/getListByUserIdList";
	private static final String USERNAME_BY_PRODUCT_ID="/product_master/getUserNameByProductId";
	private String productMasterBaseUrl;
	private RestTemplate restTemplate;
	
	
	public ProductClient(String productMasterBaseUrl) {
		this.productMasterBaseUrl = productMasterBaseUrl;
		restTemplate = new RestTemplate();

	}
	
	public String ping() {
		String url = productMasterBaseUrl.concat(PING_PRODUCT_MASTER);
		try {
			return restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			return  "Loan Application service is not available";
		}
	}
	public LoansResponse getLoanDetailsByUserIdList(List<Long> request) throws  LoansException {
		String url = productMasterBaseUrl.concat(LOAN_PRODUCT_DETAILS_BY_USER_ID);
		try {
			return restTemplate.postForObject(url, request, LoansResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	public LoansResponse getUserNameByApplicationId(Long request) throws  LoansException {
		String url = productMasterBaseUrl.concat(USERNAME_BY_PRODUCT_ID);
		try {
			return restTemplate.postForObject(url, request, LoansResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
}
