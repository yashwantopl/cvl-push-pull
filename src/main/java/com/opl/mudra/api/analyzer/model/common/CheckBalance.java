package com.opl.mudra.api.analyzer.model.common;

public class CheckBalance {

	private  String msg;
	private Boolean flag;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getFlag() {
		return flag;
	}
	public void setFlag(Boolean flag) {
		this.flag = flag;
	}
	public CheckBalance(String msg, Boolean flag) {
		super();
		this.msg = msg;
		this.flag = flag;
	}
	
	
	
	
}
