package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryPersonalLoanDetail;

public interface PrimaryPersonalLoanDetailRepository extends JpaRepository<PrimaryPersonalLoanDetail, Long> {

	@Query("from PrimaryPersonalLoanDetail pl where pl.applicationId.id =:applicationId and isActive=true and pl.applicationId.userId =:userId")
	public PrimaryPersonalLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long userId);
}
