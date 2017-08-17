package com.capitaworld.service.loans.controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.HomeLoanEligibilityRequest;
import com.capitaworld.service.loans.service.common.LoanEligibilityCalculatorService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/loan_eligibility")
public class LoanEligibilityCalculatorController {

	private static final Logger logger = LoggerFactory.getLogger(LoanEligibilityCalculatorController.class);

	@Autowired
	private LoanEligibilityCalculatorService loanEligibilityCalculatorService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/calc_min_max", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcMinMax(@RequestBody HomeLoanEligibilityRequest homeLoanRequest) {
		CommonDocumentUtils.startHook(logger, "calcMinMax");
		try {
			boolean isNull = CommonUtils.isObjectListNull(homeLoanRequest.getEmploymentType(),homeLoanRequest.getIncome(), homeLoanRequest.getDateOfBirth());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse response = new LoansResponse("Success", HttpStatus.OK.value());
			response.setData(loanEligibilityCalculatorService.getMinMaxBySalarySlab(homeLoanRequest));
			CommonDocumentUtils.endHook(logger, "calcMinMax");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calculating Loan eligibility for Home Loans");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get_eligible_tenure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getEligibleTenure(@RequestBody HomeLoanEligibilityRequest homeLoanRequest) {
		CommonDocumentUtils.startHook(logger, "getEligibleTenure");
		try {
			if (CommonUtils.isObjectListNull(homeLoanRequest.getDateOfBirth())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			CommonDocumentUtils.endHook(logger, "getEligibleTenure");
			LoansResponse response = null;
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(homeLoanRequest);
			if(tenure == null){
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			}else{
				response = new LoansResponse("Success");
				response.setData(tenure);
				response.setStatus(HttpStatus.OK.value());
			}
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			CommonDocumentUtils.endHook(logger, "getEligibleTenure");
			logger.error("Error while calculating Eligible Tenure for Home Loans");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/calc_home_loan_amount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcHomeLoanAmount(@RequestBody HomeLoanEligibilityRequest homeLoanRequest) {
		CommonDocumentUtils.startHook(logger, "calcHomeLoanAmount");
		try {
			boolean isNull = CommonUtils.isObjectListNull(homeLoanRequest.getEmploymentType(),
					homeLoanRequest.getIncome(), homeLoanRequest.getDateOfBirth(), homeLoanRequest.getStampValue(),
					homeLoanRequest.getMarketValue());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoansResponse response = new LoansResponse("Success", HttpStatus.OK.value());
			response.setData(loanEligibilityCalculatorService.calcHomeLoanAmount(homeLoanRequest));
			CommonDocumentUtils.endHook(logger, "calcHomeLoanAmount");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calculating Loan eligibility for Home Loans");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
}
