package com.capitaworld.service.loans.controller.teaser.finalview;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.*;
import com.capitaworld.service.loans.service.teaser.finalview.*;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/FinalView")
public class FinalViewController {

private static final Logger logger = LoggerFactory.getLogger(FinalViewController.class);
	
	@Autowired
	private HomeLoanFinalViewService hlFinalViewService;
	
	@Autowired
	private CarLoanFinalViewService clFinalViewService;
	
	@Autowired
	private PersonalLoanFinalViewService plFinalViewService;

	@Autowired
	private WorkingCapitalFinalService wcFinalService;

	@Autowired
	private TermLoanFinalViewService tlFinalViewService;

	@Autowired
	private LapFinalViewService lapFinalViewService;
	
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
				clFinalViewResponse = clFinalViewService.getCarLoanFinalViewDetails(toApplicationId);
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
	
	@GetMapping(value = "/PersonalLoan/{toApplicationId}")
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
        	PersonalLoanFinalViewResponse plFinalViewResponse = null;
			try {
				plFinalViewResponse = plFinalViewService.getPersonalLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(plFinalViewResponse)){
					loansResponse.setData(plFinalViewResponse);
		            loansResponse.setMessage("Personal Loan Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Personal Loan final view");
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
	
	@GetMapping(value = "/LapLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewLapLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
        }else {
        	LapFinalViewResponse lapFinalViewResponse = null;
			try {
				lapFinalViewResponse = lapFinalViewService.getLapFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(lapFinalViewResponse)){
					loansResponse.setData(lapFinalViewResponse);
		            loansResponse.setMessage("Lap Final Details");
		            loansResponse.setStatus(HttpStatus.OK.value());	
				}else{
		            loansResponse.setMessage("No data found for Lap final view");
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

	@GetMapping(value = "/WorkingCapital/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> finalViewWrokingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
		//get user id from http servlet request
		Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}else {
			WorkingCapitalFinalViewResponse workingCapitalFinalViewResponse = null;
			try {
				workingCapitalFinalViewResponse = wcFinalService.getWorkingCapitalFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(workingCapitalFinalViewResponse)){
					loansResponse.setData(workingCapitalFinalViewResponse);
					loansResponse.setMessage("Working Capital Final Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for working capital final view");
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

	@GetMapping(value = "/TermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> finalViewTermLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();
		//get user id from http servlet request
		Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}else {
			TermLoanFinalViewResponse termLoanFinalViewResponse = null;
			try {
				termLoanFinalViewResponse = tlFinalViewService.getTermLoanFinalViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(termLoanFinalViewResponse)){
					loansResponse.setData(termLoanFinalViewResponse);
					loansResponse.setMessage("Term Loan Final Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Term Loan final view");
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
}
