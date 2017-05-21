package com.capitaworld.service.loans.service.serviceprovider;

import java.util.List;

import org.json.simple.JSONArray;

import com.capitaworld.service.loans.model.SpClientListing;

public interface ServiceProviderFlowService {

	public List<SpClientListing> spClientList(Long spId,String userTypeCode) throws Exception;
}
