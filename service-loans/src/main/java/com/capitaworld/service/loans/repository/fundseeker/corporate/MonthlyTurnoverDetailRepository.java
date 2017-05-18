package com.capitaworld.service.loans.repository.fundseeker.corporate;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.fundseeker.corporate.MonthlyTurnoverDetail;

/**
 * @author Sanket
 *
 */
public interface MonthlyTurnoverDetailRepository extends JpaRepository<MonthlyTurnoverDetail, Long> {

	@Query("select o from MonthlyTurnoverDetail o where o.applicationId.id = :id and o.isActive = true")
	public List<MonthlyTurnoverDetail> listMonthlyTurnoverFromAppId(@Param("id")Long id);

}
