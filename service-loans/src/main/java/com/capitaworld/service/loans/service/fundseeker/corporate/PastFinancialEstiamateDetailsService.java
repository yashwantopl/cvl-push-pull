package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.PastFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PastFinancialEstiamateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest);

	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimateDetailsList(Long id);

}
