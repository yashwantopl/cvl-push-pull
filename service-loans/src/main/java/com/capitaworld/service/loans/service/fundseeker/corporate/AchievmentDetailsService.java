package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

public interface AchievmentDetailsService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws LoansException;
	
	
	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId,Long userId) throws LoansException;
	
	
}
