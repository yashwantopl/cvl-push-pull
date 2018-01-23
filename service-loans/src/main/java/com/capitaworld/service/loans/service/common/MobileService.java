package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.mobile.MApplicantProfileResponse;
import com.capitaworld.service.loans.model.mobile.MRetailApplicantResponse;
import com.capitaworld.service.loans.model.mobile.MRetailCoAppGuarResponse;
import com.capitaworld.service.loans.model.mobile.MobileFrameRequest;
import com.capitaworld.service.loans.model.mobile.MobileLoanRequest;

public interface MobileService {

	public MRetailApplicantResponse getApplicantDetails(MobileLoanRequest mobileUserRequest) throws Exception;
	
	public Long saveApplicantDetails(MApplicantProfileResponse mApplicantProfileResponse) throws Exception; 
	
	public MRetailCoAppGuarResponse getGuarantorDetails(MobileLoanRequest mobileUserRequest);
	
	public Long saveGuarantorDetails(MRetailCoAppGuarResponse response);
	
	public MRetailCoAppGuarResponse getCoApplicantDetails(MobileLoanRequest mobileUserRequest);
	
	public Long saveCoApplicantDetails(MRetailCoAppGuarResponse response);
	
	public Long saveLoanApplicationDetails(MobileFrameRequest request) throws Exception;
	
}
