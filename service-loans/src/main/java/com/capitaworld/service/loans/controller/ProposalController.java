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
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.service.ProposalService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.model.ProposalCountResponse;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;


@RestController
@RequestMapping("/proposal")
public class ProposalController {
	
	@Autowired
	ProposalService proposalService;
	
	@RequestMapping(value = "/fundproviderProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List> fundproviderProposal(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest) {
		
		// request must not be null
		Long userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
		List proposalDetailsList=proposalService.fundproviderProposal(request);
		return new ResponseEntity<List>(proposalDetailsList,HttpStatus.OK);
		
	}
	
	
	
	@RequestMapping(value = "/fundseekerProposal", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FundProviderProposalDetails>> fundseekerProposal(@RequestBody ProposalMappingRequest request,HttpServletRequest httpRequest) {
		
		// request must not be null
		Long userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
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
}
