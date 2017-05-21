package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.FutureFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface FutureFinancialEstimatesDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimateDetailsList(Long id,Long userId) throws Exception;
	
	public List<FutureFinancialEstimatesDetailRequest> getFutureEstimateDetailsList(Long userId,Long applicationId) throws Exception;

}
