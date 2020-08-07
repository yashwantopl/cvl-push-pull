
package com.opl.mudra.api.gst.model.yuva.response.gstr1hsnsummary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.GSTDataResponse;

/**
 * 
 * @author vijay.chauhan
 * Jan 28, 2020 12:12:42 PM
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "hsn"
})
public class Gstr1HsnSummaryResponse  extends GSTDataResponse {

    @JsonProperty("hsn")
    private Hsn hsn;

    @JsonProperty("hsn")
    public Hsn getHsn() {
        return hsn;
    }

    @JsonProperty("hsn")
    public void setHsn(Hsn hsn) {
        this.hsn = hsn;
    }


}
