package com.opl.service.loans.repository.sidbi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.sidbi.MeansOfFinanceDetail;


public interface MeansOfFinanceDetailRepository extends JpaRepository<MeansOfFinanceDetail, Long>{

	@Query("from MeansOfFinanceDetail  a where a.applicationId=:id and a.createdBy =:userId AND a.isActive=true")
	public List<MeansOfFinanceDetail> listFinanceMeansFromAppId(@Param("id") Long id, @Param("userId") Long userId);

	@Modifying
	@Query("update MeansOfFinanceDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
}
