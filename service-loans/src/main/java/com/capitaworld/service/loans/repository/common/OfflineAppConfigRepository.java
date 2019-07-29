package com.capitaworld.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capitaworld.service.loans.domain.common.OfflineAppConfig;

public interface OfflineAppConfigRepository extends JpaRepository<OfflineAppConfig,Long> {

	OfflineAppConfig findFirstByOrgIdAndLoanTypeAndBusinessTypeIdAndIsActiveOrderByIdDesc(Long orgId,Integer loanType,Integer businessTypeId,Boolean isActive);
	
	OfflineAppConfig findFirstByOrgIdAndBusinessTypeIdAndIsActiveOrderByIdDesc(Long orgId,Integer businessTypeId,Boolean isActive);
    
}
