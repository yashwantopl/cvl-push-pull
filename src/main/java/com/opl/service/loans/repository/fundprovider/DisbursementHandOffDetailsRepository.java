package com.opl.service.loans.repository.fundprovider;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.fundprovider.DisbursementHandOffDetails;

public interface DisbursementHandOffDetailsRepository extends JpaRepository<DisbursementHandOffDetails, Long> {

	public DisbursementHandOffDetails findByApplicationIdAndIsActive(Long applicationId,Boolean isActive);
}
