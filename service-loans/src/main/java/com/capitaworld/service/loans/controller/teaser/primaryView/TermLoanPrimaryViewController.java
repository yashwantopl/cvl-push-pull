package com.capitaworld.service.loans.controller.teaser.primaryView;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
public class TermLoanPrimaryViewController {

	private static final Logger logger = LoggerFactory.getLogger(TermLoanPrimaryViewController.class);

	@Autowired
	private TermLoanPrimaryViewService termLoanPrimaryViewService;

	@RequestMapping(value = "/primaryViewOfTermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfTermLoan(
			@PathVariable(value = "toApplicationId") Long toApplicationId, HttpServletRequest httpServletRequest) {
		try {
		// get user id from http servlet request

		/*Long userId = (Long) 1758l;*/
		Long userId=(Long) httpServletRequest.getAttribute(CommonUtils.USER_ID); 
									
		if (toApplicationId == null) {
			logger.warn("toApplicationId  can not be empty ==>" + userId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		TermLoanPrimaryViewResponse termLoanPrimaryViewResponse = termLoanPrimaryViewService
				.getTermLoanPrimaryViewDetails(toApplicationId, userId);
		LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
		loansResponse.setData(termLoanPrimaryViewResponse);
		return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
		
		} catch (Exception e) {
			logger.error("Error while getting primary Details of term loan==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
}
