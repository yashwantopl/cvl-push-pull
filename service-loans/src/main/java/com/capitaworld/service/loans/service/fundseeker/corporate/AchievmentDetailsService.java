package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.AchievementDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

public interface AchievmentDetailsService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws Exception;

	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId,Long userId) throws Exception;

	/*multiple bank*/
	public List<AchievementDetailRequest> getAchievementDetailListForMultipleBank(Long proposalId) throws Exception;
}
