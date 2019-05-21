package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantIncomeDetail;

public interface CoApplicantIncomeRepository extends JpaRepository<CoApplicantIncomeDetail, Long>{

	public CoApplicantIncomeDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	public CoApplicantIncomeDetail findByIdAndIsActiveAndProposalIdIsNull(Long id,Boolean isActive);
	
	public List<CoApplicantIncomeDetail> findByIsActiveAndProposalIdIsNullAndCoAppId(Boolean isActive,Long coAppId);
	
	public CoApplicantIncomeDetail findByApplicationIdAndYearAndIsActive(Long applicationId,Integer year,Boolean isActive);
	
	public List<CoApplicantIncomeDetail> findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
	
	@Query("select i from CoApplicantIncomeDetail i where i.applicationId =:applicationId and i.proposalId =:proposalId and i.isActive = true ")
	public List<CoApplicantIncomeDetail> findByPropsoalIdAndIsActive(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);

	@Query("select MAX(o.year) from CoApplicantIncomeDetail o where o.applicationId =:id and o.isActive = true")
	public Integer getMaxYearByApplicationId(@Param("id")Long id);

	@Query("select i.incomeRatio from CoApplicantIncomeDetail i where i.applicationId =:id and i.year=:year and i.isActive = true ")
	public Double getTotalIncomeByApplicationIdAndYear(@Param("id")Long id,@Param("year")Integer year);
	
}
