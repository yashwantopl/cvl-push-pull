package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.model.teaser.finalview.StrategicAlliancesResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.StrategicAlliancesDetail;

import java.util.List;

/**
 * @author Sanket
 *
 */
public interface StrategicAlliancesDetailRepository extends JpaRepository<StrategicAlliancesDetail, Long> {

	@Modifying
	@Transactional
	@Query("update StrategicAlliancesDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveStrategicAlliancesDetails(@Param("sId")Long storageDetailsId);

	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.StrategicAlliancesResponse(a.keyAlliancePartners,a.name,a.relationshipDetails) from StrategicAlliancesDetail a where a.applicationId.id= :applicationId and isActive=true")
	public List<StrategicAlliancesResponse> listByApplicationId(@Param("applicationId")Long applicationId);
}
