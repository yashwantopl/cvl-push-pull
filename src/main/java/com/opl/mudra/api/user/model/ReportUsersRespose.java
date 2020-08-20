/**
 * 
 */
package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author vijay.chauhan
 *
 */
public class ReportUsersRespose   implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<ITRUsersInsertedResponse> itrUsersInserted;
    private List<ITRUsersNotInsertedResponse> itrUsersNotInserted;
	   
	private List<BSUsersInsertedResponse> bsUsersInserted;
    private List<BSUsersNotInsertedResponse> bsUsersNotInserted;
    
    private List<MCAUsersInsertedResponse> mcaUsersInserted;
    private List<MCAUsersNotInsertedResponse> mcaUsersNotInserted;
    
    
    
	
    public List<MCAUsersInsertedResponse> getMcaUsersInserted() {
		return mcaUsersInserted;
	}
	public void setMcaUsersInserted(List<MCAUsersInsertedResponse> mcaUsersInserted) {
		this.mcaUsersInserted = mcaUsersInserted;
	}
	public List<MCAUsersNotInsertedResponse> getMcaUsersNotInserted() {
		return mcaUsersNotInserted;
	}
	public void setMcaUsersNotInserted(List<MCAUsersNotInsertedResponse> mcaUsersNotInserted) {
		this.mcaUsersNotInserted = mcaUsersNotInserted;
	}
	public List<ITRUsersInsertedResponse> getItrUsersInserted() {
		return itrUsersInserted;
	}
	public void setItrUsersInserted(List<ITRUsersInsertedResponse> itrUsersInserted) {
		this.itrUsersInserted = itrUsersInserted;
	}
	public List<ITRUsersNotInsertedResponse> getItrUsersNotInserted() {
		return itrUsersNotInserted;
	}
	public void setItrUsersNotInserted(List<ITRUsersNotInsertedResponse> itrUsersNotInserted) {
		this.itrUsersNotInserted = itrUsersNotInserted;
	}
	public List<BSUsersInsertedResponse> getBsUsersInserted() {
		return bsUsersInserted;
	}
	public void setBsUsersInserted(List<BSUsersInsertedResponse> bsUsersInserted) {
		this.bsUsersInserted = bsUsersInserted;
	}
	public List<BSUsersNotInsertedResponse> getBsUsersNotInserted() {
		return bsUsersNotInserted;
	}
	public void setBsUsersNotInserted(List<BSUsersNotInsertedResponse> bsUsersNotInserted) {
		this.bsUsersNotInserted = bsUsersNotInserted;
	}
    
	
}
