package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AchievementDetail;
import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.repository.fundseeker.corporate.AchievementDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.AchievmentDetailsService;

@Service
@Transactional
public class AchievementDetailServiceImpl implements AchievmentDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(AchievementDetailServiceImpl.class.getName());
	@Autowired
	private AchievementDetailsRepository achievementDetailsRepository;

	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;


	@Override
	public Boolean saveOrUpdate(AchievementDetailRequest achievementDetailRequest) {
		// TODO Auto-generated method stub
		try { 
			AchievementDetail achievementDetail= new AchievementDetail();
			BeanUtils.copyProperties(achievementDetailRequest, achievementDetail);
			achievementDetail.setApplicationId(loanApplicationRepository.findOne(achievementDetailRequest.getApplicationId()));
			achievementDetail.setCreatedBy(achievementDetailRequest.getApplicationId());
			achievementDetail.setCreatedDate(new Date());
			achievementDetail.setModifiedBy(achievementDetailRequest.getApplicationId());
			achievementDetail.setModifiedDate(new Date());
			achievementDetail.setIsActive(true);
			achievementDetailsRepository.save(achievementDetail);
			return true;
			}

		 catch (Exception e) {
			logger.info("Exception  in save achievementDetail  :-");
			e.printStackTrace();
			return false;
		}
		
	}


	@Override
	public AchievementDetailRequest getAchievementDetail(Long id) {
		// TODO Auto-generated method stub
		AchievementDetailRequest achievementDetailRequest = new AchievementDetailRequest();
		BeanUtils.copyProperties(achievementDetailsRepository.findOne(id), achievementDetailRequest);
		return achievementDetailRequest;
	}


	@Override
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId) {
		// TODO Auto-generated method stub
		
		List<AchievementDetail> achievementDetails=achievementDetailsRepository.listAchievementFromAppId(applicationId);
		List<AchievementDetailRequest> achievementDetailRequests = new ArrayList<AchievementDetailRequest>();
		
		for (int i = 0; i < achievementDetails.size(); i++) {
			AchievementDetailRequest achievementDetailRequest=new AchievementDetailRequest();
			BeanUtils.copyProperties(achievementDetails.get(i),achievementDetailRequest);
			achievementDetailRequests.add(achievementDetailRequest);
		}
		return achievementDetailRequests;
	}


	@Override
	public Boolean remove(Long id) {
		// TODO Auto-generated method stub
		Boolean flag;
		flag=(achievementDetailsRepository.remove(id)>0)?true:false;
		return flag;
	}

	
}
