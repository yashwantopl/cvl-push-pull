package com.opl.service.loans.service.common;

import java.util.List;

import com.opl.mudra.api.loans.model.InEligibleProposalDetailsRequest;
import com.opl.mudra.api.loans.model.ProposalDetailsAdminRequest;
import com.opl.mudra.api.matchengine.model.ProposalMappingRequest;
import com.opl.service.loans.domain.fundprovider.ProposalDetails;

/**
 * @author jaimin.darji
 */
public interface ProposalDetailsNewService {

    public ProposalDetails saveEligibleProposal(ProposalDetails proposalDetail);

    public ProposalMappingRequest saveProposalOnLoanSlection(ProposalMappingRequest proposalMappingRequest);

    public Long saveOfflineProposal(InEligibleProposalDetailsRequest inlPropReq);

    public ProposalMappingRequest getActiveProposalByApplicationId(Long applicationId);

    public List<ProposalDetailsAdminRequest> getOfflineProposals(Long userOrgId, ProposalDetailsAdminRequest request);

    public boolean updateTransferBranchDetail(InEligibleProposalDetailsRequest inEliProReq);

    public boolean updateReOpenProposalDetail(InEligibleProposalDetailsRequest inEliProReq);

    public boolean updateStatus(InEligibleProposalDetailsRequest inEliProReq);

    public Boolean checkIsExistOfflineProposalByApplicationId(Long applicationId);

}
