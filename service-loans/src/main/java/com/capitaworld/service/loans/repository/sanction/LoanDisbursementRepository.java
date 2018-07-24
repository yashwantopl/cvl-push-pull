package com.capitaworld.service.loans.repository.sanction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;

import java.util.List;

/**
 * @author Ankit
 *
 */
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursementDomain, Long> {

	@Query("SELECT sum(ldd.disbursedAmount) FROM  LoanDisbursementDomain ldd WHERE ldd.applicationId =:applicationId and ldd.isActive =true")
	public Double getTotalDisbursedAmount(@Param("applicationId") Long applicationId );

	@Query("select obj from LoanDisbursementDomain obj where obj.applicationId=:applicationId and obj.isActive =true")
	public List<LoanDisbursementDomain> getDisbursedListByApplicationId(@Param("applicationId") Long applicationId);
}
