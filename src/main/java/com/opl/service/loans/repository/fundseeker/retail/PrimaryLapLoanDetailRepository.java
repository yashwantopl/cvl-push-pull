package com.opl.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;

public interface PrimaryLapLoanDetailRepository extends JpaRepository<PrimaryLapLoanDetail, Long> {

	@Query("from PrimaryLapLoanDetail lap where lap.applicationId.id =:applicationId and isActive=true and lap.applicationId.userId =:userId")
	public PrimaryLapLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long userId);
}
