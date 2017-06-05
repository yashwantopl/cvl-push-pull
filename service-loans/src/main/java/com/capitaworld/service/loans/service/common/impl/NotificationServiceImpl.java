package com.capitaworld.service.loans.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.service.common.NotificationService;
import com.capitaworld.service.loans.service.fundprovider.ProductMasterService;
import com.capitaworld.service.loans.service.fundseeker.corporate.LoanApplicationService;
import com.capitaworld.service.matchengine.utils.MatchConstant;
import com.capitaworld.service.notification.client.NotificationClient;
import com.capitaworld.service.notification.exceptions.NotificationException;
import com.capitaworld.service.notification.model.Notification;
import com.capitaworld.service.notification.model.NotificationRequest;
import com.capitaworld.service.notification.utils.ContentType;
import com.capitaworld.service.notification.utils.NotificationType;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
	
	@Autowired
	LoanApplicationService loanApplicationService;
	
	@Autowired
	ProductMasterService productMasterService;
	
	@Autowired
	private NotificationClient notificationClient;
	
	private static Notification createNotification(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Map<String, Object> parameters, Long applicationId, Long fpProductId) {

 		Notification notification = new Notification();

		notification.setTo(toIds);
		notification.setType(NotificationType.SYSTEM);
		notification.setTemplateId(templateId);
		notification.setContentType(ContentType.TEMPLATE);
		notification.setParameters(parameters);
		notification.setFrom(fromId.toString());
		notification.setProductId(fpProductId);
		notification.setApplicationId(applicationId);

		return notification;

	}

	@Override
	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,
			Long applicationId, Long fpProductId) {
		// TODO Auto-generated method stub
		
		
		if (toUserId != null && fromUserId != null) {
			String[] a = { toUserId.toString() };
			NotificationRequest request = new NotificationRequest();
			request.setClientRefId(fromUserId.toString());
			Map<String, Object> parameters = new HashMap<String, Object>();
			if (MatchConstant.UserType.FUNDSEEKER == fromUserTypeId) {
				try {
					Object o[]=loanApplicationService.getApplicationDetailsById(applicationId);
					if(o!=null)
						parameters.put("name", o[1].toString());
					else
						parameters.put("name", "NA");

				} catch (Exception e) {
					// TODO: handle exception
					parameters.put("name", "NA");
				}
				request.addNotification(createNotification(a, fromUserId, fromUserTypeId,
						notificationId, parameters, applicationId, fpProductId));
			} else {
				try {
					Object o[]=productMasterService.getUserDetailsByPrductId(fpProductId);
					if(o!=null)
						parameters.put("name",o[1].toString());
					else
						parameters.put("name","NA");
					

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace(); 
					parameters.put("name", "NA");
				}
				request.addNotification(createNotification(a, fromUserId, fromUserTypeId,notificationId, parameters, applicationId, fpProductId));
			}

			try {
				notificationClient.send(request);
			} catch (NotificationException e) {
				e.printStackTrace();
			}
		}
		
	}

}
