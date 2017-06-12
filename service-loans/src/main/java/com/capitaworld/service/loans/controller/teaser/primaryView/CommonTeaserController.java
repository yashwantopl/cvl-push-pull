package com.capitaworld.service.loans.controller.teaser.primaryView;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.teaser.primaryview.CommonTeaserViewService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController

public class CommonTeaserController {
	private static final Logger logger = LoggerFactory.getLogger(CommonTeaserController.class);	
	
	@Autowired
	private CommonTeaserViewService  commonTeaserViewService;
	@GetMapping(value = "/primaryViewByApplicationId/{applicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> PrimaryViewByApplicationId(@PathVariable(value = "applicationId") Long applicationId,HttpServletRequest httpServletRequest) {
		 LoansResponse loansResponse = new LoansResponse();
	        //get user id from http servlet request
	      

	        if(CommonUtils.isObjectNullOrEmpty(applicationId)){
				logger.warn("Invalid data or Requested data not found.", applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
	        }else {
	        
				try {
					commonTeaserViewService.getPrimaryViewDetails(applicationId,loansResponse);
					
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				} catch (Exception e) {
			            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
			            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
	        }
	}
	
	@GetMapping(value = "/finalViewByApplicationId/{applicationId}")
    public @ResponseBody ResponseEntity<LoansResponse> finalViewByApplicationId(@PathVariable(value = "applicationId") Long applicationId,HttpServletRequest httpServletRequest) {
		 LoansResponse loansResponse = new LoansResponse();
	        //get user id from http servlet request
	      

	        if(CommonUtils.isObjectNullOrEmpty(applicationId)){
				logger.warn("Invalid data or Requested data not found.", applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
	        }else {
	        
				try {
					commonTeaserViewService.getFinalViewDetails(applicationId,loansResponse);
					
					return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				} catch (Exception e) {
			            loansResponse.setMessage("Something went wrong..!"+e.getMessage());
			            loansResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			            return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
				}
	        }
	}

}
