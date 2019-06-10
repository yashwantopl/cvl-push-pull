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
import com.capitaworld.service.loans.service.fundseeker.retail.HLCamReportService;
import com.capitaworld.service.loans.service.fundseeker.retail.PLCamReportService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/cam")
public class CamReportPdfDetailsController {
	
	@Autowired
	private CamReportPdfDetailsService camReportPdfDetailsService;
	
	@Autowired
	private InEligibleProposalCamReportService inEligibleProposalCamReportService;

	@Autowired
	private PLCamReportService plCamService;

	@Autowired
	private UniformProductCamReportService uniformProductCamReport;
	
	@Autowired
	private HLCamReportService hlCamReportService;

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
			reportRequest.setType("CAMREPORTPRIMARYSIDBI");
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
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 582L);
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
			Map<String,Object> response = plCamService.getPLInEligibleCamReport(applicationId);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(response);
			reportRequest.setTemplate(HLINELIGIBLE_CAM_REPORT );
			reportRequest.setType(HLINELIGIBLE_CAM_REPORT );
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);
			MultipartFile multipartFile = new DDRMultipart(byteArr);
			  JSONObject jsonObj = new JSONObject();
			  
				jsonObj.put(CommonUtils.APPLICATION_ID, applicationId);
				jsonObj.put(PRODUCT_DOCUMENT_MAPPING_ID, 583L);
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
}
