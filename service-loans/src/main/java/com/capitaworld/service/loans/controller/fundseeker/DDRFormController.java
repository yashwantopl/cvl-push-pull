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
import com.capitaworld.service.loans.model.common.DocumentUploadFlagRequest;
import com.capitaworld.service.loans.model.ddr.DDRCustomerRequest;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;
import com.capitaworld.service.loans.model.ddr.DDROneFormResponse;
import com.capitaworld.service.loans.model.ddr.DDRRequest;
import com.capitaworld.service.loans.model.ddr.DDRUploadRequest;
import com.capitaworld.service.loans.service.fundseeker.corporate.DDRFormService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.DDRMultipart;

@RestController
@RequestMapping("/ddr")
public class DDRFormController {

	private static final Logger logger = LoggerFactory.getLogger(DDRFormController.class);

	private static final String SAVING_REQUEST_TO_DB_MSG = "Saving Request to DB ===> ";
	private static final String UPLOAD_FLAG = "uploadFlag";

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
	 * 
	 * @param ddrFormDetailsRequest
	 * @param request
	 * @param clientId
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> save(@RequestBody DDRFormDetailsRequest ddrFormDetailsRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR Form Save Method -------------------------->");
		if (CommonUtils.isObjectNullOrEmpty(ddrFormDetailsRequest.getApplicationId())) {
			logger.info("Invalid Request, Application Id is null or Empty");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
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
		} catch (Exception e) {
			logger.error("Error while saving DDR Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	/**
	 * GET DDR MERGE FORM DETAILS BY DDR FORM ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getCombinedDDR/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCombinedDDR(@PathVariable("appId") Long appId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in COMBINED DDR Form Get Method -------------------------->" + appId);

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		try {
			DDRRequest ddrRequest = ddrFormService.getMergeDDR(appId, userId);
			logger.info("DDR Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), ddrRequest),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getCombinedDDR/{appId}/{proposalId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCombinedDDR(@PathVariable("appId") Long appId, @PathVariable("proposalId") Long proposalId, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in COMBINED DDR Form Get Method -------------------------->" + appId +" -- "+proposalId);

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		try {
			DDRRequest ddrRequest = ddrFormService.getMergeDDRByProposalId(appId, userId,proposalId);
			logger.info("DDR Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), ddrRequest),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	/**
	 * SAVE DDR MERGE FORM DETAILS BY DDR FORM ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/saveCombinedDDR", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCombinedDDR(@RequestBody DDRRequest ddrRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in COMBINED DDR Form SAVE Method -------------------------->");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Invalid Request, UserId is null or Empty, COMBINED DDR Form SAVE Method");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			ddrRequest.setUserId(userId);
			ddrFormService.saveMergeDDR(ddrRequest);
			logger.info("DDR COMBINED Form Saved Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Data Saved", HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving COMBINED DDR Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/saveCombinedDDRByProposalId", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCombinedDDRNew(@RequestBody DDRRequest ddrRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in COMBINED DDR Form SAVE Method -------------------------->");

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Invalid Request, UserId is null or Empty, COMBINED DDR Form SAVE Method");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			ddrRequest.setUserId(userId);
			ddrFormService.saveMergeDDRByProposalId(ddrRequest);
			logger.info("DDR COMBINED Form Saved Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Data Saved", HttpStatus.OK.value()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while saving COMBINED DDR Form Details ==>{}", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	/**
	 * GET DDR FORM DETAILS BY DDR FORM ID
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/get/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> get(@PathVariable("appId") Long appId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR Form Get Method -------------------------->" + appId);

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}

		try {
			DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(appId, userId);
			logger.info("DDR Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), dDRFormDetailsRequest),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getAutoFilledDetails/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancial(@PathVariable("appId") Long appId, HttpServletRequest request,
			@RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("Enter in DDR AutoFilled Form Get Method -------------------------->" + appId);

		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}

		if (CommonUtils.isObjectNullOrEmpty(userId)) {
			logger.info("Invalid Request, UserId is null or Empty");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			DDROneFormResponse oneFormDetails = ddrFormService.getOneFormDetails(userId, appId, null, true);
			logger.info("DDR AutoFilled Form Get Successfully---------------------------->");
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), oneFormDetails), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR AutoFilled Form Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getFinancialToBeFilledMaster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancialToBeFilled() {
		try {
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),
					ddrFormService.getFinancialSummaryToBeFieldsList()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Financial To Be Filled Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getSIDBI/{appId}/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getSidbi(@PathVariable("appId") Long appId,
			@PathVariable("userId") Long userId) {
		try {
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),
					ddrFormService.getSIDBIDetails(appId, userId)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Financial To Be Filled Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/getFinancialAutoFilledMaster", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getFinancialAutoFilled() {
		try {
			return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(),
					ddrFormService.getFinancialSummaryFieldsList()), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while getting DDR Financial Auto Filled Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/deleteDocs", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> deleteDocuments(@RequestBody DDRUploadRequest ddrUploadRequest) {
		try {
			boolean deleteDocument = ddrFormService.deleteDocument(ddrUploadRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Deleted", HttpStatus.OK.value(), deleteDocument), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while DDR Delete Documents ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/deleteDocsByProposalId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> deleteDocsByPropsoalId(@RequestBody DDRUploadRequest ddrUploadRequest) {
		try {
			boolean deleteDocument = ddrFormService.deleteDocumentByProposalId(ddrUploadRequest);
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Deleted", HttpStatus.OK.value(), deleteDocument), HttpStatus.OK);
		} catch (Exception e) {logger.error("Error while DDR Delete Documents ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/generateDDRPDF/{appId}/{proposalId}", method = RequestMethod.GET)
	public ResponseEntity<LoansResponse> generateDDRPDF(@PathVariable(value = "appId") Long appId,@PathVariable(value = "proposalId") Long proposalId, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "clientId", required = false) Long clientId) {
		logger.info("In generateDDRPDF");
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));

		Long  toApplicationId = loanApplicationService.getApplicationIdByProposalId(proposalId);
		Long toUserId = loanApplicationService.getUserIdByProposalId(proposalId);

	     logger.info("THIS IS THE USER ID ----------->"+toUserId);
	     logger.info("THIS IS THE application Id ----------->"+toApplicationId);

/*		 toApplicationId = ApplicationProposalMapping.getApplicationId();
	     Long toUserId = ApplicationProposalMapping.getUserId();*/

		if (CommonUtils.UserType.FUND_PROVIDER == userType) {
		//userId = loanApplicationService.getUserIdByApplicationId(appId);
			userId = loanApplicationService.getUserIdByApplicationId(toApplicationId); // NEW BASED ON PROPOSAL MAPPING ID
		} else if (CommonDocumentUtils.isThisClientApplication(request)) {
			userId = clientId;
		}
		Boolean isDDRApproved = false;
		try {
			//isDDRApproved = ddrFormService.isDDRApproved(userId, appId); // PRREVIOUS
			isDDRApproved = ddrFormService.isDDRApprovedByProposaId(proposalId); // NEW BASED ON PROPOSALID
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		if (!isDDRApproved) {
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.DDR_NOT_APPROVED, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}

		try {
			/*DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(appId, userId);
			DDROneFormResponse oneFormDetails = ddrFormService.getOneFormDetails(userId, appId,true);*/ // PREVIOUS

			DDRFormDetailsRequest dDRFormDetailsRequest = ddrFormService.get(toApplicationId, toUserId,proposalId);
			DDROneFormResponse oneFormDetails = ddrFormService.getOneFormDetails(toUserId, toApplicationId, proposalId, true); // BASED ON NEW APPLICATION ID

			DDRFormDetailsRequest.printFields(dDRFormDetailsRequest);
			DDROneFormResponse.printFields(oneFormDetails);
			Map<String, Object> obj = new HashMap<String, Object>();
			obj.put("autoFilled", oneFormDetails);
			obj.put("toBeFilled", dDRFormDetailsRequest);
			logger.info(""+obj);
			ReportRequest reportRequest = new ReportRequest();
			reportRequest.setParams(obj);
			reportRequest.setTemplate("NHBSDDR");
			reportRequest.setType("NHBSDDR");
			byte[] byteArr = reportsClient.generatePDFFile(reportRequest);

			MultipartFile multipartFile = new DDRMultipart(byteArr);
			JSONObject jsonObj = new JSONObject();

			jsonObj.put("applicationId", toApplicationId);
			jsonObj.put("productDocumentMappingId", 329L);
			jsonObj.put("userType", CommonUtils.UploadUserType.UERT_TYPE_APPLICANT);
			jsonObj.put("originalFileName", "NHBS_" + toApplicationId + ".pdf");

			DocumentResponse documentResponse = dmsClient.uploadFile(jsonObj.toString(), multipartFile);
			if (documentResponse.getStatus() == 200) {
				logger.info(""+documentResponse.getData());
				return new ResponseEntity<LoansResponse>(new LoansResponse(HttpStatus.OK.value(), "success", documentResponse.getData(), obj),HttpStatus.OK);
			} else {
				return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("thrown exception from generateDDRPDF : ",e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/uploadFlag", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadFlag(@RequestBody DocumentUploadFlagRequest documentRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {

			CommonDocumentUtils.startHook(logger, UPLOAD_FLAG);
			if (CommonUtils.isObjectNullOrEmpty(documentRequest)) {
				logger.warn("Document Request Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			logger.info("In uploadFlag");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			Integer userType = ((Integer) request.getAttribute(CommonUtils.USER_TYPE));
			if (CommonUtils.UserType.FUND_PROVIDER == userType) {
				userId = loanApplicationService.getUserIdByApplicationId(documentRequest.getApplicationId());
			} else if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			}
			documentRequest.setUserId(userId);
			Long response = ddrFormService.saveDocumentFLag(documentRequest);
			// DocumentResponse response =
			// null;//corporateUploadService.uploadOtherDoc(documentRequestString,
			// multipartFiles);
			if (response != null && response == 1) {
				logger.info("File Updated Document Flag SuccessFully");
				CommonDocumentUtils.endHook(logger, UPLOAD_FLAG);
				return new ResponseEntity<LoansResponse>(new LoansResponse(response.toString(), HttpStatus.OK.value()),
						HttpStatus.OK);
			} else {
				logger.error("Error While Updating Document Flag==>");
				CommonDocumentUtils.endHook(logger, UPLOAD_FLAG);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.error("Error While Updating Document Flag==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * CHECK CUSTOMER DETAILS FILLED OR NOT IN CORPORATE TEASER VIEW WHILE APPROVE PROPOSAL FOR ONLY BOB BANK
	 * 
	 * @param applicationId
	 * @return
	 */
	@RequestMapping(value = "/checkCustomerDetailFilled/{appId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> checkCustomerDetailFilled(@PathVariable("appId") Long appId) {
		logger.info("Enter in CHECK CUSTOMER DETAILS FILLED -------------------------->" + appId);
		try {
			DDRCustomerRequest resposnse = ddrFormService.checkCustomerDetailFilled(appId);
			logger.info("SUCCESSFULLT GET CUSTOMER DETAILS FILLED ---------------------------->" + resposnse.toString());
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), resposnse), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while CHECK CUSTOMER DETAILS FILLED" +  e.getMessage());
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	
	/**
	 * SAVE CUSTOMER DETAILS IN CORPORATE TEASER VIEW WHILE APPROVE PROPOSAL FOR ONLY BOB BANK
	 * 
	 * @param applicationId
	 * @return
	 */
	@RequestMapping(value = "/saveCustomerDetail", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> saveCustomerDetail(@RequestBody DDRCustomerRequest customerRequest,
			HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) {
		
		logger.info("ENTER IN SAVE CUSTOMER DETAIL FOR BOB ");
		if(CommonUtils.isObjectNullOrEmpty(customerRequest.getApplicationId())){
			logger.info("SAVE CUSTOMER DETAILS FILLED (APPLCIATION ID IS NULL OR EMPTY)");
			return new ResponseEntity<LoansResponse>( new LoansResponse("Invalid Request, ApplicationId is null or Empty", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
		if (CommonDocumentUtils.isThisClientApplication(request) && !CommonUtils.isObjectNullOrEmpty(clientId)) {
			userId = clientId;
		}
		customerRequest.setUserId(userId);
		logger.info("Enter in SAVE CUSTOMER DETAILS FILLED -------------------------->" + customerRequest.getApplicationId());
		try {
			boolean resposnse = ddrFormService.saveCustomerDetailFilled(customerRequest);
			if(resposnse) {
				logger.info("STATUS OF SAVE CUSTOMER DETAILS FILLED ---------------------------->" + resposnse);
				return new ResponseEntity<LoansResponse>( new LoansResponse("Customer details are successfully saved in DDR", HttpStatus.OK.value(), resposnse), HttpStatus.OK);	
			}
			logger.info("STATUS OF SAVE CUSTOMER DETAILS FILLED ---------------------------->" + resposnse);
			return new ResponseEntity<LoansResponse>( new LoansResponse("Invalid Request, Data not saved", HttpStatus.BAD_REQUEST.value(), resposnse), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while SAVE CUSTOMER DETAILS FILLED" +  e.getMessage());
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	
	/**
	 * GET CUSTOMER NAME BY CUSTOMER ID
	 * @return
	 */
	@RequestMapping(value = "/getCustomerNameById", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getCustomerNameById(@RequestBody DDRCustomerRequest customerRequest) {
		
		logger.info("ENTER IN GET CUSTOMER NAME BY CUSTOMER ID------------>");
		if(CommonUtils.isObjectNullOrEmpty(customerRequest.getCustomerId())){
			logger.info("SAVE CUSTOMER DETAILS FILLED (APPLCIATION ID IS NULL OR EMPTY)");
			return new ResponseEntity<LoansResponse>( new LoansResponse("Invalid Request, CustomerId is null or Empty", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
		}
		try {
			return new ResponseEntity<LoansResponse>( new LoansResponse(CommonUtils.SUCCESSFULLY_GET_DATA, HttpStatus.OK.value(), ddrFormService.getCustomerNameById(customerRequest)), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error while GET CUSTOMER NAME BY CUSTOMER ID" +  e.getMessage());
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
	

}
