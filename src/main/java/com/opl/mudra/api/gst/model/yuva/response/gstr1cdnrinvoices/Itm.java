
package com.opl.mudra.api.gst.model.yuva.response.gstr1cdnrinvoices;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 
 * @author vijay.chauhan
 *
 */


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
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
