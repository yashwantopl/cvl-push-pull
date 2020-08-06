package com.opl.mudra.api.analyzer.model.common;

import java.util.List;

public class AdminReportData {
Long applicationId;
List<String> response;
List<String> successList;
List<String> failureList;
private byte[] bytes;

public Long getApplicationId() {
	return applicationId;
}
public void setApplicationId(Long applicationId) {
	this.applicationId = applicationId;
}
public List<String> getResponse() {
	return response;
}
public void setResponse(List<String> response) {
	this.response = response;
}
public List<String> getSuccessList() {
	return successList;
}
public void setSuccessList(List<String> successList) {
	this.successList = successList;
}
public List<String> getFailureList() {
	return failureList;
}
public void setFailureList(List<String> failureList) {
	this.failureList = failureList;
}
public byte[] getBytes() {
	return bytes;
}
public void setBytes(byte[] bytes) {
	this.bytes = bytes;
}
}
