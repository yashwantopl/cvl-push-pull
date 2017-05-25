package com.capitaworld.service.loans.controller.teaser.primaryView;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.CarLoanPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.CarLoanPrimaryViewService;
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
 * Created by dhaval on 23-May-17.
 */
@RestController
public class CarLoanPrimaryViewController {

    private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalPrimaryViewController.class);

    @Autowired
    private CarLoanPrimaryViewService carLoanPrimaryViewService;


    @GetMapping(value = "/primaryViewOfCarLoan/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();

        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            //get user id from http servlet request
            Long userId = 1759l;/*(Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);*/
            CarLoanPrimaryViewResponse carLoanPrimaryViewResponse = carLoanPrimaryViewService.getCarLoanPrimaryViewDetails(toApplicationId, userId);
            loansResponse.setData(carLoanPrimaryViewResponse);
            loansResponse.setMessage("Car Loan Primary Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }

    }
}
