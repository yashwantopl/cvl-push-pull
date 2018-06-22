package com.capitaworld.service.loans.controller.fundseeker.corporate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CMAService;
import com.capitaworld.sidbi.integration.model.financial.FinancialRequest;

@RestController
@RequestMapping("/cma")
public class CMAController {

	public static final Logger logger = LoggerFactory.getLogger(CMAController.class);
	
	@Autowired
	private CMAService cmaService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}
	
	@RequestMapping(value = "/get/{applicationId}",method = RequestMethod.GET)
	public CMARequest get(@PathVariable("applicationId") Long applicationId) {
		logger.info("Enter in get CMA details");
		CMARequest cma = new CMARequest();
		try {
			cma = cmaService.getCMA(applicationId);
			logger.info("Get CMA Successfully ");
		} catch(Exception e) {
			logger.info("Throw Exception While Get CMA Details");
			e.printStackTrace();
		}
		return cma;
	}
	
	@RequestMapping(value = "/save",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody CMARequest cmaRequest) {
		try {
			logger.info("Enter in Save CMA Details");
			cmaService.saveCMA(cmaRequest);
			logger.info("Save CMA Successfully ");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Save Succesfully", HttpStatus.OK.value()),HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Throw Exception While Get CMA Details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get_financial_data_for_bank_integration/{applicationId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@PathVariable("applicationId") Long applicationId) {
		try {
			logger.info("Enter in GET Financial Data For Bank Integration----------------->" + applicationId);
			FinancialRequest financialDetails = cmaService.getFinancialDetailsForBankIntegration(applicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Get Succesfully", HttpStatus.OK.value(),financialDetails),HttpStatus.OK);
		} catch(Exception e) {
			logger.info("Throw Exception While GET Financial Data For Bank Integration");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
