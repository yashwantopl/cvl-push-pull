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

import com.capitaworld.service.loans.domain.fundseeker.corporate.AchievementDetail;
import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AchievementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class AchievementDetailServiceImpl implements AchievmentDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(AchievementDetailServiceImpl.class.getName());
	@Autowired
	private AchievementDetailsRepository achievementDetailsRepository;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) {
		// TODO Auto-generated method stub
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				AchievementDetailRequest achievementDetailRequest = (AchievementDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, AchievementDetailRequest.class);
				AchievementDetail achievementDetail = new AchievementDetail();
				BeanUtils.copyProperties(achievementDetailRequest, achievementDetail);
				if (achievementDetailRequest.getId() == null) {
					achievementDetail.setCreatedBy(frameRequest.getApplicationId());
					achievementDetail.setCreatedDate(new Date());
				}
				achievementDetail.setApplicationId(loanApplicationRepository.findOne(frameRequest.getApplicationId()));
				achievementDetail.setModifiedBy(frameRequest.getApplicationId());
				achievementDetail.setModifiedDate(new Date());
				achievementDetailsRepository.save(achievementDetail);
			}
			return true;
		}

		catch (Exception e) {
			logger.info("Exception  in save achievementDetail  :-");
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId) {
		// TODO Auto-generated method stub

		List<AchievementDetail> achievementDetails = achievementDetailsRepository
				.listAchievementFromAppId(applicationId);
		List<AchievementDetailRequest> achievementDetailRequests = new ArrayList<AchievementDetailRequest>();

		for (int i = 0; i < achievementDetails.size(); i++) {
			AchievementDetailRequest achievementDetailRequest = new AchievementDetailRequest();
			BeanUtils.copyProperties(achievementDetails.get(i), achievementDetailRequest);
			achievementDetailRequests.add(achievementDetailRequest);
		}
		return achievementDetailRequests;
	}

}
