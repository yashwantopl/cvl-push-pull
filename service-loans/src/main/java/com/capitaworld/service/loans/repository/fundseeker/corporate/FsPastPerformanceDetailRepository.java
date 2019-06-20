package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinanceMeansDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.FsPastPerformanceDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FsPastPerformanceDetailRepository extends JpaRepository<FsPastPerformanceDetails, Long> {

	@Query("from FsPastPerformanceDetails  a where a.applicationId=:applicationId AND a.isActive=true")
	public List<FsPastPerformanceDetails> getList(@Param("applicationId") Long applicationId);
}
