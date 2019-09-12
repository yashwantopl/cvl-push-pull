package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.FinalAutoLoanCoApplicantDetail;

public interface FinalAutoLoanCoAppDetailRepository  extends JpaRepository<FinalAutoLoanCoApplicantDetail, Long> {

	@Query("from FinalAutoLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.isActive=true")
	public FinalAutoLoanCoApplicantDetail getByApplicationAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

	@Query("from FinalAutoLoanCoApplicantDetail  hm where hm.applicationId.id =:applicationId and hm.proposalId.proposalId=:proposalId and hm.coApplicantId=:coAppId and hm.isActive=true")
	public FinalAutoLoanCoApplicantDetail getByApplicationAndProposalIdAndCoAppId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId,@Param("coAppId") Long coAppId);

}
