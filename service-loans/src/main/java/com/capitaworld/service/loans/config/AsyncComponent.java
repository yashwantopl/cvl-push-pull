package com.capitaworld.service.loans.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationType;
import com.capitaworld.service.users.client.UsersClient;
import com.capitaworld.service.users.model.UserResponse;
import com.capitaworld.service.users.model.UsersRequest;

@Component
public class AsyncComponent {
	private static final Logger logger = LoggerFactory.getLogger(AsyncComponent.class.getName());

	@Autowired
	private Environment environment;
	
	@Autowired
	private UsersClient usersClient;
	
	@Autowired
	private LoanApplicationService loanApplicationService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	private static final String EMAIL_ADDRESS_FROM = "com.capitaworld.mail.url";
	
	@SuppressWarnings("unchecked")
	@Async
	public void sendMailWhenUserHasNoApplication(Long userId){
		logger.info("Enter in sending mail when user has no application");
		try {
			Long totalApplication = loanApplicationService.getTotalUserApplication(userId);
			if(totalApplication > 0) {
				logger.info("Exits,User has more then one application");
				return;
			}
			logger.info("Call user client for get email and name by user id");
			UserResponse userResponse = usersClient.getEmailAndNameByUserId(userId);
			if (!CommonUtils.isObjectNullOrEmpty(userResponse.getData())) {
				UsersRequest request = MultipleJSONObjectHelper
    					.getObjectFromMap((LinkedHashMap<String, Object>) userResponse.getData(), UsersRequest.class);
    			if(!CommonUtils.isObjectNullOrEmpty(request)) {
    				NotificationRequest notificationRequest = new NotificationRequest();
    				notificationRequest.setClientRefId(userId.toString());
    				//GET EMAIL TEMPLATE 
    				NotificationTemplate template = NotificationTemplate.LOGOUT_IMMEDIATELY;
    				//SET EMAIL PARAMETER 
    				Map<String, Object> parameters = new HashMap<String, Object>();
    				parameters.put("fs_name", request.getName());
    				String[] toIds = {request.getEmail()};
    				//MAKE NOTIFICATION OBJECT
    				Notification notification = new Notification();
    				notification.setTo(toIds);
    				notification.setType(NotificationType.EMAIL);
    				notification.setTemplateId(template.getValue());
    				notification.setContentType(ContentType.TEMPLATE);
    				notification.setParameters(parameters);
    				notification.setFrom(environment.getRequiredProperty(EMAIL_ADDRESS_FROM));
    				notification.setSubject(template.getSubject());
    				notificationRequest.addNotification(notification);
    				//SEND MAIL
    				notificationClient.send(notificationRequest);
    				logger.info("Exits, Successfully sent mail when user has no application ---->"+request.getEmail());
    			}
    		}
		} catch(Exception e) {
			logger.info("Throw exception while sending mail, logout immediately");
			e.printStackTrace();
		}
	}
}
