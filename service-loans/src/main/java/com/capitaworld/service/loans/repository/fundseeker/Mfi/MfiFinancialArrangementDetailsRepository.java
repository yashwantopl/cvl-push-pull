package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiFinancialArrangementsDetail;

/**
 * @author Sanket
 *
 */
public interface MfiFinancialArrangementDetailsRepository extends JpaRepository<MfiFinancialArrangementsDetail, Long> {
	
	@Modifying
	@Query("update MfiFinancialArrangementsDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:createdBy where pm.applicationId.id =:applicationId and pm.isActive = true and pm.applicantId =:applicantId")
	public int inActive(@Param("createdBy") Long createdBy, @Param("applicationId") Long applicationId, @Param("applicantId") Long applicantId);

	
}
