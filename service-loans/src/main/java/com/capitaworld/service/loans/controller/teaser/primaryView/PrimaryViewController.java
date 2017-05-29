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

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.HomeLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.TermLoanPrimaryViewResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.HomeLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanPrimaryViewService;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

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
				loansResponse.setData(personalLoansPrimaryViewResponse);
				loansResponse.setMessage("Personal Loans Primary Details");
				loansResponse.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} catch (Exception e) {
				loansResponse.setData(personalLoansPrimaryViewResponse);
				loansResponse.setMessage(e.getMessage());
				loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

    @GetMapping(value = "/CarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfCarLoan(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            //get user id from http servlet request
            Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
            CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(toApplicationId);
            loansResponse.setData(carLoanPrimaryViewResponse);
            loansResponse.setMessage("Car Loan Primary Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/WorkingCapital/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(toApplicationId);
            loansResponse.setData(workingCapitalPrimaryViewResponse);
            loansResponse.setMessage("Working Capital Primary Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

	@RequestMapping(value = "/TermLoan/{toApplicationId}")
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
				.getTermLoanPrimaryViewDetails(toApplicationId);
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
