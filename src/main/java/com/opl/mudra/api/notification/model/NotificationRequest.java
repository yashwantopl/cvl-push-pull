package com.opl.mudra.api.notification.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanket
 *
 */

/**
 * @author sanket
 *
 */
public class NotificationRequest implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1571195060508109481L;
	private String clientRefId;
	private int retryCount;
	private List<Notification> notifications = new ArrayList<Notification>();
	private Long startTime;
	private String fromName;
	private Long applicationId;
	private Long productId;
	private String userType;
	private Integer pageIndex;
	private Integer size;
	private Long clientId;
	private Long orgId;
	private String alias;
	private Boolean isDynamic;
	private String host;
	private Long domainId;
	private Boolean schedulerStatus;
	
	// changes for repots
	private Boolean isReports;

	public Boolean getIsReports() {
		return isReports;
	}

	public void setIsReports(Boolean isReports) {
		this.isReports = isReports;
	}

	public Boolean getIsDynamic() {
		return isDynamic;
	}

	public void setIsDynamic(Boolean isDynamic) {
		this.isDynamic = isDynamic;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public Integer getSize() {
		return size;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getStartTime() {
		return startTime;
	}

	public String getClientRefId() {
		return clientRefId;
	}

	public void setClientRefId(String clientRefId) {
		this.clientRefId = clientRefId;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public List<Notification> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<Notification> notifications) {
		this.notifications = notifications;
	}

	public void addNotification(Notification notification) {
		this.notifications.add(notification);
	}

	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Long getDomainId() {
		return domainId;
	}

	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public Boolean getSchedulerStatus() {
		return schedulerStatus;
	}

	public void setSchedulerStatus(Boolean schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}

	@Override
	public String toString() {
		return "NotificationRequest [clientRefId=" + clientRefId
				+ ", retryCount=" + retryCount + ", notifications="
				+ notifications.toString() + ", startTime=" + startTime + ", fromName="
				+ fromName + ", applicationId=" + applicationId + ", productId="
				+ productId + ", userType=" + userType + ", pageIndex="
				+ pageIndex + ", size=" + size + ", clientId=" + clientId
				+ ", orgId=" + orgId + ", alias=" + alias + ", isDynamic="
				+ isDynamic + ", host=" + host + ", domainId=" + domainId
				+ ", schedulerStatus=" + schedulerStatus + ", isReports="
				+ isReports + "]";
	} 



}
