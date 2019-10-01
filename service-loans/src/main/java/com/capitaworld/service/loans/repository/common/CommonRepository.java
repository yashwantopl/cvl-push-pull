package com.capitaworld.service.loans.repository.common;

import java.util.List;

import com.capitaworld.service.loans.model.teaser.primaryview.CommonRequest;

public interface CommonRepository {

	public Object[] getUserCampainCodeByApplicationId(Long applicationId);
	
	public Object[] getEmailDataByApplicationId(Long applicationId);
	
	public String getCoApplicatantNameFromITR(Long coAppId);

	public List<Object[]>  getBranchUserDetailsBasedOnRoleId(Long orgId,Integer roleId);
	
	public Object[] getFpFullName(Long userId); 
	
	public Object getMakerDate(Long applicationId);
	
	public Integer getViewedTeaser(String emailId);
	
	public String getEmailIdFromUsers(Long userId);
	
	public String getNoteForHLCam(Long applicationId);
	
	public Object[] getInEligibleByApplicationId(Long applicationId);
	
	public String getSidbiAmount();
	
	public List<Object[]> getBankDetails(Long applicationId, Long orgId);

	public Object[] getUserDetailsByApplicationId(Long applicationId) throws Exception;
}
