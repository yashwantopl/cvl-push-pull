package com.capitaworld.service.loans.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;

public class LoanApplicationClient {
	private static final String PING_LOAN_APPLICATION = "/loan_application/ping";
	private static final String LOAN_APPLICATION_DETAILS_BY_USER_ID = "/loan_application/getListByUserIdList";
	private static final String USERNAME_BY_APPLICATON_ID = "/loan_application/getUserNameByApplicationId";
	private static final String USER_ID_BY_APPLICATON_ID = "/loan_application/getUserIdByApplicationId";
	private static final String PRIMARY_VIEW_BY_APPLICATON_ID = "/primaryViewByApplicationId";
	private static final String FINAL_VIEW_BY_APPLICATON_ID = "/finalViewByApplicationId";
	private String loanApplicationBaseUrl;
	private RestTemplate restTemplate;

	public LoanApplicationClient(String loanApplicationBaseUrl) {
		this.loanApplicationBaseUrl = loanApplicationBaseUrl;
		restTemplate = new RestTemplate();

	}

	public String ping() {
		String url = loanApplicationBaseUrl.concat(PING_LOAN_APPLICATION);
		try {
			return restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			return "Loan Application service is not available";
		}
	}

	public LoansResponse getLoanDetailsByUserIdList(List<Long> request) throws LoansException {
		String url = loanApplicationBaseUrl.concat(LOAN_APPLICATION_DETAILS_BY_USER_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<List<Long>> entity = new HttpEntity<List<Long>>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request,
			 * LoansResponse.class);
			 */
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public LoansResponse getUserNameByApplicationId(Long request) throws LoansException {
		String url = loanApplicationBaseUrl.concat(USERNAME_BY_APPLICATON_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request,
			 * LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
	public LoansResponse getPrimaryViewByApplicationId(Long request) throws LoansException {
		String url = loanApplicationBaseUrl.concat(PRIMARY_VIEW_BY_APPLICATON_ID).concat("/"+request);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request,
			 * LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
	public LoansResponse getFinalViewByApplicationId(Long request) throws LoansException {
		String url = loanApplicationBaseUrl.concat(FINAL_VIEW_BY_APPLICATON_ID).concat("/"+request);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request,
			 * LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public String getUserIdByApplicationId(Long applicationId) throws LoansException {
		String url = loanApplicationBaseUrl.concat(USER_ID_BY_APPLICATON_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(applicationId, headers);
			LoansResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			if (response != null) {
				return response.getData().toString();
			} else {
				throw new LoansException("User Id not found");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

}
