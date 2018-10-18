package com.capitaworld.service.loans.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.InEligibleProposalDetailsRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.ProposalDetailsAdminRequest;

/**
 * Created by KushalCW on 22-09-2018.
 */
public interface IneligibleProposalDetailsService {

	public Boolean save(InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest);

	public Boolean sendMailToFsAndBankBranch(Long applicationId, Long branchId, Long userOrgId);

	public List<ProposalDetailsAdminRequest> getOfflineProposals(Long userOrgId, Long userId,
			ProposalDetailsAdminRequest request);

}
