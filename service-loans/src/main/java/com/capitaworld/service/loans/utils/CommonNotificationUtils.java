package com.capitaworld.service.loans.utils;



import com.capitaworld.service.notification.utils.NotificationAlias;

public class CommonNotificationUtils {
	
	public enum NotificationTemplate {
		PRIMARY_VIEW(NotificationAlias.EMAIL_PRIMARY_VIEW_FS,"View Primary Details"), FINAL_VIEW(NotificationAlias.EMAIL_FINAL_VIEW_FS,"View Final Details");
		
		private Long value;
		private String subject;

		private NotificationTemplate(Long value,String subject) {
			this.value = value;
			this.subject = subject;
		}
		

		public Long getValue() {
			return value;
		}
		
		public String getSubject() {
			return subject;
		}

		public static NotificationTemplate getType(Long x) {
			if(x == NotificationAlias.EMAIL_PRIMARY_VIEW_FS) {
				return PRIMARY_VIEW; 
			} else if(x == NotificationAlias.EMAIL_FINAL_VIEW_FS) {
				return FINAL_VIEW; 
			} else {
				return null;
			} 
		}

	}

}
