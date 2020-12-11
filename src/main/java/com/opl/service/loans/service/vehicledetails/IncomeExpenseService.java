package com.opl.service.loans.service.vehicledetails;

import com.opl.mudra.api.loans.model.ProposedVehicleIncomeExpenseRequest;

/**
 * Created by pooja.patel on 02-12-2020.
 */
public interface IncomeExpenseService {

    public ProposedVehicleIncomeExpenseRequest getIncomeExpenseDetailsByApplicationId(Long applicationId);
    public Boolean saveIncomeExpenseDetails(ProposedVehicleIncomeExpenseRequest incomeExpenseRequest,Long userId);

}
