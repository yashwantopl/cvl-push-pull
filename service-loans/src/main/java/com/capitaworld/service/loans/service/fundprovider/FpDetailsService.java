package com.capitaworld.service.loans.service.fundprovider;

import com.capitaworld.service.loans.model.FpDetailsRequest;

public interface FpDetailsService {
	public boolean saveOrUpdate(FpDetailsRequest fpDetailRequest);
	
	public FpDetailsRequest getFpDetail(Long id);
}
