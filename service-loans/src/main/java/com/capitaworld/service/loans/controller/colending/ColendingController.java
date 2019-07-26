package com.capitaworld.service.loans.controller.colending;

import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.service.colending.CoLendingFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class ColendingController {

    private static final Logger logger = LoggerFactory.getLogger(ColendingController.class.getName());

    private static final String CO_LENDING_CLIENT_LIST_MSG = "Client list for CoLending";
    private static final String SOMETHING_WENT_WRONG_WHILE_FETCHING_SP_CLIENT_COUNT_MSG = "Something went wrong while fetching CoLending client count..!";

    @Autowired
    private CoLendingFlowService coLendingFlowService;

    @RequestMapping(value = "/client/list/fs",method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> clientListFs(@RequestBody UsersRequest usersRequest, HttpServletRequest request){
        if(CommonUtils.isObjectNullOrEmpty(usersRequest) ||
                CommonUtils.isObjectNullOrEmpty(usersRequest.getPageIndex()) ||
                CommonUtils.isObjectNullOrEmpty(usersRequest.getSize())){
            return new ResponseEntity<UserResponse>(

                    new UserResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        try {
            List<ClientListingCoLending> clientList = coLendingFlowService.clientListCoLending(Integer.parseInt(usersRequest.getPageIndex().toString()),Integer.parseInt(usersRequest.getSize().toString()),Long.valueOf(request.getAttribute(CommonUtils.USER_ID).toString()));

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
}
