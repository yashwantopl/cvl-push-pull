/**
 * 
 */
package com.opl.mudra.api.gst.model.yuva.response.gstr2ab2b;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Itms implements Serializable{
	
	private Double num;
	
	@JsonProperty("itc")
	private Itc itc;

	@JsonProperty("itm_det")
	private Itm_det itm_det;



	/**
	 * @return the num
	 */
	public Double getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Double num) {
		this.num = num;
	}

	/**
	 * @return the itc
	 */
	public Itc getItc() {
		return itc;
	}

	/**
	 * @param itc the itc to set
	 */
	public void setItc(Itc itc) {
		this.itc = itc;
	}

	/**
	 * @return the itm_det
	 */
	public Itm_det getItm_det() {
		return itm_det;
	}

	/**
	 * @param itm_det the itm_det to set
	 */
	public void setItm_det(Itm_det itm_det) {
		this.itm_det = itm_det;
	}


	public Itms() {
		this.num = 0.0;
		this.itc = new Itc();
		this.itm_det = new Itm_det();
	}
}
