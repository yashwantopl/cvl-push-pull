package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.SubSectorMappingDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;

public interface SubSectorMappingRepository extends JpaRepository<SubSectorMappingDetail, Long>{
	
	@Query("select si.subSectorId from SubSectorMappingDetail si where si.sectorId:=sectorId")
	public List<Long> getSectorListByIndustryList(@Param("sectorId") Long sectorId);

}
