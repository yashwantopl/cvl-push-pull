package com.opl.mudra.api.user.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchBasicDetailsRequest implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Long roleId;

	private Integer cityId;

	private String code;

	private String contactPersonEmail;

	private String contactPersonName;

	private String contactPersonNumber;

	private Integer countryId;

	private String faxNo;

	private Boolean isHo;

	private String landMark;

	private Long orgId;

	private Integer parentBranchId;

	private int pincode;

	private String premisesNo;

	private String remarks;

	private Integer stateId;

	private String streetName;

	private String telephoneNo;

	private String ifscCode;

	private Long locationId;

	private int pageIndex;

	private int size;

	private Long userId;

	private Long jobId;

	private Integer status;

	private Date approvedDate;

	private Boolean isCopied;

	private Integer dataReqType;

	private Long branchId;

	private Boolean isActive;

	private List<BranchCategoryRequest> branchCategoryRequestList;

	private List<DepartmentRequest> departmentRequestList;

	private Boolean isDeleted;

	private Boolean isEdit;

	private LocationMasterResponse locationMasterResponse;

	private Object workflowData;

	private Long loanSystemId;
	
	private String locationCode;

	private String smecCode;

	private String smecName;
	
	private String smecEmail;
	
	private String smecMobile;
	
	private String imagePath;

	public BranchBasicDetailsRequest(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public BranchBasicDetailsRequest(Long id, int stateId) {
		super();
		this.id = id;
		this.stateId = stateId;
	}
	
}
