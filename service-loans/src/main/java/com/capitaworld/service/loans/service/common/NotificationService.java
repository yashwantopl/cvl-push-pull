package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.utils.CommonNotificationUtils.NotificationTemplate;

public interface NotificationService {

	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,Long applicationId,Long fpProductId, NotificationTemplate notificationTemplate,Long loginUserType);
	
}
