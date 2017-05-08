package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.RequirementsAndAvailabilityRawMaterialsDetail;

/**
 * @author Sanket
 *
 */
public interface RequirementsAndAvailabilityRawMaterialsDetailRepository extends JpaRepository<RequirementsAndAvailabilityRawMaterialsDetail, Long>{

	@Modifying
	@Transactional
	@Query("update RequirementsAndAvailabilityRawMaterialsDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveRequirementsAndAvailabilityRawMaterialsDetails(@Param("sId")Long storageDetailsId);

}
