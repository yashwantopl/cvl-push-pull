package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.model.teaser.finalview.DriverForFutureGrowthResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DriverForFutureGrowthDetail;

import java.util.List;

/**
 * @author Sanket
 *
 */
public interface DriverForFutureGrowthDetailRepository extends JpaRepository<DriverForFutureGrowthDetail, Long>{

	@Modifying
	@Transactional
	@Query("update DriverForFutureGrowthDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveDriverForFutureGrowthDetails(@Param("sId")Long storageDetailsId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.DriverForFutureGrowthResponse(a.firstString,a.secondString,a.thirdString,a.forthString) from DriverForFutureGrowthDetail a where a.applicationId.id= :applicationId and isActive=true")
	List<DriverForFutureGrowthResponse> listByApplicationId(@Param("applicationId") Long applicationId);
}
