package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.PastFinancialEstimatesDetail;

/**
 * @author Sanket
 *
 */
public interface PastFinancialEstimateDetailsRepository extends JpaRepository<PastFinancialEstimatesDetail, Long> {

	@Query("select o from PastFinancialEstimatesDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<PastFinancialEstimatesDetail> listPastFinancialEstimateDetailsFromAppId(@Param("id") Long id);

}
