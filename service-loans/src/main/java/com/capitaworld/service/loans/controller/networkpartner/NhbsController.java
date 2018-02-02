package com.capitaworld.service.loans.controller.networkpartner;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
public class NhbsController {

	private static final Logger logger = LoggerFactory.getLogger(NhbsController.class);
	
	@Autowired
	private NetworkPartnerService networkPartnerService;
	
	@RequestMapping(value = "/get/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProposals(@RequestBody NhbsApplicationRequest applicationRequest,HttpServletRequest request) {
		try {
			
			if(CommonUtils.isObjectNullOrEmpty(applicationRequest) ||
					CommonUtils.isObjectNullOrEmpty(applicationRequest.getUserRoleId()) ||
					CommonUtils.isObjectNullOrEmpty(applicationRequest.getApplicationStatusId())){
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfProposals(applicationRequest);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);			
		} catch (Exception e) {
			logger.error("Error while getting proposals based on application status.", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/get/assigned/proposals", method = RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getAssignedProposals(@RequestBody NhbsApplicationRequest nhbsApplicationRequest,HttpServletRequest request) {
		try {
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			userId = 504l;
			if(CommonUtils.isObjectNullOrEmpty(userId) || 
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getApplicationStatusId()) ||
					CommonUtils.isObjectNullOrEmpty(nhbsApplicationRequest.getUserRoleId())){
				logger.warn("userId  can not be empty ==>" + userId);
				logger.warn("applicationStatusId  can not be empty ==>" + nhbsApplicationRequest.getApplicationStatusId());
				logger.warn("userRoleId  can not be empty ==>" + nhbsApplicationRequest.getUserRoleId());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			nhbsApplicationRequest.setUserId(userId);
			
			List<NhbsApplicationsResponse> applicationsResponseList = networkPartnerService.getListOfAssignedProposals(nhbsApplicationRequest);
			LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
			loansResponse.setListData(applicationsResponseList);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			logger.error("Error while getting assigned proposals ", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
