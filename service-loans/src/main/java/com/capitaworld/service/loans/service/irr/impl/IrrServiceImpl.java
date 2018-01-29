package com.capitaworld.service.loans.service.irr.impl;

import java.util.Date;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.irr.IrrService;
import com.capitaworld.service.rating.RatingClient;
import com.capitaworld.service.rating.model.IrrRequest;
import com.capitaworld.service.rating.model.manufacturing.FinancialInputRequest;
import com.capitaworld.service.users.utils.CommonUtils;
import com.ibm.icu.util.Calendar;

@Service
@Transactional
public class IrrServiceImpl implements IrrService{
	
	@Autowired
	RatingClient ratingClient;
	
	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Override
	public JSONObject cmaIrrMappingService(Long aplicationId) throws Exception {
		// TODO Auto-generated method stub
		IrrRequest irrRequest = new IrrRequest();
		FinancialInputRequest financialInputRequest = new FinancialInputRequest();
		OperatingStatementDetails operatingStatementDetails = new OperatingStatementDetails();
		
		// Third year data
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);		
		operatingStatementDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(aplicationId, currentYear+"");
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setDomesticSales(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDomesticSales()))
			operatingStatementDetails.setExportSales(0.0);		
		financialInputRequest.setGrossSalesTy(operatingStatementDetails.getDomesticSales()+operatingStatementDetails.getExportSales());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getLessExciseDuty()))
			operatingStatementDetails.setLessExciseDuty(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductOtherItems()))
			operatingStatementDetails.setDeductOtherItems(0.0);	
		financialInputRequest.setLessExciseDuityTy(operatingStatementDetails.getLessExciseDuty()+operatingStatementDetails.getDeductOtherItems());
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStock()))
			operatingStatementDetails.setAddOperatingStock(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductStockInProcess()))
			operatingStatementDetails.setDeductStockInProcess(0.0);	
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getAddOperatingStockFg()))
			operatingStatementDetails.setAddOperatingStockFg(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getDeductClStockFg()))
			operatingStatementDetails.setDeductClStockFg(0.0);	
		financialInputRequest.setIncreaseDecreaseStockTy((operatingStatementDetails.getAddOperatingStock()-operatingStatementDetails.getDeductStockInProcess()) + (operatingStatementDetails.getAddOperatingStockFg()-operatingStatementDetails.getDeductClStockFg()));
		
		
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getRawMaterials()))
			operatingStatementDetails.setRawMaterials(0.0);
		if(CommonUtils.isObjectNullOrEmpty(operatingStatementDetails.getOtherSpares()))
			operatingStatementDetails.setOtherSpares(0.0);	
		financialInputRequest.setRawMaterialConsumedTy(operatingStatementDetails.getRawMaterials()+operatingStatementDetails.getOtherSpares());
		
		
		
		
		
		
		
		
		
		
		irrRequest.setFinancialInputRequest(financialInputRequest);
		return null;
	}

	@Override
	public JSONObject coActIrrMappingService(Long aplicationId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
