
package com.opl.service.loans.controller.fundseeker.corporate;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.corporate.CMARequest;
import com.opl.service.loans.service.fundseeker.corporate.CMAService;

@RestController
@RequestMapping("/cma")
public class CMAController {

	public static final Logger logger = LoggerFactory.getLogger(CMAController.class);
	
	@Autowired
	private CMAService cmaService;
	
	@RequestMapping(value = "/get/{applicationId}",method = RequestMethod.GET)
	public CMARequest get(@PathVariable("applicationId") Long applicationId) {
		logger.info("Enter in get CMA details");
		CMARequest cma = new CMARequest();
		try {
			cma = cmaService.getCMA(applicationId);
			logger.info("Get CMA Successfully ");
		} catch(Exception e) {
			logger.error("Throw Exception While Get CMA Details : ",e);
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
			logger.error("Throw Exception While Get CMA Details : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * GET PROFILE AND LOSS API DATE FOR API,IRR and CAMS & TEASER VIEW
	 * @param applicationId
	 * @param proposalId
	 * @return
	 */
	@RequestMapping(value = "/getFinancialDetailsForBankIntegration/{applicationId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancialDetailsForBankIntegration(@PathVariable("applicationId") Long applicationId ,
			@RequestParam(value = "proposalId", required = false) Long proposalId) {
		logger.info("GET FINANCIAL DETAILS FOR BANK INTEGRATION ==========> APPID --->" + applicationId + "---- PROPOSAL ID ------>" + proposalId);
		try {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Save Succesfully", HttpStatus.OK.value(),cmaService.getFinancialDetailsForBankIntegration(applicationId, proposalId)),HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Throw Exception While GET FINANCIAL DETAILS FOR BANK INTEGRATION : ",e);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
