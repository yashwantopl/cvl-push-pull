package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLapLoanDetail;

public interface PrimaryLapLoanDetailRepository extends JpaRepository<PrimaryLapLoanDetail, Long> {

	@Query("from PrimaryLapLoanDetail lap where lap.applicationId.id =:applicationId and isActive=true and lap.applicationId.userId =:userId")
	public PrimaryWorkingCapitalLoanDetail getByApplicationID(@Param("applicationId") Long applicationId,
			@Param("userId") Long userId);
}
