/**
 * 
 */
package com.opl.mudra.api.mca.verifyApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class VerifyAPIDINPAN {
	
	@JsonProperty("dinname")
	private String dinName;
	
	@JsonProperty("pannumber")
	private String pannumber;
	
	
	
	public VerifyAPIDINPAN() {
		// TODO Auto-generated constructor stub
	}
	
	

	public VerifyAPIDINPAN(String dinName, String pannumber) {
		super();
		this.dinName = dinName;
		this.pannumber = pannumber;
	}



	/**
	 * @return the dinName
	 */
	public String getDinName() {
		return dinName;
	}

	/**
	 * @param dinName the dinName to set
	 */
	public void setDinName(String dinName) {
		this.dinName = dinName;
	}

	/**
	 * @return the pannumber
	 */
	public String getPannumber() {
		return pannumber;
	}

	/**
	 * @param pannumber the pannumber to set
	 */
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VerifyAPIDINPAN [dinName=" + dinName + ", pannumber=" + pannumber + "]";
	}
	
	

}
