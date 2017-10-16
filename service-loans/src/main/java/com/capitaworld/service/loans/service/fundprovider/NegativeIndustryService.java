package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.domain.fundprovider.NegativeIndustry;

public interface NegativeIndustryService {

public void saveOrUpdate(NegativeIndustry negativeIndustry) throws Exception;
	
	public List<Long> getNegativeIndustryByFpMappingId(Long fpMappingId) throws Exception;
}
