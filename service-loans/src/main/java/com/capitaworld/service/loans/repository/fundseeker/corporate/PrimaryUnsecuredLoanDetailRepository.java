package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryUnsecuredLoanDetail;

public interface PrimaryUnsecuredLoanDetailRepository extends JpaRepository<PrimaryUnsecuredLoanDetail, Long> {

	@Query("from PrimaryUnsecuredLoanDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
	public PrimaryUnsecuredLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long id);
	
	public PrimaryUnsecuredLoanDetail findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
	
	
}

