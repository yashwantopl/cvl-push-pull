package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.KeyManagementDetail;

/**
 * @author Sanket
 *
 */
public interface KeyManagementDetailRepository extends JpaRepository<KeyManagementDetail, Long> {

	@Modifying
	@Transactional
	@Query("update KeyManagementDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveKeyManagementDetails(@Param("sId")Long storageDetailsId);

}
