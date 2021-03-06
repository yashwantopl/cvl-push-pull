package com.opl.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.opl.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;

/**
 * @author Sanket
 *
 */
public interface ReferenceRetailDetailsRepository extends JpaRepository<ReferencesRetailDetail, Long> {

	@Query("select o from ReferencesRetailDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<ReferencesRetailDetail> listReferencesRetailFromAppId(@Param("id")Long id);

	@Query("select o from ReferencesRetailDetail o where o.coApplicantDetailId.id = :id and o.isActive = true")
	public List<ReferencesRetailDetail> listReferencesRetailFromCoAppId(@Param("id")Long id);

	@Query("select o from ReferencesRetailDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<ReferencesRetailDetail> listReferencesRetailFromGarrId(@Param("id")Long id);

	@Modifying
	@Query("update ReferencesRetailDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
	
	@Query("select o from ReferencesRetailDetail o where o.applicationProposalMapping.proposalId = :proposalId and o.coApplicantDetailId.id =null and o.isActive = true")
	public List<ReferencesRetailDetail> listReferencesRetailFromPropsalId(@Param("proposalId")Long proposalId);

	@Query("select o from ReferencesRetailDetail o where o.applicationProposalMapping.proposalId = :proposalId and o.coApplicantDetailId.id=:coAppId and o.isActive = true")
	public List<ReferencesRetailDetail> listReferencesRetailFromPropsalIdAndCoAppId(@Param("proposalId")Long proposalId,@Param("coAppId")Long coAppId);
}
