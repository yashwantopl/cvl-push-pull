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

import com.capitaworld.service.loans.model.CarLoanParameterRequest;
import com.capitaworld.service.loans.model.HomeLoanParameterRequest;
import com.capitaworld.service.loans.model.LasParameterRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.TermLoanParameterRequest;
import com.capitaworld.service.loans.service.fundprovider.CarLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.HomeLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.LasLoanParameterService;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;

@RestController
@RequestMapping("/las_parameter")
public class LasLoanParameterController {

	private static final Logger logger = LoggerFactory.getLogger(LasLoanParameterController.class.getName());
	@Autowired
	private LasLoanParameterService lasLoanParameterService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody LasParameterRequest  lasParameterRequest) {
		// request must not be null
		if (lasParameterRequest == null) {
			logger.warn("lasParameterRequest Object can not be empty ==>", lasParameterRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}

		boolean response = lasLoanParameterService.saveOrUpdate(lasParameterRequest);
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
