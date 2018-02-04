package com.capitaworld.service.loans.service.networkpartner.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.dms.client.DMSClient;
import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.dms.model.DocumentRequest;
import com.capitaworld.service.dms.model.DocumentResponse;
import com.capitaworld.service.dms.util.DocumentAlias;
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
	
	@Override
	public List<NhbsApplicationsResponse> getListOfProposals(NhbsApplicationRequest request) {
		logger.info("entry in getListOfProposals()");
		List<LoanApplicationMaster> applicationMastersList = new ArrayList<LoanApplicationMaster>();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getProposalsByApplicationStatus(request.getApplicationStatusId());
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getProposalsByDdrStatus(request.getDdrStatusId());
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
						nhbsApplicationsResponse.setMakerName(networkPartnerDetailsRequest.getFirstName() + " " + networkPartnerDetailsRequest.getLastName() == null ? "": networkPartnerDetailsRequest.getLastName());
					} catch (Exception e) {
						logger.error("error while fetching network partner details");
						e.printStackTrace();
					}
					
				}
				CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if(applicantDetail != null){
					nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());	
				}
				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(loanApplicationMaster.getId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
					nhbsApplicationsResponse.setClientProfilePic(documentResponse.getDataList());
				} catch (DocumentException e) {
					logger.error("error while getting profile image");
					e.printStackTrace();
				}
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == request.getUserRoleId()){
					nhbsApplicationsResponse.setApplicationDate(loanApplicationMaster.getCreatedDate());
					try{
						UserResponse userResponse = usersClient.checkUserUnderSp(loanApplicationMaster.getUserId());
						if(!CommonUtils.isObjectNullOrEmpty(userResponse) && userResponse.getStatus() == 200 && userResponse.getData().equals("true")){
							nhbsApplicationsResponse.setClientSource("SP");	
						}else{
							nhbsApplicationsResponse.setClientSource("Direct");
						}
					}catch (Exception e) {
						logger.error("error while calling users clients while calling checkUserUnderSp()");
						e.printStackTrace();
					}
				}
				nhbsApplicationsResponseList.add(nhbsApplicationsResponse);
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
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByAssigneeId(request.getApplicationStatusId(), request.getUserId());	
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
			applicationMastersList = loanApplicationRepository.getAssignedProposalsByNpUserId(request.getApplicationStatusId(), request.getUserId());
		}else{
			applicationMastersList = null;
		}
		
		List<NhbsApplicationsResponse> nhbsApplicationsResponseList = new ArrayList<NhbsApplicationsResponse>();
		if(!CommonUtils.isListNullOrEmpty(applicationMastersList)){
			for (LoanApplicationMaster loanApplicationMaster : applicationMastersList) {
				NhbsApplicationsResponse nhbsApplicationsResponse = new NhbsApplicationsResponse();
				nhbsApplicationsResponse.setApplicationType(loanApplicationMaster.getProductId());
				nhbsApplicationsResponse.setUserId(loanApplicationMaster.getUserId());
				nhbsApplicationsResponse.setApplicationId(loanApplicationMaster.getId());
				nhbsApplicationsResponse.setDdrStatus(null);//need to set the ddr status 
				CorporateApplicantDetail applicantDetail = corpApplicantRepository.getByApplicationAndUserId(loanApplicationMaster.getUserId(), loanApplicationMaster.getId());
				if(applicantDetail != null){
					nhbsApplicationsResponse.setClientName(applicantDetail.getOrganisationName());	
				}
				// get profile pic
				DocumentRequest documentRequest = new DocumentRequest();
				documentRequest.setApplicationId(loanApplicationMaster.getId());
				documentRequest.setUserType(DocumentAlias.UERT_TYPE_APPLICANT);
				documentRequest.setProductDocumentMappingId(CommonDocumentUtils.getProductDocumentId(loanApplicationMaster.getProductId()));
				try {
					DocumentResponse documentResponse = dmsClient.listProductDocument(documentRequest);
					nhbsApplicationsResponse.setClientProfilePic(documentResponse.getDataList());
				} catch (DocumentException e) {
					logger.error("error while getting profile image");
					e.printStackTrace();
				}
				if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == request.getUserRoleId()){
					nhbsApplicationsResponse.setPaymentMode(null);//need to set
					nhbsApplicationsResponse.setIsPaymentDone(null);//need to set
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
						nhbsApplicationsResponse.setApplicationDate(applicationMastersList.get(0).getModifiedDate());
					}
					if(!CommonUtils.isObjectNullOrEmpty(loanApplicationMaster.getIsFinalLocked())){
						nhbsApplicationsResponse.setIsOneFormFilled(loanApplicationMaster.getIsFinalLocked());	
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
		}else{
			nhbsApplicationsResponseList = null;
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
	public JSONObject getNhbsProposalCount(NhbsApplicationRequest nhbsApplicationRequest) {
		logger.info("entry in getNhbsProposalCount()");
		JSONObject countObj = new JSONObject();
		if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.MAKER == nhbsApplicationRequest.getUserRoleId()){
			int allotedPropsalCount = loanApplicationRepository.getCountOfAssignedProposalsByNpUserId(CommonUtils.ApplicationStatus.ASSIGNED, nhbsApplicationRequest.getUserId());
			countObj.put("allotedPropsalCount", allotedPropsalCount);
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.CHECKER == nhbsApplicationRequest.getUserRoleId()){
			int newPropsalCount = loanApplicationRepository.getCountOfProposalsByApplicationStatus(CommonUtils.ApplicationStatus.OPEN);
			int assignedPropsalCount = loanApplicationRepository.getCountOfAssignedProposalsByAssigneeId(CommonUtils.ApplicationStatus.ASSIGNED, nhbsApplicationRequest.getUserId());
			countObj.put("newProposals", newPropsalCount);
			countObj.put("assignedProposals", assignedPropsalCount);
		}else if(com.capitaworld.service.users.utils.CommonUtils.UserRoles.APPROVER == nhbsApplicationRequest.getUserRoleId()){
			int ddrSubmittedToApproverCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.SUBMITTED_TO_APPROVER);
			int ddrApprovedCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.APPROVED);
			int ddrRevertedCount = loanApplicationRepository.getCountOfProposalsByDdrStatus(CommonUtils.DdrStatus.REVERTED);
			countObj.put("ddrSubmittedToApproverCount", ddrSubmittedToApproverCount);
			countObj.put("ddrApprovedCount", ddrApprovedCount);
			countObj.put("ddrRevertedCount", ddrRevertedCount);
		}
		logger.info("exit from getNhbsProposalCount()");
		return countObj;
	}

}
