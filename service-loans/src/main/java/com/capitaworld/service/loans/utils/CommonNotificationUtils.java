package com.capitaworld.service.loans.utils;



import com.capitaworld.service.notification.utils.NotificationAlias;

public class CommonNotificationUtils {
	
	public enum NotificationTemplate {
		PRIMARY_VIEW(NotificationAlias.EMAIL_PRIMARY_VIEW_FS),
		FINAL_VIEW(NotificationAlias.EMAIL_FINAL_VIEW_FS),
		LOGOUT_IMMEDIATELY(NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY),
		PRIMARY_FILL_COMPLETE(NotificationAlias.EMAIL_FS_PROFILE_PRIMARY_COMPLETE),
		FP_VIEW_MORE_DETAILS(NotificationAlias.EMAIL_FS_FP_VIEW_MORE_DETAILS),
		FS_GO_MATCHES_PAGE(NotificationAlias.EMAIL_FS_GO_MATCHES_PAGE),
		LOGOUT_IMMEDIATELY_REMAINDER(NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY_REMAINDER),
		LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS),
		LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS),
		LOGOUT_WITHOUT_FILLED_FINAL_DETAILS(NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_FINAL_DETAILS);
		
		private Long value;

		private NotificationTemplate(Long value) {
			this.value = value;
		}
		
		public Long getValue() {
			return value;
		}
		public void setValue(Long value) {
			this.value = value;
		}

		public static String getSubjectName(Long x,String fpName) {
			if(x == NotificationAlias.EMAIL_PRIMARY_VIEW_FS) {
				return fpName + " viewed your teaser"; 
			} else if(x == NotificationAlias.EMAIL_FINAL_VIEW_FS) {
				return fpName + " viewed your final details"; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY) {
				return "Facing Problem in Selecting Loan: Provide your Feedback"; 
			} else if(x == NotificationAlias.EMAIL_FS_PROFILE_PRIMARY_COMPLETE) {
				return "Congrats! Your Matched Fund Provider"; 
			} else if(x == NotificationAlias.EMAIL_FS_FP_VIEW_MORE_DETAILS) {
				return fpName + " requested for more details";
			} else if(x == NotificationAlias.EMAIL_FS_GO_MATCHES_PAGE) {
				return "Add on Feature: List of fund provider to whom request not sent & not matched"; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_IMMEDIATELY_REMAINDER) {
				return "Remainder: Fill the one form and get connect to fund providers"; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PROFILE_DETAILS) {
				return "Facing Problem in Filling One Form: Contact Us"; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_PRIMARY_DETAILS) {
				return "Fill Primary information: One step to get connect to fund providers"; 
			} else if(x == NotificationAlias.EMAIL_FS_LOGOUT_WITHOUT_FILLED_FINAL_DETAILS) {
				return "Fill Final Information: For faster Process of Your Loan"; 
			} else {
				return "NA";
			} 
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
