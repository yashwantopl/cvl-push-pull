package com.capitaworld.service.loans.controller.fundseeker.corporate;

import javax.servlet.http.HttpServletRequest;

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

    @Autowired
    private FundSeekerInputRequestService fundSeekerInputRequestService;

    @Autowired
    private ConnectClient connectClient;

    @Autowired
    private LoanApplicationService loanApplicationService;

    @RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> save(@RequestBody FundSeekerInputRequestResponse fundSeekerInputRequestResponse,HttpServletRequest request)
            throws Exception
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
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
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
//                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
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
            throws Exception
    {
        try {
            Long userId = fundSeekerInputRequestResponse.getUserId();
            if(userId == null) {
                fundSeekerInputRequestResponse.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
            }

            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
                logger.warn("userId/applicationId can not be empty");
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	logger.info("Application Id for Getting============>{}",applicationId);
        	//Commented by Akshay discussed with Hiren

//            if (CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getUserId()) || CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getApplicationId())) {
//                logger.warn("userId/applicationId can not be empty");
//                return new ResponseEntity<LoansResponse>(
//                        new LoansResponse("Invalid Request", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(
                           new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
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
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(fundSeekerInputRequestResponse.getApplicationId() == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse("Something goes wrong while processig your Request. Please re-login again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	
        	if(CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getIsGstCompleted()) || !fundSeekerInputRequestResponse.getIsGstCompleted()){
        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.GST_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);	
        	}
        	
        	if(CommonUtils.isObjectNullOrEmpty(fundSeekerInputRequestResponse.getIsItrCompleted()) || !fundSeekerInputRequestResponse.getIsItrCompleted()){
        		return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.ITR_VALIDATION_ERROR_MSG,HttpStatus.BAD_REQUEST.value()),HttpStatus.OK);	
        	}
        	
        	fundSeekerInputRequestResponse.setUserId(userId);
        	return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.saveOrUpdateForOnePagerEligibility(fundSeekerInputRequestResponse),HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Getting Oneform Details for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/one_form_uninform", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getUniformProductOneForm(@RequestBody Long applicationId , HttpServletRequest request)
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(applicationId == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse("Something goes wrong while processig your Request. Please re-login again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	return fundSeekerInputRequestService.getDataForOnePagerOneForm(applicationId);
        } catch (Exception e) {
            logger.error("Error while Getting Oneform Details for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/post_itr_upload", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> postItrUpload(@RequestBody Long applicationId , HttpServletRequest request)
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(applicationId == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse("Something goes wrong while processig your Request. Please re-login again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
        	return fundSeekerInputRequestService.getDataForOnePagerOneForm(applicationId);
        } catch (Exception e) {
            logger.error("Error while Getting Oneform Details for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/verifyGST/{gstin}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<LoansResponse> verifyGST(@PathVariable("gstin") String gstin,@RequestParam("gstReceipts") MultipartFile[] uploadingFiles,@RequestPart("requestedData") String requestedData,HttpServletRequest request)
            throws Exception
    {
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
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
    public ResponseEntity<LoansResponse> updateFlag(@RequestBody Long applicationId , @PathVariable("flagValue") Boolean flagValue,@PathVariable("flagType") Integer flagType,HttpServletRequest request)throws Exception{
        try
        {
        	Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);
        	if(userId == null) {
        		   return new ResponseEntity<LoansResponse>(new LoansResponse("Unauthorized User! Please Re-login and try again.", HttpStatus.UNAUTHORIZED.value()), HttpStatus.OK);
        	}
        	if(applicationId == null) {
     		   return new ResponseEntity<LoansResponse>(new LoansResponse("Something goes wrong while processig your Request. Please re-login again.", HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
        	}
            return new ResponseEntity<LoansResponse>(fundSeekerInputRequestService.updateFlag(applicationId, flagValue,flagType), HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while Updating Flag value for Uniform Product : ",e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
}
