package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;

/**
 * @author Sanket
 *
 */
public interface SecurityCorporateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<SecurityCorporateDetailRequest> getsecurityCorporateDetailsList(Long id,Long userId) throws LoansException;

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailsListFromProposalId(Long proposalId,Long userId) throws Exception;

}
