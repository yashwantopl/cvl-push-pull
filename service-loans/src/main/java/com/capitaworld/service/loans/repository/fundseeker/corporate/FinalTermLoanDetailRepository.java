package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;

public interface FinalTermLoanDetailRepository extends JpaRepository<FinalTermLoanDetail, Long> {
	
	@Query("from FinalTermLoanDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
	public FinalTermLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long id);
}
