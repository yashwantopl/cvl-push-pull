package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.common.UserLoanAmountMappingRequest;

/**
 * @author harshit
 * Date : 11-Jun-2018
 */
public interface UserLoanAmountMappingService {

	public Long save(UserLoanAmountMappingRequest amountMappingRequest);
	
	public Boolean checkAmountByUserIdAndProductId(Long userId, Double amount,Long productId);
	
	public UserLoanAmountMappingRequest getByUserIdAndProductId(Long userId, Long productId);
}
