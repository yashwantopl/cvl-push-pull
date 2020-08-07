/**
 * 
 */
package com.opl.mudra.api.thirdparty.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author sanket
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CGTMSEResponseDetails {
	
	@XmlElement(name = "SSI_UNIT_NAME")
	private String ssiUnitName;

	
	@XmlElement(name = "MEM_BANK_NAME")
	private String memBankName;
	
	
	@XmlElement(name = "CGPAN")
	private String cgpan;
	
	
	@XmlElement(name = "GURAMT")
	private String gurAmt;
	
	
	@XmlElement(name = "SSI_IT_PAN")
	private String ssiItPan;

	@XmlElement(name = "PMR_UDYOG_ADHAR_NO")
	private String udyogAadhar;

	/**
	 * @return the udyogAadhar
	 */
	public String getUdyogAadhar() {
		return udyogAadhar;
	}


	/**
	 * @param udyogAadhar the udyogAadhar to set
	 */
	public void setUdyogAadhar(String udyogAadhar) {
		this.udyogAadhar = udyogAadhar;
	}


	/**
	 * @return the ssiUnitName
	 */
	public String getSsiUnitName() {
		return ssiUnitName;
	}


	/**
	 * @param ssiUnitName the ssiUnitName to set
	 */
	public void setSsiUnitName(String ssiUnitName) {
		this.ssiUnitName = ssiUnitName;
	}


	

	/**
	 * @return the memBankName
	 */
	public String getMemBankName() {
		return memBankName;
	}


	/**
	 * @param memBankName the memBankName to set
	 */
	public void setMemBankName(String memBankName) {
		this.memBankName = memBankName;
	}


	/**
	 * @return the cgpan
	 */
	public String getCgpan() {
		return cgpan;
	}


	/**
	 * @param cgpan the cgpan to set
	 */
	public void setCgpan(String cgpan) {
		this.cgpan = cgpan;
	}


	/**
	 * @return the gurAmt
	 */
	public String getGurAmt() {
		return gurAmt;
	}


	/**
	 * @param gurAmt the gurAmt to set
	 */
	public void setGurAmt(String gurAmt) {
		this.gurAmt = gurAmt;
	}


	/**
	 * @return the ssiItPan
	 */
	public String getSsiItPan() {
		return ssiItPan;
	}


	/**
	 * @param ssiItPan the ssiItPan to set
	 */
	public void setSsiItPan(String ssiItPan) {
		this.ssiItPan = ssiItPan;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CGTMSEResponseDetails [ssiUnitName=" + ssiUnitName + ", memBankName=" + memBankName + ", cgpan=" + cgpan
				+ ", gurAmt=" + gurAmt + ", ssiItPan=" + ssiItPan + ", udyogAadhar=" + udyogAadhar + "]";
	}
	
	
}
