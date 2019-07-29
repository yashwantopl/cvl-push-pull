package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetailsRequest;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FsPastPerformanceDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FsPastPerformanceDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.service.sidbi.SidbiSpecificService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.enums.sidbi.SidbiCurrencyRate;

@Service
@Transactional
public class FsPastPerformanceDetailServiceImpl implements FsPastPerformanceDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(FsPastPerformanceDetailServiceImpl.class.getName());

	@Autowired
	private FsPastPerformanceDetailRepository fsPastPerformanceDetailRepository;

	@Autowired
	OperatingStatementDetailsService operatingStatementDetailsService;

	@Autowired
	OperatingStatementDetailsRepository operatingStatementDetailsRepository;

	@Autowired
	LiabilitiesDetailsRepository liabilitiesDetailsRepository;

	@Autowired
	private ScoringService scoringService;
	
	@Autowired
	SidbiSpecificService sidbiSpecificService;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FsPastPerformanceDetailsRequest fsPastPerformanceDetailRequest = (FsPastPerformanceDetailsRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FsPastPerformanceDetailsRequest.class);
				FsPastPerformanceDetails fsPastPerformanceDetails = null;
				if (fsPastPerformanceDetailRequest.getId() != null) {
					fsPastPerformanceDetails = fsPastPerformanceDetailRepository.findOne(fsPastPerformanceDetailRequest.getId());
				} else {
					fsPastPerformanceDetails = new FsPastPerformanceDetails();
				}
				this.convertAbsoluteValues(fsPastPerformanceDetailRequest, frameRequest.getApplicationId());
				BeanUtils.copyProperties(fsPastPerformanceDetailRequest, fsPastPerformanceDetails);
				fsPastPerformanceDetails.setApplicationId(frameRequest.getApplicationId());
				fsPastPerformanceDetails.setActive(true);
				fsPastPerformanceDetailRepository.save(fsPastPerformanceDetails);
			}
			return true;
		}

		catch (Exception e) {
			logger.error("Exception in save totalCostOfProject :-",e);
			throw new LoansException(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

	@Override
	public List<FsPastPerformanceDetailsRequest> getPastPerformanceList(Long applicationId) throws Exception {
		try {

			Integer year = scoringService.getFinYear(applicationId);
			int pastYear1 = year - 1;
			int pastYear2 = year - 2;
			List<FsPastPerformanceDetails> fsPastPerformanceDetails = fsPastPerformanceDetailRepository.getList(applicationId);
			if(CommonUtils.isListNullOrEmpty(fsPastPerformanceDetails)){

				fsPastPerformanceDetails = new ArrayList<>();
				FsPastPerformanceDetails fsPastPerformanceDetails1 = new FsPastPerformanceDetails();
				OperatingStatementDetails operatingStatementPast1YearDetails = operatingStatementDetailsRepository.getOperatingStatementDetailsByAppIdAndFinYear(applicationId, pastYear1+"");
				if (!CommonUtils.isObjectNullOrEmpty(operatingStatementPast1YearDetails)) {
					fsPastPerformanceDetails1.setNetSalesPastYear1(operatingStatementPast1YearDetails.getNetSales().doubleValue());
					fsPastPerformanceDetails1.setNetProfitPastYear1(operatingStatementPast1YearDetails.getNetProfitOrLoss().doubleValue());
				}else{
					logger.error("Error while getting net sales/net profit loss past 1 year details from operating statement");
				}


				OperatingStatementDetails operatingStatementPast2YearDetails = operatingStatementDetailsRepository.getOperatingStatementDetailsByAppIdAndFinYear(applicationId, pastYear2+"");
				if (!CommonUtils.isObjectNullOrEmpty(operatingStatementPast2YearDetails)) {
					fsPastPerformanceDetails1.setNetSalesPastYear2(operatingStatementPast2YearDetails.getNetSales().doubleValue());
					fsPastPerformanceDetails1.setNetProfitPastYear2(operatingStatementPast2YearDetails.getNetProfitOrLoss().doubleValue());
				}else{
					logger.error("Error while getting net sales/net profit loss past 2 year details from operating statement");
				}

				LiabilitiesDetails liabilitiesPast1YearDetails =  liabilitiesDetailsRepository.getLiabilitiesDetailsByAppIdAndFinYear(applicationId,pastYear1+"");
				if (!CommonUtils.isObjectNullOrEmpty(liabilitiesPast1YearDetails)) {
					fsPastPerformanceDetails1.setCompNetWorthPastYear1(liabilitiesPast1YearDetails.getNetWorth().doubleValue());
				}else{
					logger.error("Error while getting company net worth past 1 year details from liabilities statement");
				}

				LiabilitiesDetails liabilitiesPast2YearDetails =  liabilitiesDetailsRepository.getLiabilitiesDetailsByAppIdAndFinYear(applicationId,pastYear2+"");
				if (!CommonUtils.isObjectNullOrEmpty(liabilitiesPast2YearDetails)) {
					fsPastPerformanceDetails1.setCompNetWorthPastYear2(liabilitiesPast2YearDetails.getNetWorth().doubleValue());
				}else{
					logger.error("Error while getting company net worth past 2 year details from liabilities statement");
				}
				fsPastPerformanceDetails.add(fsPastPerformanceDetails1);
			}
			List<FsPastPerformanceDetailsRequest> fsPastPerformanceDetailsRequests = new ArrayList<>(fsPastPerformanceDetails.size());

			for (FsPastPerformanceDetails detail : fsPastPerformanceDetails) {
				FsPastPerformanceDetailsRequest pastPerformanceDetailsRequest = new FsPastPerformanceDetailsRequest();
				BeanUtils.copyProperties(detail, pastPerformanceDetailsRequest);
				this.convertValuesIn(pastPerformanceDetailsRequest, applicationId);
				fsPastPerformanceDetailsRequests.add(pastPerformanceDetailsRequest);
			}
			return fsPastPerformanceDetailsRequests;
		} catch (Exception e) {
			logger.error("Exception getting financeMeansDetail  :- {}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}
	
	private void convertAbsoluteValues(FsPastPerformanceDetailsRequest pastPerformanceDetailsRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);

		pastPerformanceDetailsRequest.setNetSalesPastYear1(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetSalesPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesPastYear2(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetSalesPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesPresentYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetSalesPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesNextYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetSalesNextYear(), sidbiCurrencyRateObj.getRate()));
		
		pastPerformanceDetailsRequest.setNetProfitPastYear1(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetProfitPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitPastYear2(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetProfitPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitPresentYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetProfitPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitNextYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getNetProfitNextYear(), sidbiCurrencyRateObj.getRate()));
		
		pastPerformanceDetailsRequest.setCompNetWorthPastYear1(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getCompNetWorthPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthPastYear2(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getCompNetWorthPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthPresentYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getCompNetWorthPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthNextYear(CommonUtils.convertTwoDecimalAbsoluteValues(pastPerformanceDetailsRequest.getCompNetWorthNextYear(), sidbiCurrencyRateObj.getRate()));
		
		
	}
	
	private void convertValuesIn(FsPastPerformanceDetailsRequest pastPerformanceDetailsRequest,Long applicationId) throws LoansException {
		SidbiCurrencyRate sidbiCurrencyRateObj = sidbiSpecificService.getValuesIn(applicationId);
		
		pastPerformanceDetailsRequest.setNetSalesPastYear1(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetSalesPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesPastYear2(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetSalesPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesPresentYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetSalesPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetSalesNextYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetSalesNextYear(), sidbiCurrencyRateObj.getRate()));
		
		pastPerformanceDetailsRequest.setNetProfitPastYear1(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetProfitPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitPastYear2(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetProfitPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitPresentYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetProfitPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setNetProfitNextYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getNetProfitNextYear(), sidbiCurrencyRateObj.getRate()));
		
		pastPerformanceDetailsRequest.setCompNetWorthPastYear1(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getCompNetWorthPastYear1(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthPastYear2(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getCompNetWorthPastYear2(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthPresentYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getCompNetWorthPresentYear(), sidbiCurrencyRateObj.getRate()));
		pastPerformanceDetailsRequest.setCompNetWorthNextYear(CommonUtils.convertTwoDecimalValuesIn(pastPerformanceDetailsRequest.getCompNetWorthNextYear(), sidbiCurrencyRateObj.getRate()));
				
	}

	
}
