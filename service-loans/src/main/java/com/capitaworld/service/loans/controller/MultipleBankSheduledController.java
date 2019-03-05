package com.capitaworld.service.loans.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.SchedulerDataMultipleBankRequest;
import com.capitaworld.service.loans.service.common.SchedulerDataMultipleBankService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/multiple-bank-scheduled")
public class MultipleBankSheduledController {

	Logger logger = LoggerFactory.getLogger(MultipleBankSheduledController.class);
		
	@Autowired
	SchedulerDataMultipleBankService service;
	
	@RequestMapping(value = "/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> scheduledTaskController(@RequestBody SchedulerDataMultipleBankRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId) {
		LoansResponse loansResponse = null;
		logger.info("inside api to be saved on scheduler");
		try {		
			// request must not be null
			Boolean saveToSchedular = service.saveToSchedular(request);
			
			loansResponse = new LoansResponse(CommonUtils.DATA_FOUND, HttpStatus.OK.value());
			loansResponse.setData(saveToSchedular);
			logger.info("outside api to be saved on scheduler");
		}catch (Exception e) {
			loansResponse = new LoansResponse(CommonUtils.DATA_NOT_FOUND, HttpStatus.OK.value());
			loansResponse.setData(false);
			logger.error("Exception in saving data : "+e);
		}
		
		return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
	}

}
