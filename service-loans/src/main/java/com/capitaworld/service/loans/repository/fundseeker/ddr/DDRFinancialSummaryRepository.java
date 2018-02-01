package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFinancialSummary;

public interface DDRFinancialSummaryRepository extends JpaRepository<DDRFinancialSummary, Long>{
	
	@Query("select dd from DDRFinancialSummary dd where dd.id =:id and dd.isActive = true")
	public DDRFinancialSummary getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDRFinancialSummary dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
	public List<DDRFinancialSummary> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);

}
