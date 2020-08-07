package com.opl.mudra.api.mca;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Court {
	

	private List<OtherInfo> otherInfo;

	/**
	 * @param otherInfo
	 */
	public void setOtherInfo(List<OtherInfo> otherInfo) {
		this.otherInfo = otherInfo;
		
	}

	/**
	 * @return
	 */
	public List<OtherInfo> getOtherInfo() {
		return this.otherInfo;
		
	}
}
