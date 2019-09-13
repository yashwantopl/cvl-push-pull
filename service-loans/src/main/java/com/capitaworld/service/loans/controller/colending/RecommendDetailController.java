package com.capitaworld.service.loans.controller.colending;

import com.capitaworld.service.loans.model.colending.CoLendingApplicationBankMappingRequest;
import com.capitaworld.service.loans.model.colending.RecommendDetailRequest;
import com.capitaworld.service.loans.service.colending.RecommendDetailService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.users.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dhaval.panchal on 12-Sep-19.
 */
@RestController
public class RecommendDetailController {

    private static final Logger logger = LoggerFactory.getLogger(RecommendDetailController.class.getName());

    @Autowired
    private RecommendDetailService recommendDetailService;

    /**
     * @author Dhaval
     * @param request
     * @return userResponse
     * save recommend value in DB
     */
    @PostMapping(value = "/recommend/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> save(@RequestBody RecommendDetailRequest request) {
        if (CommonUtils.isObjectNullOrEmpty(request)) {
            return new ResponseEntity<UserResponse>(new UserResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        try {
            Boolean isObjectSave = recommendDetailService.save(request);
            return new ResponseEntity<UserResponse>(new UserResponse("Object Saved", HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(CommonUtils.SOMETHING_WENT_WRONG, e);
            return new ResponseEntity<UserResponse>(new UserResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
        }
    }
}
