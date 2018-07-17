package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.gst.util.CommonUtils;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;

@RestController
@RequestMapping("/corporate_director_income_details")
public class CorporateDirectorIncomeDetailsController {

	private static final Logger logger = LoggerFactory.getLogger(CorporateDirectorIncomeDetailsController.class);

	@Autowired
	private CorporateDirectorIncomeService incomeDetailsService;

	@RequestMapping(value = "/save_income_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveIncomeDetails(@RequestBody List<CorporateDirectorIncomeRequest> request)
			throws ServletException, IOException {
		logger.info("Start save Director income details()");
		try {
			if (request != null) {
				logger.info("Inside Corporate Director Income Details controller===>{}", request.toString());
				Boolean status = incomeDetailsService.saveOrUpdateIncomeDetails(request);
				logger.info("Response from save/update income details===>{}", status);
				if (status) {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Income details saved successfully", HttpStatus.OK.value(), status),
							HttpStatus.OK);
				} 
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Saving income details failed", HttpStatus.BAD_REQUEST.value(), false),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while calling save/update income details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong while calling save/update Income details",
							HttpStatus.INTERNAL_SERVER_ERROR.value(), false),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_income_details/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getIncomeDetails(@PathVariable("applicationId") Long applicationId)
			throws ServletException, IOException {
		logger.info("Start get Director income details()=============>"+applicationId);
		try {
			if (!(CommonUtils.isObjectNullOrEmpty(applicationId))) {
				logger.info("Inside Corporate Director Income Details controller===>{}"+ applicationId);
				List<CorporateDirectorIncomeRequest> response = incomeDetailsService.getDirectorIncomeDetails(applicationId);
				logger.info("Response from getting income details===>{}", response);
				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Income details get successfully", HttpStatus.OK.value(), response),
							HttpStatus.OK);
				} 
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Getting income details failed", HttpStatus.BAD_REQUEST.value(), false),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting income details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong while getting Income details",
							HttpStatus.INTERNAL_SERVER_ERROR.value(), false),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/get_director_details/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDirectorBackgroundDetails(@PathVariable("applicationId") Long applicationId)
			throws ServletException, IOException {
		logger.info("Start get Director background and employee details()=============>"+applicationId);
		try {
			if (!(CommonUtils.isObjectNullOrEmpty(applicationId))) {
				logger.info("Inside Corporate Director Details controller===>{}"+ applicationId);
				List<Map<String,Object>> response = incomeDetailsService.getDirectorBackGroundDetails(applicationId);
				logger.info("Response from getting Director background and employee details===>{}", response);
				if (!CommonUtils.isObjectNullOrEmpty(response)) {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Director background and employee details get successfully", HttpStatus.OK.value(), response),
							HttpStatus.OK);
				} 
			}
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Getting Director background and employee details failed", HttpStatus.BAD_REQUEST.value(), false),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting Director background and employee details");
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong while Director background details",
							HttpStatus.INTERNAL_SERVER_ERROR.value(), false),HttpStatus.OK);
		}
	}

}
