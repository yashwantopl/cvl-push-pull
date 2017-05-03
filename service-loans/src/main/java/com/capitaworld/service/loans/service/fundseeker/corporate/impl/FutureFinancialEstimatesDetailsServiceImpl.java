package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FutureFinancialEstimatesDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.FutureFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FutureFinancialEstimatesDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FutureFinancialEstimatesDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class FutureFinancialEstimatesDetailsServiceImpl implements FutureFinancialEstimatesDetailsService{
	
	private static final Logger logger = LoggerFactory.getLogger(FutureFinancialEstimatesDetailsServiceImpl.class);

	@Autowired
	private FutureFinancialEstimatesDetailsRepository futureFinancialEstimateDetailsRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FutureFinancialEstimatesDetailRequest futureFinancialEstimateDetailRequest = (FutureFinancialEstimatesDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FutureFinancialEstimatesDetailRequest.class);
				FutureFinancialEstimatesDetail futureFinancialEstimateDetail = new FutureFinancialEstimatesDetail();
				BeanUtils.copyProperties(futureFinancialEstimateDetailRequest, futureFinancialEstimateDetail);
				if (futureFinancialEstimateDetailRequest.getId() == null) {
					futureFinancialEstimateDetail.setCreatedBy(frameRequest.getUserId());
					futureFinancialEstimateDetail.setCreatedDate(new Date());
				}
				futureFinancialEstimateDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				futureFinancialEstimateDetail.setModifiedBy(frameRequest.getUserId());
				futureFinancialEstimateDetail.setModifiedDate(new Date());
				futureFinancialEstimateDetailsRepository.save(futureFinancialEstimateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save futureFinancialEstimateDetail  :-");
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimateDetailsList(Long id) {
		List<FutureFinancialEstimatesDetail> futureFinancialEstimateDetails = futureFinancialEstimateDetailsRepository
				.listFutureFinancialEstimateDetailsFromAppId(id);
		List<FutureFinancialEstimatesDetailRequest> futureFinancialEstimateDetailRequests = new ArrayList<FutureFinancialEstimatesDetailRequest>();

		for (FutureFinancialEstimatesDetail detail : futureFinancialEstimateDetails) {
			FutureFinancialEstimatesDetailRequest futureFinancialEstimateDetailRequest = new FutureFinancialEstimatesDetailRequest();
			BeanUtils.copyProperties(detail, futureFinancialEstimateDetailRequest);
			futureFinancialEstimateDetailRequests.add(futureFinancialEstimateDetailRequest);
		}
		return futureFinancialEstimateDetailRequests;
	}

}
