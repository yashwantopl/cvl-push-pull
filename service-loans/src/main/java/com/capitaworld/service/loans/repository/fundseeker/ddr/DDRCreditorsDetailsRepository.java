package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRCreditorsDetails;

public interface DDRCreditorsDetailsRepository extends JpaRepository<DDRCreditorsDetails, Long>{

	@Query("select dd from DDRCreditorsDetails dd where dd.id =:id and dd.isActive = true")
	public DDRCreditorsDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDRCreditorsDetails dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
	public List<DDRCreditorsDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
	
}
