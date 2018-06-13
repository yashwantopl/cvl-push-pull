package com.capitaworld.service.loans.repository.sanction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
/**
 * @author Ankit
 *
 */
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursementDomain, Long> {

	@Query("SELECT sum(ldd.disbursedAmount) FROM  LoanDisbursementDomain ldd WHERE ldd.applicationId =:applcationId and ldd.isActive =true")
	public Double getTotalDisbursedAmoount(@Param("applicationId") Long applicationId );
}
