package com.capitaworld.service.loans.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.capitaworld.service.loans.controller.fundseeker.LoanApplicationController;
import com.capitaworld.service.loans.domain.common.LogDetails;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.common.LogDetailsModel;
import com.capitaworld.service.loans.model.corporate.CorporateProduct;
import com.capitaworld.service.loans.service.common.LogService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@Controller
public class LogController {
	
	@Autowired
	private LogService logService;

	private static final Logger logger = LoggerFactory.getLogger(LogController.class);

	@RequestMapping(value = "/createFsLog/{applicationId}/{logType}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> createFsLog(@PathVariable("applicationId") Long applicationId,@PathVariable("logType") Integer logType,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "createFsLog");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.error("Application id must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			if (CommonUtils.isObjectNullOrEmpty(logType)) {
				logger.error("logType  must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(logService.saveFsLog(applicationId, logType));
			CommonDocumentUtils.endHook(logger, "createFsLog");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while createLog==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/createLog", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> createLog(
			@RequestBody LogDetailsModel logDetailsModel,HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "createLog");
			
			if (CommonUtils.isObjectNullOrEmpty(logDetailsModel)) {
				logger.error("logDetails must not be null.");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
			
			LoansResponse loansResponse = new LoansResponse("Success Result", HttpStatus.OK.value());
			loansResponse.setData(logService.save(logDetailsModel));
			CommonDocumentUtils.endHook(logger, "createLog");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while createLog==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
