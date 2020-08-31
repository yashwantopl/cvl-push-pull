package com.opl.service.loans.service.sidbi;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.FrameRequest;
import com.opl.mudra.api.loans.model.sidbi.RawMaterialDetailsRequest;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public interface RawMaterialDetailsService {

    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;
    public List<RawMaterialDetailsRequest> getRawMaterialDetailsListAppId(Long applicationId) throws LoansException;

}
