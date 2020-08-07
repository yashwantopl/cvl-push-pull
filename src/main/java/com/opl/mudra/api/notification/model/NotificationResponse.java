package com.opl.mudra.api.notification.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Sanket
 *
 */
public class NotificationResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 668251732769674954L;
	private Long status;
	private String message;
	private String sentMessage;
	private List<SysNotifyResponse> sysNotification;
	private Map<String, List<SysNotifyResponse>> recentViewResponse;
	private Long count;
	private Long response_code;
	private String response_code_message;
	private Object data;
	private String success;
	private String templateName;
	
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public Map<String, List<SysNotifyResponse>> getRecentViewResponse() {
		return recentViewResponse;
	}
	public void setRecentViewResponse(Map<String, List<SysNotifyResponse>> recentViewResponse) {
		this.recentViewResponse = recentViewResponse;
	}
	public List<SysNotifyResponse> getSysNotification() {
		return sysNotification;
	}
	public void setSysNotification(List<SysNotifyResponse> sysNotification) {
		this.sysNotification = sysNotification;
	}
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSentMessage() {
		return sentMessage;
	}
	public void setSentMessage(String sentMessage) {
		this.sentMessage = sentMessage;
	}
	public Long getResponse_code() {
		return response_code;
	}
	public void setResponse_code(Long response_code) {
		this.response_code = response_code;
	}
	public String getResponse_code_message() {
		return response_code_message;
	}
	public void setResponse_code_message(String response_code_message) {
		this.response_code_message = response_code_message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	
	
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public NotificationResponse(String success, Object data, Long response_code, String response_code_message, String message) {
		super();
		this.success = success;
		this.data = data;
		this.response_code = response_code;
		this.message = message;
		this.response_code_message = response_code_message;
	}
	public NotificationResponse(String success, Long response_code, String response_code_message, String message) {
		super();
		this.success = success;
		this.response_code = response_code;
		this.response_code_message = response_code_message;
		this.message = message;
	}
	public NotificationResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "NotificationResponse [status=" + status + ", message=" + message + ", sentMessage=" + sentMessage
				+ ", sysNotification=" + sysNotification + ", recentViewResponse=" + recentViewResponse + ", count="
				+ count + ", response_code=" + response_code + ", response_code_message=" + response_code_message
				+ ", data=" + data + ", success=" + success + ", templateName=" + templateName + "]";
	}
	
	

}
