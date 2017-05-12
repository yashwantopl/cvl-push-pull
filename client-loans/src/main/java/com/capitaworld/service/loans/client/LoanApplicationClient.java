package com.capitaworld.service.loans.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;

public class LoanApplicationClient {
	private static final String PING_LOAN_APPLICATION ="/loan_application/ping";
	private static final String LOAN_APPLICATION_DETAILS_BY_USER_ID ="/loan_application/getListByUserIdList";
	private static final String USERNAME_BY_APPLICATON_ID="/loan_application/getUserNameByApplicationId";
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
			return  "Loan Application service is not available";
		}
	}
	public LoansResponse getLoanDetailsByUserIdList(List<Long> request) throws  LoansException {
		String url = loanApplicationBaseUrl.concat(LOAN_APPLICATION_DETAILS_BY_USER_ID);
		try {
			return restTemplate.postForObject(url, request, LoansResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	public LoansResponse getUserNameByApplicationId(Long request) throws  LoansException {
		String url = loanApplicationBaseUrl.concat(USERNAME_BY_APPLICATON_ID);
		try {
			return restTemplate.postForObject(url, request, LoansResponse.class);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
}
