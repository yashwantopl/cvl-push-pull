package com.capitaworld.service.loans.controller.fundseeker;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
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
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/ddr")
public class DDRFormController {

	private static final Logger logger = LoggerFactory.getLogger(DDRFormController.class);
	
	@Autowired
	private DDRFormService ddrFormService;
	
	@Autowired
	private ReportsClient reportsClient; 

	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private DMSClient dmsClient;
	
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
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));
		if (CommonUtils.UserType.NETWORK_PARTNER == userType || CommonUtils.UserType.SERVICE_PROVIDER == userType) {
			userId = clientId;
		}
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
	@RequestMapping(value = "/get/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("appId") Long appId,HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR Form Get Method -------------------------->" + appId);
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));
		if (CommonUtils.UserType.NETWORK_PARTNER == userType || CommonUtils.UserType.SERVICE_PROVIDER == userType) {
			userId = clientId;
		}
		
		try {
			DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(appId,userId);
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
	
	@RequestMapping(value = "/getAutoFilledDetails/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancial(@PathVariable("appId") Long appId,HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR AutoFilled Form Get Method -------------------------->" + appId);
		
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));
		if (CommonUtils.UserType.NETWORK_PARTNER == userType || CommonUtils.UserType.SERVICE_PROVIDER == userType) {
			userId = clientId;
		}
		
		if(CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Invalid Request, UserId is null or Empty");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			DDROneFormResponse oneFormDetails = ddrFormService.getOneFormDetails(userId, appId);
			logger.info("DDR AutoFilled Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully get data", HttpStatus.OK.value(),oneFormDetails), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while getting DDR AutoFilled Form Details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/getFinancialToBeFilledMaster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancialToBeFilled() {
		try {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully get data", HttpStatus.OK.value(),ddrFormService.getFinancialSummaryToBeFieldsList()), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while getting DDR Financial To Be Filled Details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getFinancialAutoFilledMaster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancialAutoFilled() {
		try {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse("Successfully get data", HttpStatus.OK.value(),ddrFormService.getFinancialSummaryFieldsList()), HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while getting DDR Financial Auto Filled Details ==>", e);
			e.printStackTrace();
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
		}
	}
	
	
	@RequestMapping(value = "/generateDDRPDF/{appId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> generateDDRPDF(@PathVariable(value = "appId") Long appId,HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "clientId", required = false) Long clientId){
		logger.info("In generateDDRPDF");
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));
		if(CommonUtils.UserType.FUND_PROVIDER == userType){
			userId = loanApplicationService.getUserIdByApplicationId(appId);
		}
		else if (CommonUtils.UserType.NETWORK_PARTNER == userType || CommonUtils.UserType.SERVICE_PROVIDER == userType) {
			userId = clientId;
		}
		
		try {
			DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(appId,userId);
			DDROneFormResponse oneFormDetails = ddrFormService.getOneFormDetails(userId, appId);
			DDRFormDetailsRequest.printFields(dDRFormDetailsRequest);
			DDROneFormResponse.printFields(oneFormDetails);
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("autoFilled",oneFormDetails);
			obj.put("toBeFilled", dDRFormDetailsRequest);
			System.out.println(obj);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(obj);
			reportRequest.setTemplate("NHBSDDR");
			reportRequest.setType("NHBSDDR");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			
//			File file = new File("Nhbs"+".pdf");
//			System.out.println(file.getAbsolutePath());
//			FileOutputStream fos = new FileOutputStream(file);
//			fos.write(byteArr);
//			fos.flush();
//			fos.close();
//			
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

					jsonObj.put("applicationId", appId);
				jsonObj.put("productDocumentMappingId", 329L);
				jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put("originalFileName", "NHBS_"+appId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				System.out.println(documentResponse.getData());
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Successfull", HttpStatus.OK.value(),documentResponse.getData()), HttpStatus.OK);
				}
				else{
					 return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
//			response.getOutputStream().write(byteArr);
//			response.setContentType("application/pdf");     
//			response.setHeader("Content-Disposition", "attachment; filename=\""+"test.pdf"+"\"");
        } catch (Exception e) {
        	logger.info("thrown exception from generateDDRPDF");
            e.printStackTrace();
            return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
	}
	
}
