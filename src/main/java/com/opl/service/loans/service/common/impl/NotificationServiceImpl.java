package com.opl.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.loans.model.teaser.primaryview.CommonRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.notification.exception.NotificationException;
import com.opl.mudra.api.notification.model.Notification;
import com.opl.mudra.api.notification.model.NotificationRequest;
import com.opl.mudra.api.notification.utils.ContentType;
import com.opl.mudra.api.notification.utils.EmailSubjectAlias;
import com.opl.mudra.api.notification.utils.NotificationMasterAlias;
import com.opl.mudra.api.notification.utils.NotificationType;
import com.opl.mudra.api.user.model.UserResponse;
import com.opl.mudra.api.user.model.UsersRequest;
import com.opl.mudra.client.notification.NotificationClient;
import com.opl.mudra.client.users.UsersClient;
import com.opl.service.loans.service.common.NotificationService;
import com.opl.service.loans.service.fundprovider.ProductMasterService;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.opl.service.loans.utils.CommonDocumentUtils;
import com.opl.service.loans.utils.CommonNotificationUtils.NotificationTemplate;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

	private static final String FP_PNAME_PARAMETERS = "fp_pname";
	private static final String SEND_VIEW_NOTIFICATION = "sendViewNotification";

	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	/*@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;*/
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private Environment environment; 
//	
//	@Autowired
//	private DirectorBackgroundDetailsService directorBackgroundDetailsService;
	
	private static final String EMAIL_ADDRESS_FROM = "com.capitaworld.mail.url";

	
/*	private static Notification createSysNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {
        CommonDocumentUtils.startHook(logger, "createNotification");
        
 		Notification notification = new Notification();
		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);
		CommonDocumentUtils.endHook(logger, "createNotification");
		return notification;

	}*/
	
	@SuppressWarnings("unchecked")
	private Notification createEmailNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,String fpName,Object subjectId,Integer lonaTypeId,Long userOrgId,Long masterId) {
		
        CommonDocumentUtils.startHook(logger, "create Email Notification");
        
        String[] toEmail = new String[toIds.length];
        
        try {
        	for(int i=0;i <toIds.length;i++) {
        		UserResponse toUsersDetails = usersClient.getEmailMobile(Long.valueOf(toIds[i]));
                if (!CommonUtils.isObjectNullOrEmpty(toUsersDetails.getData())) {
					UsersRequest request = MultipleJSONObjectHelper
        					.getObjectFromMap((LinkedHashMap<String, Object>) toUsersDetails.getData(), UsersRequest.class);
        			if(!CommonUtils.isObjectNullOrEmpty(request)) {
        				toEmail[i] = request.getEmail();	
        			}
        		}	
        	}
        } catch(Exception e) {
        	logger.error("Throw exception while get users details for send mail in send notification : ",e);
        }
        if(CommonUtils.isObjectNullOrEmpty(toEmail)) {
        	logger.info("ToEmail Null Or Empty");
        	return null;
        }
        
 		Notification notification = new Notification();
		notification.setTo(toEmail);
		notification.setType(NotificationType.EMAIL);
		notification.setTemplateId(notificationTemplate.getValue());
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setLoanTypeId(lonaTypeId);
		notification.setUserOrgId(userOrgId);
		notification.setMasterId(masterId);
		notification.setFrom(environment.getRequiredProperty(EMAIL_ADDRESS_FROM));
		if(subjectId == null) {
			notification.setSubject(NotificationTemplate.getSubjectName(notificationTemplate.getValue(), fpName));
		}else {
			notification.setSubject(subjectId);
		}
		notification.setEmailSubjectId(EmailSubjectAlias.getFirstAliasByTemplateId(notificationTemplate.getValue()).getSubjectId());
		CommonDocumentUtils.endHook(logger, "create Email Notification");
		return notification;

	}
	

	@Override
	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,
			Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,Long loginUserType,Object subjectId,Integer loanTypeId,Long userOrgId) {

		CommonDocumentUtils.startHook(logger, SEND_VIEW_NOTIFICATION);
		
		if (toUserId != null && fromUserId != null) {
			String[] a = { toUserId };
			NotificationRequest request = new NotificationRequest();
			request.setClientRefId(fromUserId.toString());
			Map<String, Object> parameters = new HashMap<String, Object>();
				try {
					String fsName = loanApplicationService.getFsApplicantName(applicationId);
					parameters.put("fs_name", fsName != null ? fsName : "NA");
					parameters.put("application_id", fsName != null ? applicationId : "NA");

				} catch (Exception e) {
					logger.error("Exception in getting name of fs : ",e);
					parameters.put("fs_name", "NA");
				}
				
				
				Object[] o=productMasterService.getUserDetailsByPrductId(fpProductId);
				
				String fpName = "";
				try {
					if(o!=null) {
						fpName = o[1].toString();
						parameters.put(CommonUtils.PARAMETERS_FP_NAME,fpName);
					} else {
						parameters.put(CommonUtils.PARAMETERS_FP_NAME,"NA");
					}

				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
					parameters.put(CommonUtils.PARAMETERS_FP_NAME, "NA");
				}
				try {
					if(o!=null)
						parameters.put(FP_PNAME_PARAMETERS, o[2].toString());
					else
						parameters.put(FP_PNAME_PARAMETERS, "NA");
				} catch (Exception e) {
					logger.error(CommonUtils.EXCEPTION,e);
					parameters.put(FP_PNAME_PARAMETERS, "NA");
				}
				request.addNotification(createEmailNotification(a, fromUserId, fromUserTypeId,notificationId, parameters, applicationId, fpProductId,
						notificationTemplate,fpName,subjectId,loanTypeId,userOrgId,NotificationMasterAlias.EMAIL_PRIMARY_VIEW_FS.getMasterId()));
			try {
				notificationClient.send(request);
				logger.info("Successfully sent notification and email for primary or final view");
			} catch (NotificationException e) {
				logger.error(CommonUtils.EXCEPTION,e);
			}
			CommonDocumentUtils.endHook(logger, SEND_VIEW_NOTIFICATION);
		}
		
	}
	/** not in use any more*/
	@Override
	public void createEmailNotificationForUBI(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId, Long fromUserId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,String fpName,Integer loanTypeId,Long userOrgId,Long masterId){
		NotificationRequest request = new NotificationRequest();
		request.setClientRefId(fromUserId.toString());
      		request.addNotification(createEmailNotification(toIds, fromUserId, fromUserTypeId,templateId, parameters, applicationId, fpProductId,notificationTemplate,fpName,null,loanTypeId,userOrgId,masterId));
		try {
			notificationClient.send(request);
			logger.info("Successfully sent notification and email for primary or final view");
		} catch (NotificationException e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
		CommonDocumentUtils.endHook(logger, SEND_VIEW_NOTIFICATION);
	}

	@Override
	public CommonRequest extractArrayToCommonRequest(Object[] obj) throws Exception {
		CommonRequest request=new CommonRequest();
		request.setApplicationId(obj[0] != null ?Long.valueOf(String.valueOf(obj[0])):null);
		request.setUserId(obj[1] != null ? Long.valueOf(String.valueOf(obj[1])):null);
		request.setProposalId(obj[2] != null?Long.valueOf(String.valueOf(obj[2])):null);
		request.setFpProductId(obj[3] != null ? Long.valueOf(String.valueOf(obj[3])):null);
		request.setLoanTypeId(obj[4]!= null?Integer.valueOf(String.valueOf(obj[4])):null);
		request.setEmailId(obj[5] != null ? String.valueOf(obj[5]):null);
		request.setMobile(obj[6] != null ? String.valueOf(obj[6]):null);
		request.setUserOrgId(obj[7] != null ? Long.valueOf(String.valueOf(obj[7])):null);
		return request;
	} 

}
