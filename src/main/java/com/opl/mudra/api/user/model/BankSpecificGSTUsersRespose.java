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
public class BankSpecificGSTUsersRespose   implements Serializable{
	private static final long serialVersionUID = 1L;
	private List<GstUsersInsertedResponse> gstUsersInserted;
    private List<GstUsersNotInsertedResponse> gstUsersNotInserted;
	public List<GstUsersInsertedResponse> getGstUsersInserted() {
		return gstUsersInserted;
	}
	public void setGstUsersInserted(List<GstUsersInsertedResponse> gstUsersInserted) {
		this.gstUsersInserted = gstUsersInserted;
	}
	public List<GstUsersNotInsertedResponse> getGstUsersNotInserted() {
		return gstUsersNotInserted;
	}
	public void setGstUsersNotInserted(List<GstUsersNotInsertedResponse> gstUsersNotInserted) {
		this.gstUsersNotInserted = gstUsersNotInserted;
	}
	
    
	
}
