package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;

public interface PrimaryAutoLoanService {
	
	public boolean saveOrUpdate(ALOneformPrimaryRes alOneformPrimaryRes, Long userId) throws LoansException;

	public ALOneformPrimaryRes getOneformPrimaryDetails(Long id, Long userId) throws LoansException;
	

}
