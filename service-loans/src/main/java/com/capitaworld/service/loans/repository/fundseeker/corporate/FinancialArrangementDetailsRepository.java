package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.FinancialArrangementsDetail;

/**
 * @author Sanket
 *
 */
public interface FinancialArrangementDetailsRepository extends JpaRepository<FinancialArrangementsDetail, Long> {

	@Query("select o from FinancialArrangementsDetail o where o.applicationId.id =:id and o.isActive = true")
	public List<FinancialArrangementsDetail> listSecurityCorporateDetailFromAppId(@Param("id")Long id);

}
