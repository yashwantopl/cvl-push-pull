/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

/**
 * @author sanket
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CGTMSEResponseStatus {
	
	private String status;

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSEResponseStatus [status=" + status + "]";
	}
	
	

}
