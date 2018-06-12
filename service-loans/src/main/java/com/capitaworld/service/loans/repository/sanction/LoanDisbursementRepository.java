package com.capitaworld.service.loans.repository.sanction;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capitaworld.service.loans.domain.sanction.LoanDisbursementDomain;
/**
 * @author Ankit
 *
 */
public interface LoanDisbursementRepository extends JpaRepository<LoanDisbursementDomain, Long> {

}
