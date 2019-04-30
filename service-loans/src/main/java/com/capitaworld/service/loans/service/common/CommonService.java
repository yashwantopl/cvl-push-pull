package com.capitaworld.service.loans.service.common;

import java.util.Map;

public interface CommonService {
	
	public Map<String , Object> getCityStateCountryNameFromOneForm(Long cityId , Integer stateId ,Integer countryId);
	
}
