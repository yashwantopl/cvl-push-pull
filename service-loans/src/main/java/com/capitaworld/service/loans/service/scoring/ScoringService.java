package com.capitaworld.service.loans.service.scoring;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.LoansResponse;
import com.capitaworld.service.loans.model.score.ScoringRequestLoans;
import com.capitaworld.service.scoring.exception.ScoringException;
import com.capitaworld.service.scoring.model.GenericCheckerReqRes;
import com.capitaworld.service.scoring.model.scoringmodel.ScoringModelReqRes;

public interface ScoringService {

    public ResponseEntity<LoansResponse> calculateScoring(ScoringRequestLoans scoringRequestLoans);

    public ResponseEntity<LoansResponse> calculateMudraScoringList(List<ScoringRequestLoans> scoringRequestLoansList);

    public ResponseEntity<LoansResponse> calculateScoringTest(ScoringRequestLoans scoringRequestLoans);
    
	public Workbook  readScoringExcel(MultipartFile multipartFile ) throws IllegalStateException, InvalidFormatException,IOException , LoansException;
	
	public Workbook generateScoringExcel(List<LoansResponse> list) throws LoansException ;

	/////////

    public ScoringModelReqRes getScoringModelTempList(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes saveScoringModelTemp(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes getScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes);

    public ScoringModelReqRes saveScoringModelTempDetail(ScoringModelReqRes scoringModelReqRes);
    
    public ScoringModelReqRes getScoringModelMasterList(ScoringModelReqRes scoringModelReqRes);
    
    public ScoringModelReqRes getScoringModelMasterDetail(ScoringModelReqRes scoringModelReqRes);

    public Integer getFinYear(Long applicationId);

	public List<GenericCheckerReqRes> sendToCheckerEBLR(List<GenericCheckerReqRes> genericCheckerReqResList,
			Long userId) throws ScoringException;

	public List<GenericCheckerReqRes> sendToChecker(List<GenericCheckerReqRes> genericCheckerReqResList, Long userId) throws ScoringException;
}
