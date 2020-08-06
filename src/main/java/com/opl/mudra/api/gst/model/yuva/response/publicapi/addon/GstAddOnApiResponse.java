
package com.opl.mudra.api.gst.model.yuva.response.publicapi.addon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GstAddOnApiResponse implements Serializable{
    private static final long serialVersionUID = 1L;

    @JsonProperty("status_cd")
    private String statusCd;

    @JsonProperty("data")
    private List<TaxPayersByPanResponse> data = new ArrayList<TaxPayersByPanResponse>();

    private Error error;

    @JsonProperty("status_cd")
    public String getStatusCd() {
        return statusCd;
    }

    @JsonProperty("status_cd")
    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    @JsonProperty("data")
    public List<TaxPayersByPanResponse> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<TaxPayersByPanResponse> data) {
        this.data = data;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }


}
