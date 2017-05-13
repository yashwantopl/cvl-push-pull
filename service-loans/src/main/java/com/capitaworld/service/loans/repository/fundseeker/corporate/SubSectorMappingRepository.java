package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.SubSectorMappingDetail;
import com.capitaworld.service.loans.model.corporate.SubSectorMappingRequest;

public interface SubSectorMappingRepository extends JpaRepository<SubSectorMappingDetail, Long>{
	
	@Query("select new com.capitaworld.service.loans.model.corporate.SubSectorMappingRequest(ss.id,ss.subSectorId)  from SubSectorMappingDetail ss where ss.sectorId=:sectorId")
	//@Query("select si.subSectorId from SubSectorMappingDetail si where si.sectorId=:sectorId")
	public List<SubSectorMappingRequest> getSectorListByIndustryList(@Param("sectorId") Long sectorId);

}
