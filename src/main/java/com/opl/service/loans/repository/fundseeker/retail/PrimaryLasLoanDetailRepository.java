package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;

public interface PrimaryLasLoanDetailRepository extends JpaRepository<PrimaryLasLoanDetail, Long> {

	@Query("from PrimaryLasLoanDetail las where las.applicationId.id =:applicationId and isActive=true and las.applicationId.userId =:userId")
	public PrimaryLasLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long userId);
}
