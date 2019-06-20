package com.capitaworld.service.loans.controller.sidbi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sidbi.CorporateGovernanceComplianceRequest;
import com.capitaworld.service.loans.service.sidbi.CorporateGovernanceCompianceService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/sidbi/final")
public class SidbiFinalFormController {
	
	private static final Logger logger = LoggerFactory.getLogger(SidbiFinalFormController.class.getName());
	
	@Autowired
	CorporateGovernanceCompianceService corpoService;

	@PostMapping(value = "/saveCorporateGovernanceCompliance", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCorporateGovernanceCompliance(@RequestBody List<CorporateGovernanceComplianceRequest> corporateRequest, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "Corporate governance / compliance");
		
		if (corporateRequest.isEmpty()) {
			logger.warn("CorporategovernanceRequest must not be empty ==>{}" , corporateRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		if(corporateRequest.size() < 4) {
			logger.warn("Validation error fill all data ==>{}" , corporateRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Validation error fill all data", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			if(corpoService.saveCorporate(corporateRequest)) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);
			}else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Corporate Governance Compliance Details==>{}", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}
	
	@GetMapping(value = "/getCorporateGovernanceCompliance/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCorporateGovernanceCompliance(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getting Corporate governance / compliance");
		
		if (applicationId == null) {
			logger.warn("CorporategovernanceRequest must not be empty ==>{}" , applicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			CorporateGovernanceComplianceRequest corporateRequest =new CorporateGovernanceComplianceRequest();
			corporateRequest.setApplicationId(applicationId);
			List<CorporateGovernanceComplianceRequest> corporate = corpoService.getCorporate(corporateRequest);
			if(corporate != null || !corporate.isEmpty()) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Data retrived.", HttpStatus.OK.value(),corporate),HttpStatus.OK);
			}else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Corporate Governance Compliance Details==>{}", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}
	
}
