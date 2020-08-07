package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.FinalAutoLoanDetail;

public interface FinalAutoLoanDetailRepository
		extends JpaRepository<FinalAutoLoanDetail, Long> {

	@Query("from FinalAutoLoanDetail car where car.applicationId.id =:applicationId and car.applicationId.userId=:userId and car.isActive=true")
	public FinalAutoLoanDetail getByApplicationIdAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);
	
	@Query("from FinalAutoLoanDetail car where car.applicationId.id =:applicationId and car.proposalId.proposalId=:proposalId and car.isActive=true")
	public FinalAutoLoanDetail getByApplicationAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long userId);

}
