package com.capitaworld.service.loans.service.serviceprovider.impl;

import java.util.ArrayList;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.model.DashboardProfileResponse;
import com.capitaworld.service.loans.model.FundProviderProposalDetails;
import com.capitaworld.service.loans.model.LoanApplicationDetailsForSp;
import com.capitaworld.service.loans.model.ProductDetailsForSp;
import com.capitaworld.service.loans.model.SpClientListing;
import com.capitaworld.service.loans.model.common.RecentProfileViewResponse;
import com.capitaworld.service.loans.service.common.DashboardService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.serviceprovider.ServiceProviderFlowService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.model.NotificationResponse;
import com.capitaworld.service.notification.model.SysNotifyResponse;
import com.capitaworld.service.oneform.client.CityByCityListIdClient;
import com.capitaworld.service.oneform.client.CountryByCountryListIdClient;
import com.capitaworld.service.oneform.client.StateListByStateListIdClient;
import com.capitaworld.service.oneform.enums.Denomination;
import com.capitaworld.service.oneform.enums.LoanType;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.FundProviderDetailsRequest;
import com.capitaworld.service.users.model.SpClientResponse;
import com.capitaworld.service.users.model.UserResponse;


@Service
@Transactional
public class ServiceProviderFlowServiceImpl implements ServiceProviderFlowService {

	@Autowired
	private Environment environmment;
	
	@Autowired
	LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private DMSClient dmsClient;
	
	@Autowired
	private DashboardService dashboardService;
	
	private static final String USERS_BASE_URL_KEY = "userURL";
	private static final String ONEFORM_BASE_URL_KEY = "oneForm";
	@Override
	public List<SpClientListing> spClientList(Long spId, String userTypeCode) throws Exception {
		UsersClient usersClient = new UsersClient(environmment.getRequiredProperty(USERS_BASE_URL_KEY));
		try {
			UserResponse userResponse = usersClient.getSpUserIdClientMappingList(spId, userTypeCode);
			List<Map<String, Object>> spClientResponseList = (List<Map<String, Object>>) userResponse.getData();
			List<SpClientListing> clientListings = new ArrayList<SpClientListing>();
			for (int i = 0; i < spClientResponseList.size(); i++) {
				SpClientResponse clientResponse = MultipleJSONObjectHelper.getObjectFromMap(spClientResponseList.get(i),
						SpClientResponse.class);
				SpClientListing spClientDetail = new SpClientListing();
				spClientDetail.setClientId(clientResponse.getClientId());
				spClientDetail.setClientName(clientResponse.getClientName());
				spClientDetail.setClientEmail(clientResponse.getClientEmail());
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCity())
						&& clientResponse.getClientCity() != 0) {
					CityByCityListIdClient cityListIdClient = new CityByCityListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> cityList = new ArrayList<>();
					cityList.add((long) clientResponse.getClientCity());
					OneFormResponse cityResponse = cityListIdClient.send(cityList);
					List<Map<String, Object>> cityResponseDatalist = (List<Map<String, Object>>) cityResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(cityResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientCity(masterResponse.getValue());
					} else {
						spClientDetail.setClientCity("NA");
					}
				} else {
					spClientDetail.setClientCity("NA");
				}
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientState())
						&& clientResponse.getClientState() != 0) {
					StateListByStateListIdClient stateListIdClient = new StateListByStateListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> stateList = new ArrayList<>();
					stateList.add((long) clientResponse.getClientState());
					OneFormResponse stateResponse = stateListIdClient.send(stateList);
					List<Map<String, Object>> stateResponseDatalist = (List<Map<String, Object>>) stateResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(stateResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientState(masterResponse.getValue());
					} else {
						spClientDetail.setClientState("NA");
					}
				} else {
					spClientDetail.setClientState("NA");
				}
				if (!CommonUtils.isObjectNullOrEmpty(clientResponse.getClientCountry())
						&& clientResponse.getClientCountry() != 0) {
					CountryByCountryListIdClient countryListIdClient = new CountryByCountryListIdClient(environmment.getRequiredProperty(ONEFORM_BASE_URL_KEY));
					List<Long> countryList = new ArrayList<>();
					countryList.add((long) clientResponse.getClientCountry());
					OneFormResponse countryResponse = countryListIdClient.send(countryList);
					List<Map<String, Object>> countryResponseDatalist = (List<Map<String, Object>>) countryResponse.getListData();
					MasterResponse masterResponse = MultipleJSONObjectHelper.getObjectFromMap(countryResponseDatalist.get(0), MasterResponse.class);
					if (!CommonUtils.isObjectNullOrEmpty(masterResponse)) {
						spClientDetail.setClientCountry(masterResponse.getValue());
					} else {
						spClientDetail.setClientCountry("NA");
					}
				} else {
					spClientDetail.setClientCountry("NA");
				}
				if (userTypeCode.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDSEEKER)) {
					List<LoanApplicationDetailsForSp> fsClientDetails = loanApplicationService.getLoanDetailsByUserIdList(clientResponse.getClientId());
					List<LoanApplicationDetailsForSp> fsApplicationDetails = new ArrayList<LoanApplicationDetailsForSp>();
					for (LoanApplicationDetailsForSp applicationDetailsForSp : fsClientDetails) {
						if(!CommonUtils.isObjectNullOrEmpty(applicationDetailsForSp.getProductId())){
							boolean applied = loanApplicationService.hasAlreadyApplied(clientResponse.getClientId(), applicationDetailsForSp.getId(),applicationDetailsForSp.getProductId());
							applicationDetailsForSp.setHasAlreadyApplied(applied);
							applicationDetailsForSp.setApplicationType(CommonUtils.getUserMainType(applicationDetailsForSp.getProductId()));
							applicationDetailsForSp.setProductName(LoanType.getById(applicationDetailsForSp.getProductId()).getValue());
							
							//code for getting recent viewer
							NotificationRequest notificationRequest = new NotificationRequest();
							notificationRequest.setApplicationId(applicationDetailsForSp.getId());
							notificationRequest.setClientRefId(clientResponse.getClientId().toString());
							NotificationResponse response = notificationClient.getAllLatestRecentViewNotificationByAppId(notificationRequest);
							List<SysNotifyResponse> sysNotification = response.getSysNotification();
							List<RecentProfileViewResponse> profileViewResponsesList = new ArrayList<RecentProfileViewResponse>();
							if(!CommonUtils.isObjectListNull(sysNotification)){
								for(int j = 0 ; j < sysNotification.size() && j < 4 ; j++){
									SysNotifyResponse sysNotifyResponse = sysNotification.get(j);
									RecentProfileViewResponse profileViewResponse = new RecentProfileViewResponse();
									usersClient = new UsersClient(environmment.getRequiredProperty(CommonUtils.USER_CLIENT_URL));
									UserResponse fpUserResponse = usersClient.getFPDashboardDetails(sysNotifyResponse.getUserId());
									if(!CommonUtils.isObjectListNull(fpUserResponse.getData())){
										FundProviderDetailsRequest fpBasicDetails = MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)fpUserResponse.getData(),
												FundProviderDetailsRequest.class);	
										profileViewResponse.setName(fpBasicDetails.getOrganizationName());		
									}
									profileViewResponse.setProductId(sysNotifyResponse.getProductId());
									profileViewResponse.setUserId(sysNotifyResponse.getUserId());
									//code to get fp image

									DocumentRequest documentRequest = new DocumentRequest();
									documentRequest.setUserId(sysNotifyResponse.getUserId());
									documentRequest.setUserType(DocumentAlias.UERT_TYPE_USER);
									documentRequest.setUserDocumentMappingId(DocumentAlias.FUND_PROVIDER_PROFIEL_PICTURE);
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
									profileViewResponse.setProfilePic(imagePath);
									profileViewResponsesList.add(profileViewResponse);
								}
							}
							applicationDetailsForSp.setRecentProfileViewList(profileViewResponsesList);
							
						}else{
							applicationDetailsForSp.setProductName("NA");
						}
						applicationDetailsForSp.setDenominationValue(!CommonUtils.isObjectNullOrEmpty(applicationDetailsForSp.getDenominationId()) ?  Denomination.getById(applicationDetailsForSp.getDenominationId()).getValue() : "NA");
						fsApplicationDetails.add(applicationDetailsForSp);
					}
					spClientDetail.setListData(fsApplicationDetails);
					clientListings.add(spClientDetail);

				} else if (userTypeCode.equals(com.capitaworld.service.users.utils.CommonUtils.USER_TYPECODE_FUNDPROVIDER)) {

					List<ProductDetailsForSp> fpClientDetails = productMasterService.getProductDetailsByUserIdList(clientResponse.getClientId());
					List<ProductDetailsForSp> fpProductsDetails = new ArrayList<ProductDetailsForSp>();
					for(ProductDetailsForSp productDetailsForSp : fpClientDetails){
						if(CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getName())){
							productDetailsForSp.setName(!CommonUtils.isObjectNullOrEmpty(productDetailsForSp.getProductId()) ? LoanType.getById(productDetailsForSp.getProductId()).getValue() : "NA");
						}
						//code for getting recent viewer
						NotificationRequest notificationRequest = new NotificationRequest();
						notificationRequest.setProductId(productDetailsForSp.getId());
						notificationRequest.setClientRefId(clientResponse.getClientId().toString());
						NotificationResponse response = notificationClient.getAllLatestRecentViewNotificationByProdId(notificationRequest);
						List<SysNotifyResponse> sysNotification = response.getSysNotification();
						List<RecentProfileViewResponse> profileViewResponsesList = new ArrayList<RecentProfileViewResponse>();
						if(!CommonUtils.isObjectListNull(sysNotification)){
							for(int j = 0 ; j < sysNotification.size() && j < 4 ; j++){
								SysNotifyResponse sysNotifyResponse = sysNotification.get(j);
								RecentProfileViewResponse profileViewResponse = new RecentProfileViewResponse();
								
								DashboardProfileResponse dashboardProfileResponse = dashboardService.getBasicProfileInfo(sysNotifyResponse.getApplicationId(), sysNotifyResponse.getUserId());
								profileViewResponse.setName(dashboardProfileResponse.getName());
								profileViewResponse.setApplicationId(sysNotifyResponse.getApplicationId());
								profileViewResponse.setUserId(clientResponse.getClientId());
								//code to get applicant image
								DocumentRequest documentRequest = new DocumentRequest();
								documentRequest.setApplicationId(sysNotifyResponse.getApplicationId());
								documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
								
								switch (productDetailsForSp.getProductId()){
					            case 1://WORKING CAPITAL
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.WORKING_CAPITAL_PROFIEL_PICTURE);
					            	break;
					            case 2://Term CAPITAL
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.TERM_LOAN_PROFIEL_PICTURE);
					            	break;
					            case 3://HOME LOAN
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.HOME_LOAN_PROFIEL_PICTURE);
					            	break;
					            case 7://PERSONAL LOAN
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.PERSONAL_LOAN_PROFIEL_PICTURE);
					            	break;
					            case 12://CAR_LOAN
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.CAR_LOAN_PROFIEL_PICTURE);
					            	break;
					            case 13://LOAN_AGAINST_PROPERTY
					            	documentRequest.setProductDocumentMappingId(DocumentAlias.LAP_LOAN_PROFIEL_PICTURE);
					            	break;
					            default:
					                return null;
								}
								// applicant image
								DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
								String imagePath = null;
								if (documentResponse != null && documentResponse.getStatus() == 200) {
									List<Map<String, Object>> list = documentResponse.getDataList();
									if (!CommonUtils.isListNullOrEmpty(list)) {
										StorageDetailsResponse StorsgeResponse = null;

										StorsgeResponse = MultipleJSONObjectHelper.getObjectFromMap(list.get(0),
												StorageDetailsResponse.class);

										if (!CommonUtils.isObjectNullOrEmpty(StorsgeResponse.getFilePath()))
											imagePath = StorsgeResponse.getFilePath();
										else
											imagePath = null;
									}
								}
								profileViewResponse.setProfilePic(imagePath);
								profileViewResponsesList.add(profileViewResponse);
							}
						}
						productDetailsForSp.setRecentProfileViewList(profileViewResponsesList);
						fpProductsDetails.add(productDetailsForSp);
					}
					spClientDetail.setListData(fpProductsDetails);
					clientListings.add(spClientDetail);
				}
			}
			return clientListings;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception("Error while getting client list.");
		}

	}
	
	@Override
	public JSONObject spClientCount(Long spId) throws Exception {
		UsersClient usersClient = new UsersClient(environmment.getRequiredProperty(USERS_BASE_URL_KEY));
		try {
			UserResponse response = usersClient.getSPClientCount(spId);
			if(!CommonUtils.isObjectNullOrEmpty(response.getData())){
				return MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)response.getData(), JSONObject.class);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new Exception("Error while getting SP client count.");
		}
		return null;
	}	
}
