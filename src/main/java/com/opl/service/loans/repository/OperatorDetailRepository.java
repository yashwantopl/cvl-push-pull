package com.opl.service.loans.repository;

import com.opl.service.loans.domain.OperatorDetail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public interface OperatorDetailRepository extends JpaRepository<OperatorDetail,Long> {
	
	@Modifying
	@Query("DELETE FROM OperatorDetail lm where lm.applicationId =:applicationId")
	public void deleteByApplicationId(@Param("applicationId") Long applicationId);
	
	List<OperatorDetail> findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);
}
