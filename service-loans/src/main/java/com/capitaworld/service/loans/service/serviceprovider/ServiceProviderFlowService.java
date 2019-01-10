package com.capitaworld.service.loans.service.serviceprovider;

import java.util.List;


import com.capitaworld.service.loans.exceptions.LoansException;
import org.json.simple.JSONObject;


import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.model.SpSysNotifyResponse;
import com.capitaworld.service.loans.model.common.NotificationPageRequest;

public interface ServiceProviderFlowService {

	public List<SpClientListing> spClientList(int pageIndex,int size,Long spId,String userTypeCode) throws LoansException;


  public JSONObject spClientCount(Long spId) throws LoansException;


  public List<SpSysNotifyResponse> spClientNotifications(Long valueOf) throws LoansException;


public List<SpSysNotifyResponse> spClientAllNotifications(Long valueOf, NotificationPageRequest notificationPageRequest) throws LoansException;


public Long spClientAllNotificationsCount(Long valueOf, NotificationPageRequest notificationPageRequest) throws LoansException;

}
