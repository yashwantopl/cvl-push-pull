package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalTermLoanDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FinalUnsecureLoanDetail;

public interface FinalUnsecuredLoanDetailRepository extends JpaRepository<FinalUnsecureLoanDetail, Long> {
	
	@Query("from FinalUnsecureLoanDetail pd where pd.applicationId.id =:applicationId and pd.applicationId.userId =:userId")
	public FinalUnsecureLoanDetail getByApplicationAndUserId(@Param("applicationId") Long applicationId,
			@Param("userId") Long id);
}
