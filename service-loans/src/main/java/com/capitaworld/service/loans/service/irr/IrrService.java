package com.capitaworld.service.loans.service.irr;

import org.springframework.http.ResponseEntity;

import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetManuRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetServRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetTradRequest;
import com.capitaworld.service.rating.model.RatingResponse;

public interface IrrService {

	public  FinancialInputRequest cmaIrrMappingService(Long aplicationId) throws Exception;
	
	public  FinancialInputRequest coActIrrMappingService(Long aplicationId) throws Exception;
	
	public QualitativeInputSheetManuRequest qualitativeInputServiceManu(Long aplicationId, Long userId, Integer productId, Boolean isCmaUploaded, Boolean isCoActUploaded,Double industryRiskScore) throws Exception;
	
	public QualitativeInputSheetServRequest qualitativeInputServiceService(Long aplicationId, Long userId, Integer productId) throws Exception;
	
	public QualitativeInputSheetTradRequest qualitativeInputServiceTrading(Long aplicationId, Long userId, Integer productId) throws Exception;
	
	public ResponseEntity<RatingResponse> calculateIrrRating(Long applicationId,Long userId);
}
