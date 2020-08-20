package com.opl.service.loans.controller.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.common.CommonResponse;
import com.opl.mudra.api.common.CommonUtils;
import com.opl.service.loans.service.fundseeker.corporate.CorporateService;

@RestController
public class CorporateController {
	
	private static final Logger logger = LoggerFactory.getLogger(CorporateController.class);
	
	@Autowired
	private CorporateService corporateService;
	
	@RequestMapping(value = "/copyDataWithProposalId/{applicationId}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> get(@PathVariable("applicationId") Long applicationId, @PathVariable("proposalId") Long proposalId) {
		try {
			logger.info("Enter in Copy Data With Proposal Id ======>" + proposalId + " With Application Id =====" + applicationId);
			return new ResponseEntity<CommonResponse>(corporateService.copyMsmeDataWithProposalId(applicationId, proposalId), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Copy Data With Proposal Id ==>", e);
			return new ResponseEntity<CommonResponse>(
					new CommonResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getTabData/{profileId}/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommonResponse> getTabData(@PathVariable("profileId") Long profileId, @PathVariable("applicationId") Long applicationId,HttpServletRequest httpRequest) {
		try {
			Long userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			if(profileId == null || applicationId == null || userId == null) {
				return new ResponseEntity<CommonResponse>(new CommonResponse(CommonUtils.INVALID_REQUEST_MSG, HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
			}
			logger.info("Enter in get tab data by profile id  ======>" + profileId + " With Application Id =====" + applicationId);
			List<Map<String,Object>> loanTabsData = corporateService.getLoanTabsData(profileId, applicationId,userId);
			return new ResponseEntity<CommonResponse>(
					new CommonResponse(CommonUtils.SUCCESSFULLY_GET_DATA, loanTabsData, HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while Copy Data With Proposal Id ==>", e);
			return new ResponseEntity<CommonResponse>(
					new CommonResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @RequestMapping(value = "/getTabData/{profileId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse> getTabData(@PathVariable("profileId") Long profileId,HttpServletRequest httpRequest) {
        try {
        	Long userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			if(profileId == null || userId == null) {
				return new ResponseEntity<CommonResponse>(new CommonResponse(CommonUtils.INVALID_REQUEST_MSG, HttpStatus.BAD_REQUEST.value()),HttpStatus.BAD_REQUEST);
			}
            logger.info("Enter in get tab data by profile id  ======>" + profileId + " With profileId Id =====" + profileId);
            List<Map<String,Object>> loanTabsData = corporateService.getLoanTabsData(profileId, null,userId);
            return new ResponseEntity<CommonResponse>(
                    new CommonResponse(CommonUtils.SUCCESSFULLY_GET_DATA, loanTabsData, HttpStatus.OK.value()), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while get Tab Data With profileId==>", e);
            return new ResponseEntity<CommonResponse>(
                    new CommonResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	

}
