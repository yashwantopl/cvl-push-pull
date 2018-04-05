package com.capitaworld.service.loans.controller.scoring;

import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
