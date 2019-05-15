package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.LoanPurposeAmountMappingRequest;

public interface LoanPurposeAmountMappingService {
	public Boolean save(Long fpProductId, Integer type, Double min,Double max);
	
	public int deleteByFpProductId(Long fpProductId);
	
	public Boolean save(List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequest,Long fpProductId);
	
	public Boolean save(LoanPurposeAmountMappingRequest loanPurposeAmountMappingRequest);
	
	public Boolean deleteAndSave(List<LoanPurposeAmountMappingRequest> loanPurposeAmountMappingRequest,Long fpProductId);
	
	public List<LoanPurposeAmountMappingRequest> getByFpProductId(Long fpProductId);
	
}
