package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundseeker.IneligibleProposalDetails;
import com.capitaworld.service.loans.domain.fundseeker.corporate.PrimaryCorporateDetail;
import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.InEligibleProposalDetailsRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.repository.fundseeker.IneligibleProposalDetailsRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.PrimaryCorporateDetailRepository;
import com.capitaworld.service.loans.service.common.IneligibleProposalDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.ProposalDetailsClient;
import com.capitaworld.service.matchengine.model.ProposalMappingResponse;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationAlias;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.oneform.client.OneFormClient;
import com.capitaworld.service.oneform.enums.PurposeOfLoan;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.BranchBasicDetailsRequest;
import com.capitaworld.service.users.model.UserOrganisationRequest;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

/**
 * Created by KushalCW on 22-09-2018.
 */

@Service
@Transactional
public class IneligibleProposalDetailsServiceImpl implements IneligibleProposalDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(IneligibleProposalDetailsServiceImpl.class);

	@Autowired
	private IneligibleProposalDetailsRepository ineligibleProposalDetailsRepository;

	@Autowired
	private UsersClient userClient;

	@Autowired
	private OneFormClient oneFormClient;

	@Autowired
	LoanApplicationService loanApplicationService;

	@Autowired
	private NotificationClient notificationClient;

	@Autowired
	private ProposalDetailsClient proposalDetailsClient;

	@Autowired
	DirectorBackgroundDetailsService directorBackgroundDetailsService;

	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private PrimaryCorporateDetailRepository primaryCorporateDetailRepository;

	private static final String EMAIL_ADDRESS_FROM = "no-reply@capitaworld.com";

	@Override
	public Boolean save(InEligibleProposalDetailsRequest inEligibleProposalDetailsRequest) {
		try {
			IneligibleProposalDetails ineligibleProposalDetails = new IneligibleProposalDetails();
			BeanUtils.copyProperties(inEligibleProposalDetailsRequest, ineligibleProposalDetails);
			ineligibleProposalDetailsRepository.save(ineligibleProposalDetails);
			return true;
		} catch (Exception e) {
			logger.error("error while saving in eligible proposal");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean sendMailToFsAndBankBranch(Long applicationId, Long branchId, Long userOrgId) {
		boolean isSent = false;
		if (applicationId != null && branchId != null && userOrgId != null) {
			try {
				Map<String, Object> notificationParams = new HashMap<>();
				// Sending mail to FS who become Ineligible
//			 1 Get Details of FS_NAME,Bank name, Branch name and Address based on application Id
				LoanApplicationRequest applicationRequest = null;
				try {
					applicationRequest = loanApplicationService.getFromClient(applicationId);
				} catch (Exception e1) {
					logger.info("Exception in getting :" + e1);
					e1.printStackTrace();
				}
				// For getting Fund Seeker's Name
				if (applicationRequest != null) {
					notificationParams = getFsNameFromDirectorBeckgroundDetailsForNTBandExisting(applicationId,
							applicationRequest);
					notificationParams = getBankAndBranchDetails(userOrgId, branchId, notificationParams);

					UserResponse response = null;
					UsersRequest signUpUser = null;
					try {
						response = userClient.getEmailMobile(applicationRequest.getUserId());
					} catch (Exception e) {
						logger.info("Something went wrong while calling Users client from sending mail to fs===>{}");
						e.printStackTrace();
					}
					if (!CommonUtils.isObjectNullOrEmpty(response)) {
						try {
							signUpUser = MultipleJSONObjectHelper
									.getObjectFromMap((Map<String, Object>) response.getData(), UsersRequest.class);
						} catch (Exception e) {
							logger.info("Exception getting signup user at Sending email to fs and bank branch");
							e.printStackTrace();
						}
					}
					//==================For getting Organisation Name================================= 
					
					UserResponse userResponse = null;
					Map<String, Object> usersResp = null;
					UserOrganisationRequest organisationRequest = null;
					String organisationName = null;
					try {
						userResponse = userClient.getOrgNameByOrgId(Long.valueOf(userOrgId.toString()));	
					}
					catch(Exception e) {
						logger.info("Exception occured while getting Organisation details by orgId");
						e.printStackTrace();
					}
                    
					try {
						if(!CommonUtils.isObjectNullOrEmpty(userResponse)) {
							usersResp = (Map<String, Object>) userResponse.getData();
							organisationRequest = MultipleJSONObjectHelper.getObjectFromMap(usersResp,
									UserOrganisationRequest.class);
							organisationName = organisationRequest.getOrganisationName(); 
						}
						
					}
					catch(Exception e) {
						logger.info("Exception occured while getting Organisation details by orgId");
						e.printStackTrace();
						
					}
					
					//================================================================================
					notificationParams.put("bank_name", organisationName != null ? organisationName : "NA");

					String subject = "Manual Application : " + applicationId.toString();
					createNotificationForEmail(signUpUser.getEmail(), applicationRequest.getUserId().toString(),
							notificationParams, NotificationAlias.EMAIL_FS_WHEN_IN_ELIGIBLE, subject);

					// ===========================================================================================
					// 2nd email Step2 Get Details of Bank branch --- Sending mail to Branch
					// Checker/Maker/BO
					// ============================================================================================
					Map<String, Object> mailParameters = new HashMap<String, Object>();
					subject = "Manual Application : " + applicationId.toString();

					mailParameters.put("fs_name",
							notificationParams.get("fs_name") != null ? notificationParams.get("fs_name") : "NA");
					mailParameters.put("mobile_no", signUpUser.getMobile() != null ? signUpUser.getMobile() : "NA");
					mailParameters.put("address",
							notificationParams.get("address") != null ? notificationParams.get("address") : "NA");
				
					//=========================For getting Loan Type===================================
					PrimaryCorporateDetail primaryCorporateDetail = primaryCorporateDetailRepository.findOneByApplicationIdId(applicationId);
					if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail)){
						if(!CommonUtils.isObjectNullOrEmpty(primaryCorporateDetail.getPurposeOfLoanId())){
							String loanType = PurposeOfLoan.getById(primaryCorporateDetail.getPurposeOfLoanId()).getValue();
							if("Asset Acquisition".equals(loanType)){
								mailParameters.put("loan_type","Term Loan");	
							}
							else{
								mailParameters.put("loan_type",loanType!=null?loanType:"NA");
							}	
						}
						else{
							mailParameters.put("loan_type","NA");
						}
						mailParameters.put("loan_amount",primaryCorporateDetail.getLoanAmount()!=null?primaryCorporateDetail.getLoanAmount().toString():"NA");
					}
					else{
						mailParameters.put("loan_type","NA");
						mailParameters.put("loan_amount","NA");
					}
					//=================================================================================

					List<Map<String, Object>> usersRespList = null;
					String to = null;
					userResponse = userClient.getUserDetailByOrgRoleBranchId(userOrgId,
							com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_MAKER, branchId);
					usersRespList = (List<Map<String, Object>>) userResponse.getListData();
					if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
						for (int i = 0; i < usersRespList.size(); i++) {
							UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
									UsersRequest.class);
							if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
								// System.out.println("Checker ID:---"+userObj.getEmail());
								to = userObj.getEmail();

								createNotificationForEmail(to, applicationRequest.getUserId().toString(),
										mailParameters, NotificationAlias.EMAIL_BRANCH_FS_WHEN_IN_ELIGIBLE, subject);
							}
						}

					} else {
						logger.info("No Maker found=================>");
					}

					userResponse = userClient.getUserDetailByOrgRoleBranchId(userOrgId,
							com.capitaworld.service.users.utils.CommonUtils.UserRoles.FP_CHECKER, branchId);
					usersRespList = (List<Map<String, Object>>) userResponse.getListData();
					if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
						for (int i = 0; i < usersRespList.size(); i++) {
							UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
									UsersRequest.class);
							if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
								// System.out.println("Checker ID:---"+userObj.getEmail());
								to = userObj.getEmail();
								createNotificationForEmail(to, applicationRequest.getUserId().toString(),
										mailParameters, NotificationAlias.EMAIL_BRANCH_FS_WHEN_IN_ELIGIBLE, subject);
							}
						}

					} else {
						logger.info("No Checker found=================>");
					}
					userResponse = userClient.getUserDetailByOrgRoleBranchId(userOrgId,
							com.capitaworld.service.users.utils.CommonUtils.UserRoles.BRANCH_OFFICER, branchId);
					usersRespList = (List<Map<String, Object>>) userResponse.getListData();
					if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
						for (int i = 0; i < usersRespList.size(); i++) {
							UsersRequest userObj = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
									UsersRequest.class);
							if (!CommonUtils.isObjectNullOrEmpty(userObj.getEmail())) {
								// System.out.println("Checker ID:---"+userObj.getEmail());
								to = userObj.getEmail();
								createNotificationForEmail(to, applicationRequest.getUserId().toString(),
										mailParameters, NotificationAlias.EMAIL_BRANCH_FS_WHEN_IN_ELIGIBLE, subject);
							}
						}

					} else {
						logger.info("No BO found=================>");
					}
				}
				isSent = true;
			} catch (Exception e) {
				logger.info(
						"Exception in sending email to fs and bank branch when ineligible from IneligibleProposalDetailsServiceImpl : "
								+ e);
				isSent = false;
			}
			return isSent;
		} else {
			return isSent;
		}
	}

	private Map<String, Object> getBankAndBranchDetails(Long userOrgId, Long branchId, Map<String, Object> notificationParams) {
		
		UserResponse userResponse = null;
		String address = null;
		String premiseNo = null;
		String streetName = null;
		String landMark = null;
		String state = null;
		String city = null;
		String pinCode;
		try {
			if (!CommonUtils.isObjectNullOrEmpty(branchId)) {
				userResponse = userClient.getBranchDataBasedOnOrgAndBranchId(userOrgId, branchId);
				List<Map<String, Object>> usersRespList = null;
				if (!CommonUtils.isObjectNullOrEmpty(userResponse))
					usersRespList = (List<Map<String, Object>>) userResponse.getListData();
				if (!CommonUtils.isObjectNullOrEmpty(usersRespList)) {
					for (int i = 0; i < usersRespList.size(); i++) {
						BranchBasicDetailsRequest resp = MultipleJSONObjectHelper.getObjectFromMap(usersRespList.get(i),
								BranchBasicDetailsRequest.class);
						if (!CommonUtils.isObjectNullOrEmpty(resp)) {
							notificationParams.put("branch_name", resp.getName() != null ? resp.getName() : "-");
							notificationParams.put("branch_code", resp.getCode() != null ? resp.getCode() : "-");

							premiseNo = resp.getPremisesNo() != null ? resp.getPremisesNo() : " ";
							streetName = resp.getStreetName() != null ? resp.getStreetName() : " ";
							landMark = resp.getLandMark() != null ? resp.getLandMark() : " ";

							if (!CommonUtils.isObjectNullOrEmpty(resp.getPincode())) {
								pinCode = ", " + Integer.toString(resp.getPincode());
							} else {
								pinCode = ", " + " ";
							}
							if (!CommonUtils.isObjectNullOrEmpty(resp.getStateId())) {
								try {
									state = CommonDocumentUtils.getState(Long.valueOf(resp.getStateId().toString()),
											oneFormClient);
								} catch (Exception e) {
									logger.info("Error while calling One form client for getting State");
									state = " ";
									e.printStackTrace();
								}
								state = state != null ? state : " ";
							} else {
								state = " ";
							}
							if (!CommonUtils.isObjectNullOrEmpty(resp.getCityId())) {
								try {
									city = CommonDocumentUtils.getCity(Long.valueOf(resp.getCityId().toString()),
											oneFormClient);
								} catch (Exception e) {
									logger.info("Error while calling One form client for getting City");
									city = " ";
									e.printStackTrace();
								}
								city = city != null ? city : " ";
							} else {
								city = " ";
							}
							address = premiseNo.toString() + ", " + streetName.toString() + ", " + landMark.toString()
									+ ", " + state.toString() + ", " + city.toString();
							address = address + pinCode;
							notificationParams.put("branch_address", address != null ? address : "-");
							notificationParams.put("branch_contact",
									resp.getContactPersonNumber() != null ? resp.getContactPersonNumber() : "-");
						} else {
							notificationParams.put("branch_name", "-");
							notificationParams.put("branch_code", "-");
							notificationParams.put("ifsc_code", "-");
							notificationParams.put("branch_address", "-");
							notificationParams.put("branch_contact", "-");
						}
					}
				}
			} else {
				notificationParams.put("branch_name", "-");
				notificationParams.put("branch_code", "-");
				notificationParams.put("ifsc_code", "-");
				notificationParams.put("branch_address", "-");
				notificationParams.put("branch_contact", "-");
			}
			return notificationParams;
		} catch (Exception e) {
			logger.info("Error while calling User's client for getting Branch Details");
			notificationParams.put("branch_name", "-");
			notificationParams.put("branch_code", "-");
			notificationParams.put("ifsc_code", "-");
			notificationParams.put("branch_address", "-");
			notificationParams.put("branch_contact", "-");
			e.printStackTrace();
			return notificationParams;
		}
	}

	private Map<String, Object> getFsNameFromDirectorBeckgroundDetailsForNTBandExisting(Long applicationId,
			LoanApplicationRequest applicationRequest) {
		Map<String, Object> notificationParams = new HashMap();
		String fsName = null;
		String address = null;
		List<DirectorBackgroundDetailRequest> NTBResponse = null;
		if (applicationRequest.getBusinessTypeId() == CommonUtils.BusinessType.NEW_TO_BUSINESS.getId()) {
			try {
				NTBResponse = directorBackgroundDetailsService.getDirectorBasicDetailsListForNTB(applicationId);
			} catch (Exception e) {
				e.printStackTrace();
				logger.info("Exception in  geting details of user in ntb:" + e);
			}
			if (!CommonUtils.isObjectNullOrEmpty(NTBResponse)) {
				int isMainDirector = 0;
				for (DirectorBackgroundDetailRequest director : NTBResponse) {
					if (!CommonUtils.isObjectNullOrEmpty(director) && director.getIsMainDirector()) {
						fsName = director.getDirectorsName() != null ? director.getDirectorsName() : "NA";
						notificationParams.put("fs_name", fsName);
						notificationParams.put("address", director.getAddress() != null ? director.getAddress() : "NA");
						isMainDirector = 1;
					}
				}
				if (isMainDirector == 0) {
					fsName = NTBResponse.get(0).getDirectorsName() != null ? NTBResponse.get(0).getDirectorsName()
							: "NA";
					notificationParams.put("fs_name", fsName != null ? fsName : "NA");
					notificationParams.put("address",
							NTBResponse.get(0).getAddress() != null ? NTBResponse.get(0).getAddress() : "NA");
				}
			} else {
				notificationParams.put("fs_name", fsName != null ? fsName : "NA");
				notificationParams.put("address", "NA");
			}
			return notificationParams;
		} else {
			fsName = applicationRequest.getUserName() != null ? applicationRequest.getUserName() : "NA";
			notificationParams.put("fs_name", fsName);
			if (applicationRequest.getBusinessTypeId() == CommonUtils.BusinessType.EXISTING_BUSINESS.getId()) {
				CorporateApplicantRequest applicantRequest = corporateApplicantService
						.getCorporateApplicant(applicationId);
				if (!CommonUtils.isObjectNullOrEmpty(applicantRequest)
						&& !CommonUtils.isObjectNullOrEmpty(applicantRequest.getFirstAddress())) {
			
					String premiseNumber = null;
					String streetName = null;
					String landMark = null;
					premiseNumber = applicantRequest.getFirstAddress().getPremiseNumber()!=null?applicantRequest.getFirstAddress().getPremiseNumber():"";
					streetName = applicantRequest.getFirstAddress().getStreetName()!=null?applicantRequest.getFirstAddress().getStreetName():"";
					landMark = applicantRequest.getFirstAddress().getLandMark()!=null?applicantRequest.getFirstAddress().getLandMark():"";
					address = premiseNumber.toString()+" "+streetName.toString()+" "+landMark.toString();
					
					
					notificationParams.put("address", address != null ? address : "NA");
				}
			}
			return notificationParams;
		}
	}

	private void createNotificationForEmail(String toNo, String userId, Map<String, Object> mailParameters,
			Long templateId, String emailSubject) throws NotificationException {
		logger.info("Inside send notification===>{}" + toNo);
		NotificationRequest notificationRequest = new NotificationRequest();
		notificationRequest.setClientRefId(userId);
		String to[] = { toNo };
		Notification notification = new Notification();
		notification.setContentType(ContentType.TEMPLATE);
		notification.setTemplateId(templateId);
		notification.setSubject(emailSubject);
		notification.setTo(to);
		notification.setType(NotificationType.EMAIL);
		notification.setFrom(EMAIL_ADDRESS_FROM);
		notification.setParameters(mailParameters);
		notificationRequest.addNotification(notification);
		sendEmail(notificationRequest);
		logger.info("Outside send notification===>{}" + toNo);
	}

	private void sendEmail(NotificationRequest notificationRequest) throws NotificationException {
		logger.info("Inside send Email===>{}");
		notificationClient.send(notificationRequest);
		logger.info("Outside send Email===>{}");
	}
}
