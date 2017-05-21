package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinanceMeansDetail;

public interface FinanceMeansDetailRepository extends JpaRepository<FinanceMeansDetail, Long> {

	@Query("from FinanceMeansDetail  a where a.applicationId.id=:id and a.applicationId.userId =:userId AND a.isActive=true")
	public List<FinanceMeansDetail> listFinanceMeansFromAppId(@Param("id") Long id, @Param("userId") Long userId);
	
}
