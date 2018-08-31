package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantIncomeDetail;

public interface RetailApplicantIncomeRepository extends JpaRepository<RetailApplicantIncomeDetail, Long>{

	public RetailApplicantIncomeDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	public RetailApplicantIncomeDetail findByApplicationIdAndYearAndIsActive(Long applicationId,Integer year,Boolean isActive);
	
	public List<RetailApplicantIncomeDetail> findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
}
