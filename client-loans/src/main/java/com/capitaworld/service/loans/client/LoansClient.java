package com.capitaworld.service.loans.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.exceptions.ExcelException;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.CMADetailResponse;
import com.capitaworld.service.loans.model.CreditRatingOrganizationDetailRequest;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.ExcelRequest;
import com.capitaworld.service.loans.model.ExcelResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.EkycRequest;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.LogDetailsModel;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.corporate.FinalTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.FinalWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.model.corporate.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.model.corporate.PrimaryWorkingCapitalLoanRequest;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MRetailCoAppGuarResponse;
import com.capitaworld.service.loans.model.mobile.MobileApiResponse;
import com.capitaworld.service.loans.model.mobile.MobileFPMatchesRequest;
import com.capitaworld.service.loans.model.mobile.MobileFrameRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;
import com.capitaworld.service.loans.model.retail.CreditCardsDetailRequest;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;

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
	private static final String CORPORATE_APPLICATION_DETAILS_SAVE = "/fs_profile/save";
	private static final String CORPORATE_APPLICATION_ITR_MAPPING_SAVE = "/fs_profile/saveITRMapping";
	private static final String CORPORATE_APPLICATION_DETAILS_GET = "/fs_profile/getApplicationClientForEligibility";
	private static final String CORPORATE_APPLICATION_DETAILS_GET_NEW= "/fs_profile/getApplicationClient";
	private static final String CREDIT_RATING_ORGANIZATION_DETAILS = "/credit_rating_organization_details/save";
	private static final String EXISTING_PRODUCT_DETAILS = "/existing_product_details/save";
	private static final String MEANS_OF_FINANCE = "/means_of_finance/save";
	private static final String FINANCIAL_ARRANGEMENT_DETAILS = "/financial_arrangement_details/save";
	private static final String FINANCIAL_ARRANGEMENT_DETAILS_TOTAL_EMI = "/financial_arrangement_details/get_total_emi";
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
	private static final String SAVE_TERM_LOAN_PRIMARY_GET = "/term_loan/get_client";
	private static final String TOTAL_COST_OF_PROJECT = "/total_cost_of_project/save";
	private static final String WORKING_CAPITAL_PRIMARY = "/working_capital/primary/save";
	private static final String WORKING_CAPITAL_PRIMARY_GET = "/working_capital/get_client";
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
	private static final String MOBILE_CHANGE_STATUS = "/mobile/changeStatus";

	private static final String EXISTING_LOAN_DETAIL_CIBIL = "/existing_loan_details/save_from_cibil";
	private static final String CREDIT_CARD_DETAIL_CIBIL = "/credit_cards_detail/save_from_cibil";
	private static final String FINANCIAL_ARRANGEMENT_DETAILS_CIBIL = "/financial_arrangement_details/save_from_cibil";
	private static final String CREDIT_RATING_DETAILS_CIBIL = "/credit_rating_organization_details/save_from_cibil";

	private static final String CREATE_LOAN_FROM_CAMPAIGN = "/loan_application/create_loan_from_campaign";
	private static final String INACTIVE_APPLICATION_BY_APPLICATION_ID = "/loan_application/inactive_application";

	private static final String EKYC_AUTHENTICATION = "/loan_application/getDetailsForEkycAuthentication";
	
	private static final String FS_DETAILS_FOR_PDF = "/fsDetailsForPdf/getDataMap";
	
	private static final String GET_OTHER_DOC_REPORT = "/corporate_upload/uploadDocumentList/get";

	private static final String GET_FULL_PRIMARY_HL = "/home/primary/get_primary_info";
	private static final String GET_FULL_PRIMARY_PL = "/personal/primary/get_primary_info";
	private static final String GET_FULL_PRIMARY_LAP = "/lap/primary/get_primary_info";
	private static final String GET_FULL_PROFILE = "/fs_retail_profile/profile/get_profile";
	
	private static final String IS_TERM_LOAN_LESS_THAN_LIMIT = "/loan_application/isTermLoanLessThanLimit";
	
	private static final String SET_ELIGIBILITY_AMOUNT = "/loan_application/set_eligibility_amount";
	
	private static final String SAVE_DIRECTOR_BACKGROUND_DETAILS = "/director_background_details/save";
	private static final String GET_DIRECTOR_BACKGROUND_DETAILS = "/director_background_details/getList_client";
	private static final String GET_DIRECTOR_BACKGROUND_DETAIL = "/director_background_details/get";
	private static final String UPDATE_DIRECTOR_BACKGROUND_API_FLAG = "/director_background_details/update_api_flag";

	private static final String GET_LOAN_DETAILS = "/loan_application/get_client";
	
	private static final String GET_FINANCIAL_TO_BE_FILLED = "/ddr/get";
	
	private static final String GET_FINANCIAL_AUTO_FILLED_MASTER = "/ddr/getAutoFilledDetails";

	private static final String CALCULATE_SCORING_CORPORATE = "/score/calculate_score/corporate";

	private static final String GET_CMA_DETAIL = "/loan_eligibility/getCMADetailForEligibility/";
	
	private static final String CMA_DETAILS = "/cma/get";
	private static final String SAVE_CMA_DETAILS = "/cma/save";

	private static final String FUNDSEEKER_INPUT_REQUEST_SAVE = "/fundseeker_input_request/save";
	private static final String FUNDSEEKER_INPUT_REQUEST_GET = "/fundseeker_input_request/get";

	private static final String FUNDSEEKER_INPUT_REQUEST_SAVE_MOBILE = "/fundseeker_input_request_mobile/save_oneform";
	private static final String FUNDSEEKER_INPUT_REQUEST_GET_MOBILE = "/fundseeker_input_request_mobile/get_oneform";
	private static final String FUNDSEEKER_INPUT_REQUEST_MATCHES_MOBILE = "/fundseeker_input_request_mobile/match";

	private static final String GET_ORG_PAN_DETAILS = "/fs_profile/getOrgAndPanByAppId";
	
	private static final String  GET_FPDETAILS_BY_FPPRODUCTID = "getFpDetailsByFpProductId";
	private static final String SAVE_PHASE_ONE = "/loan_application/save_phase1_sidbi";
	private static final String SAVE_PHASE_TWO = "/loan_application/save_phase2_sidbi";
	
    private static final String GET_CORPORATE_SCORE="/score/calculate_score/corporate/test";
    
    private static final String SIDBI_GET_DIRECTOR_DETAILS ="/fundseeker_input_request_mobile/get_director_detail";
    private static final String SIDBI_SAVE_DIRECTOR_DETAILS ="/fundseeker_input_request_mobile/save_director_detail";
    private static final String UPDATE_PRODUCT_DETAILS ="/loan_application/updateProductDetails";
    
    private static final String GET_SCORING_EXCEL="/score/getScoreExcel";
    private static final String GET_DIRECTORLIST_BY_APPLICATION_ID = "/director_background_details/getDirectorList";
    
    private static final String GET_DATA_FOR_CGTMSE="/common/getDataForCGTMSE";
	private static final Logger logger = LoggerFactory.getLogger(LoansClient.class);
	
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
		headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileUserRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call get applicant details for mobile app");
		}
	}

	public LoansResponse saveApplicantDetails(MRetailApplicantResponse mRetailApplicantResponse) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_APPLICANT);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MRetailApplicantResponse> entity = new HttpEntity<MRetailApplicantResponse>(mRetailApplicantResponse, headers);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse saveCorporateApplicant(CorporateApplicantRequest applicantRequest) throws ExcelException {
		String url = loansBaseUrl.concat(CORPORATE_APPLICATION_DETAILS_SAVE);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CorporateApplicantRequest> entity = new HttpEntity<CorporateApplicantRequest>(applicantRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse saveITRMappingCorporateApplicant(CorporateApplicantRequest applicantRequest) throws ExcelException {
		String url = loansBaseUrl.concat(CORPORATE_APPLICATION_ITR_MAPPING_SAVE);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CorporateApplicantRequest> entity = new HttpEntity<CorporateApplicantRequest>(applicantRequest,
					headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
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
	
	public LoansResponse getCorporateApplicantNew(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(CORPORATE_APPLICATION_DETAILS_GET_NEW).concat("/" + applicationId);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getTotalEMI(Long applicationId) throws Exception{
		String url = loansBaseUrl.concat(FINANCIAL_ARRANGEMENT_DETAILS_TOTAL_EMI).concat("/" + applicationId);
		System.out.println("url for Getting TotalEMI From Client=================>" + url + " and For Application Id====>" + applicationId);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}

	public LoansResponse saveFutureFinancialEstimatesDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(FUTURE_FINANCIAL_ESTIMATE_DETAILS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<PrimaryWorkingCapitalLoanRequest> entity = new HttpEntity<PrimaryWorkingCapitalLoanRequest>(
					request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getWorkingCapitalPrimary(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(WORKING_CAPITAL_PRIMARY_GET).concat("/" + applicationId);
		System.out.println("url for Getting Working Capital Primary From Client=================>" + url + " and For Application Id====>" + applicationId);
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

	public LoansResponse saveWorkingCapitalFinal(FinalWorkingCapitalLoanRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(WORKING_CAPITAL_FINAL);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
	
	public LoansResponse saveFinancialArrangementDetailFromCibil(List<FinancialArrangementsDetailRequest> detailRequests, Long userId,
			Long clientId, Long applicationId,Long directorId) throws ExcelException {
		String url = loansBaseUrl.concat(FINANCIAL_ARRANGEMENT_DETAILS_CIBIL)
				.concat("/" + applicationId + "/" + userId + "/" + clientId + "/" + directorId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<List<FinancialArrangementsDetailRequest>> entity = new HttpEntity<List<FinancialArrangementsDetailRequest>>(
					detailRequests, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException(url + " in Loans service is not available");
		}
	}
	
	public LoansResponse saveCreditRatingDetailFromCibil(List<CreditRatingOrganizationDetailRequest> detailRequests, Long userId,
			Long clientId, Long applicationId) throws Exception {
		String url = loansBaseUrl.concat(CREDIT_RATING_DETAILS_CIBIL)
				.concat("/" + applicationId + "/" + userId + "/" + clientId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<List<CreditRatingOrganizationDetailRequest>> entity = new HttpEntity<List<CreditRatingOrganizationDetailRequest>>(
					detailRequests, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(url + " in Loans service is not available");
		}
	}

	public LoansResponse saveLoanApplicantDetails(MobileFrameRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_SAVE_LOANAPPLICATION);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MobileFPMatchesRequest> entity = new HttpEntity<MobileFPMatchesRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while get fp matches list for mobile app");
		}
	}
	
	public LoansResponse changeStatus(MobileFPMatchesRequest request) throws LoansException {
		String url = loansBaseUrl.concat(MOBILE_CHANGE_STATUS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("req_auth", "true");
			HttpEntity<MobileFPMatchesRequest> entity = new HttpEntity<MobileFPMatchesRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while get fp matches list for mobile app");
		}
	}

	public LoansResponse getCreateCampaignLoan(Long userId, Boolean isActive,Integer businessTypeId, String...code) throws ExcelException {
		String url = loansBaseUrl.concat(CREATE_LOAN_FROM_CAMPAIGN) + "?clientId=" + userId + "&isActive = " + isActive + "&businessType = " + businessTypeId;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<List<String>> entity = new HttpEntity<List<String>>(Arrays.asList(code),headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not availables");
		}
		}
	public LoansResponse getFullPrimaryDetails(Long applicationId,Long userId,Integer productId) throws Exception {
		String url = null;
		LoanType type = CommonUtils.LoanType.getType(productId);
		switch (type) {
		case HOME_LOAN:
			url = loansBaseUrl.concat(GET_FULL_PRIMARY_HL);			
			break;
		case PERSONAL_LOAN:
			url = loansBaseUrl.concat(GET_FULL_PRIMARY_PL);			
			break;
		case LAP_LOAN:
			url = loansBaseUrl.concat(GET_FULL_PRIMARY_LAP);			
			break;
		default:
			break;
		}
		url = url.concat("/" + applicationId + "/" + userId);
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
			headers.setContentType(MediaType.APPLICATION_JSON);
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
	
	public LoansResponse saveDirectorBackgroundDetails(FrameRequest request) throws ExcelException {
		String url = loansBaseUrl.concat(SAVE_DIRECTOR_BACKGROUND_DETAILS);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse updateDirectorAPIFlag(Long directorId,Long userId,Integer apiId, Boolean apiFlag) throws Exception {
		String url = loansBaseUrl.concat(UPDATE_DIRECTOR_BACKGROUND_API_FLAG) + "?userId=" + userId + "&directorId=" + directorId + "&apiId=" + apiId + "&apiFlag=" + apiFlag;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null,headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("404")) {
				throw new Exception(url + " is Not Found");				
			}else if(e.getMessage().contains("400")) {
				throw new Exception(url + " is Not Valid Request");				
			}else {
				throw new ExcelException("Loans service is not available");
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<DirectorBackgroundDetailRequest> getDirectorBackgroundDetails(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_DIRECTOR_BACKGROUND_DETAILS).concat("/" + applicationId);
		System.out.println("url for Getting DirectorBackgroundDetails From Client=================>" + url + " and For Application Id====>" + applicationId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, List.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getDirectorBackgroundDetail(Long id) throws Exception {
		String url = loansBaseUrl.concat(GET_DIRECTOR_BACKGROUND_DETAIL).concat("/" + id);
		System.out.println("url for Getting DirectorBackgroundDetail From Client=================>" + url + " and For Id====>" + id);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			if(e.getMessage().contains("404")) {
				throw new Exception(url + " is Not Found");				
			}else if(e.getMessage().contains("400")) {
				throw new Exception(url + " is Not Valid Request");				
			}else {
				throw new ExcelException("Loans service is not available");
			}
		}
	}
	
	
	public LoanApplicationRequest getLoanMasterInfo(Long applicationId) throws LoansException {
		String url = loansBaseUrl.concat(GET_LOAN_DETAILS).concat("/" + applicationId);
		try {
			System.out.println("url====================>" + url);
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoanApplicationRequest.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getLoanMasterInfo");
		}
	}
	
	public LoansResponse getFilledDetails(Long appId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_FINANCIAL_TO_BE_FILLED).concat("/"+appId);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getAutoFilledDetails(Long appId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_FINANCIAL_AUTO_FILLED_MASTER).concat("/"+appId);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FrameRequest> entity = new HttpEntity<FrameRequest>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}



	public LoansResponse calculateScoringCorporate(ScoringRequestLoans scoringRequestLoans) throws Exception {
		String url = loansBaseUrl.concat(CALCULATE_SCORING_CORPORATE);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ScoringRequestLoans> entity = new HttpEntity<ScoringRequestLoans>(scoringRequestLoans,headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}
	public CMADetailResponse getCMADetils(Long appId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_CMA_DETAIL).concat("/"+appId);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
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
	
	public CMARequest getCMA(Long applicationId) throws Exception {
		String url = loansBaseUrl.concat(CMA_DETAILS) + "/" + applicationId;
		logger.info("Enter in Loan CLient For get CMA Details ----------------------> " + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<Object>(headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, CMARequest.class).getBody();
		} catch (Exception e) {
			logger.info("Throw Exception While Get CMA Details Using Loan CLient");
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}
	
	public LoansResponse saveCMA(CMARequest cmaRequest) throws Exception {
		String url = loansBaseUrl.concat(SAVE_CMA_DETAILS);
		logger.info("Enter in save CMA details in Loan client");
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<CMARequest> entity = new HttpEntity<CMARequest>(cmaRequest,headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			logger.info("Throw Exception while call save CMA details");
			e.printStackTrace();
			throw new Exception("Loans service is not available");
		}
	}


	public LoansResponse saveFundseekerInputRequest(FundSeekerInputRequestResponse request) throws ExcelException {
		String url = loansBaseUrl.concat(FUNDSEEKER_INPUT_REQUEST_SAVE);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}


	public LoansResponse getFundseekerInputRequest(FundSeekerInputRequestResponse request) throws ExcelException {
		String url = loansBaseUrl.concat(FUNDSEEKER_INPUT_REQUEST_GET);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getOrgAndPanByAppId(Long applicationId) throws LoansException {
		String url = loansBaseUrl.concat(GET_ORG_PAN_DETAILS).concat("/" + applicationId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	public LoansResponse inactiveApplication(Long applicationId,Long userId) throws LoansException {
		String url = loansBaseUrl.concat(INACTIVE_APPLICATION_BY_APPLICATION_ID).concat("/" + applicationId).concat("/" + userId);
		System.out.println("url to Inactive Application==>" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available");
		}
	}
	
	public LoansResponse savePhase1Sidbi(LoanApplicationRequest loanApplicationRequest) throws LoansException {
		String url = loansBaseUrl.concat(SAVE_PHASE_ONE);
		System.out.println("Enter in " + SAVE_PHASE_ONE + " -------- URL ---------->" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(loanApplicationRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(SAVE_PHASE_ONE + " Loans service is not available");
		}
	}
	
	public LoansResponse savePhase2Sidbi(LoanApplicationRequest loanApplicationRequest) throws LoansException {
		String url = loansBaseUrl.concat(SAVE_PHASE_TWO);
		System.out.println("Enter in " + SAVE_PHASE_TWO + " -------- URL ---------->" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(loanApplicationRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException(SAVE_PHASE_TWO + " Loans service is not available");
		}
	}
	
	public LoansResponse getFpDetailsByFpProductId(Long fpProductId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_FPDETAILS_BY_FPPRODUCTID).concat("/"+fpProductId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse getCorporateScore(ScoringRequestLoans scoringRequestLoans) throws LoansException {
		String url = loansBaseUrl.concat(GET_CORPORATE_SCORE);
		System.out.println("url to get Corporate Score Calculation ==>" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<ScoringRequestLoans> entity = new HttpEntity<ScoringRequestLoans>(scoringRequestLoans, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
			/*
			 * return restTemplate.postForObject(url, request, LoansResponse.class);
			 */

		} catch (Exception e) {
			e.printStackTrace();
			
			throw e;//("Loans service is not available");
		}
	}
		
	public MobileApiResponse getSIDBIDirectorDetails(FundSeekerInputRequestResponse fsInputReqRes) throws ExcelException {
		String url = loansBaseUrl.concat(SIDBI_GET_DIRECTOR_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(fsInputReqRes, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, MobileApiResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public MobileApiResponse saveSIDBIDirectorDetails(FundSeekerInputRequestResponse fsInputReqRes) throws ExcelException {
		String url = loansBaseUrl.concat(SIDBI_SAVE_DIRECTOR_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(fsInputReqRes, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, MobileApiResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public LoansResponse updateProductDetails(LoanApplicationRequest loanRequest) throws ExcelException {
		String url = loansBaseUrl.concat(UPDATE_PRODUCT_DETAILS);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<LoanApplicationRequest> entity = new HttpEntity<LoanApplicationRequest>(loanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public MobileApiResponse saveOneFormForMobile(FundSeekerInputRequestResponse request) throws ExcelException {
		String url = loansBaseUrl.concat(FUNDSEEKER_INPUT_REQUEST_SAVE_MOBILE);
		try {
			/* return restTemplate.postForObject(url, request, ExcelResponse.class); */
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(request, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, MobileApiResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	@SuppressWarnings("unchecked")
	public MobileApiResponse getOneFormForMobile(FundSeekerInputRequestResponse fundSeekerInputRequestResponse) throws ExcelException {
		String url = loansBaseUrl.concat(FUNDSEEKER_INPUT_REQUEST_GET_MOBILE);
		System.out.println("url for Getting Oneform details From Client=================>" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundSeekerInputRequestResponse> entity = new HttpEntity<FundSeekerInputRequestResponse>(fundSeekerInputRequestResponse, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, MobileApiResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	public MobileApiResponse matchesForMobile(MobileLoanRequest mobileLoanRequest) throws ExcelException {
		String url = loansBaseUrl.concat(FUNDSEEKER_INPUT_REQUEST_MATCHES_MOBILE);
		System.out.println("Url for MATCHES details From Client=================>" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<MobileLoanRequest> entity = new HttpEntity<MobileLoanRequest>(mobileLoanRequest, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, MobileApiResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}
	
	
	public LoansResponse getScoringExcel(MultipartFile multipartFile) throws LoansException, ExcelException {
		String url = loansBaseUrl.concat(GET_SCORING_EXCEL);
		System.out.println("url to get Corporate Score Calculation ==>" + url);
		try {
			HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);
            headers.set("req_auth", "true");
            MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
            ByteArrayResource contentsAsResource = new ByteArrayResource(multipartFile.getBytes()){
                @Override
                public String getFilename(){
                    return "";
                }
            };
            map.add("file", contentsAsResource);
           /* map.add("applicationId", applicationId);*/

            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);
          return  restTemplate.postForObject(url, request,LoansResponse.class);
            
		} catch (IOException e) {
			e.printStackTrace();
			
			throw new ExcelException("Loans service is not available");
			//throw e;//("Loans service is not available");
		}
	}
	
	public LoansResponse getDataForCGTMSE(Long applicationId) throws ExcelException {
		String url = loansBaseUrl.concat(GET_DATA_FOR_CGTMSE).concat("/"+applicationId);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<?> entity = new HttpEntity<>(null, headers);
			return restTemplate.exchange(url, HttpMethod.GET, entity, LoansResponse.class).getBody();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExcelException("Loans service is not available");
		}
	}

	public LoansResponse getDirectorsListByApplicationId(Long applicationId) throws LoansException {
		String url = loansBaseUrl.concat(GET_DIRECTORLIST_BY_APPLICATION_ID);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("req_auth", "true");
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Long> entity = new HttpEntity<Long>(applicationId, headers);
			return restTemplate.exchange(url, HttpMethod.POST, entity, LoansResponse.class).getBody();

		} catch (Exception e) {
			e.printStackTrace();
			throw new LoansException("Loans service is not available while call getChatListByApplicationId");
		}
	}
}