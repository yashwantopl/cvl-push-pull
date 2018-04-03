package com.capitaworld.service.loans.controller.fundseeker.corporate;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.CorporateMcqRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateMcqService;
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
@RequestMapping("/corporate_mcq")
public class CorporateMcqController {

    private static final Logger logger = LoggerFactory.getLogger(CorporateMcqController.class);

    @Autowired
    private CorporateMcqService corporateMcqService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String getPing() {
        logger.info("Ping success");
        return "Ping Succeed";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody CorporateMcqRequest corporateMcqRequest,
                                              HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId)
            throws Exception {
        try {
            CommonDocumentUtils.startHook(logger, "save");
            // request must not be null

            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId can not be empty ==>" + corporateMcqRequest);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (corporateMcqRequest.getApplicationId() == null) {
                logger.warn("Application ID can not be empty ==>" + corporateMcqRequest.getId());
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Application ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
            if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() ||
                    CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
                            .intValue()) {
                corporateMcqRequest.setClientId(clientId);
            }
            corporateMcqService.saveOrUpdate(corporateMcqRequest, userId);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                    HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while saving final corporate mcq");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> get(@PathVariable("applicationId") Long applicationId,
                                                  HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
        try {
            try {
                CommonDocumentUtils.startHook(logger, "get");
                Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
                if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue() ||
                        CommonUtils.UserType.NETWORK_PARTNER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))
                                .intValue()) {
                    userId = clientId;
                } else {
                    userId = (Long) request.getAttribute(CommonUtils.USER_ID);
                }
                if (userId == null || applicationId == null) {
                    logger.warn("ID and ApplicationId Require to get Final  corporate mcq. ID==>" + userId
                            + " and ApplicationId==>" + applicationId);
                    return new ResponseEntity<LoansResponse>(
                            new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
                }
                CorporateMcqRequest response = corporateMcqService.get(userId, applicationId);
                LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
                loansResponse.setData(response);
                CommonDocumentUtils.endHook(logger, "get");
                return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
            } catch (Exception e) {
                logger.error("Error while getting Final corporate mcq==>", e);
                e.printStackTrace();
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Error while getting  final corporate mcq");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }
}
