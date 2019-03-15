package com.capitaworld.service.loans.repository.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;



import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigInteger;
import java.util.List;

public interface ApplicationProposalMappingRepository extends JpaRepository<ApplicationProposalMapping, Long> {

    @Query("select count(proposalId) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isFinalLocked=1 and apm.isActive = true")
    Long checkFinalDetailIsLocked(@Param("proposalId") Long proposalId);

    @Query("select count(proposalId) from ApplicationProposalMapping apm where apm.proposalId =:proposalId and apm.isPrimaryLocked=1 and apm.isActive = true")
    Long checkPrimaryDetailIsLocked(@Param("proposalId") Long proposalId);
    
    @Query("from ApplicationProposalMapping lm where lm.applicationId =:applicationId and lm.orgId =:orgId and lm.isActive = true")
	public ApplicationProposalMapping getByApplicationIdAndOrgId(@Param("applicationId") Long applicationId, @Param("orgId") Long orgId);

    @Query("from ApplicationProposalMapping lm where lm.applicationId =:applicationId and lm.proposalId =:proposalId and lm.orgId =:orgId and lm.isActive = true")
	public ApplicationProposalMapping getByApplicationIdAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId, @Param("orgId") Long orgId);
    
    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isFinalUploadFilled =:isFinalUploadFilled,apm.modifiedDate = NOW() where apm.proposalId=:proposalId AND apm.applicationId =:applicationId AND apm.isActive = true")
    public int setIsFinalUploadMandatoryFilled(@Param("proposalId") Long proposalId,
                                               @Param("applicationId") Long applicationId,
                                               @Param("isFinalUploadFilled") Boolean isFinalUploadFilled);


    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isFinalDprUploadFilled =:isFinalDprUploadFilled,apm.modifiedDate = NOW() where apm.proposalId=:proposalId and apm.applicationId =:applicationId and apm.isActive = true")
    public int setIsFinalDprMandatoryFilled(@Param("proposalId") Long proposalId,
                                            @Param("applicationId") Long applicationId,
                                            @Param("isFinalDprUploadFilled") Boolean isFinalDprUploadFilled);


    @Query("select tenure from ApplicationProposalMapping where proposalId =:proposalId")
    public Double getTenure(@Param("proposalId") Long proposalId);

    @Query("from ApplicationProposalMapping apm where apm.proposalId=:proposalId and apm.applicationId=:applicationId and apm.isActive = true order by apm.proposalId")
    public ApplicationProposalMapping getByProposalIdAndApplicationId(@Param("proposalId") Long proposalId, @Param("applicationId") Long applicationId);

    @Query(value = "select * from application_proposal_mapping lm where lm.application_id =:applicationId and lm.is_active = true order by lm.proposal_id desc limit 1",nativeQuery = true)
    public ApplicationProposalMapping getByApplicationId(@Param("applicationId") Long applicationId);

    //fp-maker - new proposal - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and lm.status =:id and lm.org_id=:npOrgId and (lm.payment_status=:paymentStatus or lm.payment_status='ByPass') and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPProposalsByApplicationStatusAndNpOrgIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId, @Param("npOrgId")Long npOrgId, @Param("paymentStatus")String paymentStatus, @Param("branchId") Long branchId);

    //fp-maker - new proposal - count
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and lm.status =:id and lm.org_id=:npOrgId and (lm.payment_status=:paymentStatus or lm.payment_status='ByPass') and pd.is_active=true and lm.is_active = true",nativeQuery = true)
    public List<BigInteger> getFPMakerNewProposalCount(@Param("id") Long applicationStatusId, @Param("npOrgId")Long npOrgId, @Param("paymentStatus")String paymentStatus, @Param("branchId") Long branchId);

    //fp-maker-pending tab - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and (lm.status =:id or lm.status =:revertedId or lm.status =:submitId) and lm.fp_maker_id=:npUserId and pd.is_active=true and  lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPAssignedTabPropsByNPUserIdForPagination(Pageable pageable,@Param("id") Long applicationStatusAssignId, @Param("revertedId") Long applicationRevertedStatusId, @Param("submitId") Long applicationSubmitStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    //fp-maker-pending tab - count
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and (lm.status =:id or lm.status =:revertedId or lm.status =:submitId) and lm.fp_maker_id=:npUserId and pd.is_active=true and lm.is_active = true",nativeQuery = true)
    public List<BigInteger> getFPAssignedTabPropsByNPUserIdCount(@Param("id") Long applicationStatusAssignId, @Param("revertedId") Long applicationRevertedStatusId, @Param("submitId") Long applicationSubmitStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    //fp-maker-assigned to checker - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and (lm.status >=:id or lm.status=5) and lm.fp_maker_id=:npUserId and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPAssignedProposalsByNPUserIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    //fp - maker-assigned to checker - count
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and (lm.status >=:id or lm.status=5) and lm.fp_maker_id=:npUserId and pd.is_active=true and lm.is_active = true ",nativeQuery = true)
    public List<BigInteger> getFPMakerAssignedAndAssginedToCheckerCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    //fp - maker - all other proposals - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and lm.status >=:id and lm.fp_maker_id!=:npUserId and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPProposalsWithOthersForPagination(Pageable pageable, @Param("id") Long applicationStatusId, @Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    //fp - maker - all other proposals - count
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and lm.status >=:id and lm.fp_maker_id!=:npUserId and pd.is_active=true and lm.is_active = true ",nativeQuery = true)
    public List<BigInteger> getFPProposalsWithOthersCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId);

    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isFinalMcqFilled =:isFinalMcqFilled,apm.modifiedDate = NOW(),apm.modifiedBy =:userId where apm.proposalId =:proposalId and apm.userId =:userId and apm.isActive = true")
    public int setIsFinalMcqMandatoryFilled(@Param("proposalId") Long proposalId, @Param("userId") Long userId,
                                            @Param("isFinalMcqFilled") Boolean isFinalMcqFilled);

    @Modifying
    @Query("update ApplicationProposalMapping apm set apm.isApplicantFinalFilled =:isApplicantFinalFilled,apm.modifiedDate = NOW(),apm.modifiedBy =:userId where apm.proposalId =:proposalId and apm.applicationId =:applicationId and apm.userId =:userId and apm.isActive = true")
    public int setIsApplicantFinalMandatoryFilled(@Param("proposalId") Long proposalId,
                                                  @Param("applicationId") Long applicationId ,
                                                  @Param("userId") Long userId,@Param("isApplicantFinalFilled") Boolean isApplicantFinalFilled);

    @Query(value= "select lm.product_id from application_proposal_mapping lm where lm.application_id =:id and lm.user_id =:userId and lm.is_active = true order by lm.proposal_id desc limit 1",nativeQuery = true)
    public Integer getProductIdByApplicationId(@Param("id") Long applicationId, @Param("userId") Long userId);

    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.ddr_status_id =:id and lm.np_user_id=:npUserId and pd.is_active=true and lm.is_active = true ",nativeQuery = true)
    public List<BigInteger> getFPAssignedToCheckerProposalsCount(@Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.status >=:id and (lm.np_assignee_id IS NULL or lm.np_assignee_id!=:npUserId) and (lm.np_user_id IS NULL or lm.np_user_id!=:npUserId) and pd.is_active=true and lm.is_active = true ",nativeQuery = true)
    public List<BigInteger> getFPCheckerProposalsWithOthersCount(@Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.ddr_status_id =:id and lm.np_user_id=:npUserId and pd.is_active=true and lm.is_active = true ",nativeQuery = true)
    public List<BigInteger> getFPAssignedToCheckerRevertedCount(@Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.ddr_status_id =:id and lm.np_user_id=:npUserId and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPAssignedToCheckerProposalsByNPUserIdPagination(Pageable pageable, @Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    //fp - checker - for reverted applications - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.ddr_status_id =:id and lm.np_user_id=:npUserId and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPAssignedToCheckerReverted(Pageable pageable, @Param("id") Long ddrStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    //fp - checker - for other applications - pagination
    @Query(value = "select lm.proposal_id from application_proposal_mapping lm inner join proposal_details pd on pd.id=lm.proposal_id where pd.branch_id=:branchId and pd.fp_product_id=:fpProductId and lm.status >=:id and (lm.np_assignee_id IS NULL or lm.np_assignee_id!=:npUserId) and (lm.np_user_id IS NULL or lm.np_user_id!=:npUserId) and pd.is_active=true and lm.is_active = true order by lm.modified_date desc \n#pageable\n",nativeQuery = true)
    public List<BigInteger> getFPCheckerProposalsWithOthersForPagination(Pageable pageable, @Param("id") Long applicationStatusId,@Param("npUserId") Long npUserId, @Param("branchId") Long branchId,@Param("fpProductId") Long fpProductId);

    @Query("select lm.userId from ApplicationProposalMapping lm where lm.applicationId =:applicationId AND lm.proposalId=:proposalId")
    public List<Object[]> getUserDetailsByApplicationIdAndPropsoalMappingId(@Param("applicationId") Long applicationId,@Param("proposalId") Long proposalMappingId);
    
    // STARTS HERE==MULTIPLE BANK===>
    @Query(value = "select * from application_proposal_mapping lm where lm.application_id =:applicationId and lm.proposal_id =:proposalId and lm.is_active= true",nativeQuery = true)
   	public ApplicationProposalMapping getByApplicationIdAndProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);
   	
    @Query(value = "select * from application_proposal_mapping lm where lm.proposal_id =:proposalId and lm.is_active= true",nativeQuery = true)
   	public ApplicationProposalMapping  getByApplicationIdAndProposalId(@Param("proposalId") Long proposalId);

    @Query(value = "select count(proposal_id) from application_proposal_mapping lm where  lm.proposal_id =:proposalId and lm.is_active= true",nativeQuery = true)
   	public Long getByProposalId(@Param("proposalId") Long proposalId);

 // getting applicationId By ProposalId for DDR purpose in final teaser
 	@Query(value = "select lm.application_id from application_proposal_mapping lm where lm.proposal_id =:proposalId and lm.is_active= true",nativeQuery = true)
 	public Long getApplicationIdByProposalId(@Param("proposalId") Long proposalId);
    //ENDS HERE=====
 	
   	public ApplicationProposalMapping  findByProposalIdAndIsActive(Long proposalId,Boolean isActive);
   	
	@Query(value = "select lm.user_id from application_proposal_mapping lm where lm.proposal_id =:proposalId and lm.is_active= true",nativeQuery = true)
	public Long getUserIdByProposalId(@Param("proposalId") Long proposalId);
   	
    //nhbs-pagination proposal mapping
    @Query("select apm from ApplicationProposalMapping apm where apm.applicationStatusMaster.id >=:id and apm.npAssigneeId=:assigneeId and  apm.isActive = true order by apm.modifiedDate desc")
    public List<ApplicationProposalMapping> getAssignedProposalsByAssigneeIdForPagination(Pageable pageable, @Param("id") Long applicationStatusId, @Param("assigneeId") Long assigneeId);

    @Query("select apm from ApplicationProposalMapping apm where apm.npUserId=:npUserId and  apm.isActive = true ")
    public List<ApplicationProposalMapping> getAssignedProposalsByNpUserIdForPagination(Pageable pageable,@Param("npUserId") Long npUserId);

    @Query(value = "select * from application_proposal_mapping apm where apm.application_id=:applicationId and apm.user_id=:userId and apm.is_active = true order by proposal_id desc limit 1",nativeQuery = true)
    public ApplicationProposalMapping getLastByApplicationIdAndUserId(@Param("applicationId") Long applicationId,@Param("userId") Long userId);

    @Modifying
	@Query("update ApplicationProposalMapping lm set lm.isMcqSkipped =:isMcqSkipped,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.proposalId =:proposalId and lm.applicationId =:applicationId and lm.userId =:userId and lm.isActive = true")
	public int setIsMcqSkipped(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalMapId,@Param("userId") Long userId,
			@Param("isMcqSkipped") Boolean isMcqSkipped);

    @Query("from ApplicationProposalMapping apm where apm.userId =:userId and apm.isActive = true order by apm.proposalId desc")
    public List<ApplicationProposalMapping> getUserLoans(@Param("userId") Long userId);
    
    @Modifying
	@Query("update ApplicationProposalMapping lm set lm.isApplicantDetailsFilled =:isApplicantDetailsFilled,lm.modifiedDate = NOW(),lm.modifiedBy =:userId where lm.proposalId =:proposalId and lm.userId =:userId and lm.isActive = true")
	public int setIsApplicantProfileMandatoryFilled(@Param("proposalId") Long proposalId, @Param("userId") Long userId,
			@Param("isApplicantDetailsFilled") Boolean isApplicantDetailsFilled);
    
    @Modifying
   	@Query("update ApplicationProposalMapping lm set lm.isPrimaryLocked =:isPrimaryLocked where lm.proposalId =:proposalId and lm.userId =:userId and lm.isActive = true")
   	public int setIsPrimaryLocked(@Param("proposalId") Long proposalId, @Param("userId") Long userId,
   			@Param("isPrimaryLocked") Boolean isPrimaryLocked);
}
