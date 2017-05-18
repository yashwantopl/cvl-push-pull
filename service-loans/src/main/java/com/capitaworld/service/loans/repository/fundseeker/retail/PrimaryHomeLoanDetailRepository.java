package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryHomeLoanDetail;

public interface PrimaryHomeLoanDetailRepository
		extends JpaRepository<PrimaryHomeLoanDetail, Long> {

	@Query("from PrimaryHomeLoanDetail hl where hl.applicationId.id =:applicationId and isActive=true")
	public PrimaryHomeLoanDetail getByApplicationID(@Param("applicationId") Long applicationId);
}
