package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;

public interface LoanApplicationRepository extends JpaRepository<LoanApplicationMaster, Long> {

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isActive = false,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int inActive(@Param("id") Long id, @Param("userId") Long userId);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isApplicantDetailsFilled =:isApplicantDetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isApplicantDetailsFilled") Boolean isApplicantDetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isApplicantPrimaryFilled =:isApplicantPrimaryFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantPrimaryMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isApplicantPrimaryFilled") Boolean isApplicantPrimaryFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isApplicantFinalFilled =:isApplicantFinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isApplicantFinalFilled") Boolean isApplicantFinalFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp1DetailsFilled =:isCoApp1DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppOneProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isCoApp1DetailsFilled") Boolean isCoApp1DetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp1FinalFilled =:isCoApp1FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppOneFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isCoApp1FinalFilled") Boolean isCoApp1FinalFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp2DetailsFilled =:isCoApp2DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppTwoProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isCoApp2DetailsFilled") Boolean isCoApp2DetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp2FinalFilled =:isCoApp2FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppTwoFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isCoApp2FinalFilled") Boolean isCoApp2FinalFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor1DetailsFilled =:isGuarantor1DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorOneProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isGuarantor1DetailsFilled") Boolean isGuarantor1DetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor1FinalFilled =:isGuarantor1FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorOneFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isGuarantor1FinalFilled") Boolean isGuarantor1FinalFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor2DetailsFilled =:isGuarantor2DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorTwoProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isGuarantor2DetailsFilled") Boolean isGuarantor2DetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isPrimaryUploadFilled =:isPrimaryUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsPrimaryUploadMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isPrimaryUploadFilled") Boolean isPrimaryUploadFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalDprUploadFilled =:isFinalDprUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalDprMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isFinalDprUploadFilled") Boolean isFinalDprUploadFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalUploadFilled =:isFinalUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalUploadMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isFinalUploadFilled") Boolean isFinalUploadFilled);
	
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalMcqFilled =:isFinalMcqFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalMcqMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId, @Param("isFinalMcqFilled") Boolean isFinalMcqFilled);
	

	@Query("from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true")
	public List<LoanApplicationMaster> getUserLoans(@Param("userId") Long userId);

	@Query("from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true order by lm.id")
	public LoanApplicationMaster getByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("select new com.capitaworld.service.loans.model.LoanApplicationDetailsForSp(lm.id,lm.productId,lm.amount,lm.denominationId)  from LoanApplicationMaster lm where lm.userId=:userId and lm.isActive = true")
	public List<LoanApplicationDetailsForSp> getListByUserId(@Param("userId") Long userId);
	
	@Query("select lm.productId from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public Integer getProductIdByApplicationId(@Param("id") Long applicationId, @Param("userId") Long userId);
	
	@Query("select lm.id,lm.name from LoanApplicationMaster lm where lm.id =:applicationId and lm.isActive = true")
	public Object[] getUserDetailsByApplicationId(@Param("applicationId") Long applicationId);
	
	
	

}
