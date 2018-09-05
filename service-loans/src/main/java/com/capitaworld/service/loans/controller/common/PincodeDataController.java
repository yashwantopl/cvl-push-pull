package com.capitaworld.service.loans.controller.common;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.PincodeDataResponse;
import com.capitaworld.service.loans.service.common.PincodeDateService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class PincodeDataController {

    private static final Logger logger = LoggerFactory.getLogger(PincodeDataController.class);

    @Autowired
    private PincodeDateService pincodeDateService;

    @GetMapping(value = "/pincodeData/{pincode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> isTermLoanLessThanLimit(HttpServletRequest request, @PathVariable String pincode) {
        try {
            if (!CommonUtils.isObjectNullOrEmpty(pincode)) {
                List<PincodeDataResponse> dataResponseList = pincodeDateService.listData(pincode);
                LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
                loansResponse.setData(dataResponseList);
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            }else{
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error while isApplicationEligibleForIrr==>", e);
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
