package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.OwnershipDetail;

/**
 * @author Sanket
 *
 */
public interface OwnershipDetailsRepository extends JpaRepository<OwnershipDetail, Long> {

	@Query("from OwnershipDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<OwnershipDetail> listOwnershipFromAppId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("from OwnershipDetail o where o.applicationId.id =:id and o.isActive = true")
	public List<OwnershipDetail> listOwnershipFromAppId(@Param("id") Long id);
	
	@Modifying
	@Query("update OwnershipDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);


}
