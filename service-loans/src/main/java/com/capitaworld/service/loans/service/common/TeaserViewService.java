package com.capitaworld.service.loans.service.common;

public interface TeaserViewService {

	// this method for check fund seeker and fund provider primary teaser view validity
	public Boolean checkPrimaryTeaserViewIsValid(Long applicationId,Long fpProductId,Long userType);
	
	// this method for check fund seeker final view validity
	public Boolean checkFinalTeaserViewIsValid(Long applicationId);
}
