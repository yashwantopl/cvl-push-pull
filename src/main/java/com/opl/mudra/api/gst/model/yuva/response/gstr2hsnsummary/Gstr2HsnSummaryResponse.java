
package com.opl.mudra.api.gst.model.yuva.response.gstr2hsnsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.GSTDataResponse;

/**
 * 
 * @author vijay.chauhan
 * Jan 28, 2020 2:57:01 PM
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "hsnsum"
})
public class Gstr2HsnSummaryResponse  extends GSTDataResponse {

    @JsonProperty("hsnsum")
    private Hsnsum hsnsum;

    @JsonProperty("hsnsum")
    public Hsnsum getHsnsum() {
        return hsnsum;
    }

    @JsonProperty("hsnsum")
    public void setHsnsum(Hsnsum hsnsum) {
        this.hsnsum = hsnsum;
    }

}
