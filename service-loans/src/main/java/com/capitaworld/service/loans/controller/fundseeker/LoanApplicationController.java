package com.capitaworld.service.loans.controller.fundseeker;

import java.util.List;

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

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;

@RestController
@RequestMapping("/loan_application")
public class LoanApplicationController {

	private static final Logger logger = LoggerFactory.getLogger(LoanApplicationController.class);

	@Autowired
	private LoanApplicationService loanApplicationService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest commonRequest) {
		try {
			// request must not be null
			if (commonRequest == null) {
				logger.warn("commonRequest Object can not be empty ==>" + commonRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			boolean response = loanApplicationService.saveOrUpdate(commonRequest);
			if (response) {

				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while saving applicationRequest Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("id") Long id) {
		// request must not be null
		try {
			if (id == null) {
				logger.warn("ID Require to get Loan Application Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			LoanApplicationRequest response = loanApplicationService.get(id);
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
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/getList/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable("userId") Long userId) {
		// request must not be null
		try {
			if (userId == null) {
				logger.warn("userId Require to get Loan Applications Details ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<LoanApplicationRequest> response = loanApplicationService.getList(userId);
			if (response != null) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setListData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActive(@PathVariable("id") Long id) {
		// request must not be null
		try {
			if (id == null) {
				logger.warn("id Require to Inactive Loan Application Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			boolean response = loanApplicationService.inActive(id);
			if (response) {
				return new ResponseEntity<LoansResponse>(new LoansResponse("Inactivated", HttpStatus.OK.value()),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Loan Application Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
