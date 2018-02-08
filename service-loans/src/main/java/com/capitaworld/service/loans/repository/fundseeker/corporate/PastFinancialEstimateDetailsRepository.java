package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PastFinancialEstimatesDetail;
import com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest;

/**
 * @author Sanket
 *
 */
public interface PastFinancialEstimateDetailsRepository extends JpaRepository<PastFinancialEstimatesDetail, Long> {

	@Query("select o from PastFinancialEstimatesDetail o where o.applicationId.id = :id and o.isActive = true order by financialYear asc")
	public List<PastFinancialEstimatesDetail> listPastFinancialEstimateDetailsFromAppId(@Param("id") Long id);

	@Modifying
	@Query("update PastFinancialEstimatesDetail o set o.isActive = false , o.modifiedDate = NOW() where o.applicationId.id = :applicationId and o.id =:id and o.isActive = true")
	public int inactiveByApplicationAndId(
			@Param("applicationId") Long applicationId, @Param("id") Long id);

	@Query("select new com.capitaworld.service.loans.model.retail.PastFinancialEstimatesDetailRequest(o.id,o.applicationId.id,o.currentAssets,o.currentLiabilities,o.debt,o.ebitda,o.financialYear,o.fixedAssets,o.netWorth,o.pat,o.sales,o.contingentLiability,o.isActive) from PastFinancialEstimatesDetail o where o.applicationId.id=:id and isActive = true order by financialYear asc")
	public List<PastFinancialEstimatesDetailRequest> listPastFinancialEstimateDetailsRequestFromAppId(@Param("id") Long id);
	
	@Modifying
	@Query("update PastFinancialEstimatesDetail pm set pm.isActive = false,pm.modifiedDate = NOW(),pm.modifiedBy =:userId where pm.applicationId.id =:applicationId and pm.isActive = true")
	public int inActive(@Param("userId") Long userId,@Param("applicationId") Long applicationId);
}
