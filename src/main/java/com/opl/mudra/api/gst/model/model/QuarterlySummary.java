/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;

/**
 * @author vijay.chauhan
 *
 */

public class QuarterlySummary implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String financialYear;
	private String total;
	private String q1;
	private String q2;
	private String q3;
	private String q4;
	
	
	public QuarterlySummary() {
		super();		
		this.total = "0";
		this.q1 = "0";
		this.q2 = "0";
		this.q3 = "0";
		this.q4 = "0";
	}


	public String getFinancialYear() {
		return financialYear;
	}


	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}


	public String getTotal() {
		return total;
	}


	public void setTotal(String total) {
		this.total = total;
	}


	public String getQ1() {
		return q1;
	}


	public void setQ1(String q1) {
		this.q1 = q1;
	}


	public String getQ2() {
		return q2;
	}


	public void setQ2(String q2) {
		this.q2 = q2;
	}


	public String getQ3() {
		return q3;
	}


	public void setQ3(String q3) {
		this.q3 = q3;
	}


	public String getQ4() {
		return q4;
	}


	public void setQ4(String q4) {
		this.q4 = q4;
	}


	@Override
	public String toString() {
		return "QuarterlySummary [financialYear=" + financialYear + ", total=" + total + ", q1=" + q1 + ", q2=" + q2
				+ ", q3=" + q3 + ", q4=" + q4 + "]";
	}
	
}
