package com.capitaworld.service.loans.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ExcelRequest;
import com.capitaworld.service.loans.model.ExcelResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.LogDetailsModel;

public class LoansClient {
	
	private static final String READ_CMA = "/cw_excel/read_cma";
	private static final String INACTIVE_CMA = "/cw_excel/inactive_cma";
	private static final String READ_DPR = "/cw_excel/read_dpr";
	private static final String INACTIVE_DPR = "/cw_excel/inactive_dpr";
	private static final String READ_BS = "/cw_excel/read_bs";
	private static final String INACTIVE_BS = "/cw_excel/inactive_bs";
	private static final String LOAN_APPLICATION_DETAILS_BY_USER_ID = "/loan_application/getListByUserIdList";
	private static final String USERNAME_BY_APPLICATON_ID = "/loan_application/getUserNameByApplicationId";
	private static final String USER_ID_BY_APPLICATON_ID = "/loan_application/getUserIdByApplicationId";
	private static final String PRIMARY_VIEW_BY_APPLICATON_ID = "/primaryViewByApplicationId";
	private static final String FINAL_VIEW_BY_APPLICATON_ID = "/finalViewByApplicationId";
	private static final String LOAN_PRODUCT_DETAILS_BY_USER_ID ="/product_master/getListByUserIdList";
	private static final String USERNAME_BY_PRODUCT_ID="/product_master/getUserNameByProductId";
	private static final String USER_ID_BY_PRODUCT_ID="/product_master/getUserIdByProductId";
	private static final String REGISTERD_USERS_DETAILS="/loan_application/getUsersRegisteredLoanDetails";
	private static final String GET_LOAN_DETAILS_ADMIN_PANEL="/loan_application/getLoanDetailsForAdminPanel";
	private static final String GET_FILLED_LOAN_DETAILS_ADMIN_PANEL="/loan_application/getFilledLoanDetailsForAdminPanel";
	private static final String GET_CHATLIST_BY_FP_MAPPING="/loan_application/getChatListByFpMappingId";
	private static final String GET_CHATLIST_BY_APPLICATION_ID="/loan_application/getChatListByApplicationId";
	private static final String CREATE_LOG="/createLog";
	
	
	private String loansBaseUrl;
	private RestTemplate restTemplate;


	public LoansClient(String loansBaseUrl) {
		this.loansBaseUrl = loansBaseUrl;
		restTemplate = new RestTemplate();
	}
	
	public ExcelResponse readCMA(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_CMA);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse readDPR(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_DPR);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveCMA(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_CMA);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveDPR(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_DPR);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse readBS(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_BS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}
	
	public ExcelResponse inactiveBS(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_BS);
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
	
	
	public LoansResponse getLoanDetailsByUserIdList(List<Long> request) throws LoansException {
		String url = loansBaseUrl.concat(LOAN_APPLICATION_DETAILS_BY_USER_ID);
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
		String url = loansBaseUrl.concat(USERNAME_BY_APPLICATON_ID);
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
		String url = loansBaseUrl.concat(PRIMARY_VIEW_BY_APPLICATON_ID).concat("/"+request);
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
		String url = loansBaseUrl.concat(FINAL_VIEW_BY_APPLICATON_ID).concat("/"+request);
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
		String url = loansBaseUrl.concat(USER_ID_BY_APPLICATON_ID);
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
	public LoansResponse getProductDetailsByUserIdList(List<Long> request) throws  LoansException {
		String url = loansBaseUrl.concat(LOAN_PRODUCT_DETAILS_BY_USER_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<List<Long>> entity = new HttpEntity<List<Long>>(request, headers);
			return restTemplate.exchange(url,  HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	public LoansResponse getUserNameByProductMappingId(Long request) throws  LoansException {
		String url = loansBaseUrl.concat(USERNAME_BY_PRODUCT_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
	public LoansResponse getRegisteredUsersDetails() throws  LoansException {
		String url = loansBaseUrl.concat(REGISTERD_USERS_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getRegisteredUsersDetails");
		}
	}
	
	public LoansResponse getLoanDetailsForAdminPanel() throws  LoansException {
		String url = loansBaseUrl.concat(GET_LOAN_DETAILS_ADMIN_PANEL);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	
	public LoansResponse getFilledLoanDetailsForAdminPanel() throws  LoansException {
		String url = loansBaseUrl.concat(GET_FILLED_LOAN_DETAILS_ADMIN_PANEL);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getFilledLoanDetailsForAdminPanel");
		}
	}
	
	public String getUserIdByProductId(Long productId) throws  LoansException {
		String url = loansBaseUrl.concat(USER_ID_BY_PRODUCT_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(productId, headers);
		    LoansResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		    if(response != null){
		    	return response.getData().toString();
		    }else{
		    	throw new LoansException("something went wrong");
		    }
			/*return restTemplate.postForObject(url, request, LoansResponse.class);*/
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("product master is not available");
		}
	}
	
	public LoansResponse getChatListByApplicationId(Long applicationId) throws  LoansException {
		String url = loansBaseUrl.concat(GET_CHATLIST_BY_FP_MAPPING);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(applicationId, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getChatListByApplicationId");
		}
	}
	

	public LoansResponse getChatListByFpMappingId(Long productMappingId) throws  LoansException {
		String url = loansBaseUrl.concat(GET_CHATLIST_BY_APPLICATION_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(productMappingId, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getChatListByProductMappingId");
		}
	}
	
	public LoansResponse createLog(LogDetailsModel logDetailsModel) throws  LoansException {
		String url = loansBaseUrl.concat(CREATE_LOG);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<LogDetailsModel> entity = new HttpEntity<LogDetailsModel>(logDetailsModel, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call create log");
		}
	}

	

}
