
package com.opl.mudra.api.gst.model.yuva.response.gstr4summary;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "rev",
    "nonRev"
})
public class LiabDetl {

    @JsonProperty("rev")
    private Rev rev;
    @JsonProperty("nonRev")
    private NonRev nonRev;

    @JsonProperty("rev")
    public Rev getRev() {
        return rev;
    }

    @JsonProperty("rev")
    public void setRev(Rev rev) {
        this.rev = rev;
    }

    @JsonProperty("nonRev")
    public NonRev getNonRev() {
        return nonRev;
    }

    @JsonProperty("nonRev")
    public void setNonRev(NonRev nonRev) {
        this.nonRev = nonRev;
    }
}
