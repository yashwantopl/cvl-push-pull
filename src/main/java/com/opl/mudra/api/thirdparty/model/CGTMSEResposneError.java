/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author sanket
 *
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class CGTMSEResposneError {
	
	
	@XmlElement(name = "error_code")
	private String errorCode;
	
	@XmlElement(name = "error_message")
	private String errorMessage;

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSEResposneError [errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

	

}
