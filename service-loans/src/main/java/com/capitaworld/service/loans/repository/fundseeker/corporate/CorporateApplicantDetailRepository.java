package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;

public interface CorporateApplicantDetailRepository extends JpaRepository<CorporateApplicantDetail, Long> {

	@Query("from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true")
	public CorporateApplicantDetail getByApplicationAndUserId(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	
	public CorporateApplicantDetail findOneByApplicationIdId(Long applicationId);

	
	@Query("select count(cr.applicationId.id) from CorporateApplicantDetail cr where cr.applicationId.id =:applicationId and cr.applicationId.userId =:userId and cr.isActive=true and (cr.organisationName != NULL and cr.organisationName != '')")
	public Long hasAlreadyApplied(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);

}
