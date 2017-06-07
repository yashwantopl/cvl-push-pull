package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;

public interface PrimaryCarLoanDetailRepository
		extends JpaRepository<PrimaryCarLoanDetail, Long> {

	@Query("from PrimaryCarLoanDetail car where car.applicationId.id =:applicationId and car.applicationId.userId =:userId and isActive=true")
	public PrimaryCarLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);
}
