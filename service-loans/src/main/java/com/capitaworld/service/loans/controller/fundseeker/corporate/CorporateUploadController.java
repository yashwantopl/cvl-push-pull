package com.capitaworld.service.loans.controller.fundseeker.corporate;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.utils.CommonUtils;

@RestController
@RequestMapping("/corporate_upload")
public class CorporateUploadController {

	private static final Logger logger = LoggerFactory.getLogger(CorporateUploadController.class);

	private static final String DMS_BASE_URL_KEY = "dmsURL";

	@Autowired
	private Environment environment;

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String getPing() {
		logger.info("Ping success");
		return "Ping Succeed";
	}

	@RequestMapping(value = "/profile/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> uploadProfileImage(@RequestPart("fileName") String fileName,
			@RequestPart("file") MultipartFile multipartFiles, @PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {
			if (CommonUtils.isObjectNullOrEmpty(fileName)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse("Invalid data or Requested data not found.", HttpStatus.BAD_REQUEST.value()),
						HttpStatus.OK);
			}

			DocumentResponse documentResponse = uploadProfilePic(applicationId, fileName, multipartFiles);
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				logger.info("Profile picture uploaded successfully-->");
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(documentResponse.getMessage(), HttpStatus.OK.value()), HttpStatus.OK);
			} else {
				logger.error(CommonUtils.SOMETHING_WENT_WRONG);
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

	@RequestMapping(value = "/profile/get/{applicationId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> getProfileImage(@PathVariable("applicationId") Long applicationId,
			HttpServletRequest request) {
		try {
			if (CommonUtils.isObjectNullOrEmpty(applicationId)) {
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}

			DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(DMS_BASE_URL_KEY));
			DocumentRequest docRequest = new DocumentRequest();
			docRequest.setApplicationId(applicationId);
			docRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
			docRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
			DocumentResponse documentResponse = dmsClient.listProductDocument(docRequest);
			logger.info("Profile Picture Found");
			LoansResponse loansResponse = new LoansResponse(documentResponse.getMessage(), HttpStatus.OK.value());
			loansResponse.setData(documentResponse);
			return new ResponseEntity<LoansResponse>(loansResponse, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error while Getting Profile Images==>" + e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	private DocumentResponse uploadProfilePic(Long applicantId, String fileName, MultipartFile multipartFile) {

		DMSClient dmsClient = new DMSClient(environment.getRequiredProperty(DMS_BASE_URL_KEY));
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("applicationId", applicantId);
		jsonObj.put("productDocumentMappingId", DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
		jsonObj.put("userType", DocumentAlias.UERT_TYPE_APPLICANT);
		jsonObj.put("originalFileName", fileName);
		try {
			DocumentResponse documentResponse = dmsClient.productImage(jsonObj.toString(), multipartFile);
			return documentResponse;
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			logger.error("Error while uploading Profile Document");
			e.printStackTrace();
		}
		return null;
	}

}
