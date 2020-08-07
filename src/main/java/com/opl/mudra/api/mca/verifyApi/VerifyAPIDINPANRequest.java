/**
 * 
 */
package com.opl.mudra.api.mca.verifyApi;

/**
 * @author sanket
 *
 */
public class VerifyAPIDINPANRequest {
	
private String request;
	
	private VerifyAPIPara para;

	/**
	 * @return the request
	 */
	public String getRequest() {
		return request;
	}

	/**
	 * @param request the request to set
	 */
	public void setRequest(String request) {
		this.request = request;
	}



	public VerifyAPIPara getPara() {
		return para;
	}

	public void setPara(VerifyAPIPara para) {
		this.para = para;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifyAPIDINPANRequest [request=" + request + ", para=" + para + "]";
	}
	
	


}
