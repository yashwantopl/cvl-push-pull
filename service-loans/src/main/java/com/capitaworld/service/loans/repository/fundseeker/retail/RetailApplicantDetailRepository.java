package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;

public interface RetailApplicantDetailRepository extends JpaRepository<RetailApplicantDetail, Long> {

	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.id =:id")
	public RetailApplicantDetail getByApplicationAndID(@Param("id") Long id,
			@Param("applicationId") Long applicationId);
}
