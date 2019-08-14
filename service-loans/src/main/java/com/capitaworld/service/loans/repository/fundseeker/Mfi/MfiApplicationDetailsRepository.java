package com.capitaworld.service.loans.repository.fundseeker.Mfi;


import com.capitaworld.service.loans.domain.fundseeker.mfi.MFIApplicantDetail;
import com.capitaworld.service.loans.model.micro_finance.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MfiApplicationDetailsRepository extends JpaRepository<MFIApplicantDetail,Long> {

    @Query("from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.type= :type and fn.isActive = true")
    public MFIApplicantDetail findByAppIdAndType(@Param("appId") Long appId,@Param("type") Integer type);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq(fn.applicationId.id,fn.firstName,fn.lastName,fn.middleName,fn.birthDate,fn.genderId,fn.mobile,fn.email,fn.addressSameAsAadhar,fn.currentDistrict,fn.aadharDistrict,fn.currentHouse,fn.aadharHouse,fn.currentLandmark,fn.aadharLandmark,fn.currentLocation,fn.aadharLocation,fn.currentState,fn.aadharState,fn.currentStreet,fn.aadharStreet,fn.currentVtc,fn.aadharVtc,fn.aadharSubdist,fn.currentSubdist,fn.aadharPo,fn.currentPo,fn.aadharCareOf,fn.addressPincode,fn.aadharPincode,fn.addressProofType) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true and fn.type=:type")
    public List<AadharDetailsReq> findAadharDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);

    @Modifying
    @Query("update MFIApplicantDetail set isBankDetailsFilled = true where applicationId.id =:appId")
    public int updateBankFilledFlag(@Param("appId") Long appId);
 
    @Query("select mf from MFIApplicantDetail mf where mf.applicationId.id =:applicationId and  mf.type =:type and mf.isActive = true ")
    public MFIApplicantDetail findByApplicationIdAndAndTypeIsActive(@Param("applicationId") Long applicationId,@Param("type") Integer type);
     

    @Query("select new com.capitaworld.service.loans.model.micro_finance.PersonalDetailsReq(fn.applicationId.id,fn.fatherName,fn.motherName,fn.spouseName,fn.spouseBirthDate,fn.noDependent,fn.spouseMobile,fn.nomineeName,fn.nomineeBirthDate,\n" +
    		"			fn.relationWithNomineeId, fn.nomineePincode, fn.educationQualification,\n" +
    		"			fn.landHolding,fn.houseType,fn.nameOfFirm,fn.businessType, fn.nomineeState, fn.nomineeCity,\n" +
    		"			fn.nomineeDistrict,fn.nomineeLocation, fn.nomineeHouseNo,fn.nomineeLandmark,\n" +
    		"			fn.academicReligion, fn.academicCaste, fn.isAcademicLifeInsurance, fn.houseOwnership,\n" +
    		"			fn.areaType, fn.businessPremises, fn.expInSameLine, fn.academicSumInsured,\n" +
    		"			fn.isPersonalDetailsFilled) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true and fn.type =:type")
    public List<PersonalDetailsReq> findPersonalDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);
    

    @Query("select new com.capitaworld.service.loans.model.micro_finance.ProjectDetailsReq(fn.applicationId.id,fn.loanType,fn.purposeOfLoan,fn.loanAmountRequired,fn.costOfEquipment,fn.workingCapOfEquipment,fn.totalCostEquipment,fn.promoterContribution,fn.loanRequiredFromSidbi,fn.totalMeanFinance,fn.totalCashFlow,fn.repaymentFrequency,fn.insurenceRequired,fn.insurenceCompanyName,fn.insurencePremium,fn.isProjectDetailsFilled,me.businessInBrief,me.monthlyCashflow,me.monthlyExpenditure,me.monthlyIncome) from MFIApplicantDetail fn, MfiExpenseExpectedIncomeDetails me where me.applicationId = fn.applicationId.id and fn.applicationId.id = :appId and fn.isActive = true and fn.type =:type")
    public List<ProjectDetailsReq> findProjectDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);
    
    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiIncomeAndExpenditureReq(fn.applicationId.id,me.educationExpense,me.medicalExpense,me.foodExpense,me.otherExpense,fn.ppiNoFamilyMember,fn.ppiAcadamicHeadFamily,fn.ppiRafrigeratorInFamily,fn.ppiStoveInFamily,fn.ppiPressureCookerInFamily,fn.ppiTvInFamily,fn.ppiFanInFamily,fn.ppiVehicleInFamily,fn.ppiDressingTableInFamily,fn.ppiOtherTableInFamily,me.houseHoldExpense,me.clothesExpense,me.type) from MFIApplicantDetail fn, MfiExpenseExpectedIncomeDetails me where me.applicationId = fn.applicationId.id  and fn.applicationId.id = :appId and me.isActive = true and me.type =:type")
	public MfiIncomeAndExpenditureReq findIncomeAndExpenditureDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);
    
    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq(fn.applicationId.id,fn.purposeOfLoan,fn.clientType,fn.isBusinessPremiseVisited,fn.repaymentTrack,fn.creaditWorthiness,fn.loanLiabilityRatio,fn.competition) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true and fn.type =:type")
    public List<MfiLoanAssessmentDetailsReq> findLoanAssessmentDetailsByAppId(@Param("appId") Long appId,@Param("type") Integer type);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.FlagCheckMFI(fn.applicationId.id,fn.isPersonalDetailsFilled,fn.isFamilyDetailsFilled,fn.isNomineeDetailsFilled,fn.isAcadamicDetailsFilled,fn.isBankDetailsFilled,fn.isIncomeDetailsFilled,fn.isFamilyIncomeFilled,fn.isFamilyExpenseFilled,fn.isExpectedIncomeFilled,fn.isPPIFilled,fn.isProjectDetailsFilled,fn.isApplyLoanFilled,fn.isCostProjectFilled,fn.isMeanFinanceFilled,fn.isCashFlowDetailsFilled,fn.isAssetsDetailsFilled,fn.isCurrentAssetsFilled,fn.isFixedAssetsFilled,fn.isCurrntLiabilityFilled,fn.isRepaymentDetailsFilled,fn.isConsentFormFilled) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true and type=:type")
    public FlagCheckMFI getFlagDetailByApplicationId(@Param("appId") Long appId,@Param("type") Integer type);

//    @Query("select new com.capitaworld.service.loans.model.micro_finance.MfiLoanAssessmentDetailsReq(fn.applicationId.id,fn.monthlyIncome,fn.totalExpense,(fn.monthlyIncome - fn.totalExpense),fn.totalMonthlyIncomeForFamily,((fn.monthlyIncome - fn.totalExpense)+fn.totalMonthlyIncomeForFamily)) from MFIApplicantDetail fn where fn.applicationId.id = :appId and fn.isActive = true and type=:type")
//    public MfiLoanAssessmentDetailsReq getCashFlowAssesmentByAppId(@Param("appId") Long appId,@Param("type") Integer type);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq(ma.id,ma.applicationId.id,ma.firstName,ma.lastName) from MFIApplicantDetail ma where ma.applicationId.id In(select lm.id from LoanApplicationMaster lm where lm.isActive = true and lm.applicationStatusMaster.status IN (9,10) and lm.fpMakerId =:userId and lm.npOrgId =:userOrgId)")
    public AadharDetailsReq getPendingApplications(@Param("userId") Long userId,@Param("userOrgId") Long userOrgId);

    @Query("select new com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq(ma.id,ma.applicationId.id,ma.firstName,ma.lastName,sm.status) from MFIApplicantDetail ma Left Join ma.applicationId lm Left Join lm.applicationStatusMaster sm where sm.id IN (11,12,13,14,15,16) and lm.fpMakerId =:userId and lm.npOrgId =:userOrgId")
    public AadharDetailsReq getApprovedApplications(@Param("userId") Long userId,@Param("userOrgId") Long userOrgId);
}
