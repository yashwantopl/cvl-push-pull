package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.SectorIndustryMapping;
import com.capitaworld.service.loans.domain.fundseeker.corporate.SubsectorDetail;

public interface SectorIndustryMappingRepository extends JpaRepository<SectorIndustryMapping, Long>{
	
	@Query("select si.sectorId from SectorIndustryMapping si where si.industryId in :industryList and si.isActive = true")
	public List<Long> getSectorListByIndustryList(@Param("industryList") List<Long> industryList);

}
