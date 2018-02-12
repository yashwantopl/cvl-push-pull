package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;

/**
 * @author Sanket
 *
 */
public interface FinancialArrangementDetailsRepository extends JpaRepository<FinancialArrangementsDetail, Long> {

	@Query("select o from FinancialArrangementsDetail o where o.applicationId.id =:id  and o.applicationId.userId =:userId and o.isActive = true")
	public List<FinancialArrangementsDetail> listSecurityCorporateDetailFromAppId(@Param("id")Long id, @Param("userId") Long userId);
	
	@Modifying
	@Query("update FinancialArrangementsDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);


}
