package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRRelWithDbsDetails;

public interface DDRRelWithDbsDetailsRepository extends JpaRepository<DDRRelWithDbsDetails, Long> {

	@Query("select dd from DDRRelWithDbsDetails dd where dd.id =:id and dd.isActive = true")
	public DDRRelWithDbsDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDRRelWithDbsDetails dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
	public List<DDRRelWithDbsDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
}
