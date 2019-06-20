package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetailsRequest;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FsPastPerformanceDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FsPastPerformanceDetailsService;
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
			List<FsPastPerformanceDetails> fsPastPerformanceDetails = fsPastPerformanceDetailRepository
					.getList(applicationId);
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
