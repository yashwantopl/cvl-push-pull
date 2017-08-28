package com.capitaworld.service.loans.controller.common;

import java.util.Map;

import org.json.simple.JSONObject;
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
import com.capitaworld.service.loans.model.common.LAPEligibilityRequest;
import com.capitaworld.service.loans.model.common.PersonalLoanEligibilityRequest;
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

	// Home Loan Calculation Starts
	@RequestMapping(value = "${hl}/calc_min_max", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcMinMax(@RequestBody HomeLoanEligibilityRequest homeLoanRequest) {
		CommonDocumentUtils.startHook(logger, "calcMinMax");
		try {
			boolean isNull = CommonUtils.isObjectListNull(homeLoanRequest.getEmploymentType(),
					homeLoanRequest.getIncome(), homeLoanRequest.getDateOfBirth());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse response = null;
			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService.getMinMaxBySalarySlab(homeLoanRequest);
			if (minMaxBySalarySlab == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(minMaxBySalarySlab);
				response.setStatus(HttpStatus.OK.value());
			}
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

	@RequestMapping(value = "${hl}/get_eligible_tenure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getEligibleTenure(@RequestBody HomeLoanEligibilityRequest homeLoanRequest) {
		CommonDocumentUtils.startHook(logger, "getEligibleTenure");
		try {
			if (CommonUtils.isObjectListNull(homeLoanRequest.getDateOfBirth())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			CommonDocumentUtils.endHook(logger, "getEligibleTenure");
			LoansResponse response = null;
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(homeLoanRequest,
					CommonUtils.LoanType.HOME_LOAN.getValue());
			if (tenure == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
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

	@RequestMapping(value = "${hl}/calc_home_loan_amount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

			LoansResponse response = null;
			JSONObject jsonObject = loanEligibilityCalculatorService.calcHomeLoanAmount(homeLoanRequest);
			if (jsonObject == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(jsonObject);
				response.setStatus(HttpStatus.OK.value());
			}
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
	// Home Loan Calculation Ends

	// Personal Loan Calculation Starts
	@RequestMapping(value = "${pl}/get_eligible_tenure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getEligibleTenurePL(
			@RequestBody PersonalLoanEligibilityRequest eligibilityRequest) {
		CommonDocumentUtils.startHook(logger, "getEligibleTenurePL");
		try {
			boolean isNull = CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			// If Receipt Mode is CASH
			if (eligibilityRequest.getReceiptMode().equals(CommonUtils.ReceiptMode.CASH)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("You are not eligible for Personal Loan", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			CommonDocumentUtils.endHook(logger, "getEligibleTenurePL");
			LoansResponse response = null;
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(eligibilityRequest,
					CommonUtils.LoanType.PERSONAL_LOAN.getValue());
			if (tenure == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Personal Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(tenure);
				response.setStatus(HttpStatus.OK.value());
			}
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			CommonDocumentUtils.endHook(logger, "getEligibleTenurePL");
			logger.error("Error while calculating Eligible Tenure for Personal Loans");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "${pl}/calc_min_max", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcMinMaxPL(@RequestBody PersonalLoanEligibilityRequest eligibilityRequest) {
		CommonDocumentUtils.startHook(logger, "calcMinMaxPL");
		try {
			if (CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth(), eligibilityRequest.getIncome(),
					eligibilityRequest.getConstitution(), eligibilityRequest.getReceiptMode())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			// If Receipt Mode is not BANK
			if (!eligibilityRequest.getReceiptMode().equals(CommonUtils.ReceiptMode.BANK)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("You are not eligible for Personal Loan", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			// If Constitution Must be 1(Proprietorship/Partnership) OR 2(Others)
			if (!eligibilityRequest.getConstitution().equals(CommonUtils.EmployerConstitution.ANYOTHER) && !eligibilityRequest.getConstitution().equals(CommonUtils.EmployerConstitution.PARTNERSHIP_PROPRIETORSHIP)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Constitution Must be ANYOTHER or PARTNERSHIP/PROPRIETORSHIP", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			LoansResponse response = null;
			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService
					.calcMinMaxForPersonalLoan(eligibilityRequest);
			if (minMaxBySalarySlab == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Personal Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(minMaxBySalarySlab);
				response.setStatus(HttpStatus.OK.value());
			}
			CommonDocumentUtils.endHook(logger, "calcMinMaxPL");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calculating Loan eligibility for Personal Loans");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	// Personal Loan Calculation Ends
	
	//LAP Calculation Starts
	@RequestMapping(value = "${lap}/get_eligible_tenure", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getEligibleTenureLAP(
			@RequestBody PersonalLoanEligibilityRequest eligibilityRequest) {
		CommonDocumentUtils.startHook(logger, "getEligibleTenureLAP");
		try {
			boolean isNull = CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			CommonDocumentUtils.endHook(logger, "getEligibleTenureLAP");
			LoansResponse response = null;
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(eligibilityRequest, CommonUtils.LoanType.LAP_LOAN.getValue());
			if (tenure == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Loan Against Properties.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(tenure);
				response.setStatus(HttpStatus.OK.value());
			}
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);

		} catch (Exception e) {
			CommonDocumentUtils.endHook(logger, "getEligibleTenureLAP");
			logger.error("Error while calculating Eligible Tenure for Loan Against Properties.");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "${lap}/calc_min_max", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcMinMaxLAP(@RequestBody LAPEligibilityRequest eligibilityRequest) {
		CommonDocumentUtils.startHook(logger, "calcMinMaxLAP");
		try {
			if (CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth(), eligibilityRequest.getIncome(),
					eligibilityRequest.getEmploymentType(), eligibilityRequest.getPropertyType())) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse response = null;
			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService.calcMinMaxForLAP(eligibilityRequest);
			if (minMaxBySalarySlab == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Loan Against Properties.");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(minMaxBySalarySlab);
				response.setStatus(HttpStatus.OK.value());
			}
			CommonDocumentUtils.endHook(logger, "calcMinMaxLAP");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calculating Loan eligibility for LAP");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "${lap}/calc_lap_amount", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> calcLAP(@RequestBody LAPEligibilityRequest eligibilityRequest) {
		CommonDocumentUtils.startHook(logger, "calcLAP");
		try {
			boolean isNull = CommonUtils.isObjectListNull(eligibilityRequest.getEmploymentType(),
					eligibilityRequest.getIncome(), eligibilityRequest.getDateOfBirth(),
					eligibilityRequest.getMarketValue());
			if (isNull) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			LoansResponse response = null;
			JSONObject jsonObject = loanEligibilityCalculatorService.calcLAPAmount(eligibilityRequest);
			if (jsonObject == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());
			} else {
				response = new LoansResponse("Success");
				response.setData(jsonObject);
				response.setStatus(HttpStatus.OK.value());
			}
			CommonDocumentUtils.endHook(logger, "calcLAP");
			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calculating Loan eligibility for LAP");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	//LAP Calculation Ends
	
}
