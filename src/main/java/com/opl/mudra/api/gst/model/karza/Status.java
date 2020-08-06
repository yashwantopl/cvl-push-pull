package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Status {

	private String status;
	
	private Boolean tileDisable;
	
	@JsonProperty("due_dt")
	private String dueDate;
	
	@JsonProperty("return_ty")
	private String returnTy;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getReturnTy() {
		return returnTy;
	}

	public void setReturnTy(String returnTy) {
		this.returnTy = returnTy;
	}

	public Boolean getTileDisable() {
		return tileDisable;
	}

	public void setTileDisable(Boolean tileDisable) {
		this.tileDisable = tileDisable;
	}
	
	
}
