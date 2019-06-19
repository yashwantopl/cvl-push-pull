package com.capitaworld.service.loans.service.sidbi;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.corporate.TotalCostOfProjectRequest;

public interface MeansOfFinanceDetailService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws LoansException;
	
	public List<TotalCostOfProjectRequest> getMeansOfFinanceList(Long applicationId,Long userId) throws LoansException;

}
