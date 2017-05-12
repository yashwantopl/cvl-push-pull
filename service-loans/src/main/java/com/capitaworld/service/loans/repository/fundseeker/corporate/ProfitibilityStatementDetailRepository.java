package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ProfitibilityStatementDetail;

public interface ProfitibilityStatementDetailRepository extends JpaRepository<ProfitibilityStatementDetail, Long>{

	@Modifying
	@Transactional
	@Query("update ProfitibilityStatementDetail p set p.isActive = false where p.storageDetailsId= :sId")
	public void inActiveProfitibilityStatementDetail(@Param("sId") Long storageDetailsId);
	
}
