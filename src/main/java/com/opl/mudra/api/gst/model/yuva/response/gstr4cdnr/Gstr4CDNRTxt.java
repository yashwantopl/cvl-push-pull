
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnr;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "cdnr"
})
public class Gstr4CDNRTxt {

    @JsonProperty("cdnr")
    private Cdnr[] cdnr;

	public Cdnr[] getCdnr() {
		return cdnr;
	}

	public void setCdnr(Cdnr[] cdnr) {
		this.cdnr = cdnr;
	}
    
    
 }
