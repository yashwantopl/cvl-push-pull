package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.StrategicAlliancesResponse;
import com.opl.service.loans.domain.fundseeker.corporate.StrategicAlliancesDetail;

/**
 * @author Sanket
 *
 */
public interface StrategicAlliancesDetailRepository extends JpaRepository<StrategicAlliancesDetail, Long> {

	@Modifying
	@Transactional
	@Query("update StrategicAlliancesDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveStrategicAlliancesDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update StrategicAlliancesDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveStrategicAlliancesDetailsByAppId(@Param("applicationId")Long applicationId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.StrategicAlliancesResponse(a.keyAlliancePartners,a.name,a.relationshipDetails) from StrategicAlliancesDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<StrategicAlliancesResponse> listByApplicationId(@Param("applicationId")Long applicationId);
	
	public List<StrategicAlliancesDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
}
