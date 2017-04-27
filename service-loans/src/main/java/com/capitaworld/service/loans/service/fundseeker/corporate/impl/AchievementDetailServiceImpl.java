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
	public Boolean saveOrUpdate(List<AchievementDetailRequest> achievementDetailRequests) {
		// TODO Auto-generated method stub
		try { 
			
			for (int i = 0; i < achievementDetailRequests.size(); i++) {
				AchievementDetail achievementDetail= new AchievementDetail();
				BeanUtils.copyProperties(achievementDetailRequests.get(i),achievementDetail);
				if(achievementDetailRequests.get(i).getId()==null)
				{
					achievementDetail.setCreatedBy(achievementDetailRequests.get(i).getApplicationId());
					achievementDetail.setCreatedDate(new Date());
				}
				achievementDetail.setModifiedBy(achievementDetailRequests.get(i).getApplicationId());
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
		
		List<AchievementDetail> achievementDetails=achievementDetailsRepository.listAchievementFromAppId(applicationId);
		List<AchievementDetailRequest> achievementDetailRequests = new ArrayList<AchievementDetailRequest>();
		
		for (int i = 0; i < achievementDetails.size(); i++) {
			AchievementDetailRequest achievementDetailRequest=new AchievementDetailRequest();
			BeanUtils.copyProperties(achievementDetails.get(i),achievementDetailRequest);
			achievementDetailRequests.add(achievementDetailRequest);
		}
		return achievementDetailRequests;
	}


	

	
}
