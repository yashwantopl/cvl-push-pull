package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "gstin",
    "ret_period",
    "chksum",
    "sec_sum",
    "liabDetl",
    "utilizeCashReqVO"
})
public class Gstr4SummaryTxt {

    @JsonProperty("gstin")
    private String gstin;
  
    @JsonProperty("ret_period")
    private String retPeriod;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("ttl_inv")
    private Integer ttlInv;
    
    @JsonProperty("sec_sum")
    private SecSum[] secSum;

    @JsonProperty("dbtdtl")
    private  Dbtdtl[] dbtdbl;

    @JsonProperty("liabDetl")
    private LiabDetl liabDetl;

    @JsonProperty("utilizeCashReqVO")
    private UtilizeCashReqVO utilizeCashReqVO;

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("ret_period")
    public String getRetPeriod() {
        return retPeriod;
    }

    @JsonProperty("ret_period")
    public void setRetPeriod(String retPeriod) {
        this.retPeriod = retPeriod;
    }

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("liabDetl")
    public LiabDetl getLiabDetl() {
        return liabDetl;
    }

    @JsonProperty("liabDetl")
    public void setLiabDetl(LiabDetl liabDetl) {
        this.liabDetl = liabDetl;
    }

    @JsonProperty("utilizeCashReqVO")
    public UtilizeCashReqVO getUtilizeCashReqVO() {
        return utilizeCashReqVO;
    }

    @JsonProperty("utilizeCashReqVO")
    public void setUtilizeCashReqVO(UtilizeCashReqVO utilizeCashReqVO) {
        this.utilizeCashReqVO = utilizeCashReqVO;
    }

	public SecSum[] getSecSum() {
		return secSum;
	}

	public void setSecSum(SecSum[] secSum) {
		this.secSum = secSum;
	}

	public Integer getTtlInv() {
		return ttlInv;
	}

	public void setTtlInv(Integer ttlInv) {
		this.ttlInv = ttlInv;
	}

	public Dbtdtl[] getDbtdbl() {
		return dbtdbl;
	}

	public void setDbtdbl(Dbtdtl[] dbtdbl) {
		this.dbtdbl = dbtdbl;
	}
	
}
