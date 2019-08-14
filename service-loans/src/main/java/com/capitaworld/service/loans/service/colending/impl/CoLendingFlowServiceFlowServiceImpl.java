package com.capitaworld.service.loans.service.colending.impl;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.ClientListingCoLending;
import com.capitaworld.service.loans.service.colending.CoLendingFlowService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.NbfcClientResponse;
import com.capitaworld.service.users.model.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class CoLendingFlowServiceFlowServiceImpl implements CoLendingFlowService {

	private static final Logger logger = LoggerFactory.getLogger(CoLendingFlowServiceFlowServiceImpl.class);

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	private UsersClient usersClient;

	private static final String ERROR_WHILE_GETTING_CLIENT_LIST = "Error while getting client list.";
	private static final String ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT = "Error while getting NBFC client count.";

	@Override
	public List<ClientListingCoLending> clientListCoLending(int pageIndex,int size,Long npUserId) throws LoansException {
		try {
			UserResponse userResponse = usersClient.getNbfcUserIdClientMappingList(pageIndex,size,npUserId);
			List<Map<String, Object>> nbfcClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			List<ClientListingCoLending> clientListings = new ArrayList<ClientListingCoLending>();
			for (int i = 0; i < nbfcClientResponseList.size(); i++) {
				NbfcClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(nbfcClientResponseList.get(i), NbfcClientResponse.class);
				ClientListingCoLending clientDetailCoLending = new ClientListingCoLending();
				clientDetailCoLending.setClientId(clientResponse.getClientId());
				clientDetailCoLending.setClientName(clientResponse.getClientName());
				clientDetailCoLending.setClientEmail(clientResponse.getClientEmail());
				clientDetailCoLending.setClientMobile(clientResponse.getClientMobile());
				clientDetailCoLending.setLastAccessId(clientResponse.getLastAccessId());
				//get city name
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCity()) && clientResponse.getClientCity() != 0) {
					List<Long> cityList = new ArrayList<>();
					cityList.add((long) clientResponse.getClientCity());
					OneFormResponse cityResponse = oneFormClient.getCityByCityListId(cityList);
					List<Map<String, Object>> cityResponseDatalist = (List<Map<String, Object>>) cityResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(cityResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						clientDetailCoLending.setClientCity(masterResponse.getValue());
					} else {
						clientDetailCoLending.setClientCity("NA");
					}
				} else {
					clientDetailCoLending.setClientCity("NA");
				}
				//get state name
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientState())
						&& clientResponse.getClientState() != 0) {
					List<Long> stateList = new ArrayList<>();
					stateList.add((long) clientResponse.getClientState());
					OneFormResponse stateResponse = oneFormClient.getStateByStateListId(stateList);
					List<Map<String, Object>> stateResponseDatalist = (List<Map<String, Object>>) stateResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(stateResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						clientDetailCoLending.setClientState(masterResponse.getValue());
					} else {
						clientDetailCoLending.setClientState("NA");
					}
				} else {
					clientDetailCoLending.setClientState("NA");
				}
				clientListings.add(clientDetailCoLending);

			}
			return clientListings;
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_CLIENT_LIST,e);
			throw new LoansException(ERROR_WHILE_GETTING_CLIENT_LIST);
		}

	}
	
	@Override
	public JSONObject nbfcClientCount(Long nbfcUserId) throws LoansException {
		try {
			UserResponse response = usersClient.getNbfcClientCount(nbfcUserId);
			if(!CommonUtils.isObjectNullOrEmpty(response.getData())){
				return MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)response.getData(), JSONObject.class);
			}
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT,e);
			throw new LoansException(ERROR_WHILE_GETTING_NBFC_CLIENT_COUNT);
		}
		return null;
	}

	/*@Override
	public List<SpSysNotifyResponse> spClientNotifications(Long spId) throws LoansException {
		String[] userTypeIds = {"fs","fp"};
		List<SpSysNotifyResponse> spSysNotifResponse = new ArrayList<SpSysNotifyResponse>();
		try {
			for(String userTpyeId : userTypeIds){
			UserResponse userResponse = usersClient.getSpUserIdClientMappingList(0,Integer.MAX_VALUE,spId, userTpyeId);
			List<Map<String, Object>> spClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			for (int i = 0; i < spClientResponseList.size(); i++) {
				SpClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(spClientResponseList.get(i),
						SpClientResponse.class);
				
				if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDSEEKER)) {
					List<LoanApplicationDetailsForSp> fsClientDetails = loanApplicationService.getLoanDetailsByUserIdList(clientResponse.getClientId());
					for (LoanApplicationDetailsForSp applicationDetailsForSp : fsClientDetails) {
						if(!CommonUtils.isObjectNullOrEmpty(applicationDetailsForSp.getProductId())){
//							boolean applied = loanApplicationService.hasAlreadyApplied(clientResponse.getClientId(), applicationDetailsForSp.getId(),applicationDetailsForSp.getProductId());
//							applicationDetailsForSp.setHasAlreadyApplied(applied);
//							applicationDetailsForSp.setApplicationType(CommonUtils.getUserMainType(applicationDetailsForSp.getProductId()));
//							applicationDetailsForSp.setProductName(LoanType.getById(applicationDetailsForSp.getProductId()).getValue());
							//code for sp fs notification
							NotificationRequest notificationRequestSpFS = new NotificationRequest();
							notificationRequestSpFS.setApplicationId(applicationDetailsForSp.getId());
							notificationRequestSpFS.setClientRefId(clientResponse.getClientId().toString());
							NotificationResponse responseSpFsCount = notificationClient.getAllUnreadNotificationByAppId(notificationRequestSpFS);
							List<SysNotifyResponse> sysNotificationSpFs = responseSpFsCount.getSysNotification();
							
							
							if(!CommonUtils.isListNullOrEmpty(sysNotificationSpFs)){
							for(SysNotifyResponse source : sysNotificationSpFs){
								SpSysNotifyResponse target = new SpSysNotifyResponse();
							BeanUtils.copyProperties(source, target);
							spSysNotifResponse.add(target);
							
							}
							}
							
							
						}else{
							applicationDetailsForSp.setProductName("NA");
						}
					}
					

				} 
				
				
				else if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDPROVIDER)) {

					
					List<ProductDetailsForSp> fpClientDetails = productMasterService.getProductDetailsByUserIdList(clientResponse.getClientId());
					for(ProductDetailsForSp productDetailsForSp : fpClientDetails){
						if(CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getName())){
							productDetailsForSp.setName(!CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getProductId()) ? LoanType.getById(productDetailsForSp.getProductId()).getValue() : "NA");
						}
						//code for sp fp notification
						NotificationRequest notificationRequestSpFp = new NotificationRequest();
						notificationRequestSpFp.setProductId(productDetailsForSp.getId());
						notificationRequestSpFp.setClientRefId(clientResponse.getClientId().toString());
						NotificationResponse responseSpFsCount = notificationClient.getAllUnreadNotificationByProdId(notificationRequestSpFp);
						List<SysNotifyResponse> sysNotificationSpFs = responseSpFsCount.getSysNotification();

						productDetailsForSp.setSysNotifyResponse(new ArrayList<SpSysNotifyResponse>());
						if(!CommonUtils.isListNullOrEmpty(sysNotificationSpFs)){
							for(SysNotifyResponse source : sysNotificationSpFs){
								SpSysNotifyResponse target = new SpSysNotifyResponse();
							BeanUtils.copyProperties(source, target);
							spSysNotifResponse.add(target);
							
							}
						}
						
						
					}
				}
			}
			}
			return spSysNotifResponse;
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_CLIENT_LIST,e);
			throw new LoansException(ERROR_WHILE_GETTING_CLIENT_LIST);
		}
		
	
	}

	@Override
	public List<SpSysNotifyResponse> spClientAllNotifications(Long spId, NotificationPageRequest notificationPageRequest) throws LoansException {
		String[] userTypeIds = {"fs","fp"};
		List<SpSysNotifyResponse> spSysNotifResponse = new ArrayList<SpSysNotifyResponse>();
		try {
			for(String userTpyeId : userTypeIds){
			UserResponse userResponse = usersClient.getSpUserIdClientMappingList(0,Integer.MAX_VALUE,spId, userTpyeId);
			List<Map<String, Object>> spClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			for (int i = 0; i < spClientResponseList.size(); i++) {
				SpClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(spClientResponseList.get(i),
						SpClientResponse.class);
				
				if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDSEEKER)) {
					List<LoanApplicationDetailsForSp> fsClientDetails = loanApplicationService.getLoanDetailsByUserIdList(clientResponse.getClientId());
					for (LoanApplicationDetailsForSp applicationDetailsForSp : fsClientDetails) {
						if(!CommonUtils.isObjectNullOrEmpty(applicationDetailsForSp.getProductId())){
							//code for sp fs notification
							NotificationRequest notificationRequestSpFS = new NotificationRequest();
							notificationRequestSpFS.setApplicationId(applicationDetailsForSp.getId());
							notificationRequestSpFS.setClientRefId(clientResponse.getClientId().toString());
							notificationRequestSpFS.setClientId(clientResponse.getClientId());
			                notificationRequestSpFS.setPageIndex(notificationPageRequest.getPageIndex());
			                notificationRequestSpFS.setSize(notificationPageRequest.getSize());
							NotificationResponse responseSpFsCount = notificationClient.getAllSPNotificationByAppId(notificationRequestSpFS);
							List<SysNotifyResponse> sysNotificationSpFs = responseSpFsCount.getSysNotification();
							
							
							if(!CommonUtils.isListNullOrEmpty(sysNotificationSpFs)){
							for(SysNotifyResponse source : sysNotificationSpFs){
								SpSysNotifyResponse target = new SpSysNotifyResponse();
							BeanUtils.copyProperties(source, target);
							spSysNotifResponse.add(target);
							
							}
							}
							
							
						}else{
							applicationDetailsForSp.setProductName("NA");
						}
					}
					

				} 
				
				
				else if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDPROVIDER)) {

					
					List<ProductDetailsForSp> fpClientDetails = productMasterService.getProductDetailsByUserIdList(clientResponse.getClientId());
					for(ProductDetailsForSp productDetailsForSp : fpClientDetails){
						if(CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getName())){
							productDetailsForSp.setName(!CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getProductId()) ? LoanType.getById(productDetailsForSp.getProductId()).getValue() : "NA");
						}
						//code for sp fp notification
						NotificationRequest notificationRequestSpFp = new NotificationRequest();
						notificationRequestSpFp.setProductId(productDetailsForSp.getId());
						notificationRequestSpFp.setClientRefId(clientResponse.getClientId().toString());
						notificationRequestSpFp.setClientId(clientResponse.getClientId());
						notificationRequestSpFp.setPageIndex(notificationPageRequest.getPageIndex());
						notificationRequestSpFp.setSize(notificationPageRequest.getSize());
						NotificationResponse responseSpFsCount = notificationClient.getAllSPNotificationByProdId(notificationRequestSpFp);
						List<SysNotifyResponse> sysNotificationSpFs = responseSpFsCount.getSysNotification();

						productDetailsForSp.setSysNotifyResponse(new ArrayList<SpSysNotifyResponse>());
						if(!CommonUtils.isListNullOrEmpty(sysNotificationSpFs)){
							for(SysNotifyResponse source : sysNotificationSpFs){
								SpSysNotifyResponse target = new SpSysNotifyResponse();
							BeanUtils.copyProperties(source, target);
							spSysNotifResponse.add(target);
							
							}
						}
						
						
					}
				}
			}
			}
			List<SpSysNotifyResponse> spSysNotifResponses;
			spSysNotifResponses = getPages(spSysNotifResponse,notificationPageRequest.getPageIndex(), notificationPageRequest.getSize());
			return spSysNotifResponses;
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_CLIENT_LIST,e);
			throw new LoansException(ERROR_WHILE_GETTING_CLIENT_LIST);
		}
		
	
	}

	@Override
	public Long spClientAllNotificationsCount(Long spId, NotificationPageRequest notificationPageRequest) throws LoansException{
		String[] userTypeIds = {"fs","fp"};
		Long totalCount = 0L;
		try {
			for(String userTpyeId : userTypeIds){
			UserResponse userResponse = usersClient.getSpUserIdClientMappingList(0,Integer.MAX_VALUE,spId, userTpyeId);
			List<Map<String, Object>> spClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			for (int i = 0; i < spClientResponseList.size(); i++) {
				SpClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(spClientResponseList.get(i),
						SpClientResponse.class);
				
				if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDSEEKER)) {
					List<LoanApplicationDetailsForSp> fsClientDetails = loanApplicationService.getLoanDetailsByUserIdList(clientResponse.getClientId());
					for (LoanApplicationDetailsForSp applicationDetailsForSp : fsClientDetails) {
						if(!CommonUtils.isObjectNullOrEmpty(applicationDetailsForSp.getProductId())){
							//code for sp fs notification
							NotificationRequest notificationRequestSpFS = new NotificationRequest();
							notificationRequestSpFS.setApplicationId(applicationDetailsForSp.getId());
							notificationRequestSpFS.setClientRefId(clientResponse.getClientId().toString());
							notificationRequestSpFS.setClientId(clientResponse.getClientId());
							notificationRequestSpFS.setUserType("fs2");
			                notificationRequestSpFS.setPageIndex(notificationPageRequest.getPageIndex());
			                notificationRequestSpFS.setSize(notificationPageRequest.getSize());
							NotificationResponse responseSpFsCount = notificationClient.getNotificationCount(notificationRequestSpFS);
							Long sysNotificationSpFs = responseSpFsCount.getCount();
							totalCount = totalCount+sysNotificationSpFs;
							
						}else{
							applicationDetailsForSp.setProductName("NA");
						}
					}
					

				} 
				
				
				else if (userTpyeId.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDPROVIDER)) {

					
					List<ProductDetailsForSp> fpClientDetails = productMasterService.getProductDetailsByUserIdList(clientResponse.getClientId());
					for(ProductDetailsForSp productDetailsForSp : fpClientDetails){
						if(CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getName())){
							productDetailsForSp.setName(!CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getProductId()) ? LoanType.getById(productDetailsForSp.getProductId()).getValue() : "NA");
						}
						//code for sp fp notification
						NotificationRequest notificationRequestSpFp = new NotificationRequest();
						notificationRequestSpFp.setProductId(productDetailsForSp.getId());
						notificationRequestSpFp.setClientRefId(clientResponse.getClientId().toString());
						notificationRequestSpFp.setClientId(clientResponse.getClientId());
						notificationRequestSpFp.setUserType("fp2");
						notificationRequestSpFp.setPageIndex(notificationPageRequest.getPageIndex());
						notificationRequestSpFp.setSize(notificationPageRequest.getSize());
						NotificationResponse responseSpFsCount = notificationClient.getNotificationCount(notificationRequestSpFp);
						Long sysNotificationSpFs = responseSpFsCount.getCount();
						totalCount=totalCount+sysNotificationSpFs;
					}
				}
			}
			}
			return totalCount;
			
		} catch (Exception e) {
			logger.error(ERROR_WHILE_GETTING_CLIENT_LIST,e);
			throw new LoansException(ERROR_WHILE_GETTING_CLIENT_LIST);
		}
		
	
	}	
	
	public static <T> List<T> getPages(Collection<T> c, Integer index, Integer pageSize) {
	    if (c == null)
	        return Collections.emptyList();
	    List<T> list = new ArrayList<T>(c);
	    if (pageSize == null || pageSize <= 0 || pageSize > list.size())
	        pageSize = list.size();
	    int numPages = (int) Math.ceil((double)list.size() / (double)pageSize);
	    List<List<T>> pages = new ArrayList<List<T>>(numPages);
	    for (int pageNum = 0; pageNum < numPages;){
	        pages.add(list.subList(pageNum * pageSize, Math.min(++pageNum * pageSize, list.size())));
	    	}
	    return pages.get(index);
	}*/
	
}
