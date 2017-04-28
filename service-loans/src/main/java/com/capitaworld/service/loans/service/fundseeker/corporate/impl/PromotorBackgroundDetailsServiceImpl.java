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

import com.capitaworld.service.loans.domain.fundseeker.corporate.PromotorBackgroundDetail;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.PromotorBackgroundDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PromotorBackgroundDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.PromotorBackgroundDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

/**
 * @author Sanket
 *
 */
@Service
@Transactional
public class PromotorBackgroundDetailsServiceImpl implements PromotorBackgroundDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(PromotorBackgroundDetailsServiceImpl.class);
	
	@Autowired
	private PromotorBackgroundDetailsRepository promotorBackgroundDetailsRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				PromotorBackgroundDetailRequest promotorBackgroundDetailRequest = (PromotorBackgroundDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, PromotorBackgroundDetailRequest.class);
				PromotorBackgroundDetail promotorBackgroundDetail = new PromotorBackgroundDetail();
				BeanUtils.copyProperties(promotorBackgroundDetailRequest, promotorBackgroundDetail);
				if (promotorBackgroundDetailRequest.getId() == null) {
					promotorBackgroundDetail.setCreatedBy(frameRequest.getUserId());
					promotorBackgroundDetail.setCreatedDate(new Date());
				}
				promotorBackgroundDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				promotorBackgroundDetail.setModifiedBy(frameRequest.getUserId());
				promotorBackgroundDetail.setModifiedDate(new Date());
				promotorBackgroundDetailsRepository.save(promotorBackgroundDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save promoterBackgroundDetail  :-");
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public List<PromotorBackgroundDetailRequest> getPromotorBackgroundDetailList(Long applicationId) {
		List<PromotorBackgroundDetail> promotorBackgroundDetails = promotorBackgroundDetailsRepository
				.listPromotorBackgroundFromAppId(applicationId);
		List<PromotorBackgroundDetailRequest> promotorBackgroundDetailRequests = new ArrayList<PromotorBackgroundDetailRequest>();

		for (int i = 0; i < promotorBackgroundDetails.size(); i++) {
			PromotorBackgroundDetailRequest promotorBackgroundDetailRequest = new PromotorBackgroundDetailRequest();
			BeanUtils.copyProperties(promotorBackgroundDetails.get(i), promotorBackgroundDetailRequest);
			promotorBackgroundDetailRequests.add(promotorBackgroundDetailRequest);
		}
		return promotorBackgroundDetailRequests;
	}


}
