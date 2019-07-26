package com.capitaworld.service.loans.controller.common;

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
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.OfflineAppConfigRequest;
import com.capitaworld.service.loans.service.common.OfflineAppConfigService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/offlineAppConfig")
public class OfflineAppConfigController {

	private static final Logger logger = LoggerFactory.getLogger(OfflineAppConfigController.class);

	@Autowired
	private OfflineAppConfigService offlineAppConfigService; 

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody OfflineAppConfigRequest  appConfigRequest,HttpServletRequest request) {

		CommonDocumentUtils.startHook(logger, "save");

		if (CommonUtils.isObjectListNull(appConfigRequest.getOrgId(),appConfigRequest.getBusinessTypeId(),appConfigRequest.getBankSpecific(),appConfigRequest.getMarketPlace(),appConfigRequest.getLoanType())) {
			String errorMsg = "OrganizationId , BusinessType, Answers and Loan Type are Mandatory fields to Save the configuration";
			logger.warn(errorMsg);
			return new ResponseEntity<LoansResponse>(new LoansResponse(errorMsg, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value(),offlineAppConfigService.save(appConfigRequest, userId)), HttpStatus.OK);
	}
	
	@PostMapping(value = "/get", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@RequestBody OfflineAppConfigRequest  appConfigRequest,HttpServletRequest request) {

		CommonDocumentUtils.startHook(logger, "get");

		if (CommonUtils.isObjectListNull(appConfigRequest.getOrgId(),appConfigRequest.getBusinessTypeId())) {
			String errorMsg = "OrganizationId and BusinessType is Mandatory to get the configuration";
			logger.warn(errorMsg);
			return new ResponseEntity<LoansResponse>(new LoansResponse(errorMsg, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESS, HttpStatus.OK.value(),offlineAppConfigService.get(appConfigRequest)), HttpStatus.OK);
	}
}
