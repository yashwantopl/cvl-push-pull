package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;

public interface IndustrySectorRepository extends JpaRepository<IndustrySectorDetail, Long> {

	@Modifying
	@Query("update IndustrySectorDetail isd set isd.isActive = false where isd.applicationId =:applicationId and isd.isActive = true")
	public int inActiveMappingByApplicationId(@Param("applicationId") Long applicationId);

}
