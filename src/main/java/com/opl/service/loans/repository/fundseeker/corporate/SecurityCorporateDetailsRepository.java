package com.opl.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.corporate.SecurityCorporateDetail;

/**
 * @author Sanket
 *
 */
public interface SecurityCorporateDetailsRepository extends JpaRepository<SecurityCorporateDetail, Long> {

	@Query("select o from SecurityCorporateDetail o where o.applicationId.id = :id and o.applicationId.userId =:userId and o.isActive = true")
	public List<SecurityCorporateDetail> listSecurityCorporateDetailFromAppId(@Param("id") Long id, @Param("userId") Long userId);
	
	@Query("select o from SecurityCorporateDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<SecurityCorporateDetail> getSecurityCorporateDetailFromAppId(@Param("id") Long id);

	@Query("select o from SecurityCorporateDetail o where o.proposalId.proposalId = :proposalId and o.isActive = true")
	public List<SecurityCorporateDetail> getSecurityCorporateDetailFromProposalId(@Param("proposalId") Long proposalId);
	
	public SecurityCorporateDetail findByIdAndIsActive(Long id,Boolean isActive);
	
	@Modifying
	@Query("update SecurityCorporateDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);


}
