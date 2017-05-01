package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryWorkingCapitalLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryLasLoanDetail;

public interface PrimaryLasLoanDetailRepository
		extends JpaRepository<PrimaryLasLoanDetail, Long> {

	@Query("from PrimaryLasLoanDetail pd where pd.applicationId.id =:applicationId and isActive=true")
	public PrimaryWorkingCapitalLoanDetail getByApplicationID(@Param("applicationId") Long applicationId);
}
