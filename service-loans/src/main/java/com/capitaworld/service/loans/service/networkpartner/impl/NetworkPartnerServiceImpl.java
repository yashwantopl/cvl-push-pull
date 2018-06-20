package com.capitaworld.service.loans.service.networkpartner.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.capitaworld.api.workflow.model.WorkflowRequest;
import com.capitaworld.api.workflow.model.WorkflowResponse;
import com.capitaworld.api.workflow.utility.WorkflowUtils;
import com.capitaworld.client.workflow.WorkflowClient;
import com.capitaworld.service.gateway.model.GatewayResponse;
import com.capitaworld.service.gateway.model.PaymentTypeRequest;
import com.capitaworld.service.loans.domain.fundprovider.FpNpMapping;
import com.capitaworld.service.loans.model.FpNpMappingRequest;
import com.capitaworld.service.loans.repository.fundprovider.FpNpMappingRepository;
import com.capitaworld.service.loans.repository.fundprovider.ProposalDetailsRepository;
import com.capitaworld.service.loans.service.fundprovider.FpNpMappingService;
import com.capitaworld.service.users.model.*;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
import com.capitaworld.service.oneform.enums.Title;
import com.capitaworld.service.users.client.UsersClient;

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

	@Autowired
	private FpNpMappingService fpNpMappingService;

	@Autowired
	private FpNpMappingRepository fpNpMappingRepository;

	@Autowired
	private ProposalDetailsRepository proposalDetailsRepository;

	@Autowired
	private WorkflowClient workflowClient;
	
	@Autowired
	private Environment environment; 
	
	
	private static String isPaymentBypass="cw.is_payment_bypass";

	@Override
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request,Long npOrgId,Long userId) {
		logger.info("entry in getListOfProposals()");
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			if(!CommonUtils.isObjectListNull(npOrgId) && npOrgId != CommonUtils.NP_NHBS){
				applicationMastersList = getApplicationListToAssignedCheckerFromBoFp(userId,CommonUtils.ApplicationStatus.OPEN,true,request.getPageIndex(),request.getSize());
			}else if(!CommonUtils.isObjectListNull(npOrgId)){
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
			}else{
				applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
			}
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == request.getUserRoleId()){
            if(!CommonUtils.isObjectListNull(npOrgId)){
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),npOrgId);
            }else {
                applicationMastersList = loanApplicationRepository.getProposalsByDdrStatusForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId());
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
							logger.error("error while calling gateway client");
							e.printStackTrace();
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
							logger.error("error while calling users clients while calling checkUserUnderSp()");
							e.printStackTrace();
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
							logger.error("error while fetching network partner details");
							e.printStackTrace();
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
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByAssigneeIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED, request.getUserId());
			applicationMastersList.sort(Comparator.comparing(LoanApplicationMaster::getModifiedDate));
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByNpUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getUserId());
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
						nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
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
					}else{
						nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
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
			int newPropsalCount = 0;
			if(!CommonUtils.isObjectListNull(npOrgId) && npOrgId != CommonUtils.NP_NHBS){
				applicationMastersList = getApplicationListToAssignedCheckerFromBoFp(nhbsApplicationRequest.getUserId(),CommonUtils.ApplicationStatus.OPEN,false,0,0);
				if(!CommonUtils.isObjectListNull(applicationMastersList))
					newPropsalCount = applicationMastersList.size();
			}else{
				if(!CommonUtils.isObjectListNull(npOrgId)){
					applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatusAndNpOrgId(CommonUtils.ApplicationStatus.OPEN,npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
				}else{
					applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatus(CommonUtils.ApplicationStatus.OPEN,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
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
					logger.error("error while calling gateway client");
					e.printStackTrace();
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

			logger.info("Error while sending SMS =====>");
			e.printStackTrace();

		}

		
		return false;
	}

	@Override
	public List<LoanApplicationMaster> getApplicationListToAssignedCheckerFromBoFp(Long userId,Long appStatusId,Boolean forPagination,int pageIndex,int size) {
		logger.info("entry in getApplicationListToAssignedCheckerFromBoFp()");
		List<FpNpMapping> fpNpMappingList = new ArrayList<FpNpMapping>();
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
		return null;
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
				logger.error("error while fetching network partner details");
				e.printStackTrace();
			}
		}
		logger.info("exit from getCheckerName()");
		return checkerName;
	}


	@Override
	public List<NhbsApplicationsResponse> getListOfProposalsFP(NhbsApplicationRequest request,Long npOrgId,Long userId) {
		logger.info("entry in getListOfProposalsFP()");
		Long branchId = null;
		UsersRequest usersRequestForBranch = new UsersRequest();
		usersRequestForBranch.setId(userId);
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequestForBranch);
			BranchBasicDetailsRequest branchBasicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error("error while FP branch details");
			e.printStackTrace();
		}
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == request.getUserRoleId()){
			List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchId(branchId);
			if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.OPEN){
				if(environment.getRequiredProperty(isPaymentBypass).equals("true")) {
				applicationMastersList = loanApplicationRepository.getFPProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.BYPASS);
				}
				else {
				applicationMastersList = loanApplicationRepository.getFPProposalsByApplicationStatusAndNpOrgIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
				}
			}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED){
				applicationMastersList = loanApplicationRepository.getFPAssignedTabPropsByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED,CommonUtils.ApplicationStatus.REVERTED,CommonUtils.ApplicationStatus.SUBMITTED,userId);
			}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
				applicationMastersList = loanApplicationRepository.getFPAssignedProposalsByNPUserIdForPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getApplicationStatusId(),userId);
			}else{
				applicationMastersList = loanApplicationRepository.getFPProposalsWithOthersForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.ASSIGNED,userId);
			}
			applicationMastersList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
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
						UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
						FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
								FundProviderDetailsRequest.class);
						nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
					} catch (Exception e) {
						logger.error("error while fetching FP details");
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

				nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
				if(request.getApplicationStatusId()>=CommonUtils.ApplicationStatus.ASSIGNED){
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getDdrStatusId())){
						nhbsApplicationsResponse.setDdrStatus(CommonUtils.getDdrStatusString(loanApplicationMaster.getDdrStatusId().intValue()));
						nhbsApplicationsResponse.setDdrStatusId(loanApplicationMaster.getDdrStatusId().intValue());
					}else{
						nhbsApplicationsResponse.setDdrStatus("NA");
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.OPEN);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setProposalTakenDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
					List<ApplicationStatusAudit> applicationStatusAuditListForAssignedToCheckerDate = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.ASSIGNED);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						if(applicationStatusAuditListForAssignedToCheckerDate.size()>1){
							nhbsApplicationsResponse.setAssignedToCheckerDate(applicationStatusAuditList.get(applicationStatusAuditListForAssignedToCheckerDate.size()-1).getModifiedDate());
						}else{
							nhbsApplicationsResponse.setAssignedToCheckerDate(applicationStatusAuditList.get(0).getModifiedDate());
						}
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())){
						nhbsApplicationsResponse.setOneFormFilled(loanApplicationMaster.getIsFinalLocked() ? "Locked" : "Unlocked");
					}else{
						nhbsApplicationsResponse.setOneFormFilled("Unlocked");
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){
						UsersRequest usersRequest = new UsersRequest();
						usersRequest.setId(loanApplicationMaster.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequest);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error("error while fetching FP details");
							e.printStackTrace();
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
								logger.error("error while fetching FP details");
								e.printStackTrace();
							}
						}
					}else if(request.getApplicationStatusId()==CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER){
						nhbsApplicationsResponse.setApplicationWith("Checker");
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
								logger.error("error while fetching FP details");
								e.printStackTrace();
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
									logger.error("error while fetching FP details");
									e.printStackTrace();
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
		try {
			UserResponse userResponseForName = usersClient.getBranchDetailsBYUserId(usersRequest);
			BranchBasicDetailsRequest branchBasicDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
					BranchBasicDetailsRequest.class);
			branchId = branchBasicDetailsRequest.getId();
		} catch (Exception e) {
			logger.error("error while FP branch details");
			e.printStackTrace();
		}
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER == request.getUserRoleId()){
            List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchIdAndFpProductId(branchId,request.getFpProductId());

				if(request.getDdrStatusId()==CommonUtils.DdrStatus.SUBMITTED || request.getDdrStatusId()==CommonUtils.DdrStatus.APPROVED){
                    applicationMastersList = loanApplicationRepository.getFPAssignedToCheckerProposalsByNPUserIdPagination(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),request.getUserId());
                }else if(request.getDdrStatusId()==CommonUtils.DdrStatus.REVERTED){
					applicationMastersList = loanApplicationRepository.getFPAssignedToCheckerReverted(new PageRequest(request.getPageIndex(),request.getSize()),request.getDdrStatusId(),request.getUserId());
                }else{
					applicationMastersList = loanApplicationRepository.getFPCheckerProposalsWithOthersForPagination(new PageRequest(request.getPageIndex(),request.getSize()),CommonUtils.ApplicationStatus.OPEN,request.getUserId());
				}
			applicationMastersList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
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
					nhbsApplicationsResponse.setDdrStatus("-");
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


					nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
                if(request.getDdrStatusId()==CommonUtils.DdrStatus.SUBMITTED){
                    List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnDDRStatusForFPChecker(loanApplicationMaster.getId(), CommonUtils.DdrStatus.IN_PROGRESS);
                    if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
                        nhbsApplicationsResponse.setReceivedDate(applicationStatusAuditList.get(0).getModifiedDate());
                    }
                }else if(request.getDdrStatusId()==CommonUtils.DdrStatus.REVERTED){
                    List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnDDRStatusForFPChecker(loanApplicationMaster.getId(), CommonUtils.DdrStatus.SUBMITTED);
                    if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
                        nhbsApplicationsResponse.setReceivedDate(applicationStatusAuditList.get(0).getModifiedDate());
                    }
                }else if(request.getDdrStatusId()==CommonUtils.DdrStatus.APPROVED){
                    List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnDDRStatusForFPChecker(loanApplicationMaster.getId(), CommonUtils.DdrStatus.SUBMITTED);
                    if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
                        nhbsApplicationsResponse.setApprovalDate(applicationStatusAuditList.get(0).getModifiedDate());
                    }
                }



				if(loanApplicationMaster.getApplicationStatusMaster().getId()>=CommonUtils.ApplicationStatus.ASSIGNED){
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getFpMakerId())){
						UsersRequest usersRequestForMaker = new UsersRequest();
						usersRequestForMaker.setId(loanApplicationMaster.getFpMakerId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequestForMaker);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setMakerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error("error while fetching FP details");
							e.printStackTrace();
						}
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.OPEN);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setProposalTakenDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				}else{
					nhbsApplicationsResponse.setMakerName("-");
				}
				if((loanApplicationMaster.getApplicationStatusMaster().getId()>=CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER) ||
						(loanApplicationMaster.getDdrStatusId()>=CommonUtils.DdrStatus.SUBMITTED)){
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getNpUserId())){
						UsersRequest usersRequestForMaker = new UsersRequest();
						usersRequestForMaker.setId(loanApplicationMaster.getNpUserId());
						try {
							UserResponse userResponseForName = usersClient.getFPDetails(usersRequestForMaker);
							FundProviderDetailsRequest fundProviderDetailsRequest = MultipleJSONObjectHelper.getObjectFromMap((Map<Object,Object>)userResponseForName.getData(),
									FundProviderDetailsRequest.class);
							nhbsApplicationsResponse.setCheckerName(fundProviderDetailsRequest.getFirstName() + " " + (fundProviderDetailsRequest.getLastName() == null ? "": fundProviderDetailsRequest.getLastName()));
						} catch (Exception e) {
							logger.error("error while fetching FP details");
							e.printStackTrace();
						}
					}
					List<ApplicationStatusAudit> applicationStatusAuditList = appStatusRepository.getApplicationByUserIdBasedOnStatusForFPMaker(loanApplicationMaster.getId(), CommonUtils.ApplicationStatus.ASSIGNED);
					if(!CommonUtils.isListNullOrEmpty(applicationStatusAuditList)){
						nhbsApplicationsResponse.setReceivedDate(applicationStatusAuditList.get(0).getModifiedDate());
					}
				}else{
					nhbsApplicationsResponse.setCheckerName("-");
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
			logger.error("error while FP branch details");
			e.printStackTrace();
		}

		JSONObject countObj = new JSONObject();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER == nhbsApplicationRequest.getUserRoleId()){
			List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchId(branchId);
			List<LoanApplicationMaster> newApplicationList = null;
			if(environment.getRequiredProperty(isPaymentBypass).equals("true")) {
				newApplicationList = loanApplicationRepository.getFPMakerNewProposalCount(CommonUtils.ApplicationStatus.OPEN,npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.BYPASS);
			}
			else {
				newApplicationList = loanApplicationRepository.getFPMakerNewProposalCount(CommonUtils.ApplicationStatus.OPEN,npOrgId,com.capitaworld.service.gateway.utils.CommonUtils.PaymentStatus.SUCCESS);
			}
			newApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("newProposalCount", newApplicationList.size());

			List<LoanApplicationMaster> assignedToMakerApplicationList = loanApplicationRepository.getFPAssignedTabPropsByNPUserIdCount(CommonUtils.ApplicationStatus.ASSIGNED,CommonUtils.ApplicationStatus.REVERTED,CommonUtils.ApplicationStatus.SUBMITTED,nhbsApplicationRequest.getUserId());
			assignedToMakerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("pendingProposalCount", assignedToMakerApplicationList.size());

			List<LoanApplicationMaster> assignedToCheckerApplicationList = loanApplicationRepository.getFPMakerAssignedAndAssginedToCheckerCount(CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER,nhbsApplicationRequest.getUserId());
			assignedToCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("assignedToCheckerProposalCount", assignedToCheckerApplicationList.size());

			List<LoanApplicationMaster> allOtherApplicationList = loanApplicationRepository.getFPProposalsWithOthersCount(CommonUtils.ApplicationStatus.ASSIGNED,nhbsApplicationRequest.getUserId());
			allOtherApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("allOtherProposalCount", allOtherApplicationList.size());

		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER == nhbsApplicationRequest.getUserRoleId()){
			List<Long> applicationForSameBranchList = proposalDetailsRepository.getApplicationsBasedOnBranchIdAndFpProductId(branchId,nhbsApplicationRequest.getFpProductId());
			List<LoanApplicationMaster> assignedTocheckerApplicationList = loanApplicationRepository.getFPAssignedToCheckerProposalsCount(CommonUtils.DdrStatus.SUBMITTED,nhbsApplicationRequest.getUserId());
			assignedTocheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("assignedToCheckerProposalCount", assignedTocheckerApplicationList.size());

			List<LoanApplicationMaster> approvedByCheckerApplicationList = loanApplicationRepository.getFPAssignedToCheckerProposalsCount(CommonUtils.DdrStatus.APPROVED,nhbsApplicationRequest.getUserId());
			approvedByCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("approvedByCheckerPropsalCount", approvedByCheckerApplicationList.size());

			List<LoanApplicationMaster> revertedByCheckerApplicationList = loanApplicationRepository.getFPAssignedToCheckerRevertedCount(CommonUtils.DdrStatus.REVERTED,nhbsApplicationRequest.getUserId());
			revertedByCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("revertedByCheckerPropsalCount", revertedByCheckerApplicationList.size());

			List<LoanApplicationMaster> allOtherCheckerApplicationList = loanApplicationRepository.getFPCheckerProposalsWithOthersCount(CommonUtils.ApplicationStatus.OPEN,nhbsApplicationRequest.getUserId());
			allOtherCheckerApplicationList.removeIf((LoanApplicationMaster loanApplicationMaster) -> !applicationForSameBranchList.contains(loanApplicationMaster.getId()));
			countObj.put("allOtherProposalCount", allOtherCheckerApplicationList.size());

		}
		logger.info("exit from getFPProposalCount()");
		return countObj;
	}

	@Override
	public boolean setFPMaker(NhbsApplicationRequest request) {
		logger.info("entry in setFPMaker()");
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setFpMakerId(request.getUserId());
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);

			try {
				WorkflowRequest workflowRequest = new WorkflowRequest();
				workflowRequest.setApplicationId(applicationMaster.getId());
				workflowRequest.setUserId(request.getUserId());
				workflowRequest.setActionId(WorkflowUtils.Action.ASSIGN_TO_MAKER_ON_SAVE);
				workflowRequest.setWorkflowId(WorkflowUtils.Workflow.DDR);
				WorkflowResponse workflowResponse = workflowClient.createJob(workflowRequest);
				if (com.capitaworld.service.matchengine.utils.CommonUtils.isObjectNullOrEmpty(workflowResponse)
						|| workflowResponse.getStatus() != HttpStatus.OK.value()) {
					logger.error(
							"Something goes wrong with Creating Process when Generating Matches for Application Id===>{} ===>>{}",
							applicationMaster.getId(), request.getUserId());
				} else {
					logger.info("Status of Creating Proess for Application Id===>{} ===>>{}",
							applicationMaster.getId(), request.getUserId());
				}

			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error while Creating Process ==>{}", e);
			}

			logger.info("exit from setFPMaker()");
			return true;
		}
		logger.info("exit from setFPMaker()");
		return false;
	}

	@Override
	public boolean setFPChecker(NhbsApplicationRequest request) {
		logger.info("entry in setFPChecker()");
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			ApplicationStatusMaster applicationStatusMaster = new ApplicationStatusMaster();
			applicationStatusMaster.setId(CommonUtils.ApplicationStatus.ASSIGNED_TO_CHECKER);
			applicationMaster.setApplicationStatusMaster(applicationStatusMaster);
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			loanApplicationRepository.save(applicationMaster);
			logger.info("exit from setFPChecker()");
			return true;
		}
		logger.info("exit from setFPChecker()");
		return false;
	}
	
	@Override
	public boolean revertApplication(NhbsApplicationRequest request) {
		logger.info("entry in revertApplication()");
		LoanApplicationMaster applicationMaster = loanApplicationRepository.findOne(request.getApplicationId());
		if(!CommonUtils.isObjectNullOrEmpty(applicationMaster)){
			applicationMaster.setApplicationStatusMaster(new ApplicationStatusMaster(CommonUtils.ApplicationStatus.REVERTED));
			applicationMaster.setNpAssigneeId(request.getUserId());
			applicationMaster.setNpUserId(request.getNpUserId());
			applicationMaster.setModifiedBy(request.getUserId());
			applicationMaster.setModifiedDate(new Date());
			applicationMaster.setDdrStatusId(CommonUtils.DdrStatus.REVERTED);
			loanApplicationRepository.save(applicationMaster);
			logger.info("exit from revertApplication()");
			return true;
		}
		logger.info("exit from setFPMaker()");
		return false;
	}

}
