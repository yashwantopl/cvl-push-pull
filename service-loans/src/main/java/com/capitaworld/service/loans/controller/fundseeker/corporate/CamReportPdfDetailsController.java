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
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/cam")
public class CamReportPdfDetailsController {
	
	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService;
	
	
	@Autowired
	private ReportsClient reportsClient;
	
	@Autowired
	private DMSClient dmsClient;
	
	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsController.class);
	
	@RequestMapping(value = "/getFinalDataMap/{applicationId}/{productMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinalDataMap(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn("Invalid data or Requested data not found.", applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getCamReportFinalDetails(applicationId,productId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("CAMREPORTSIDBI");
			reportRequest.setType("CAMREPORTSIDBI");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

				jsonObj.put("applicationId", applicationId);
				jsonObj.put("productDocumentMappingId", 358L);
				jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put("originalFileName", "CAMREPORTSIDBI"+applicationId+".pdf");
				
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

		} catch (Exception e) {
			logger.error("Error while getting MAP Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@RequestMapping(value = "/getPrimaryDataMap/{applicationId}/{productMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryDataMap(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn("Invalid data or Requested data not found.", applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,productId);
			ReportRequest reportRequest = new ReportRequest();
			logger.info("Response",response);
			reportRequest.setParams(response);
			reportRequest.setTemplate("CAMREPORTPRIMARYSIDBI");
			reportRequest.setType("CAMREPORTPRIMARYSIDBI");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();
			  

				jsonObj.put("applicationId", applicationId);
				jsonObj.put("productDocumentMappingId", 355L);
				jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put("originalFileName", "CAMREPORTSIDBIPRIMARY"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				System.out.println(documentResponse);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("success", HttpStatus.OK.value(),documentResponse.getData()), HttpStatus.OK);
				}
				else{
					 return new ResponseEntity<LoansResponse>(
								new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}

		} catch (Exception e) {
			logger.error("Error while getting MAP Details==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
}
