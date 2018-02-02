package com.capitaworld.service.loans.service.irr;

import com.capitaworld.service.rating.model.FinancialInputRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetManuRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetServRequest;
import com.capitaworld.service.rating.model.QualitativeInputSheetTradRequest;

public interface IrrService {

	public  FinancialInputRequest cmaIrrMappingService(Long aplicationId) throws Exception;
	
	public  FinancialInputRequest coActIrrMappingService(Long aplicationId) throws Exception;
	
	public QualitativeInputSheetManuRequest qualitativeInputServiceManu(Long aplicationId, Integer productId) throws Exception;
	
	public QualitativeInputSheetServRequest qualitativeInputServiceService(Long aplicationId, Integer productId) throws Exception;
	
	public QualitativeInputSheetTradRequest qualitativeInputServiceTrading(Long aplicationId, Integer productId) throws Exception;
}
