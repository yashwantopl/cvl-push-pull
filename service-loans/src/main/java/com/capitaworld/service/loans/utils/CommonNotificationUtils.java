package com.capitaworld.service.loans.utils;



import com.capitaworld.service.notification.utils.NotificationAlias;

public class CommonNotificationUtils {
	
	public enum NotificationTemplate {
		PRIMARY_VIEW(NotificationAlias.EMAIL_PRIMARY_VIEW_FS," viewed your teaser",true),
		FINAL_VIEW(NotificationAlias.EMAIL_FINAL_VIEW_FS," viewed your final details",true),
		LOGOUT_IMMEDIATELY(NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY,"Reviews",false),
		PRIMARY_FILL_COMPLETE(NotificationAlias.EMAIL_FS_PROFILE_PRIMARY_COMPLETE,"Your Matches",false),
		FP_VIEW_MORE_DETAILS(NotificationAlias.EMAIL_FS_FP_VIEW_MORE_DETAILS," requested for more details",true),
		FS_GO_MATCHES_PAGE(NotificationAlias.EMAIL_FS_GO_MATCHES_PAGE,"Matches & Connections",false),
		LOGOUT_IMMEDIATELY_REMAINDER(NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY_REMAINDER,"Remainder",false),
		LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS,"Profile not filled",false),
		LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS,"Primary not filled",false),
		LOGOUT_WITHOUT_FILLED_FINAL_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_FINAL_DETAILS,"Final not filled",false);
		
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
			} else if(x == NotificationAlias.EMAIL_FS_GO_MATCHES_PAGE) {
				return FS_GO_MATCHES_PAGE; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY_REMAINDER) {
				return LOGOUT_IMMEDIATELY_REMAINDER; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS) {
				return LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS) {
				return LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_FINAL_DETAILS) {
				return LOGOUT_WITHOUT_FILLED_FINAL_DETAILS; 
			} else {
				return null;
			} 
		}
		
	}

}
