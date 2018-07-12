package com.capitaworld.service.loans.service.scoring;

import java.io.IOException;

import java.util.List;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;

public interface ScoringService {

    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans);

    public ResponseEntity<LoansResponse> calculateExistingBusinessScoring(ScoringRequestLoans scoringRequestLoans);

    public ResponseEntity<LoansResponse> calculateNTBScoring(ScoringRequestLoans scoringRequestLoans, PrimaryCorporateDetail primaryCorporateDetail);

    //////////////

    public ResponseEntity<LoansResponse> calculateScoringTest(ScoringRequestLoans scoringRequestLoans);
    
	public Workbook  readScoringExcel(MultipartFile multipartFile ) throws IllegalStateException, InvalidFormatException,IOException , LoansException;
	
	public Workbook generateScoringExcel(List<LoansResponse> list) throws LoansException ;

	/////////

    public ScoringModelReqRes getScoringModelList(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes saveScoringModel(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes getScoringModelDetail(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes saveScoringModelDetail(ScoringModelReqRes scoringModelReqRes);
    
    public List<GenericCheckerReqRes> sendToChecker(List <GenericCheckerReqRes> genericCheckerReqRes , Long userId)  throws ScoringException ;
}
