package com.capitaworld.service.loans.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ExcelRequest;
import com.capitaworld.service.loans.model.ExcelResponse;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LogDetailsModel;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MRetailCoAppGuarResponse;
import com.capitaworld.service.loans.model.mobile.MobileFPMatchesRequest;
import com.capitaworld.service.loans.model.mobile.MobileFrameRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;

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
	private static final String LOAN_PRODUCT_DETAILS_BY_USER_ID = "/product_master/getListByUserIdList";
	private static final String USERNAME_BY_PRODUCT_ID = "/product_master/getUserNameByProductId";
	private static final String USER_ID_BY_PRODUCT_ID = "/product_master/getUserIdByProductId";
	private static final String REGISTERD_USERS_DETAILS = "/loan_application/getUsersRegisteredLoanDetails";
	private static final String GET_LOAN_DETAILS_ADMIN_PANEL = "/loan_application/getLoanDetailsForAdminPanel";
	private static final String GET_UBI_REPORT_1 = "/loan_application/getUbiReport1ForAdminPanel";
	private static final String GET_UBI_REPORT_2 = "/loan_application/getUbiReport2ForAdminPanel";
	private static final String GET_UBI_REPORT_3 = "/loan_application/getUbiReport3ForAdminPanel";
	private static final String GET_UBI_REPORT_4 = "/loan_application/getUbiReport4ForAdminPanel";
	private static final String GET_UBI_REPORT_5 = "/loan_application/getUbiReport5ForAdminPanel";
	private static final String GET_FILLED_LOAN_DETAILS_ADMIN_PANEL = "/loan_application/getFilledLoanDetailsForAdminPanel";
	private static final String GET_CHATLIST_BY_FP_MAPPING = "/loan_application/getChatListByFpMappingId";
	private static final String GET_CHATLIST_BY_APPLICATION_ID = "/product_master/getChatListByApplicationId";
	private static final String CREATE_LOG = "/createLog";
	private static final String SAVE_ACHIEVEMENT_DETAILS = "/achievment_details/save";
	private static final String SAVE_ASSOCIATED_CONCERN_DETAIL = "/associated_concern_details/save";
	private static final String CORPORATE_APPLICATION_DETAILS = "/fs_profile/save";
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
	private static final String UPDATE_LOAN_APPLICATION = "/loan_application/updateLoanApplication";
	private static final String BASIC_DETAIL_URL = "/fs_retail_profile/profile/get_basic_details";
	private static final String LOAN_BASIC_DETAILS = "/loan_application/getLoanBasicDetails";
	private static final String STRING_TO_BINARY_ARRAY = "/convertToByteArray";

	private static final String MOBILE_LOANLIST = "/mobile/loanList";
	private static final String MOBILE_GET_APPLICANT = "/mobile/getApplicantDetails";
	private static final String MOBILE_SAVE_APPLICANT = "/mobile/saveApplicantDetails";
	private static final String MOBILE_LOCK_PRIMARY = "/mobile/lockPrimary";
	private static final String MOBILE_GET_COAPPLICANT = "/mobile/getCoApplicantDetails";
	private static final String MOBILE_SAVE_COAPPLICANT = "/mobile/saveCoApplicantDetails";
	private static final String MOBILE_GET_GUARANTOR = "/mobile/getGuarantorDetails";
	private static final String MOBILE_SAVE_GUARANTOR = "/mobile/saveGuarantorDetails";
	private static final String MOBILE_LOAN_ELIGIBILITY_HL_CALC_MINMAX = "/loan_eligibility/hl/calc_min_max";
	private static final String MOBILE_LOAN_ELIGIBILITY_HL_GET_ELIGIBLE_TENURE = "/loan_eligibility/hl/get_eligible_tenure";
	private static final String MOBILE_LOAN_ELIGIBILITY_HL_CALC_LOAN_AMOUNT = "/loan_eligibility/hl/calc_home_loan_amount";
	private static final String MOBILE_LOAN_ELIGIBILITY_PL_GET_ELIGIBLE_TENURE = "/loan_eligibility/pl/get_eligible_tenure";
	private static final String MOBILE_LOAN_ELIGIBILITY_PL_CALC_MINMAX = "/loan_eligibility/pl/calc_min_max";
	private static final String MOBILE_LOAN_ELIGIBILITY_LAP_GET_ELIGIBLE_TENURE = "/loan_eligibility/lap/get_eligible_tenure";
	private static final String MOBILE_LOAN_ELIGIBILITY_LAP_CALC_MINMAX = "/loan_eligibility/lap/calc_min_max";
	private static final String MOBILE_LOAN_ELIGIBILITY_LAP_CALC_LAP_AMOUNT = "/loan_eligibility/lap/calc_lap_amount";
	private static final String MOBILE_GET_FP_PRODUCT_LIST = "/mobile/getProductList";
	private static final String MOBILE_SAVE_LOANAPPLICATION = "/mobile/saveLoanApplicationDetails";
	private static final String MOBILE_GET_FP_MATCHES_LIST = "/mobile/fundproviderProposal";
	private static final String MOBILE_GET_FS_MATCHES_LIST = "/mobile/fundSeekerProposal";

	private static final String EXISTING_LOAN_DETAIL_CIBIL = "/existing_loan_details/save_from_cibil";
	private static final String CREDIT_CARD_DETAIL_CIBIL = "/credit_cards_detail/save_from_cibil";

	private static final String CREATE_LOAN_FROM_CAMPAIGN = "/loan_application/create_loan_from_campaign";

	private static final String EKYC_AUTHENTICATION = "/loan_application/getDetailsForEkycAuthentication";
	
	private static final String FS_DETAILS_FOR_PDF = "/fsDetailsForPdf/getDataMap";
	
	private static final String GET_OTHER_DOC_REPORT = "/corporate_upload/uploadDocumentList/get";

	private static final String GET_FULL_PRIMARY_HL = "/home/primary/get_primary_info";
	private static final String GET_FULL_PROFILE = "/fs_retail_profile/profile/get_profile";
	
	private static final String IS_TERM_LOAN_LESS_THAN_LIMIT = "/loan_application/isTermLoanLessThanLimit";
	
	private static final String SET_ELIGIBILITY_AMOUNT = "/loan_application/set_eligibility_amount";
	
	private String loansBaseUrl;
	private RestTemplate restTemplate;

	public LoansClient(String loansBaseUrl) {
		this.loansBaseUrl = loansBaseUrl;
		restTemplate = new RestTemplate();
	}

	public ExcelResponse readCMA(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_CMA);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	public ExcelResponse readDPR(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_DPR);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	public ExcelResponse inactiveCMA(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_CMA);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	public ExcelResponse inactiveDPR(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_DPR);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	public ExcelResponse readBS(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(READ_BS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	public ExcelResponse inactiveBS(ExcelRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(INACTIVE_BS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			return restTemplate.exchange(url, HttpMethod.POST, setHttpHeader(request), ExcelResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Excel Extration service is not available");
		}
	}

	private HttpEntity<ExcelRequest> setHttpHeader(ExcelRequest request) {
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
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
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
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public LoansResponse getPrimaryViewByApplicationId(Long request) throws LoansException {
		String url = loansBaseUrl.concat(PRIMARY_VIEW_BY_APPLICATON_ID).concat("/" + request);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public LoansResponse getFinalViewByApplicationId(Long request) throws LoansException {
		String url = loansBaseUrl.concat(FINAL_VIEW_BY_APPLICATON_ID).concat("/" + request);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(request, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public LoansResponse getDetailsForEkycAuthentication(EkycRequest request) throws LoansException {
		String url = loansBaseUrl.concat(EKYC_AUTHENTICATION);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<EkycRequest> entity = new HttpEntity<EkycRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
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

	public LoansResponse getProductDetailsByUserIdList(List<Long> request) throws LoansException {
		String url = loansBaseUrl.concat(LOAN_PRODUCT_DETAILS_BY_USER_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<List<Long>> entity = new HttpEntity<List<Long>>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}

	public LoansResponse getUserNameByProductMappingId(Long request) throws LoansException {
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

	public LoansResponse getRegisteredUsersDetails(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(REGISTERD_USERS_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getRegisteredUsersDetails");
		}
	}

	public LoansResponse getLoanDetailsForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_LOAN_DETAILS_ADMIN_PANEL);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	public LoansResponse getUbiReport1ForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_UBI_REPORT_1);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	public LoansResponse getUbiReport2ForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_UBI_REPORT_2);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	public LoansResponse getUbiReport3ForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_UBI_REPORT_3);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	public LoansResponse getUbiReport4ForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_UBI_REPORT_4);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}
	public LoansResponse getUbiReport5ForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_UBI_REPORT_5);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanDetailsForAdminPanel");
		}
	}

	public LoansResponse getFilledLoanDetailsForAdminPanel(MobileLoanRequest loanRequest) throws LoansException {
		String url = loansBaseUrl.concat(GET_FILLED_LOAN_DETAILS_ADMIN_PANEL);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getFilledLoanDetailsForAdminPanel");
		}
	}

	public String getUserIdByProductId(Long productId) throws LoansException {
		String url = loansBaseUrl.concat(USER_ID_BY_PRODUCT_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(productId, headers);
			LoansResponse response = restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			if (response != null) {
				return response.getData().toString();
			} else {
				throw new LoansException("something went wrong");
			}
			/* return restTemplate.postForObject(url, request, LoansResponse.class); */
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("product master is not available");
		}
	}

	public LoansResponse getChatListByApplicationId(Long applicationId) throws LoansException {
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

	public LoansResponse getChatListByFpMappingId(Long productMappingId) throws LoansException {
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

	public LoansResponse createLog(LogDetailsModel logDetailsModel) throws LoansException {
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

	public LoansResponse getLoanListForMobile(MobileLoanRequest mobileUserRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOANLIST);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call loan list for mobile app");
		}
	}

	public LoansResponse lockPrimaryDetails(MobileLoanRequest mobileUserRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOCK_PRIMARY);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call loan list for mobile app");
		}
	}

	public LoansResponse getApplicantDetails(MobileLoanRequest mobileUserRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_APPLICANT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call get applicant details for mobile app");
		}
	}

	public LoansResponse saveApplicantDetails(MRetailApplicantResponse response) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_APPLICANT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MRetailApplicantResponse> entity = new HttpEntity<MRetailApplicantResponse>(response, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call save applicant details for mobile app");
		}
	}

	public LoansResponse saveAchievementdetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_ACHIEVEMENT_DETAILS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<CorporateApplicantRequest> entity = new HttpEntity<CorporateApplicantRequest>(applicantRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveCreditRatingOrganizationDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(CREDIT_RATING_ORGANIZATION_DETAILS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<PrimaryWorkingCapitalLoanRequest> entity = new HttpEntity<PrimaryWorkingCapitalLoanRequest>(
					request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveWorkingCapitalFinal(FinalWorkingCapitalLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(WORKING_CAPITAL_FINAL);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<FinalWorkingCapitalLoanRequest> entity = new HttpEntity<FinalWorkingCapitalLoanRequest>(request,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveLoanApplicationMaster(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(UPDATE_LOAN_APPLICATION);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse updateLoanApplicationMaster(LoanApplicationRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(UPDATE_LOAN_APPLICATION);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse getLoanBasicDetails(LoanApplicationRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(LOAN_BASIC_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException(
					"Loans service is not available while calling loan basic details for sending mails");
		}
	}

	public LoansResponse getBasicDetail(Long userId, Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(BASIC_DETAIL_URL).concat("/" + applicationId + "/" + userId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse getCoApplicantDetails(MobileLoanRequest mobileUserRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_COAPPLICANT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call get co-applicant details for mobile app");
		}
	}

	public LoansResponse saveCoApplicantDetails(MRetailCoAppGuarResponse coAppGuarResponse) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_COAPPLICANT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MRetailCoAppGuarResponse> entity = new HttpEntity<MRetailCoAppGuarResponse>(coAppGuarResponse,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call save co-applicant details for mobile app");
		}
	}

	public LoansResponse getGuarantorDetails(MobileLoanRequest mobileUserRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_GUARANTOR);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call get guarantor details for mobile app");
		}
	}

	public LoansResponse saveGuarantorDetails(MRetailCoAppGuarResponse coAppGuarResponse) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_GUARANTOR);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MRetailCoAppGuarResponse> entity = new HttpEntity<MRetailCoAppGuarResponse>(coAppGuarResponse,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call save guarantor details for mobile app");
		}
	}

	public LoansResponse mobileHlCalcMinMax(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_HL_CALC_MINMAX);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<HomeLoanEligibilityRequest> entity = new HttpEntity<HomeLoanEligibilityRequest>(homeLoanRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility HomeLoan Calc Min Max for mobile app");
		}
	}

	public LoansResponse mobileHlGetEligibleTenure(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_HL_GET_ELIGIBLE_TENURE);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<HomeLoanEligibilityRequest> entity = new HttpEntity<HomeLoanEligibilityRequest>(homeLoanRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility HomeLoan get eligible tenure for mobile app");
		}
	}

	public LoansResponse mobileHlCalcHomeLoanAmount(HomeLoanEligibilityRequest homeLoanRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_HL_CALC_LOAN_AMOUNT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<HomeLoanEligibilityRequest> entity = new HttpEntity<HomeLoanEligibilityRequest>(homeLoanRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility HomeLoan calc loan amount for mobile app");
		}
	}

	public LoansResponse mobilePlGetEligibleTenure(PersonalLoanEligibilityRequest eligibilityRequest)
			throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_PL_GET_ELIGIBLE_TENURE);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<PersonalLoanEligibilityRequest> entity = new HttpEntity<PersonalLoanEligibilityRequest>(
					eligibilityRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility PersonalLoan get eligible amount for mobile app");
		}
	}

	public LoansResponse mobilePlCalcMinMax(PersonalLoanEligibilityRequest eligibilityRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_PL_CALC_MINMAX);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<PersonalLoanEligibilityRequest> entity = new HttpEntity<PersonalLoanEligibilityRequest>(
					eligibilityRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility PersonalLoan calc min max for mobile app");
		}
	}

	public LoansResponse mobileLapGetEligibleTenure(LAPEligibilityRequest eligibilityRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_LAP_GET_ELIGIBLE_TENURE);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<LAPEligibilityRequest> entity = new HttpEntity<LAPEligibilityRequest>(eligibilityRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility LAP get eligible tenure for mobile app");
		}
	}

	public LoansResponse mobileLapCalcMinMax(LAPEligibilityRequest eligibilityRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_LAP_CALC_MINMAX);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<LAPEligibilityRequest> entity = new HttpEntity<LAPEligibilityRequest>(eligibilityRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility LAP Calc Min max for mobile app");
		}
	}

	public LoansResponse mobileLapCalcLapAmount(LAPEligibilityRequest eligibilityRequest) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_LOAN_ELIGIBILITY_LAP_CALC_LAP_AMOUNT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<LAPEligibilityRequest> entity = new HttpEntity<LAPEligibilityRequest>(eligibilityRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call Mobile Loan Eligibility LAP calc lap amount for mobile app");
		}
	}

	public LoansResponse saveExistingLoanFromCibil(List<ExistingLoanDetailRequest> detailRequests, Long userId,
			Long clientId, Long applicationId, Integer applicantType) throws ExcelException {
		String url = loansBaseUrl.concat(EXISTING_LOAN_DETAIL_CIBIL)
				.concat("/" + applicationId + "/" + userId + "/" + clientId + "/" + applicantType);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<List<ExistingLoanDetailRequest>> entity = new HttpEntity<List<ExistingLoanDetailRequest>>(
					detailRequests, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveCreditCardFromCibil(List<CreditCardsDetailRequest> detailRequests, Long userId,
			Long clientId, Long applicationId, Integer applicantType) throws ExcelException {
		String url = loansBaseUrl.concat(CREDIT_CARD_DETAIL_CIBIL)
				.concat("/" + applicationId + "/" + userId + "/" + clientId + "/" + applicantType);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<List<CreditCardsDetailRequest>> entity = new HttpEntity<List<CreditCardsDetailRequest>>(
					detailRequests, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveLoanApplicantDetails(MobileFrameRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_LOANAPPLICATION);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileFrameRequest> entity = new HttpEntity<MobileFrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(
					"Loans service is not available while call save loan application details for mobile app");
		}
	}

	public LoansResponse getProductList(MobileLoanRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_FP_PRODUCT_LIST);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while get fp product list for mobile app");
		}
	}

	public LoansResponse fundproviderProposal(MobileFPMatchesRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_FP_MATCHES_LIST);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileFPMatchesRequest> entity = new HttpEntity<MobileFPMatchesRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while get fp matches list for mobile app");
		}
	}

	public LoansResponse fundSeekerProposal(MobileFPMatchesRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_GET_FS_MATCHES_LIST);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<MobileFPMatchesRequest> entity = new HttpEntity<MobileFPMatchesRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while get fp matches list for mobile app");
		}
	}

	public LoansResponse getCreateCampaignLoan(Long userId, String code) throws ExcelException {
		String url = loansBaseUrl.concat(CREATE_LOAN_FROM_CAMPAIGN) + "/" + userId + "/" + code;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not availables");
		}
	}

	public LoansResponse convertToByteArrayFile(Long applicantId) throws LoansException {
		String url = loansBaseUrl.concat(STRING_TO_BINARY_ARRAY).concat("/" + applicantId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<Long> entity = new HttpEntity<Long>(applicantId, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
	
	public LoansResponse getDataMap(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(FS_DETAILS_FOR_PDF) + "/" + applicationId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not availables");
		}
	}
	
	
	public LoansResponse getOtherDocReport(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_OTHER_DOC_REPORT) + "/" + applicationId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not availables");
		}
		}
	public LoansResponse getFullHLPrimaryDetails(Long applicationId,Long userId) throws Exception {
		String url = loansBaseUrl.concat(GET_FULL_PRIMARY_HL).concat("/" + applicationId + "/" + userId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}
	public RetailApplicantRequest getFullProfileDetail(Long applicationId,Long userId) throws Exception {
		String url = loansBaseUrl.concat(GET_FULL_PROFILE).concat("/" + applicationId + "/" + userId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<Object>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, RetailApplicantRequest.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}
	
	public LoansResponse isTermLoanLessThanLimit(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(IS_TERM_LOAN_LESS_THAN_LIMIT) + "/" + applicationId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not availables");
		}
		}
	
	public Integer setEligibilityAmount(LoanApplicationRequest applicationRequest) throws Exception {
		String url = loansBaseUrl.concat(SET_ELIGIBILITY_AMOUNT);
		System.out.println("Eligibility update client URL==>"  + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(applicationRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, Integer.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}
	
}
