package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.corporate.DprUserDataDetail;
import com.capitaworld.service.loans.model.teaser.finalview.DprUserDataDetailResponse;

/**
 * @author Sanket
 *
 */
public interface DprUserDataDetailRepository extends JpaRepository<DprUserDataDetail, Long> {

	@Modifying
	@Transactional
	@Query("update DprUserDataDetail a set a.isActive = false where a.storageDetailsId= :sId")
	public void inActiveDprUserDataDetails(@Param("sId")Long storageDetailsId);
	
	@Query("select new com.capitaworld.service.loans.model.teaser.finalview.DprUserDataDetailResponse(a.absenceCivicRestrictions, a.competitiveLandscape, a.globalScenario, a.keyPlayers, a.labourAvailability, a.manufacturingProcess, a.marketForTheProduct, a.marketGrowth, a.marketNeeds, a.marketTrends, a.marketsCurrentlyServed, a.nationalScenario, a.otherAvailability, a.otherBenefits, a.powerAvailability, a.projectJjustification, a.proximityToSourceRawMaterials, a.shiftsInDayNumber, a.specialFeaturesProductsAndServices, a.targetMarketStrategy, a.technicalKnowHow, a.transportAvailability, a.waterAvailability, a.whetherClearanceIsObtainedFromPollutionControlAuthority, a.working_Days_in_month__number) from DprUserDataDetail a where a.applicationId.id= :applicationId and isActive=true")
	 public List<DprUserDataDetailResponse> listByApplicationId(@Param("applicationId")Long applicationId);
	 

}
