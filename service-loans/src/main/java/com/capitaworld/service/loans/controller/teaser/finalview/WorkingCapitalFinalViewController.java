package com.capitaworld.service.loans.controller.teaser.finalview;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.finalview.WorkingCapitalFinalViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalFinalService;

/**
 * Created by dhaval on 18-May-17.
 */
@RestController
public class WorkingCapitalFinalViewController {

    private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalFinalViewController.class);

    @Autowired
    private WorkingCapitalFinalService workingCapitalFinalService;

    @GetMapping(value = "/finalViewOfWorkingCapital/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewOfWorkingCapital(@PathVariable(value = "toApplicationId") Long toApplicationId,HttpServletRequest httpServletRequest) {



        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
       /* Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
        if(CommonUtils.isObjectNullOrEmpty(toApplicationId)){
            logger.warn("Invalid data or Requested data not found.", toApplicationId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
        }else {
            WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(toApplicationId,userId);
            loansResponse.setData(workingCapitalPrimaryViewResponse);
            loansResponse.setMessage("Working Capital Primary Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }*/
    	WorkingCapitalFinalViewResponse response =  workingCapitalFinalService.getWorkingCapitalFinalViewDetails(toApplicationId,52l);
    	loansResponse.setData(response);
        return new ResponseEntity<LoansResponse>(loansResponse,HttpStatus.OK);
    }
}
