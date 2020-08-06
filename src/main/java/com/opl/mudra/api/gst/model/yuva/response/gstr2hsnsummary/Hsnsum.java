
package com.opl.mudra.api.gst.model.yuva.response.gstr2hsnsummary;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author vijay.chauhan
 * Jan 28, 2020 2:58:33 PM
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "chksum",
    "det"
})
public class Hsnsum {

    @JsonProperty("chksum")
    private String chksum;
    @JsonProperty("det")
    private List<Det> det = new ArrayList<Det>();

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("det")
    public List<Det> getDet() {
        return det;
    }

    @JsonProperty("det")
    public void setDet(List<Det> det) {
        this.det = det;
    }

}
