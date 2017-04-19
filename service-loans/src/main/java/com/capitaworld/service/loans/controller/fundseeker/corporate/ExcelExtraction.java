package com.capitaworld.service.loans.controller.fundseeker.corporate;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/excel_extraction")
public class ExcelExtraction {

	@Autowired
	ExcelExtractionService excelExtractionService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		return "Welcome to Loan Application Service...";
	}

	@RequestMapping(value = "/cma", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> extractCMA(@RequestBody JSONObject jsonObject) {
	
		String filePath=(String) jsonObject.get("filePath");
		
		if(CommonUtils.isObjectNullOrEmpty(filePath))
		{
			LoansResponse res= new LoansResponse("Filepath not exists", HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		if(!(excelExtractionService.readCMA(filePath)))
		{
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.OK.value());
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		LoansResponse res= new LoansResponse("File successfully read", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}

}
