package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.BalanceSheetDetail;

public interface BalanceSheetDetailRepository extends JpaRepository<BalanceSheetDetail, Long>{

	

	@Modifying
	@Transactional
	@Query("update BalanceSheetDetail b set b.isActive = false where b.storageDetailsId= :sId")
	public void inActiveBalanceSheetDetail(@Param("sId") Long storageDetailsId);
	
	@Query("from BalanceSheetDetail b where b.applicationId = :appId and b.year = :yr and b.isActive = true")
	public BalanceSheetDetail getBalanceSheetDetail(@Param("appId") Long applicationId, @Param("yr") String year);
}
