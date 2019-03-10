package com.capitaworld.service.loans.repository.fundseeker.ddr;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.ddr.DDRFormDetails;

public interface DDRFormDetailsRepository extends JpaRepository<DDRFormDetails, Long>{

	@Query("select ddr from DDRFormDetails ddr where ddr.id =:id and ddr.isActive = true")
	public DDRFormDetails getByIdAndIsActive(@Param("id") Long id);
	
	@Query("select ddr from DDRFormDetails ddr where ddr.id =:id and ddr.applicationId =:appId and ddr.isActive = true")
	public DDRFormDetails getByIdAndAppIdAndIsActive(@Param("id") Long id,@Param("appId") Long appId);
	
	@Query("select ddr from DDRFormDetails ddr where ddr.applicationId =:appId and ddr.isActive = true")
	public DDRFormDetails getByAppIdAndIsActive(@Param("appId") Long appId);

	@Query("select ddr from DDRFormDetails ddr where ddr.id =:id and ddr.proposalMappingId =:proposalId and ddr.applicationId=:applicationId and ddr.isActive = true")
	public DDRFormDetails getByIdAndProposaMappingIdAndApplicationId(@Param("id") Long id,@Param("proposalId") Long proposalId,@Param("applicationId") Long applicationId);

	@Query(value = "select * from fs_ddr_form_details ddr where ddr.proposal_mapping_id =:proposalId and ddr.application_id=:applicationId and ddr.is_active = true ORDER BY id DESC limit 1",nativeQuery = true)
	public DDRFormDetails getByProposaMappingIdAndApplicationId(@Param("applicationId") Long applicationId, @Param("proposalId") Long proposalId);
	
	@Query("select ddr from DDRFormDetails ddr where ddr.applicationId =:appId and ddr.orgId =:orgId and ddr.isActive = true")
	public DDRFormDetails getByAppIdAndOrgIdAndIsActive(@Param("appId") Long appId, @Param("orgId") Long orgId);
}
