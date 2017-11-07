package com.capitaworld.service.loans.controller.rating;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.TotalCostOfProjectService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.CompanyDetails;
import com.capitaworld.service.rating.model.QualitativeInputRequest;
import com.capitaworld.service.rating.model.RatingResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import exception.RatingException;

@RestController
@RequestMapping("/rating")
public class RatingController {

	private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	
	@Autowired
	private RatingClient ratingClient;
	
	@Autowired
	private TotalCostOfProjectService totalCostOfProjectService;
	
	@Autowired
	private DMSClient dmsClient;
	
	
	@Autowired
	private CorporateApplicantService applicantService;
	
	@RequestMapping(value = "/company_details/get_company_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RatingResponse getCompanyDetails(@RequestBody Long applicationId,HttpServletRequest httpRequest,@RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		try {
			Long userId = null;
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE)).intValue()) {
				userId = clientId;
			} else {
				userId = (Long) httpRequest.getAttribute(CommonUtils.USER_ID);
			}
			CompanyDetails companyDetails=applicantService.getCompanyDetails(applicationId,userId);
			RatingResponse ratingResponse=ratingClient.getCompanyDetails(companyDetails);
			return ratingResponse;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}

	@RequestMapping(value = "/qualitative_input/get_qualitative_input", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RatingResponse getQualitativeInput(@RequestBody QualitativeInputRequest qualitativeInputRequest,HttpServletRequest httpRequest) throws RatingException {
		try {
			qualitativeInputRequest.setUserId(((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue());
			
			RatingResponse ratingResponse=ratingClient.getQualitativeInput(qualitativeInputRequest);
			return ratingResponse;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	
	@RequestMapping(value = "/qualitative_input/save_qualitative_input", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RatingResponse saveQualitativeInput(@RequestBody RatingResponse ratingResponse,HttpServletRequest httpRequest) throws RatingException {
		try {
			Long userId=((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue();
			ratingResponse.setUserId(userId);
			
			if( 2 == ratingResponse.getProductId())
			{
				ratingResponse.setProjectSize(totalCostOfProjectService.getCostOfProject(ratingResponse.getApplicationId(), userId));
			}
			RatingResponse ratingResponseNew=ratingClient.saveQualitativeInput(ratingResponse);
			return ratingResponseNew;
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	@RequestMapping(value = "/manufacturing/save_financial_input", method = RequestMethod.POST)
	public RatingResponse saveFinancialInputOfManufacturing(@RequestPart("fileBytes") MultipartFile multipartFile, @RequestPart("request") String request, HttpServletRequest httpRequest,
			@RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		try {
			ObjectMapper mapperObj = new ObjectMapper();
			RatingResponse ratingResponse = new RatingResponse();
			ratingResponse =MultipleJSONObjectHelper.getObjectFromString(request, RatingResponse.class);
			ratingResponse.setUserId(((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue());
			String jsonStr = mapperObj.writeValueAsString(ratingResponse);
			RatingResponse ratingResponseNew=ratingClient.saveFinancialInputOfManufacturing(multipartFile,jsonStr);
			if(ratingResponseNew.getStatus() == HttpStatus.OK.value())
			{
				   JSONObject jsonObj = new JSONObject();
				   jsonObj.put("companyId", ratingResponse.getCompanyDetailsId());
				   jsonObj.put("documentId", DocumentAlias.IRR_MANUFACTURING);
				   jsonObj.put("originalFileName", multipartFile.getOriginalFilename());
				   try {
				    DocumentResponse documentResponse = dmsClient.uploadIrrDocument(jsonObj.toString(), multipartFile);
				    logger.info("exit form uploadDocument()");
				   } catch (DocumentException e) {
				    // TODO Auto-generated catch block
				    e.printStackTrace();
				  }
			}
			return ratingClient.saveFinancialInputOfManufacturing(multipartFile,jsonStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	@RequestMapping(value = "/manufacturing/calculate_fitch_Rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RatingResponse calculateFitchRatingOfManufacturing(HttpServletRequest httpRequest) throws RatingException {
		try {
			return ratingClient.calculateFitchRatingOfManufacturing(((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}

	
	
	@RequestMapping(value = "/service/save_financial_input", method = RequestMethod.POST)
	public RatingResponse saveFinancialInputOfService(@RequestPart MultipartFile fileBytes,HttpServletRequest httpRequest) throws RatingException {
		try {
			RatingResponse ratingResponse=new RatingResponse();
			ratingResponse.setUserId(((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue());
			return ratingClient.saveFinancialInputOfService(fileBytes,ratingResponse.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	@RequestMapping(value = "/service/calculate_fitch_Rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public RatingResponse calculateFitchRatingOfService(HttpServletRequest httpRequest) throws RatingException {
		try {
			return ratingClient.calculateFitchRatingOfService(((Long) httpRequest.getAttribute(CommonUtils.USER_ID)).longValue());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	
	@RequestMapping(value = "/get_irr_format", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public StorageDetailsResponse getIrrFormat(@RequestBody DocumentRequest documentRequest,HttpServletRequest httpRequest) {
		try {
		    DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
		    List<Map<String,Object>> list =  documentResponse.getDataList();
		    if(!CommonUtils.isListNullOrEmpty(list)){
		     StorageDetailsResponse response = null;
		     try {
		      response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
		      return response;
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		     }
		    }
		    
		   } catch (DocumentException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    logger.warn("Error while getting image");
		   }
		return null;
	}
	
	
	@RequestMapping(value = "/inactive_irr_format", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DocumentResponse inActiveIrrFormat(@RequestBody String userStorageId,HttpServletRequest httpRequest) {
		  try {
			   DocumentResponse documentResponse = dmsClient.deleteUserDocument(userStorageId);
			   logger.info("exit form deleteUploadedDocuments()");
			   return documentResponse;
			  } catch (DocumentException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }  
			  logger.info("exit form deleteUploadedDocuments()");
			  return null;
	}
	
	
}
