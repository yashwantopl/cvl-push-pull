
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnur;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "cdnur"
})
public class Gstr4CDNURTxt {

    @JsonProperty("cdnur")
    private Cdnur[] cdnur;

	public Cdnur[] getCdnur() {
		return cdnur;
	}

	public void setCdnur(Cdnur[] cdnur) {
		this.cdnur = cdnur;
	}
}
