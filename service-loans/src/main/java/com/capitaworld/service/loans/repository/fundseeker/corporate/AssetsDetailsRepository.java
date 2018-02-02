package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.AssetsDetails;

public interface AssetsDetailsRepository extends JpaRepository<AssetsDetails, Long> {
	
	@Modifying
	@Transactional
	@Query("update AssetsDetails a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveAssetsDetails(@Param("sId") Long storageDetailsId);
	
	@Query("from AssetsDetails a where a.loanApplicationMaster = :appId and a.year = :yr and a.isActive = true")
	public AssetsDetails getAssetsDetails(@Param("appId") Long applicationId, @Param("yr") String year);
}
