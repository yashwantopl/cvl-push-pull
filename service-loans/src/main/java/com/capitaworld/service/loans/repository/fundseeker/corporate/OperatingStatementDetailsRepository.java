package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OperatingStatementDetails;

public interface OperatingStatementDetailsRepository  extends JpaRepository<OperatingStatementDetails, Long>{
	
	@Modifying
	@Transactional
	@Query("update OperatingStatementDetails o set o.isActive = false where o.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);

	@Query("select OperatingStatementDetails o where o.application_id = :appId and o.year = :yr and o.isActive = true")
	public OperatingStatementDetails getOperatingStatementDetails(@Param("appId") Long applicationId, @Param("yr") String year);
}
