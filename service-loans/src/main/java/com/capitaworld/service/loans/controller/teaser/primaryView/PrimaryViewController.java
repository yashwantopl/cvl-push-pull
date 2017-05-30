package com.capitaworld.service.loans.controller.teaser.primaryView;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.*;
import com.capitaworld.service.loans.service.teaser.primaryview.*;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/PrimaryView")
public class PrimaryViewController {

	private static final Logger logger = LoggerFactory.getLogger(PrimaryViewController.class);
	
	@Autowired
	private HomeLoanPrimaryViewService homeLoanPrimaryViewService;

	@Autowired
    private PersonalLoansViewService personalLoansViewService;
	
	@Autowired
    private CarLoanPrimaryViewService carLoanPrimaryViewService;
	
	@Autowired
	private LapPrimaryViewService lapPrimaryService;
	
	@Autowired
    private WorkingCapitalPrimaryViewService workingCapitalPrimaryViewService;
	
	@Autowired
	private TermLoanPrimaryViewService termLoanPrimaryViewService;
	
	@GetMapping(value = "/HomeLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewHomeLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId =  (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
        	HomeLoanPrimaryViewResponse homeLoanPrimaryViewResponse = null;
			try {
				homeLoanPrimaryViewResponse = homeLoanPrimaryViewService.getHomeLoanPrimaryViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(homeLoanPrimaryViewResponse)){
					loansResponse.setData(homeLoanPrimaryViewResponse);
					loansResponse.setMessage("Home Loan Primary Details");
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
	
	@GetMapping(value = "/PersonalLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfPersonalLoans(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = (Long)httpServletRequest.getAttribute(CommonUtils.USER_ID);

		if (CommonUtils.isObjectNullOrEmpty(toApplicationId)) {
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		} else {
			RetailPrimaryViewResponse personalLoansPrimaryViewResponse = null;
			try {
				personalLoansPrimaryViewResponse = personalLoansViewService.getPersonalLoansPrimaryViewDetails(toApplicationId);
				if(!CommonUtils.isObjectNullOrEmpty(personalLoansPrimaryViewResponse)){
					loansResponse.setData(personalLoansPrimaryViewResponse);
					loansResponse.setMessage("Personal Loans Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Personal Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(personalLoansPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

    @GetMapping(value = "/CarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCarLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
			CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = null;
			try {
				carLoanPrimaryViewResponse = carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(carLoanPrimaryViewResponse)){
					loansResponse.setData(carLoanPrimaryViewResponse);
					loansResponse.setMessage("Car Loan Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Car Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(carLoanPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
        }
    }

    @GetMapping(value = "/LapLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfLap(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
		Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            LapPrimaryViewResponse lapPrimaryViewResponse= null;
			try {
				lapPrimaryViewResponse = lapPrimaryService.getLapPrimaryViewDetails(toApplicationId);
				if (!CommonUtils.isObjectNullOrEmpty(lapPrimaryViewResponse)){
					loansResponse.setData(lapPrimaryViewResponse);
					loansResponse.setMessage("LAP Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for LAP Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
	            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(lapPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
	            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
	            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
        }
    }

	@GetMapping(value = "/WorkingCapital/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();

		//get user id from http servlet request
		/*Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		Long userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);*/

		Long userId = 1778l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);*/
		Long userType = 2l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);*/

		if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}else {
			WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = null;
			try {
				workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(toApplicationId,userType,userId);
				if(!CommonUtils.isObjectNullOrEmpty(workingCapitalPrimaryViewResponse)){
					loansResponse.setData(workingCapitalPrimaryViewResponse);
					loansResponse.setMessage("Working Capital Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				}else{
					loansResponse.setMessage("No data found for Working Capital final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
			}catch (Exception e){
				loansResponse.setData(workingCapitalPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}

	@GetMapping(value = "/TermLoan/{toApplicationId}")
	public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfTermLoan(@PathVariable(value = "toApplicationId") Long toApplicationId, HttpServletRequest httpServletRequest) {
		LoansResponse loansResponse = new LoansResponse();

		// get user id from http servlet request
		Long userId = 1778l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);*/
		Long userType = 2l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);*/

		/*Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
		Long userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);*/

		if (toApplicationId == null) {
			logger.warn("Invalid data or Requested data not found.", toApplicationId);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}else {
			TermLoanPrimaryViewResponse termLoanPrimaryViewResponse = null;
			try {
				termLoanPrimaryViewResponse = termLoanPrimaryViewService.getTermLoanPrimaryViewDetails(toApplicationId, userType, userId);
				if (!CommonUtils.isObjectNullOrEmpty(termLoanPrimaryViewResponse)) {
					loansResponse.setData(termLoanPrimaryViewResponse);
					loansResponse.setMessage("Working Capital Primary Details");
					loansResponse.setStatus(HttpStatus.OK.value());
				} else {
					loansResponse.setMessage("No data found for Term Loan final view");
					loansResponse.setStatus(HttpStatus.OK.value());
				}
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(termLoanPrimaryViewResponse);
				loansResponse.setMessage("Something went wrong..!");
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			}
		}
	}
}
