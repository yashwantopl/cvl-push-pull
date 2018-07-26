package com.capitaworld.service.loans.controller.fundseeker.corporate;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
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
import java.util.List;

@RestController
@RequestMapping("/ntb")
public class NTBController {

    private static final Logger logger = LoggerFactory.getLogger(NTBController.class);

    @Autowired
    private NTBService ntbService;
    @Autowired
    private DirectorBackgroundDetailsService directorBackgroundDetailsService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String getPing() {
        logger.info("Ping success");
        return "Ping Succeed";
    }

    @RequestMapping(value = "/oneform_director_detail/get/{directorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getOneformDetailByDirectorId(@PathVariable("directorId") Long directorId, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter getOneformDetailByDirectorId()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else{
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }
        // request must not be null
        try {
            if (directorId == null ) {

                logger.warn("directorId Require to get director Background Details ==>" + directorId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            DirectorBackgroundDetailRequest directorBackgroundDetailRequest = null;



            directorBackgroundDetailRequest = ntbService.getOneformDetailByDirectorId(directorId);

            LoansResponse loansResponse = null;
            if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest)) {
                loansResponse = new LoansResponse("Successfully Fetched ", HttpStatus.OK.value());
                loansResponse.setData(directorBackgroundDetailRequest);
            }else {
                loansResponse = new LoansResponse("Error while getting getOneformDetail for Director Id", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit getOneformDetailByDirectorId()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while fetching data for getOneformDetailByDirectorId()==>", e);
            logger.info("Exit getOneformDetailByDirectorId()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/financial/get/{applicationId}/{directorId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getFinancialForPartner(@PathVariable("applicationId") Long applicationId, @PathVariable("directorId") Long directorId, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter getFinancialForPartner()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else{
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }
        // request must not be null
        try {
            if (directorId == null || applicationId == null) {
                logger.warn("required parameter is null ==> directorId : " + directorId + " applicationId : " + applicationId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList = null;
            financialArrangementsDetailRequestList = ntbService.getFinancialDetails(applicationId, directorId);

            LoansResponse loansResponse = null;
            if(!CommonUtils.isObjectNullOrEmpty(financialArrangementsDetailRequestList)) {
                loansResponse = new LoansResponse("Successfully Fetched ", HttpStatus.OK.value());
                loansResponse.setData(financialArrangementsDetailRequestList);
            }else {
                loansResponse = new LoansResponse("Error while getting getFinancialForPartner", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit getFinancialForPartner()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while fetching data for getFinancialForPartner()==>", e);
            logger.info("Exit getFinancialForPartner()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/oneform_director_detail/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveOneformDetailForDirector(@RequestBody DirectorBackgroundDetailRequest directorBackgroundDetailRequest, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter saveOneformDetailForDirector()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }else{
            logger.warn("userId can not be empty ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        // request must not be null
        try {
            if (CommonUtils.isObjectNullOrEmpty(directorBackgroundDetailRequest)) {
                logger.warn("Object can not be empty ==>" + directorBackgroundDetailRequest);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            Boolean result = ntbService.saveOneformDetailForDirector(directorBackgroundDetailRequest, userId);
            LoansResponse loansResponse = null;
            if(result) {
                loansResponse = new LoansResponse("Successfully Saved", HttpStatus.OK.value());
            }else {
                loansResponse = new LoansResponse("Something goes wrong while saving saveOneformDetailForDirector", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit saveOneformDetailForDirector()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving data for saveOneformDetailForDirector()==>", e);
            logger.info("Exit saveOneformDetailForDirector()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/financial/save/{applicationId}/{directorId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveFinancialForPartner(@PathVariable("applicationId") Long applicationId, @PathVariable("directorId") Long directorId, @RequestBody List<FinancialArrangementsDetailRequest> financialArrangementsDetailRequestList, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter saveFinancialForPartner()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }else{
            logger.warn("userId can not be empty ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        // request must not be null
        try {
            if (CommonUtils.isListNullOrEmpty(financialArrangementsDetailRequestList)) {
                logger.warn("Object can not be empty ==>" + financialArrangementsDetailRequestList);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            Boolean result = ntbService.saveFinancialDetails(financialArrangementsDetailRequestList, applicationId, userId, directorId);
            LoansResponse loansResponse = null;
            if(result) {
                loansResponse = new LoansResponse("Successfully Saved", HttpStatus.OK.value());
               /* List<DirectorBackgroundDetailRequest> response = directorBackgroundDetailsService.getDirectorBackgroundDetailList(applicationId,userId);
                if(!CommonUtils.isListNullOrEmpty(response)){
                      loansResponse.setListData(response);
                      loansResponse.setData(true);
                }*/
            }else {
                loansResponse = new LoansResponse("Something goes wrong while saving saveFinancialForPartner", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit saveFinancialForPartner()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving data for saveFinancialForPartner()==>", e);
            logger.info("Exit saveFinancialForPartner()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/other/get/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getOtherDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter getOtherDetails()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }else{
            logger.warn("userId can not be empty ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        // request must not be null
        try {
            if (applicationId == null) {
                logger.warn("required parameter is null ==>  " +  " applicationId : " + applicationId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            FundSeekerInputRequestResponse fundSeekerInputRequestResponse= ntbService.getOthersDetail(applicationId);

            LoansResponse loansResponse = null;
            if(!CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse)) {
                loansResponse = new LoansResponse("Successfully Fetched ", HttpStatus.OK.value());
                loansResponse.setData(fundSeekerInputRequestResponse);
            }else {
                loansResponse = new LoansResponse("Error while getting getOtherDetails", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit getOtherDetails()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while fetching data for getOtherDetails()==>", e);
            logger.info("Exit getOtherDetails()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/other/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveOtherDetails(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse, HttpServletRequest request, @RequestParam(value = "clientId",required = false) Long clientId) {
        logger.info("Enter saveOtherDetails()");
        Long userId = null;
        if(CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)){
            userId = clientId;
        }else if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))){
            userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        }else{
            logger.warn("userId can not be empty ");
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }

        // request must not be null
        try {
            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse)) {
                logger.warn("Object can not be empty ==>" + fundSeekerInputRequestResponse);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            Boolean result = ntbService.saveOthersDetail(fundSeekerInputRequestResponse, fundSeekerInputRequestResponse.getApplicationId(), userId);
            LoansResponse loansResponse = null;
            if(result) {
                loansResponse = new LoansResponse("Successfully Saved", HttpStatus.OK.value());
            }else {
                loansResponse = new LoansResponse("Something goes wrong while saving saveOtherDetails", HttpStatus.BAD_REQUEST.value());
            }
            logger.info("Exit saveOtherDetails()");
            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving data for saveOtherDetails()==>", e);
            logger.info("Exit saveOtherDetails()");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }


    @RequestMapping(value = "/post/directorBackground", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> callPostIndividualDirector(@RequestBody NTBRequest ntbRequest, HttpServletRequest request)
            throws Exception
    {
        try
        {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if(userId == null) {
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if(CommonUtils.isObjectListNull(ntbRequest.getDirectorId(),ntbRequest.getApplicationId(),ntbRequest.getBusineeTypeId())) {
                logger.info("Director Id or Application Id or BusinessTypeId is NUll============>{}",ntbRequest.toString());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST,HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
            ntbRequest.setUserId(userId);
            logger.info("Application Id for Getting============>{}",ntbRequest.getApplicationId());
            LoansResponse callMatchEngineClient = ntbService.postDirectorBackground(ntbRequest);
            logger.info("Response from directorBackground ==>{}",callMatchEngineClient.toString());
            return new ResponseEntity<LoansResponse>(callMatchEngineClient, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after directorBackground");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/post/dirBackChangeStage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> postDirectorsChangeStage(@RequestBody NTBRequest ntbRequest, HttpServletRequest request)
            throws Exception {
    	logger.info("ENTER IN POST DIRECTOR BACKGROUD DETAILS --------------> ");
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if(userId == null) {
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if(CommonUtils.isObjectListNull(ntbRequest.getApplicationId(),ntbRequest.getBusineeTypeId())) {
                logger.info("Application Id or BusinessTypeId is NUll============>{}",ntbRequest.toString());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST,HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
            ntbRequest.setUserId(userId);
            logger.info("Application Id for Getting============>{}",ntbRequest.getApplicationId());
            LoansResponse loanResponse = ntbService.postDirectorsChangeStage(ntbRequest);
            logger.info("Response from dirBackChangeStage ==>{}",loanResponse.toString());
            return new ResponseEntity<LoansResponse>(loanResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after dirBackChangeStage");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/post/others", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> postOthersChangeStage(@RequestBody NTBRequest ntbRequest, HttpServletRequest request)
            throws Exception
    {
        try
        {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if(userId == null) {
                return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            if(CommonUtils.isObjectListNull(ntbRequest.getApplicationId(),ntbRequest.getBusineeTypeId())) {
                logger.info("Application Id or BusinessTypeId is NUll============>{}",ntbRequest.toString());
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST,HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
            }
            ntbRequest.setUserId(userId);
            logger.info("Application Id for Getting============>{}",ntbRequest.getApplicationId());
            LoansResponse loanResponse = ntbService.postOthersChangeStage(ntbRequest);
            logger.info("Response from postOthersChangeStage ==>{}",loanResponse.toString());
            return new ResponseEntity<LoansResponse>(loanResponse, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after Oneform Submit");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

}
