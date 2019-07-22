package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicantDetail,Long> {

    @Query("select fn.applicationId.id,fn.firstName,mf.lastName,mf.middleName,mf.birthDate,mf.genderId,mf.mobile,mf.email,mf.addressSameAsAadhar,mf.currentDistrict,mf.aadharDistrict,mf.currentHouse,mf.aadharHouse,mf.currentLandmark,mf.aadharLandmark,mf.currentLocation,mf.aadharLocation,mf.currentState,mf.aadharState,mf.currentStreet,mf.aadharStreet,mf.currentVtc,mf.aadharVtc,mf.aadharSubdist,mf.currentSubdist,mf.aadharPo,mf.currentPo,mf.aadharCareOf,mf.addressPincode,mf.aadharPincode,mf.addressProofType from MFIApplicantDetail fn where fn.applicationId = :appId and fn.isActive = true")
    public List findAadharDetailsByAppId(@Param("appId") Long appId);
}
