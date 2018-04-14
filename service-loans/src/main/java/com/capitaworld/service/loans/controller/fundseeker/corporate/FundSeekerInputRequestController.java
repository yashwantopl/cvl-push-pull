package com.capitaworld.service.loans.controller.fundseeker.corporate;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
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
@RequestMapping("/fundseeker_input_request")
public class FundSeekerInputRequestController {


    private static final Logger logger = LoggerFactory.getLogger(FundSeekerInputRequestController.class);

    @Autowired
    private FundSeekerInputRequestService fundSeekerInputRequestService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws Exception
    {
        try {
        	Long userId = fundSeekerInputRequestResponse.getUserId();
        	if(userId == null) {
        		fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));        		
        	}

            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
                logger.warn("userId/applicationId can not be empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            return fundSeekerInputRequestService.saveOrUpdate(fundSeekerInputRequestResponse);


        } catch (Exception e) {
            logger.error("Error while saving fund seeker input request");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> get(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws Exception
    {
        try
        {
        	Long userId = fundSeekerInputRequestResponse.getUserId();
        	if(userId == null) {
        		fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));        		
        	}
        	logger.info("Application Id for Getting============>{}",fundSeekerInputRequestResponse.getApplicationId());
        	//Commented by Akshay discussed with Hiren

//            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
//                logger.warn("userId/applicationId can not be empty");
//                return new ResponseEntity<LoansResponse>(
//                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
//            }

            return fundSeekerInputRequestService.get(fundSeekerInputRequestResponse);


        } catch (Exception e) {
            logger.error("Error while fetching fund seeker input request");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @RequestMapping(value = "/match/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> callMatchengine(@RequestBody Long applicationId,HttpServletRequest request)
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	logger.info("Application Id for Getting============>{}",applicationId);
        	//Commented by Akshay discussed with Hiren

//            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
//                logger.warn("userId/applicationId can not be empty");
//                return new ResponseEntity<LoansResponse>(
//                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
//            }

            LoansResponse callMatchEngineClient = fundSeekerInputRequestService.callMatchEngineClient(applicationId,userId);
            logger.info("Response from Matchengine ==>{}",callMatchEngineClient.toString());
            return new ResponseEntity<LoansResponse>(callMatchEngineClient, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after Oneform Submit");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }
}
