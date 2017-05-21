package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;

public interface TotalCostOfProjectService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws Exception;
	
	
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailList(Long applicationId,Long userId) throws Exception;
	
	
}
