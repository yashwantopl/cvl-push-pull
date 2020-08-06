
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "gstin",
    "ret_type",
    "ret_period",
    "op_liab"
})
public class UtilizeCashReqVO {

    @JsonProperty("gstin")
    private String gstin;
    @JsonProperty("ret_type")
    private String retType;
    @JsonProperty("ret_period")
    private String retPeriod;
    @JsonProperty("op_liab")
    private OpLiab[] opLiab;

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("ret_type")
    public String getRetType() {
        return retType;
    }

    @JsonProperty("ret_type")
    public void setRetType(String retType) {
        this.retType = retType;
    }

    @JsonProperty("ret_period")
    public String getRetPeriod() {
        return retPeriod;
    }

    @JsonProperty("ret_period")
    public void setRetPeriod(String retPeriod) {
        this.retPeriod = retPeriod;
    }

	public OpLiab[] getOpLiab() {
		return opLiab;
	}

	public void setOpLiab(OpLiab[] opLiab) {
		this.opLiab = opLiab;
	}

}
