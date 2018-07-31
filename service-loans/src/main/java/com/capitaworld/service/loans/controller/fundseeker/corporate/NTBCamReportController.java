package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.NtbCamReportService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/ntbCam")
public class NTBCamReportController {

	@Autowired
	private NtbCamReportService ntbCamReportService;
	
	@Autowired
	private ReportsClient reportsClient;
	
	@Autowired
	private DMSClient dmsClient;
	
	private static final Logger logger = LoggerFactory.getLogger(NTBCamReportController.class);
	
	@RequestMapping(value = "/getPrimaryNtbCamData/{applicationId}/{productMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryNtbCamData(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn("Invalid data or Requested data not found.", applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		Long userId = null;
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
		}
		try {
			Map<String,Object> response = ntbCamReportService.getNtbCamReport(applicationId,productId,userId,false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("NTBCAMPRIMARY");
			reportRequest.setType("NTBCAMPRIMARY");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();
			  
				jsonObj.put("applicationId", applicationId);
				jsonObj.put("productDocumentMappingId", 355L);
				jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put("originalFileName", "NTBCAMPRIMARY"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
					logger.info("DocumentResponse"+documentResponse);
					return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), response),HttpStatus.OK);
				}
				else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error("Error while getting MAP Details==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/getFinalNtbCamData/{applicationId}/{productMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinalNtbCamData(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn("Invalid data or Requested data not found.", applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		Long userId = null;
		if(!CommonUtils.isObjectNullOrEmpty(request.getAttribute(CommonUtils.USER_ID))) {
			userId = ((Long) request.getAttribute(CommonUtils.USER_ID));		
		}
		try {
			Map<String,Object> response = ntbCamReportService.getNtbCamReport(applicationId,productId,userId,true);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("NTBCAMFINAL");
			reportRequest.setType("NTBCAMFINAL");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

				jsonObj.put("applicationId", applicationId);
				jsonObj.put("productDocumentMappingId", 362L);
				jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put("originalFileName", "NTBCAMFINAL"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
					logger.info("DocumentResponse"+documentResponse);
					return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), response),HttpStatus.OK);
				}
				else{
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error("Error while getting MAP Details==>", e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
