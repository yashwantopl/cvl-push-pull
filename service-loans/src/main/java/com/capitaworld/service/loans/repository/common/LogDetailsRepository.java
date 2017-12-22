package com.capitaworld.service.loans.repository.common;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.common.LogDetails;

public interface LogDetailsRepository extends JpaRepository<LogDetails, Long>{
	@Query("select lm.createdDate from LogDetails lm where lm.loanApplicationMasterId =:id and lm.dateTypeMasterId =:dateType")
	public Date getDateByLogType(@Param("id") Long applicationId, @Param("dateType") Integer dateType);

	@Query(value = "SELECT log_details.created_date FROM log_details WHERE log_details.loan_application_master_id=:applicationId AND log_details.product_mapping_id=:fpProductId AND log_details.date_type_master_id='13' ORDER BY log_details.created_date DESC LIMIT 1", nativeQuery = true)
	public Date getDateByADFForAdminPanel(@Param("applicationId") Long applicationId, @Param("fpProductId") Long fpProductId);
}
