package com.capitaworld.service.loans.controller.fundseeker.retail;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.PLRetailApplicant;
import com.capitaworld.service.loans.service.fundseeker.retail.PlRetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/sbi_pl")
public class PlRetailApplicantController {

    private static final Logger logger = LoggerFactory.getLogger(PlRetailApplicantController.class.getName());

    @Autowired
    private PlRetailApplicantService plRetailApplicantService;

    @PostMapping(value = "/profile/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody PLRetailApplicant plRetailApplicant, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if (plRetailApplicant == null) {
                logger.warn("applicantRequest  can not be empty ==>", plRetailApplicant);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (plRetailApplicant.getApplicationId() == null) {
                logger.warn("Application Id can not be empty ==>" + plRetailApplicant.getApplicationId());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (CommonDocumentUtils.isThisClientApplication(request)) {
                plRetailApplicant.setClientId(clientId);
            }
            plRetailApplicantService.save(plRetailApplicant, userId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/profile/get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> get(PLRetailApplicant plRetailApplicant, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            Long userId = null;
            Long applicationId = null;
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (plRetailApplicant.getApplicationId() == null) {
                logger.warn("ApplicationId Require to get Retail Profile Details. Application Id ==> NULL");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }else{
               applicationId = plRetailApplicant.getApplicationId();
            }

            plRetailApplicant = plRetailApplicantService.get(userId,applicationId);
            LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
            loansResponse.setData(plRetailApplicant);
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while getting Retail Applicant Profile Details==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
