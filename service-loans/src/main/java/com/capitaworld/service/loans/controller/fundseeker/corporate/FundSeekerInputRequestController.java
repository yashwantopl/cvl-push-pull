package com.capitaworld.service.loans.controller.fundseeker.corporate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.capitaworld.service.loans.exceptions.LoansException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.connect.api.ConnectAuditErrorCode;
import com.capitaworld.connect.api.ConnectLogAuditRequest;
import com.capitaworld.connect.api.ConnectResponse;
import com.capitaworld.connect.api.ConnectStage;
import com.capitaworld.connect.client.ConnectClient;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.NTBRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.service.fundseeker.corporate.FundSeekerInputRequestService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;

@RestController
@RequestMapping("/fundseeker_input_request")
public class FundSeekerInputRequestController {

    private static final Logger logger = LoggerFactory.getLogger(FundSeekerInputRequestController.class);

    private static final String SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG = "Something goes wrong while processing your Request.Please re-login again.";

    @Autowired
    private FundSeekerInputRequestService fundSeekerInputRequestService;

    @Autowired
    private ConnectClient connectClient;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws LoansException
    {
        try {
        	Long userId = fundSeekerInputRequestResponse.getUserId();
        	if(userId == null) {
        		fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));        		
        	}

        	logger.info("ENTER IN SAVE FUNDSEEKER INPUT REQUEST----------------------------------->");

            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
                logger.warn("userId/applicationId can not be empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            logger.info("GOING TO SAVE FUNDSEEKER INPUT REQUEST-------------USERID--->" + userId + "-------------APPLICATION ID --------------------->" + fundSeekerInputRequestResponse.getApplicationId());
            boolean result = fundSeekerInputRequestService.saveOrUpdate(fundSeekerInputRequestResponse);

        	if(result){
        		LoansResponse response = fundSeekerInputRequestService.invokeFraudAnalytics(fundSeekerInputRequestResponse);
        		//harshit's client
                connectClient.saveAuditLog(new ConnectLogAuditRequest(fundSeekerInputRequestResponse.getApplicationId(),
                        ConnectStage.ONE_FORM.getId(),fundSeekerInputRequestResponse.getUserId(),response.getMessage(), ConnectAuditErrorCode.ONFORM_SUBMIT.toString(),CommonUtils.BusinessType.EXISTING_BUSINESS.getId()));
        			return new ResponseEntity<LoansResponse>(response,HttpStatus.OK);
            } else {
                logger.info("FUNDSEEKER INPUT NOT SAVED");
                return new ResponseEntity<LoansResponse>(new LoansResponse("Oneform Not Saved", HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);
            }

        } catch (Exception e) {
            logger.error("Error while saving one form data : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> get(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = fundSeekerInputRequestResponse.getUserId();
        	if(userId == null) {
        		fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));        		
        	}
        	logger.info("Application Id for Getting one form============>{}",fundSeekerInputRequestResponse.getApplicationId());
        	//Commented by Akshay discussed with Hiren

//            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
//                logger.warn("userId/applicationId can not be empty");
//                return new ResponseEntity<LoansResponse>(
//                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
//            }

            return fundSeekerInputRequestService.get(fundSeekerInputRequestResponse);


        } catch (Exception e) {
            logger.error("Error while fetching one form data : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/get_director_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getDirectorDetail(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws LoansException
    {
        try
        {
            Long userId = fundSeekerInputRequestResponse.getUserId();
            if(userId == null) {
                fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
            }
            logger.info("Application Id for Getting director detail============>{}",fundSeekerInputRequestResponse.getApplicationId());

            return fundSeekerInputRequestService.getDirectorDetail(fundSeekerInputRequestResponse);


        } catch (Exception e) {
            logger.error("Error while fetching director detail : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/save_director_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveDirectorDetail(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws LoansException
    {
        try {
            Long userId = fundSeekerInputRequestResponse.getUserId();
            if(userId == null) {
                fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
            }

            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
                logger.warn("userId/applicationId can not be empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }


            return fundSeekerInputRequestService.saveOrUpdateDirectorDetail(fundSeekerInputRequestResponse);


        } catch (Exception e) {
            logger.error("Error while saving director detail : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping(value = "/match/{businessTypeId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> callMatchengine(@RequestBody Long applicationId,@PathVariable("businessTypeId") Integer businessTypeId,HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	logger.info("Application Id for Getting============>{}",applicationId);
        	//Commented by Akshay discussed with Hiren

//            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
//                logger.warn("userId/applicationId can not be empty");
//                return new ResponseEntity<LoansResponse>(
//                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
//            }

            LoansResponse callMatchEngineClient = fundSeekerInputRequestService.callMatchEngineClient(applicationId,userId,businessTypeId);
            logger.info("Response from Matchengine ==>{}",callMatchEngineClient.toString());
            return new ResponseEntity<LoansResponse>(callMatchEngineClient, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after Oneform Submit : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/match_ntb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> callMatchengineNTB(@RequestBody NTBRequest ntbRequest,HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	if(CommonUtils.isObjectListNull(ntbRequest.getDirectorId(),ntbRequest.getApplicationId(),ntbRequest.getBusineeTypeId())) {
        		logger.info("Director Id or Application Id or BusinessTypeId is NUll============>{}",ntbRequest.toString());
        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST,HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
        	}
        	ntbRequest.setUserId(userId);
        	logger.info("Application Id for Getting============>{}",ntbRequest.getApplicationId());
            LoansResponse callMatchEngineClient = fundSeekerInputRequestService.postDirectorBackground(ntbRequest);
            logger.info("Response from Matchengine ==>{}",callMatchEngineClient.toString());
            return new ResponseEntity<LoansResponse>(callMatchEngineClient, HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Calling Connect Client after Oneform Submit : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get_min_max_margin", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getMinMaxMargin(@RequestBody NTBRequest ntbRequest,HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	if(CommonUtils.isObjectNullOrEmpty(ntbRequest.getApplicationId()) || CommonUtils.isObjectNullOrEmpty(ntbRequest.getBusineeTypeId())) {
        		logger.info("Application Id OR BusinessTypeID is NUll============>{}");
        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST,HttpStatus.BAD_REQUEST.value()),
                        HttpStatus.OK);
        	}
        	logger.info("Application Id for Getting Margin============>{}"+ntbRequest.getApplicationId()+ "BusinessTypeID ====>{}"+ ntbRequest.getBusineeTypeId());
            ScoringModelReqRes scoringResponse = loanApplicationService.getMinMaxMarginByApplicationId(ntbRequest.getApplicationId(),ntbRequest.getBusineeTypeId());
            logger.info("Response from Scoring==>{}",scoringResponse.toString());
            return new ResponseEntity<LoansResponse>(new LoansResponse("Details successfully fetched",HttpStatus.OK.value(),scoringResponse), HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Fetching details for min-max Margin : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/save_one_form_uninform", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveUniformProductOneForm(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse , HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(fundSeekerInputRequestResponse.getApplicationId() == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	
//        	if(CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getIsGstCompleted()) || !fundSeekerInputRequestResponse.getIsGstCompleted()){
//        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.GST_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);	
//        	}
//        	
//        	if(CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getIsItrCompleted()) || !fundSeekerInputRequestResponse.getIsItrCompleted()){
//        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.ITR_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);	
//        	}
        	
        	fundSeekerInputRequestResponse.setUserId(userId);
        	LoansResponse eligibility = fundSeekerInputRequestService.saveOrUpdateForOnePagerEligibility(fundSeekerInputRequestResponse);
        	if(CommonUtils.isObjectNullOrEmpty(eligibility) || CommonUtils.isObjectNullOrEmpty(eligibility.getFlag()) || !eligibility.getFlag() || eligibility.getStatus() != 200){
        		return new ResponseEntity<LoansResponse>(eligibility,HttpStatus.OK);	
        	}
        		try {
        			ConnectResponse postOneForm = connectClient.postOneForm(fundSeekerInputRequestResponse.getApplicationId(), userId, CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId());
        			if (postOneForm != null) {
        				logger.info("postOneForm=======================>Client Connect Response Uniform Product=============>{}",
        						postOneForm.toString());
        				if(!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 4){
        					eligibility.setStatus(HttpStatus.METHOD_FAILURE.value());
        					eligibility.setMessage("Your request could not be processed now, please try again after sometime.");
        				}else if (!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 6) {
        					eligibility.setStatus(HttpStatus.BAD_REQUEST.value());
        					eligibility.setMessage("Not Eligibile from Matchengine");
        				}else if (!postOneForm.getProceed().booleanValue() && postOneForm.getStatus() == 500) {
        					eligibility.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        					eligibility.setMessage("Your request could not be refined now, please try again after sometime!");
        				} else {
        					eligibility.setStatus(HttpStatus.OK.value());
        					eligibility.setMessage("Successfully Matched");
        				}
        			}
        			return new ResponseEntity<LoansResponse>(eligibility,HttpStatus.OK);
        		}catch(Exception e){
        			logger.error("Exception :{}",e);
        			eligibility.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
					eligibility.setMessage("Your request could not be refined now, please try again after sometime!");
        			return new ResponseEntity<LoansResponse>(eligibility,HttpStatus.OK);
        		}
        } catch (Exception e) {
            logger.error("Error while Getting Oneform Details for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/one_form_uninform", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getUniformProductOneForm(@RequestBody Long applicationId , HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(applicationId == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.getDataForOnePagerOneForm(applicationId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Getting Oneform Details for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/verifyGST/{gstin}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LoansResponse> verifyGST(@PathVariable("gstin") String gstin,@RequestParam("gstReceipts") MultipartFile[] uploadingFiles,@RequestPart("requestedData") String requestedData,HttpServletRequest request)
            throws LoansException
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	
        	if(requestedData == null) {
        		logger.warn("Request Data is Null in verify GST Information for GST===={}",gstin);
      		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.GENERIC_ERROR_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
         	}
        	
        	Long applicationId = null;
        	try{
        		applicationId = Long.valueOf(requestedData);
            	if(applicationId == null) {
         		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.GENERIC_ERROR_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            	}        		
        	}catch(Exception e){
        		logger.error("Error Converting String to Long for ApplicationId : {}",e);
        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.GENERIC_ERROR_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
            return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.verifyGST(gstin, applicationId,userId,uploadingFiles), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Fetching details for min-max Margin : ",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/updateFlag/{flagValue}/{flagType}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> updateFlag(@RequestBody Long applicationId , @PathVariable("flagValue") Boolean flagValue,@PathVariable("flagType") Integer flagType,HttpServletRequest request)throws LoansException{
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(applicationId == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
            return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.updateFlag(applicationId, flagValue,flagType), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Updating Flag value for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/delete/{applicationId}/{mappingId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> deleteFile(@RequestBody List<Long> docIds , @PathVariable("applicationId") Long applicationId,@PathVariable("mappingId") Long mappingId,HttpServletRequest request)throws LoansException{
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(CommonUtils.isListNullOrEmpty(docIds)) {
        		logger.warn("docIds Must not be null or Empty====>{}",docIds);
     		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	
        	if(CommonUtils.isObjectNullOrEmpty(mappingId)) {
        		logger.warn("mappingId Must not be null or Empty====>{}",mappingId);
      		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
         	}
        	
        	if(CommonUtils.isObjectNullOrEmpty(applicationId)) {
        		logger.warn("applicationId Must not be null or Empty====>{}",applicationId);
      		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
         	}
            return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.deleteDocument(applicationId, docIds, mappingId), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Deleting Document for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/reset", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> resetApplicationUniform(@RequestBody ConnectResponse connectResponse ,HttpServletRequest request)throws LoansException{
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.UNAUTHORIZED_USER_PLEASE_RE_LOGIN_AND_TRY_AGAIN, HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	
        	if(CommonUtils.isObjectNullOrEmpty(connectResponse.getApplicationId())) {
        		logger.warn("applicationId Must not be null or Empty====>{}",connectResponse.getApplicationId());
      		   return new ResponseEntity<LoansResponse>(new LoansResponse(SOMETHING_GOES_WRONG_WHILE_PROCESSING_YOUR_REQUEST_PLEASE_RE_LOGIN_AGAIN_MSG, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
         	}
        	
        	connectResponse.setUserId(userId);
            return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.resetUniformApplication(connectResponse), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Deleting Document for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
}
