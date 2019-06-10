package com.capitaworld.service.loans.repository.fundseeker.retail;

import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanCoApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FinalHomeLoanCoAppDetailRepository
		extends JpaRepository<FinalHomeLoanCoApplicantDetail, Long> {

	@Query("from FinalHomeLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.isActive=true")
	public FinalHomeLoanCoApplicantDetail getByApplicationAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

	@Query("from FinalHomeLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.coApplicantId=:coAppId and hm.isActive=true")
	public FinalHomeLoanCoApplicantDetail getByApplicationAndProposalIdAndCoAppId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId,@Param("coAppId") Long coAppId);

}
