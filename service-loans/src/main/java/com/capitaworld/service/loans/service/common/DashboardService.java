package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.DashboardProfileResponse;

public interface DashboardService {
	public DashboardProfileResponse getBasicProfileInfo(Long applicationId,Long userId) throws Exception ;
}
