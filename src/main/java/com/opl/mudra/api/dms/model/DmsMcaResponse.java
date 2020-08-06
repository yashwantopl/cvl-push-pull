/**
 * 
 */
package com.opl.mudra.api.dms.model;

/**
 * @author vijay.chauhan
 *
 */
public class DmsMcaResponse{

	private String message;
    private Integer status;
    private String resourceUrl;
    
    public DmsMcaResponse() {
    	
    }
	

	public DmsMcaResponse(String resourceUrl, String message, Integer status) {
		super();
		this.resourceUrl = resourceUrl;
		this.message = message;
		this.status = status;
	}


	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @return the resourceUrl
	 */
	public String getResourceUrl() {
		return resourceUrl;
	}
	/**
	 * @param resourceUrl the resourceUrl to set
	 */
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	
	
}
