package com.capitaworld.service.loans.controller.fundseeker.retail;

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
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.ExistingLoanDetailRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.ExistingLoanDetailsService;

/**
 * @author Sanket
 *
 */
@RestController
@RequestMapping("/existing_loan_details")
public class ExistingLoanDetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ExistingLoanDetailsController.class);
	
	@Autowired
	private ExistingLoanDetailsService existingLoanDetailsService;
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody FrameRequest frameRequest) {
		// request must not be null
		if (frameRequest == null) {
			logger.warn("frameRequest can not be empty ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		//application id and user id must not be null
		if(frameRequest.getApplicationId()==null||frameRequest.getUserId()==null || frameRequest.getApplicantType()==0)
		{
			logger.warn("application id, user id and applicant Type must not be null ==>" + frameRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
		try
		{
		boolean response = existingLoanDetailsService.saveOrUpdate(frameRequest);
		if (response) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		}
		catch (Exception e) {
			   logger.error("Error while saving Existing Loan Details==>", e);
			   return new ResponseEntity<LoansResponse>(
			     new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
			     HttpStatus.OK);
			  }

	}
	
	@RequestMapping(value = "/getList/{applicationType}/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getList(@PathVariable Long id,@PathVariable int applicationType) {
		// request must not be null
		  try {
		   if (id == null) {
		    logger.warn("ID Require to get Existing Loan Details ==>" + id);
		    return new ResponseEntity<LoansResponse>(
		      new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		   }

		   List<ExistingLoanDetailRequest> response = existingLoanDetailsService.getExistingLoanDetailList(id, applicationType);
		   if (response != null && !response.isEmpty()) {
		    LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
		    loansResponse.setListData(response);
		    return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		   } else {
		    return new ResponseEntity<LoansResponse>(
		      new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
		      HttpStatus.OK);
		   }
		  } catch (Exception e) {
		   logger.error("Error while getting Existing Loan Details==>", e);
		   return new ResponseEntity<LoansResponse>(
		     new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
		     HttpStatus.INTERNAL_SERVER_ERROR);
		  }

	}

}
