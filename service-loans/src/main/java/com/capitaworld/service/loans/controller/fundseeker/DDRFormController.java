package com.capitaworld.service.loans.controller.fundseeker;


import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/ddr")
public class DDRFormController {

	private static final Logger logger = LoggerFactory.getLogger(DDRFormController.class);
	
	@Autowired
	private DDRFormService ddrFormService;
	
	/**
	 * SAVE ALL DDR FIELDS EXCEPT FRAME
	 * @param ddrFormDetailsRequest
	 * @param request
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody DDRFormDetailsRequest ddrFormDetailsRequest, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR Form Save Method -------------------------->");
		if(CommonUtils.isObjectNullOrEmpty(ddrFormDetailsRequest.getApplicationId())) {
			logger.info("Invalid Request, Application Id is null or Empty");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		Long userId = 1954L;
		/*Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) request.getAttribute(CommonUtils.USER_TYPE))) {
			userId = clientId;
		}*/
		if(CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Invalid Request, UserId is null or Empty");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		
		try {
			ddrFormDetailsRequest.setUserId(userId);
			ddrFormService.saveDDRForm(ddrFormDetailsRequest);
			logger.info("DDR Form Saved Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully Data Saved", HttpStatus.OK.value()), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while saving DDR Form Details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	/**
	 * GET DDR FORM DETAILS BY DDR FORM ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("id") Long id) {
		logger.info("Enter in DDR Form Get Method -------------------------->");
		try {
			DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(id);
			logger.info("DDR Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully get data", HttpStatus.OK.value(),dDRFormDetailsRequest), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while getting DDR Form Details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
}
