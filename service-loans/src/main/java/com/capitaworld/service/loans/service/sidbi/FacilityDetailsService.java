package com.capitaworld.service.loans.service.sidbi;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.FrameRequest;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public interface FacilityDetailsService {

    public Boolean saveOrUpdate(FrameRequest frameRequest) throws LoansException;

}
