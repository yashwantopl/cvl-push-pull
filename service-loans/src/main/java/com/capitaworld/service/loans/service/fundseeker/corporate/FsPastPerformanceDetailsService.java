package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetailsRequest;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;

import java.util.List;

public interface FsPastPerformanceDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<FsPastPerformanceDetailsRequest> getPastPerformanceList(Long applicationId) throws Exception;
	
}
