package com.capitaworld.service.loans.service.irr;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CreditRatingCompanyDetail;
import com.capitaworld.service.loans.exceptions.LoansException;
import org.springframework.http.ResponseEntity;

import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetManuRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetServRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetTradRequest;
import com.capitaworld.service.rating.model.RatingResponse;

import java.util.List;

public interface IrrService {

	public  FinancialInputRequest cmaIrrMappingService(Long userId, Long aplicationId,String industry,Long denom) throws LoansException;
	
	public  FinancialInputRequest coActIrrMappingService(Long userId, Long aplicationId,String industry,Long denom) throws LoansException;
	
	public QualitativeInputSheetManuRequest qualitativeInputServiceManu(Long aplicationId, Long userId, Integer productId, Boolean isCmaUploaded, Boolean isCoActUploaded,Double industryRiskScore,Long denom) throws LoansException;
	
	public QualitativeInputSheetServRequest qualitativeInputServiceService(Long aplicationId, Long userId, Integer productId,Boolean isCmaUploaded, Boolean isCoActUploaded,Long denom) throws LoansException;
	
	public QualitativeInputSheetTradRequest qualitativeInputServiceTrading(Long aplicationId, Long userId, Integer productId,Boolean isCmaUploaded, Boolean isCoActUploaded, Long denom) throws LoansException;
	
	public ResponseEntity<RatingResponse> calculateIrrRating(Long applicationId,Long userId);

	public List<CreditRatingCompanyDetail> getCompanyDetails(String companyName);

	public List<CreditRatingCompanyDetail> getAllCompanyDetail();
}
