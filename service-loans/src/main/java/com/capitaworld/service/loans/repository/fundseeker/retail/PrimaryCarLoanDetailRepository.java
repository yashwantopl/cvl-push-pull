package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryCarLoanDetail;

public interface PrimaryCarLoanDetailRepository
		extends JpaRepository<PrimaryCarLoanDetail, Long> {

	@Query("from PrimaryCarLoanDetail car where car.applicationId.id =:applicationId and isActive=true")
	public PrimaryCarLoanDetail getByApplicationID(@Param("applicationId") Long applicationId);
}
