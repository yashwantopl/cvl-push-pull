package com.capitaworld.service.loans.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.api.payment.gateway.model.LoansResponse;
import com.capitaworld.service.loans.model.GstRelatedPartyRequest;
import com.capitaworld.service.loans.service.common.RelatedPartyService;

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
	
}
