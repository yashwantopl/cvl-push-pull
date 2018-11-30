package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationProposalMappingRepository extends JpaRepository<ApplicationProposalMapping, Long> {

    @Query("select count(proposal_mapping_id) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isFinalLocked=1 and apm.isActive = true")
    Long checkFinalDetailIsLocked(@Param("proposalId") Long proposalId);

    @Query("select count(proposalId) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isPrimaryLocked=1 and apm.isActive = true")
    Long checkPrimaryDetailIsLocked(@Param("proposalId") Long proposalId);
    
    @Query("from ApplicationProposalMapping lm where lm.applicationId =:applicationId and lm.orgId =:orgId and lm.isActive = true")
	public ApplicationProposalMapping getByApplicationIdAndOrgId(@Param("applicationId") Long applicationId, @Param("orgId") Long orgId);
}
