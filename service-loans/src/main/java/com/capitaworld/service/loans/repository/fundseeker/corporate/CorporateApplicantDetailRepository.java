package com.capitaworld.service.loans.repository.fundseeker.corporate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;

public interface CorporateApplicantDetailRepository extends JpaRepository<CorporateApplicantDetail, Long> {
//	@Modifying
//	@Query("update CorporateApplicantDetail cad set cad.name =:name,cad.groupName =:groupName,cad.constitutionId =:constitutionId,cad.panNo =:panNo,cad.establishmentYear =:establishmentYear,cad.establishmentMonth =:establishmentMonth,cad.websiteAddress =:websiteAddress,cad.registeredPremiseNumber =:registeredPremiseNumber,cad.registeredStreetName =:registeredStreetName,cad.registeredLandMark =:registeredLandMark,cad.registeredCountryId =:registeredCountryId,cad.registeredStateId =:registeredStateId,cad.registeredCityId =:registeredCityId,cad.registeredPincode =:registeredPincode,cad.sameAs =:sameAs,cad.administrativePremiseNumber =:administrativePremiseNumber,cad.administrativeStreetName =:administrativeStreetName,cad.administrativeLandMark =:administrativeLandMark,cad.administrativeCountryId =:administrativeCountryId,cad.administrativeStateId =:administrativeStateId,cad.administrativeCityId =:administrativeCityId,cad.administrativePincode =:administrativePincode,cad.landlineNo =:landlineNo,cad.latitude =:latitude,cad.longitude =:longitude,cad.aboutUs =:aboutUs,cad.keyVericalFunding =:keyVericalFunding,cad.modifiedDate = NOW(),cad.modifiedBy =:modifiedDate where cad.applicationId.id =:applicationId and cad.isActive = true")
//	public int inActiveMappingByApplicationId(@Param("name") String name, @Param("groupName") String groupName,
//			@Param("constitutionId") Long constitutionId, @Param("panNo") String panNo,
//			@Param("establishmentYear") Integer establishmentYear,
//			@Param("establishmentMonth") Integer establishmentMonth, @Param("websiteAddress") String websiteAddress,
//			@Param("registeredPremiseNumber") String registeredPremiseNumber,
//			@Param("registeredStreetName") String registeredStreetName,
//			@Param("registeredLandMark") String registeredLandMark,
//			@Param("registeredCountryId") Integer registeredCountryId,
//			@Param("registeredStateId") Long registeredStateId, @Param("registeredCityId") Long registeredCityId,
//			@Param("registeredPincode") Long registeredPincode, @Param("sameAs") Boolean sameAs,
//			@Param("administrativePremiseNumber") String administrativePremiseNumber, @Param("registeredPincode") Long registeredPincode,
//			@Param("registeredPincode") Long registeredPincode, @Param("registeredPincode") Long registeredPincode,
//			@Param("registeredPincode") Long registeredPincode, @Param("registeredPincode") Long registeredPincode,
//			@Param("registeredPincode") Long registeredPincode, @Param("registeredPincode") Long registeredPincode);
}
