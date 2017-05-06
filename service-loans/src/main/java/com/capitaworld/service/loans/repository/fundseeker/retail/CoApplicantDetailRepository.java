package com.capitaworld.service.loans.repository.fundseeker.retail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.retail.CoApplicantDetail;

/**
 * @author Sanket
 *
 */
public interface CoApplicantDetailRepository extends JpaRepository<CoApplicantDetail, Long> {

	@Query("from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.applicationId.userId =:userId and cd.isActive = true")
	public CoApplicantDetail get(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

	@Modifying
	@Query("update CoApplicantDetail cd set cd.isActive = false,cd.modifiedDate = NOW() where cd.applicationId.id =:applicationId and cd.applicationId.userId =:userId and cd.isActive = true")
	public int inactiveCoApplicant(@Param("applicationId") Long applicationId, @Param("userId") Long id);

	@Query("from CoApplicantDetail cd where cd.applicationId.id =:applicationId and cd.isActive = true and cd.applicationId.userId =:userId")
	public List<CoApplicantDetail> getList(@Param("applicationId") Long applicationId, @Param("userId") Long userId);

}
