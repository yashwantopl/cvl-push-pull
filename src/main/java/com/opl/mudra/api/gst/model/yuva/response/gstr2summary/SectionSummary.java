

package com.opl.mudra.api.gst.model.yuva.response.gstr2summary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author vijay.chauhan
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)

public class SectionSummary {
    

    @JsonProperty("sec_nm")
    private String secNm;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("rc")
    private Integer rc;
    
    @JsonProperty("ttl_val")
    private Double ttlVal;
    
    @JsonProperty("ttl_txpd_igst")
    private Double ttlTxpdIgst;
    
    @JsonProperty("ttl_txpd_sgst")
    private Double ttlTxpdSgst;
    
    @JsonProperty("ttl_txpd_cgst")
    private Double ttlTxpdCgst;
    
    @JsonProperty("ttl_txpd_cess")
    private Double ttlTxpdCess;
    
    @JsonProperty("ttl_isd_igst")
    private Double ttlIsdIgst;
    
    @JsonProperty("ttl_isd_sgst")
    private Double ttlIsdSgst;
    
    @JsonProperty("ttl_isd_cgst")
    private Double ttlIsdCgst;
    
    @JsonProperty("ttl_isd_cess")
    private Double ttlIsdCess;
    
    @JsonProperty("ttl_itcavld_igst")
    private Double ttlItcavldIgst;
    
    @JsonProperty("ttl_itcavld_sgst")
    private Double ttlItcavldSgst;
    
    @JsonProperty("ttl_itcavld_cgst")
    private Double ttlItcavldCgst;
    
    @JsonProperty("ttl_itcavld_cess")
    private Double ttlItcavldCess;
    
    @JsonProperty("ti_val")
    private Double tiVal;
    
    @JsonProperty("ttl_cppdr")
    private Double ttlCppdr;
    
    @JsonProperty("ttl_expdsply")
    private Double ttlExpdsply;
    
    @JsonProperty("ttl_ngsply")
    private Double ttlNgsply;
    
    @JsonProperty("ttl_nilsply")
    private Double ttlNilsply;
    
    @JsonProperty("cpty_sum")
    private CptySum[] cptySum;

	public String getSecNm() {
		return secNm;
	}

	public void setSecNm(String secNm) {
		this.secNm = secNm;
	}

	public String getChksum() {
		return chksum;
	}

	public void setChksum(String chksum) {
		this.chksum = chksum;
	}

	public Integer getRc() {
		return rc;
	}

	public void setRc(Integer rc) {
		this.rc = rc;
	}

	public Double getTtlVal() {
		return ttlVal;
	}

	public void setTtlVal(Double ttlVal) {
		this.ttlVal = ttlVal;
	}

	public Double getTtlTxpdIgst() {
		return ttlTxpdIgst;
	}

	public void setTtlTxpdIgst(Double ttlTxpdIgst) {
		this.ttlTxpdIgst = ttlTxpdIgst;
	}

	public Double getTtlTxpdSgst() {
		return ttlTxpdSgst;
	}

	public void setTtlTxpdSgst(Double ttlTxpdSgst) {
		this.ttlTxpdSgst = ttlTxpdSgst;
	}

	public Double getTtlTxpdCgst() {
		return ttlTxpdCgst;
	}

	public void setTtlTxpdCgst(Double ttlTxpdCgst) {
		this.ttlTxpdCgst = ttlTxpdCgst;
	}

	public Double getTtlTxpdCess() {
		return ttlTxpdCess;
	}

	public void setTtlTxpdCess(Double ttlTxpdCess) {
		this.ttlTxpdCess = ttlTxpdCess;
	}

	public Double getTtlIsdIgst() {
		return ttlIsdIgst;
	}

	public void setTtlIsdIgst(Double ttlIsdIgst) {
		this.ttlIsdIgst = ttlIsdIgst;
	}

	public Double getTtlIsdSgst() {
		return ttlIsdSgst;
	}

	public void setTtlIsdSgst(Double ttlIsdSgst) {
		this.ttlIsdSgst = ttlIsdSgst;
	}

	public Double getTtlIsdCgst() {
		return ttlIsdCgst;
	}

	public void setTtlIsdCgst(Double ttlIsdCgst) {
		this.ttlIsdCgst = ttlIsdCgst;
	}

	public Double getTtlIsdCess() {
		return ttlIsdCess;
	}

	public void setTtlIsdCess(Double ttlIsdCess) {
		this.ttlIsdCess = ttlIsdCess;
	}

	public Double getTtlItcavldIgst() {
		return ttlItcavldIgst;
	}

	public void setTtlItcavldIgst(Double ttlItcavldIgst) {
		this.ttlItcavldIgst = ttlItcavldIgst;
	}

	public Double getTtlItcavldSgst() {
		return ttlItcavldSgst;
	}

	public void setTtlItcavldSgst(Double ttlItcavldSgst) {
		this.ttlItcavldSgst = ttlItcavldSgst;
	}

	public Double getTtlItcavldCgst() {
		return ttlItcavldCgst;
	}

	public void setTtlItcavldCgst(Double ttlItcavldCgst) {
		this.ttlItcavldCgst = ttlItcavldCgst;
	}

	public Double getTtlItcavldCess() {
		return ttlItcavldCess;
	}

	public void setTtlItcavldCess(Double ttlItcavldCess) {
		this.ttlItcavldCess = ttlItcavldCess;
	}

	public Double getTiVal() {
		return tiVal;
	}

	public void setTiVal(Double tiVal) {
		this.tiVal = tiVal;
	}

	public Double getTtlCppdr() {
		return ttlCppdr;
	}

	public void setTtlCppdr(Double ttlCppdr) {
		this.ttlCppdr = ttlCppdr;
	}

	public Double getTtlExpdsply() {
		return ttlExpdsply;
	}

	public void setTtlExpdsply(Double ttlExpdsply) {
		this.ttlExpdsply = ttlExpdsply;
	}

	public Double getTtlNgsply() {
		return ttlNgsply;
	}

	public void setTtlNgsply(Double ttlNgsply) {
		this.ttlNgsply = ttlNgsply;
	}

	public Double getTtlNilsply() {
		return ttlNilsply;
	}

	public void setTtlNilsply(Double ttlNilsply) {
		this.ttlNilsply = ttlNilsply;
	}

	public CptySum[] getCptySum() {
		return cptySum;
	}

	public void setCptySum(CptySum[] cptySum) {
		this.cptySum = cptySum;
	}
    

}

