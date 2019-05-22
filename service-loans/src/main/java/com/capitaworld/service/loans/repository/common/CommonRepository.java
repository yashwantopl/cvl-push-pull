package com.capitaworld.service.loans.repository.common;

public interface CommonRepository {

	public Object[] getUserCampainCodeByApplicationId(Long applicationId);
	
	public Object[] getEmailDataByApplicationId(Long applicationId);
	
	public String getCoApplicatantNameFromITR(Long coAppId);
	
}
