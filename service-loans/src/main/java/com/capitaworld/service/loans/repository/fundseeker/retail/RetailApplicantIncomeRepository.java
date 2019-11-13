package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantIncomeDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RetailApplicantIncomeRepository extends JpaRepository<RetailApplicantIncomeDetail, Long>{

	public RetailApplicantIncomeDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	public RetailApplicantIncomeDetail findByApplicationIdAndYearAndIsActive(Long applicationId,Integer year,Boolean isActive);
	
	public List<RetailApplicantIncomeDetail> findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	
	@Query("select i from RetailApplicantIncomeDetail i where i.applicationId =:id and i.proposalId IS NULL and i.isActive = true ")
	public List<RetailApplicantIncomeDetail> findByApplicationId(@Param("id")Long id);
	
	public List<RetailApplicantIncomeDetail> findByProposalIdAndIsActive(Long proposalId, Boolean isActive);

	@Query("select MAX(o.year) from RetailApplicantIncomeDetail o where o.applicationId =:id and o.isActive = true")
	public Integer getMaxYearByApplicationId(@Param("id")Long id);

	@Query("select i.incomeRatio from RetailApplicantIncomeDetail i where i.applicationId =:id and i.year=:year and i.isActive = true ")
	public Double getTotalIncomeByApplicationIdAndYear(@Param("id")Long id,@Param("year")Integer year);
	
	@Query(value="SELECT salary_income FROM  `loan_application`.`fs_retail_applicant_income_details` WHERE application_id =:applicationId AND proposal_mapping_id IS NULL AND is_active = TRUE ORDER BY YEAR DESC LIMIT 1", nativeQuery=true)
	public Double getApplicantLatestYearIncome(@Param("applicationId") Long applicationId);

}
