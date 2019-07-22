package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicantDetail,Long> {

    @Query("select fn.applicationId.id,fn.firstName,fn.lastName,fn.middleName,fn.birthDate,fn.genderId,fn.mobile,fn.email,fn.addressSameAsAadhar,fn.currentDistrict,fn.aadharDistrict,fn.currentHouse,fn.aadharHouse,fn.currentLandmark,fn.aadharLandmark,fn.currentLocation,fn.aadharLocation,fn.currentState,fn.aadharState,fn.currentStreet,fn.aadharStreet,fn.currentVtc,fn.aadharVtc,fn.aadharSubdist,fn.currentSubdist,fn.aadharPo,fn.currentPo,fn.aadharCareOf,fn.addressPincode,fn.aadharPincode,fn.addressProofType from MFIApplicantDetail fn where fn.applicationId = :appId and fn.isActive = true")
    public List findAadharDetailsByAppId(@Param("appId") Long appId);
}
