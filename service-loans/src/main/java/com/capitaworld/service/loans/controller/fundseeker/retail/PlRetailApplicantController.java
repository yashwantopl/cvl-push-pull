package com.capitaworld.service.loans.controller.fundseeker.retail;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;
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
    public ResponseEntity<LoansResponse> saveProfile(@RequestBody PLRetailApplicantRequest plRetailApplicantRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if (plRetailApplicantRequest == null) {
                logger.warn("applicantRequest  can not be empty ==>", plRetailApplicantRequest);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (plRetailApplicantRequest.getApplicationId() == null) {
                logger.warn("Application Id can not be empty ==>" + plRetailApplicantRequest.getApplicationId());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (CommonDocumentUtils.isThisClientApplication(request)) {
                plRetailApplicantRequest.setClientId(clientId);
            }
            plRetailApplicantService.saveProfile(plRetailApplicantRequest, userId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/profile/get/{applicationId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getProfile(@PathVariable("applicationId")  Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (applicationId == null) {
                logger.warn("ApplicationId Require to get Retail Profile Details. Application Id ==> NULL");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getProfile(userId,applicationId);
            LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
            loansResponse.setData(plRetailApplicantRequest);
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while getting Retail Applicant Profile Details==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/primary/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> savePrimary(@RequestBody PLRetailApplicantRequest plRetailApplicantRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if (plRetailApplicantRequest == null) {
                logger.warn("applicantRequest  can not be empty ==>", plRetailApplicantRequest);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (plRetailApplicantRequest.getApplicationId() == null) {
                logger.warn("Application Id can not be empty ==>" + plRetailApplicantRequest.getApplicationId());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (CommonDocumentUtils.isThisClientApplication(request)) {
                plRetailApplicantRequest.setClientId(clientId);
            }
            plRetailApplicantService.savePrimary(plRetailApplicantRequest, userId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/primary/get/{applicationId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getPrimary(@PathVariable("applicationId")  Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (applicationId == null) {
                logger.warn("ApplicationId Require to get Retail Primary Details. Application Id ==> NULL");
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            PLRetailApplicantRequest plRetailApplicantRequest = plRetailApplicantService.getPrimary(userId,applicationId);
            LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
            loansResponse.setData(plRetailApplicantRequest);
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while getting Retail Applicant Primary Details==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/final/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveFinal(@RequestBody RetailFinalInfoRequest retailFinalInfoRequest,
                                                   HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if (retailFinalInfoRequest == null) {
                logger.warn("applicantRequest can not be empty ==>", retailFinalInfoRequest);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (retailFinalInfoRequest.getApplicationId() == null) {
                logger.warn("Application Id  can not be empty Application ID==>" + retailFinalInfoRequest.getApplicationId());
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);

            }
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                retailFinalInfoRequest.setClientId(clientId);
            }
            plRetailApplicantService.saveFinal(retailFinalInfoRequest, userId);
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                    HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/final/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getFinal(@PathVariable("applicationId") Long applicationId,
                                                  HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            Long userId = null;
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                userId = clientId;
            } else {
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            }
            if (applicationId == null) {
                logger.warn("Application ID Require to get Retail Final Profile Details. Application ID==>"
                        + applicationId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            RetailFinalInfoRequest response = plRetailApplicantService.getFinal(userId, applicationId);
            LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
            loansResponse.setData(response);
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting Retail Applicant Final  Details==>", e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
