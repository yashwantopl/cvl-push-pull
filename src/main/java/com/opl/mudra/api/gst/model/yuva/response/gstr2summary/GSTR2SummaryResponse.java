
package com.opl.mudra.api.gst.model.yuva.response.gstr2summary;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.opl.mudra.api.gst.model.yuva.response.gstr1summary.GSTDataResponse;


/**
 * 
 * @author vijay.chauhan
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_NULL)

public class GSTR2SummaryResponse extends GSTDataResponse {

	@JsonProperty("gstin")
    private String gstin;
    
    @JsonProperty("ret_period")
    private String retPeriod;
    
    @JsonProperty("chksum")
    private String chksum;
    
    @JsonProperty("summ_typ")
    private String summTyp;
    
    @JsonProperty("section_summary")
    private List<SectionSummary> sectionSummary = new ArrayList<>();
    

    @JsonProperty("gstin")
    public String getGstin() {
        return gstin;
    }

    @JsonProperty("gstin")
    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    @JsonProperty("ret_period")
    public String getRetPeriod() {
        return retPeriod;
    }

    @JsonProperty("ret_period")
    public void setRetPeriod(String retPeriod) {
        this.retPeriod = retPeriod;
    }

    @JsonProperty("chksum")
    public String getChksum() {
        return chksum;
    }

    @JsonProperty("chksum")
    public void setChksum(String chksum) {
        this.chksum = chksum;
    }

    @JsonProperty("summ_typ")
    public String getSummTyp() {
        return summTyp;
    }

    @JsonProperty("summ_typ")
    public void setSummTyp(String summTyp) {
        this.summTyp = summTyp;
    }

    @JsonProperty("section_summary")
    public List<SectionSummary> getSectionSummary() {
        return sectionSummary;
    }

    @JsonProperty("section_summary")
    public void setSectionSummary(List<SectionSummary> sectionSummary) {
        this.sectionSummary = sectionSummary;
    }

}
