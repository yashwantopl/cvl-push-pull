package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.AchievementDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

public interface AchievmentDetailsService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws LoansException;


	public List<AchievementDetailRequest> getAchievementDetailList(Long applicationId,Long userId) throws LoansException;

	/*multiple bank*/
	public List<AchievementDetailRequest> getAchievementDetailListForMultipleBank(Long proposalId) throws Exception;
}
