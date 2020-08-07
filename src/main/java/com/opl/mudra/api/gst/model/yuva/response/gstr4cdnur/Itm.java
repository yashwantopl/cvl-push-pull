
package com.opl.mudra.api.gst.model.yuva.response.gstr4cdnur;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "num",
    "itm_det"
})
public class Itm {

    @JsonProperty("num")
    private Integer num;
    @JsonProperty("itm_det")
    private ItmDet itmDet;

    @JsonProperty("num")
    public Integer getNum() {
        return num;
    }

    @JsonProperty("num")
    public void setNum(Integer num) {
        this.num = num;
    }

    @JsonProperty("itm_det")
    public ItmDet getItmDet() {
        return itmDet;
    }

    @JsonProperty("itm_det")
    public void setItmDet(ItmDet itmDet) {
        this.itmDet = itmDet;
    }

}
