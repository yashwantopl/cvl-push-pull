package com.capitaworld.service.loans.repository.sanction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.capitaworld.service.loans.domain.sanction.LoanSanctionDomain;

/**
 * @author Ankit
 *
 */
public interface LoanSanctionRepository extends JpaRepository<LoanSanctionDomain, Long> {
	/*@Query("select o from BankAccountHeldDetail o where o.applicantId.id = :id and o.isActive = true")*/
															  
	@Query("SELECT lsd FROM LoanSanctionDomain lsd where lsd.applicationId =:applicationId AND lsd.isActive = true")
	public LoanSanctionDomain findByAppliationId(@Param("applicationId") Long applicationId);
}
