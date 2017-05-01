package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;

public interface CorporateApplicantDetailRepository extends JpaRepository<CorporateApplicantDetail, Long> {

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.id =:id")
	public CorporateApplicantDetail getByApplicationAndID(@Param("id") Long id,
			@Param("applicationId") Long applicationId);
}
