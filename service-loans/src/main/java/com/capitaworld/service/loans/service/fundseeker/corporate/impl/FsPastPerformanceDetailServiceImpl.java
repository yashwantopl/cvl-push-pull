package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetailsRequest;
import com.capitaworld.service.loans.domain.fundseeker.corporate.LiabilitiesDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.CMARequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FsPastPerformanceDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LiabilitiesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.OperatingStatementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CMAService;
import com.capitaworld.service.loans.service.fundseeker.corporate.FsPastPerformanceDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.OperatingStatementDetailsService;
import com.capitaworld.service.loans.service.scoring.ScoringService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
			List<FsPastPerformanceDetails> fsPastPerformanceDetails = fsPastPerformanceDetailRepository
					.getList(applicationId);
			if(CommonUtils.isListNullOrEmpty(fsPastPerformanceDetails)){

				fsPastPerformanceDetails = new ArrayList<>();
				FsPastPerformanceDetails fsPastPerformanceDetails1 = new FsPastPerformanceDetails();
				OperatingStatementDetails operatingStatementPast1YearDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, pastYear1+"");
				if (!CommonUtils.isObjectNullOrEmpty(operatingStatementPast1YearDetails)) {
					fsPastPerformanceDetails1.setNetSalesPastYear1(operatingStatementPast1YearDetails.getNetSales().longValue());
					fsPastPerformanceDetails1.setNetProfitPastYear1(operatingStatementPast1YearDetails.getNetProfitOrLoss().longValue());
				}else{
					logger.error("Error while getting net sales/net profit loss past 1 year details from operating statement");
				}


				OperatingStatementDetails operatingStatementPast2YearDetails = operatingStatementDetailsRepository.getOperatingStatementDetails(applicationId, pastYear2+"");
				if (!CommonUtils.isObjectNullOrEmpty(operatingStatementPast2YearDetails)) {
					fsPastPerformanceDetails1.setNetSalesPastYear2(operatingStatementPast2YearDetails.getNetSales().longValue());
					fsPastPerformanceDetails1.setNetProfitPastYear2(operatingStatementPast2YearDetails.getNetProfitOrLoss().longValue());
				}else{
					logger.error("Error while getting net sales/net profit loss past 2 year details from operating statement");
				}

				LiabilitiesDetails liabilitiesPast1YearDetails =  liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId,pastYear1+"");
				if (!CommonUtils.isObjectNullOrEmpty(liabilitiesPast1YearDetails)) {
					fsPastPerformanceDetails1.setCompNetWorthPastYear1(liabilitiesPast1YearDetails.getNetWorth().longValue());
				}else{
					logger.error("Error while getting company net worth past 1 year details from liabilities statement");
				}

				LiabilitiesDetails liabilitiesPast2YearDetails =  liabilitiesDetailsRepository.getLiabilitiesDetails(applicationId,pastYear2+"");
				if (!CommonUtils.isObjectNullOrEmpty(liabilitiesPast2YearDetails)) {
					fsPastPerformanceDetails1.setCompNetWorthPastYear2(liabilitiesPast2YearDetails.getNetWorth().longValue());
				}else{
					logger.error("Error while getting company net worth past 2 year details from liabilities statement");
				}
				fsPastPerformanceDetails.add(fsPastPerformanceDetails1);
			}
			List<FsPastPerformanceDetailsRequest> fsPastPerformanceDetailsRequests = new ArrayList<>(
					fsPastPerformanceDetails.size());

			for (FsPastPerformanceDetails detail : fsPastPerformanceDetails) {
				FsPastPerformanceDetailsRequest financeMeansDetailRequest = new FsPastPerformanceDetailsRequest();
				BeanUtils.copyProperties(detail, financeMeansDetailRequest);
				fsPastPerformanceDetailsRequests.add(financeMeansDetailRequest);
			}
			return fsPastPerformanceDetailsRequests;
		} catch (Exception e) {
			logger.error("Exception getting financeMeansDetail  :- {}",e);
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

}
