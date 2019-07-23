package com.capitaworld.service.loans.controller.fundseeker;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
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
@RequestMapping("/mfi")
public class MFIApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(MFIApplicationController.class);

    @Autowired
    private MfiApplicationService mfiApplicationService;

    /**
     * save Aadhar detail For create new Application in MFI Application
     * @param aadharDetailsReq
     * @param request
     * @return
     */
    @PostMapping(value = "/saveAdharDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveAdharDetails(@RequestBody AadharDetailsReq aadharDetailsReq, HttpServletRequest request) {
        try {
            // request must not be null
            CommonDocumentUtils.startHook(logger, "save");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            aadharDetailsReq.setUserId(userId);
            mfiApplicationService.saveOrUpdateAadharDetails(aadharDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving save Adhar Details Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAadharDetails/{applicationId}")
    public ResponseEntity<LoansResponse> getAadharDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
        try {
            // request must not be null
            CommonDocumentUtils.startHook(logger, "Get Aadhar");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            mfiApplicationService.getAadharDetailsByAppId(applicationId);
            CommonDocumentUtils.endHook(logger, "Get Aadhar");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Aadhar details.", HttpStatus.OK.value()),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving save Adhar Details Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }


}
