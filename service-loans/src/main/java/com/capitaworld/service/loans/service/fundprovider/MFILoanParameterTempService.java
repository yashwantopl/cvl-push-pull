package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.mfi.MFILoanParameterRequest;

public interface MFILoanParameterTempService {

    public MFILoanParameterRequest getMFILoanParameterRequest(Long id,Long role,Long userId);

    public Boolean saveOrUpdateTemp(MFILoanParameterRequest mfiLoanParameterRequest);
}
