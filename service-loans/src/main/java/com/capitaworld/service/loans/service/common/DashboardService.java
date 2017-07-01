package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.users.model.UserResponse;

public interface DashboardService {
	public DashboardProfileResponse getBasicProfileInfo(Long applicationId,Long userId,boolean isSP) throws Exception ;
	
	public Integer getCount(int userType) throws Exception;
	
	public UserResponse getFPBasicProfileInfo(Long userId) throws Exception ;
}
