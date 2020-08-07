package com.opl.service.loans.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opl.mudra.api.loans.model.GstRelatedPartyRequest;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.service.loans.service.common.RelatedPartyService;

/**
  *@auther : Maaz Shaikh
  */

@RestController
@RequestMapping("relatedParty")
public class RelatedPartyController {

	@Autowired
	private RelatedPartyService service;
	
	@PostMapping(value="save",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveRelatedParty(@RequestBody GstRelatedPartyRequest[] relatedPartyRequest) {
		LoansResponse response=new LoansResponse();
		/*if(relatedPartyRequest != null && relatedPartyRequest.length > 0) {
			response.setData(false);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
		}*/
		
		try {
			response.setData(service.saveRelatedParty(relatedPartyRequest));
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);	
		}catch (Exception e) {
			response.setData(false);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
		}
	}
	
	@PostMapping(value="saveRelatedPartyFlag",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveRelatedPartyFlag(@RequestBody GstRelatedPartyRequest relatedPartyRequest) {
		LoansResponse response=new LoansResponse();
		try {
			response.setData(service.saveRelatedPartyFlag(relatedPartyRequest));
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);	
		}catch (Exception e) {
			response.setData(false);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
		}
	}
	
	@PostMapping(value="checkGstType",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkGstType(@RequestBody GstRelatedPartyRequest relatedPartyRequest) {
		LoansResponse response=new LoansResponse();
		try {
			response.setData(service.checkGstType(relatedPartyRequest));
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);	
		}catch (Exception e) {
			response.setData(null);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
		}
	}
	
	@PostMapping(value="updateRelatedParyFlagOnConnect",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveRelatedPartyFlagOnConnect(@RequestBody GstRelatedPartyRequest relatedPartyRequest) {
		LoansResponse response=new LoansResponse();
		try {
			response.setData(service.updateRelatedPartyFilledFlageOnConnect(relatedPartyRequest.getApplicationId()));
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);	
		}catch (Exception e) {
			response.setData(null);
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
		}
	}
	
	
}
