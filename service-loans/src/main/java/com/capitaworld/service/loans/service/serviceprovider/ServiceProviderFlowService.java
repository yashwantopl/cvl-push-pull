package com.capitaworld.service.loans.service.serviceprovider;

import java.util.List;


import org.json.simple.JSONObject;


import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.model.SpSysNotifyResponse;

public interface ServiceProviderFlowService {

	public List<SpClientListing> spClientList(int pageIndex,Long spId,String userTypeCode) throws Exception;


  public JSONObject spClientCount(Long spId) throws Exception;


  public List<SpSysNotifyResponse> spClientNotifications(Long valueOf) throws Exception;


public List<SpSysNotifyResponse> spClientAllNotifications(Long valueOf) throws Exception;

}
