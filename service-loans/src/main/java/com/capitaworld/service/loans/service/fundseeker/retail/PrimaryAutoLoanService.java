package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.PrimaryAutoLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryCarLoanDetailRequest;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;

public interface PrimaryAutoLoanService {
	
	public boolean saveOneformPrimaryDetails(ALOneformPrimaryRes alOneformPrimaryRes, Long userId) throws LoansException;
	
	public boolean saveOrUpdate(PrimaryAutoLoanDetailRequest alOneformPrimaryRes, Long userId) throws LoansException;

	public ALOneformPrimaryRes getOneformPrimaryDetails(Long id);
	
	public PrimaryAutoLoanDetailRequest get(Long id,Long userId) throws LoansException;
	

}
