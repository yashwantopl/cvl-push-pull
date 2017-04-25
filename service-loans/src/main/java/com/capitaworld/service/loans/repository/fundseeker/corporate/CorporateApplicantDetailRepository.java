package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;

public interface CorporateApplicantDetailRepository extends JpaRepository<CorporateApplicantDetail, Long> {
	@Modifying
	@Query("update CorporateApplicantDetail cad set cad.name =:name,cad.groupName =:groupName,cad.constitutionId =:constitutionId,cad.panNo =:panNo,cad.establishmentYear =:establishmentYear,cad.establishmentMonth =:establishmentMonth,cad.websiteAddress =:websiteAddress,cad.registeredPremiseNumber =:registeredPremiseNumber,cad.registeredStreetName =:registeredStreetName,cad.registeredLandMark =:registeredLandMark,cad.registeredCountryId =:registeredCountryId,cad.registeredStateId =:registeredStateId,cad.registeredCityId =:registeredCityId,cad.registeredPincode =:registeredPincode,cad.sameAs =:sameAs,cad.administrativePremiseNumber =:administrativePremiseNumber,cad.administrativeLandMark =:administrativeLandMark,cad.administrativeCountryId =:administrativeCountryId,cad.administrativeStateId =:administrativeStateId,cad.administrativeCityId =:administrativeCityId")
	public int inActiveMappingByApplicationId(@Param("applicationId") Long applicationId);
}
