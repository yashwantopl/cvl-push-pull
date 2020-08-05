package com.opl.mudra.api.connect;

import java.io.Serializable;

public class EmpMasterRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Long empId;
	private String empName;
	private Integer type;


	public EmpMasterRequest() {
		super();
	}

	public EmpMasterRequest(Long empId,String empName,Integer type) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.type= type;
	}

	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
