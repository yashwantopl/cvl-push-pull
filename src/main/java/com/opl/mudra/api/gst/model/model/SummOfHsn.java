/**
 * 
 */
package com.opl.mudra.api.gst.model.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author vijay.chauhan
 * Jan 28, 2020 6:18:53 PM
 */
public class SummOfHsn implements Serializable {
	private static final long serialVersionUID = 1L;
	private String retPeriod;
	private Integer num;
    private String hsnSc;
    private String desc;
    private String uqc;
    private Double qty;
    private Double val;
    private Double txval;
    private Double iamt;
    private Double camt;
    private Double samt;
    private Double csamt;
    private List<String> descList;
    
	public SummOfHsn() {
		this.retPeriod = null;
		this.num = 0;
		this.hsnSc = null;
		this.desc = null;
		this.uqc = null;
		this.qty = 0.0;
		this.val = 0.0;
		this.txval = 0.0;
		this.iamt = 0.0;
		this.camt = 0.0;
		this.samt = 0.0;
		this.csamt = 0.0;
		descList=new ArrayList<>();
	}

	public String getRetPeriod() {
		return retPeriod;
	}

	public void setRetPeriod(String retPeriod) {
		this.retPeriod = retPeriod;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getHsnSc() {
		return hsnSc;
	}

	public void setHsnSc(String hsnSc) {
		this.hsnSc = hsnSc;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUqc() {
		return uqc;
	}

	public void setUqc(String uqc) {
		this.uqc = uqc;
	}

	public Double getQty() {
		return qty;
	}

	public void setQty(Double qty) {
		this.qty = qty;
	}

	public Double getVal() {
		return val;
	}

	public void setVal(Double val) {
		this.val = val;
	}

	public Double getTxval() {
		return txval;
	}

	public void setTxval(Double txval) {
		this.txval = txval;
	}

	public Double getIamt() {
		return iamt;
	}

	public void setIamt(Double iamt) {
		this.iamt = iamt;
	}

	public Double getCamt() {
		return camt;
	}

	public void setCamt(Double camt) {
		this.camt = camt;
	}

	public Double getSamt() {
		return samt;
	}

	public void setSamt(Double samt) {
		this.samt = samt;
	}

	public Double getCsamt() {
		return csamt;
	}

	public void setCsamt(Double csamt) {
		this.csamt = csamt;
	}

	
	public List<String> getDescList() {
		return descList;
	}

	public void setDescList(List<String> descList) {
		this.descList = descList;
	}

	public void addDesc(String desc) {
		if(!getDescList().contains(desc)) {
			getDescList().add(desc);			
		}
	}
	@Override
	public String toString() {
		return "SummOfHsn [retPeriod=" + retPeriod + ", num=" + num + ", hsnSc=" + hsnSc + ", desc=" + desc + ", uqc="
				+ uqc + ", qty=" + qty + ", val=" + val + ", txval=" + txval + ", iamt=" + iamt + ", camt=" + camt
				+ ", samt=" + samt + ", csamt=" + csamt + ", descList=" + descList + "]";
	}
    
    
}
