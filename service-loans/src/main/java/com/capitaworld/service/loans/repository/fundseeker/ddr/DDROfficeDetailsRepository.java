package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDROfficeDetails;

public interface DDROfficeDetailsRepository extends JpaRepository<DDROfficeDetails, Long>{

	@Query("select dd from DDROfficeDetails dd where dd.id =:id and dd.isActive = true")
	public DDROfficeDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDROfficeDetails dd where dd.ddrFormId =:ddrFormId and dd.officeType=:officeType and dd.isActive = true")
	public List<DDROfficeDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId, @Param("officeType") Integer officeType);
	
}
