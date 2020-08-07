package com.opl.mudra.api.itr.model;

import java.io.Serializable;
import java.util.List;

public class ITRXMLScrapeRes implements Serializable {


	private static final long serialVersionUID = 1L;
	
	private String appid;
	private String cappath;
	private Boolean success;
	private String filepath;
	private List<String> pdfList;
	private List<String> xmlList;
	private String msg; 
	private List<String> yearList;
	private String stage; 
	private String statuscode;
	private String sessionid;
	private String url;
	
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getCappath() {
		return cappath;
	}
	public void setCappath(String cappath) {
		this.cappath = cappath;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public List<String> getPdfList() {
		return pdfList;
	}
	public void setPdfList(List<String> pdfList) {
		this.pdfList = pdfList;
	}
	public List<String> getXmlList() {
		return xmlList;
	}
	public void setXmlList(List<String> xmlList) {
		this.xmlList = xmlList;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<String> getYearList() {
		return yearList;
	}
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	
	public String getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(String statuscode) {
		this.statuscode = statuscode;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "ITRXMLScrapeRes [appid=" + appid + ", cappath=" + cappath + ", success=" + success + ", filepath="
				+ filepath + ", pdfList=" + pdfList + ", xmlList=" + xmlList + ", msg=" + msg + ", yearList=" + yearList
				+ ", stage=" + stage + ", statuscode=" + statuscode + ", sessionid=" + sessionid + ", url=" + url + "]";
	}
	
	
	
}
