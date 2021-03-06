package com.opl.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.RetailApplicantDetail;

public interface RetailApplicantDetailRepository extends JpaRepository<RetailApplicantDetail, Long> {

	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true and rt.applicationProposalMapping.proposalId IS NULL")
	public RetailApplicantDetail getByApplicationAndUserId(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	@Query("select rt.firstName,rt.lastName,rt.modifiedDate from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true")
	public List<Object[]> getNameAndLastUpdatedDate(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
	@Query("select rt.firstName,rt.lastName,rt.isOneFormCompleted,rt.isCibilCompleted,rt.isOneformPrimaryComplete from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.isActive = true")
	public Object[] getBasicDetailsByAppId(@Param("applicationId") Long applicationId);
	
	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId")
	public RetailApplicantDetail getByApplicationAndUserIdForSP(@Param("userId") Long userId,
			@Param("applicationId") Long applicationId);
	
//	@Query("select rt.currencyId from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true")
//	public Integer getCurrency(@Param("userId") Long userId,
//			@Param("applicationId") Long applicationId);
	
	@Query("select lm.currencyId from LoanApplicationMaster lm where lm.applicationId.id =:applicationId and lm.applicationId.userId =:userId and lm.isActive = true")
	public Integer getCurrency(@Param("userId") Long userId, @Param("applicationId") Long applicationId);
	
	//public RetailApplicantDetail findOneByApplicationIdId(Long applicationId);
	
	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationProposalMapping.proposalId =:proposalId and rt.isActive = true")
	public RetailApplicantDetail findByProposalId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);
	
//	@Query("from RetailApplicantDetail rt where rt.applicationProposalMapping.proposalId =:proposalId and rt.applicationId.id =:applicationId and rt.applicationProposalMapping.userId =:userId and rt.isActive = true")
//	public RetailApplicantDetail findByProposalIdAndUserId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId, @Param("userId") Long userId);
	
	@Query("from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.isActive = true and rt.applicationProposalMapping.proposalId IS NULL")
	public RetailApplicantDetail findByApplicationId(@Param("applicationId") Long applicationId);
	
	@Query("select rt.loanAmountRequired from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.isActive = true and rt.applicationProposalMapping.proposalId IS NULL")
	public Double getLoanAmountByApplicationId(@Param("applicationId") Long applicationId);

	@Query("select count(rt.applicationId.id) from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.applicationId.userId =:userId and rt.isActive = true and (rt.firstName != NULL and rt.firstName != '') ")
	public Long hasAlreadyApplied(@Param("userId") Long userId,
								  @Param("applicationId") Long applicationId);
	
	@Query("select rt.grossMonthlyIncome,rt.monthlyIncome from RetailApplicantDetail rt where rt.applicationId.id =:applicationId and rt.isActive = true and rt.applicationProposalMapping.proposalId IS NULL")
	public Object[] getGrossAndMonthlyIncome(@Param("applicationId") Long applicationId);

}
