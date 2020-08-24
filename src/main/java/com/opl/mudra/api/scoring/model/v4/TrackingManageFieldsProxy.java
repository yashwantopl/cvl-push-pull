package com.opl.mudra.api.scoring.model.v4;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.opl.mudra.api.common.AuditFieldsProxy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class TrackingManageFieldsProxy extends AuditFieldsProxy implements Serializable {

	private static final long serialVersionUID = -93321449016462267L;

	private Long id;
	
	private Integer status;

	private Long jobId;

	private Long orgId;
	
	private Boolean isApproved;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date approvedDate;
	
	private Boolean isEdit;
	
	private String reason;
	
	private Object workFlowData;
	
	private List<Long> roleIds;
	
	private Long roleId;

	private Long userId;
	
	private Long businessTypeId;
	
	private List<String> actionTakenBy;
	
	private Float version;
	
	private Boolean isCopied;

}
