package com.opl.mudra.api.analyzer.model.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AnalyzerResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;
	
	private List<?> listData = Collections.emptyList();
	
	private Map<String ,Map<String, Object>> mapData;
	
	private Map map;
	
	private byte[] contentInBytes;
	
	private Boolean flag;
	
	private Boolean isAfftectedByRenewal;
	
	private String missingMonth;

	private Integer totalBankStatement;

	public Date nextDueDate;
	
	public Date lastUpdatedDate;

	public List<BankStatementResponse> bankStatementList;
	
	private Boolean isManualUpload;

	private String failureReason;

	private String success;

	private String sysMessage;

	private Integer responseType;

	private Integer msgCode;
	
	public byte[] getContentInBytes() {
		return contentInBytes;
	}

	public void setContentInBytes(byte[] contentInBytes) {
		this.contentInBytes = contentInBytes;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public Map<String, Map<String, Object>> getMapData() {
		return mapData;
	}

	public void setMapData(Map<String, Map<String, Object>> mapData) {
		this.mapData = mapData;
	}

	public AnalyzerResponse() {
		super();
	}

	public AnalyzerResponse(String message) {
		super();
		this.message = message;
	}
	
	public AnalyzerResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	
	public AnalyzerResponse(String message, Integer status, Object data) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public AnalyzerResponse(String message, Integer status, byte[] contentInBytes) {
		super();
		this.message = message;
		this.status = status;
		this.contentInBytes = contentInBytes;
	}

	public AnalyzerResponse(String message, Integer status, Object data,String missingMonth) {
		super();
		this.message = message;
		this.status = status;
		this.data = data;
		this.missingMonth=missingMonth;
	}

	public AnalyzerResponse(String message, Integer status, Object data, String success, String sysMessage, Integer responseType, Integer msgCode) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
		this.success = success;
		this.sysMessage = sysMessage;
		this.responseType = responseType;
		this.msgCode = msgCode;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<?> getListData() {
		return listData;
	}

	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public Boolean getIsAfftectedByRenewal() {
		return isAfftectedByRenewal;
	}

	public void setIsAfftectedByRenewal(Boolean isAfftectedByRenewal) {
		this.isAfftectedByRenewal = isAfftectedByRenewal;
	}

	public String getMissingMonth() {
		return missingMonth;
	}

	public void setMissingMonth(String missingMonth) {
		this.missingMonth = missingMonth;
	}


	public Integer getTotalBankStatement() {
		return totalBankStatement;
	}

	public void setTotalBankStatement(Integer totalBankStatement) {
		this.totalBankStatement = totalBankStatement;
	}

	public Date getNextDueDate() {
		return nextDueDate;
	}

	public void setNextDueDate(Date nextDueDate) {
		this.nextDueDate = nextDueDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public List<BankStatementResponse> getBankStatementList() {
		return bankStatementList;
	}

	public void setBankStatementList(List<BankStatementResponse> bankStatementList) {
		this.bankStatementList = bankStatementList;
	}

	public Boolean getIsManualUpload() {
		return isManualUpload;
	}

	public void setIsManualUpload(Boolean isManualUpload) {
		this.isManualUpload = isManualUpload;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public String getSysMessage() {
		return sysMessage;
	}

	public void setSysMessage(String sysMessage) {
		this.sysMessage = sysMessage;
	}

	public Integer getResponseType() {
		return responseType;
	}

	public void setResponseType(Integer responseType) {
		this.responseType = responseType;
	}

	public Integer getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(Integer msgCode) {
		this.msgCode = msgCode;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
}