package com.opl.service.loans.controller.api;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.rating.model.FinancialInputRequest;
import com.opl.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.opl.service.loans.service.teaser.primaryview.CorporatePrimaryViewService;






@RestController
@RequestMapping("/api")
public class getCrossComparisonDataController {


	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService; 

	@Autowired
	private CorporatePrimaryViewService corporatePrimaryViewService;

	private static final Logger logger = LoggerFactory.getLogger(getCrossComparisonDataController.class);

	private static final String SUCCESS_LITERAL = "success";

	private static final String ERROR_WHILE_GETTING_MAP_DETAILS = "Error while getting MAP Details==>";

	@GetMapping(value = {"/getDataForCrossComparison/{applicationId}/{proposalId}"} , produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<LoansResponse> getDataForCrossComparison(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(name ="proposalId" ,required = false) Long proposalId ,
			@PathVariable(value = "gstMasterId") Long gstMasterId , @PathVariable(value = "itrMasterId") Long itrMasterId ,@PathVariable(value = "bsMasterId") Long bsMasterId, HttpServletRequest request)  {

		try {
			
			if (CommonUtils.isObjectNullOrEmpty(applicationId) && CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, + applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			FinancialInputRequest financialInputRequest= camReportPdfDetailsService.finaForCam(applicationId, proposalId , itrMasterId);
				if(financialInputRequest != null) {
					LinkedHashMap<String, Object> map = corporatePrimaryViewService.gstVsItrVsBsComparision(applicationId, financialInputRequest ,gstMasterId , itrMasterId , bsMasterId );
					if(map != null) {
						return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, map),HttpStatus.OK);
					}else{
						return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
					}
				}else{
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}

		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

}