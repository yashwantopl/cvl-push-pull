package com.capitaworld.service.loans.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.SchedulerDataMultipleBank;

public interface SchedulerDataMultipleBankRepository extends JpaRepository<SchedulerDataMultipleBank, Long>{

	@Query("FROM SchedulerDataMultipleBank b WHERE b.applicationId=:app_id and isActive=1")
	public List<SchedulerDataMultipleBank> getDataByApplicationId(@Param(value="app_id") Long applicationId);
}
