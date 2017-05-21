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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinanceMeansDetail;
import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.FinanceMeansDetailRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.FinanceMeansDetailsService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class FinanceMeansDetailServiceImpl implements FinanceMeansDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(FinanceMeansDetailServiceImpl.class.getName());
	@Autowired
	private FinanceMeansDetailRepository financeMeansDetailRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		// TODO Auto-generated method stub
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				FinanceMeansDetailRequest financeMeansRequest = (FinanceMeansDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, FinanceMeansDetailRequest.class);
				FinanceMeansDetail financeMeansDetail = null;
				if (financeMeansRequest.getId() != null) {
					financeMeansDetail = financeMeansDetailRepository.findOne(financeMeansRequest.getId());
				} else {
					financeMeansDetail = new FinanceMeansDetail();
					financeMeansDetail.setCreatedBy(frameRequest.getUserId());
					financeMeansDetail.setCreatedDate(new Date());
				}

				BeanUtils.copyProperties(financeMeansRequest, financeMeansDetail);
				financeMeansDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				financeMeansDetail.setModifiedBy(frameRequest.getUserId());
				financeMeansDetail.setModifiedDate(new Date());
				financeMeansDetailRepository.save(financeMeansDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception in save totalCostOfProject :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
		
	}

	@Override
	public List<FinanceMeansDetailRequest> getMeansOfFinanceList(Long applicationId, Long userId) throws Exception {
		try {
			List<FinanceMeansDetail> financeMeansDetails = financeMeansDetailRepository
					.listFinanceMeansFromAppId(applicationId, userId);
			List<FinanceMeansDetailRequest> financeMeansRequests = new ArrayList<FinanceMeansDetailRequest>(
					financeMeansDetails.size());

			for (FinanceMeansDetail detail : financeMeansDetails) {
				FinanceMeansDetailRequest financeMeansDetailRequest = new FinanceMeansDetailRequest();
				BeanUtils.copyProperties(detail, financeMeansDetailRequest);
				financeMeansRequests.add(financeMeansDetailRequest);
			}
			return financeMeansRequests;
		} catch (Exception e) {
			logger.info("Exception getting financeMeansDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
