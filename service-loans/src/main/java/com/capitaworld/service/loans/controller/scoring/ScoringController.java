package com.capitaworld.service.loans.controller.scoring;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;

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
    @RequestMapping(value = "/getScoreExcel", method = RequestMethod.POST,produces =MediaType.MULTIPART_FORM_DATA_VALUE,consumes= MediaType.MULTIPART_FORM_DATA_VALUE  )
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
	        	//fileOutputStream.close();
	        	logger.info("-----------------Sucessfullly  reading excel file() ---------------- file lenght---- " +file.length()   );
	             loansResponse =new LoansResponse("Sucessfull created .", HttpStatus.OK.value());
	           
	             loansResponse.setContentInBytes(FileUtils.readFileToByteArray(file));
	  	       return loansResponse;	             
	          }catch (NullPointerException |IllegalStateException| InvalidFormatException  |IOException |LoansException e) {
	        	 return   loansResponse = new LoansResponse(e.getMessage(),
							HttpStatus.INTERNAL_SERVER_ERROR.value());
	        }
	}
}
