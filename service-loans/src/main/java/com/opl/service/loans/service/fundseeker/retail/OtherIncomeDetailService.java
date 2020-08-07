package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.retail.OtherIncomeDetailRequest;

/**
 * @author Sanket
 *
 */
public interface OtherIncomeDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public Boolean saveOrUpdateCoApplicant(FrameRequest frameRequest) throws LoansException;

	public List<OtherIncomeDetailRequest> getOtherIncomeDetailList(Long id, int applicationType) throws LoansException;

	public List<OtherIncomeDetailRequest> getOtherIncomeDetailList(Long id, int applicationType,Long proposalId) throws LoansException;

	public List<OtherIncomeDetailRequest> getOtherIncomeDetailListForCoApplicant(Long id, Long proposalId,Long coAppId) throws LoansException;

}
