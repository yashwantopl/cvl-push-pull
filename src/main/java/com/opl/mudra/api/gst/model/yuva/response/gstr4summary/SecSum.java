
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "sec_nm",
    "chksum",
    "ttl_rec",
    "ttl_val",
    "ttl_tax",
    "ttl_igst",
    "ttl_sgst",
    "ttl_cgst",
    "ttl_cess",
    "ttl_liab",
    "gross_adv_pd",
    "gross_adv_adj",
    "ttl_rchrg",
    "ttl_non_rchrg",
    "cpty_sum"
})
public class SecSum {

    @JsonProperty("sec_nm")
    private String secNm;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("ttl_rec")
    private Integer ttlRec;
    
    @JsonProperty("ttl_val")
    private Integer ttlVal;
    
    @JsonProperty("ttl_tax")
    private Integer ttlTax;
    
    @JsonProperty("ttl_igst")
    private Integer ttlIgst;
    
    @JsonProperty("ttl_sgst")
    private Integer ttlSgst;
    
    @JsonProperty("ttl_cgst")
    private Integer ttlCgst;
    
    @JsonProperty("ttl_cess")
    private Integer ttlCess;
    
    @JsonProperty("ttl_liab")
    private Integer ttlLiab;
    
    @JsonProperty("gross_adv_pd")
    private Integer grossAdvPd;
    
    @JsonProperty("gross_adv_adj")
    private Integer grossAdvAdj;
    
    
    @JsonProperty("ttl_rchrg")
    private TtlRchrg ttlRchrg;

    @JsonProperty("ttl_non_rchrg")
    private TtlNonRchrg ttlNonRchrg;

    @JsonProperty("cpty_sum")
    private CptySum[] cptySum;

    @JsonProperty("sec_nm")
    public String getSecNm() {
        return secNm;
    }

    @JsonProperty("sec_nm")
    public void setSecNm(String secNm) {
        this.secNm = secNm;
    }

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("ttl_rec")
    public Integer getTtlRec() {
        return ttlRec;
    }

    @JsonProperty("ttl_rec")
    public void setTtlRec(Integer ttlRec) {
        this.ttlRec = ttlRec;
    }

    @JsonProperty("ttl_val")
    public Integer getTtlVal() {
        return ttlVal;
    }

    @JsonProperty("ttl_val")
    public void setTtlVal(Integer ttlVal) {
        this.ttlVal = ttlVal;
    }

    @JsonProperty("ttl_tax")
    public Integer getTtlTax() {
        return ttlTax;
    }

    @JsonProperty("ttl_tax")
    public void setTtlTax(Integer ttlTax) {
        this.ttlTax = ttlTax;
    }

    @JsonProperty("ttl_igst")
    public Integer getTtlIgst() {
        return ttlIgst;
    }

    @JsonProperty("ttl_igst")
    public void setTtlIgst(Integer ttlIgst) {
        this.ttlIgst = ttlIgst;
    }

    @JsonProperty("ttl_sgst")
    public Integer getTtlSgst() {
        return ttlSgst;
    }

    @JsonProperty("ttl_sgst")
    public void setTtlSgst(Integer ttlSgst) {
        this.ttlSgst = ttlSgst;
    }

    @JsonProperty("ttl_cgst")
    public Integer getTtlCgst() {
        return ttlCgst;
    }

    @JsonProperty("ttl_cgst")
    public void setTtlCgst(Integer ttlCgst) {
        this.ttlCgst = ttlCgst;
    }

    @JsonProperty("ttl_cess")
    public Integer getTtlCess() {
        return ttlCess;
    }

    @JsonProperty("ttl_cess")
    public void setTtlCess(Integer ttlCess) {
        this.ttlCess = ttlCess;
    }

    @JsonProperty("ttl_liab")
    public Integer getTtlLiab() {
        return ttlLiab;
    }

    @JsonProperty("ttl_liab")
    public void setTtlLiab(Integer ttlLiab) {
        this.ttlLiab = ttlLiab;
    }

    @JsonProperty("gross_adv_pd")
    public Integer getGrossAdvPd() {
        return grossAdvPd;
    }

    @JsonProperty("gross_adv_pd")
    public void setGrossAdvPd(Integer grossAdvPd) {
        this.grossAdvPd = grossAdvPd;
    }

    @JsonProperty("gross_adv_adj")
    public Integer getGrossAdvAdj() {
        return grossAdvAdj;
    }

    @JsonProperty("gross_adv_adj")
    public void setGrossAdvAdj(Integer grossAdvAdj) {
        this.grossAdvAdj = grossAdvAdj;
    }

    @JsonProperty("ttl_rchrg")
    public TtlRchrg getTtlRchrg() {
        return ttlRchrg;
    }

    @JsonProperty("ttl_rchrg")
    public void setTtlRchrg(TtlRchrg ttlRchrg) {
        this.ttlRchrg = ttlRchrg;
    }

    @JsonProperty("ttl_non_rchrg")
    public TtlNonRchrg getTtlNonRchrg() {
        return ttlNonRchrg;
    }

    @JsonProperty("ttl_non_rchrg")
    public void setTtlNonRchrg(TtlNonRchrg ttlNonRchrg) {
        this.ttlNonRchrg = ttlNonRchrg;
    }

	public CptySum[] getCptySum() {
		return cptySum;
	}

	public void setCptySum(CptySum[] cptySum) {
		this.cptySum = cptySum;
	}
	
}
