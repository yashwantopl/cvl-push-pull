package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.FinalHomeLoanDetail;

public interface FinalHomeLoanDetailRepository
		extends JpaRepository<FinalHomeLoanDetail, Long> {

	@Query("from FinalHomeLoanDetail hm where hm.applicationId.id =:applicationId and hm.applicationId.userId=:userId and hm.isActive=true")
	public FinalHomeLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);
}
