package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.ExistingProductDetail;

/**
 * @author Sanket
 *
 */
public interface ExistingProductDetailsRepository extends JpaRepository<ExistingProductDetail, Long> {

	@Query("from ExistingProductDetail  a where a.applicationId.id=:id and a.applicationId.userId =:userId AND a.isActive=true")
	public List<ExistingProductDetail> listExistingProductFromAppId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("from ExistingProductDetail  a where a.applicationId.id=:id AND a.isActive=true")
	public List<ExistingProductDetail> listExistingProductFromAppId(@Param("id") Long id);

	@Modifying
	@Query("update ExistingProductDetail pm set pm.isActive = false , pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId, @Param("applicationId") Long applicationId);

}
