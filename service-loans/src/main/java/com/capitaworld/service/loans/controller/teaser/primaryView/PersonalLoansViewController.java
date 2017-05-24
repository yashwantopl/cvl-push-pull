package com.capitaworld.service.loans.controller.teaser.primaryView;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.PersonalLoansViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Sanket
 *
 */
@RestController
public class PersonalLoansViewController {
	

	    private static final Logger logger = LoggerFactory.getLogger(PersonalLoansViewController.class);

	    @Autowired
	    private PersonalLoansViewService personalLoansViewService;

	    @RequestMapping(value = "/ping", method = RequestMethod.GET)
		public String getPing() {
			logger.info("Ping success");
			return "Ping Succeed";
		}

	    @GetMapping(value = "/primaryViewOfPersonalLoans/{toApplicationId}")
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
					personalLoansPrimaryViewResponse = personalLoansViewService.getPersonalLoansPrimaryViewDetails(toApplicationId, userId);
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

}
