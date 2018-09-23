package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.PincodeDataResponse;

import java.util.List;

public interface PincodeDateService {
    public List<PincodeDataResponse> listData(String pincode);
    
    public PincodeDataResponse getById(Long id);
}
