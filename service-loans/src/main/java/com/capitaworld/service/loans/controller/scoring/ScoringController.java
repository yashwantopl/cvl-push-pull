package com.capitaworld.service.loans.controller.scoring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.matchengine.model.ProposalMappingRequest;
import com.capitaworld.service.rating.exception.RatingException;
import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.RatingResponse;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
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


    @RequestMapping(value = "/calculate_score/corporate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoansResponse> calculateScore(@RequestBody ScoringRequestLoans scoringRequestLoans) {

        if (CommonUtils.isObjectNullOrEmpty(scoringRequestLoans)
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getApplicationId())
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getScoringModelId())
                || CommonUtils.isObjectNullOrEmpty(scoringRequestLoans.getFpProductId()))
        {
            logger.warn("request parameter is null or empty");
            return new ResponseEntity<LoansResponse>(
                    new LoansResponse("request parameter is null or empty.", HttpStatus.BAD_REQUEST.value()),
                    HttpStatus.OK);
        }
        return scoringService.calculateScoring(scoringRequestLoans);
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
			LoansResponse loansResponse= null;
	        try {
	        	
	        	File file=new File(CommonUtils.SCORING_EXCEL);
	        	
	        	FileOutputStream fileOutputStream =new FileOutputStream(file);
				logger.info("-----------------In reading excel file() ----------------");
	        	scoringService.readScoringExcel(multipartFiles).write(fileOutputStream);
	        	
	        	fileOutputStream.flush();
	        	fileOutputStream.close();
	        	logger.info("-----------------Sucessfullly  reading excel file() ---------------- file lenght---- " +file.length()   );
	             loansResponse =new LoansResponse("Sucessfull created .", HttpStatus.OK.value());
	           
	             loansResponse.setContentInBytes(FileUtils.readFileToByteArray(file));
	  	       return loansResponse;	             
	          }catch (NullPointerException |IllegalStateException| InvalidFormatException  |IOException |LoansException e) {
	        	 return   loansResponse = new LoansResponse(e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR.value());
	        }
	}


	////////////////


    @RequestMapping(value = "/get_scoring_model_list", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelList(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);;
            scoringModelReqRes.setUserId(userId);
            /*scoringModelReqRes.setUserId(1l);*/

            logger.info("userId ============> "+userId);
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelList(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model list");
            e.printStackTrace();
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/save_scoring_model", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> saveScoringModel(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);;
            scoringModelReqRes.setUserId(userId);
            /*scoringModelReqRes.setUserId(1l);*/
            ScoringModelReqRes scoringModelReqResNew=scoringService.saveScoringModel(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while saving scoring model");
            e.printStackTrace();
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/get_scoring_model_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> getScoringModelDetail(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);;
            scoringModelReqRes.setUserId(userId);
           /* scoringModelReqRes.setUserId(1l);*/
            ScoringModelReqRes scoringModelReqResNew=scoringService.getScoringModelDetail(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while getting scoring model detail");
            e.printStackTrace();
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/save_scoring_model_detail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ScoringModelReqRes> saveScoringModelDetail(@RequestBody ScoringModelReqRes scoringModelReqRes, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {

        try {
            Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);;
            scoringModelReqRes.setUserId(userId);
            /*scoringModelReqRes.setUserId(1l);*/
            ScoringModelReqRes scoringModelReqResNew=scoringService.saveScoringModelDetail(scoringModelReqRes);
            return new ResponseEntity<ScoringModelReqRes>(scoringModelReqResNew,HttpStatus.OK);
        }
        catch (Exception e)
        {
            // TODO: handle exception
            ScoringModelReqRes res=new ScoringModelReqRes(com.capitaworld.service.scoring.utils.CommonUtils.SOMETHING_WENT_WRONG,HttpStatus.BAD_REQUEST.value());
            logger.error("Error while saving scoring model detail");
            e.printStackTrace();
            return new ResponseEntity<ScoringModelReqRes>(res,HttpStatus.OK);
        }
    }
    
    @RequestMapping(value = "/sendToChecker", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GenericCheckerReqRes> > sendToChecker(@RequestBody List<GenericCheckerReqRes> genericCheckerReqResList, HttpServletRequest httpRequest, HttpServletRequest request, @RequestParam(value = "clientId", required = false) Long clientId) throws RatingException {
    	 logger.info ("==================Enter in sendToChecker(){} ================ genericCheckerReqResList size ==> " + genericCheckerReqResList.size());
        try {
        	Long userId =  (Long) request.getAttribute(CommonUtils.USER_ID);
            
            /*scoringModelReqRes.setUserId(1l);*/
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
            logger.error("Error while saving scoring model detail");
            e.printStackTrace();
            return new ResponseEntity<List<GenericCheckerReqRes> >(res,HttpStatus.OK);
        }
    }
}
