/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author sanket
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="details")
public class CGTMSEResponse {
	
	private CGTMSEResponseDetails[] details;
	private CGTMSEResponseStatus status;

	private CGTMSEResposneError errors;





	/**
	 * @return the errors
	 */
	public CGTMSEResposneError getErrors() {
		return errors;
	}

	/**
	 * @param errors the errors to set
	 */
	public void setErrors(CGTMSEResposneError errors) {
		this.errors = errors;
	}

	/**
	 * @return the status
	 */
	public CGTMSEResponseStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(CGTMSEResponseStatus status) {
		this.status = status;
	}

	/**
	 * @return the details
	 */
	public CGTMSEResponseDetails[] getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(CGTMSEResponseDetails[] details) {
		this.details = details;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSEResponse [details=" + details + ", status=" + status + ", errors=" + errors + "]";
	}
	
	

}
