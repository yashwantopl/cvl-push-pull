package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.FutureFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface FutureFinancialEstimatesDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimateDetailsList(Long id,Long userId) throws LoansException;
	
	public List<FutureFinancialEstimatesDetailRequest> getFutureEstimateDetailsList(Long userId,Long applicationId) throws LoansException;

}
