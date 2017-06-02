package com.capitaworld.service.loans.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;


@RestController
@RequestMapping("/proposal")
public class ProposalController {
	
	@Autowired
	ProposalService proposalService;
	
	@RequestMapping(value = "/fundproviderProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> fundproviderProposal(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId) {
		
		// request must not be null
		Long userId = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userId = clientId;
		} else {
			userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
		}
		List proposalDetailsList=proposalService.fundproviderProposal(request);
		return new ResponseEntity<List>(proposalDetailsList,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/fundseekerProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FundProviderProposalDetails>> fundseekerProposal(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId) {
		
		// request must not be null
		Long userId = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userId = clientId;
		} else {
			userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
		}
		List<FundProviderProposalDetails> proposalDetailsList=proposalService.fundseekerProposal(request, userId);
		return new ResponseEntity<List<FundProviderProposalDetails>>(proposalDetailsList,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/count/fundprovider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalCountResponse> fundProviderProposalCount(@RequestBody ProposalMappingRequest request) {
		return new ResponseEntity<ProposalCountResponse>(proposalService.fundProviderProposalCount(request),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/count/fundseeker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalCountResponse> fundSeekerProposalCount(@RequestBody ProposalMappingRequest request) {
		return new ResponseEntity<ProposalCountResponse>(proposalService.fundSeekerProposalCount(request),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalMappingResponse> get(@RequestBody ProposalMappingRequest request,HttpServletRequest httpServletRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) {
		
		Long userId = null;
		Long userType = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userId = clientId;
			userType = clientUserType;
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);
		}
		request.setUserType(userType);
		request.setUserId(userId);
		ProposalMappingResponse response = proposalService.get(request);
		response.setUserType(userType.longValue());
		return new ResponseEntity<ProposalMappingResponse>(response,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalMappingResponse> changeStatus(@RequestBody ProposalMappingRequest request,HttpServletRequest httpServletRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) {
		Long userId = null;
		Long userType = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userId = clientId;
			userType = clientUserType;
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);
		}
		request.setLastActionPerformedBy(userType);
		request.setUserId(userId);
		return new ResponseEntity<ProposalMappingResponse>(proposalService.changeStatus(request),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/sendRequest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalMappingResponse> sendRequest(@RequestBody ProposalMappingRequest request,HttpServletRequest httpServletRequest,@RequestParam(value = "clientId", required = false) Long clientId,@RequestParam(value = "clientUserType", required = false) Long clientUserType) {
		Long userId = null;
		Long userType = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userId = clientId;
			userType = clientUserType;
		} else {
			userId = (Long) httpServletRequest.getAttribute(CommonUtils.USER_ID);
			userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);
		}
		request.setUserId(userId);
		request.setUserType(userType.longValue());
		return new ResponseEntity<ProposalMappingResponse>(proposalService.sendRequest(request),HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/listfundseekerproposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalMappingResponse> listOfFundSeekerProposal(@RequestBody ProposalMappingRequest request) {
		return new ResponseEntity<ProposalMappingResponse>(proposalService.listOfFundSeekerProposal(request),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/connections", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProposalMappingResponse> connections(@RequestBody ProposalMappingRequest request,HttpServletRequest httpServletRequest,@RequestParam(value = "clientUserType", required = false) Long clientUserType) {
		
		Long userType = null;
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpServletRequest.getAttribute(CommonUtils.USER_TYPE))
				.intValue()) {
			userType = clientUserType;
		} else {
			userType = (Long) httpServletRequest.getAttribute(CommonUtils.USER_TYPE);
		}
		request.setUserType(userType);
		return new ResponseEntity<ProposalMappingResponse>(proposalService.getConectionList(request),HttpStatus.OK);
	}
}
