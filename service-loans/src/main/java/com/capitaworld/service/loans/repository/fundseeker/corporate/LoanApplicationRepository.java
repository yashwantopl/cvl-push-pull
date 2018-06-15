package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
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
	public int setIsApplicantProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isApplicantDetailsFilled") Boolean isApplicantDetailsFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isApplicantPrimaryFilled =:isApplicantPrimaryFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantPrimaryMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isApplicantPrimaryFilled") Boolean isApplicantPrimaryFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isApplicantFinalFilled =:isApplicantFinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isApplicantFinalFilled") Boolean isApplicantFinalFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp1DetailsFilled =:isCoApp1DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppOneProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isCoApp1DetailsFilled") Boolean isCoApp1DetailsFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp1FinalFilled =:isCoApp1FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppOneFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isCoApp1FinalFilled") Boolean isCoApp1FinalFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp2DetailsFilled =:isCoApp2DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppTwoProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isCoApp2DetailsFilled") Boolean isCoApp2DetailsFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isCoApp2FinalFilled =:isCoApp2FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsCoAppTwoFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isCoApp2FinalFilled") Boolean isCoApp2FinalFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor1DetailsFilled =:isGuarantor1DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorOneProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isGuarantor1DetailsFilled") Boolean isGuarantor1DetailsFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor1FinalFilled =:isGuarantor1FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorOneFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isGuarantor1FinalFilled") Boolean isGuarantor1FinalFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor2DetailsFilled =:isGuarantor2DetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorTwoProfileMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isGuarantor2DetailsFilled") Boolean isGuarantor2DetailsFilled);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isGuarantor2FinalFilled =:isGuarantor2FinalFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsGuarantorTwoFinalMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isGuarantor2FinalFilled") Boolean isGuarantor2FinalFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isPrimaryUploadFilled =:isPrimaryUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsPrimaryUploadMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isPrimaryUploadFilled") Boolean isPrimaryUploadFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalDprUploadFilled =:isFinalDprUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalDprMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isFinalDprUploadFilled") Boolean isFinalDprUploadFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalUploadFilled =:isFinalUploadFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalUploadMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isFinalUploadFilled") Boolean isFinalUploadFilled);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isFinalMcqFilled =:isFinalMcqFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setIsFinalMcqMandatoryFilled(@Param("id") Long id, @Param("userId") Long userId,
			@Param("isFinalMcqFilled") Boolean isFinalMcqFilled);

	@Query("from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true order by lm.id desc")
	public List<LoanApplicationMaster> getUserLoans(@Param("userId") Long userId);

	@Query("from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true order by lm.id")
	public LoanApplicationMaster getByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("from LoanApplicationMaster lm where lm.id =:id and lm.isActive = true order by lm.id")
	public LoanApplicationMaster getById(@Param("id") Long id);

	@Query("select new com.capitaworld.service.loans.model.LoanApplicationDetailsForSp(lm.id,lm.productId,lm.amount,lm.currencyId,lm.denominationId)  from LoanApplicationMaster lm where lm.userId=:userId and lm.isActive = true")
	public List<LoanApplicationDetailsForSp> getListByUserId(@Param("userId") Long userId);

	@Query("select lm.productId from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public Integer getProductIdByApplicationId(@Param("id") Long applicationId, @Param("userId") Long userId);
	
	@Query("select lm.productId from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId")
	public Integer getProductIdByApplicationIdForSP(@Param("id") Long applicationId, @Param("userId") Long userId);

	@Query("select lm.userId,lm.name from LoanApplicationMaster lm where lm.id =:applicationId")
	public List<Object[]> getUserDetailsByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("select count(applicationId) from LoanApplicationMaster lm where lm.id =:id and lm.isActive = true")
	public Long checkApplicationIdActive(@Param("id") Long id);

	@Query("select count(id) from LoanApplicationMaster lm where lm.id =:id and lm.isPrimaryLocked=1 and lm.isActive = true")
	public Long checkPrimaryDetailIsLocked(@Param("id") Long applicationId);

	@Query("select count(id) from LoanApplicationMaster lm where lm.id =:id and lm.isFinalLocked=1 and lm.isActive = true")
	public Long checkFinalDetailIsLocked(@Param("id") Long applicationId);

	@Query("select count(id) from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public Long isSelfApplicantView(@Param("id") Long applicationId, @Param("userId") Long userId);

	@Query("select lm.currencyId from LoanApplicationMaster lm where lm.id =:applicationId and lm.userId =:userId and lm.isActive = true")
	public Integer getCurrencyId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);
	
	@Query("select lm.denominationId from LoanApplicationMaster lm where lm.id =:applicationId and lm.userId =:userId and lm.isActive = true")
	public Integer getDenominationId(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.detailsFilledCount =:detailsFilledCount,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setProfileFilledCount(@Param("id") Long id, @Param("userId") Long userId,
			@Param("detailsFilledCount") String detailsFilledCount);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.primaryFilledCount =:primaryFilledCount,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setPrimaryFilledCount(@Param("id") Long id, @Param("userId") Long userId,
			@Param("primaryFilledCount") String primaryFilledCount);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.finalFilledCount =:finalFilledCount,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setFinalFilledCount(@Param("id") Long id, @Param("userId") Long userId,
			@Param("finalFilledCount") String finalFilledCount);
	
	@Query("select lm from LoanApplicationMaster lm where lm.userId IN (:userIds) and lm.isActive = true and (lm.createdDate BETWEEN :fromDate and :toDate)")
	public List<LoanApplicationMaster> getLoanDetailsForAdminPanel(@Param("userIds") List<Long> userIds,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);

	@Query("select lm from LoanApplicationMaster lm where lm.userId IN (:userIds) and lm.id IN(:appIds) and lm.isActive = true and (lm.createdDate BETWEEN :fromDate and :toDate)")
	public List<LoanApplicationMaster> getLoanDetailsForAdminPanelUbi(@Param("userIds") List<Long> userIds,@Param("appIds") List<Long> appIds,@Param("fromDate") Date fromDate,@Param("toDate") Date toDate);
	
	@Query("select lm from LoanApplicationMaster lm where lm.id =:id and lm.userId =:userId and lm.isActive = true order by lm.id")
	public LoanApplicationMaster getMCACompanyIdByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("select count(lm.id) from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true")
	public Long getTotalUserApplication(@Param("userId") Long userId);
	
	@Query("select lm.userId from LoanApplicationMaster lm where lm.id =:applicationId")
	public Long getUserIdByApplicationId(@Param("applicationId") Long applicationId);
	
//	 and lm.isActive = true
	@Query("select count(*) from LoanApplicationMaster lm where lm.userId =:userId and lm.campaignCode =:campaignCode")
	public Long getApplicantCountByCode(@Param("userId") Long userId,@Param("campaignCode") String campaignCode);
	
	@Query("select lm.campaignCode from LoanApplicationMaster lm where lm.id =:applicationId")
	public String getCampaignCodeByApplicationId(@Param("applicationId") Long applicationId);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.eligibleAmnt =:eligibleAmnt,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.userId =:userId and lm.isActive = true")
	public int setEligibleAmount(@Param("id") Long id, @Param("userId") Long userId,
			@Param("eligibleAmnt") Double amount);
	
	//nhbs
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=null and lm.paymentStatus=:paymentStatus and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getProposalsByApplicationStatus(@Param("id") Long applicationStatusId,@Param("paymentStatus")String paymentStatus);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=null and lm.paymentStatus=:paymentStatus and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getProposalsByApplicationStatusForPagination(Pageable pageable,@Param("id") Long applicationStatusId,@Param("paymentStatus")String paymentStatus);
	
	//to get count of proposal based on application status
	@Query("select count(*) from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=null and lm.isActive = true ")
	public int getCountOfProposalsByApplicationStatus(@Param("id") Long applicationStatusId);

	//nhbs
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=:npOrgId and lm.paymentStatus=:paymentStatus and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getProposalsByApplicationStatusAndNpOrgId(@Param("id") Long applicationStatusId,@Param("npOrgId")Long npOrgId,@Param("paymentStatus")String paymentStatus);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=:npOrgId and lm.paymentStatus=:paymentStatus and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getProposalsByApplicationStatusAndNpOrgIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId, @Param("npOrgId")Long npOrgId, @Param("paymentStatus")String paymentStatus);

    //to get count of proposal based on application status and npOrgId
    @Query("select count(*) from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.typeOfPayment<>null and lm.npOrgId=:npOrgId and lm.isActive = true ")
    public int getCountOfProposalsByApplicationStatusAndNpOrgId(@Param("id") Long applicationStatusId,@Param("npOrgId")Long npOrgId);

	//nhbs	
	//@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.npAssigneeId=:assigneeId and  lm.isActive = true ")
	//public List<LoanApplicationMaster> getAssignedProposalsByAssigneeId(@Param("id") Long applicationStatusId,@Param("assigneeId") Long assigneeId);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.npAssigneeId=:assigneeId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getAssignedProposalsByAssigneeIdForPagination(Pageable pageable,@Param("id") Long applicationStatusId,@Param("assigneeId") Long assigneeId);

	//to get count of assigned proposals based on assignee id
	@Query("select count(*) from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.npAssigneeId=:assigneeId and  lm.isActive = true ")
	public int getCountOfAssignedProposalsByAssigneeId(@Param("id") Long applicationStatusId,@Param("assigneeId") Long assigneeId);
	
	//nhbs	
	//@Query("select lm from LoanApplicationMaster lm where lm.npUserId=:npUserId and  lm.isActive = true ")
	//public List<LoanApplicationMaster> getAssignedProposalsByNpUserId(@Param("npUserId") Long npUserId);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.npUserId=:npUserId and  lm.isActive = true ")
	public List<LoanApplicationMaster> getAssignedProposalsByNpUserIdForPagination(Pageable pageable,@Param("npUserId") Long npUserId);
	
	//to get count of proposal based on NpUserId
	@Query("select count(*) from LoanApplicationMaster lm where lm.npUserId=:npUserId and  lm.isActive = true ")
	public int getCountOfAssignedProposalsByNpUserId(@Param("npUserId") Long npUserId);
	
	//nhbs
	//@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.isActive = true ")
	//public List<LoanApplicationMaster> getProposalsByDdrStatus(@Param("id") Long ddrStatusId);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.isActive = true ")
	public List<LoanApplicationMaster> getProposalsByDdrStatusForPagination(Pageable pageable,@Param("id") Long ddrStatusId);

	//nhbs
	@Query("select count(*) from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.isActive = true ")
	public int getCountOfProposalsByDdrStatus(@Param("id") Long ddrStatusId);

    //nhbs
    //@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npOrgId=:npOrgId and lm.isActive = true ")
    //public List<LoanApplicationMaster> getProposalsByDdrStatusAndNpOrgId(@Param("id") Long ddrStatusId,@Param("npOrgId")Long npOrgId);

	//nhbs-pagination
	@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npOrgId=:npOrgId and lm.isActive = true ")
	public List<LoanApplicationMaster> getProposalsByDdrStatusAndNpOrgIdForPagination(Pageable pageable,@Param("id") Long ddrStatusId,@Param("npOrgId")Long npOrgId);

    //nhbs
    @Query("select count(*) from LoanApplicationMaster lm where lm.ddrStatusId =:id and npOrgId=:npOrgId and lm.isActive = true ")
    public int getCountOfProposalsByDdrStatusAndNpOrgId(@Param("id") Long ddrStatusId,@Param("npOrgId")Long npOrgId);
    
	@Query("select lm from LoanApplicationMaster lm where lm.userId =:userId and lm.isActive = true and lm.productId IS NULL and lm.businessTypeId =:businessTypeId")
	public LoanApplicationMaster getCorporateLoan(@Param("userId") Long userId,@Param("businessTypeId") Integer businessTypeId);
	
	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isActive  = false where lm.userId =:userId and lm.isActive = true and lm.productId IS NULL")
	public int inActiveCorporateLoan(@Param("userId") Long userId);

	@Modifying
	@Query("update LoanApplicationMaster lm set lm.isActive = false,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.id =:id and lm.isActive = true")
	public int inActiveApplication(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("select tenure from LoanApplicationMaster where id =:id")
	public Double getTenure(@Param("id") Long applicationId);

	//fp-maker - new proposal - pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.npOrgId=:npOrgId and lm.paymentStatus=:paymentStatus and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPProposalsByApplicationStatusAndNpOrgIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId, @Param("npOrgId")Long npOrgId, @Param("paymentStatus")String paymentStatus);

	//fp-maker - new proposal - count
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.npOrgId=:npOrgId and lm.paymentStatus=:paymentStatus and lm.isActive = true")
	public List<LoanApplicationMaster> getFPMakerNewProposalCount(@Param("id") Long applicationStatusId, @Param("npOrgId")Long npOrgId, @Param("paymentStatus")String paymentStatus);

	//fp-maker-assigned to checker - pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.fpMakerId=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPAssignedProposalsByNPUserIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);

	//fp - maker-assigned to checker - count
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id =:id and lm.fpMakerId=:npUserId and  lm.isActive = true ")
	public List<LoanApplicationMaster> getFPMakerAssignedAndAssginedToCheckerCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);

	//fp-maker-pending tab - pagination
	@Query("select lm from LoanApplicationMaster lm where (lm.applicationStatusMaster.id =:id or lm.applicationStatusMaster.id =:revertedId or lm.applicationStatusMaster.id =:submitId) and lm.fpMakerId=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPAssignedTabPropsByNPUserIdForPagination(Pageable pageable,@Param("id") Long applicationStatusAssignId, @Param("revertedId") Long applicationRevertedStatusId, @Param("submitId") Long applicationSubmitStatusId,@Param("npUserId") Long npUserId);

	//fp-maker-pending tab - count
	@Query("select lm from LoanApplicationMaster lm where (lm.applicationStatusMaster.id =:id or lm.applicationStatusMaster.id =:revertedId or lm.applicationStatusMaster.id =:submitId) and lm.fpMakerId=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPAssignedTabPropsByNPUserIdCount(@Param("id") Long applicationStatusAssignId, @Param("revertedId") Long applicationRevertedStatusId, @Param("submitId") Long applicationSubmitStatusId,@Param("npUserId") Long npUserId);

	//fp - maker - all other proposals - pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.fpMakerId!=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPProposalsWithOthersForPagination(Pageable pageable, @Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);

	//fp - maker - all other proposals - count
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.fpMakerId!=:npUserId and  lm.isActive = true ")
	public List<LoanApplicationMaster> getFPProposalsWithOthersCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);

	//fp - checker - for approved or submitted - pagination
	@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npUserId=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPAssignedToCheckerProposalsByNPUserIdPagination(Pageable pageable, @Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId);

	//fp - checker - for approved or submitted - count
	@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npUserId=:npUserId and  lm.isActive = true ")
	public List<LoanApplicationMaster> getFPAssignedToCheckerProposalsCount(@Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId);

    //fp - checker - for reverted applications - pagination
    @Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npAssigneeId=:npUserId and  lm.isActive = true order by lm.modifiedDate desc")
    public List<LoanApplicationMaster> getFPAssignedToCheckerReverted(Pageable pageable, @Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId);

	//fp - checker - for reverted applications - count
	@Query("select lm from LoanApplicationMaster lm where lm.ddrStatusId =:id and lm.npAssigneeId=:npUserId and  lm.isActive = true ")
	public List<LoanApplicationMaster> getFPAssignedToCheckerRevertedCount(@Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId);

	//fp - checker - for other applications - pagination
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.npAssigneeId!=:npUserId and lm.npUserId!=:npUserId and lm.isActive = true order by lm.modifiedDate desc")
	public List<LoanApplicationMaster> getFPCheckerProposalsWithOthersForPagination(Pageable pageable, @Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);

	//fp - checker - for other applications - count
	@Query("select lm from LoanApplicationMaster lm where lm.applicationStatusMaster.id >=:id and lm.npAssigneeId!=:npUserId and lm.npUserId!=:npUserId and lm.isActive = true ")
	public List<LoanApplicationMaster> getFPCheckerProposalsWithOthersCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId);
}
