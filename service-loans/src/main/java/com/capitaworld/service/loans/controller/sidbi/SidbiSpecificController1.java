package com.capitaworld.service.loans.controller.sidbi;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.sidbi.FacilityDetailsService;
import com.capitaworld.service.loans.service.sidbi.RawMaterialDetailsService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pooja.patel on 19-06-2019.
 */
@RestController
public class SidbiSpecificController1 {

    private static final Logger logger = LoggerFactory.getLogger(SidbiSpecificController1.class.getName());

    @Autowired
    FacilityDetailsService facilityDetailsService;

    @Autowired
    RawMaterialDetailsService rawMaterialDetailsService;

    @PostMapping(value = "/saveFinalFacilityDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveFinalFacilityDetails(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
        // request must not be null
        CommonDocumentUtils.startHook(logger, "saveFinalFacilityDetails");

        Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

        if (frameRequest == null) {
            logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        try {
            frameRequest.setUserId(userId);

            facilityDetailsService.saveOrUpdate(frameRequest);
            CommonDocumentUtils.endHook(logger, "saveFinalFacilityDetails");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving Facility Details==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }

    }

    @PostMapping(value = "/saveRawMaterialDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveRawMaterialDetails(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
        // request must not be null
        CommonDocumentUtils.startHook(logger, "saveRawMaterialDetails");

        Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

        if (frameRequest == null) {
            logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        }
        try {
            frameRequest.setUserId(userId);

            rawMaterialDetailsService.saveOrUpdate(frameRequest);
            CommonDocumentUtils.endHook(logger, "saveRawMaterialDetails");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving Raw Material Details==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }

    }

}
