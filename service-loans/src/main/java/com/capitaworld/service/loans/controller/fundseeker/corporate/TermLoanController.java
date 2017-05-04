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

import com.capitaworld.service.loans.model.FinalTermLoanRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.PrimaryTermLoanRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinalTermLoanService;
import com.capitaworld.service.loans.service.fundseeker.corporate.PrimaryTermLoanService;

@RestController
@RequestMapping("/term_loan")
public class TermLoanController {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanController.class);

	@Autowired
	private FinalTermLoanService finalTLService;

	@Autowired
	private PrimaryTermLoanService primaryTLService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FinalTermLoanRequest termLoanRequest) throws Exception {
		try {
			// request must not be null
			if (termLoanRequest == null) {
				logger.warn("TermLoanRequest Object can not be empty ==>" + termLoanRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (termLoanRequest.getApplicationId() == null) {
				logger.warn("Application ID can not be empty ==>" + termLoanRequest.getId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Application ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			boolean response = finalTLService.saveOrUpdate(termLoanRequest);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving final information of Term Loan");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "${final}/get/{id}/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinal(@PathVariable("id") Long id,
			@PathVariable("applicationId") Long applicationId) {
		try {
			try {
				if (id == null || applicationId == null) {
					logger.warn("ID and ApplicationId Require to get Final Term Loan Details. ID==>" + id
							+ " and ApplicationId==>" + applicationId);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}

				FinalTermLoanRequest response = finalTLService.get(id, applicationId);
				if (response != null) {
					LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
					loansResponse.setData(response);
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} catch (Exception e) {
				logger.error("Error while getting Final Term Loan Details==>", e);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting  final information of Term Loan");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "${primary}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePrimary(@RequestBody PrimaryTermLoanRequest termLoanRequest) {
		try {
			// request must not be null
			if (termLoanRequest == null) {
				logger.warn("WorkingCapitalLoanRequest Object can not be empty ==>" + termLoanRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (termLoanRequest.getId() == null) {
				logger.warn("ID must not be empty ==>" + termLoanRequest.getId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("ID must not be empty.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			boolean response = primaryTLService.saveOrUpdate(termLoanRequest);
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),

						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving Primary Working Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "${primary}/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimary(@PathVariable("id") Long id) {
		// request must not be null
		try {
			if (id == null) {
				logger.warn("ID Require to get Primary Working Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			PrimaryTermLoanRequest response = primaryTLService.get(id);
			if (response != null) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Primary Term Loan Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
}
