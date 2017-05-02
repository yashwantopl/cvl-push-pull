package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.FutureFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface FutureFinancialEstimatesDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<FutureFinancialEstimatesDetailRequest> getFutureFinancialEstimateDetailsList(Long id);

}
