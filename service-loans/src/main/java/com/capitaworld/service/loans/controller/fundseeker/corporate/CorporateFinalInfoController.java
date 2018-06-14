package com.capitaworld.service.loans.controller.fundseeker.corporate;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.CorporateFinalInfoRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateFinalInfoService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
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
@RequestMapping("/final_info")
public class CorporateFinalInfoController {

    private static final Logger logger = LoggerFactory.getLogger(CorporateFinalInfoController.class.getName());

    @Autowired
    private CorporateFinalInfoService corporateFinalInfoService;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody CorporateFinalInfoRequest corporateFinalInfoRequest,
                                              HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        try {
            CommonDocumentUtils.startHook(logger, "save");
            // request must not be null
            Long userId = null;

            // Long tempUserId =null;// (Long) request.getAttribute(CommonUtils.USER_ID);
            // if(tempUserId != null){
            // userId =tempUserId;
            // }
            // else if(applicantRequest.getUserId() !=null){
            // userId = applicantRequest.getUserId();
            // }
            // else{
            // userId = null;
            // }

            // ==============

            if ((!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_TYPE)))
                    && (CommonDocumentUtils.isThisClientApplication(request))) {
                corporateFinalInfoRequest.setClientId(clientId);
                userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            } else {
                if (!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
                    userId = (Long) request.getAttribute(CommonUtils.USER_ID);
                } else if (!CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getUserId())) {
                    userId = corporateFinalInfoRequest.getUserId();
                } else {
                    logger.warn("Invalid request.");
                    return new ResponseEntity<LoansResponse>(
                            new LoansResponse("Invalid request.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
                }
            }

            // ==============
            // if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)
            // request.getAttribute(CommonUtils.USER_TYPE))
            // .intValue()) {
            // applicantRequest.setClientId(clientId);
            // }

            if (corporateFinalInfoRequest == null) {
                logger.warn("corporateFinalInfoRequest  can not be empty ==>", userId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (corporateFinalInfoRequest.getApplicationId() == null) {
                logger.warn("Application Id can not be empty ==>", corporateFinalInfoRequest);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            corporateFinalInfoRequest.setUserId(userId);
            // Checking Profile is Locked
            Long finalUserId = (CommonUtils.isObjectNullOrEmpty(corporateFinalInfoRequest.getClientId()) ? userId
                    : corporateFinalInfoRequest.getClientId());
            Boolean finalLocked = loanApplicationService.isFinalLocked(corporateFinalInfoRequest.getApplicationId(),
                    finalUserId);
            if (!CommonUtils.isObjectNullOrEmpty(finalLocked) && finalLocked.booleanValue()) {
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.APPLICATION_LOCKED_MESSAGE, HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }

            corporateFinalInfoService.saveOrUpdate(corporateFinalInfoRequest, userId);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> get(@PathVariable("applicationId") Long applicationId,
                                             HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        // request must not be null
        try {
            CommonDocumentUtils.startHook(logger, "get");
            Long id = null;
            if (CommonDocumentUtils.isThisClientApplication(request)) {
                id = clientId;
            } else {
                id = (Long) request.getAttribute(CommonUtils.USER_ID);
            }

            if (applicationId == null) {
                logger.warn(
                        "ApplicationId Require to get Corporate Final Info Details. Application Id ==>" + applicationId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            CorporateFinalInfoRequest response = corporateFinalInfoService.get(id, applicationId);
            LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
            loansResponse.setData(response);
            CommonDocumentUtils.endHook(logger, "get");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while getting Final Applicant Info Details==>", e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
