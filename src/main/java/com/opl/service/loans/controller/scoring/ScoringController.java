package com.opl.service.loans.controller.scoring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.score.ScoringRequestLoans;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.rating.exception.RatingException;
import com.opl.mudra.api.scoring.model.GenericCheckerReqRes;
import com.opl.mudra.api.scoring.model.scoringmodel.ScoringModelReqRes;
import com.opl.service.loans.service.scoring.ScoringService;

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

        return scoringService.calculateMudraScoringList(scoringRequestLoansList);
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while saving scoring model detail : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
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
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model detail : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
    
    @RequestMapping(value = "/sendToCheckerEBLR", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenericCheckerReqRes>> sendToCheckerEBLR(@RequestBody List<GenericCheckerReqRes> genericCheckerReqResList, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
        logger.info("==================Enter in sendToChecker(){} ================ genericCheckerReqResList size ==> ",genericCheckerReqResList.size());
        try {
            Long userId = (Long) request.getAttribute(CommonUtils.USER_ID);

            List<GenericCheckerReqRes> genericCheckerReqRes = scoringService.sendToCheckerEBLR(genericCheckerReqResList, userId);
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

    @RequestMapping(value = "/inactive_scoring_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> inactivateScoringDetails(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);

            logger.info("userId ============> "+userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.inactivateScoringDetails(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while inactivateScoringDetails : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/get_history_scoring_details", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringHistoryDetails(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            scoringModelReqRes.setUserId(userId);

            logger.info("userId ============> "+userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringHistoryDetails(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            ScoringModelReqRes res=new ScoringModelReqRes(com.opl.mudra.api.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while inactivateScoringDetails : ",e);
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
}
