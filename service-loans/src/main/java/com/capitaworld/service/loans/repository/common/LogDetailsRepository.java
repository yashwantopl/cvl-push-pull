package com.capitaworld.service.loans.repository.common;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.common.LogDetails;

public interface LogDetailsRepository extends JpaRepository<LogDetails, Long>{
	@Query("select lm.createdDate from LogDetails lm where lm.loanApplicationMasterId =:id and lm.dateTypeMasterId =:dateType")
	public Date getDateByLogType(@Param("id") Long applicationId, @Param("dateType") Integer dateType);

	@Query("select lm.createdDate from LogDetails lm where lm.loanApplicationMasterId =:id and lm.dateTypeMasterId =:dateType and lm.productMappingId =:fpProductId ORDER BY lm.createdDate DESC")
	public Date getDateByADFForAdminPanel(@Param("id") Long applicationId, @Param("fpProductId") Long fpProductId,@Param("dateType") Integer dateType);
}
