package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRVehiclesOwnedDetails;

public interface DDRVehiclesOwnedDetailsRepository extends JpaRepository<DDRVehiclesOwnedDetails,Long> {

	@Query("select dd from DDRVehiclesOwnedDetails dd where dd.id =:id and dd.isActive = true")
	public DDRVehiclesOwnedDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select dd from DDRVehiclesOwnedDetails dd where dd.ddrFormId =:ddrFormId and dd.isActive = true")
	public List<DDRVehiclesOwnedDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
	
}
