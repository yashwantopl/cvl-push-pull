package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq;
import com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq;
import com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicantDetail,Long> {

    @Query("from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.type= :type and fn.isActive = true")
    public MFIApplicantDetail findByAppIdAndType(@Param("appId") Long appId,@Param("type") Integer type);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq(fn.applicationId.id,fn.firstName,fn.lastName,fn.middleName,fn.birthDate,fn.genderId,fn.mobile,fn.email,fn.addressSameAsAadhar,fn.currentDistrict,fn.aadharDistrict,fn.currentHouse,fn.aadharHouse,fn.currentLandmark,fn.aadharLandmark,fn.currentLocation,fn.aadharLocation,fn.currentState,fn.aadharState,fn.currentStreet,fn.aadharStreet,fn.currentVtc,fn.aadharVtc,fn.aadharSubdist,fn.currentSubdist,fn.aadharPo,fn.currentPo,fn.aadharCareOf,fn.addressPincode,fn.aadharPincode,fn.addressProofType) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
    public List<AadharDetailsReq> findAadharDetailsByAppId(@Param("appId") Long appId);

    @Modifying
    @Query("update MFIApplicantDetail set isBankDetailsFilled = true where applicationId.id =:appId")
    public int updateBankFilledFlag(@Param("appId") Long appId);
 
    @Query("select mf from MFIApplicantDetail mf where mf.applicationId.id =:applicationId and mf.isActive = true")
    public List<MFIApplicantDetail> findByApplicationIdAndIsActive(@Param("applicationId") Long applicationId);
     

    @Query("select new com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq(fn.applicationId.id,fn.fatherName,fn.motherName,fn.spouseName,fn.spouseBirthDate,fn.noDependent,fn.spouseMobile,fn.nomineeName,fn.nomineeBirthDate,\n" +
    		"			fn.relationWithNomineeId, fn.nomineeAddress, fn.nomineePincode, fn.educationQualification,\n" +
    		"			fn.religion,fn.landHolding,fn.houseType,fn.nameOfFirm,\n" +
    		"		fn.businessType, fn.nomineeState, fn.nomineeCity,\n" +
    		"			fn.nomineeDistrict,fn.nomineeLocation, fn.nomineeHouseNo,fn.nomineeLandmark,\n" +
    		"			fn.academicReligion, fn.academicCaste, fn.isAcademicLifeInsurance, fn.houseOwnership,\n" +
    		"			fn.areaType, fn.businessPremises, fn.expInSameLine, fn.academicSumInsured,\n" +
    		"			fn.isPersonalDetailsFilled) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
    public List<PersonalDetailsReq> findPersonalDetailsByAppId(@Param("appId") Long appId);
    
    @Query("select new com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq(fn.applicationId.id,fn.loanType,fn.purposeOfLoan,fn.loanAmountRequired,fn.costOfProject,fn.costOfEquipment,fn.workingCapOfEquipment,fn.totalCostEquipment,fn.promoterContribution,fn.loanRequiredFromSidbi,fn.totalMeanFinance,fn.totalCashFlow,fn.repaymentFrequency,fn.insurenceRequired,fn.insurenceCompanyName,fn.insurencePremium,fn.isProjectDetailsFilled) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
    public List<ProjectDetailsReq> findProjectDetailsByAppId(@Param("appId") Long appId);
    
    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq(fn.applicationId.id,fn.shipShgiInstallment,fn.otherInstallment,fn.loanInstallment,fn.educationExpense,fn.medicalExpense,fn.foodExpense,fn.otherExpense,fn.businessInBrief,fn.monthlyCashflow,fn.monthlyExpenditure,fn.monthlyIncome,fn.ppiNoFamilyMember,fn.ppiAcadamicHeadFamily,fn.ppiRafrigeratorInFamily,fn.ppiStoveInFamily,fn.ppiPressureCookerInFamily,fn.ppiTvInFamily,fn.ppiFanInFamily,fn.ppiVehicleInFamily,fn.ppiDressingTableInFamily,fn.ppiOtherTableInFamily) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
	public List<MfiIncomeAndExpenditureReq> findIncomeAndExpenditureDetailsByAppId(@Param("appId") Long appId);
    
    
    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq(fn.applicationId.id,fn.purposeOfLoan,fn.clientType,fn.isBusinessPremiseVisited,fn.repaymentTrack,fn.creaditWorthiness,fn.loanLiabilityRatio,fn.competition,fn.loanAmountRecomandation,fn.tenureRecomandation,fn.moratoriumRecomandation,fn.interestRateRecomandation,fn.installmentRecomandation) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true")
    public List<MfiLoanAssessmentDetailsReq> findLoanAssessmentDetailsByAppId(@Param("appId") Long appId);
    
    
}
