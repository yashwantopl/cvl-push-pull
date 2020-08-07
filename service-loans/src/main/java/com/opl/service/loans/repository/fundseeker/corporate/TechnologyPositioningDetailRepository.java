package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.finalview.TechnologyPositioningResponse;
import com.opl.service.loans.domain.fundseeker.corporate.TechnologyPositioningDetail;

/**
 * @author Sanket
 *
 */
public interface TechnologyPositioningDetailRepository extends JpaRepository<TechnologyPositioningDetail, Long> {

	@Modifying
	@Transactional
	@Query("update TechnologyPositioningDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveTechnologyPositioningDetails(@Param("sId")Long storageDetailsId);
	
	@Modifying
	@Transactional
	@Query("update TechnologyPositioningDetail a set a.isActive = false where a.applicationId.id= :applicationId and a.isActive=true")
	public void inActiveTechnologyPositioningDetailsByAppId(@Param("applicationId")Long applicationId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.TechnologyPositioningResponse(a.details,a.type) from TechnologyPositioningDetail a where a.applicationId.id= :applicationId and isActive=true")
	List<TechnologyPositioningResponse> listByApplicationId(@Param("applicationId") Long applicationId);
	
	public List<TechnologyPositioningDetail> findByApplicationIdIdAndIsActive(Long applicationId, Boolean isActive);
}
