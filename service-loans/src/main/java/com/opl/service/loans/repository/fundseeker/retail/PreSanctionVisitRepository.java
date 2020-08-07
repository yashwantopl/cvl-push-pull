package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.PreSanctionVisit;

public interface PreSanctionVisitRepository extends JpaRepository<PreSanctionVisit, Long> {
	
	@Query("select a from PreSanctionVisit a where a.applicationId.id =:applicationId and a.applicationProposalMapping.proposalId =:proposalId")
	public PreSanctionVisit getByApplicationIdAndApplicationProposalMapping(@Param("applicationId") Long applicationId , @Param("proposalId") Long proposalId);
	
}
