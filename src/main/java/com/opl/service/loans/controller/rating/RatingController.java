package com.opl.service.loans.controller.rating;


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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.mudra.api.rating.exception.RatingException;
import com.opl.mudra.api.rating.model.FinancialInputRequest;
import com.opl.mudra.api.rating.model.RatingResponse;
import com.opl.service.loans.service.irr.IrrService;
import com.opl.service.loans.utils.CommonDocumentUtils;

@RestController
@RequestMapping("/rating")
public class RatingController {
	
private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	@Autowired
	private IrrService irrService;
	
	@RequestMapping(value = "/calculate_irr_Rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingResponse> calculateIrrRating(@RequestBody ProposalMappingRequest proposalMappingRequest,HttpServletRequest httpRequest, HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		
		Long userId = null;
		if(CommonDocumentUtils.isThisClientApplication(httpRequest)){
		   userId = clientId;
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}

		return irrService.calculateIrrRating(proposalMappingRequest.getApplicationId(), userId, proposalMappingRequest.getId());
	}
	
	@RequestMapping(value = "/cma_irr_mapping_financial_input", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingResponse> cmaIrrMappingService(@RequestBody ProposalMappingRequest proposalMappingRequest,HttpServletRequest httpRequest, HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		
		Long userId = null;
		if(CommonDocumentUtils.isThisClientApplication(httpRequest)){
		   userId = clientId;
		} else {
			userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		}
		
		if(CommonUtils.isObjectNullOrEmpty(proposalMappingRequest.getApplicationId()))
		{
			logger.error("application id is null or empty");
			return new ResponseEntity<RatingResponse>(
					new RatingResponse("application id is null or empty", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {

			Long denomination=1l;
			logger.info("denomination:::"+denomination);
			FinancialInputRequest financialInputRequest=irrService.cmaIrrMappingService(userId, proposalMappingRequest.getApplicationId(), null, denomination,proposalMappingRequest.getId(),proposalMappingRequest.getItrId());
			return new ResponseEntity<RatingResponse>(new RatingResponse(financialInputRequest,"financial input fetched from cma", HttpStatus.OK.value()), HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error("error while getting financial input from cma : ",e);
			return new ResponseEntity<RatingResponse>(
					new RatingResponse("error while getting financial input from cma", HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.OK);
			
		}
	}

	@RequestMapping(value = "/getCompanyInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingResponse> getCompanyInfo(@RequestBody String companyName) throws RatingException {
		logger.info("Request String----"+companyName);
		RatingResponse ratingResponse =  new RatingResponse();
		ratingResponse.setData(irrService.getCompanyDetails(companyName));
		return new ResponseEntity<>(ratingResponse, HttpStatus.OK);
	}

	@RequestMapping(value = "/getAllCompanyDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RatingResponse> getAllCompanyDetail() throws RatingException {
		RatingResponse ratingResponse =  new RatingResponse();
		ratingResponse.setData(irrService.getAllCompanyDetail());
		return new ResponseEntity<>(ratingResponse, HttpStatus.OK);
	}
}