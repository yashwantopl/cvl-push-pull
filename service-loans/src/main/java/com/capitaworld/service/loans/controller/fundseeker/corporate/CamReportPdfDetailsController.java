package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.api.reports.ReportRequest;
import com.capitaworld.client.reports.ReportsClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.CamReportPdfDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.InEligibleProposalCamReportService;
import com.capitaworld.service.loans.service.fundseeker.corporate.UniformProductCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.ALCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.HLCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.HLIneligibleCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.PLCamReportService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/cam")
public class CamReportPdfDetailsController {
	
	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService;
	
	@Autowired
	private InEligibleProposalCamReportService inEligibleProposalCamReportService;
	
	@Autowired
	private HLIneligibleCamReportService hlIneligibleCamReportService;

	@Autowired
	private PLCamReportService plCamService;

	@Autowired
	private UniformProductCamReportService uniformProductCamReport;
	
	@Autowired
	private HLCamReportService hlCamReportService;
	
	@Autowired
	private ALCamReportService alCamReportService;

	@Autowired
	private ReportsClient reportsClient;
	
	@Autowired
	private DMSClient dmsClient;
	
	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsController.class);

	private static final String SUCCESS_LITERAL = "success";
	private static final String PRODUCT_DOCUMENT_MAPPING_ID = "productDocumentMappingId";
	private static final String USER_TYPE = "userType";
	private static final String ORIGINAL_FILE_NAME = "originalFileName";
	private static final String ERROR_WHILE_GETTING_MAP_DETAILS = "Error while getting MAP Details==>";
	private static final String INELIGIBLE_CAM_REPORT = "INELIGIBLECAMREPORT";
	private static final String PLINELIGIBLE_CAM_REPORT = "INELIGIBLEPLCAM";
	private static final String HLINELIGIBLE_CAM_REPORT = "INELIGIBLEHLCAM";
	private static final String UNIFORM_CAM_REPORT = "UNIFORMCAMREPORT";

	@GetMapping(value = "/getPrimaryDataMap/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryDataMap(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId,
			@PathVariable(value ="proposalId") Long proposalId,	HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(productId)||CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, + productId + proposalId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,productId,proposalId,false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("CAMREPORTPRIMARYSIDBI");
			reportRequest.setType("CAMREPORTPRIMARYSIDBI");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();
			  

				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 355L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, "CAMREPORTSIDBIPRIMARY"+proposalId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info(""+documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * cam generate for gateway
	 * @return  byte[]
	 * */

	@GetMapping(value = "/getPrimaryDataInByteArray/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryDataInByteArray(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, 
			@PathVariable(value = "proposalId") Long proposalId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)||CommonUtils.isObjectListNull(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);

				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,productId,proposalId,false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("CAMREPORTPRIMARYSIDBI");
			reportRequest.setType("INPRINCIPLECAM");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			
			if(byteArr != null){
				try {
					MultipartFile multipartFile = new DDRMultipart(byteArr);			  
					JSONObject jsonObj = new JSONObject();
					jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
					jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 594L);
					jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
					jsonObj.put(ORIGINAL_FILE_NAME, "INPRINCIPLECAM"+proposalId+".pdf");
					DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
					logger.info("InPrinciple CAM Uploaded for ==>"+applicationId);
				} catch (Exception e) {
					logger.info("Error while Generated InPrinciple-Latter upload to dms for==>"+applicationId);
					logger.info("Error: "+e);
				}
				
				return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESS_LITERAL,HttpStatus.OK.value(), byteArr),HttpStatus.OK);
			}else{
				 logger.info("Byte array not generated at inPrinciple time for ==>"+applicationId);
				 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
	@GetMapping(value = "/getFinalDataMap/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinalDataMap(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId,
			@PathVariable(value ="proposalId") Long proposalId,HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)||CommonUtils.isObjectNullOrEmpty(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId+proposalId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,productId,proposalId,true);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("CAMREPORTFINALSIDBI");
			reportRequest.setType("CAMREPORTFINALSIDBI");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 362L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, "CAMREPORTSIDBI"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse.getData());
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}
				else{
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getBankStatementAnalysis/{applicationId}/{productMappingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getBankStatementAnalysis(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = camReportPdfDetailsService.getBankStatementAnalysisReport(applicationId,productId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("BANKSTATEMENTANALYSIS");
			reportRequest.setType("BANKSTATEMENTANALYSIS");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID,553L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, "BANK_STATEMENT_ANALYSIS"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse.getData());
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}
				else{
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getHLBankStatementAnalysis/{applicationId}/{productMappingId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getHLBankStatementAnalysis(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, HttpServletRequest request)  {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = hlCamReportService.getHLBankStatementAnalysisReport(applicationId, productId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("HLBANKSTATEMENTANALYSIS");
			reportRequest.setType("HLBANKSTATEMENTANALYSIS");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);			  
			  JSONObject jsonObj = new JSONObject();

				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID,553L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, "HL_BANK_STATEMENT_ANALYSIS"+applicationId+".pdf");
				
				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse.getData());
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}
				else{
					return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getInEligiblePrimaryDataMap/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryDataMap(@PathVariable(value = "applicationId") Long applicationId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = inEligibleProposalCamReportService.getInEligibleCamReport(applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(INELIGIBLE_CAM_REPORT);
			reportRequest.setType(INELIGIBLE_CAM_REPORT);
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			  JSONObject jsonObj = new JSONObject();


				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 570L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, INELIGIBLE_CAM_REPORT+applicationId+".pdf");

				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@GetMapping(value = "/getUniformProductCam/{applicationId}/{fpProductId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getUniformProductDataMap(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "fpProductId") Long fpProductId)  {
		logger.info("In Uniform Product Cam Report..ApplicationId====>>{}...and fpProductId is==>>{}" , applicationId, fpProductId);
		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = uniformProductCamReport.getUniformProductCamReport(applicationId,fpProductId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(UNIFORM_CAM_REPORT);
			reportRequest.setType(UNIFORM_CAM_REPORT);
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			  JSONObject jsonObj = new JSONObject();


				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 574L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, UNIFORM_CAM_REPORT+applicationId+".pdf");

				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse);
				logger.info("Out From Uniform Product Cam Report......Cam Genereated......ApplicationId====>>{}",applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	/**
	 * cam generate for gateway
	 * @return  byte[]
	 * */

	@GetMapping(value = "/getHlPrimaryDataInByteArray/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getHlPrimaryDataInByteArray(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, 
			@PathVariable(value = "proposalId") Long proposalId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)||CommonUtils.isObjectListNull(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);

				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = hlCamReportService.getCamReportDetailsByProposalId(applicationId, productId,proposalId, false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("HLCAMPRIMARY");
			reportRequest.setType("HLCAMPRIMARY");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			if(byteArr != null){
				return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESS_LITERAL,HttpStatus.OK.value(), byteArr),HttpStatus.OK);
			}else{
				 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getPrimaryHlCamData/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryHLCamDataByProposalId(@PathVariable(value = "proposalId") Long proposalId, @PathVariable(value = "applicationId") Long applicationId, @PathVariable(value = "productMappingId") Long productId, HttpServletRequest request) {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(productId) || CommonUtils.isObjectNullOrEmpty(proposalId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		try {
			Map<String, Object> response = hlCamReportService.getCamReportDetailsByProposalId(applicationId, productId,proposalId, false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("HLCAMPRIMARY");
			reportRequest.setType("HLCAMPRIMARY");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			JSONObject jsonObj = new JSONObject();

			jsonObj.put("applicationId", applicationId);
			jsonObj.put("productDocumentMappingId", 355L);
			jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
			jsonObj.put("originalFileName", "HLCAMPRIMARYREPORT" + applicationId + ".pdf");

			DocumentResponse documentResponse = dmsClient.uploadFile(jsonObj.toString(), multipartFile);
			if (documentResponse.getStatus() == 200) {
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), response),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/getFinalHlCamData/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinalHLCamDataMap(@PathVariable(value = "proposalId") Long proposalId, @PathVariable(value = "applicationId") Long applicationId, @PathVariable(value = "productMappingId") Long productId, HttpServletRequest request) {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(productId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		try {
			Map<String, Object> response = hlCamReportService.getCamReportDetailsByProposalId(applicationId, productId,proposalId, true);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("HLCAMFINAL");
			reportRequest.setType("HLCAMFINAL");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			JSONObject jsonObj = new JSONObject();

			jsonObj.put("applicationId", applicationId);
			jsonObj.put("productDocumentMappingId", 362L);
			jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
			jsonObj.put("originalFileName", "HLCAMFINALREPORT" + applicationId + ".pdf");

			DocumentResponse documentResponse = dmsClient.uploadFile(jsonObj.toString(), multipartFile);
			if (documentResponse.getStatus() == 200) {
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), response),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value = "/getPLInEligiblePrimaryDataMap/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPLInEligiblePrimaryDataMap(@PathVariable(value = "applicationId") Long applicationId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			Map<String,Object> response = plCamService.getPLInEligibleCamReport(applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(PLINELIGIBLE_CAM_REPORT);
			reportRequest.setType(PLINELIGIBLE_CAM_REPORT);
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			  JSONObject jsonObj = new JSONObject();
			  
				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 570L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, PLINELIGIBLE_CAM_REPORT+applicationId+".pdf");

				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	@GetMapping(value = "/getHLInEligiblePrimaryDataMap/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getHLInEligiblePrimaryDataMap(@PathVariable(value = "applicationId") Long applicationId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			Map<String,Object> response = hlIneligibleCamReportService.getHLInEligibleCamReport(applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(HLINELIGIBLE_CAM_REPORT );
			reportRequest.setType(HLINELIGIBLE_CAM_REPORT );
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			  JSONObject jsonObj = new JSONObject();
			  
				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 570L);
				jsonObj.put(USER_TYPE, CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
				jsonObj.put(ORIGINAL_FILE_NAME, HLINELIGIBLE_CAM_REPORT +applicationId+".pdf");

				DocumentResponse  documentResponse  =  dmsClient.uploadFile(jsonObj.toString(), multipartFile);
				if(documentResponse.getStatus() == 200){
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, documentResponse.getData(), response),HttpStatus.OK);
				}else{
					 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
				}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	/**
	 * This method is used for sending in mail.
	 * */
	@GetMapping(value = "/getPLInEligiblePrimaryDataForMail/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPLInEligiblePrimaryDataCam(@PathVariable(value = "applicationId") Long applicationId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			Map<String,Object> response = plCamService.getPLInEligibleCamReport(applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(PLINELIGIBLE_CAM_REPORT);
			reportRequest.setType(PLINELIGIBLE_CAM_REPORT);
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);

			return new ResponseEntity<>(new LoansResponse(SUCCESS_LITERAL,HttpStatus.OK.value(),byteArr),HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	
	/**
	 * This method is used for sending in mail.
	 * */
	@GetMapping(value = "/getHLInEligiblePrimaryDataForMail/{applicationId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getHLInEligiblePrimaryDataCam(@PathVariable(value = "applicationId") Long applicationId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId);
				return new ResponseEntity<>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			byte[] byteArr = hlIneligibleCamReportService.generateIneligibleCamReportFromMap(applicationId);
			return new ResponseEntity<>(new LoansResponse(SUCCESS_LITERAL,HttpStatus.OK.value(),byteArr),HttpStatus.OK);
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getPrimaryALCamData/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getPrimaryALCamDataByProposalId(@PathVariable(value = "proposalId") Long proposalId, @PathVariable(value = "applicationId") Long applicationId, @PathVariable(value = "productMappingId") Long productId, HttpServletRequest request) {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(productId) || CommonUtils.isObjectNullOrEmpty(proposalId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		try {
			Map<String, Object> response = alCamReportService.getCamReportDetailsByProposalId(applicationId, productId, proposalId, false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("ALCAMPRIMARY");
			reportRequest.setType("ALCAMPRIMARY");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			JSONObject jsonObj = new JSONObject();

			jsonObj.put("applicationId", applicationId);
			jsonObj.put("productDocumentMappingId", 355L);
			jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
			jsonObj.put("originalFileName", "ALCAMPRIMARYREPORT" + applicationId + ".pdf");

			DocumentResponse documentResponse = dmsClient.uploadFile(jsonObj.toString(), multipartFile);
			if (documentResponse.getStatus() == 200) {
				logger.info("DocumentResponse Data==>{}",documentResponse);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), response),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * AL cam generate for gateway
	 * @return  byte[]
	 * */

	@GetMapping(value = "/getALPrimaryDataInByteArray/{applicationId}/{productMappingId}/{proposalId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getALPrimaryDataInByteArray(@PathVariable(value = "applicationId") Long applicationId,@PathVariable(value = "productMappingId") Long productId, 
			@PathVariable(value = "proposalId") Long proposalId)  {

		if (CommonUtils.isObjectNullOrEmpty(applicationId)||CommonUtils.isObjectNullOrEmpty(productId)||CommonUtils.isObjectListNull(proposalId)) {
				logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);

				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {

			Map<String,Object> response = alCamReportService.getCamReportDetailsByProposalId(applicationId, productId, proposalId, false);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("ALCAMPRIMARY");
			reportRequest.setType("ALCAMPRIMARY");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			if(byteArr != null){
				return new ResponseEntity<LoansResponse>(new LoansResponse(SUCCESS_LITERAL,HttpStatus.OK.value(), byteArr),HttpStatus.OK);
			}else{
				 return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping(value = "/getGstDataReport/{panNo}" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getGSTDataReport(@PathVariable(value = "panNo") String panNo, HttpServletResponse  httpServletResponse) {
		logger.info("Into get Gst Data Report ");
		if (CommonUtils.isObjectNullOrEmpty(panNo)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, panNo);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		try {
			Map<String, Object> response = camReportPdfDetailsService.getGstReportData(panNo);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate("GSTDATAREPORT");
			reportRequest.setType("GSTDATAREPORT");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);

			if (byteArr != null && byteArr.length > 0) {
				httpServletResponse.setContentType("application/octet-stream");
				httpServletResponse.setHeader("Content-Disposition", String.format("inline; filename=GST\"" + " GSTDataReport.pdf" + "\""));
				httpServletResponse.setContentLength((int) byteArr.length);
				InputStream inputStream = new ByteArrayInputStream(byteArr); 
				FileCopyUtils.copy(inputStream, httpServletResponse.getOutputStream());
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), SUCCESS_LITERAL, response),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Autowired
	private PLCamReportService plCamReportService;
	
	//only to view cam data of All types
	@GetMapping(value = {"/getCamData/{applicationId}/{productMappingId}/{proposalId}","/getCamData/{applicationId}/{productMappingId}/{proposalId}/{loanType}","/getCamData/{applicationId}/{productMappingId}/{proposalId}/{loanType}/{camType}"}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCamDataByProposalId(@PathVariable(value = "proposalId") Long proposalId, @PathVariable(value = "applicationId") Long applicationId, @PathVariable(value = "productMappingId") Long productId,
			@PathVariable(name= "loanType" , required = false) Integer loanType, @PathVariable(name= "camType" , required = false) Boolean camType, HttpServletRequest request) {
		
		if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(productId) || CommonUtils.isObjectNullOrEmpty(proposalId)) {
			logger.warn(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, applicationId + productId + proposalId);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_DATA_OR_REQUESTED_DATA_NOT_FOUND, HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
		}
		try {
			logger.info("In getCamData Method With ApplicationId==>{} ProductMappingId==>{} ProposalId==>{} LoanType==>{} camType==>{}" , 
					applicationId ,productId ,proposalId ,loanType ,camType);
			
			if(camType == null) {
				camType = false;
			}
			
			if(loanType == null) {
				loanType = 1;
			}
			
			Map<String, Object> response = new HashMap<String, Object>();
				
			if(loanType == LoanType.PERSONAL_LOAN.getValue()) {
				logger.info("Fetching Data of Personal Loan by ApplicationId==>{} ProductMappingId==>{} ProposalId==>{}");
				response = plCamReportService.getCamReportDetailsByProposalId(applicationId, productId,proposalId, camType);
			}else if(loanType == LoanType.HOME_LOAN.getValue()) {
				logger.info("Fetching Data of Home Loan by ApplicationId==>{} ProductMappingId==>{} ProposalId==>{}");
				response = hlCamReportService.getCamReportDetailsByProposalId(applicationId, productId,proposalId, camType);
			}else {
				logger.info("Fetching Data of MSME by ApplicationId==>{} ProductMappingId==>{} ProposalId==>{}");
				response = camReportPdfDetailsService.getCamReportPrimaryDetails(applicationId,productId,proposalId, camType);
			}
			
			if(response != null && !response.isEmpty()) {
				logger.info("DocumentResponse Data==>{}",response);
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", response),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_MAP_DETAILS, e);
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
