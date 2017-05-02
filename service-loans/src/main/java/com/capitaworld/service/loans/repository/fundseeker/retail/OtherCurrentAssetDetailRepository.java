package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.OtherCurrentAssetDetail;

/**
 * @author Sanket
 *
 */
public interface OtherCurrentAssetDetailRepository extends JpaRepository<OtherCurrentAssetDetail, Long> {

	@Query("select o from OtherCurrentAssetDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<OtherCurrentAssetDetail> listOtherCurrentAssetFromAppId(@Param("id")Long id);

	@Query("select o from OtherCurrentAssetDetail o where o.coApplicantDetailId.id = :id and o.isActive = true")
	public List<OtherCurrentAssetDetail> listOtherCurrentAssetFromCoAppId(@Param("id")Long id);

	@Query("select o from OtherCurrentAssetDetail o where o.guarantorDetailId.id = :id and o.isActive = true")
	public List<OtherCurrentAssetDetail> listOtherCurrentAssetFromGarrId(@Param("id")Long id);

}
