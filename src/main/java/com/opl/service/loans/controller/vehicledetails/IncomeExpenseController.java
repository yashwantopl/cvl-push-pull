package com.opl.service.loans.controller.vehicledetails;


import com.opl.mudra.api.common.CommonUtils;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.ProposedVehicleIncomeExpenseRequest;
import com.opl.service.loans.service.vehicledetails.IncomeExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pooja.patel on 02-12-2020.
 */
@RestController
public class IncomeExpenseController {

    private static final Logger logger = LoggerFactory.getLogger(IncomeExpenseController.class);

    @Autowired
    IncomeExpenseService incomeExpenseService;

    @RequestMapping(value = "/getIncomeExpenseDetails/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getIncomeExpenseDetails(@PathVariable Long applicationId, HttpServletRequest httpRequest) {
        try {
            ProposedVehicleIncomeExpenseRequest incomeExpenseRequest = incomeExpenseService.getIncomeExpenseDetailsByApplicationId(applicationId);
            if (!CommonUtils.isObjectNullOrEmpty(incomeExpenseRequest)) {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data fetched Successfully!!", HttpStatus.OK.value(), incomeExpenseRequest), HttpStatus.OK);
            } else {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data Not Found!!", HttpStatus.NOT_FOUND.value()), HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error("Error in getIncomeExpenseDetails()");
        }
        return new ResponseEntity<LoansResponse>(new LoansResponse("Error while fetching Income Expense Details!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/saveIncomeExpenseDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveIncomeExpenseDetails(@RequestBody ProposedVehicleIncomeExpenseRequest request, HttpServletRequest httpRequest) {
        try {
            Long userId =  (Long) httpRequest.getAttribute(CommonUtils.USER_ID);

            Boolean update = incomeExpenseService.saveIncomeExpenseDetails(request,userId);
            if (update) {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Data saved Successfully!!", HttpStatus.OK.value()), HttpStatus.OK);
            } else {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Something Went Wrong!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
            }
        }catch (Exception e){
            logger.error("Error in getIncomeExpenseDetails()");
        }
        return new ResponseEntity<LoansResponse>(new LoansResponse("Error while saving Income Expense Details!!", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
