package com.capitaworld.service.loans.controller.rating;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.RatingResponse;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	private IrrService irrService;
	
	@RequestMapping(value = "/calculate_irr_Rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingResponse> calculateIrrRating(@RequestBody ProposalMappingRequest proposalMappingRequest,HttpServletRequest httpRequest, HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		
		Long userId = null;
		Integer userType = ((Integer)request.getAttribute(CommonUtils.USER_TYPE)).intValue();
		if(CommonUtils.UserType.SERVICE_PROVIDER == userType || CommonUtils.UserType.NETWORK_PARTNER == userType){
		   userId = clientId;
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}
		
		//return irrService.calculateIrrRating(proposalMappingRequest.getApplicationId(), userId);
		return null;
	}
}
