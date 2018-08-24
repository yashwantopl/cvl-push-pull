package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PromotorBackgroundDetail;

/**
 * @author Sanket
 *
 */
public interface PromotorBackgroundDetailsRepository extends JpaRepository<PromotorBackgroundDetail, Long> {

	@Query("from PromotorBackgroundDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and isActive = true")
	public List<PromotorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id,@Param("userId")Long userId);
	
	@Query("from PromotorBackgroundDetail o where o.applicationId.id = :id and isActive = true")
	public List<PromotorBackgroundDetail> listPromotorBackgroundFromAppId(@Param("id") Long id);
	
	public PromotorBackgroundDetail findByIdAndIsActive(Long id, Boolean isActive);

	@Modifying
	@Query("update PromotorBackgroundDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
}
