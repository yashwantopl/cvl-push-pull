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
import com.capitaworld.service.loans.domain.fundseeker.corporate.AchievementDetail;
import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AchievementDetailsRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;

@Service
@Transactional
public class AchievementDetailServiceImpl implements AchievmentDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(AchievementDetailServiceImpl.class.getName());
	@Autowired
	private AchievementDetailsRepository achievementDetailsRepository;

	@Override
	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "saveOrUpdate");
		try {
			for (Map<String, Object> obj : frameRequest.getDataList()) {
				AchievementDetailRequest achievementDetailRequest = (AchievementDetailRequest) MultipleJSONObjectHelper
						.getObjectFromMap(obj, AchievementDetailRequest.class);
				AchievementDetail achievementDetail = new AchievementDetail();
				BeanUtils.copyProperties(achievementDetailRequest, achievementDetail);
				if (achievementDetailRequest.getId() == null) {
					achievementDetail.setCreatedBy(frameRequest.getUserId());
					achievementDetail.setCreatedDate(new Date());
				}
				achievementDetail.setApplicationId(new LoanApplicationMaster(frameRequest.getApplicationId()));
				achievementDetail.setModifiedBy(frameRequest.getUserId());
				achievementDetail.setModifiedDate(new Date());
				achievementDetailsRepository.save(achievementDetail);
			}
			CommonDocumentUtils.endHook(logger, "saveOrUpdate");
			return true;
		}

		catch (Exception e) {
			logger.error("Exception  in save achievementDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId, Long userId) throws Exception {
		try {
			CommonDocumentUtils.startHook(logger, "getAchievementDetailList");
			List<AchievementDetail> achievementDetails = achievementDetailsRepository
					.listAchievementFromAppId(applicationId,userId);
			List<AchievementDetailRequest> achievementDetailRequests = new ArrayList<AchievementDetailRequest>(
					achievementDetails.size());

			for (AchievementDetail detail : achievementDetails) {
				AchievementDetailRequest achievementDetailRequest = new AchievementDetailRequest();
				BeanUtils.copyProperties(detail, achievementDetailRequest);
				achievementDetailRequests.add(achievementDetailRequest);
			}
			CommonDocumentUtils.endHook(logger, "getAchievementDetailList");
			return achievementDetailRequests;
		} catch (Exception e) {
			logger.error("Exception getting achievementDetail  :-");
			e.printStackTrace();
			throw new Exception(CommonUtils.SOMETHING_WENT_WRONG);
		}
	}

}
