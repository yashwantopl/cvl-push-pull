package com.opl.service.loans.service.common;

import java.util.List;

import com.opl.mudra.api.loans.model.PincodeDataResponse;

public interface PincodeDateService {
    public List<PincodeDataResponse> listData(String pincode);
    
    public PincodeDataResponse getById(Long id);
}
