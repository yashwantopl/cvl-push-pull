package com.capitaworld.service.loans.repository.fundseeker.ddr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRVehiclesOwnedDetails;

public interface DDRVehiclesOwnedDetailsRepository extends JpaRepository<DDRVehiclesOwnedDetails,Long> {

	@Query("select * from DDRVehiclesOwnedDetails where id =:id and isActive = true")
	public DDRVehiclesOwnedDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select * from DDRVehiclesOwnedDetails where ddrFormId =:ddrFormId and isActive = true")
	public List<DDRVehiclesOwnedDetails> getListByDDRFormId(@Param("ddrFormId") Long ddrFormId);
	
}
