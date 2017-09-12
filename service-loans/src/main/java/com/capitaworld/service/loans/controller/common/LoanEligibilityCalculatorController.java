package com.capitaworld.service.loans.controller.common;

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
			LoansResponse response = isHomeLoanRequestIsValid(homeLoanRequest, false);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService.getMinMaxBySalarySlab(homeLoanRequest);
			if (minMaxBySalarySlab == null) {
				response.setMessage("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else if (minMaxBySalarySlab.isEmpty()) {
				response.setMessage("Invalid");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
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
			LoansResponse response = isHomeLoanRequestIsValid(homeLoanRequest, false);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			CommonDocumentUtils.endHook(logger, "getEligibleTenure");
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(homeLoanRequest,
					CommonUtils.LoanType.HOME_LOAN.getValue());
			if (tenure == null) {
				response.setMessage("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
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
			LoansResponse response = isHomeLoanRequestIsValid(homeLoanRequest, true);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			JSONObject jsonObject = loanEligibilityCalculatorService.calcHomeLoanAmount(homeLoanRequest);
			if (jsonObject == null) {
				response.setMessage("Invalid Age");
				response.setData("You are not eligible for Home Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
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
			LoansResponse response = isPersonalLoanRequestIsValid(eligibilityRequest);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			CommonDocumentUtils.endHook(logger, "getEligibleTenurePL");
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(eligibilityRequest,
					CommonUtils.LoanType.PERSONAL_LOAN.getValue());
			if (tenure == null) {
				response.setMessage("Invalid Age");
				response.setData("You are not eligible for Personal Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
			} else {
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
			LoansResponse response = isPersonalLoanRequestIsValid(eligibilityRequest);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService
					.calcMinMaxForPersonalLoan(eligibilityRequest);
			if (minMaxBySalarySlab == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Personal Loan");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
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

	// LAP Calculation Starts
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
			Integer tenure = loanEligibilityCalculatorService.calculateTenure(eligibilityRequest,
					CommonUtils.LoanType.LAP_LOAN.getValue());
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
			LoansResponse response = isLAPRequestIsValid(eligibilityRequest);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}

			JSONObject minMaxBySalarySlab = loanEligibilityCalculatorService.calcMinMaxForLAP(eligibilityRequest);
			if (minMaxBySalarySlab == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Loan Against Properties.");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
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
			LoansResponse response = isLAPRequestIsValid(eligibilityRequest);
			if (response.getStatus().equals(HttpStatus.BAD_REQUEST.value())) {
				return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
			}
			JSONObject jsonObject = loanEligibilityCalculatorService.calcLAPAmount(eligibilityRequest);
			if (jsonObject == null) {
				response = new LoansResponse("Invalid Age");
				response.setData("You are not eligible for Loan Against Property");
				response.setStatus(HttpStatus.BAD_REQUEST.value());
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

	private static boolean isValidRequest(Integer propertyType) {
		if (CommonUtils.isObjectNullOrEmpty(propertyType)) {
			return false;
		}
		if (!(propertyType.intValue() == CommonUtils.PropertyType.RESIDENTIAL)
				&& !(propertyType.intValue() == CommonUtils.PropertyType.COMMERCIAL)
				&& !(propertyType.intValue() == CommonUtils.PropertyType.INDUSTRIAL)
				&& !(propertyType.intValue() == CommonUtils.PropertyType.PLOT)) {
			return false;
		}
		return true;
	}

	private static LoansResponse isHomeLoanRequestIsValid(HomeLoanEligibilityRequest homeLoanRequest, boolean isMVSV) {
		if (!CommonUtils.isObjectNullOrEmpty(homeLoanRequest)) {
			logger.info("Request Object ==>" + homeLoanRequest.toString());
		}

		LoansResponse response = null;
		boolean isNull = CommonUtils.isObjectListNull(homeLoanRequest.getEmploymentType(), homeLoanRequest.getIncome(),
				homeLoanRequest.getDateOfBirth());
		if (isNull) {
			response = new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value());
			response.setData("You are not eligible for Home Loan");
			return response;
		}

		if (homeLoanRequest.getIncome() <= homeLoanRequest.getObligation()) {
			response = new LoansResponse("Obligation Must be less than Income", HttpStatus.BAD_REQUEST.value());
			response.setData("You are not eligible for Home Loan");
			return response;
		}
		if (homeLoanRequest.getIncome() < 9000) {
			response = new LoansResponse("Minimum Salary should be 9000", HttpStatus.BAD_REQUEST.value());
			response.setData("You are not eligible for Home Loan");
			return response;
		}
		if (isMVSV) {
			isNull = CommonUtils.isObjectListNull(homeLoanRequest.getStampValue(), homeLoanRequest.getMarketValue());
			if (isNull) {
				response = new LoansResponse("Market Value and Stamp value must not be empty.",
						HttpStatus.BAD_REQUEST.value());
				response.setData("You are not eligible for Home Loan");
				return response;
			}
		}
		return new LoansResponse("Success", HttpStatus.OK.value());
	}

	private static LoansResponse isPersonalLoanRequestIsValid(PersonalLoanEligibilityRequest eligibilityRequest) {
		final String MSG = "You are not eligible for Personal Loan";
		if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest)) {
			logger.info("Request Object ==>" + eligibilityRequest.toString());
		}

		LoansResponse response = null;
		boolean isNull = CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth(),
				eligibilityRequest.getIncome(), eligibilityRequest.getConstitution(),
				eligibilityRequest.getReceiptMode());
		if (isNull) {
			response = new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		if (!eligibilityRequest.getReceiptMode().equals(CommonUtils.ReceiptMode.BANK)) {
			response = new LoansResponse("Receipt Mode Cash is Not allowed.", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		if (!eligibilityRequest.getConstitution().equals(CommonUtils.EmployerConstitution.ANYOTHER)
				&& !eligibilityRequest.getConstitution()
						.equals(CommonUtils.EmployerConstitution.PARTNERSHIP_PROPRIETORSHIP)) {
			response = new LoansResponse("Constitution Must be ANYOTHER or PARTNERSHIP/PROPRIETORSHIP",
					HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}

		if (eligibilityRequest.getIncome() <= eligibilityRequest.getObligation()) {
			response = new LoansResponse("Obligation Must be less than Income", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		if (eligibilityRequest.getIncome() < 10000) {
			response = new LoansResponse("Minimum Salary should be 10000", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		return new LoansResponse("Success", HttpStatus.OK.value());
	}

	private static LoansResponse isLAPRequestIsValid(LAPEligibilityRequest eligibilityRequest) {
		final String MSG = "You are not eligible for Loan Against Property";
		if (!CommonUtils.isObjectNullOrEmpty(eligibilityRequest)) {
			logger.info("Request Object ==>" + eligibilityRequest.toString());
		}

		LoansResponse response = null;
		boolean isNull = CommonUtils.isObjectListNull(eligibilityRequest.getDateOfBirth(),
				eligibilityRequest.getIncome(), eligibilityRequest.getEmploymentType(),
				eligibilityRequest.getPropertyType());
		if (isNull) {
			response = new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		boolean validRequest = isValidRequest(eligibilityRequest.getPropertyType());
		if (!validRequest) {
			response = new LoansResponse("Invalid PropertyType", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
		}
		if (eligibilityRequest.getIncome() <= eligibilityRequest.getObligation()) {
			response = new LoansResponse("Obligation Must be less than Income", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		if (eligibilityRequest.getIncome() < 12000) {
			response = new LoansResponse("Minimum Salary should be 12000", HttpStatus.BAD_REQUEST.value());
			response.setData(MSG);
			return response;
		}
		return new LoansResponse("Success", HttpStatus.OK.value());
	}
	// LAP Calculation Ends

}
