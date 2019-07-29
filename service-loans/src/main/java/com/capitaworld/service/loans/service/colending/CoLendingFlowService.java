package com.capitaworld.service.loans.service.colending;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.model.SpSysNotifyResponse;
import com.capitaworld.service.loans.model.common.NotificationPageRequest;
import org.json.simple.JSONObject;

import java.util.List;

public interface CoLendingFlowService {

	public List<ClientListingCoLending> clientListCoLending(int pageIndex, int size, Long npUserId) throws LoansException;

    /*public JSONObject spClientCount(Long spId) throws LoansException;

    public List<SpSysNotifyResponse> spClientNotifications(Long valueOf) throws LoansException;

    public List<SpSysNotifyResponse> spClientAllNotifications(Long valueOf, NotificationPageRequest notificationPageRequest) throws LoansException;

    public Long spClientAllNotificationsCount(Long valueOf, NotificationPageRequest notificationPageRequest) throws LoansException;*/

}
