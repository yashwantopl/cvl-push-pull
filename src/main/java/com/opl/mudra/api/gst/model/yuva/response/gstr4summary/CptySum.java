
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "ctin",
    "chksum",
    "ttl_rec",
    "ttl_val",
    "ttl_tax",
    "ttl_igst",
    "ttl_sgst",
    "ttl_cgst",
    "ttl_cess"
})
public class CptySum {

    @JsonProperty("ctin")
    private String ctin;
    @JsonProperty("trade_name")
    private String tradeName;
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

    @JsonProperty("ctin")
    public String getCtin() {
        return ctin;
    }

    @JsonProperty("ctin")
    public void setCtin(String ctin) {
        this.ctin = ctin;
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

	public String getTradeName() {
		return tradeName;
	}

	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}

	public Integer getTtlLiab() {
		return ttlLiab;
	}

	public void setTtlLiab(Integer ttlLiab) {
		this.ttlLiab = ttlLiab;
	}
    
}
