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

import com.capitaworld.service.loans.domain.fundseeker.corporate.PastFinancialEstimatesDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PastFinancialEstimateDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PastFinancialEstiamateDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PastFinancialEstimateDetailsServiceImpl implements PastFinancialEstiamateDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(PastFinancialEstimateDetailsServiceImpl.class.getName());
	
	@Autowired
	private PastFinancialEstimateDetailsRepository pastFinancialEstimateDetailsRepository; 
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PastFinancialEstimatesDetailRequest pastFinancialEstimateDetailRequest = (PastFinancialEstimatesDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, PastFinancialEstimatesDetailRequest.class);
				PastFinancialEstimatesDetail pastFinancialEstimateDetail = new PastFinancialEstimatesDetail();
				BeanUtils.copyProperties(pastFinancialEstimateDetailRequest, pastFinancialEstimateDetail);
				if (pastFinancialEstimateDetailRequest.getId() == null) {
					pastFinancialEstimateDetail.setCreatedBy(frameRequest.getUserId());
					pastFinancialEstimateDetail.setCreatedDate(new Date());
				}
				pastFinancialEstimateDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				pastFinancialEstimateDetail.setModifiedBy(frameRequest.getUserId());
				pastFinancialEstimateDetail.setModifiedDate(new Date());
				pastFinancialEstimateDetailsRepository.save(pastFinancialEstimateDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save pastFinancialEstimateDetail  :-");
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimateDetailsList(Long id) {
		List<PastFinancialEstimatesDetail> pastFinancialEstimateDetails = pastFinancialEstimateDetailsRepository
				.listPastFinancialEstimateDetailsFromAppId(id);
		List<PastFinancialEstimatesDetailRequest> pastFinancialEstimateDetailRequests = new ArrayList<PastFinancialEstimatesDetailRequest>();

		for (PastFinancialEstimatesDetail detail : pastFinancialEstimateDetails) {
			PastFinancialEstimatesDetailRequest pastFinancialEstimateDetailRequest = new PastFinancialEstimatesDetailRequest();
			BeanUtils.copyProperties(detail, pastFinancialEstimateDetailRequest);
			pastFinancialEstimateDetailRequests.add(pastFinancialEstimateDetailRequest);
		}
		return pastFinancialEstimateDetailRequests;
	}

}
