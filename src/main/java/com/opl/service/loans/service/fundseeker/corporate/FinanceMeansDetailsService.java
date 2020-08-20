package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FinanceMeansDetailRequest;
import com.opl.mudra.api.loans.model.FrameRequest;

public interface FinanceMeansDetailsService {

	public Boolean saveOrUpdate(FrameRequest  frameRequest) throws LoansException;
	
	
	public List<FinanceMeansDetailRequest> getMeansOfFinanceList(Long applicationId,Long userId) throws LoansException;

	public List<FinanceMeansDetailRequest> getMeansOfFinanceListByProposalId(Long proposalId,Long userId) throws Exception;
	
}
