
package com.opl.mudra.api.gst.model.yuva.response.gstr1hsnsummary;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 
 * @author vijay.chauhan
 * Jan 28, 2020 12:12:50 PM
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonPropertyOrder({
    "flag",
    "chksum",
    "data"
})
public class Hsn {

    @JsonProperty("flag")
    private String flag;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("data")
    private List<Data> data = new ArrayList<Data>();

    @JsonProperty("flag")
    public String getFlag() {
        return flag;
    }

    @JsonProperty("flag")
    public void setFlag(String flag) {
        this.flag = flag;
    }

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("data")
    public List<Data> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Data> data) {
        this.data = data;
    }


}
