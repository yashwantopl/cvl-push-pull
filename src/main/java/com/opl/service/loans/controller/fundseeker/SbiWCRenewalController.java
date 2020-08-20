package com.opl.service.loans.controller.fundseeker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.service.loans.service.fundseeker.corporate.SbiWCRenewalService;

/**
 * Created by dhaval.panchal on 25-Apr-19.
 */
@RestController
public class SbiWCRenewalController {

    @Autowired
    private SbiWCRenewalService sbiWCRenewalService;

    @GetMapping(value = "/sbi/renewal/{applicationId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "userId") Long userId) {
         //skip payment request
        Boolean isProceed = null;
        boolean isMatchesDone = sbiWCRenewalService.callMatchEngine(applicationId,userId);
        if (isMatchesDone) {
            sbiWCRenewalService.callSkipPayment(applicationId,userId);
            isProceed =true;
        }
        LoansResponse response = new LoansResponse();
        response.setData(isProceed);
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/fs/bank/specific/{applicationId}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> fsBankSpecificPostOneForm(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "userId") Long userId) {
         //skip payment request
        Boolean isProceed = null;
        boolean isMatchesDone = sbiWCRenewalService.callMatchEngine(applicationId,userId);
        if (isMatchesDone) {
            sbiWCRenewalService.callSkipPayment(applicationId,userId);
            isProceed =true;
        }
        LoansResponse response = new LoansResponse();
        response.setData(isProceed);
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
    }
}
