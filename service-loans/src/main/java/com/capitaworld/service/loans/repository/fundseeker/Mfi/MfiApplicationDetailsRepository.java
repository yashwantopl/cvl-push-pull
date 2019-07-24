package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicantDetail,Long> {

    @Query("select new com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq(fn.applicationId.id,fn.firstName,fn.lastName,fn.middleName,fn.birthDate,fn.genderId,fn.mobile,fn.email,fn.addressSameAsAadhar,fn.currentDistrict,fn.aadharDistrict,fn.currentHouse,fn.aadharHouse,fn.currentLandmark,fn.aadharLandmark,fn.currentLocation,fn.aadharLocation,fn.currentState,fn.aadharState,fn.currentStreet,fn.aadharStreet,fn.currentVtc,fn.aadharVtc,fn.aadharSubdist,fn.currentSubdist,fn.aadharPo,fn.currentPo,fn.aadharCareOf,fn.addressPincode,fn.aadharPincode,fn.addressProofType) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
    public List<AadharDetailsReq> findAadharDetailsByAppId(@Param("appId") Long appId);
    
    
//    @Query("select new com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq(fn.applicationId.id,fn.fatherName,fn.motherName,fn.spouseName,fn.spouseBirthDate,fn.noDependent,fn.spouseMobile,fn.nomineeName,fn.nomineeBirthDate,\n" + 
//    		"			fn.relationWithNomineeId, fn.nomineeAddress, fn.nomineePincode, fn.educationQualification,\n" + 
//    		"			fn.religion,fn.cast,fn.landHolding,fn.houseType,fn.nameOfFirm,\n" + 
//    		"		fn.businessType, fn.lifeInsurance, fn.sumInsured, fn.nomineeState, fn.nomineeCity,\n" + 
//    		"			fn.nomineeDistrict,fn.nomineeLocation, fn.nomineeHouseNo,fn.nomineeLandmark,\n" + 
//    		"			fn.academicReligion, fn.academicCaste, fn.isAcademicLifeInsurance, fn.houseOwnership,\n" + 
//    		"			fn.areaType, fn.businessPremises, fn.expInSameLine, fn.academicSumInsured,\n" + 
//    		"			fn.isPersonalDetailsFilled) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
//    public List<PersonalDetailsReq> findPersonalDetailsByAppId(@Param("appId") Long appId);
    
}
