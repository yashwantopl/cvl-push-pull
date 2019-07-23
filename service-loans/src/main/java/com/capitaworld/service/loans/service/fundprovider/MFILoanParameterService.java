package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.mfi.MFILoanParameterRequest;

public interface MFILoanParameterService {

    public MFILoanParameterRequest getMFILoanParameterRequest(Long id);

    public MFILoanParameterRequest getTemp(Long id, Long role, Long userId);

    public boolean saveOrUpdate(MFILoanParameterRequest mfiLoanParameterRequest);

    public Boolean saveMasterFromTemp(Long mappingId) throws LoansException;
}
