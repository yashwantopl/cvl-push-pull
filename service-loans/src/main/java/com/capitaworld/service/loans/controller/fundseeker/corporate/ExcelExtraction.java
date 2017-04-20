package com.capitaworld.service.loans.controller.fundseeker.corporate;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AssetsDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/cw_excel")
public class ExcelExtraction {

	private final Logger log = LoggerFactory.getLogger(ExcelExtraction.class);
	
	@Autowired
	ExcelExtractionService excelExtractionService;
	
	@Autowired
	AssetsDetailsService assetsDetailsService;
	
	@Autowired
	LiabilitiesDetailsService liabilitiesDetailsService;
	
	@Autowired
	OperatingStatementDetailsService operatingStatementDetailsService; 
	

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		
		return "Welcome to Loan Application Service...";
	}

	@RequestMapping(value = "/read_cma", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> extractCMA(@RequestBody JSONObject jsonObject) {
	
		String filePath=(String) jsonObject.get("filePath");
		Long applicationId=Long.parseLong((String) jsonObject.get("applicationId"));
		Long storageDetailsId=Long.parseLong((String) jsonObject.get("storageDetailsId"));
		
		
		if(CommonUtils.isObjectNullOrEmpty(filePath) || CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.OK.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		if(!(excelExtractionService.readCMA(applicationId,storageDetailsId,filePath)))
		{
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.OK.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		LoansResponse res= new LoansResponse("File successfully read", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/in_active_cma", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActive(@RequestBody JSONObject jsonObject) {
	
		Long storageDetailsId=Long.parseLong((String) jsonObject.get("storageDetailsId"));
		
		
		if(CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.OK.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		try {
			assetsDetailsService.inActiveAssetsDetails((long) storageDetailsId);
			liabilitiesDetailsService.inActiveAssetsDetails((long) storageDetailsId);
			operatingStatementDetailsService.inActiveAssetsDetails((long) storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.OK.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		LoansResponse res= new LoansResponse("Cma Detailes inActivated", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}

}
