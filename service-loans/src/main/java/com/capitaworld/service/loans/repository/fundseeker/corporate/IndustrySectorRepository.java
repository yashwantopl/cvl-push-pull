package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;

public interface IndustrySectorRepository extends JpaRepository<IndustrySectorDetail, Long> {

	@Modifying
	@Query("update IndustrySectorDetail isd set isd.isActive = false where isd.applicationId =:applicationId and isd.isActive = true")
	public int inActiveMappingByApplicationId(@Param("applicationId") Long applicationId);

	@Query("select o.industryId from IndustrySectorDetail o where o.sectorId = :sectorId")
	public Long findOneBySectorId(@Param("sectorId") Long sectorId);

	@Query("select o.industryId from IndustrySectorDetail o where o.applicationId = :applicationId and o.isActive = true and  o.sectorId = NULL")
	public List<Long> getIndustryByApplicationId(@Param("applicationId") Long applicationId);

	@Query("select o.sectorId from IndustrySectorDetail o where o.applicationId = :applicationId and o.isActive = true and o.industryId = NULL")
	public List<Long> getSectorByApplicationId(@Param("applicationId") Long applicationId);

	@Query("select o.industryId from IndustrySectorDetail o where o.fpProductId = :fpProductId and o.isActive = true and  o.sectorId = NULL")
	public List<Long> getIndustryByProductId(@Param("fpProductId") Long fpProductId);

	@Query("select o.sectorId from IndustrySectorDetail o where o.fpProductId = :fpProductId and o.isActive = true and o.industryId = NULL")
	public List<Long> getSectorByProductId(@Param("fpProductId") Long fpProductId);

	@Modifying
	@Query("update IndustrySectorDetail isd set isd.isActive = false where isd.fpProductId =:fpProductId and isd.isActive = true")
	public int inActiveMappingByFpProductId(@Param("fpProductId") Long fpProductId);

}
