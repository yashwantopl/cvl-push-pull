package com.capitaworld.service.loans.service.irr;

import org.json.simple.JSONObject;

public interface IrrService {

	public  JSONObject cmaIrrMappingService(Long aplicationId) throws Exception;
	
	public  JSONObject coActIrrMappingService(Long aplicationId) throws Exception;
}
