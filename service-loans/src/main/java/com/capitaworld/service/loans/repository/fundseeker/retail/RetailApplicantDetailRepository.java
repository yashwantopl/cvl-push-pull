package com.capitaworld.service.loans.repository.fundseeker.retail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;

public interface RetailApplicantDetailRepository extends JpaRepository<RetailApplicantDetail, Long> {

	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true")
	public RetailApplicantDetail getByApplicationAndUserId(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	@Query("select rt.currencyId from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true")
	public Integer getCurrency(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
}
