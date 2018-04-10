package com.capitaworld.service.loans.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.model.CMADetailResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;

public class LoansClient1 {

	private static final Logger logger = LoggerFactory.getLogger(LoansClient1.class);

	private String loansBaseUrl;
	private RestTemplate restTemplate;
	
	
	private static final String GET_CMA_DETAIL = "/loan_eligibility/getCmaDetail/";
	private static final String CORPORATE_APPLICATION_DETAILS_GET = "/fs_profile/get_application_client";
	
	public LoansClient1(String loansBaseUrl) {
		
		logger.info("In Client 1");
		this.loansBaseUrl = loansBaseUrl;
		restTemplate = new RestTemplate();
	}

	public CMADetailResponse getCMADetils(Long appId) throws ExcelException {
		
		System.out.println("Getting CMA DEtails");
		String url = loansBaseUrl.concat(GET_CMA_DETAIL).concat("/" + appId);
		try {
			/*
			 * return restTemplate.postForObject(url, request,
			 * ExcelResponse.class);
			 */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, CMADetailResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}

	}

	
	public LoansResponse getCorporateApplicant(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(CORPORATE_APPLICATION_DETAILS_GET).concat("/" + applicationId);
		System.out.println("url for Getting Corporate Details From Client=================>" + url + " and For Application Id====>" + applicationId);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
}
