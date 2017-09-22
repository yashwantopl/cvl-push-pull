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
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.LogDetailsModel;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;

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
	private static final String GET_CHATLIST_BY_APPLICATION_ID="/product_master/getChatListByApplicationId";
	private static final String CREATE_LOG="/createLog";
	private static final String SAVE_ACHIEVEMENT_DETAILS = "/achievment_details/save";
	private static final String SAVE_ASSOCIATED_CONCERN_DETAIL="/associated_concern_details/save";
	private static final String CORPORATE_APPLICATION_DETAILS= "/fs_profile/save";
	private static final String CREDIT_RATING_ORGANIZATION_DETAILS = "/credit_rating_organization_details/save";
	private static final String EXISTING_PRODUCT_DETAILS = "/existing_product_details/save";
	private static final String MEANS_OF_FINANCE = "/means_of_finance/save";
	private static final String FINANCIAL_ARRANGEMENT_DETAILS = "/financial_arrangement_details/save";
	private static final String FUTURE_FINANCIAL_ESTIMATE_DETAILS = "/future_financial_estimate_details/save";
	private static final String GUARANTORS_CORPORATE_DETAILS = "/guarantors_corporate_details/save";
	private static final String MONTHLY_TURNOVER_DETAILS = "/monthly_turnover_details/save";
	private static final String OWNERSHIP_DETAILS = "/ownership_details/save";
	private static final String PAST_FINANCIAL_ESTIMATE_DETAILS = "/past_financial_estimate_details/save";
	private static final String PROMOTOR_BACKGROUND_DETAILS = "/promotor_background_details/save";
	private static final String PROPOSED_PRODUCT_DETAILS = "/proposed_product_details/save";
	private static final String SECURITY_CORPORATE_DETAILS = "/security_corporate_details/save";
	private static final String SAVE_TERM_LOAN_FINAL = "/term_loan/final/save";
	private static final String SAVE_TERM_LOAN_PRIMARY = "/term_loan/primary/save";
	private static final String TOTAL_COST_OF_PROJECT = "/total_cost_of_project/save";
	private static final String WORKING_CAPITAL_PRIMARY = "/working_capital/primary/save";
	private static final String WORKING_CAPITAL_FINAL = "/working_capital/final/save";
	private static final String WORKING_CAPITAL_FINAL_PING = "/working_capital/ping";
	
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
	
	public LoansResponse getRegisteredUsersDetails(Long userType) throws  LoansException {
		String url = loansBaseUrl.concat(REGISTERD_USERS_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<Long> entity = new HttpEntity<Long>(userType, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		
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
		String url = loansBaseUrl.concat(GET_CHATLIST_BY_APPLICATION_ID);
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
		String url = loansBaseUrl.concat(GET_CHATLIST_BY_FP_MAPPING);
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


	public LoansResponse saveAchievementdetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_ACHIEVEMENT_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveAssociatedConcernDetail(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_ASSOCIATED_CONCERN_DETAIL);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveCorporateApplicant(CorporateApplicantRequest applicantRequest) throws ExcelException {
		String url = loansBaseUrl.concat(CORPORATE_APPLICATION_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<CorporateApplicantRequest> entity = new HttpEntity<CorporateApplicantRequest>(applicantRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveCreditRatingOrganizationDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(CREDIT_RATING_ORGANIZATION_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveExistingProductDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(EXISTING_PRODUCT_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveMeansOfFinance(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(MEANS_OF_FINANCE);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveFinancialArrangementDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(FINANCIAL_ARRANGEMENT_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveFutureFinancialEstimatesDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(FUTURE_FINANCIAL_ESTIMATE_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveGuarantorsCorporateDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(GUARANTORS_CORPORATE_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveMonthlyTurnoverDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(MONTHLY_TURNOVER_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveOwnershipDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(OWNERSHIP_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse savePastFinancialEstimateDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(PAST_FINANCIAL_ESTIMATE_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse savePromotorBackgroundDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(PROMOTOR_BACKGROUND_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveProposedProductDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(PROPOSED_PRODUCT_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveSecurityCorporateDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SECURITY_CORPORATE_DETAILS);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveTermLoanFinal(FinalTermLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_TERM_LOAN_FINAL);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FinalTermLoanRequest> entity = new HttpEntity<FinalTermLoanRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveTermLoanPrimary(PrimaryTermLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_TERM_LOAN_PRIMARY);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<PrimaryTermLoanRequest> entity = new HttpEntity<PrimaryTermLoanRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveTotalCostOfProject(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(TOTAL_COST_OF_PROJECT);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveWorkingCapitalPrimary(PrimaryWorkingCapitalLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(WORKING_CAPITAL_PRIMARY);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<PrimaryWorkingCapitalLoanRequest> entity = new HttpEntity<PrimaryWorkingCapitalLoanRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveWorkingCapitalFinal(FinalWorkingCapitalLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(WORKING_CAPITAL_FINAL);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
		    HttpEntity<FinalWorkingCapitalLoanRequest> entity = new HttpEntity<FinalWorkingCapitalLoanRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse test(String a)throws Exception{
		String url = loansBaseUrl.concat(WORKING_CAPITAL_FINAL_PING);
		try {
			/*return restTemplate.postForObject(url, request, ExcelResponse.class);*/
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.set("test", a);
		    HttpEntity<FinalWorkingCapitalLoanRequest> entity = new HttpEntity<FinalWorkingCapitalLoanRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
		
	}
	
}
