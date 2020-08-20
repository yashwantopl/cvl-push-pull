package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.KeyManagementResponse;
import com.opl.service.loans.domain.fundseeker.corporate.KeyManagementDetail;

/**
 * @author Sanket
 *
 */
public interface KeyManagementDetailRepository extends JpaRepository<KeyManagementDetail, Long> {

	@Modifying
	@Transactional
	@Query("update KeyManagementDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveKeyManagementDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update KeyManagementDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveKeyManagementDetailsByAppId(@Param("applicationId") Long applicationId);

	@Query("select new com.opl.mudra.api.loans.model.teaser.finalview.KeyManagementResponse(a.anySpecialAchievement,a.designation,a.experience,a.functionalDuties,a.name,a.qualification) from KeyManagementDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<KeyManagementResponse> listByApplicationId(@Param("applicationId")Long applicationId);
	
	public List<KeyManagementDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
}
