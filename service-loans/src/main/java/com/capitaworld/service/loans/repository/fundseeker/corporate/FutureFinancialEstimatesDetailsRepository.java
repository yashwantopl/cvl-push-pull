package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FutureFinancialEstimatesDetail;

/**
 * @author Sanket
 *
 */
public interface FutureFinancialEstimatesDetailsRepository extends JpaRepository<FutureFinancialEstimatesDetail, Long> {

	@Query("select o from FutureFinancialEstimatesDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<FutureFinancialEstimatesDetail> listFutureFinancialEstimateDetailsFromAppId(@Param("id") Long id);

	@Modifying
	@Query("update FutureFinancialEstimatesDetail o set o.isActive = false where o.applicationId.id = :applicationId and o.isActive = true and o.id =:id")
	public int inactiveByApplicationAndId(@Param("applicationId") Long applicationId, @Param("id") Long id);

}
