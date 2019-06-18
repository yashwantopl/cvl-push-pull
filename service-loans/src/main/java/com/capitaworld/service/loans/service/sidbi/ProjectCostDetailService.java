package com.capitaworld.service.loans.service.sidbi;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;

public interface ProjectCostDetailService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws LoansException;
	
	public List<TotalCostOfProjectRequest> getCostOfProjectDetailList(Long applicationId,Long userId) throws LoansException;
	
	public Double getCostOfProject(Long applicationId, Long userId);
}
