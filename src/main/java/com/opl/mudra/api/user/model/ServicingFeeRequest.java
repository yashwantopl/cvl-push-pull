package com.opl.mudra.api.user.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author rahul.meena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicingFeeRequest implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	private Long userOrgId;
    private String dayMonthsYear;
	private String totalDisbursementAmount;
	private String serviceFeePayable;
	private String serviceFeePayableSecond;
	private String cGST;
	private String sGST;
	private String iGST;
	private String tDS;
	private String netServiceFreePayble;
	private String serviceFessCarriedOver;
	private String action;
	/**
	 * @return the userOrgId
	 */
	public Long getUserOrgId() {
		return userOrgId;
	}
	/**
	 * @param userOrgId the userOrgId to set
	 */
	public void setUserOrgId(Long userOrgId) {
		this.userOrgId = userOrgId;
	}
	/**
	 * @return the dayMonthsYear
	 */
	public String getDayMonthsYear() {
		return dayMonthsYear;
	}
	/**
	 * @param dayMonthsYear the dayMonthsYear to set
	 */
	public void setDayMonthsYear(String dayMonthsYear) {
		this.dayMonthsYear = dayMonthsYear;
	}
	/**
	 * @return the totalDisbursementAmount
	 */
	public String getTotalDisbursementAmount() {
		return totalDisbursementAmount;
	}
	/**
	 * @param totalDisbursementAmount the totalDisbursementAmount to set
	 */
	public void setTotalDisbursementAmount(String totalDisbursementAmount) {
		this.totalDisbursementAmount = totalDisbursementAmount;
	}
	/**
	 * @return the serviceFeePayable
	 */
	public String getServiceFeePayable() {
		return serviceFeePayable;
	}
	/**
	 * @param serviceFeePayable the serviceFeePayable to set
	 */
	public void setServiceFeePayable(String serviceFeePayable) {
		this.serviceFeePayable = serviceFeePayable;
	}
	/**
	 * @return the serviceFeePayableSecond
	 */
	public String getServiceFeePayableSecond() {
		return serviceFeePayableSecond;
	}
	/**
	 * @param serviceFeePayableSecond the serviceFeePayableSecond to set
	 */
	public void setServiceFeePayableSecond(String serviceFeePayableSecond) {
		this.serviceFeePayableSecond = serviceFeePayableSecond;
	}
	/**
	 * @return the cGST
	 */
	public String getcGST() {
		return cGST;
	}
	/**
	 * @param cGST the cGST to set
	 */
	public void setcGST(String cGST) {
		this.cGST = cGST;
	}
	/**
	 * @return the sGST
	 */
	public String getsGST() {
		return sGST;
	}
	/**
	 * @param sGST the sGST to set
	 */
	public void setsGST(String sGST) {
		this.sGST = sGST;
	}
	/**
	 * @return the iGST
	 */
	public String getiGST() {
		return iGST;
	}
	/**
	 * @param iGST the iGST to set
	 */
	public void setiGST(String iGST) {
		this.iGST = iGST;
	}
	/**
	 * @return the tDS
	 */
	public String gettDS() {
		return tDS;
	}
	/**
	 * @param tDS the tDS to set
	 */
	public void settDS(String tDS) {
		this.tDS = tDS;
	}
	/**
	 * @return the netServiceFreePayble
	 */
	public String getNetServiceFreePayble() {
		return netServiceFreePayble;
	}
	/**
	 * @param netServiceFreePayble the netServiceFreePayble to set
	 */
	public void setNetServiceFreePayble(String netServiceFreePayble) {
		this.netServiceFreePayble = netServiceFreePayble;
	}
	/**
	 * @return the serviceFessCarriedOver
	 */
	public String getServiceFessCarriedOver() {
		return serviceFessCarriedOver;
	}
	/**
	 * @param serviceFessCarriedOver the serviceFessCarriedOver to set
	 */
	public void setServiceFessCarriedOver(String serviceFessCarriedOver) {
		this.serviceFessCarriedOver = serviceFessCarriedOver;
	}
	/**
	 * @return the action
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	/**
	 *
	 */
	@Override
	public String toString() {
		return "ServicingFeeRequest [userOrgId=" + userOrgId + ", dayMonthsYear=" + dayMonthsYear
				+ ", totalDisbursementAmount=" + totalDisbursementAmount + ", serviceFeePayable=" + serviceFeePayable
				+ ", serviceFeePayableSecond=" + serviceFeePayableSecond + ", cGST=" + cGST + ", sGST=" + sGST
				+ ", iGST=" + iGST + ", tDS=" + tDS + ", netServiceFreePayble=" + netServiceFreePayble
				+ ", serviceFessCarriedOver=" + serviceFessCarriedOver + ", action=" + action + "]";
	}
}
