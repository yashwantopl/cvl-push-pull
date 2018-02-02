package com.capitaworld.service.loans.controller.rating;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.IrrRequest;


/*
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
	public RatingResponse calculateFitchRatingOfManufacturing(@RequestBody Long companyDetailsId,HttpServletRequest httpRequest) throws RatingException {
		try {
			return ratingClient.calculateFitchRatingOfManufacturing(companyDetailsId);
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
	public RatingResponse calculateFitchRatingOfService(@RequestBody Long companyDetailsId,HttpServletRequest httpRequest) throws RatingException {
		try {
			return ratingClient.calculateFitchRatingOfService(companyDetailsId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
	
	
	@RequestMapping(value = "/get_irr_format", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DocumentResponse getIrrFormat(@RequestBody DocumentRequest documentRequest,HttpServletRequest httpRequest) {
		 StorageDetailsResponse response = null;
		try {
		    DocumentResponse documentResponse = dmsClient.listIrrDocument(documentRequest);
		    List<Map<String,Object>> list =  documentResponse.getDataList();
		    if(!CommonUtils.isListNullOrEmpty(list)){
		     try {
		      response = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
		      return documentResponse;
		     } catch (IOException e) {
		      // TODO Auto-generated catch block
		      e.printStackTrace();
		      return new DocumentResponse("No data found", HttpStatus.INTERNAL_SERVER_ERROR.value());
		     }
		    }
		    
		   } catch (DocumentException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		    logger.warn("Error while getting image");
		    return new DocumentResponse("No data found", HttpStatus.INTERNAL_SERVER_ERROR.value());
		   }
		 return new DocumentResponse("No data found", HttpStatus.INTERNAL_SERVER_ERROR.value());
	}
	
	
	@RequestMapping(value = "/inactive_irr_format", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DocumentResponse inActiveIrrFormat(@RequestBody String userStorageId,HttpServletRequest httpRequest) {
		  try {
			   DocumentResponse documentResponse = dmsClient.deleteIrrDocument(userStorageId);
			   logger.info("exit form deleteUploadedDocuments()");
			   return documentResponse;
			  } catch (DocumentException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }  
			  logger.info("exit form deleteUploadedDocuments()");
			  return null;
	}
	
	
}*/
@RestController
@RequestMapping("/rating")
public class RatingController {
	
private static final Logger logger = LoggerFactory.getLogger(RatingController.class);
	
	
	@Autowired
	private RatingClient ratingClient;
	
	@Autowired
	private IrrService irrService;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CorporateApplicantService applicantService;
	
	@RequestMapping(value = "/calculate_irr_Rating", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void calculateIrrRating(@RequestBody Long appId,HttpServletRequest httpRequest, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
		IrrRequest irrRequest = new IrrRequest();
		LoanApplicationMaster applicationMaster = null;
		try {
			applicationMaster = loanApplicationRepository.findOne(appId);
			irrRequest.setApplicationId(appId);
			//irrRequest.setCompanyName(applicationMaster.getMcaCompanyId());
			
			//---- Manufacturing
			irrRequest.setQualitativeInputSheetManuRequest(irrService.qualitativeInputServiceManu(appId, applicationMaster.getProductId()));
			//---- Service
			irrRequest.setQualitativeInputSheetServRequest(irrService.qualitativeInputServiceService(appId, applicationMaster.getProductId()));
			//---- Trading
			irrRequest.setQualitativeInputSheetTradRequest(irrService.qualitativeInputServiceTrading(appId, applicationMaster.getProductId()));
			
			
			if (CommonUtils.UserType.SERVICE_PROVIDER == ((Integer) httpRequest.getAttribute(CommonUtils.USER_TYPE))
					.intValue()) {
				irrRequest.setUserId(clientId);
			}
			// if CMA filled
			irrRequest.setFinancialInputRequest(irrService.cmaIrrMappingService(appId));
			// if coAct filled
			irrRequest.setFinancialInputRequest(irrService.coActIrrMappingService(appId));
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RatingException("Ratings Service is not available");
		}
	}
}
