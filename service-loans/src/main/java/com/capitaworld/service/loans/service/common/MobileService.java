package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;

public interface MobileService {

	public MRetailApplicantResponse getApplicantDetails(MobileLoanRequest mobileUserRequest) throws Exception;
	
	public Long saveApplicantDetails(MRetailApplicantResponse mRetailApplicantResponse) throws Exception; 
	
}
