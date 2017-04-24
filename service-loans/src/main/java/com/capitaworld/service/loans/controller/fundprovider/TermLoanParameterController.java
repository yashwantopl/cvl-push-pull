package com.capitaworld.service.loans.controller.fundprovider;

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

import com.capitaworld.service.loans.model.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.TermLoanParameterRequest;
import com.capitaworld.service.loans.model.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;

@RestController
@RequestMapping("/tl_parameter")
public class TermLoanParameterController {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanParameterController.class.getName());
	@Autowired
	private TermLoanParameterService termLoanParameterService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody TermLoanParameterRequest  termLoanParameterRequest) {
		// request must not be null
		if (termLoanParameterRequest == null) {
			logger.warn("termLoanParameterRequest Object can not be empty ==>", termLoanParameterRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}

		boolean response = termLoanParameterService.saveOrUpdate(termLoanParameterRequest);
		if (response) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
