package com.capitaworld.service.loans.controller.teaser.finalview;

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

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.CarLoanFinalViewResponse;
import com.capitaworld.service.loans.model.teaser.finalview.HomeLoanFinalViewResponse;
import com.capitaworld.service.loans.service.teaser.finalview.CarLoanFinalViewService;
import com.capitaworld.service.loans.service.teaser.finalview.HomeLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/FinalView")
public class FinalViewController {

private static final Logger logger = LoggerFactory.getLogger(FinalViewController.class);
	
	@Autowired
	private HomeLoanFinalViewService hlFinalViewService;
	
	@Autowired
	private CarLoanFinalViewService clFinalViewService;

	@GetMapping(value = "/HomeLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewHomeLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	HomeLoanFinalViewResponse homeLoanFinalViewResponse = null;
			try {
				homeLoanFinalViewResponse = hlFinalViewService.getHomeLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(homeLoanFinalViewResponse)){
					loansResponse.setData(homeLoanFinalViewResponse);
		            loansResponse.setMessage("Home Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Home Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}      
        }
	}
	
	@GetMapping(value = "/CarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewCarLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	CarLoanFinalViewResponse clFinalViewResponse = null;
			try {
				clFinalViewResponse = clFinalViewService.getHomeLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(clFinalViewResponse)){
					loansResponse.setData(clFinalViewResponse);
		            loansResponse.setMessage("Car Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Car Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}      
        }
	}
	
	/*@GetMapping(value = "/PersonalLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewPersonalLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	CarLoanFinalViewResponse clFinalViewResponse = null;
			try {
				clFinalViewResponse = clFinalViewService.getHomeLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(clFinalViewResponse)){
					loansResponse.setData(clFinalViewResponse);
		            loansResponse.setMessage("Car Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Car Loan final view");
		            loansResponse.setStatus(HttpStatus.OK.value());
				}
		        return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
		            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
		            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}      
        }
	}*/
}
