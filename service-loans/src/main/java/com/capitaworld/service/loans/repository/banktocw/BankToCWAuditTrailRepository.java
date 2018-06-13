package com.capitaworld.service.loans.repository.banktocw;

import org.springframework.data.jpa.repository.JpaRepository;
import com.capitaworld.service.loans.domain.BankCWAuditTrailDomain;

public interface BankToCWAuditTrailRepository extends JpaRepository<BankCWAuditTrailDomain, Long>{

}
