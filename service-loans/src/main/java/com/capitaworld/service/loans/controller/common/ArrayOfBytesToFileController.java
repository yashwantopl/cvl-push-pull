package com.capitaworld.service.loans.controller.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.common.ArrayOfBytesToFileService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
public class ArrayOfBytesToFileController {

	@Autowired
	private ArrayOfBytesToFileService arrayOfBytesToFileService;
	
	private static final Logger logger = LoggerFactory.getLogger(ArrayOfBytesToFileController.class);
	
	@RequestMapping(value = "/convertToByteArray/{applicantId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> convertToByteArrayFile(@PathVariable("applicantId")String applicantId,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId) {
		
		if(CommonUtils.isObjectNullOrEmpty(applicantId)){
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()),
					HttpStatus.OK);
		}
		try {
			byte[] stringToBytes = arrayOfBytesToFileService.createByteFileFromMap(Long.valueOf(applicantId));
			LoansResponse loansResponse = new LoansResponse("Data Found",
					HttpStatus.OK.value());
			loansResponse.setContentInBytes(stringToBytes);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);	
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error while converting string into bytes", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
		
		
		
	}
}
