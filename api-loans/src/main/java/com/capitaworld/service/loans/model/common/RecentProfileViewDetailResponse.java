package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Sanket
 *
 */
public class RecentProfileViewDetailResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Map<String , List<RecentProfileViewResponse>> recentProfileMap;

	public Map<String, List<RecentProfileViewResponse>> getRecentProfileMap() {
		return recentProfileMap;
	}

	public void setRecentProfileMap(Map<String, List<RecentProfileViewResponse>> recentProfileMap) {
		this.recentProfileMap = recentProfileMap;
	}
	
	
	

}
