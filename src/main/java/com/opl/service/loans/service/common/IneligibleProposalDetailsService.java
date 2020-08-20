package com.opl.service.loans.service.common;

import java.util.List;

import com.opl.mudra.api.loans.model.InEligibleProposalDetailsRequest;
import com.opl.mudra.api.loans.model.ProposalDetailsAdminRequest;

/**
 * Created by KushalCW on 22-09-2018.
 */
public interface IneligibleProposalDetailsService {

	public Integer save(InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest);
	
	public InEligibleProposalDetailsRequest get(Long applicationId);

	public Boolean sendMailToFsAndBankBranch(Long applicationId, Long branchId, Long userOrgId);

	public Boolean sendMailToFsAndBankBranchForSbiBankSpecific(Long applicationId,Long branchId,Long userOrgId,Boolean sidbiStatus);

	public List<ProposalDetailsAdminRequest> getOfflineProposals(Long userOrgId, Long userId,
			ProposalDetailsAdminRequest request);

	public boolean updateStatus(InEligibleProposalDetailsRequest inEliProReq);

	//added by Jaimin Darji For transfer branch
	public boolean updateTransferBranchDetail(InEligibleProposalDetailsRequest inEliProReq);

	//added by Jaimin Darji For Reopen Proposal
	public boolean updateReOpenProposalDetail(InEligibleProposalDetailsRequest inEliProReq);
	
	public Boolean checkIsExistOfflineProposalByApplicationId(Long applicationId);

	public Integer getBusinessTypeIdFromApplicationId(Long applicationId);

	public String sendInEligibleForSidbi(Long applicationId);

	public boolean updateApplicationStatus(InEligibleProposalDetailsRequest inEliProReq);

	public boolean updateSanctionStatus(InEligibleProposalDetailsRequest inEliProReq);
}
