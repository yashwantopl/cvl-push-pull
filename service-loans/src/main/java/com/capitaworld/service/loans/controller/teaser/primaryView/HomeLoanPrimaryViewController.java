package com.capitaworld.service.loans.controller.teaser.primaryView;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.controller.fundseeker.LoanApplicationController;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/home_loan_primary_view")
public class HomeLoanPrimaryViewController {
	
	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;

	private static final Logger logger = LoggerFactory.getLogger(HomeLoanPrimaryViewController.class);
	
	@GetMapping(value = "/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewHomeLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse = null;
			try {
				homeLoanPrimaryViewResponse = homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(toApplicationId);
				 loansResponse.setData(homeLoanPrimaryViewResponse);
		            loansResponse.setMessage("Home Loan Primary Details");
		            loansResponse.setStatus(HttpStatus.OK.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
           
        }
    }
}
