package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;

public interface FinalHomeLoanDetailRepository
		extends JpaRepository<FinalHomeLoanDetail, Long> {

	@Query("from FinalHomeLoanDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.isActive=true")
	public FinalHomeLoanDetail getByApplicationAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);
}
