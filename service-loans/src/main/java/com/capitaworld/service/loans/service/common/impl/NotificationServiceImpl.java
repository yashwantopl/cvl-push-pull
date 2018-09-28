package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.model.DirectorBackgroundDetailRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailApplicantRequest;
import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.capitaworld.service.loans.service.fundseeker.corporate.DirectorBackgroundDetailsService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.service.fundseeker.retail.RetailApplicantService;
import com.capitaworld.service.loans.utils.CommonDocumentUtils;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	private static final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private ProductMasterService productMasterService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private RetailApplicantService retailApplicantService;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private Environment environment; 
	
	@Autowired
	private DirectorBackgroundDetailsService directorBackgroundDetailsService;
	
	private static final String EMAIL_ADDRESS_FROM = "com.capitaworld.mail.url";

	
	private static Notification createSysNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
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

	}
	
	private Notification createEmailNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,String fpName) {
		
        CommonDocumentUtils.startHook(logger, "create Email Notification");
        
        String[] toEmail = new String[toIds.length];
        
        try {
        	for(int i=0;i <toIds.length;i++) {
        		UserResponse toUsersDetails = usersClient.getEmailMobile(Long.valueOf(toIds[i].toString()));
                if (!CommonUtils.isObjectNullOrEmpty(toUsersDetails.getData())) {
        			UsersRequest request = MultipleJSONObjectHelper
        					.getObjectFromMap((LinkedHashMap<String, Object>) toUsersDetails.getData(), UsersRequest.class);
        			if(!CommonUtils.isObjectNullOrEmpty(request)) {
        				toEmail[i] = request.getEmail();	
        			}
        		}	
        	}
        } catch(Exception e) {
        	logger.info("Throw exception while get users details for send mail in send notification");
        	e.printStackTrace();
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
		notification.setFrom(environment.getRequiredProperty(EMAIL_ADDRESS_FROM));
		notification.setSubject(NotificationTemplate.getSubjectName(notificationTemplate.getValue(), fpName));
		CommonDocumentUtils.endHook(logger, "create Email Notification");
		return notification;

	}
	

	@Override
	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,
			Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,Long loginUserType) {
		// TODO Auto-generated method stub
		CommonDocumentUtils.startHook(logger, "sendViewNotification");
		
		if (toUserId != null && fromUserId != null) {
			String[] a = { toUserId.toString() };
			NotificationRequest request = new NotificationRequest();
			request.setClientRefId(fromUserId.toString());
			Map<String, Object> parameters = new HashMap<String, Object>();
				try {
					int fsProdId;
					if(CommonUtils.UserType.FUND_SEEKER == fromUserTypeId)
						fsProdId =loanApplicationService.getProductIdByApplicationId(applicationId, fromUserId);
					else
						fsProdId =loanApplicationService.getProductIdByApplicationId(applicationId, Long.parseLong(toUserId));
					
					int fsType = CommonUtils.getUserMainType(fsProdId);
					String fsName = loanApplicationService.getFsApplicantName(applicationId);
					parameters.put("fs_name", fsName != null ? fsName : "NA");
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("Exception in getting name of fs:"+e);
					e.printStackTrace();
					parameters.put("fs_name", "NA");
				}
				
				
				
				Object o[]=productMasterService.getUserDetailsByPrductId(fpProductId);
				
				String fpName = "";
				try {
					if(o!=null) {
						fpName = o[1].toString();
						parameters.put("fp_name",fpName);
					} else {
						parameters.put("fp_name","NA");
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					parameters.put("fp_name", "NA");
				}
				try {
					if(o!=null)
						parameters.put("fp_pname", o[2].toString());
					else
						parameters.put("fp_pname", "NA");
				} catch (Exception e) {
					// TODO: handle exception
					logger.info("Exception :"+e);
					parameters.put("fp_pname", "NA");
				}
				request.addNotification(createEmailNotification(a, fromUserId, fromUserTypeId,notificationId, parameters, applicationId, fpProductId,notificationTemplate,fpName));
			try {
				notificationClient.send(request);
				logger.info("Successfully sent notification and email for primary or final view");
			} catch (NotificationException e) {
				e.printStackTrace();
			}
			CommonDocumentUtils.endHook(logger, "sendViewNotification");
		}
		
	}
	
	@Override
	public void createEmailNotificationForUBI(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId, Long fromUserId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId,NotificationTemplate notificationTemplate,String fpName){
		NotificationRequest request = new NotificationRequest();
		request.setClientRefId(fromUserId.toString());
		request.addNotification(createEmailNotification(toIds, fromUserId, fromUserTypeId,templateId, parameters, applicationId, fpProductId,notificationTemplate,fpName));
		try {
			notificationClient.send(request);
			logger.info("Successfully sent notification and email for primary or final view");
		} catch (NotificationException e) {
			e.printStackTrace();
		}
		CommonDocumentUtils.endHook(logger, "sendViewNotification");
	} 

}
