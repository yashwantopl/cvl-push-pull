package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;

public interface PrimaryWorkingCapitalLoanDetailRepository
		extends JpaRepository<PrimaryWorkingCapitalLoanDetail, Long> {

	@Query("from PrimaryWorkingCapitalLoanDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
	public PrimaryWorkingCapitalLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long id);
}
