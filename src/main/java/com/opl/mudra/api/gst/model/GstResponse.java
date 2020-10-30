/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.opl.mudra.api.gst.model.util.CommonUtils;

/**
 * @author sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class GstResponse implements Serializable{
	
	

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1905122041950251207L;

	private Long id;

	private Integer status;

	private String message;

	private Object data;

	private List<?> listData = Collections.emptyList();
	
	private List<?> gstinListData = Collections.emptyList();
	
	public List<?> getGstinListData() {
		return gstinListData;
	}

	public void setGstinListData(List<?> gstinListData) {
		this.gstinListData = gstinListData;
	}

	private String gstin;
	private String imGstin;
	
	private String statusCd;
	
	private String username;
	
	private CommonUtils.Step nextStep;
	
	private CommonUtils.Step currentStep;
	
	private String pan;
	
	private Integer maxGstinNo;

	private Integer currentGstinCount;
	
	private String orgName;
	
	private Object compData;
	
	private Integer msgCode;
	private String sysMessage;
	
	/**
	 * @return the currentStep
	 */
	public CommonUtils.Step getCurrentStep() {
		return currentStep;
	}

	/**
	 * @param currentStep the currentStep to set
	 */
	public void setCurrentStep(CommonUtils.Step currentStep) {
		this.currentStep = currentStep;
	}

	/**
	 * @return the nextStep
	 */
	public CommonUtils.Step getNextStep() {
		return nextStep;
	}

	/**
	 * @param nextStep the nextStep to set
	 */
	public void setNextStep(CommonUtils.Step nextStep) {
		this.nextStep = nextStep;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the statusCd
	 */
	public String getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @return the listData
	 */
	public List<?> getListData() {
		return listData;
	}

	/**
	 * @param listData the listData to set
	 */
	public void setListData(List<?> listData) {
		this.listData = listData;
	}

	/**
	 * @param status
	 * @param message
	 * @param gstin
	 */

	/**
	 * @param statusCd
	 * @param message
	 */

	public GstResponse(Integer status,String statusCd, String message) {
		super();
		this.statusCd = statusCd;
		this.message = message;
		this.status = status;
	}
	

	/**
	 * @param status
	 * @param message
	 */
	public GstResponse(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	
	public GstResponse(Integer status, String message , Object data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	/**
	 * @param status
	 * @param message
	 * @param gstin
	 */
	public GstResponse(Integer status,String statusCd, String message, String gstin) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.gstin = gstin;
	}
	
	public GstResponse(String pan,Integer status,String statusCd, String message) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.pan = pan;
	}
	
	public GstResponse(Integer status,String statusCd, String message, String gstin, String username) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.gstin = gstin;
		this.username = username;
	}
	
	
	public GstResponse(Integer status,String statusCd, String message, String gstin, String username, Object data) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.gstin = gstin;
		this.username = username;
		this.data = data;
	}
	public GstResponse(Integer status,String statusCd, String message, String gstin, String username, Object data,Object compData) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.gstin = gstin;
		this.username = username;
		this.data = data;
		this.compData = compData;
	}
	public GstResponse(Integer status,String statusCd, String message, String pan, Object data) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.pan = pan;
		this.data = data;
	}
	/**
	 * 
	 */
	public GstResponse() {
		super();
	}

	/**
	 * @param message
	 * @param status
	 */
	public GstResponse(String message, Integer status) {
		this.message = message;
		this.status = status;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	

	public String getPan() {
		return pan;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GstResponse [id=" + id + ", status=" + status + ", message=" + message + ", data=" + data
				+ ", listData=" + listData + ", gstinListData=" + gstinListData + ", gstin=" + gstin + ", statusCd="
				+ statusCd + ", username=" + username + ", nextStep=" + nextStep + ", currentStep=" + currentStep
				+ ", pan=" + pan + ", maxGstinNo=" + maxGstinNo + ", orgName=" + orgName + "]";
	}

	public void setPan(String pan) {
		this.pan = pan;
	}	

	public GstResponse(Integer status,String statusCd, String message, String gstin,String username,String pan) {
		super();
		this.status = status;
		this.statusCd = statusCd;
		this.message = message;
		this.gstin = gstin;
		this.username = username;
		this.pan = pan;
	}

	/**
	 * @return the maxGstinNo
	 */
	public Integer getMaxGstinNo() {
		return maxGstinNo;
	}

	/**
	 * @param maxGstinNo the maxGstinNo to set
	 */
	public void setMaxGstinNo(Integer maxGstinNo) {
		this.maxGstinNo = maxGstinNo;
	}

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	/**
	 * @return the currentGstinCount
	 */
	public Integer getCurrentGstinCount() {
		return currentGstinCount;
	}

	/**
	 * @param currentGstinCount the currentGstinCount to set
	 */
	public void setCurrentGstinCount(Integer currentGstinCount) {
		this.currentGstinCount = currentGstinCount;
	}

	public Object getCompData() {
		return compData;
	}

	public void setCompData(Object compData) {
		this.compData = compData;
	}


	public String getImGstin() {
		return imGstin;
	}

	public void setImGstin(String imGstin) {
		this.imGstin = imGstin;
	}

	public Integer getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(Integer msgCode) {
		this.msgCode = msgCode;
	}

	public String getSysMessage() {
		return sysMessage;
	}

	public void setSysMessage(String sysMessage) {
		this.sysMessage = sysMessage;
	}

	
}
