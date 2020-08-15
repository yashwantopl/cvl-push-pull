package com.opl.service.loans.repository.common;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.common.OfflineAppConfigAudit;

public interface OfflineAppConfigAuditRepository extends JpaRepository<OfflineAppConfigAudit,Long> {

	OfflineAppConfigAudit findFirstByOrgIdAndLoanTypeAndBusinessTypeIdAndIsActiveOrderByIdDesc(Long orgId,Integer loanType,Integer businessTypeId,Boolean isActive);
	
	List<OfflineAppConfigAudit> findByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(Long orgId,Integer businessTypeId,Boolean isActive);
    
}
