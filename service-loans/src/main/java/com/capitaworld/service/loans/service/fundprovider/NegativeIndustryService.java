package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;
import com.capitaworld.service.loans.exceptions.LoansException;

public interface NegativeIndustryService {

public void saveOrUpdate(NegativeIndustry negativeIndustry) throws LoansException;
	
	public List<Long> getNegativeIndustryByFpMappingId(Long fpMappingId) throws LoansException;
}
