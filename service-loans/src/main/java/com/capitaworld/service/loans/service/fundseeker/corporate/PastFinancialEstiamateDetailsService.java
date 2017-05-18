package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PastFinancialEstiamateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimateDetailsList(Long id);

}
