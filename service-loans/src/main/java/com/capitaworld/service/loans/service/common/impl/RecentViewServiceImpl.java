package com.capitaworld.service.loans.service.common.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.common.RecentProfileViewDetailResponse;
import com.capitaworld.service.loans.model.common.RecentProfileViewResponse;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.service.common.RecentViewService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.model.NotificationResponse;
import com.capitaworld.service.notification.model.SysNotifyResponse;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;

/**
 * @author Sanket
 *
 */
@Service
public class RecentViewServiceImpl implements RecentViewService{
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private DMSClient dmsClient;
	
	@Autowired
	private OneFormClient oneFormClient; 
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CorporateApplicantDetailRepository corporateApplicantDetailRepository;
	
	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;

	@Override
	public RecentProfileViewDetailResponse getRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException {
		// TODO Auto-generated method stub
		NotificationRequest request = new NotificationRequest();
		request.setApplicationId(applicationId);
		NotificationResponse notificationResponse = null;
		
		try {
			notificationResponse =  notificationClient.getAllRecentViewNotificationByAppId(request);
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map<String, List<SysNotifyResponse>> a = notificationResponse.getRecentViewResponse();
		List<SysNotifyResponse> thisWeek = a.get("thisWeek");
		List<SysNotifyResponse> thisMonth = a.get("thisMonth");
		List<SysNotifyResponse> earlier = a.get("earlier");
		
		RecentProfileViewDetailResponse detailResponse = new RecentProfileViewDetailResponse();
		
		Map<String, List<RecentProfileViewResponse>> map = new HashMap<String, List<RecentProfileViewResponse>>();
		List<RecentProfileViewResponse> listThisWeek = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : thisWeek){
			
			RecentProfileViewResponse thisWeekResp = new RecentProfileViewResponse();
//			Long fpProductId = response.getProductId();
			// calling DMS for getting fp profile image path
			
			
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setUserId(response.getUserId());
			documentRequest.setUserType("user");
			documentRequest.setUserDocumentMappingId(1L);
			
			DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
			String imagePath = "";
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				List<Map<String, Object>> list = documentResponse.getDataList();
				if (!CommonUtils.isListNullOrEmpty(list)) {
					StorageDetailsResponse storageResponse = null;

					storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
					if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
					else
							imagePath="";
				}
			}
			
			// calling USER for getting fp details
			UserResponse userResponse = usersClient.getFPDashboardDetails(response.getUserId());
			
			FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
			
			thisWeekResp.setProfilePic(imagePath);
			thisWeekResp.setName(fundProviderDetailsRequest.getOrganizationName());
			thisWeekResp.setAddress(fundProviderDetailsRequest.getAddress());
			thisWeekResp.setApplicationId(response.getApplicationId());
			thisWeekResp.setProductId(response.getProductId());
			thisWeekResp.setUserId(response.getUserId());
			listThisWeek.add(thisWeekResp);
			
		}
		map.put("thisWeek", listThisWeek);
		
		
		List<RecentProfileViewResponse> listThisMonth = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : thisMonth){
			
			RecentProfileViewResponse thisMonthResp = new RecentProfileViewResponse();
			Long fpProductId = response.getProductId();
			// calling DMS for getting fp profile image path
			
			
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setUserId(response.getUserId());
			documentRequest.setUserType("user");
			documentRequest.setUserDocumentMappingId(1L);
			
			DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
			String imagePath = "";
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				List<Map<String, Object>> list = documentResponse.getDataList();
				if (!CommonUtils.isListNullOrEmpty(list)) {
					StorageDetailsResponse storageResponse = null;

					storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
					if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
					else
							imagePath="";
				}
			}
			
			// calling USER for getting fp details
			UserResponse userResponse = usersClient.getFPDashboardDetails(response.getUserId());
			
			FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
			
			thisMonthResp.setProfilePic(imagePath);
			thisMonthResp.setName(fundProviderDetailsRequest.getOrganizationName());
			thisMonthResp.setAddress(fundProviderDetailsRequest.getAddress());
			thisMonthResp.setApplicationId(response.getApplicationId());
			thisMonthResp.setProductId(response.getProductId());
			thisMonthResp.setUserId(response.getUserId());
			listThisMonth.add(thisMonthResp);
			
		}
		map.put("thisMonth", listThisMonth);
		
		
		List<RecentProfileViewResponse> listEarlier = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : earlier){
			
			RecentProfileViewResponse earlierResp = new RecentProfileViewResponse();
			Long fpProductId = response.getProductId();
			// calling DMS for getting fp profile image path
			
			
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setUserId(response.getUserId());
			documentRequest.setUserType("user");
			documentRequest.setUserDocumentMappingId(1L);
			
			DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
			String imagePath = "";
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				List<Map<String, Object>> list = documentResponse.getDataList();
				if (!CommonUtils.isListNullOrEmpty(list)) {
					StorageDetailsResponse storageResponse = null;

					storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
					if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
					else
							imagePath="";
				}
			}
			
			// calling USER for getting fp details
			UserResponse userResponse = usersClient.getFPDashboardDetails(response.getUserId());
			
			FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
			
			earlierResp.setProfilePic(imagePath);
			earlierResp.setName(fundProviderDetailsRequest.getOrganizationName());
			earlierResp.setAddress(fundProviderDetailsRequest.getAddress());
			earlierResp.setApplicationId(response.getApplicationId());
			earlierResp.setProductId(response.getProductId());
			earlierResp.setUserId(response.getUserId());
			listEarlier.add(earlierResp);
			
		}
		map.put("earlier", listEarlier);
		
		
		detailResponse.setRecentProfileMap(map);
		return detailResponse;
	}

	@Override
	public RecentProfileViewDetailResponse getRecentViewDetailListByProdId(Long productId, Long userId)
			throws Exception {
		// TODO Auto-generated method stub
		NotificationRequest request = new NotificationRequest();
		request.setProductId(productId);
		NotificationResponse notificationResponse = null;
		
		try {
			notificationResponse =  notificationClient.getAllRecentViewNotificationByProdId(request);
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		Map<String, List<SysNotifyResponse>> a = notificationResponse.getRecentViewResponse();
		List<SysNotifyResponse> thisWeek = a.get("thisWeek");
		List<SysNotifyResponse> thisMonth = a.get("thisMonth");
		List<SysNotifyResponse> earlier = a.get("earlier");
		
		RecentProfileViewDetailResponse detailResponse = new RecentProfileViewDetailResponse();
		
		Map<String, List<RecentProfileViewResponse>> map = new HashMap<String, List<RecentProfileViewResponse>>();
		List<RecentProfileViewResponse> listMonthWeek = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : thisWeek){
			
			RecentProfileViewResponse thisWeekResp = new RecentProfileViewResponse();
			Long applicationId = response.getApplicationId();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
			// calling DMS for getting fp profile image path
			
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
			{
				
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
				
				if(corporateApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				{
					address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				{
					address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
					thisWeekResp.setName("NA");
				else
					thisWeekResp.setName(corporateApplicantDetail.getOrganisationName());
				
				
				//for get industry id
			

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse storageResponse = null;

						storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
						else
							imagePath=null;
					}
				}
				
				
				thisWeekResp.setProfilePic(imagePath);
				
				thisWeekResp.setAddress(address);
				thisWeekResp.setApplicationId(response.getApplicationId());
				thisWeekResp.setProductId(response.getProductId());
				thisWeekResp.setUserId(response.getUserId());
				listMonthWeek.add(thisWeekResp);
				
			}	
			else{

				Long fpProductId=response.getProductId();
				
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

				if(retailApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
				{
					address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
				{
					address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				String name="";
				
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
					name+="NA";
				else name+=retailApplicantDetail.getFirstName();
				

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse StorsgeResponse = null;

						StorsgeResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(StorsgeResponse.getFilePath()))
							imagePath = StorsgeResponse.getFilePath();
						else
							imagePath=null;
					}
				}


				thisWeekResp.setProfilePic(imagePath);
				thisWeekResp.setName(name);
				thisWeekResp.setAddress(address);
				thisWeekResp.setApplicationId(response.getApplicationId());
				thisWeekResp.setProductId(response.getProductId());
				thisWeekResp.setUserId(response.getUserId());
				listMonthWeek.add(thisWeekResp);
			}
			
			
		}
		map.put("thisWeek", listMonthWeek);
		
		
		List<RecentProfileViewResponse> listThisMonth = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : thisMonth){
			
			RecentProfileViewResponse thisMonthResp = new RecentProfileViewResponse();
			Long applicationId = response.getApplicationId();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
			// calling DMS for getting fp profile image path
			
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
			{
				
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
				
				if(corporateApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				{
					address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				{
					address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
					thisMonthResp.setName("NA");
				else
					thisMonthResp.setName(corporateApplicantDetail.getOrganisationName());
				
				
				//for get industry id
			

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse storageResponse = null;

						storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
						else
							imagePath=null;
					}
				}
				
				
				thisMonthResp.setProfilePic(imagePath);
				
				thisMonthResp.setAddress(address);
				thisMonthResp.setApplicationId(response.getApplicationId());
				thisMonthResp.setProductId(response.getProductId());
				thisMonthResp.setUserId(response.getUserId());
				listMonthWeek.add(thisMonthResp);
				
			}	
			else{

				Long fpProductId=response.getProductId();
				
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

				if(retailApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
				{
					address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
				{
					address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				String name="";
				
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
					name+="NA";
				else name+=retailApplicantDetail.getFirstName();
				

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse StorsgeResponse = null;

						StorsgeResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(StorsgeResponse.getFilePath()))
							imagePath = StorsgeResponse.getFilePath();
						else
							imagePath=null;
					}
				}


				thisMonthResp.setProfilePic(imagePath);
				thisMonthResp.setName(name);
				thisMonthResp.setAddress(address);
				thisMonthResp.setApplicationId(response.getApplicationId());
				thisMonthResp.setProductId(response.getProductId());
				thisMonthResp.setUserId(response.getUserId());
				listMonthWeek.add(thisMonthResp);
			}
			
			
		}
		map.put("thisMonth", listThisMonth);
		
		
		List<RecentProfileViewResponse> listEarlier = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : earlier){
			
			RecentProfileViewResponse earlierResp = new RecentProfileViewResponse();
			Long applicationId = response.getApplicationId();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
			// calling DMS for getting fp profile image path
			
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
			{
				
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
				
				if(corporateApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				{
					address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				{
					address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
					earlierResp.setName("NA");
				else
					earlierResp.setName(corporateApplicantDetail.getOrganisationName());
				
				
				//for get industry id
			

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse storageResponse = null;

						storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
						else
							imagePath=null;
					}
				}
				
				
				earlierResp.setProfilePic(imagePath);
				
				earlierResp.setAddress(address);
				earlierResp.setApplicationId(response.getApplicationId());
				earlierResp.setProductId(response.getProductId());
				earlierResp.setUserId(response.getUserId());
				listEarlier.add(earlierResp);
				
			}	
			else{

				Long fpProductId=response.getProductId();
				
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

				if(retailApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
				{
					address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
				{
					address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				String name="";
				
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
					name+="NA";
				else name+=retailApplicantDetail.getFirstName();
				

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse StorsgeResponse = null;

						StorsgeResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(StorsgeResponse.getFilePath()))
							imagePath = StorsgeResponse.getFilePath();
						else
							imagePath=null;
					}
				}


				earlierResp.setProfilePic(imagePath);
				earlierResp.setName(name);
				earlierResp.setAddress(address);
				
				earlierResp.setApplicationId(response.getApplicationId());
				earlierResp.setProductId(response.getProductId());
				earlierResp.setUserId(response.getUserId());
				listEarlier.add(earlierResp);
			}
			
			
		}
		map.put("earlier", listEarlier);
		
		
		detailResponse.setRecentProfileMap(map);
		return detailResponse;
	}

	@Override
	public RecentProfileViewDetailResponse getLatestRecentViewDetailListByAppId(Long applicationId, Long userId) throws DocumentException, IOException {
		NotificationRequest request = new NotificationRequest();
		request.setApplicationId(applicationId);
		NotificationResponse notificationResponse = null;
		
		try {
			notificationResponse =  notificationClient.getAllLatestRecentViewNotificationByAppId(request);
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	
		List<SysNotifyResponse> recentView = notificationResponse.getSysNotification();
		
		RecentProfileViewDetailResponse detailResponse = new RecentProfileViewDetailResponse();
		
		Map<String, List<RecentProfileViewResponse>> map = new HashMap<String, List<RecentProfileViewResponse>>();
		List<RecentProfileViewResponse> listRecentView = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : recentView){
			
			RecentProfileViewResponse thisWeekResp = new RecentProfileViewResponse();
			Long fpProductId = response.getProductId();
			// calling DMS for getting fp profile image path
			
			
			DocumentRequest documentRequest = new DocumentRequest();
			documentRequest.setUserId(response.getUserId());
			documentRequest.setUserType("user");
			documentRequest.setUserDocumentMappingId(1L);
			
			DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
			String imagePath = "";
			if (documentResponse != null && documentResponse.getStatus() == 200) {
				List<Map<String, Object>> list = documentResponse.getDataList();
				if (!CommonUtils.isListNullOrEmpty(list)) {
					StorageDetailsResponse storageResponse = null;

					storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0), StorageDetailsResponse.class);
					if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
					else
							imagePath="";
				}
			}
			
			// calling USER for getting fp details
			UserResponse userResponse = usersClient.getFPDashboardDetails(response.getUserId());
			if(userResponse !=null){
			FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(LinkedHashMap<String, Object>) userResponse.getData(), FundProviderDetailsRequest.class);
			
			thisWeekResp.setProfilePic(imagePath);
			thisWeekResp.setName(fundProviderDetailsRequest.getOrganizationName());
			thisWeekResp.setAddress(fundProviderDetailsRequest.getAddress());
			thisWeekResp.setApplicationId(response.getApplicationId());
			thisWeekResp.setProductId(response.getProductId());
			thisWeekResp.setUserId(response.getUserId());
			}
			listRecentView.add(thisWeekResp);
			
		}
		
		map.put("recentView", listRecentView);
		detailResponse.setRecentProfileMap(map);
		
		return detailResponse;
	}

	@Override
	public RecentProfileViewDetailResponse getLatestRecentViewDetailListByProdId(Long productId, Long userId) throws Exception {
		NotificationRequest request = new NotificationRequest();
		request.setProductId(productId);
		NotificationResponse notificationResponse = null;
		
		try {
			notificationResponse =  notificationClient.getAllLatestRecentViewNotificationByProdId(request);
		} catch (NotificationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		List<SysNotifyResponse> thisWeek = notificationResponse.getSysNotification();
		
		RecentProfileViewDetailResponse detailResponse = new RecentProfileViewDetailResponse();
		
		Map<String, List<RecentProfileViewResponse>> map = new HashMap<String, List<RecentProfileViewResponse>>();
		List<RecentProfileViewResponse> listMonthWeek = new ArrayList<RecentProfileViewResponse>();
		for(SysNotifyResponse response  : thisWeek){
			
			RecentProfileViewResponse thisWeekResp = new RecentProfileViewResponse();
			Long applicationId = response.getApplicationId();
			LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(applicationId);
			// calling DMS for getting fp profile image path
			
			if (CommonUtils.UserMainType.CORPORATE == CommonUtils.getUserMainType(loanApplicationMaster.getProductId()))
			{
				
				CorporateApplicantDetail corporateApplicantDetail = corporateApplicantDetailRepository.findOneByApplicationIdId(applicationId);
				
				if(corporateApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCityId()))
				{
					address+=CommonDocumentUtils.getCity(corporateApplicantDetail.getRegisteredCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredStateId()))
				{
					address+=CommonDocumentUtils.getState(corporateApplicantDetail.getRegisteredStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getRegisteredCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(corporateApplicantDetail.getRegisteredCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				
				if(CommonUtils.isObjectNullOrEmpty(corporateApplicantDetail.getOrganisationName()))
					thisWeekResp.setName("NA");
				else
					thisWeekResp.setName(corporateApplicantDetail.getOrganisationName());
				
				
				//for get industry id
			

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse storageResponse = null;

						storageResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(storageResponse.getFilePath()))
							imagePath = storageResponse.getFilePath();
						else
							imagePath=null;
					}
				}
				
				
				thisWeekResp.setProfilePic(imagePath);
				
				thisWeekResp.setAddress(address);
				thisWeekResp.setApplicationId(response.getApplicationId());
				thisWeekResp.setProductId(response.getProductId());
				thisWeekResp.setUserId(response.getUserId());
				listMonthWeek.add(thisWeekResp);
				
			}	
			else{

				Long fpProductId=response.getProductId();
				
				RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.findOneByApplicationIdId(applicationId);

				if(retailApplicantDetail == null)
					continue;
				
				// for get address city state country
				String address="";
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCityId()))
				{
					address+=CommonDocumentUtils.getCity(retailApplicantDetail.getPermanentCityId(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentStateId()))
				{
					address+=CommonDocumentUtils.getState(retailApplicantDetail.getPermanentStateId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA ,";
				}
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getPermanentCountryId()))
				{
					address+=CommonDocumentUtils.getCountry(retailApplicantDetail.getPermanentCountryId().longValue(), oneFormClient);
				}
				else
				{
					address+="NA";
				}
				
				
				String name="";
				
				if(CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getFirstName()))
					name+="NA";
				else name+=retailApplicantDetail.getFirstName();
				

				// calling DMS for getting fp profile image path
				
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationId);
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setUserDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				DocumentResponse documentResponse = dmsClient.listUserDocument(documentRequest);
				String imagePath = null;
				if (documentResponse != null && documentResponse.getStatus() == 200) {
					List<Map<String, Object>> list = documentResponse.getDataList();
					if (!CommonUtils.isListNullOrEmpty(list)) {
						StorageDetailsResponse StorsgeResponse = null;

						StorsgeResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
								StorageDetailsResponse.class);

						if(!CommonUtils.isObjectNullOrEmpty(StorsgeResponse.getFilePath()))
							imagePath = StorsgeResponse.getFilePath();
						else
							imagePath=null;
					}
				}


				thisWeekResp.setProfilePic(imagePath);
				thisWeekResp.setName(name);
				thisWeekResp.setAddress(address);
				thisWeekResp.setApplicationId(response.getApplicationId());
				thisWeekResp.setProductId(response.getProductId());
				thisWeekResp.setUserId(response.getUserId());
				listMonthWeek.add(thisWeekResp);
			}
			
			
		}
		map.put("recentView",listMonthWeek );
		detailResponse.setRecentProfileMap(map);
		
		return null;
	}

}
