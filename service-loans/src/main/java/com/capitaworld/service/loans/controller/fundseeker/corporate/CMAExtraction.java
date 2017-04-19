package com.capitaworld.service.loans.controller.fundseeker.corporate;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cma_extraction")
public class CMAExtraction {
	
	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		return "Welcome to Loan Application Service...";
	}
	
	
	@RequestMapping(value = "/extractCMA", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public String extractCMA(@RequestBody JSONObject jsonObject) {
	
		String filePath=(String) jsonObject.get("filePath");
		System.out.println("File Path :: "+ filePath);
		
		return filePath;
	}

}
