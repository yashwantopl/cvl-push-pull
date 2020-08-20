package com.opl.service.loans.service.fundprovider;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.service.loans.domain.fundprovider.NegativeIndustry;

public interface NegativeIndustryService {

public void saveOrUpdate(NegativeIndustry negativeIndustry) throws LoansException;
	
	public List<Long> getNegativeIndustryByFpMappingId(Long fpMappingId) throws LoansException;
}
