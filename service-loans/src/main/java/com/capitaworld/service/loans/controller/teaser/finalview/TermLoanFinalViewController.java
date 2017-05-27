package com.capitaworld.service.loans.controller.teaser.finalview;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.TermLoanFinalViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.TermLoanFinalViewService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by dhaval on 27-May-17.
 */
@RestController
public class TermLoanFinalViewController {

    private static final Logger logger = LoggerFactory.getLogger(TermLoanFinalViewController.class);

    @Autowired
    private TermLoanFinalViewService termLoanFinalViewService;

    @GetMapping(value = "/finalViewOfTermLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = 1759l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);*/
        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            TermLoanFinalViewResponse termLoanFinalViewResponse = termLoanFinalViewService.getTermLoanFinalViewDetails(toApplicationId, userId);
            loansResponse.setData(termLoanFinalViewResponse);
            loansResponse.setMessage("Term Loan Final View Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }
}
