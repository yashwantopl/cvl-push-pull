package com.capitaworld.service.loans.service.common;

public interface NotificationService {

	public void sendViewNotification(String toUserId, Long fromUserId, Long fromUserTypeId, Long notificationId,Long applicationId,Long fpProductId);
	
}
