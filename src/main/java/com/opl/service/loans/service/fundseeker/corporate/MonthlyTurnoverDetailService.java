package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.MonthlyTurnoverDetailRequest;

/**
 * @author Sanket
 *
 */
public interface MonthlyTurnoverDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailList(Long id,Long userId) throws LoansException;
	
	
	public List<MonthlyTurnoverDetailRequest> getMonthlyTurnoverDetailListByProposalId(Long id,Long userId,Long proposalId) throws LoansException;

}
