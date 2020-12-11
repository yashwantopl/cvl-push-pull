package com.opl.service.loans.service.vehicledetails;

import com.opl.mudra.api.loans.model.VehicleOperatorRequest;

public interface VehicleOperatorService {

	public VehicleOperatorRequest getByApplicationId(Long applicationId);
	
	public Boolean saveDetail(VehicleOperatorRequest req, Long userId);
}
