package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.FinalHomeLoanCoApplicantDetail;

public interface FinalHomeLoanCoAppDetailRepository
		extends JpaRepository<FinalHomeLoanCoApplicantDetail, Long> {

	@Query("from FinalHomeLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.isActive=true")
	public FinalHomeLoanCoApplicantDetail getByApplicationAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

	@Query("from FinalHomeLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.coApplicantId=:coAppId and hm.isActive=true")
	public FinalHomeLoanCoApplicantDetail getByApplicationAndProposalIdAndCoAppId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId,@Param("coAppId") Long coAppId);

}
