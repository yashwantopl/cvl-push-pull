package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.DriverForFutureGrowthResponse;
import com.opl.service.loans.domain.fundseeker.corporate.DriverForFutureGrowthDetail;

/**
 * @author Sanket
 *
 */
public interface DriverForFutureGrowthDetailRepository extends JpaRepository<DriverForFutureGrowthDetail, Long>{

	@Modifying
	@Transactional
	@Query("update DriverForFutureGrowthDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveDriverForFutureGrowthDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update DriverForFutureGrowthDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveDriverForFutureGrowthDetailsByAppId(@Param("applicationId") Long applicationId);

	@Query("select new com.opl.mudra.api.loans.model.teaser.finalview.DriverForFutureGrowthResponse(a.firstString,a.secondString,a.thirdString,a.forthString) from DriverForFutureGrowthDetail a where a.applicationId.id= :applicationId and isActive=true")
	List<DriverForFutureGrowthResponse> listByApplicationId(@Param("applicationId") Long applicationId);
	
	public List<DriverForFutureGrowthDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
}
