package com.opl.service.loans.repository;

import com.opl.service.loans.domain.PastVehicleLoanDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public interface PastVehicleLoanDetailRepository extends JpaRepository<PastVehicleLoanDetail,Long> {
	
	@Modifying
	@Query("DELETE FROM PastVehicleLoanDetail lm where lm.applicationId =:applicationId")
	public void deleteByApplicationId(@Param("applicationId") Long applicationId);
	
	List<PastVehicleLoanDetail> findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);

	@Query("SELECT sum(sanctionAmt) FROM  PastVehicleLoanDetail  WHERE applicationId =:applicationId and isActive =:isActive group by applicationId")
	public Double getTotalSanctionAmount(@Param("applicationId") Long applicationId,@Param("isActive") Boolean isActive);
}
