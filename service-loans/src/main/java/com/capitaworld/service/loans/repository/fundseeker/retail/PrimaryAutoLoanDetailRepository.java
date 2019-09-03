package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.PrimaryAutoLoanDetail;

public interface PrimaryAutoLoanDetailRepository extends JpaRepository<PrimaryAutoLoanDetail, Long> {

	public PrimaryAutoLoanDetail findById(Long id);
	
	@Query("from PrimaryAutoLoanDetail al where al.applicationId.id =:applicationId and al.isActive=true and al.applicationId.userId =:userId")
	public PrimaryAutoLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);
}
