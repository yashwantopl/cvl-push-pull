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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

}
