package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryTermLoanDetail;

public interface PrimaryTermLoanDetailRepository extends JpaRepository<PrimaryTermLoanDetail, Long> {
	@Query("from PrimaryTermLoanDetail pd where pd.applicationId.id =:applicationId and pd.id =:id")
	public PrimaryTermLoanDetail getByApplicationIDAndID(@Param("applicationId") Long applicationId,
			@Param("id") Long id);
}
