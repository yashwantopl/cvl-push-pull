package com.opl.mudra.api.notification.model;

import java.io.Serializable;

/**
 * @author sanket
 *
 */
public class BulkEmailRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SchedulerDataMultipleBankRequest[] scheduledData;

	public SchedulerDataMultipleBankRequest[] getScheduledData() {
		return scheduledData;
	}

	public void setScheduledData(SchedulerDataMultipleBankRequest[] scheduledData) {
		this.scheduledData = scheduledData;
	}
	

}
