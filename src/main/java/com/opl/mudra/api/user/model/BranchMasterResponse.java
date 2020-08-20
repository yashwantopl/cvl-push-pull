package com.opl.mudra.api.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchMasterResponse implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String branchId;
	private String branchCode;
	private String branchName;
	private String contactPersonName;
	private String contactPersonEmail;
	private String contactPersonNumber;
	private String branchAddress;
	private String branchState;
	private String branchCity;
	private String branchRegion;
	private String ifscCode;
	private String pinCode;
	private String isActive;
	private String email;
	private String stateId;
	private String cityId;
	private String regionCode;
	private Object data;
	private Long id;
	private String message;
	private Integer status;
	private String location;
	private String makerCount;
	private String checkerCount;
	
	private String roId;
	private String roCode;
	private String roName;
	
	private String zoId;
	private String zoCode;
	private String zoName;
	
	private Long branchROID;
	private Long branchZOID;
	
	private String makerCheckerEmail;
	private String makerCheckerMobile;
	private String makerCheckerName;
	
	public BranchMasterResponse(String msg,Integer status){
		this.message = msg;
		this.status = status;
	}

}