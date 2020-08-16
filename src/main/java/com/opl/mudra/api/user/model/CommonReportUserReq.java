package com.opl.mudra.api.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class CommonReportUserReq implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = -1672552555455822212L;
	private String fileType;
	private String searchStr;
	private Long pageIndex;
	private Long orgId;
	private Long getDataType;
	private Long id;
	private Long branchId;
	private Long roleId;
	private Long userId;

}
