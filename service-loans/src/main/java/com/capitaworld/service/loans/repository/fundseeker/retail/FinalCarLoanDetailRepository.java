package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.FinalCarLoanDetail;

public interface FinalCarLoanDetailRepository
		extends JpaRepository<FinalCarLoanDetail, Long> {

	@Query("from FinalCarLoanDetail car where car.applicationId.id =:applicationId and car.applicationId.userId=:userId and car.isActive=true")
	public FinalCarLoanDetail getByApplicationID(@Param("applicationId") Long applicationId,@Param("userId") Long userId);
}
