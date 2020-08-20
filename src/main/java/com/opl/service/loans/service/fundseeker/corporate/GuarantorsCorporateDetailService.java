package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.GuarantorsCorporateDetailRequest;

/**
 * @author Sanket
 *
 */
public interface GuarantorsCorporateDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailList(Long id,Long userId) throws LoansException;

	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailListByProposalId(Long proposalId,Long userId) throws Exception;

}
