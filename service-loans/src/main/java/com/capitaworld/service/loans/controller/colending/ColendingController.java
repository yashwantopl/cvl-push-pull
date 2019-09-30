package com.capitaworld.service.loans.controller.colending;

import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.model.corporate.CoLendingRequest;
import com.capitaworld.service.loans.service.colending.CoLendingFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.json.simple.JSONObject;
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
public class ColendingController {

    private static final Logger logger = LoggerFactory.getLogger(ColendingController.class.getName());

    private static final String CO_LENDING_CLIENT_LIST_MSG = "Client list for CoLending";
    private static final String SOMETHING_WENT_WRONG_WHILE_FETCHING_CO_LENDING_CLIENT_COUNT_MSG = "Something went wrong while fetching CoLending client count..!";

    @Autowired
    private CoLendingFlowService coLendingFlowService;

    @RequestMapping(value = "/client/list/fs",method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> clientListFs(@RequestBody UsersRequest usersRequest, HttpServletRequest request){
        Long userId = Long.valueOf(request.getAttribute(CommonUtils.USER_ID).toString());
        if(CommonUtils.isObjectNullOrEmpty(usersRequest) || CommonUtils.isObjectNullOrEmpty(userId) ||
                CommonUtils.isObjectNullOrEmpty(usersRequest.getPageIndex()) ||
                CommonUtils.isObjectNullOrEmpty(usersRequest.getSize())){
            return new ResponseEntity<UserResponse>(

                    new UserResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        try {
            List<ClientListingCoLending> clientList = coLendingFlowService.clientListCoLending(Integer.parseInt(usersRequest.getPageIndex().toString()),Integer.parseInt(usersRequest.getSize().toString()),userId);

            if(clientList != null && !clientList.isEmpty()){
                logger.info(CO_LENDING_CLIENT_LIST_MSG);
                return new ResponseEntity<UserResponse>(
                        new UserResponse(clientList,CO_LENDING_CLIENT_LIST_MSG, HttpStatus.OK.value()),
                        HttpStatus.OK);
            }else{
                logger.info(CommonUtils.DATA_NOT_FOUND);
                return new ResponseEntity<UserResponse>(
                        new UserResponse(CommonUtils.DATA_NOT_FOUND, HttpStatus.OK.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(CommonUtils.SOMETHING_WENT_WRONG,e);
            return new ResponseEntity<UserResponse>(
                    new UserResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/client/count/nbfc",method = RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> nbfcClientCount(HttpServletRequest request){
        if(CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID).toString())){
            return new ResponseEntity<UserResponse>(
                    new UserResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        try {
            JSONObject nbfcClientCount = coLendingFlowService.nbfcClientCount(Long.valueOf(request.getAttribute(CommonUtils.USER_ID).toString()));
            if(!nbfcClientCount.isEmpty()){
                logger.info("co-lending's client count");
                return new ResponseEntity<UserResponse>(
                        new UserResponse(nbfcClientCount,"co-lending's client count", HttpStatus.OK.value()),
                        HttpStatus.OK);
            }else{
                logger.info(SOMETHING_WENT_WRONG_WHILE_FETCHING_CO_LENDING_CLIENT_COUNT_MSG);
                return new ResponseEntity<UserResponse>(
                        new UserResponse("Something went wrong while fetching co-lending client count..!-->", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error(SOMETHING_WENT_WRONG_WHILE_FETCHING_CO_LENDING_CLIENT_COUNT_MSG,e);
            return new ResponseEntity<UserResponse>(
                    new UserResponse(SOMETHING_WENT_WRONG_WHILE_FETCHING_CO_LENDING_CLIENT_COUNT_MSG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/nbfc/finalCalculation/{applicationId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> nbfcFlowBlendedCalculation(@PathVariable("applicationId") Long applicationId, HttpServletRequest request){
        if(CommonUtils.isObjectNullOrEmpty(applicationId)){
            return new ResponseEntity<UserResponse>(
                    new UserResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        try {
            Boolean isRateCalculated = coLendingFlowService.calculateBlendedRateNbfc(applicationId);
            if(!CommonUtils.isObjectNullOrEmpty(isRateCalculated) && isRateCalculated){
                logger.info("co-origination's flow,blended rate calculated");
                return new ResponseEntity<UserResponse>(
                        new UserResponse(isRateCalculated,"co-origination's flow,blended rate calculated", HttpStatus.OK.value()),
                        HttpStatus.OK);
            }else{
                logger.info("co-origination's flow,blended rate calculated-->"+isRateCalculated);
                return new ResponseEntity<UserResponse>(
                        new UserResponse("Something went wrong while co-origination's flow,blended rate calculation", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        HttpStatus.OK);
            }
        } catch (Exception e) {
            logger.error("Something went wrong while co-origination's flow,blended rate calculation - nbfcFlowBlendedCalculation()",e);
            return new ResponseEntity<UserResponse>(
                    new UserResponse("Something went wrong while co-origination's flow,blended rate calculation - nbfcFlowBlendedCalculation()", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }

    }
}
