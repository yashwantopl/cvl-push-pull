package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFamilyDirectorsDetails;

public interface DDRFamilyDirectorsDetailsRepository extends JpaRepository<DDRFamilyDirectorsDetails, Long>{

	@Query("select dd from DDRFamilyDirectorsDetails dd where dd.id =:id and dd.isActive = true")
	public DDRFamilyDirectorsDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDRFamilyDirectorsDetails dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
	public List<DDRFamilyDirectorsDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);

}
