package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.ReferencesRetailDetail;

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

}
