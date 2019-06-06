package com.capitaworld.service.loans.service.networkpartner.impl;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.itr.api.model.ITRConnectionResponse;
import com.capitaworld.itr.client.ITRClient;
import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.loans.config.FPAsyncComponent;
import com.capitaworld.service.loans.domain.fundprovider.FpNpMapping;
import com.capitaworld.service.loans.domain.fundprovider.ProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.*;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.domain.fundseeker.corporate.DirectorBackgroundDetail;
import com.capitaworld.service.loans.domain.fundseeker.retail.RetailApplicantDetail;
import com.capitaworld.service.loans.model.FpNpMappingRequest;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;
import com.capitaworld.service.loans.repository.fundprovider.FpNpMappingRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.ApplicationStatusAuditRepository;
import com.capitaworld.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.ApplicationProposalMappingRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.DirectorBackgroundDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.repository.fundseeker.retail.RetailApplicantDetailRepository;
import com.capitaworld.service.loans.repository.sanction.LoanSanctionRepository;
import com.capitaworld.service.loans.service.fundprovider.FpNpMappingService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.ApplicationStatus;
import com.capitaworld.service.loans.utils.CommonUtils.BusinessType;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.McaResponse;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

@Service
@Transactional
public class NetworkPartnerServiceImpl implements NetworkPartnerService {

	private static final Logger logger = LoggerFactory.getLogger(NetworkPartnerServiceImpl.class);
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	@Autowired
	private ApplicationProposalMappingRepository  applicationProposalMappingRepository;

	@Autowired
	private CorporateApplicantDetailRepository corpApplicantRepository;

	@Autowired
	private RetailApplicantDetailRepository retailApplicantDetailRepository;
	
	@Autowired
	private ApplicationStatusAuditRepository appStatusRepository;

	@Autowired
	private LoanSanctionRepository loanSanctionRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private DMSClient dmsClient;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private OneFormClient  oneFormClient;

	@Autowired
	private FpNpMappingService fpNpMappingService;

	@Autowired
	private FpNpMappingRepository fpNpMappingRepository;

	@Autowired
	private ITRClient itrClient;

	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private DirectorBackgroundDetailsRepository directorBackgroundDetailsRepository;
	
	@Autowired
	private LoanApplicationService loanApplicationService;

	@Autowired
	private McaClient mcaClient;
	
	@Autowired
	private FPAsyncComponent fpAsyncComponent;

	@Autowired
	private IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;

	private static String isPaymentBypass="cw.is_payment_bypass";

	private static final String LITERAL_LOCKED = "Locked";
	private static final String LITERAL_UNLOCKED = "Unlocked";

	private static final String ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS = "error while fetching network partner details : ";
	private static final String ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY = "error while fetching details from oneform client for city/state/country : ";
	private static final String ERROR_WHILE_GETTING_PROFILE_IMAGE = "error while getting profile image : ";
	private static final String ERROR_WHILE_FP_BRANCH_DETAILS = "error while FP branch details : ";
	private static final String ERROR_WHILE_FETCHING_FP_DETAILS = "error while fetching FP details : ";

	private static final String MSG_EXIT_FROM_SET_FP_MAKER = "exit from setFPMaker()";

	@Override
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request,Long npOrgId,Long userId) {
		logger.info("entry in getListOfProposals()");
		List<LoanApplicationMaster> applicationMastersList;
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			if(!CommonUtils.isObjectListNull(npOrgId) && npOrgId != CommonUtils.NP_NHBS){
				applicationMastersList = getApplicationListToAssignedCheckerFromBoFp(userId,CommonUtils.ApplicationStatus.OPEN,true,request.getPageIndex(),request.getSize());
			}else if(!CommonUtils.isObjectListNull(npOrgId)){
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,CommonUtils.PaymentStatus.SUCCESS);
			}else{
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),CommonUtils.PaymentStatus.SUCCESS);
			}
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == request.getUserRoleId()){
            if(!CommonUtils.isObjectListNull(npOrgId)){
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),npOrgId);
            }else {
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatusForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId());
            }
            if(applicationMastersList != null && !applicationMastersList.isEmpty()) {
				applicationMastersList.sort(Comparator.comparing(LoanApplicationMaster::getModifiedDate));
			}
		}else{
			applicationMastersList = null;
		}

		List<NhbsApplicationsResponse> nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
		if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
			for (LoanApplicationMaster loanApplicationMaster : applicationMastersList) {

				NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
				nhbsApplicationsResponse.setUserId(loanApplicationMaster.getUserId());
				nhbsApplicationsResponse.setApplicationId(loanApplicationMaster.getId());
				nhbsApplicationsResponse.setApplicationType(loanApplicationMaster.getProductId());
				if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){

					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(loanApplicationMaster.getNpUserId());
					try {
						UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
						NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
								NetworkPartnerDetailsRequest.class);
						nhbsApplicationsResponse.setMakerName(networkPartnerDetailsRequest.getFirstName() + " " + (networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName()));
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS,e);
					}
					
				}
				CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if(applicantDetail != null){
					nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());	
					try {
						// Setting City Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId())) {
							nhbsApplicationsResponse.setCity(
									CommonDocumentUtils.getCity(applicantDetail.getRegisteredCityId(), oneFormClient));
						}

						// Setting State Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId())) {
							nhbsApplicationsResponse.setState(CommonDocumentUtils
									.getState(applicantDetail.getRegisteredStateId().longValue(), oneFormClient));
						}

						// Country State Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId())) {
							nhbsApplicationsResponse.setCountry(CommonDocumentUtils
									.getCountry(applicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
						}
					} catch (Exception e) {
						logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
					}
				}
				
				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(loanApplicationMaster.getId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
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
					nhbsApplicationsResponse.setClientProfilePic(imagePath);
					
				} catch (DocumentException | IOException e) {
					logger.error(ERROR_WHILE_GETTING_PROFILE_IMAGE,e);
				}
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){

					/*List<Map<String, Object>> receivedPaymentList = new ArrayList<>();
					List<Long> receivedAppIdList = new ArrayList<>();
					if(npOrgId == CommonUtils.NP_NHBS){
						PaymentTypeRequest paymentTypeRequest = new PaymentTypeRequest();
						paymentTypeRequest.setListType(com.capitaworld.service.gateway.utils.CommonUtils.PAYMENT_PENDING_LIST);
						try {
							GatewayResponse gatewayResponse = gatewayClient.getPaymentList(paymentTypeRequest);
							if(!CommonUtils.isObjectNullOrEmpty(gatewayResponse.getListData())){
								receivedPaymentList = (List<Map<String, Object>>) gatewayResponse.getListData();
								for (int i = 0; i < receivedPaymentList.size(); i++) {
									PaymentTypeRequest paymentTypeRequest1 = MultipleJSONObjectHelper.getObjectFromMap(receivedPaymentList.get(i),PaymentTypeRequest.class);
									receivedAppIdList.add(paymentTypeRequest1.getApplicationId());
								}
							}
						}catch (Exception e){
							logger.error("error while calling gateway client : ",e);
						}
					}*/
					//logger.info("appId->"+loanApplicationMaster.getId());
					//if((npOrgId != CommonUtils.NP_NHBS) || (!CommonUtils.isObjectListNull(receivedAppIdList) && receivedAppIdList.contains(loanApplicationMaster.getId()))){
					    //logger.info("received list->"+receivedAppIdList.toString());
						nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
						try{
							UserResponse userResponse = usersClient.checkUserUnderSp(loanApplicationMaster.getUserId());
							if(!CommonUtils.isObjectNullOrEmpty(userResponse) && userResponse.getStatus() == 200 && (boolean)userResponse.getData()){
								nhbsApplicationsResponse.setClientSource("SP");
							}else{
								nhbsApplicationsResponse.setClientSource("Direct");
							}
						}catch (Exception e) {
							logger.error("error while calling users clients while calling checkUserUnderSp()",e);
						}
						nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
						/*if(npOrgId != CommonUtils.NP_NHBS){
							nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
						}else{
							if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getTypeOfPayment()) && loanApplicationMaster.getTypeOfPayment().equals(CommonUtils.PaymentMode.ONLINE)){
								GatewayRequest gatewayRequest = getPaymentStatuOfApplication(loanApplicationMaster.getId());
								if(!CommonUtils.isObjectNullOrEmpty(gatewayRequest)){
									if(gatewayRequest.getStatus().equals(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS)){
										nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
									}
								}
							}else{
								nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
							}
						}*/
					//}
				}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == request.getUserRoleId()){
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpAssigneeId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(loanApplicationMaster.getNpAssigneeId());
						try {
							UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
							NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									NetworkPartnerDetailsRequest.class);
							nhbsApplicationsResponse.setAssigneeName(networkPartnerDetailsRequest.getFirstName() + " " + (networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS,e);
						}
					}
					nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
				}
			}
		}/*else{
			nhbsApplicationsResponseList = null;
		}*/
		logger.info("exit form getListOfProposals()");
		return nhbsApplicationsResponseList;
		
	}

	@Override
	public List<NhbsApplicationsResponse> getListOfAssignedProposals(NhbsApplicationRequest request) {
		logger.info("entry in getListOfAssignedProposals()");
		List<ApplicationProposalMapping> applicationMastersList;
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			applicationMastersList = applicationProposalMappingRepository.getAssignedProposalsByAssigneeIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED, request.getUserId());
			applicationMastersList.sort(Comparator.comparing(ApplicationProposalMapping::getModifiedDate));
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
			applicationMastersList = applicationProposalMappingRepository.getAssignedProposalsByNpUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getUserId());
		}else{
			applicationMastersList = null;
		}

		List<NhbsApplicationsResponse> nhbsApplicationsResponseList =null;
		if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
			nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
			for (ApplicationProposalMapping applicationProposalMapping : applicationMastersList) {
				NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
				nhbsApplicationsResponse.setApplicationType(applicationProposalMapping.getProductId());
				nhbsApplicationsResponse.setUserId(applicationProposalMapping.getUserId());
				nhbsApplicationsResponse.setApplicationId(applicationProposalMapping.getApplicationId());
				nhbsApplicationsResponse.setProposalId(applicationProposalMapping.getProposalId());
				if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getDdrStatusId())){
					nhbsApplicationsResponse.setDdrStatus(CommonUtils.getDdrStatusString(applicationProposalMapping.getDdrStatusId().intValue()));
					nhbsApplicationsResponse.setDdrStatusId(applicationProposalMapping.getDdrStatusId().intValue());
				}else{
					nhbsApplicationsResponse.setDdrStatus("NA");
				}
				 
				CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(applicationProposalMapping.getUserId(), applicationProposalMapping.getApplicationId());
				if(applicantDetail != null){
					nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());
					try {
						// Setting City Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId())) {
							nhbsApplicationsResponse.setCity(
									CommonDocumentUtils.getCity(applicantDetail.getRegisteredCityId(), oneFormClient));
						}

						// Setting State Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId())) {
							nhbsApplicationsResponse.setState(CommonDocumentUtils
									.getState(applicantDetail.getRegisteredStateId().longValue(), oneFormClient));
						}

						// Country State Value
						if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId())) {
							nhbsApplicationsResponse.setCountry(CommonDocumentUtils
									.getCountry(applicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
						}
					} catch (Exception e) {
						logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
					}
				}				
				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationProposalMapping.getApplicationId());
				documentRequest.setProposalMappingId(applicationProposalMapping.getProposalId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(applicationProposalMapping.getProductId()));
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
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
					nhbsApplicationsResponse.setClientProfilePic(imagePath);
					
				} catch (DocumentException | IOException e) {
					logger.error(ERROR_WHILE_GETTING_PROFILE_IMAGE,e);
				}
				if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalLocked())){
					nhbsApplicationsResponse.setOneFormFilled(applicationProposalMapping.getIsFinalLocked() ? LITERAL_LOCKED : LITERAL_UNLOCKED);
				}else{
					nhbsApplicationsResponse.setOneFormFilled(LITERAL_UNLOCKED);
				}
				
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByAndProposalIdNpUserIdBasedOnStatus(applicationProposalMapping.getApplicationId(), applicationProposalMapping.getProposalId(),CommonUtils.ApplicationStatus.OPEN, request.getUserId());
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setApplicationDate(applicationStatusAuditList.get(0).getModifiedDate());
					}else{
						nhbsApplicationsResponse.setApplicationDate(applicationProposalMapping.getCreatedDate());
					}
                    nhbsApplicationsResponse.setIsPaymentDone("Received");
					if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getNpAssigneeId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(applicationProposalMapping.getNpAssigneeId());
						try {
							UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
							NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									NetworkPartnerDetailsRequest.class);
							nhbsApplicationsResponse.setAssigneeName(networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS,e);
						}
						
					}
				}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByAssigneeIdBasedOnStatus(applicationProposalMapping.getApplicationId(),applicationProposalMapping.getProposalId(), CommonUtils.ApplicationStatus.OPEN, request.getUserId());
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setApplicationDate(applicationStatusAuditList.get(0).getModifiedDate());
					}else{
						nhbsApplicationsResponse.setApplicationDate(applicationProposalMapping.getCreatedDate());
					}
					if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getNpUserId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(applicationProposalMapping.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
							NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									NetworkPartnerDetailsRequest.class);
							nhbsApplicationsResponse.setMakerName(networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS,e);
						}
						
					}
				}
				
				nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
			}
			if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
				nhbsApplicationsResponseList.sort(Comparator.comparing(NhbsApplicationsResponse::getApplicationDate));	
			}	
		}
			
		logger.info("exit from getListOfAssignedProposals()");
		return nhbsApplicationsResponseList; 				
	}

	@Override
	public boolean setMaker(NhbsApplicationRequest request) {
		logger.info("entry in setMaker()");
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setNpUserId(request.getAssignedUserId());
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			logger.info("exit from setMaker()");
			return true;
		}
		logger.info("exit from setMaker()");
		return false;
	}

	@Override
	public JSONObject getNhbsProposalCount(NhbsApplicationRequest nhbsApplicationRequest,Long npOrgId) {
		logger.info("entry in getNhbsProposalCount()");
		JSONObject countObj = new JSONObject();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == nhbsApplicationRequest.getUserRoleId()){
			int allotedPropsalCount = loanApplicationRepository.getCountOfAssignedProposalsByNpUserId(nhbsApplicationRequest.getUserId());
			countObj.put("allotedPropsalCount", allotedPropsalCount);
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == nhbsApplicationRequest.getUserRoleId()){
            List<LoanApplicationMaster> applicationMastersList ;
			int newPropsalCount = 0;
			if(!CommonUtils.isObjectListNull(npOrgId) && npOrgId != CommonUtils.NP_NHBS){
				applicationMastersList = getApplicationListToAssignedCheckerFromBoFp(nhbsApplicationRequest.getUserId(),CommonUtils.ApplicationStatus.OPEN,false,0,0);
				if(!CommonUtils.isObjectListNull(applicationMastersList))
					newPropsalCount = applicationMastersList.size();
			}else{
				if(!CommonUtils.isObjectListNull(npOrgId)){
					applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgId(CommonUtils.ApplicationStatus.OPEN,npOrgId,CommonUtils.PaymentStatus.SUCCESS);
				}else{
					applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatus(CommonUtils.ApplicationStatus.OPEN,CommonUtils.PaymentStatus.SUCCESS);
				}
				/*List<Map<String, Object>> receivedPaymentList = new ArrayList<>();
				List<Long> receivedAppIdList = new ArrayList<>();
				PaymentTypeRequest paymentTypeRequest = new PaymentTypeRequest();
				paymentTypeRequest.setListType(com.capitaworld.service.gateway.utils.CommonUtils.PAYMENT_PENDING_LIST);
				try {
					GatewayResponse gatewayResponse = gatewayClient.getPaymentList(paymentTypeRequest);
					if(!CommonUtils.isObjectNullOrEmpty(gatewayResponse.getListData())){
						receivedPaymentList = (List<Map<String, Object>>) gatewayResponse.getListData();
						for (int i = 0; i < receivedPaymentList.size(); i++) {
							PaymentTypeRequest paymentTypeRequest1 = MultipleJSONObjectHelper.getObjectFromMap(receivedPaymentList.get(i),PaymentTypeRequest.class);
							receivedAppIdList.add(paymentTypeRequest1.getApplicationId());
						}
					}
				}catch (Exception e){
					logger.error("error while calling gateway client : ",e);
				}*/
				if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
					newPropsalCount = applicationMastersList.size();
					/*for (LoanApplicationMaster loanApplicationMaster : applicationMastersList) {
						if(!CommonUtils.isObjectListNull(receivedAppIdList) && receivedAppIdList.contains(loanApplicationMaster.getId())){
							if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getTypeOfPayment()) && loanApplicationMaster.getTypeOfPayment().equals(CommonUtils.PaymentMode.ONLINE)){
								GatewayRequest gatewayRequest = getPaymentStatuOfApplication(loanApplicationMaster.getId());
								if(!CommonUtils.isObjectNullOrEmpty(gatewayRequest)){
									if(gatewayRequest.getStatus().equals(com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS)){
										newPropsalCount++;
									}
								}
							}else{
								newPropsalCount++;
							}
						}
					}*/
				}
			}

			//int newPropsalCount = loanApplicationRepository.getCountOfProposalsByApplicationStatus(CommonUtils.ApplicationStatus.OPEN);
            int assignedPropsalCount = loanApplicationRepository.getCountOfAssignedProposalsByAssigneeId(CommonUtils.ApplicationStatus.ASSIGNED, nhbsApplicationRequest.getUserId());
			countObj.put("newProposals", newPropsalCount);
			countObj.put("assignedProposals", assignedPropsalCount);
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == nhbsApplicationRequest.getUserRoleId()){
            int ddrSubmittedToApproverCount = 0;
            int ddrApprovedCount = 0;
            int ddrRevertedCount = 0;
		    if(!CommonUtils.isObjectListNull(npOrgId)){
                ddrSubmittedToApproverCount = loanApplicationRepository.getCountOfProposalsByDdrStatusAndNpOrgId(CommonUtils.DdrStatus.SUBMITTED_TO_APPROVER,npOrgId);
                ddrApprovedCount = loanApplicationRepository.getCountOfProposalsByDdrStatusAndNpOrgId(CommonUtils.DdrStatus.APPROVED,npOrgId);
                ddrRevertedCount = loanApplicationRepository.getCountOfProposalsByDdrStatusAndNpOrgId(CommonUtils.DdrStatus.REVERTED,npOrgId);
            }else{
                ddrSubmittedToApproverCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.SUBMITTED_TO_APPROVER);
                ddrApprovedCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.APPROVED);
                ddrRevertedCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.REVERTED);
            }
			countObj.put("ddrSubmittedToApproverCount", ddrSubmittedToApproverCount);
			countObj.put("ddrApprovedCount", ddrApprovedCount);
			countObj.put("ddrRevertedCount", ddrRevertedCount);
		}
		logger.info("exit from getNhbsProposalCount()");
		return countObj;
	}

	/*@Override
	public GatewayRequest getPaymentStatuOfApplication(Long applicationId) {
		logger.info("entry in getPaymentStatuOfApplication()");
		GatewayRequest gatewayRequest = new GatewayRequest();
		gatewayRequest.setApplicationId(applicationId);
		try {
			GatewayRequest paymentStatus = gatewayClient.getPaymentStatus(gatewayRequest);
			logger.info("exit from getPaymentStatuOfApplication()");
			return paymentStatus;
		} catch (GatewayException e) {
			logger.error("error while calling gateway client for payment status : ",e);
		}
		return null;
	}*/

	@Override
	public boolean sendSMSNotificationWhenCheckerAssignMaker(Long applicationId, Long assignedUserId) {
		
		try {

			logger.info("Sending SMS notification to FS when Checker Assign Maker=========>");
		
			
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(assignedUserId);
			
			UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
			NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
						NetworkPartnerDetailsRequest.class);
			
			LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(applicationId);
			UserResponse response = usersClient.getEmailMobile(applicationMaster.getUserId());
			UsersRequest applicantRequest = MultipleJSONObjectHelper.getObjectFromMap(
					(Map<String, Object>) response.getData(), UsersRequest.class);
			
			
			String mobile = applicantRequest.getMobile();

			String[] to = { 91 + mobile };
			NotificationRequest notificationRequest = new NotificationRequest();
			notificationRequest.setClientRefId(assignedUserId.toString());
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);

			notification.setTemplateId(NotificationAlias.SMS_CHECKER_ASSIGNED_MAKER);
			notification.setTo(to);
			notification.setType(NotificationType.SMS);
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("maker",Title.getById(networkPartnerDetailsRequest.getTitleId()) +" "+ networkPartnerDetailsRequest.getFirstName() + " " + (networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName()));
			parameters.put("url", "https://bit.ly/2IGwvBF");
			
			notification.setParameters(parameters);
			notificationRequest.addNotification(notification);
//			notificationRequest.addNotification(notification);

			notificationClient.send(notificationRequest);

			logger.info("End SMS notification to FS when Checker Assign Maker=====>");

			return true;
		} catch (Exception e) {
			logger.error("Error while sending SMS =====>",e);
		}

		
		return false;
	}

	@Override
	public List<LoanApplicationMaster> getApplicationListToAssignedCheckerFromBoFp(Long userId,Long appStatusId,Boolean forPagination,int pageIndex,int size) {
		logger.info("entry in getApplicationListToAssignedCheckerFromBoFp()");
		List<FpNpMapping> fpNpMappingList ;
		if(forPagination){
			fpNpMappingList = fpNpMappingRepository.listOfNpCheckerAssignedByBoForPagination(new PageRequest(pageIndex,size),userId);
		}else{
			fpNpMappingList = fpNpMappingRepository.listOfNpCheckerAssignedByBo(userId);
		}

		List<LoanApplicationMaster> loanApplicationMasterList = new ArrayList<LoanApplicationMaster>();
		if(!CommonUtils.isObjectListNull(fpNpMappingList)){
			for (FpNpMapping fpNpMapping:fpNpMappingList) {
				LoanApplicationMaster loanApplicationMaster = loanApplicationRepository.findOne(fpNpMapping.getApplicationId());
				if(loanApplicationMaster.getApplicationStatusMaster().getId() == appStatusId)
					loanApplicationMasterList.add(loanApplicationMaster);
			}
			logger.info("exit from getApplicationListToAssignedCheckerFromBoFp()");
			return loanApplicationMasterList;
		}
		logger.info("exit from getApplicationListToAssignedCheckerFromBoFp()");
		return Collections.emptyList();
	}

	@Override
	public String getCheckerName(FpNpMappingRequest fpNpMappingRequest) {
		logger.info("entry in getCheckerName()");
		FpNpMapping fpNpMapping = fpNpMappingRepository.getNpCheckerUserIdBasedOnAppIdAndProdId(fpNpMappingRequest.getApplicationId(),fpNpMappingRequest.getFpProductId());
		String checkerName="";
		if(!CommonUtils.isObjectListNull(fpNpMapping)){
			UsersRequest usersRequest = new UsersRequest();
			usersRequest.setId(fpNpMapping.getNpUserId());
			try {
				UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);
				NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
						NetworkPartnerDetailsRequest.class);
				checkerName = networkPartnerDetailsRequest.getFirstName() + " " + (networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName());
			} catch (Exception e) {
				logger.error(ERROR_WHILE_FETCHING_NETWORK_PARTNER_DETAILS,e);
			}
		}
		logger.info("exit from getCheckerName()");
		return checkerName;
	}


	@Override
	public List<NhbsApplicationsResponse> getListOfProposalsFP(NhbsApplicationRequest request,Long npOrgId,Long userId) {
		logger.info("entry in getListOfProposalsFP()");
		Long branchId = null;
		String mcaCompanyId = null;
		McaResponse mcaResponse;
		UsersRequest usersRequestForBranch = new UsersRequest();
		usersRequestForBranch.setId(userId);
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequestForBranch);
			BranchBasicDetailsRequest branchBasicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error(ERROR_WHILE_FP_BRANCH_DETAILS,e);
		}
		List<BigInteger> applicationIdList ;
		List<BigInteger> proposalIdList = new ArrayList<BigInteger>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId()){
			//List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchId(branchId);
			if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.OPEN){
				if(environment.getRequiredProperty(isPaymentBypass).equals("true")) {
					proposalIdList = applicationProposalMappingRepository.getFPProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,CommonUtils.PaymentStatus.BYPASS,branchId);
				}
				else {
					proposalIdList = applicationProposalMappingRepository.getFPProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,CommonUtils.PaymentStatus.SUCCESS,branchId);
				}
			}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED){
				proposalIdList = applicationProposalMappingRepository.getFPAssignedTabPropsByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED,CommonUtils.ApplicationStatus.REVERTED,CommonUtils.ApplicationStatus.SUBMITTED,userId,branchId);
			}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
				proposalIdList = applicationProposalMappingRepository.getFPAssignedProposalsByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),userId,branchId);
			}else{
				proposalIdList = applicationProposalMappingRepository.getFPProposalsWithOthersForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED,userId,branchId);
			}
			//applicationMastersList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
		}else{
			proposalIdList = null;
		}

		List<NhbsApplicationsResponse> nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
		if(!CommonUtils.isListNullOrEmpty(proposalIdList)){
			for (BigInteger proposalId : proposalIdList) {
				ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalId.longValue());
				Long fpProductId = proposalDetailsRepository.getFpProductIdByApplicationIdAndProposalId(applicationProposalMapping.getApplicationId(), proposalId.longValue());
				NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
				nhbsApplicationsResponse.setUserId(applicationProposalMapping.getUserId());
				nhbsApplicationsResponse.setProposalId(applicationProposalMapping.getProposalId());
				nhbsApplicationsResponse.setApplicationId(applicationProposalMapping.getApplicationId());
				nhbsApplicationsResponse.setApplicationType(applicationProposalMapping.getProductId());
				nhbsApplicationsResponse.setBusinessTypeId(applicationProposalMapping.getBusinessTypeId());
				nhbsApplicationsResponse.setApplicationCode(applicationProposalMapping.getApplicationCode());
				nhbsApplicationsResponse.setFpProductId(fpProductId);
				nhbsApplicationsResponse.setIsFinalLocked(applicationProposalMapping.getIsFinalLocked());

				if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getNpUserId())){
					UsersRequest usersRequest = new UsersRequest();
					usersRequest.setId(applicationProposalMapping.getNpUserId());
					try {
						UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
								FundProviderDetailsRequest.class);
						nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
						
					} catch (Exception e) {
						logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
					}
				}else {
					nhbsApplicationsResponse.setCheckerName("NA");
				}
				
				if(CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(applicationProposalMapping.getBusinessTypeId())) {//MEANS GET DIRECTOR NAME
					
					DirectorBackgroundDetail directorBackgroundDetail = directorBackgroundDetailsRepository.getByAppIdAndIsMainDirector(applicationProposalMapping.getApplicationId());
					if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail)) {
					    nhbsApplicationsResponse.setClientId(directorBackgroundDetail.getId());
						nhbsApplicationsResponse.setClientName(directorBackgroundDetail.getDirectorsName());
						nhbsApplicationsResponse.setCity(directorBackgroundDetail.getCity());
						try {
							if(!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getStateCode())) {
								ITRConnectionResponse itrRes = itrClient.getOneFormStateIdFromITRStateId(Long.valueOf(directorBackgroundDetail.getStateCode()));
								if(!CommonUtils.isObjectNullOrEmpty(itrRes) && !CommonUtils.isObjectNullOrEmpty(itrRes.getData())) {
									Integer stateId = (Integer)itrRes.getData();
									nhbsApplicationsResponse.setState(CommonDocumentUtils
											.getState(stateId.longValue(), oneFormClient));
								}	
							}
						} catch (Exception e) {
							logger.error(CommonUtils.EXCEPTION,e);
						}
						if (!CommonUtils.isObjectNullOrEmpty(directorBackgroundDetail.getCountryId())) {
							try {
								nhbsApplicationsResponse.setCountry(CommonDocumentUtils.getCountry(Long.valueOf(directorBackgroundDetail.getCountryId()), oneFormClient));	
							} catch (Exception e) {
								logger.error(CommonUtils.EXCEPTION,e);
							}
							
						}
					}
					
				} else if(CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(applicationProposalMapping.getBusinessTypeId())){
					CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(applicationProposalMapping.getUserId(), applicationProposalMapping.getApplicationId());
					if(applicantDetail != null){
						nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());
						try {
							// Setting City Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId())) {
								nhbsApplicationsResponse.setCity(
										CommonDocumentUtils.getCity(applicantDetail.getRegisteredCityId(), oneFormClient));
							}

							// Setting State Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId())) {
								nhbsApplicationsResponse.setState(CommonDocumentUtils
										.getState(applicantDetail.getRegisteredStateId().longValue(), oneFormClient));
							}

							// Country State Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId())) {
								nhbsApplicationsResponse.setCountry(CommonDocumentUtils
										.getCountry(applicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
							}
						} catch (Exception e) {
							logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
						}
					}	
				} else if(CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(applicationProposalMapping.getBusinessTypeId()) || BusinessType.RETAIL_HOME_LOAN.getId().equals(applicationProposalMapping.getBusinessTypeId())){
					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.getByApplicationAndUserId(applicationProposalMapping.getUserId(), applicationProposalMapping.getApplicationId());
					if(retailApplicantDetail != null){
						try {
							String firstName = retailApplicantDetail.getFirstName();
							String lastName =retailApplicantDetail.getLastName();
							String middleName =retailApplicantDetail.getMiddleName();

							String fullName = firstName==null?"":firstName;
							fullName += middleName==null?"":" "+ middleName;
							fullName += lastName==null?"":" "+lastName;

							nhbsApplicationsResponse.setClientName(fullName);
							// Setting City Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCity())) {
								nhbsApplicationsResponse.setCity(
										CommonDocumentUtils.getCity(retailApplicantDetail.getAddressCity(), oneFormClient));
							}

							// Setting State Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressState())) {
								nhbsApplicationsResponse.setState(CommonDocumentUtils
										.getState(retailApplicantDetail.getAddressState().longValue(), oneFormClient));
							}

							// Country State Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCountry())) {
								nhbsApplicationsResponse.setCountry(CommonDocumentUtils
										.getCountry(retailApplicantDetail.getAddressCountry().longValue(), oneFormClient));
							}
						} catch (Exception e) {
							logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
						}
					}
				}

				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(applicationProposalMapping.getApplicationId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(applicationProposalMapping.getProductId()));
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
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
					nhbsApplicationsResponse.setClientProfilePic(imagePath);
				} catch (DocumentException | IOException e) {
					logger.error(ERROR_WHILE_GETTING_PROFILE_IMAGE,e);
				}

				nhbsApplicationsResponse.setApplicationDate(applicationProposalMapping.getCreatedDate());

				if (!CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId().equals(applicationProposalMapping.getBusinessTypeId())) {
					try {
						mcaCompanyId = loanApplicationService.getMCACompanyIdById(applicationProposalMapping.getApplicationId());
						if (mcaCompanyId != null) {
							mcaResponse = mcaClient.mcaStatusCheck(applicationProposalMapping.getApplicationId().toString(), mcaCompanyId);
							logger.info("MCA Response" + mcaResponse);
							if ("true".equalsIgnoreCase(String.valueOf(mcaResponse.getData()))) {
								nhbsApplicationsResponse.setMcaStatus(CommonUtils.COMPLETED);
							} else {
								nhbsApplicationsResponse.setMcaStatus(CommonUtils.IN_PROGRESS);
							}
						} else {
							nhbsApplicationsResponse.setMcaStatus(CommonUtils.NA);
						}
					} catch (Exception e) {
						logger.error("error while getting MCA Status : ",e);
					}
				}
				
				if(applicationProposalMapping.getApplicationStatusMaster().getId()>=CommonUtils.ApplicationStatus.ASSIGNED){
					if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getDdrStatusId())){
						nhbsApplicationsResponse.setDdrStatus(CommonUtils.getDdrStatusString(applicationProposalMapping.getDdrStatusId().intValue()));
						nhbsApplicationsResponse.setDdrStatusId(applicationProposalMapping.getDdrStatusId().intValue());
					}else{
						nhbsApplicationsResponse.setDdrStatus("NA");
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(applicationProposalMapping.getApplicationId(),applicationProposalMapping.getProposalId(), CommonUtils.ApplicationStatus.OPEN);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setProposalTakenDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
					List<ApplicationStatusAudit> applicationStatusAuditListForAssignedToCheckerDate = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(applicationProposalMapping.getApplicationId(),applicationProposalMapping.getProposalId(), CommonUtils.ApplicationStatus.SUBMITTED);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditListForAssignedToCheckerDate)){
						
						if(applicationStatusAuditListForAssignedToCheckerDate.size()>1){
							nhbsApplicationsResponse.setAssignedToCheckerDate(applicationStatusAuditListForAssignedToCheckerDate.get(applicationStatusAuditListForAssignedToCheckerDate.size()-1).getModifiedDate());
						}else {
							nhbsApplicationsResponse.setAssignedToCheckerDate(applicationStatusAuditListForAssignedToCheckerDate.get(0).getModifiedDate());
						}
					}
					if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getIsFinalLocked())){
						nhbsApplicationsResponse.setOneFormFilled(applicationProposalMapping.getIsFinalLocked() ? LITERAL_LOCKED : LITERAL_UNLOCKED);
					}else{
						nhbsApplicationsResponse.setOneFormFilled(LITERAL_UNLOCKED);
					}
					/*if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(loanApplicationMaster.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
						}
					}*/
					/*else {
						nhbsApplicationsResponse.setCheckerName("NA");
					}*/
					if(applicationProposalMapping.getApplicationStatusMaster().getId()==CommonUtils.ApplicationStatus.ASSIGNED ||
							applicationProposalMapping.getApplicationStatusMaster().getId()==CommonUtils.ApplicationStatus.SUBMITTED){
						nhbsApplicationsResponse.setApplicationWith("Maker");
						if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping.getFpMakerId())){
							UsersRequest usersRequest = new UsersRequest();
							usersRequest.setId(applicationProposalMapping.getFpMakerId());
							try {
								UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
								FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
										FundProviderDetailsRequest.class);
								nhbsApplicationsResponse.setMakerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
							} catch (Exception e) {
								logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
							}
						}
					}else if(applicationProposalMapping.getApplicationStatusMaster().getId()>=CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
						nhbsApplicationsResponse.setApplicationWith("Checker");
					}
				}

				ProposalDetails proposalDetails = proposalDetailsRepository.getSanctionProposalByApplicationId(nhbsApplicationsResponse.getApplicationId());
				IneligibleProposalDetails ineligibleProposalDetails = ineligibleProposalDetailsRepository.getSanctionedByApplicationId(nhbsApplicationsResponse.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
					ApplicationProposalMapping applicationProposalMapping1 = applicationProposalMappingRepository.findOne(proposalDetails.getId());
					if (!applicationProposalMapping1.getOrgId().equals(npOrgId)) {
						nhbsApplicationsResponse.setSanction(true);
					}
				}
				if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails)
						&& (CommonUtils.isObjectNullOrEmpty(nhbsApplicationsResponse.isSanction()) || !nhbsApplicationsResponse.isSanction())){
					if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getUserOrgId())
							&& !ineligibleProposalDetails.getUserOrgId().equals(npOrgId.toString())){
						if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getIsDisbursed()) && ineligibleProposalDetails.getIsDisbursed() == true)
							nhbsApplicationsResponse.setSanction(true);
						else if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getIsSanctioned()) && ineligibleProposalDetails.getIsSanctioned() == true)
							nhbsApplicationsResponse.setSanction(true);
					}else{
						nhbsApplicationsResponse.setSanction(false);
					}
				}
				nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
			}
		}
		logger.info("exit form getListOfProposalsFP()");
		return nhbsApplicationsResponseList;

	}

    /*@Override
    public List<NhbsApplicationsResponse> getListOfAssignedProposalsFP(NhbsApplicationRequest request) {
        logger.info("entry in getListOfAssignedProposalsFP()");
        List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
        if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId()){
        	if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED || request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
				applicationMastersList = loanApplicationRepository.getFPAssignedProposalsByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),request.getUserId());
			}else{
				applicationMastersList = loanApplicationRepository.getFPProposalsIwthOthersByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED,request.getUserId());
			}

        }else{
            applicationMastersList = null;
        }

        List<NhbsApplicationsResponse> nhbsApplicationsResponseList =null;
        if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
            nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
            for (LoanApplicationMaster loanApplicationMaster : applicationMastersList) {
                NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
                nhbsApplicationsResponse.setApplicationType(loanApplicationMaster.getProductId());
                nhbsApplicationsResponse.setUserId(loanApplicationMaster.getUserId());
                nhbsApplicationsResponse.setApplicationId(loanApplicationMaster.getId());
                if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDdrStatusId())){
                    nhbsApplicationsResponse.setDdrStatus(CommonUtils.getDdrStatusString(loanApplicationMaster.getDdrStatusId().intValue()));
                    nhbsApplicationsResponse.setDdrStatusId(loanApplicationMaster.getDdrStatusId().intValue());
                }else{
                    nhbsApplicationsResponse.setDdrStatus("NA");
                }

                CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
                if(applicantDetail != null){
                    nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());
                    try {
                        // Setting City Value
                        if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId())) {
                            nhbsApplicationsResponse.setCity(
                                    CommonDocumentUtils.getCity(applicantDetail.getRegisteredCityId(), oneFormClient));
                        }

                        // Setting State Value
                        if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId())) {
                            nhbsApplicationsResponse.setState(CommonDocumentUtils
                                    .getState(applicantDetail.getRegisteredStateId().longValue(), oneFormClient));
                        }

                        // Country State Value
                        if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId())) {
                            nhbsApplicationsResponse.setCountry(CommonDocumentUtils
                                    .getCountry(applicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
                        }
                    } catch (Exception e) {
                        logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
                    }
                }
                // get profile pic
                DocumentRequest documentRequest = new DocumentRequest();
                documentRequest.setApplicationId(loanApplicationMaster.getId());
                documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
                documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
                try {
                    DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
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
                    nhbsApplicationsResponse.setClientProfilePic(imagePath);

                } catch (DocumentException | IOException e) {
                    logger.error(ERROR_WHILE_GETTING_PROFILE_IMAGE,e);
                }
                if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())){
                    nhbsApplicationsResponse.setOneFormFilled(loanApplicationMaster.getIsFinalLocked() ? LITERAL_LOCKED : LITERAL_UNLOCKED);
                }else{
                    nhbsApplicationsResponse.setOneFormFilled(LITERAL_UNLOCKED);
                }

                if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId()){
					nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
                	List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByNpUserIdBasedOnStatus(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.OPEN, request.getUserId());
                    if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
                        nhbsApplicationsResponse.setProposalTakenDate(applicationStatusAuditList.get(0).getModifiedDate());
                    }
					if(request.getApplicationStatusId()>=CommonUtils.ApplicationStatus.ASSIGNED){
						if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){
							UsersRequest usersRequest = new UsersRequest();
							usersRequest.setId(loanApplicationMaster.getNpUserId());
							try {
								UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
								FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
										FundProviderDetailsRequest.class);
								nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
							} catch (Exception e) {
								logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
							}
						}
						else {
							nhbsApplicationsResponse.setCheckerName("NA");
						}
						if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED){
							nhbsApplicationsResponse.setApplicationWith("Maker");
							if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getFpMakerId())){
								UsersRequest usersRequest = new UsersRequest();
								usersRequest.setId(loanApplicationMaster.getFpMakerId());
								try {
									UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
									FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
											FundProviderDetailsRequest.class);
									nhbsApplicationsResponse.setMakerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
								} catch (Exception e) {
									logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
								}
							}
						}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
							nhbsApplicationsResponse.setApplicationWith("Checker");
						}
					}
                }
                nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
            }
        }

        logger.info("exit from getListOfAssignedProposalsFP()");
        return nhbsApplicationsResponseList;
    }*/

	@Override
	public List<NhbsApplicationsResponse> getListOfCheckerProposalsFP(NhbsApplicationRequest request) {
		logger.info("entry in getListOfCheckerProposalsFP()");
		Long branchId = null;
		UsersRequest usersRequest = new UsersRequest();
		usersRequest.setId(request.getUserId());
		usersRequest.setUserOrgId(request.getUserOrgId());
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequest);
			BranchBasicDetailsRequest branchBasicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error(ERROR_WHILE_FP_BRANCH_DETAILS,e);
		}
		List<BigInteger> applicationIdList ;
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER == request.getUserRoleId()){
            //List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchIdAndFpProductId(branchId,request.getFpProductId());
				if(request.getDdrStatusId()==CommonUtils.DdrStatus.SUBMITTED){
					applicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerProposalsByNPUserIdPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),request.getUserId(),branchId,request.getFpProductId(), ApplicationStatus.ASSIGNED_TO_CHECKER,request.getBusinessTypeId());
                }else if(request.getDdrStatusId()==CommonUtils.DdrStatus.APPROVED){
					applicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerProposalsByNPUserIdPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),request.getUserId(),branchId,request.getFpProductId(), ApplicationStatus.APPROVED,request.getBusinessTypeId());
                }else if(request.getDdrStatusId()==CommonUtils.DdrStatus.REVERTED){
					applicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerReverted(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),request.getUserId(),branchId,request.getFpProductId(), ApplicationStatus.REVERTED,request.getBusinessTypeId());
                }else{
					applicationIdList = applicationProposalMappingRepository.getFPCheckerProposalsWithOthersForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.OPEN,request.getUserId(),branchId,request.getFpProductId(),request.getBusinessTypeId());
				}
			//applicationMastersList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
		}else{
			applicationIdList = null;
		}

		List<NhbsApplicationsResponse> nhbsApplicationsResponseList =null;
		if(!CommonUtils.isListNullOrEmpty(applicationIdList)) {
			nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
			for (BigInteger proposalId : applicationIdList) {
				ApplicationProposalMapping proposalMapping = applicationProposalMappingRepository.findOne(proposalId.longValue());
				NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
				nhbsApplicationsResponse.setApplicationType(proposalMapping.getProductId());
				nhbsApplicationsResponse.setUserId(proposalMapping.getUserId());
				nhbsApplicationsResponse.setApplicationId(proposalMapping.getApplicationId());
				nhbsApplicationsResponse.setApplicationCode(proposalMapping.getApplicationCode());
				nhbsApplicationsResponse.setProposalId(proposalMapping.getProposalId());
				nhbsApplicationsResponse.setBusinessTypeId(proposalMapping.getBusinessTypeId());
				nhbsApplicationsResponse.setApprovalDate(proposalMapping.getApprovedDate());
				if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getDdrStatusId())) {
					nhbsApplicationsResponse.setDdrStatus(CommonUtils.getDdrStatusString(proposalMapping.getDdrStatusId().intValue()));
					nhbsApplicationsResponse.setDdrStatusId(proposalMapping.getDdrStatusId().intValue());
				} else {
					nhbsApplicationsResponse.setDdrStatus("-");
				}

				if (CommonUtils.BusinessType.EXISTING_BUSINESS.getId().equals(proposalMapping.getBusinessTypeId()) ||
						CommonUtils.BusinessType.NEW_TO_BUSINESS.getId().equals(proposalMapping.getBusinessTypeId())) {

					CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndProposalIdAndUserId(proposalMapping.getUserId(), proposalMapping.getApplicationId(), nhbsApplicationsResponse.getProposalId());
					if (applicantDetail != null) {
						nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());
						try {
							// Setting City Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCityId())) {
								nhbsApplicationsResponse.setCity(
										CommonDocumentUtils.getCity(applicantDetail.getRegisteredCityId(), oneFormClient));
							}

							// Setting State Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredStateId())) {
								nhbsApplicationsResponse.setState(CommonDocumentUtils
										.getState(applicantDetail.getRegisteredStateId().longValue(), oneFormClient));
							}

							// Country State Value
							if (!CommonUtils.isObjectNullOrEmpty(applicantDetail.getRegisteredCountryId())) {
								nhbsApplicationsResponse.setCountry(CommonDocumentUtils
										.getCountry(applicantDetail.getRegisteredCountryId().longValue(), oneFormClient));
							}
						} catch (Exception e) {
							logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
						}
					}
				} else if (proposalMapping.getBusinessTypeId().equals(CommonUtils.BusinessType.RETAIL_PERSONAL_LOAN.getId())) {

					RetailApplicantDetail retailApplicantDetail = retailApplicantDetailRepository.getByApplicationAndUserId(proposalMapping.getUserId(), proposalMapping.getApplicationId());
					if (retailApplicantDetail != null) {
						try {
							String firstName = retailApplicantDetail.getFirstName();
							String lastName = retailApplicantDetail.getLastName();
							String middleName = retailApplicantDetail.getMiddleName();

							String fullName = firstName == null ? "" : firstName;
							fullName += middleName == null ? "" : " " + middleName;
							fullName += lastName == null ? "" : " " + lastName;

							nhbsApplicationsResponse.setClientName(fullName);
							// Setting City Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressCity())) {
								nhbsApplicationsResponse.setCity(
										CommonDocumentUtils.getCity(retailApplicantDetail.getAddressCity(), oneFormClient));
							}

							// Setting State Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getAddressState())) {
								nhbsApplicationsResponse.setState(CommonDocumentUtils
										.getState(retailApplicantDetail.getAddressState().longValue(), oneFormClient));
							}

							// Country State Value
							if (!CommonUtils.isObjectNullOrEmpty(retailApplicantDetail.getOfficeCountryId())) {
								nhbsApplicationsResponse.setCountry(CommonDocumentUtils
										.getCountry(retailApplicantDetail.getOfficeCountryId().longValue(), oneFormClient));
							}
						} catch (Exception e) {
							logger.error(ERROR_FETCHING_DETAILS_FROM_ONEFORM_CLIENT_FOR_CITY_STATE_COUNTRY,e);
						}
					}

				}
				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(proposalMapping.getApplicationId());
				documentRequest.setProposalMappingId(proposalMapping.getProposalId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(proposalMapping.getProductId()));
				try {
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
					nhbsApplicationsResponse.setClientProfilePic(imagePath);

				} catch (DocumentException | IOException e) {
					logger.error(ERROR_WHILE_GETTING_PROFILE_IMAGE,e);
				}

				if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getIsFinalLocked())) {
					nhbsApplicationsResponse.setOneFormFilled(proposalMapping.getIsFinalLocked() ? LITERAL_LOCKED : LITERAL_UNLOCKED);
				} else {
					nhbsApplicationsResponse.setOneFormFilled(LITERAL_UNLOCKED);
				}


				nhbsApplicationsResponse.setApplicationDate(proposalMapping.getCreatedDate());
				if (request.getDdrStatusId() == CommonUtils.DdrStatus.SUBMITTED) {
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdAndProposalIdBasedOnDDRStatusForFPChecker(proposalMapping.getApplicationId(), proposalMapping.getProposalId(), CommonUtils.DdrStatus.IN_PROGRESS);
					if (!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)) {
						nhbsApplicationsResponse.setReceivedDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				} else if (request.getDdrStatusId() == CommonUtils.DdrStatus.REVERTED) {
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdAndProposalIdBasedOnDDRStatusForFPChecker(proposalMapping.getApplicationId(), proposalMapping.getProposalId(), CommonUtils.DdrStatus.SUBMITTED);
					if (!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)) {
						nhbsApplicationsResponse.setRevertDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				} else if (request.getDdrStatusId() == CommonUtils.DdrStatus.APPROVED) {
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdAndProposalIdBasedOnDDRStatusForFPChecker(proposalMapping.getApplicationId(), proposalMapping.getProposalId(), CommonUtils.DdrStatus.SUBMITTED);
					if (!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)) {
						nhbsApplicationsResponse.setApprovalDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				}


				if (proposalMapping.getApplicationStatusMaster().getId() >= CommonUtils.ApplicationStatus.ASSIGNED) {
					if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getFpMakerId())) {
						UsersRequest usersRequestForMaker = new UsersRequest();
						usersRequestForMaker.setId(proposalMapping.getFpMakerId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequestForMaker);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setMakerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
						}
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdAndProposalIdBasedOnStatusForFPMaker(proposalMapping.getApplicationId(), proposalMapping.getProposalId(), CommonUtils.ApplicationStatus.OPEN);
					if (!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)) {
						nhbsApplicationsResponse.setProposalTakenDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				} else {
					nhbsApplicationsResponse.setMakerName("-");
				}
//				if ((proposalMapping.getApplicationStatusMaster().getId() >= CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER) ||
//						(proposalMapping.getDdrStatusId() >= CommonUtils.DdrStatus.SUBMITTED)) {
				if ((proposalMapping.getApplicationStatusMaster().getId() >= CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER)) {
					if (!CommonUtils.isObjectNullOrEmpty(proposalMapping.getNpUserId())) {
						UsersRequest usersRequestForMaker = new UsersRequest();
						usersRequestForMaker.setId(proposalMapping.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequestForMaker);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object, Object>) userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "" : fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error(ERROR_WHILE_FETCHING_FP_DETAILS,e);
						}
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdAndProposalIdBasedOnStatusForFPMaker(proposalMapping.getApplicationId(), proposalMapping.getProposalId(), CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER);
					if (!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)) {
						nhbsApplicationsResponse.setReceivedDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				} else {
					nhbsApplicationsResponse.setCheckerName("-");
				}

				ProposalDetails proposalDetails = proposalDetailsRepository.getSanctionProposalByApplicationId(nhbsApplicationsResponse.getApplicationId());
				IneligibleProposalDetails ineligibleProposalDetails = ineligibleProposalDetailsRepository.getSanctionedByApplicationId(nhbsApplicationsResponse.getApplicationId());
				if (!CommonUtils.isObjectNullOrEmpty(proposalDetails)) {
					ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findOne(proposalDetails.getId());
					if (!applicationProposalMapping.getOrgId().equals(usersRequest.getUserOrgId())) {
						nhbsApplicationsResponse.setSanction(true);
					}
				}
				if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails)
						&& (CommonUtils.isObjectNullOrEmpty(nhbsApplicationsResponse.isSanction()) || !nhbsApplicationsResponse.isSanction())){
					if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getUserOrgId())
							&& !ineligibleProposalDetails.getUserOrgId().equals(usersRequest.getUserOrgId().toString())){
						if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getIsDisbursed()) && ineligibleProposalDetails.getIsDisbursed() == true)
							nhbsApplicationsResponse.setSanction(true);
						else if(!CommonUtils.isObjectNullOrEmpty(ineligibleProposalDetails.getIsSanctioned()) && ineligibleProposalDetails.getIsSanctioned() == true)
							nhbsApplicationsResponse.setSanction(true);
					}else{
						nhbsApplicationsResponse.setSanction(false);
					}
				}
				nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
			}
		}

		logger.info("exit from getListOfCheckerProposalsFP()");
		return nhbsApplicationsResponseList;
	}

    @Override
	public JSONObject getFPProposalCount(NhbsApplicationRequest nhbsApplicationRequest,Long npOrgId) {
		logger.info("entry in getFPProposalCount()");

		Long branchId = null;
		UsersRequest usersRequestForBranch = new UsersRequest();
		usersRequestForBranch.setId(nhbsApplicationRequest.getUserId());
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequestForBranch);
			BranchBasicDetailsRequest branchBasicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error(ERROR_WHILE_FP_BRANCH_DETAILS,e);
		}

		JSONObject countObj = new JSONObject();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == nhbsApplicationRequest.getUserRoleId()){
			//List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchId(branchId);
			List<BigInteger> newApplicationIdList = null;
			if(environment.getRequiredProperty(isPaymentBypass).equals("true")) {
				//logger.info("In payment bypass" + " \nRequest details Application status" + CommonUtils.ApplicationStatus.OPEN + " ORGID: " + npOrgId +" branch id: " + branchId);
				newApplicationIdList = applicationProposalMappingRepository.getFPMakerNewProposalCount(CommonUtils.ApplicationStatus.OPEN,npOrgId,CommonUtils.PaymentStatus.BYPASS,branchId,nhbsApplicationRequest.getBusinessTypeId());
			}
			else {
				//logger.info("In payment");
				//logger.info("In payment " + " \nRequest details Application status" + CommonUtils.ApplicationStatus.OPEN + " ORGID: " + npOrgId +" branch id: " + branchId);
				newApplicationIdList = applicationProposalMappingRepository.getFPMakerNewProposalCount(CommonUtils.ApplicationStatus.OPEN,npOrgId,CommonUtils.PaymentStatus.SUCCESS,branchId,nhbsApplicationRequest.getBusinessTypeId());
			}
			//logger.info("Applications found from database :: " + newApplicationIdList.size());
			//newApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("newProposalCount", newApplicationIdList.size());

			List<BigInteger> assignedToMakerApplicationIdList = applicationProposalMappingRepository.getFPAssignedTabPropsByNPUserIdCount(CommonUtils.ApplicationStatus.ASSIGNED,CommonUtils.ApplicationStatus.REVERTED,CommonUtils.ApplicationStatus.SUBMITTED,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getBusinessTypeId());
			//assignedToMakerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("pendingProposalCount", assignedToMakerApplicationIdList.size());

			List<BigInteger> assignedToCheckerApplicationIdList = applicationProposalMappingRepository.getFPMakerAssignedAndAssginedToCheckerCount(CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getBusinessTypeId());
			//assignedToCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("assignedToCheckerProposalCount", assignedToCheckerApplicationIdList.size());

			List<BigInteger> allOtherApplicationIdList = applicationProposalMappingRepository.getFPProposalsWithOthersCount(CommonUtils.ApplicationStatus.ASSIGNED,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getBusinessTypeId());
			//allOtherApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("allOtherProposalCount", allOtherApplicationIdList.size());

		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER == nhbsApplicationRequest.getUserRoleId()){
			//List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchIdAndFpProductId(branchId,nhbsApplicationRequest.getFpProductId());
			List<BigInteger> assignedTocheckerApplicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerProposalsCount(CommonUtils.DdrStatus.SUBMITTED,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getFpProductId(), ApplicationStatus.ASSIGNED_TO_CHECKER,nhbsApplicationRequest.getBusinessTypeId());
			//assignedTocheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("assignedToCheckerProposalCount", assignedTocheckerApplicationIdList.size());

			List<BigInteger> approvedByCheckerApplicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerProposalsCount(CommonUtils.DdrStatus.APPROVED,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getFpProductId(), ApplicationStatus.APPROVED,nhbsApplicationRequest.getBusinessTypeId());
			//approvedByCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("approvedByCheckerPropsalCount", approvedByCheckerApplicationIdList.size());

			List<BigInteger> revertedByCheckerApplicationIdList = applicationProposalMappingRepository.getFPAssignedToCheckerRevertedCount(CommonUtils.DdrStatus.REVERTED,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getFpProductId(), ApplicationStatus.REVERTED,nhbsApplicationRequest.getBusinessTypeId());
			//revertedByCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("revertedByCheckerPropsalCount", revertedByCheckerApplicationIdList.size());

			List<BigInteger> allOtherCheckerApplicationIdList = applicationProposalMappingRepository.getFPCheckerProposalsWithOthersCount(CommonUtils.ApplicationStatus.OPEN,nhbsApplicationRequest.getUserId(),branchId,nhbsApplicationRequest.getFpProductId(),nhbsApplicationRequest.getBusinessTypeId());
			//allOtherCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("allOtherProposalCount", allOtherCheckerApplicationIdList.size());

		}
		logger.info("exit from getFPProposalCount()");
		logger.info(countObj.toJSONString());
		return countObj;
	}

	@Override
	public boolean setFPMaker(NhbsApplicationRequest request) {
		logger.info("entry in setFPMaker()");
		//LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		ApplicationProposalMapping  applicationMaster = applicationProposalMappingRepository.findOne(request.getProposalMappingId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setFpMakerId(request.getUserId());
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			applicationProposalMappingRepository.save(applicationMaster);

			try {
				WorkflowRequest workflowRequest = new WorkflowRequest();
				workflowRequest.setApplicationId(applicationMaster.getApplicationId());
				workflowRequest.setUserId(request.getUserId());
				workflowRequest.setActionId(WorkflowUtils.Action.ASSIGN_TO_MAKER_ON_SAVE);
				if(applicationMaster.getBusinessTypeId() == BusinessType.EXISTING_BUSINESS.getId()) {
					workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
				}else if(applicationMaster.getBusinessTypeId() == BusinessType.RETAIL_PERSONAL_LOAN.getId()) {
					workflowRequest.setWorkflowId(WorkflowUtils.Workflow.PL_PROCESS);
				}
				
				WorkflowResponse workflowResponse = workflowClient.createJob(workflowRequest);
				if (com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse)
						|| workflowResponse.getStatus() != HttpStatus.OK.value()) {
					logger.error(
							"Something goes wrong with Creating Process when Generating Matches for Application Id===>{} ===>>{}",
							applicationMaster.getApplicationId(), request.getUserId());
				} else {
					logger.info("Status of Creating Proess for Application Id===>{} ===>>{}",
							applicationMaster.getApplicationId(), request.getUserId());
				}

			} catch (Exception e) {
				logger.error("Error while Creating Process ==>{}", e);
			}

			//=====================Sending Mail to all Makers/Checkers/HO/BO when maker accept to fill the Proposal===================
			
			    fpAsyncComponent.sendMailToMakerandAllMakersWhenMakerAcceptProposal(request);
			
			//========================================================================================================================
			 
			logger.info(MSG_EXIT_FROM_SET_FP_MAKER);
			return true;
		}
		logger.info(MSG_EXIT_FROM_SET_FP_MAKER);
		return false;
	}

	@Override
	public boolean setFPCheckerByProposalId(NhbsApplicationRequest request) {
		logger.info("entry in setFPChecker()");
		ApplicationProposalMapping applicationMaster = applicationProposalMappingRepository.findOne(request.getProposalMappingId());

		Long applicationStatus = null;
		Date lastModifiedDate = null;
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)) {
			if(!CommonUtils.isObjectNullOrEmpty(applicationMaster.getApplicationStatusMaster())
					&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getApplicationStatusMaster().getId())) {
				applicationStatus = applicationMaster.getApplicationStatusMaster().getId();
				if(!CommonUtils.isObjectNullOrEmpty(applicationMaster.getModifiedDate())) {
					lastModifiedDate = applicationMaster.getModifiedDate();
				}
			}
		}
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			applicationProposalMappingRepository.save(applicationMaster);

			//=====================Sending Mail to all Checker/HO/BO when Maker assign/Re-assign DDR to Checker===================

			if(!CommonUtils.isObjectNullOrEmpty(applicationStatus) && CommonUtils.ApplicationStatus.REVERTED.equals(applicationStatus)) {
				fpAsyncComponent.sendMailWhenMakerReAssignDDRToChecker(request, lastModifiedDate);
			}
			else {
				fpAsyncComponent.sendMailWhenMakerAssignDDRToChecker(request);
			}

			//========================================================================================================================


			logger.info("exit from setFPChecker()");
			return true;
		}
		logger.info("exit from setFPChecker()");
		return false;
	}

	@Override
	public boolean setFPChecker(NhbsApplicationRequest request) {
		logger.info("entry in setFPChecker()");
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
        
		Long applicationStatus = null;
		Date lastModifiedDate = null;
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster) && !CommonUtils.isObjectNullOrEmpty(applicationMaster.getApplicationStatusMaster())
				&& !CommonUtils.isObjectNullOrEmpty(applicationMaster.getApplicationStatusMaster().getId()) ) {
				applicationStatus = applicationMaster.getApplicationStatusMaster().getId();
				if(!CommonUtils.isObjectNullOrEmpty(applicationMaster.getModifiedDate())) {
					lastModifiedDate = applicationMaster.getModifiedDate();
			}
		}
			if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			
			//=====================Sending Mail to all Checker/HO/BO when Maker assign/Re-assign DDR to Checker===================
	
			if(!CommonUtils.isObjectNullOrEmpty(applicationStatus) && CommonUtils.ApplicationStatus.REVERTED.equals(applicationStatus)) {
				fpAsyncComponent.sendMailWhenMakerReAssignDDRToChecker(request, lastModifiedDate);	
			}
			else {
				fpAsyncComponent.sendMailWhenMakerAssignDDRToChecker(request);	
			} 
		
     		//========================================================================================================================

			
			logger.info("exit from setFPChecker()");
			return true;
		}
		logger.info("exit from setFPChecker()");
		return false;
	}
	
	@Override
	public boolean revertApplication(NhbsApplicationRequest request) {
		logger.info("entry in revertApplication()");
		if(request.getProposalMappingId() != null) {
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(request.getProposalMappingId(), true);
			if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)) {
				applicationProposalMapping.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.REVERTED));
				applicationProposalMapping.setNpAssigneeId(request.getUserId());
				applicationProposalMapping.setNpUserId(request.getNpUserId());
				applicationProposalMapping.setModifiedBy(request.getUserId());
				applicationProposalMapping.setModifiedDate(new Date());
				applicationProposalMapping.setIsFinalLocked(false);
				if(BusinessType.EXISTING_BUSINESS.getId() == applicationProposalMapping.getBusinessTypeId()) {
					applicationProposalMapping.setDdrStatusId(CommonUtils.DdrStatus.REVERTED);
				}
				applicationProposalMappingRepository.save(applicationProposalMapping);
			}
		}
		
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			applicationMaster.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.REVERTED));
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			applicationMaster.setIsFinalLocked(false);
			applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.REVERTED);
			loanApplicationRepository.save(applicationMaster);
			logger.info("exit from revertApplication()");
			return true;
		}
		
		logger.info(MSG_EXIT_FROM_SET_FP_MAKER);
		return false;
	}

	public boolean approveApplication(NhbsApplicationRequest request) {
		logger.info("entry in revertApplication()");
		if(request.getProposalMappingId() != null) {
			ApplicationProposalMapping applicationProposalMapping = applicationProposalMappingRepository.findByProposalIdAndIsActive(request.getProposalMappingId(), true);
			if(!CommonUtils.isObjectNullOrEmpty(applicationProposalMapping)) {
				applicationProposalMapping.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.APPROVED));
				applicationProposalMapping.setNpAssigneeId(request.getUserId());
				applicationProposalMapping.setNpUserId(request.getNpUserId());
				applicationProposalMapping.setModifiedBy(request.getUserId());
				applicationProposalMapping.setModifiedDate(new Date());
				if(BusinessType.EXISTING_BUSINESS.getId() == applicationProposalMapping.getBusinessTypeId()) {
					applicationProposalMapping.setDdrStatusId(CommonUtils.DdrStatus.APPROVED);
				}
				applicationProposalMappingRepository.save(applicationProposalMapping);
			}
		}
		
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			applicationMaster.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.APPROVED));
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.APPROVED);
			loanApplicationRepository.save(applicationMaster);
			logger.info("exit from revertApplication()");
			return true;
		}
		
		logger.info(MSG_EXIT_FROM_SET_FP_MAKER);
		return false;
	}
}
