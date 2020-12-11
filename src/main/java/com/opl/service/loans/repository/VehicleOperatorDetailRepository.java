package com.opl.service.loans.repository;

import com.opl.service.loans.domain.VehicleOperatorDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public interface VehicleOperatorDetailRepository extends JpaRepository<VehicleOperatorDetail,Long> {
	
	VehicleOperatorDetail findByApplicationIdAndIsActive(Long applicationId, Boolean isActive);
}
