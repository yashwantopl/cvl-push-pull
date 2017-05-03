package com.capitaworld.service.loans.controller.fundseeker.retail;

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

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.retail.CoApplicantRequest;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.service.fundseeker.retail.CoApplicantService;

@RestController
@RequestMapping("/co_applicant")
public class CoApplicantController {

	private static final Logger logger = LoggerFactory.getLogger(CoApplicantController.class.getName());
	@Autowired
	private CoApplicantService coApplicantService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	// Primary Portion
	@RequestMapping(value = "${profile}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody CoApplicantRequest applicantRequest) {
		// request must not be null
		try {

			if (applicantRequest == null) {
				logger.warn("CorporateApplicantRequest Object can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			if (applicantRequest.getApplicationId() == null) {
				logger.warn("Application Id can not be empty ==>", applicantRequest);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Appli cation ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);

			}

			boolean response = coApplicantService.save(applicantRequest, applicantRequest.getApplicationId());
			if (response) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "${profile}/get/{id}/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("id") Long id,
			@PathVariable("applicationId") Long applicationId) {
		// request must not be null
		try {
			if (id == null) {
				logger.warn("ID Require to get CoApplicant Profile Details ==>" + id);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			CoApplicantRequest response = coApplicantService.get(id, applicationId);
			if (response != null) {
				LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
				loansResponse.setData(response);
				return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error while getting Co Applicant Profile Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	// Final Portion
		@RequestMapping(value = "${final}/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<LoansResponse> saveFinal(@RequestBody FinalCommonRetailRequest applicantRequest) {
			// request must not be null
			try {

				if (applicantRequest == null) {
					logger.warn("FinalCommonRetailRequest Object can not be empty ==>", applicantRequest);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Requested data can not be empty.", HttpStatus.BAD_REQUEST.value()),
							HttpStatus.OK);
				}

				if (applicantRequest.getApplicationId() == null) {
					logger.warn("Application Id can not be empty ==>", applicantRequest);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Appli cation ID can not be empty.", HttpStatus.BAD_REQUEST.value()),
							HttpStatus.OK);

				}

				boolean response = coApplicantService.saveFinal(applicantRequest);
				if (response) {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Successfully Saved.", HttpStatus.OK.value()), HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.INTERNAL_SERVER_ERROR);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}

		}

		@RequestMapping(value = "${final}/get/{id}/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<LoansResponse> getFinal(@PathVariable("id") Long id,
				@PathVariable("applicationId") Long applicationId) {
			// request must not be null
			try {
				if (id == null) {
					logger.warn("ID Require to get CoApplicant Profile Details ==>" + id);
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Something went wrong!", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
				}

				CoApplicantRequest response = coApplicantService.get(id, applicationId);
				if (response != null) {
					LoansResponse loansResponse = new LoansResponse("Data Found.", HttpStatus.OK.value());
					loansResponse.setData(response);
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				} else {
					return new ResponseEntity<LoansResponse>(
							new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
							HttpStatus.OK);
				}
			} catch (Exception e) {
				logger.error("Error while getting Co Applicant Profile Details==>", e);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR.value()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}

}
