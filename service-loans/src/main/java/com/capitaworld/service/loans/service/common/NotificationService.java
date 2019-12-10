package com.capitaworld.service.loans.service.common;

import java.util.Map;

import com.capitaworld.service.loans.model.teaser.primaryview.CommonRequest;
import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;

public interface NotificationService {

	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,Long applicationId,Long fpProductId, NotificationTemplate notificationTemplate,Long loginUserType,Object subject,Integer loanTypeId,Long productId);

	public void createEmailNotificationForUBI(String[] toIds, Long fromId, Long fromUserTypeId, Long templateId,
			Long fromUserId, Map<String, Object> parameters, Long applicationId, Long fpProductId,
			NotificationTemplate notificationTemplate, String fpName,Integer loanTypeId,Long userOrgId,Long masterId);
	
	public CommonRequest extractArrayToCommonRequest(Object[] obj) throws Exception;
	
}
