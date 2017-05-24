package com.capitaworld.service.loans.controller.fundseeker.corporate;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ProductDetailsResponse;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchRequest;
import com.capitaworld.service.matchengine.model.MatchResponse;


@RestController
@RequestMapping("/matches")
public class MatchesController {

	private static final Logger logger = LoggerFactory.getLogger(MatchesController.class);

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/${corporate}/fundseeker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> matchFSCorporate(@RequestBody MatchRequest matchRequest,
			HttpServletRequest request) {
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		matchRequest.setUserId(userId);
		
		if (matchRequest == null || matchRequest.getApplicationId() == null) {
			logger.warn("matchRequest must not be empty ==>" + matchRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			logger.info("MATCHES_URL==>" + environment.getRequiredProperty(CommonUtils.MATCHES_URL));
			MatchEngineClient engineClient = new MatchEngineClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
			MatchResponse matchResponse = engineClient.calculateMatchesOfCorporateFundSeeker(matchRequest);
			if (matchResponse != null && matchResponse.getStatus() == 200) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Matches Successfully Saved", HttpStatus.OK.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Matches for Corporate Fundseeker ==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/${retail}/fundseeker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> matchFSRetail(@RequestBody MatchRequest matchRequest,
			HttpServletRequest request) {
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		matchRequest.setUserId(userId);
		
		if (matchRequest == null || matchRequest.getApplicationId() == null) {
			logger.warn("matchRequest must not be empty ==>" + matchRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			MatchEngineClient engineClient = new MatchEngineClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
			MatchResponse matchResponse = engineClient.calculateMatchesOfRetailFundSeeker(matchRequest);
			if (matchResponse != null && matchResponse.getStatus() == 200) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Matches Successfully Saved", HttpStatus.OK.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Matches for Retail Fundseeker ==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/${corporate}/fundprovider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> matchFPCorporate(@RequestBody MatchRequest matchRequest,
			HttpServletRequest request) {
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		matchRequest.setUserId(userId);
		
		if (matchRequest == null || matchRequest.getProductId() == null) {
			logger.warn("matchRequest must not be empty ==>" + matchRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			MatchEngineClient engineClient = new MatchEngineClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
			MatchResponse matchResponse = engineClient.calculateMatchesOfCorporateFundProvider(matchRequest);
			if (matchResponse != null && matchResponse.getStatus() == 200) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Matches Successfully Saved", HttpStatus.OK.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Matches for Corporate Fundseeker ==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/${retail}/fundprovider", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> matchFPRetail(@RequestBody MatchRequest matchRequest,
			HttpServletRequest request) {
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		matchRequest.setUserId(userId);
		
		if (matchRequest == null || matchRequest.getApplicationId() == null) {
			logger.warn("matchRequest must not be empty ==>" + matchRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			MatchEngineClient engineClient = new MatchEngineClient(environment.getRequiredProperty(CommonUtils.MATCHES_URL));
			MatchResponse matchResponse = engineClient.calculateMatchesOfRetailFundProvider(matchRequest);
			if (matchResponse != null && matchResponse.getStatus() == 200) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Matches Successfully Saved", HttpStatus.OK.value()), HttpStatus.OK);
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving Matches for Retail Fundseeker ==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}

	}
}
