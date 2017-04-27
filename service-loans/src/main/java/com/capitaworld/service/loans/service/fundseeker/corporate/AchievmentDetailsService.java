package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;

public interface AchievmentDetailsService {

	public Boolean saveOrUpdate(List<AchievementDetailRequest> achievementDetailRequests);
	
	
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId);
	
	
}
