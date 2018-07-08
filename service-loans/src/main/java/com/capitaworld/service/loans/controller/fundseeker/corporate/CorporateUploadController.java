package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.dms.util.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.service.common.DownLoadCMAFileService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateUploadService;
import com.capitaworld.service.loans.service.fundseeker.corporate.ExcelExtractionService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/corporate_upload")
public class CorporateUploadController {

	private static final Logger logger = LoggerFactory.getLogger(CorporateUploadController.class);

	@Autowired
	private Environment environment;

	@Autowired
	private ExcelExtractionService excelExtractionService;

	@Autowired
	private CorporateUploadService corporateUploadService;
	
	@Autowired
	private DownLoadCMAFileService downLoadCMAFileService;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/profile/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadProfileImage(@RequestPart("fileName") String fileName,
			@RequestPart("userType") String userType, @RequestPart("productDocMapId") String productDocMapId,
			@RequestPart("file") MultipartFile multipartFiles, @PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "uploadProfileImage");
			if (CommonUtils.isObjectNullOrEmpty(fileName) || CommonUtils.isObjectNullOrEmpty(productDocMapId)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			DocumentResponse documentResponse = corporateUploadService.uploadProfile(applicationId,
					Long.valueOf(productDocMapId), fileName, userType, multipartFiles);
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				logger.info("Profile picture uploaded successfully-->");
				CommonDocumentUtils.endHook(logger, "uploadProfileImage");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(documentResponse.getMessage(), HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				logger.error(CommonUtils.SOMETHING_WENT_WRONG);
				CommonDocumentUtils.endHook(logger, "uploadProfileImage");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/profile/get/{applicationId}/{mappingId}/{userType}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProfileImage(@PathVariable("applicationId") Long applicationId,
			@PathVariable("mappingId") Long mappingId, @PathVariable("userType") String userType,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "getProfileImage");
			if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(mappingId)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			DocumentResponse profilePic = corporateUploadService.getProfilePic(applicationId, mappingId, userType);
			LoansResponse loansResponse = new LoansResponse(profilePic.getMessage(), HttpStatus.OK.value());
			loansResponse.setData(profilePic);
			CommonDocumentUtils.endHook(logger, "getProfileImage");
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Getting Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/other_doc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadDoc(@RequestPart("uploadRequest") String documentRequestString,
			@RequestPart("file") MultipartFile multipartFiles, HttpServletRequest request,@RequestParam(value = "clientId", required = false) Long clientId) {
		try {
			CommonDocumentUtils.startHook(logger, "uploadDoc");
			if (CommonUtils.isObjectNullOrEmpty(documentRequestString)) {
				logger.warn("Document Request Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}

			DocumentResponse response = corporateUploadService.uploadOtherDoc(documentRequestString, multipartFiles,userId);
			if (response != null && response.getStatus() == 200) {
				logger.info("File Uploaded SuccessFully");
				CommonDocumentUtils.endHook(logger, "uploadDoc");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(response.getMessage(), HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				logger.error("Error While Uploading Document==>");
				CommonDocumentUtils.endHook(logger, "uploadDoc");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/other_doc/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getDocList(@RequestBody DocumentRequest documentRequest,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "getDocList");
			DocumentResponse response = corporateUploadService.getOtherDoc(documentRequest);
			if (response != null && response.getStatus() == 200) {
				logger.info("File Uploaded SuccessFully -->");
				LoansResponse finalResponse = new LoansResponse(response.getMessage(), response.getStatus());
				finalResponse.setListData(response.getDataList());
				CommonDocumentUtils.endHook(logger, "getDocList");
				return new ResponseEntity<LoansResponse>(finalResponse, HttpStatus.OK);
			} else {
				logger.warn("Invalid Request while Getting Documents");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/excel_doc/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getExcelDocList(@RequestBody DocumentRequest documentRequest,
			HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "getExcelDocList");
			if (CommonUtils.isObjectNullOrEmpty(documentRequest)) {
				logger.warn("Document Request Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));

			DocumentResponse response = dmsClient.listProductDocument(documentRequest);
			if (response != null && response.getStatus() == 200) {
				logger.info("File Uploaded SuccessFully -->");
				LoansResponse finalResponse = new LoansResponse(response.getMessage(), response.getStatus());
				if (!(response.getDataList().size() > 0)) {
					try {
						Thread.sleep(5000); // 5000 milliseconds is three
											// second.
						response = dmsClient.listProductDocument(documentRequest);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
				}
				finalResponse = new LoansResponse(response.getMessage(), response.getStatus());
				finalResponse.setListData(response.getDataList());
				CommonDocumentUtils.endHook(logger, "getExcelDocList");
				return new ResponseEntity<LoansResponse>(finalResponse, HttpStatus.OK);
			} else {
				logger.warn("Invalid Request while Getting Documents");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/excel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadExcel(@RequestPart("uploadRequest") String documentRequestString,
			@RequestPart("file") MultipartFile multipartFiles, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "uploadExcel");
			if (CommonUtils.isObjectNullOrEmpty(documentRequestString)) {
				logger.warn("Document Request Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));

			Long applicationId = MultipleJSONObjectHelper.getNestedObject(documentRequestString, "applicationId",
					Long.class);
			Integer productDocumentMappingId = MultipleJSONObjectHelper.getNestedObject(documentRequestString,
					"productDocumentMappingId", Integer.class);
			DocumentResponse response = dmsClient.readExcel(documentRequestString, multipartFiles);

			if (response != null && response.getStatus() == 200) {

				// Code for read CMA BS and DPR

				Boolean flag = false;
				try {

					switch (productDocumentMappingId) {
					case DocumentAlias.WC_DPR_OUR_FORMAT: {
						flag = excelExtractionService.readDPR(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.WC_CMA: {
						flag = excelExtractionService.readCMA(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.WC_COMPANY_ACT: {
						flag = excelExtractionService.readBS(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.WCTL_CMA: {
						flag = excelExtractionService.readCMA(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.WCTL_COMPANY_ACT: {
						flag = excelExtractionService.readBS(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.TL_DPR_OUR_FORMAT: {
						flag = excelExtractionService.readDPR(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.TL_CMA: {
						flag = excelExtractionService.readCMA(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.TL_COMPANY_ACT: {
						flag = excelExtractionService.readBS(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.USL_CMA: {
						flag = excelExtractionService.readCMA(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					case DocumentAlias.USL_COMPANY_ACT: {
						flag = excelExtractionService.readBS(applicationId, response.getStorageId(), multipartFiles);
						break;
					}
					}

				} catch (Exception e) {
					// TODO: handle exception

					JSONObject json = new JSONObject();
					json.put("id", response.getStorageId());
					dmsClient.deleteProductDocument(json.toJSONString());

					// code for inactive CMA BS and DPR recored

					logger.error("Error While Uploading Document==>");
					e.printStackTrace();
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()),
							HttpStatus.OK);
				}

				if (flag) {

					logger.info("File Uploaded SuccessFully");
					CommonDocumentUtils.endHook(logger, "uploadExcel");
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(response.getMessage(), HttpStatus.OK.value()), HttpStatus.OK);
				} else {

					// code for inactive CMA BS and DPR recored
					System.out.println("inActivation start");
					JSONObject json = new JSONObject();
					json.put("id", response.getStorageId());
					System.out.println("excel file's storage id is====>>>>"+response.getStorageId());
					dmsClient.deleteProductDocument(json.toJSONString());
					
					logger.error("Error While Uploading Document==>");
					CommonDocumentUtils.endHook(logger, "uploadExcel");
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()),
							HttpStatus.OK);
				}

			} else {
				logger.warn("Invalid Request while Getting Documents");
				CommonDocumentUtils.endHook(logger, "uploadExcel");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error While Uploading Document==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/other_doc/delete/{docId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> removeDoc(@PathVariable("docId") Long docId, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "removeDoc");
			if (CommonUtils.isObjectNullOrEmpty(docId)) {
				logger.warn("Document Id not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));

			JSONObject json = new JSONObject();
			json.put("id", docId);
			DocumentResponse response = dmsClient.deleteProductDocument(json.toJSONString());

			// code for inactive CMA BS and DPR recored

			try {
				excelExtractionService.inActiveCMA(docId);
				excelExtractionService.inActiveBS(docId);
				excelExtractionService.inActiveDPR(docId);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (response != null && response.getStatus() == 200) {
				logger.info("File SuccessFully Removed.");
				LoansResponse finalResponse = new LoansResponse(response.getMessage(), response.getStatus());
				finalResponse.setListData(response.getDataList());
				CommonDocumentUtils.endHook(logger, "removeDoc");
				return new ResponseEntity<LoansResponse>(finalResponse, HttpStatus.OK);
			} else {
				logger.warn("Invalid Request while Deleting Document");
				CommonDocumentUtils.endHook(logger, "removeDoc");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/excel_doc/delete/{docId}/{productDocumentMappingId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> removeExcelDoc(@PathVariable("docId") Long docId,
			@PathVariable("productDocumentMappingId") Integer productDocumentMappingId, HttpServletRequest request) {
		try {
			System.out.println("in /excel/delete/doc");
			CommonDocumentUtils.startHook(logger, "removeExcelDoc");
			System.out.println("product documet id is===>>>>>"+productDocumentMappingId);
			if (CommonUtils.isObjectNullOrEmpty(docId) && CommonUtils.isObjectNullOrEmpty(productDocumentMappingId)) {
				logger.warn("Document Id and ProductDocumentMappingId not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(CommonUtils.DMS_BASE_URL_KEY));
			
			System.out.println("dms client url is====>>>>"+dmsClient);

			// code for inactive CMA BS and DPR recored

			try {

				JSONObject json = new JSONObject();
				System.out.println("docis====>>"+docId);
				json.put("id", docId);
				DocumentResponse response = dmsClient.deleteProductDocument(json.toJSONString());

				if (response != null && response.getStatus() == 200) {

					if (productDocumentMappingId == DocumentAlias.WC_DPR_OUR_FORMAT
							|| productDocumentMappingId == DocumentAlias.TL_DPR_OUR_FORMAT)
						excelExtractionService.inActiveDPR(docId);
					else if (productDocumentMappingId == DocumentAlias.WC_CMA
							|| productDocumentMappingId == DocumentAlias.TL_CMA || productDocumentMappingId == DocumentAlias.WCTL_CMA || productDocumentMappingId==DocumentAlias.USL_CMA)
						{
						System.out.println("in else if for cma inactivate...===>>>>"+docId);
						excelExtractionService.inActiveCMA(docId);}
					else if (productDocumentMappingId == DocumentAlias.WC_COMPANY_ACT
							|| productDocumentMappingId == DocumentAlias.TL_COMPANY_ACT || 
							productDocumentMappingId==DocumentAlias.USL_COMPANY_ACT)
						excelExtractionService.inActiveBS(docId);
					

					logger.info("File SuccessFully Removed.");
					LoansResponse finalResponse = new LoansResponse(response.getMessage(), response.getStatus());
					
					System.out.println("fina response is====>>"+finalResponse);
					finalResponse.setListData(response.getDataList());
					CommonDocumentUtils.endHook(logger, "removeExcelDoc");
					return new ResponseEntity<LoansResponse>(finalResponse, HttpStatus.OK);
				} else {
					logger.warn("Invalid Request while Deleting Document");
					CommonDocumentUtils.endHook(logger, "removeExcelDoc");
					return new ResponseEntity<LoansResponse>(
							new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()),
							HttpStatus.OK);
				}

			} catch (Exception e) {
				// TODO: handle exception
				logger.error("Invalid Request while Deleting Document");
				e.printStackTrace();
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/update_flag/{isFilled}/{applicationId}/{tabType}/{filledCount}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadFlag(@PathVariable("isFilled") Boolean isFilled, @PathVariable("applicationId") Long applicationId,
			@PathVariable("tabType") Integer tabType,@PathVariable(value = "filledCount",required = false) String filledCount, 
			@RequestParam(value = "clientId", required = false) Long clientId, HttpServletRequest request) {
		try {
			CommonDocumentUtils.startHook(logger, "uploadFlag");
			Long userId = null;
			if (CommonDocumentUtils.isThisClientApplication(request)) {
				userId = clientId;
			} else {
				userId = (Long) request.getAttribute(CommonUtils.USER_ID);
			}
			if (CommonUtils.isObjectNullOrEmpty(applicationId) || CommonUtils.isObjectNullOrEmpty(tabType)) {
				logger.warn("applicationId or tabType Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			corporateUploadService.updateLoanApplicationFlag(applicationId, userId, tabType, isFilled,filledCount);
			CommonDocumentUtils.endHook(logger, "uploadFlag");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully updated", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Saving Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value = "/uploadDocumentList/get/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<LoansResponse> getOtherDocReport(@PathVariable("applicationId") Long applicationId, HttpServletRequest httpRequest, @RequestParam(value = "clientId", required = false) Long clientId) {
		try {	
			CommonDocumentUtils.startHook(logger, "getOtherDocReport");
			if (CommonUtils.isObjectNullOrEmpty(applicationId) ) {
				logger.warn("applicationId Must not be null");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			
			LoansResponse loansResponse = new LoansResponse();
			Map<String, Map<String, Object>> response = corporateUploadService.getOtherDocReport(applicationId);
			
			loansResponse.setMapData(response);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while fetching doc==>" + e);
			return null;
		}
	}
	
	@RequestMapping(value="/downloadCMAAndCoCMAExcelFile/{applicationId}/{productDocumentMappingId}" , method=RequestMethod.GET)
	public void downloadExcelFile(@PathVariable("applicationId") Long applicationId , @PathVariable("productDocumentMappingId") Long productDocumentMappingId ,HttpServletResponse httpServletResponse) {
		logger.info("In getCmaFile");
		
        try {
        	  httpServletResponse.setContentType("application/csv");  
          if(productDocumentMappingId==(long)DocumentAlias.WC_CMA) {
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\""+CommonUtils.CW_CMA_EXCEL+"\"");
            downLoadCMAFileService.cmaFileGenerator(applicationId, productDocumentMappingId).write(httpServletResponse.getOutputStream());
          }
          
          else if (productDocumentMappingId==(long)DocumentAlias.TL_CMA || productDocumentMappingId==(long) DocumentAlias.WCTL_CMA){
        	  httpServletResponse.setContentType("application/csv");
        	  httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\""+CommonUtils.CW_TL_WCTL_EXCEL+"\"");
              downLoadCMAFileService.cmaFileGenerator(applicationId, productDocumentMappingId).write(httpServletResponse.getOutputStream());
		}
          else if(productDocumentMappingId==(long)DocumentAlias.WC_COMPANY_ACT|| productDocumentMappingId==(long)DocumentAlias.TL_COMPANY_ACT || productDocumentMappingId==(long)DocumentAlias.USL_COMPANY_ACT|| productDocumentMappingId==(long) DocumentAlias.WCTL_COMPANY_ACT_DOC ) {
        	  httpServletResponse.setContentType("application/csv");
        	  httpServletResponse.setHeader("Content-Disposition", "attachment; filename=\""+CommonUtils.CO_CMA_EXCEL+"\"");
              downLoadCMAFileService.coCMAFileGenerator(applicationId, productDocumentMappingId).write(httpServletResponse.getOutputStream());
          }
            logger.info("Out getCmaFile");
         
        } catch (NullPointerException |IOException e) {
        	logger.info("thrown exception from getCmaFile");
            e.printStackTrace();
          
        }
     
	}
	
	
	@RequestMapping(value="/get_CMA_by_applicationId_productDocumentMappingId/{applicationId}/{productDocumentMappingId}" , method=RequestMethod.GET)
	public ResponseEntity<LoansResponse> getCMAForGateway(@PathVariable("applicationId") Long applicationId , @PathVariable("productDocumentMappingId") Long productDocumentMappingId) throws IOException {
		logger.info("In getCmaFile");
		
		try {
			
			ByteArrayOutputStream bos = null;
			ObjectOutputStream oos = null;
			byte[] cmaFile = null;
			Workbook wb = downLoadCMAFileService.cmaFileGenerator(applicationId, productDocumentMappingId);
			logger.info("WorkBook Object====>"+wb);
			
				
			    bos = new ByteArrayOutputStream();
			    oos = new ObjectOutputStream(bos);
			    oos.writeObject(wb);
			    cmaFile = bos.toByteArray();
			    logger.info("WorkBook Object as bytes====>"+cmaFile);	

			

			LoansResponse response = new LoansResponse("CMA Successfully generated", HttpStatus.OK.value(), cmaFile);

			return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);

		} catch (NullPointerException e) {
			logger.info("Thrown exception from getCmaFile====>"+e.getMessage());

		}
		LoansResponse response = new LoansResponse("Mapping Id not matched", HttpStatus.OK.value(), null);
		return new ResponseEntity<LoansResponse>(response, HttpStatus.OK);
	}
	
}
