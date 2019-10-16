package com.capitaworld.service.loans.service.colending;

import com.capitaworld.service.loans.model.colending.CoLendingApplicationBankMappingRequest;

/**
 * Created by dhaval.panchal on 27-Aug-19.
 */

public interface CoLendingApplicationBankMappingService {

    boolean save(CoLendingApplicationBankMappingRequest coLendingApplicationBankMappingRequest);

    CoLendingApplicationBankMappingRequest get(CoLendingApplicationBankMappingRequest coLendingApplicationBankMappingRequest);
}
