package com.opl.mudra.api.analyzer.model.enumlist;

public enum StatementUploadExcel {

	NO(0,"No"),
	ID(1,"Id"),
	APPLICATION_ID(2, "Application Id"),
	EMAIL(3,"Email"),
	PHONE(4,"Phone"),
	TXN_ID(5,"Txn ID"),
	PERFIOUS_TXN_ID(6,"Perfious Txn Id"),
	ERROR_TYPE(7,"Error Type"),
	ERROR(8,"Error"),
	DATE(9,"Date"),
	REMARK(10, "Remark");
	
	private StatementUploadExcel(int id,String value) {
		this.id = id;
		this.value = value;
	}
	
	private int id;
	private String value;
	
	public int getId() {
		return id;
	}
	public String getValue() {
		return value;
	}
	
	
	
}
