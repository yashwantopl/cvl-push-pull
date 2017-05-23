package com.capitaworld.service.loans.controller.teaser.primaryView;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.WorkingCapitalPrimaryViewResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.WorkingCapitalPrimaryViewService;

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
 * Created by dhaval on 18-May-17.
 */
@RestController
public class WorkingCapitalPrimaryViewController {

    private static final Logger logger = LoggerFactory.getLogger(WorkingCapitalPrimaryViewController.class);

    @Autowired
    private WorkingCapitalPrimaryViewService workingCapitalPrimaryViewService;


    @GetMapping(value = "/primaryViewOfWorkingCapital/{toApplicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> primaryViewOfWorkingCapital(@PathVariable(value = "toApplicationId") String toApplicationId,HttpServletRequest httpServletRequest) {
        LoansResponse loansResponse = new LoansResponse();
        //get user id from http servlet request
        Long userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);

        boolean isValidateRequest = workingCapitalPrimaryViewService.validateWorkingCapitalPrimaryViewRequest(toApplicationId);
        if(isValidateRequest){
            logger.warn("Invalid Request {}", toApplicationId);
            return new ResponseEntity(new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }else {
            WorkingCapitalPrimaryViewResponse workingCapitalPrimaryViewResponse = workingCapitalPrimaryViewService.getWorkingCapitalPrimaryViewDetails(Long.parseLong(toApplicationId),userId);
            loansResponse.setData(workingCapitalPrimaryViewResponse);
            loansResponse.setMessage("Working Capital Primary Details");
            loansResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        }
    }

}
