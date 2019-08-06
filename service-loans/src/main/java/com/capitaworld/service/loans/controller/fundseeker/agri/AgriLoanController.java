package com.capitaworld.service.loans.controller.fundseeker.agri;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.agri.AgriRequest;
import com.capitaworld.service.loans.service.fundseeker.agri.AgriLoanService;
import com.capitaworld.service.loans.service.fundseeker.agri.CropDetailService;
import com.capitaworld.service.loans.service.fundseeker.agri.PrimaryAgriLoanDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinancialArrangementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;


@RestController
@RequestMapping("/agri")
public class AgriLoanController {
	private static final Logger logger = LoggerFactory.getLogger(AgriLoanController.class);

	@Autowired
	private AgriLoanService agriLoanService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;
	
	@Autowired
	private CropDetailService cropDetailService;
	
	@Autowired
	private PrimaryAgriLoanDetailService primaryAgriLoanDetailService;
	
	@Autowired
	private FinancialArrangementDetailsService financialArrangementDetailsService;
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody AgriRequest agriRequest , HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			// request must not be null
			if(CommonUtils.isObjectNullOrEmpty(agriRequest.getApplicantRequest())) {
				logger.warn("Applicant Request can not be empty for ApplicationId ==>{}",agriRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			if(CommonUtils.isObjectNullOrEmpty(agriRequest.getPrimaryRequest())) {
				logger.warn("Primary Request can not be empty for ApplicationId ==>{}",agriRequest.getApplicationId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			agriRequest.setUserId(userId);
			
			retailApplicantService.save(agriRequest.getApplicantRequest(), userId);
			primaryAgriLoanDetailService.saveOrUpdate(agriRequest.getPrimaryRequest(), userId);
			cropDetailService.save(agriRequest.getCropDetailRequests(), agriRequest.getApplicationId(), userId);
			financialArrangementDetailsService.saveOrUpdate(agriRequest.getArrangementsDetailRequests(), agriRequest.getApplicationId(), userId);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Agri Loan Data Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@RequestBody Long applicationId , HttpServletRequest request) {
		try {
			// request must not be null
			if(CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn("ApplicationId must not be Empty ==>{}",applicationId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			AgriRequest agriRequest = new  AgriRequest();
			agriRequest.setApplicantRequest(retailApplicantService.get(applicationId));
			agriRequest.setPrimaryRequest(primaryAgriLoanDetailService.get(applicationId));
			agriRequest.setCropDetailRequests(cropDetailService.getList(applicationId));
			agriRequest.setArrangementsDetailRequests(financialArrangementDetailsService.getFinancialArrangementDetailsList(applicationId, userId));
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Get Result", HttpStatus.OK.value(),agriRequest), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Agri Loan Data Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@PostMapping(value = "/applications/{status}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getApplication(@RequestBody Integer status , HttpServletRequest request) {
		try {
			
			CommonDocumentUtils.startHook(logger, "save");
			Long orgId = (Long) request.getAttribute(CommonUtils.USER_ORG_ID);
			if (orgId == null) {
				logger.warn("Organization  can not be empty ==>" + orgId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(new LoansResponse("Get Result", HttpStatus.OK.value(),agriLoanService.getApplications(orgId.intValue(), status)), HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Agri Loan Data Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
}
