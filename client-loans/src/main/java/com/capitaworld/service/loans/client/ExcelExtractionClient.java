package com.capitaworld.service.loans.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class ExcelExtractionClient {
	private static Logger logger = LoggerFactory.getLogger(ExcelExtractionClient.class);

	private static final String PING_EXCEL_EXTRACTION ="cw_excel/ping";
	
	private String excelExtractionBaseUrl;
	private RestTemplate restTemplate;
	
	public ExcelExtractionClient(String excelExtractionBaseUrl) {
		this.excelExtractionBaseUrl = excelExtractionBaseUrl;
		restTemplate = new RestTemplate();

	}
	
	
	public String ping() {
		String url = excelExtractionBaseUrl.concat(PING_EXCEL_EXTRACTION);
		try {
			return restTemplate.getForObject(url, String.class);
		} catch (Exception e) {
			return  "Excel extration service is not available";
		}
	}

}
