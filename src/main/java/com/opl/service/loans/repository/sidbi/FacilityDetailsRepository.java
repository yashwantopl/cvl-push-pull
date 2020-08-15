package com.opl.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.sidbi.FacilityDetails;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public interface FacilityDetailsRepository extends JpaRepository<FacilityDetails, Long> {

	@Query("from FacilityDetails  a where a.applicationId =:applicationId AND a.isActive=true")
	public List<FacilityDetails> getFacilityDetailsListAppId(@Param("applicationId") Long applicationId);
}
