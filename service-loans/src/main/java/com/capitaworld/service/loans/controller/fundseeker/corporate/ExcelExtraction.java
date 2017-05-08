package com.capitaworld.service.loans.controller.fundseeker.corporate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.model.ExcelRequest;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.AssetsDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.BalanceSheetDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.EntityInformationDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LiabilitiesDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ManagementDetailService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ProfitibilityStatementDetailService;
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
	
	@Autowired
	private EntityInformationDetailService entityInformationDetailService; 
	
	@Autowired
	private ManagementDetailService managementDetailService; 
	
	@Autowired
	BalanceSheetDetailService balanceSheetDetailService;
	
	@Autowired
	ProfitibilityStatementDetailService profitibilityStatementDetailService;
	

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		
		return "Welcome to Loan Application Service...";
	}

	@RequestMapping(value = "/read_cma", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> readCMA(@RequestBody ExcelRequest excelRequest) {
	
		String filePath=excelRequest.getFilePath();
		Long applicationId=excelRequest.getApplicationId();
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(filePath) && CommonUtils.isObjectNullOrEmpty(applicationId) && CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.BAD_REQUEST.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		if(!(excelExtractionService.readCMA(applicationId,storageDetailsId,filePath)))
		{
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		LoansResponse res= new LoansResponse("CMA successfully read", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/inactive_cma", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActiveCMA(@RequestBody ExcelRequest excelRequest) {
	
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.BAD_REQUEST.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		try {
			assetsDetailsService.inActiveAssetsDetails(storageDetailsId);
			liabilitiesDetailsService.inActiveAssetsDetails(storageDetailsId);
			operatingStatementDetailsService.inActiveAssetsDetails(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		LoansResponse res= new LoansResponse("CMA Detailes inActivated", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/read_dpr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> extractDPR(@RequestBody ExcelRequest excelRequest) {
	
		String filePath=excelRequest.getFilePath();
		Long applicationId=excelRequest.getApplicationId();
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(filePath) || CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.OK.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		if(!(excelExtractionService.readDPR(applicationId,storageDetailsId,filePath)))
		{
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.OK.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		LoansResponse res= new LoansResponse("DPR successfully read", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/inactive_dpr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActiveDPR(@RequestBody ExcelRequest excelRequest) {
	
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.OK.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		try {
			entityInformationDetailService.inActiveEntityInformationDetails(storageDetailsId);
			managementDetailService.inActiveManagementDetails(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.OK.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		LoansResponse res= new LoansResponse("DPR Detailes inActivated", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/read_bs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> readBS(@RequestBody ExcelRequest excelRequest) {
	
		String filePath=excelRequest.getFilePath();
		Long applicationId=excelRequest.getApplicationId();
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(filePath) && CommonUtils.isObjectNullOrEmpty(applicationId) && CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.BAD_REQUEST.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		if(!(excelExtractionService.readBS(applicationId,storageDetailsId,filePath)))
		{
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		LoansResponse res= new LoansResponse("BS successfully read", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	
	
	@RequestMapping(value = "/inactive_bs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> inActiveBS(@RequestBody ExcelRequest excelRequest) {
	
		Long storageDetailsId=excelRequest.getStorageDetailsId();
		
		
		if(CommonUtils.isObjectNullOrEmpty(storageDetailsId))
		{
			LoansResponse res= new LoansResponse("request parameter is null or empty", HttpStatus.BAD_REQUEST.value());
			log.error("request parameter is null or empty");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		try {
			balanceSheetDetailService.inActiveBalanceSheetDetail(storageDetailsId);
			profitibilityStatementDetailService.inActiveProfitibilityStatementDetail(storageDetailsId);
		} catch (Exception e) {
			// TODO: handle exception
			
			LoansResponse res= new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value());
			log.error("Something went wrong");
			return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		}
		
		
		LoansResponse res= new LoansResponse("BS Detailes inActivated", HttpStatus.OK.value());
		return new ResponseEntity<LoansResponse>(res,HttpStatus.OK);
		
	}
	

}
