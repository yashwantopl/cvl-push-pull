package com.opl.service.loans.service.fundprovider.impl;

import java.util.Collections;
import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.service.loans.domain.fundprovider.NegativeIndustry;
import com.opl.service.loans.service.fundprovider.NegativeIndustryService;

public class NegativeIndustryServiceImpl implements NegativeIndustryService{

	@Override
	public void saveOrUpdate(NegativeIndustry negativeIndustry) throws LoansException {
		// Do nothing because of X and Y.
	}

	@Override
	public List<Long> getNegativeIndustryByFpMappingId(Long fpMappingId) throws LoansException {
		return Collections.emptyList();
	}

}
