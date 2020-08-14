package com.opl.mudra.api.common;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuditFieldsProxy implements Serializable {

	private static final long serialVersionUID = -93321449016462267L;

	private Long createdBy;

	private Long modifiedBy;

	private Date createdDate;

	private Date modifiedDate;
	
	private Boolean isActive;

}
