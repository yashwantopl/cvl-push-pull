package com.opl.service.loans.service.irr;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.rating.model.FinancialInputRequest;
import com.opl.mudra.api.rating.model.QualitativeInputSheetManuRequest;
import com.opl.mudra.api.rating.model.QualitativeInputSheetServRequest;
import com.opl.mudra.api.rating.model.QualitativeInputSheetTradRequest;
import com.opl.mudra.api.rating.model.RatingResponse;
import com.opl.service.loans.domain.fundseeker.corporate.CreditRatingCompanyDetail;

public interface IrrService {

	public  FinancialInputRequest cmaIrrMappingService(Long userId, Long aplicationId,String industry,Long denom, Long proposalMapId) throws LoansException;
	
	public  FinancialInputRequest coActIrrMappingService(Long userId, Long aplicationId,String industry,Long denom) throws LoansException;
	
	public QualitativeInputSheetManuRequest qualitativeInputServiceManu(Long aplicationId, Long userId, Integer productId, Boolean isCmaUploaded, Boolean isCoActUploaded,Double industryRiskScore,Long denom, Long proposalMapId) throws LoansException;
	
	public QualitativeInputSheetServRequest qualitativeInputServiceService(Long aplicationId, Long userId, Integer productId,Boolean isCmaUploaded, Boolean isCoActUploaded,Long denom, Long proposalMapId) throws LoansException;
	
	public QualitativeInputSheetTradRequest qualitativeInputServiceTrading(Long aplicationId, Long userId, Integer productId,Boolean isCmaUploaded, Boolean isCoActUploaded, Long denom, Long proposalMapId) throws LoansException;
	
//	public ResponseEntity<RatingResponse> calculateIrrRating(Long applicationId,Long userId);

	public ResponseEntity<RatingResponse> calculateIrrRating(Long applicationId,Long userId, Long proposalId);

	public List<CreditRatingCompanyDetail> getCompanyDetails(String companyName);

	public List<CreditRatingCompanyDetail> getAllCompanyDetail();
}
