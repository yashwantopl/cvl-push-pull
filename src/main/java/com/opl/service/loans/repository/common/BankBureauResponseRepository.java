package com.opl.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.common.BankBureauResponse;

public interface BankBureauResponseRepository extends JpaRepository<BankBureauResponse,Long>{
	

	@Modifying
	@Query("update BankBureauResponse set modifiedDate = NOW(),isActive = FALSE where type =:type and isActive = TRUE")
	public int inactiveByType(@Param("type")Integer type);
	
	@Modifying
	@Query("update BankBureauResponse set modifiedDate = NOW(),isActive = FALSE where applicationId =:applicationId and fpProductId =:fpProductId and type =:type and isActive = TRUE")
	public int inactiveByAppAndFpProductIdAndType(@Param("applicationId")Long applicationId,@Param("fpProductId")Long fpProductId,@Param("type")Integer type);
	
	@Modifying
	@Query("update BankBureauResponse set modifiedDate = NOW(),isActive = FALSE where applicationId =:applicationId and scoringModelId =:scoringModelId and fieldMasterId =:fieldMasterId and type =:type and isActive = TRUE")
	public int inactiveByAppAndScoringModelAndFieldMasterIdAndType(@Param("applicationId")Long applicationId,@Param("scoringModelId")Long scoringModelId,@Param("fieldMasterId")Long fieldMasterId,@Param("type")Integer type);
	
	public BankBureauResponse findFirstByApplicationIdAndFpProductIdAndTypeOrderByIdDesc(Long applicationId,Long fpProductId,Integer type);

}
