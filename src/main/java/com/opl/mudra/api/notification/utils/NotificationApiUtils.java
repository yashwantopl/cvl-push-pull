package com.opl.mudra.api.notification.utils;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

/**
 * @author Sanket
 *
 */
public class NotificationApiUtils {

	private NotificationApiUtils() {
		//Do Nothing
	}

	public static Timestamp getCurrentTimeStamp(){
		 Date date = new Date();
	        return new Timestamp(date.getTime());
	}
	
	public static boolean isListNullOrEmpty(Collection<?> data) {
		return (data == null || data.isEmpty());
	}

	public static boolean isObjectNullOrEmpty(Object value) {
		return (value == null || (value instanceof String ? (((String) value).isEmpty()
				|| "".equals(((String) value).trim()) || "null".equals(value) || "undefined".equals(value)) : false));
	}
	
	

	public enum ApplicationType {
		
		Online(1),Offline(2); 
		
		
		private Integer appTypeId;
	
		ApplicationType(Integer tid) {
			this.appTypeId = tid;
		}
	
		public Integer getId() {
			return this.appTypeId;
		}
		
	}
	
	public enum SchedulerMapping{
		OTP_MOBILE_VERIFICATION(1L, "otp mobile varification"),
		MULTIPLE_BANK(2L, "Multiple Bank");
		
		private Long id;
		private String schedulerName;
		
		SchedulerMapping(Long id,String schedulerName){
			this.id=id;
			this.schedulerName=schedulerName;
		}
		
		public Long getId() {
			return this.id;
		}
		
		public String getName() {
			return this.schedulerName;
		}
		
		public static String getSchedulerNameById(Long id) {
			 for (SchedulerMapping mapping : SchedulerMapping.values()) {
				if(mapping.id == id) {
					return mapping.getName();
				}
			}
			  throw new IllegalArgumentException("The given Scheduler not found from Id:" + id);
		}
	}

}
