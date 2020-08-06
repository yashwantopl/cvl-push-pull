
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnr;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "ctin",
    "cfs",
    "nt"
})
public class Cdnr {

    @JsonProperty("ctin")
    private String ctin;
    @JsonProperty("cfs")
    private String cfs;
    @JsonProperty("nt")
    private Nt[] nt;

    @JsonProperty("ctin")
    public String getCtin() {
        return ctin;
    }

    @JsonProperty("ctin")
    public void setCtin(String ctin) {
        this.ctin = ctin;
    }

    @JsonProperty("cfs")
    public String getCfs() {
        return cfs;
    }

    @JsonProperty("cfs")
    public void setCfs(String cfs) {
        this.cfs = cfs;
    }

	public Nt[] getNt() {
		return nt;
	}

	public void setNt(Nt[] nt) {
		this.nt = nt;
	}
    
}
