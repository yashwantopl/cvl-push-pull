package com.capitaworld.service.loans.service.networkpartner.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import com.capitaworld.service.gateway.model.GatewayResponse;
import com.capitaworld.service.gateway.model.PaymentTypeRequest;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.model.StorageDetailsResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
import com.capitaworld.service.gateway.client.GatewayClient;
import com.capitaworld.service.gateway.exception.GatewayException;
import com.capitaworld.service.gateway.model.GatewayRequest;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusAudit;
import com.capitaworld.service.loans.domain.fundseeker.ApplicationStatusMaster;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;
import com.capitaworld.service.loans.domain.fundseeker.corporate.CorporateApplicantDetail;
import com.capitaworld.service.loans.model.NhbsApplicationRequest;
import com.capitaworld.service.loans.model.NhbsApplicationsResponse;
import com.capitaworld.service.loans.repository.fundseeker.ApplicationStatusAuditRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.CorporateApplicantDetailRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.networkpartner.NetworkPartnerService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.NetworkPartnerDetailsRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class NetworkPartnerServiceImpl implements NetworkPartnerService {

	private static final Logger logger = LoggerFactory.getLogger(NetworkPartnerServiceImpl.class);
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CorporateApplicantDetailRepository corpApplicantRepository;
	
	@Autowired
	private ApplicationStatusAuditRepository appStatusRepository;
	
	@Autowired
	private DMSClient dmsClient;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private OneFormClient  oneFormClient;
	
	@Autowired
	private GatewayClient gatewayClient;
	
	@Override
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request,Long npOrgId) {
		logger.info("entry in getListOfProposals()");
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			if(!CommonUtils.isObjectListNull(npOrgId)){
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgId(request.getApplicationStatusId(),npOrgId);
			}else{
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatus(request.getApplicationStatusId());
				logger.info("list of payment for nhbs call->"+applicationMastersList.size());
			}
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == request.getUserRoleId()){
            if(!CommonUtils.isObjectListNull(npOrgId)){
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatusAndNpOrgId(request.getDdrStatusId(),npOrgId);
            }else {
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatus(request.getDdrStatusId());
            }
			applicationMastersList.sort(Comparator.comparing(LoanApplicationMaster::getModifiedDate));
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
						logger.error("error while fetching network partner details");
						e.printStackTrace();
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
						// TODO: handle exception
						logger.error("error while fetching details from oneform client for city/state/country");
						e.printStackTrace();
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
					logger.error("error while getting profile image");
					e.printStackTrace();
				}
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){

					List<Map<String, Object>> receivedPaymentList = new ArrayList<>();
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
						logger.error("error while calling gateway client");
						e.printStackTrace();
					}
					logger.info("appId->"+loanApplicationMaster.getId());
					if(!CommonUtils.isObjectListNull(receivedAppIdList) && receivedAppIdList.contains(loanApplicationMaster.getId())){
					    logger.info("received list->"+receivedAppIdList.toString());
						nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
						try{
							UserResponse userResponse = usersClient.checkUserUnderSp(loanApplicationMaster.getUserId());
							if(!CommonUtils.isObjectNullOrEmpty(userResponse) && userResponse.getStatus() == 200 && (boolean)userResponse.getData()){
								nhbsApplicationsResponse.setClientSource("SP");
							}else{
								nhbsApplicationsResponse.setClientSource("Direct");
							}
						}catch (Exception e) {
							logger.error("error while calling users clients while calling checkUserUnderSp()");
							e.printStackTrace();
						}
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
					}
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
							logger.error("error while fetching network partner details");
							e.printStackTrace();
						}
					}
					nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
				}
			}
		}else{
			nhbsApplicationsResponseList = null;
		}
		logger.info("exit form getListOfProposals()");
		return nhbsApplicationsResponseList;
		
	}

	@Override
	public List<NhbsApplicationsResponse> getListOfAssignedProposals(NhbsApplicationRequest request) {
		logger.info("entry in getListOfAssignedProposals()");
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByAssigneeId(CommonUtils.ApplicationStatus.ASSIGNED, request.getUserId());
			applicationMastersList.sort(Comparator.comparing(LoanApplicationMaster::getModifiedDate));
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByNpUserId(request.getUserId());
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
						// TODO: handle exception
						logger.error("error while fetching details from oneform client for city/state/country");
						e.printStackTrace();
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
					logger.error("error while getting profile image");
					e.printStackTrace();
				}
				if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())){
					nhbsApplicationsResponse.setOneFormFilled(loanApplicationMaster.getIsFinalLocked() ? "Locked" : "Unlocked");	
				}else{
					nhbsApplicationsResponse.setOneFormFilled("Unlocked");
				}
				
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByNpUserIdBasedOnStatus(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.OPEN, request.getUserId());
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setApplicationDate(applicationStatusAuditList.get(0).getModifiedDate());
					}else{
						nhbsApplicationsResponse.setApplicationDate(new Date());
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getTypeOfPayment())){
						nhbsApplicationsResponse.setPaymentMode(loanApplicationMaster.getTypeOfPayment());
						nhbsApplicationsResponse.setIsPaymentDone("Received");
					}else{
						nhbsApplicationsResponse.setIsPaymentDone("Not Received");
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpAssigneeId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(loanApplicationMaster.getNpAssigneeId());
						try {
							UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
							NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									NetworkPartnerDetailsRequest.class);
							nhbsApplicationsResponse.setAssigneeName(networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching network partner details");
							e.printStackTrace();
						}
						
					}
				}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByAssigneeIdBasedOnStatus(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.OPEN, request.getUserId());
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setApplicationDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(loanApplicationMaster.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getNPDetails(usersRequest);	
							NetworkPartnerDetailsRequest networkPartnerDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									NetworkPartnerDetailsRequest.class);
							nhbsApplicationsResponse.setMakerName(networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName());
						} catch (Exception e) {
							logger.error("error while fetching network partner details");
							e.printStackTrace();
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
            List<LoanApplicationMaster> applicationMastersList = new ArrayList<>();
            if(!CommonUtils.isObjectListNull(npOrgId)){
                applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgId(CommonUtils.ApplicationStatus.OPEN,npOrgId);
            }else{
                applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatus(CommonUtils.ApplicationStatus.OPEN);
            }

			int newPropsalCount = 0;

			List<Map<String, Object>> receivedPaymentList = new ArrayList<>();
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
				logger.error("error while calling gateway client");
				e.printStackTrace();
			}
			if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
				for (LoanApplicationMaster loanApplicationMaster : applicationMastersList) {
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

	@Override
	public GatewayRequest getPaymentStatuOfApplication(Long applicationId) {
		logger.info("entry in getPaymentStatuOfApplication()");
		GatewayRequest gatewayRequest = new GatewayRequest();
		gatewayRequest.setApplicationId(applicationId);
		try {
			GatewayRequest paymentStatus = gatewayClient.getPaymentStatus(gatewayRequest);
			logger.info("exit from getPaymentStatuOfApplication()");
			return paymentStatus;
		} catch (GatewayException e) {
			// TODO Auto-generated catch block
			logger.error("error while calling gateway client for payment status");
			e.printStackTrace();			
		}
		return null;
	}

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
			notificationRequest.setClientRefId("123");
			Notification notification = new Notification();
			notification.setContentType(ContentType.TEMPLATE);

			notification.setTemplateId(NotificationAlias.SMS_CHECKER_ASSIGNED_MAKER);
			notification.setTo(to);
			notification.setType(NotificationType.SMS);
			Map<String, Object> parameters = new HashMap<String, Object>();

			parameters.put("maker", networkPartnerDetailsRequest.getFirstName() + " " + (networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName()));
			parameters.put("url", "www.bitly.com");
			
			notification.setParameters(parameters);
			notificationRequest.addNotification(notification);
			notificationRequest.addNotification(notification);

			notificationClient.send(notificationRequest);

			logger.info("End SMS notification to FS when Checker Assign Maker=====>");

			return true;
		} catch (Exception e) {

			logger.info("Error while sending SMS =====>");
			e.printStackTrace();

		}

		
		return false;
	}

}
