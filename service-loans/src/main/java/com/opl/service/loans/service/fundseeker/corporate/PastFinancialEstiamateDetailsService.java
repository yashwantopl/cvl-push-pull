package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.PastFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PastFinancialEstiamateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<PastFinancialEstimatesDetailRequest> getPastFinancialEstimateDetailsList(Long id) throws LoansException;

	public List<PastFinancialEstimatesDetailRequest> getFinancialListData(Long userId, Long applicationId) throws LoansException;

}
