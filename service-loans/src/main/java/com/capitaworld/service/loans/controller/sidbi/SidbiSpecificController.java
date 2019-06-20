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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.sidbi.SidbiBasicDetailRequest;
import com.capitaworld.service.loans.model.sidbi.PersonalCorporateGuaranteeRequest;
import com.capitaworld.service.loans.model.sidbi.PrimaryCollateralSecurityRequest;
import com.capitaworld.service.loans.service.sidbi.PersonalCorporateGuaranteeService;
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

	@Autowired
	private PersonalCorporateGuaranteeService personalCorporateGuaranteeService;

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
	
	@RequestMapping(value = "/save/additionalData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveAdditionalData(@RequestBody SidbiBasicDetailRequest corporateAdditionalRequest,
                                                     HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId)
            throws LoansException {
        try {
            CommonDocumentUtils.startHook(logger, "saveAdditionalData");
            // request must not be null
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
            if (CommonDocumentUtils.isThisClientApplication(request)) {
            	corporateAdditionalRequest.setClientId(clientId);
            }

            if (userId == null) {
                logger.warn("userId can not be empty ==>" + corporateAdditionalRequest);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            if (corporateAdditionalRequest.getApplicationId() == null) {
                logger.warn("ID must not be empty ==>" + corporateAdditionalRequest.getApplicationId());
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("ID must not be empty.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            sidbiSpecificService.saveOrUpdateAdditionalData(corporateAdditionalRequest, userId);
            CommonDocumentUtils.endHook(logger, "savePrimarySpecificData");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                    HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving savePrimarySpecificData()==>", e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping(value = "/savePersonalCorporateGuarantee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePersonalCorporateGuarantee(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "savePersonalCorporateGuarantee");
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

		if (frameRequest == null) {
			logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			frameRequest.setUserId(userId);
						
			personalCorporateGuaranteeService.saveOrUpdate(frameRequest);
			CommonDocumentUtils.endHook(logger, "savePersonalCorporateGuarantee");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Personal Corporate Guarantee Details==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}
	
	@PostMapping(value = "/getPersonalCorporateGuaranteeList", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPersonalCorporateGuaranteeList(@RequestBody FrameRequest frameRequest, HttpServletRequest request) {
		// request must not be null
		CommonDocumentUtils.startHook(logger, "getPersonalCorporateGuaranteeList");

		if (frameRequest == null) {
			logger.warn("frameRequest must not be empty ==>{}" , frameRequest);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			
			if (frameRequest.getApplicationId() == null) {
				logger.warn("ID Require to get Personal Corporate Guarantee List ==>{}" , frameRequest.getApplicationId());
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			List<PersonalCorporateGuaranteeRequest> response = personalCorporateGuaranteeService.getPersonalCorporateGuaranteeListAppId(frameRequest.getApplicationId());
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(response);
			CommonDocumentUtils.endHook(logger, "getPersonalCorporateGuaranteeList");
			return new ResponseEntity<>(loansResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("Error while get Personal Corporate Guarantee List Details==>", e);
			return new ResponseEntity<>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}

	}

	
}
