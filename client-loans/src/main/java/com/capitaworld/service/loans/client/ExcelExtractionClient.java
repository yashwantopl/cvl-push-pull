package com.capitaworld.service.loans.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.model.ExcelRequest;
import com.capitaworld.service.loans.model.ExcelResponse;

public class ExcelExtractionClient {
//	private static Logger logger = LoggerFactory.getLogger(ExcelExtractionClient.class);

	private static final String PING_EXCEL_EXTRACTION ="cw_excel/ping";
	
	private static final String READ_CMA = "/cw_excel/read_cma";
	private static final String INACTIVE_CMA = "/cw_excel/inactive_cma";
	private static final String READ_DPR = "/cw_excel/read_dpr";
	private static final String INACTIVE_DPR = "/cw_excel/inactive_dpr";
	private static final String READ_BS = "/cw_excel/read_bs";
	private static final String INACTIVE_BS = "/cw_excel/inactive_bs";
	
	
	
	private String matchBaseUrl;
	
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
			return  "Excel Extration service is not available";
		}
	}
	
	public ExcelResponse readCMA(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(READ_CMA);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse readDPR(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(READ_DPR);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveCMA(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(INACTIVE_CMA);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveDPR(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(INACTIVE_DPR);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse readBS(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(READ_BS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveBS(ExcelRequest request) throws ExcelException {
		String url = matchBaseUrl.concat(INACTIVE_BS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	private HttpEntity<ExcelRequest> setHttpHeader(ExcelRequest request){
		HttpHeaders headers = new HttpHeaders();
		headers.set("req_auth", "true");
	    return new HttpEntity<ExcelRequest>(request, headers);
	}
}
