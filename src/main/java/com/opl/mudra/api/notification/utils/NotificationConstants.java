package com.opl.mudra.api.notification.utils;

/**
* @author Sanket
*
*/
public class NotificationConstants {

	private NotificationConstants() {
		//Do Nothing because of X and Y.
	}

	public static final class NotificationProperty {
		public static final Long STATUS_SUCCESSFULL = 200L;
		public static final Long STATUS_FAILURE = 500L;
		public static final Long STATUS_NOTIFICATION_SENDING_FAILURE = 401L;
		public static final Long STATUS_MAIL_TEMPLATE_NOTFOUND= 402L;
		
		public static final Long STATUS_SMS_SENDING_FAILURE = 403L;
		public static final Long STATUS_SMS_TEMPLATE_NOTFOUND= 405L;
		
		public static final Long RECENT_VIEW_NOTIFICATION_LIMIT = 4L;
		
		public static final Long SUCCESSFUL = 1L;
		public static final Long FAILURE = 0L;

		private NotificationProperty() {
			//Do Nothing because of X and Y.
		}
		
		public enum DomainValue{
			MSME(1L),RETAIL(2L);
			
			Long domainId;
			DomainValue(Long domainId){
				this.domainId = domainId;
			}
			
			public Long getId() {
				return this.domainId;
			} 
			
		}
	}
	
	
	

}
