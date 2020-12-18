package com.opl.service.loans.service.scoring;


import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoansResponse;
import com.opl.mudra.api.loans.model.score.ScoringRequestLoans;
import com.opl.mudra.api.loans.model.score.ScoringResponse;
import com.opl.mudra.api.scoring.exception.ScoringException;
import com.opl.mudra.api.scoring.model.GenericCheckerReqRes;
import com.opl.mudra.api.scoring.model.scoringmodel.ScoringModelReqRes;
import com.opl.mudra.api.utils.scoring.MCLRReqRes;

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
	
    public ScoringModelReqRes inactivateScoringDetails(ScoringModelReqRes scoringModelReqRes);

	public ScoringModelReqRes getScoringHistoryDetails(ScoringModelReqRes scoringModelReqRes);

	public ScoringResponse getMCLRHistoryDetail(MCLRReqRes mclrReqRes);

	public ScoringResponse getLatestMCLRDetails(MCLRReqRes mclrReqRes);

}
