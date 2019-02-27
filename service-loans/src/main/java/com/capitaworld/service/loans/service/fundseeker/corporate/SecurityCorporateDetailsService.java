package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;

/**
 * @author Sanket
 *
 */
public interface SecurityCorporateDetailsService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

	public List<SecurityCorporateDetailRequest> getsecurityCorporateDetailsList(Long id,Long userId) throws LoansException;

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailsListFromProposalId(Long proposalId,Long userId) throws Exception;

}
