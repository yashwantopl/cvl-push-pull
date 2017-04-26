package com.capitaworld.service.loans.controller.fundseeker.corporate;

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

import com.capitaworld.service.loans.model.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;

@RestController
@RequestMapping("/fs_profile")
public class CorporateApplicantController {

	private static final Logger logger = LoggerFactory.getLogger(CorporateApplicantController.class.getName());
	@Autowired
	private CorporateApplicantService applicantService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody CorporateApplicantRequest applicantRequest) {
		// request must not be null
		if (applicantRequest == null) {
			logger.warn("CorporateApplicantRequest Object can not be empty ==>", applicantRequest);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}

		boolean response = applicantService.save(applicantRequest);
		if (response) {
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
