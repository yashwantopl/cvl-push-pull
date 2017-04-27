package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;

public interface AchievmentDetailsService {

	public Boolean saveOrUpdate(AchievementDetailRequest achievementDetailRequest);
	
	public AchievementDetailRequest getAchievementDetail(Long id);
	
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId);
	
	public Boolean remove(Long id);
	
}
