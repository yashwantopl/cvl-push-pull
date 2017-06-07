package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FinanceMeansDetailRequest;
import com.capitaworld.service.loans.model.FrameRequest;

public interface FinanceMeansDetailsService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws Exception;
	
	
	public List<FinanceMeansDetailRequest> getMeansOfFinanceList(Long applicationId,Long userId) throws Exception;
	
	
}
