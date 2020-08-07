package com.opl.service.loans.repository.common;

import org.springframework.data.jpa.repository.JpaRepository;

import com.opl.service.loans.domain.common.PaymentGatewayAuditMaster;

public interface PaymentGatewayAuditMasterRepository extends JpaRepository<PaymentGatewayAuditMaster, Long>{

	
	
}
