package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.HLOneformPrimaryRes;
import com.capitaworld.service.loans.model.retail.HLOneformRequest;
import com.capitaworld.service.loans.model.retail.HLOnefromResponse;
import com.capitaworld.service.loans.model.retail.PrimaryHomeLoanDetailRequest;

public interface PrimaryHomeLoanService {

	public boolean saveOrUpdate(PrimaryHomeLoanDetailRequest homeLoanDetailRequest,Long userId) throws LoansException;
	
	public PrimaryHomeLoanDetailRequest get(Long id,Long userId) throws LoansException;
	
	public boolean saveProfileOneForm(HLOneformRequest req) throws LoansException;
	
	public HLOneformRequest getProfileDetailsById(Long applicationId,Long coAppId);
	
	public HLOnefromResponse getListForOneForm(Long applicationId);
	
	public HLOneformPrimaryRes getOneformPrimaryDetails(Long applicationId);
	
	public boolean saveOneformPrimaryDetails(HLOneformPrimaryRes hlOneformPrimaryRes) throws LoansException;
	
	
}
