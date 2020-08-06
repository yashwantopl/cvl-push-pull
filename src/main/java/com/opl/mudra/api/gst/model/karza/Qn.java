/**
 * 
 */
package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Qn {

	
	private String q1;
	
	private String q2;
	
	private String q3;
	
	private String q4;
	
	private String q5;
	
	private String q6;
	
	private String q7;

	/**
	 * @return the q1
	 */
	public String getQ1() {
		return q1;
	}

	/**
	 * @param q1 the q1 to set
	 */
	public void setQ1(String q1) {
		this.q1 = q1;
	}

	/**
	 * @return the q2
	 */
	public String getQ2() {
		return q2;
	}

	/**
	 * @param q2 the q2 to set
	 */
	public void setQ2(String q2) {
		this.q2 = q2;
	}

	/**
	 * @return the q3
	 */
	public String getQ3() {
		return q3;
	}

	/**
	 * @param q3 the q3 to set
	 */
	public void setQ3(String q3) {
		this.q3 = q3;
	}

	/**
	 * @return the q4
	 */
	public String getQ4() {
		return q4;
	}

	/**
	 * @param q4 the q4 to set
	 */
	public void setQ4(String q4) {
		this.q4 = q4;
	}

	/**
	 * @return the q5
	 */
	public String getQ5() {
		return q5;
	}

	/**
	 * @param q5 the q5 to set
	 */
	public void setQ5(String q5) {
		this.q5 = q5;
	}

	/**
	 * @return the q6
	 */
	public String getQ6() {
		return q6;
	}

	/**
	 * @param q6 the q6 to set
	 */
	public void setQ6(String q6) {
		this.q6 = q6;
	}

	/**
	 * @return the q7
	 */
	public String getQ7() {
		return q7;
	}

	/**
	 * @param q7 the q7 to set
	 */
	public void setQ7(String q7) {
		this.q7 = q7;
	}
	
	

}
