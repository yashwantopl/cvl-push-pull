package com.capitaworld.service.loans.controller.sidbi;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sidbi.PrimaryCollateralSecurityRequest;
import com.capitaworld.service.loans.service.sidbi.PrimaryCollateralSecurityService;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/sidbi")
public class SidbiSpecificController {
	
	private static final Logger logger = LoggerFactory.getLogger(SidbiSpecificController.class.getName());
	
	@Autowired
	SidbiSpecificService sidbiSpecificService;

	@Autowired
	private PrimaryCollateralSecurityService primaryCollateralSecurityService;

	@PostMapping(value = "/savePrimaryCollateralSecurity", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePrimaryCollateralSecurity(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "savePrimaryCollateralSecurity");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			frameRequest.setUserId(userId);
						
			primaryCollateralSecurityService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "savePrimaryCollateralSecurity");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Primary Collateral Security Details==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}
	
	@PostMapping(value = "/getPrimaryCollateralSecurityList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryCollateralSecurityList(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getPrimaryCollateralSecurityList");

		if (frameRequest == null) {
			logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			
			if (frameRequest.getApplicationId() == null) {
				logger.warn("ID Require to get Primary Collateral Security List ==>{}" , frameRequest.getApplicationId());
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<PrimaryCollateralSecurityRequest> response = primaryCollateralSecurityService.getPrimaryCollateralSecurityListAppId(frameRequest.getApplicationId());
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getPrimaryCollateralSecurityList");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error while get Primary Collateral Security Details==>", e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}
	
}
