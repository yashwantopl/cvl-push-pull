package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetails;

public interface FsPastPerformanceDetailRepository extends JpaRepository<FsPastPerformanceDetails, Long> {

	@Query("from FsPastPerformanceDetails  a where a.applicationId=:applicationId AND a.isActive=true")
	public List<FsPastPerformanceDetails> getList(@Param("applicationId") Long applicationId);
}
