package com.capitaworld.service.loans.repository.common;

import java.util.List;

public interface CommonRepository {

	public Object[] getUserCampainCodeByApplicationId(Long applicationId);
	
	public Object[] getEmailDataByApplicationId(Long applicationId);
	
	public String getCoApplicatantNameFromITR(Long coAppId);

	public List<Object[]>  getBranchUserDetailsBasedOnRoleId(Long orgId,Integer roleId);
	
	public Object[] getFpFullName(Long userId); 
}
