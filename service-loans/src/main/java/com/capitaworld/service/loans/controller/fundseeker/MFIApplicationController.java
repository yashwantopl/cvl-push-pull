package com.capitaworld.service.loans.controller.fundseeker;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.micro_finance.*;
import com.capitaworld.service.loans.service.fundseeker.microfinance.MfiApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mfi")
public class MFIApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(MFIApplicationController.class);

    @Autowired
    private MfiApplicationService mfiApplicationService;

    /**
     * save Aadhar detail For create new Application in MFI Application
     * @param aadharDetailsReq
     * @param request
     * @return
     */
    @PostMapping(value = "/saveAdharDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveAdharDetails(@RequestBody AadharDetailsReq aadharDetailsReq, HttpServletRequest request) {
        try {
            // request must not be null
            CommonDocumentUtils.startHook(logger, "save");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            aadharDetailsReq.setUserId(userId);
            mfiApplicationService.saveOrUpdateAadharDetails(aadharDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving save Adhar Details Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getAadharDetails/{applicationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getAadharDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
        try {
            // request must not be null
            CommonDocumentUtils.startHook(logger, "Get Aadhar");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            AadharDetailsReq aadharDetailsByAppId = mfiApplicationService.getAadharDetailsByAppId(applicationId);
            CommonDocumentUtils.endHook(logger, "Get Aadhar");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Aadhar details.", HttpStatus.OK.value(),aadharDetailsByAppId),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Get Adhar Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @PostMapping(value = "/savePersonalDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<LoansResponse> savePersonalDetails(@RequestBody PersonalDetailsReq personalDetailsReq,
			HttpServletRequest request) {
		try {
			logger.info("service call savepersonalDetails----------->");
			CommonDocumentUtils.startHook(logger, "save");
			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

			if (userId == null) {
				logger.warn("userId  can not be empty ==>" + userId);
				return new ResponseEntity<LoansResponse>(
						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
			}
			mfiApplicationService.saveOrUpdatePersonalDetails(personalDetailsReq);
			CommonDocumentUtils.endHook(logger, "save");
			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
					HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Error while saving save Personal Details Details ==>", e);
			return new ResponseEntity<LoansResponse>(
					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
					HttpStatus.OK);
		}
	}
	
    @GetMapping(value = "/getPersonalDetails/{applicationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getPersonalDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
        try {

        	CommonDocumentUtils.startHook(logger, "Get Personal Details");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            PersonalDetailsReq personalrDetailsByAppId = mfiApplicationService.getPersonalDetailsAppId(applicationId);
            CommonDocumentUtils.endHook(logger, "Get Personal Details");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Personal details.", HttpStatus.OK.value(),personalrDetailsByAppId),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Get Adhar Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
    @PostMapping(value = "/saveProjectDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
   	public ResponseEntity<LoansResponse> saveProjectDetails(@RequestBody ProjectDetailsReq projectDetailsReq,
   			HttpServletRequest request) {
   		try {
   			logger.info("service call saveProjectDetails----------->");
   			CommonDocumentUtils.startHook(logger, "save");
   			Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

   			if (userId == null) {
   				logger.warn("userId  can not be empty ==>" + userId);
   				return new ResponseEntity<LoansResponse>(
   						new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
   			}
   			mfiApplicationService.saveOrUpdateProjectDetails(projectDetailsReq);
   			CommonDocumentUtils.endHook(logger, "save");
   			return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
   					HttpStatus.OK);

   		} catch (Exception e) {
   			logger.error("Error while saving save Project Details Details ==>", e);
   			return new ResponseEntity<LoansResponse>(
   					new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
   					HttpStatus.OK);
   		}
   	}
    
    @RequestMapping(value = "/getMfiDetails/{applicationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  	public ResponseEntity<LoansResponse> getMfiApplicantIncomeDetails(@PathVariable("applicationId") Long applicationId){
  		logger.info("ENTER HERE MFI GET APPLIANT DETAILS====={}======{}======>" + applicationId);
  		try {
  			List<MfiReqResponse> list = mfiApplicationService.getMfiApplicantDetails(applicationId);
  			logger.info("GET DETAIILS FOR MFI APPLICANTS DETAILS PURPOSE----->" + list.size());
  			return new ResponseEntity<LoansResponse>(new LoansResponse("SUCCESSFULLY RETRIVE MFI RESPONSE", HttpStatus.OK.value(), list),HttpStatus.OK);	
  		} catch (Exception e) {
  			logger.error("THROW EXCEPTION WHILE GETTING MFI DETAILS ====={}======{}====>",e);
  			return new ResponseEntity<LoansResponse>(new LoansResponse("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.INTERNAL_SERVER_ERROR);
  		}
    }



    @PostMapping(value = "/saveBankDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> saveBankDetails(@RequestBody MfiBankDetailsReq mfiBankDetailsReq, HttpServletRequest request) {
        try {
            logger.info("service call saveProjectDetails----------->");
            CommonDocumentUtils.startHook(logger, "save");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(
                        new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }
            mfiApplicationService.saveOrUpdateBankDetails(mfiBankDetailsReq);
            CommonDocumentUtils.endHook(logger, "save");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Saved.", HttpStatus.OK.value()),
                    HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while saving save Project Details Details ==>", e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.OK);
        }
    }

    @GetMapping(value = "/getBankDetails/{applicationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getBankDetails(@PathVariable("applicationId") Long applicationId, HttpServletRequest request) {
        try {

            CommonDocumentUtils.startHook(logger, "Get Bank Details");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            MfiBankDetailsReq mfiBankDetailsReq = mfiApplicationService.fetchBankDetail(applicationId);
            CommonDocumentUtils.endHook(logger, "Get Bank Details");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch Bank details.", HttpStatus.OK.value(),mfiBankDetailsReq),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Get Bank Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    @GetMapping(value = "/getAllApplicantDetails/{applicationId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getAllApplicantDetails(@PathVariable("applicationId") Long applicationId,HttpServletRequest request) {
        try {

            CommonDocumentUtils.startHook(logger, "Get Bank Details");
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            if (userId == null) {
                logger.warn("userId  can not be empty ==>" + userId);
                return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.BAD_REQUEST.value()), HttpStatus.OK);
            }

            List<MfiApplicantDetailsReq> allApplicantDetails = mfiApplicationService.getAllApplicantDetails(applicationId);
            CommonDocumentUtils.endHook(logger, "Get Bank Details");
            return new ResponseEntity<LoansResponse>(new LoansResponse("Successfully Fetch All Applicant details.", HttpStatus.OK.value(),allApplicantDetails),HttpStatus.OK);

        } catch (Exception e) {
            logger.error("Error while Get All applicant Details ==>", e);
            return new ResponseEntity<LoansResponse>(new LoansResponse(CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR.value()),HttpStatus.OK);
        }
    }
    
}
