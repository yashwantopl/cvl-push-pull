package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;
import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.service.fundprovider.NegativeIndustryService;

public class NegativeIndustryServiceImpl implements NegativeIndustryService{

	@Override
	public void saveOrUpdate(NegativeIndustry negativeIndustry) throws LoansException {
		// Do nothing because of X and Y.
	}

	@Override
	public List<Long> getNegativeIndustryByFpMappingId(Long fpMappingId) throws LoansException {
		return null;
	}

}
