package com.capitaworld.service.loans.controller.scoring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.scoring.MCLRReqRes;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.ScoringResponse;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/score")
public class ScoringController {

    private static final Logger logger = LoggerFactory.getLogger(ScoringController.class);

    @Autowired
    private ScoringService scoringService;


    @RequestMapping(value = "/calculate_score", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScore(@RequestBody ScoringRequestLoans scoringRequestLoans) {

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans)
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getApplicationId())
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getScoringModelId())
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFpProductId())
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getUserId()))
        {
            logger.warn("request parameter is null or empty");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("request parameter is null or empty.", HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        return scoringService.calculateScoring(scoringRequestLoans);
    }


    @RequestMapping(value = "/calculate_score/corporate_existing_list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScoreExisting(@RequestBody List<ScoringRequestLoans> scoringRequestLoansList) {

        return scoringService.calculateExistingBusinessScoringList(scoringRequestLoansList);
    }

    @RequestMapping(value = "/calculate_score/retail_pl_list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScoreRetailPL(@RequestBody List<ScoringRequestLoans> scoringRequestLoansList) {

        return scoringService.calculateRetailPersonalLoanScoringList(scoringRequestLoansList);
    }
    
    @PostMapping(value = "/calculate_score/retail_hl_list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScoreRetailHL(@RequestBody List<ScoringRequestLoans> scoringRequestLoansList) {
        return scoringService.calculateRetailHomeLoanScoringList(scoringRequestLoansList);
    }
    
    @PostMapping(value = "/calculate_score/retail_hl_list_coapplicant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScoreRetailHLForCoApplicant(@RequestBody List<ScoringRequestLoans> scoringRequestLoansList) {
        return scoringService.calculateRetailHomeLoanScoringListForCoApplicant(scoringRequestLoansList);
    }

    @RequestMapping(value = "/calculate_score/corporate/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScoreTest(@RequestBody ScoringRequestLoans scoringRequestLoans) {

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getScoringModelId()))
        {
            logger.warn("request parameter is null or empty");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("request parameter is null or empty.", HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        return scoringService.calculateScoringTest(scoringRequestLoans);
    }
    @RequestMapping(value = "/getScoreExcel", method = RequestMethod.POST,consumes= MediaType.MULTIPART_FORM_DATA_VALUE  )
	public LoansResponse  uploadExcel( @RequestPart("file") MultipartFile multipartFiles) {
		// Code for read Score Excel
			logger.info("-----------------In uploadExcel() ----------------multipartfile size---"+multipartFiles.getSize());
            FileOutputStream fileOutputStream = null;
			LoansResponse loansResponse= null;
	        try {
	        	
	        	File file=new File(CommonUtils.SCORING_EXCEL);

	        	fileOutputStream =new FileOutputStream(file);
				logger.info("-----------------In reading excel file() ----------------");
	        	scoringService.readScoringExcel(multipartFiles).write(fileOutputStream);
	        	
	        	fileOutputStream.flush();
	        	fileOutputStream.close();
	        	logger.info("-----------------Sucessfullly  reading excel file() ---------------- file lenght---- " +file.length()   );
	             loansResponse =new LoansResponse("Sucessfull created .", HttpStatus.OK.value());
	           
	             loansResponse.setContentInBytes(FileUtils.readFileToByteArray(file));
	  	       return loansResponse;	             
	          }catch (NullPointerException |IllegalStateException| InvalidFormatException  |IOException |LoansException e) {
	        	 return  new LoansResponse(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
	        }
	        finally {
                    try {
                        if(fileOutputStream != null){
                            fileOutputStream.close();
                        }
                    }catch (IOException e){
                        logger.error(CommonUtils.EXCEPTION,e);
                    }
            }
	}


	////////////////


    @RequestMapping(value = "/get_scoring_model_tmp_list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelTempList(@RequestBody ScoringModelReqRes scoringModelReqRes,HttpServletRequest request) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);

            logger.info("userId ============> "+userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelTempList(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model list :  ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/save_scoring_model_temp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> saveScoringModelTemp(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.saveScoringModelTemp(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while saving scoring model : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get_scoring_model_temp_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelTempDetail(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelTempDetail(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model detail :  ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/save_scoring_model_temp_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> saveScoringModelTempDetail(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.saveScoringModelTempDetail(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while saving scoring model detail : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/sendToChecker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenericCheckerReqRes> > sendToChecker(@RequestBody List<GenericCheckerReqRes> genericCheckerReqResList, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
    	 logger.info ("==================Enter in sendToChecker(){} ================ genericCheckerReqResList size ==> " + genericCheckerReqResList.size());
        try {
        	Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);

            List<GenericCheckerReqRes>  genericCheckerReqRes=scoringService.sendToChecker(genericCheckerReqResList , userId);
            logger.info ("==================Exit from sendToChecker(){} ================ genericCheckerReqRes List  Size ==> " , genericCheckerReqRes.size());
            return new ResponseEntity<List<GenericCheckerReqRes> >(genericCheckerReqRes ,HttpStatus.OK);
        }
        catch (Exception e)
        {
        	List<GenericCheckerReqRes>  res= new ArrayList<GenericCheckerReqRes >();
        	GenericCheckerReqRes reqres= new GenericCheckerReqRes();
        	reqres.setActionFlag(false);
        	res.add(reqres);
            logger.error("Error while saving scoring model detail : ",e);
            return new ResponseEntity<List<GenericCheckerReqRes> >(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/get_scoring_model_master_list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelMasterList(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);

            logger.info("userId ============> "+userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelMasterList(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model list : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/get_scoring_model_master_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelMasterDetail(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelMasterDetail(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model detail : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/sendToCheckerMCLR", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenericCheckerReqRes>> sendToCheckerMCLR(@RequestBody List<GenericCheckerReqRes> genericCheckerReqResList, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
        logger.info("==================Enter in sendToChecker(){} ================ genericCheckerReqResList size ==> ",genericCheckerReqResList.size());
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            List<GenericCheckerReqRes> genericCheckerReqRes = scoringService.sendToCheckerMCLR(genericCheckerReqResList, userId);
            logger.info("==================Exit from sendToChecker(){} ================ genericCheckerReqRes List  Size ==> ", genericCheckerReqRes.size());
            return new ResponseEntity<List<GenericCheckerReqRes>>(genericCheckerReqRes, HttpStatus.OK);
        } catch (Exception e) {
            List<GenericCheckerReqRes> res = new ArrayList<GenericCheckerReqRes>();
            GenericCheckerReqRes reqres = new GenericCheckerReqRes();
            reqres.setActionFlag(false);
            res.add(reqres);
            logger.error("Error while saving scoring model detail : ", e);
            return new ResponseEntity<List<GenericCheckerReqRes>>(res, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/create_job", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> createJob(@RequestBody MCLRReqRes mclrReqRes, HttpServletRequest httpRequest) {

        try {
            mclrReqRes.setUserId((Long) httpRequest.getAttribute(CommonUtils.USER_ID));
            ScoringResponse scoringResponse = scoringService.createJob(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse, HttpStatus.OK);
        } catch (Exception e) {
            ScoringResponse res = new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting mclr history", e);
            return new ResponseEntity<ScoringResponse>(res, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get_mclr_history", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> getMclrHistory(@RequestBody MCLRReqRes mclrReqRes, HttpServletRequest request) {
        try {
            ScoringResponse scoringResponse = scoringService.getMCLRHistoryDetail(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse, HttpStatus.OK);
        } catch (Exception e) {
            ScoringResponse res = new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting mclr history", e);
            return new ResponseEntity<ScoringResponse>(res, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getLatestMCLRDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> getLatestMCLRDetails(@RequestBody MCLRReqRes mclrReqRes,HttpServletRequest request) {
        try {
            ScoringResponse scoringResponse = scoringService.getLatestMCLRDetails(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse, HttpStatus.OK);
        } catch (Exception e) {
            ScoringResponse res = new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting mclr history", e);
            return new ResponseEntity<ScoringResponse>(res, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/save_mclr", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> saveMCLR(@RequestBody MCLRReqRes mclrReqRes,
                                                    HttpServletRequest request) throws RatingException {

        try {
            mclrReqRes.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
            ScoringResponse scoringResponse = scoringService.saveMCLRDetails(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse, HttpStatus.OK);
        } catch (Exception e) {
            ScoringResponse res = new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting mclr history", e);
            return new ResponseEntity<ScoringResponse>(res, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getMCLRForChecker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> getMCLRForChecker(@RequestBody MCLRReqRes mclrReqRes, HttpServletRequest request) {
        try {
            mclrReqRes.setUserId((Long) request.getAttribute(CommonUtils.USER_ID));
            ScoringResponse scoringResponse = scoringService.getMCLRForChecker(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse, HttpStatus.OK);
        } catch (Exception e) {
            ScoringResponse res = new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting mclr history", e);
            return new ResponseEntity<ScoringResponse>(res, HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/getEffectiveMCLR", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringResponse> getEffectiveMCLR(@RequestBody MCLRReqRes mclrReqRes) {

        try {
            if(com.capitaworld.service.scoring.utils.CommonUtils.isObjectNullOrEmpty(mclrReqRes)){
                ScoringResponse res=new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
                return new ResponseEntity<ScoringResponse>(res,HttpStatus.OK);
            }
            ScoringResponse scoringResponse= scoringService.getEffectiveMCLRDetails(mclrReqRes);
            return new ResponseEntity<ScoringResponse>(scoringResponse,HttpStatus.OK);

        } catch (Exception e) {
            ScoringResponse res=new ScoringResponse(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.INTERNAL_SERVER_ERROR.value());
            logger.error("Error while getting effective mclr ", e);
            return new ResponseEntity<ScoringResponse>(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/getConcessionDetails", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> getRetailConcessionDetails(@RequestBody ScoringRequestLoans scoringRequestLoans) {
        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getApplicationId())) {
            logger.warn("Request Data Can Not Find Appplication ID Is Null Or Empty======>");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse(CommonUtils.INVALID_REQUEST, HttpStatus.OK.value()), HttpStatus.OK);
        }
        try {
        	  Object[] concesssionResponse = scoringService.getRetailConcessionDetails(scoringRequestLoans, null, null, null);
        	  logger.info("concesssionResponse ===>concesssionResponse=======>"+concesssionResponse);
        	  	LoansResponse loanResponse = new LoansResponse();
            
        	if(!CommonUtils.isObjectNullOrEmpty(concesssionResponse)){
        		loanResponse.setData(concesssionResponse);
        		loanResponse.setMessage("SuccessFully get data");
            }else {
            	loanResponse.setMessage("No Data Found");
            }
        	
        	loanResponse.setStatus(HttpStatus.OK.value());
            return new ResponseEntity<LoansResponse>(loanResponse, HttpStatus.OK);
            
        } catch (Exception e) {
            logger.error("Thow exception while getting concession details",e);
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("Failure", HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
           }
        } 
}

