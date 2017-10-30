package com.capitaworld.service.loans.utils;



import com.capitaworld.service.notification.utils.NotificationAlias;

public class CommonNotificationUtils {
	
	public enum NotificationTemplate {
		PRIMARY_VIEW(NotificationAlias.EMAIL_PRIMARY_VIEW_FS," viewed your teaser",true),
		FINAL_VIEW(NotificationAlias.EMAIL_FINAL_VIEW_FS," viewed your final details",true),
		LOGOUT_IMMEDIATELY(NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY,"Reviews",false),
		PRIMARY_FILL_COMPLETE(NotificationAlias.EMAIL_FS_PROFILE_PRIMARY_COMPLETE,"Your Matches",false),
		FP_VIEW_MORE_DETAILS(NotificationAlias.EMAIL_FS_FP_VIEW_MORE_DETAILS," requested for more details",true);
		
		private Long value;
		private String subject;
		private boolean isSubjConfig;

		private NotificationTemplate(Long value,String subject, boolean isSubjConfig) {
			this.value = value;
			this.subject = subject;
			this.isSubjConfig = isSubjConfig;
		}
		

		public Long getValue() {
			return value;
		}
		
		public String getSubject() {
			return subject;
		}
		
		public boolean isSubjConfig() {
			return isSubjConfig;
		}

		public static NotificationTemplate getType(Long x) {
			if(x == NotificationAlias.EMAIL_PRIMARY_VIEW_FS) {
				return PRIMARY_VIEW; 
			} else if(x == NotificationAlias.EMAIL_FINAL_VIEW_FS) {
				return FINAL_VIEW; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY) {
				return LOGOUT_IMMEDIATELY; 
			} else if(x == NotificationAlias.EMAIL_FS_PROFILE_PRIMARY_COMPLETE) {
				return PRIMARY_FILL_COMPLETE; 
			} else if(x == NotificationAlias.EMAIL_FS_FP_VIEW_MORE_DETAILS) {
				return FP_VIEW_MORE_DETAILS; 
			} else {
				return null;
			} 
		}

	}

}
