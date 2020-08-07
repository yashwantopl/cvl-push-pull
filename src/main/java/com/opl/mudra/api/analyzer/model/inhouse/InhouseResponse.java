
package com.opl.mudra.api.analyzer.model.inhouse;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)

@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
        "files",
        "monthlydetails",
        "summaryInfo",
        "xns",
        "globtimeperiod",
        "bouncePenalXns",
        "appid",
        "accnum",
        "multiacc",
        "encrypted",
        "scanned",
        "fileExists",
        "validcustomerinfo",
        "inconsistent",
        "inconsistentbetween",
        "betweenDates",
        "errorCode",
        "anyMonthWithzeroTxn",
        "monthsWithZeroTxn",
        "errorMsg"
})
public class InhouseResponse {

    @JsonProperty("files")
    private List<File> files = new ArrayList<File>();
    @JsonProperty("monthlydetails")
    private List<Monthlydetail> monthlydetails = new ArrayList<Monthlydetail>();
    @JsonProperty("summaryInfo")
    private SummaryInfo summaryInfo;
    @JsonProperty("xns")
    private List<Xn> xns = new ArrayList<Xn>();
    @JsonProperty("bouncePenalXns")
    private List<BouncePenalXn> bouncePenalXns = new ArrayList<BouncePenalXn>();
    @JsonProperty("appid")
    private String appid;
    @JsonProperty("errorCode")
    private String errorCode;
    @JsonProperty("errorMsg")
    private String errorMsg;
    @JsonProperty("isReportGen")
    private Boolean isReportGen;
    @JsonProperty("accnum")
    private List<String> accnum = new ArrayList<String>();
    @JsonProperty("multiacc")
    private Boolean multiacc;
    @JsonProperty("encrypted")
    private Boolean encrypted;
    @JsonProperty("scanned")
    private Boolean scanned;
    @JsonProperty("fileExists")
    private Boolean fileExists;
    @JsonProperty("validcustomerinfo")
    private Boolean validcustomerinfo;
    @JsonProperty("inconsistent")
    private Boolean inconsistent;
    @JsonProperty("inconsistentbetween")
    private List<Object> inconsistentbetween = new ArrayList<Object>();
    @JsonProperty("betweenDates")
    private Boolean betweenDates;
    @JsonProperty("monthsWithZeroTxn")
    private List<String> monthsWithZeroTxn;
    @JsonProperty("anyMonthWithzeroTxn")
    private Boolean anyMonthWithzeroTxn;
    @JsonProperty("globtimeperiodmonths")
    private List<String> globtimeperiod;


    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("files")
    public List<File> getFiles() {
        return files;
    }

    @JsonProperty("files")
    public void setFiles(List<File> files) {
        this.files = files;
    }

    @JsonProperty("monthlydetails")
    public List<Monthlydetail> getMonthlydetails() {
        return monthlydetails;
    }

    @JsonProperty("monthlydetails")
    public void setMonthlydetails(List<Monthlydetail> monthlydetails) {
        this.monthlydetails = monthlydetails;
    }

    @JsonProperty("summaryInfo")
    public SummaryInfo getSummaryInfo() {
        return summaryInfo;
    }

    @JsonProperty("summaryInfo")
    public void setSummaryInfo(SummaryInfo summaryInfo) {
        this.summaryInfo = summaryInfo;
    }

    @JsonProperty("xns")
    public List<Xn> getXns() {
        return xns;
    }

    @JsonProperty("xns")
    public void setXns(List<Xn> xns) {
        this.xns = xns;
    }

    @JsonProperty("bouncePenalXns")
    public List<BouncePenalXn> getBouncePenalXns() {
        return bouncePenalXns;
    }

    @JsonProperty("bouncePenalXns")
    public void setBouncePenalXns(List<BouncePenalXn> bouncePenalXns) {
        this.bouncePenalXns = bouncePenalXns;
    }

    @JsonProperty("appid")
    public String getAppid() {
        return appid;
    }

    @JsonProperty("appid")
    public void setAppid(String appid) {
        this.appid = appid;
    }

    @JsonProperty("accnum")
    public List<String> getAccnum() {
        return accnum;
    }

    @JsonProperty("accnum")
    public void setAccnum(List<String> accnum) {
        this.accnum = accnum;
    }

    @JsonProperty("multiacc")
    public Boolean getMultiacc() {
        return multiacc;
    }

    @JsonProperty("multiacc")
    public void setMultiacc(Boolean multiacc) {
        this.multiacc = multiacc;
    }

    @JsonProperty("encrypted")
    public Boolean getEncrypted() {
        return encrypted;
    }

    @JsonProperty("encrypted")
    public void setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
    }

    @JsonProperty("scanned")
    public Boolean getScanned() {
        return scanned;
    }

    @JsonProperty("scanned")
    public void setScanned(Boolean scanned) {
        this.scanned = scanned;
    }

    @JsonProperty("fileExists")
    public Boolean getFileExists() {
        return fileExists;
    }

    @JsonProperty("fileExists")
    public void setFileExists(Boolean fileExists) {
        this.fileExists = fileExists;
    }

    @JsonProperty("validcustomerinfo")
    public Boolean getValidcustomerinfo() {
        return validcustomerinfo;
    }

    @JsonProperty("validcustomerinfo")
    public void setValidcustomerinfo(Boolean validcustomerinfo) {
        this.validcustomerinfo = validcustomerinfo;
    }

    @JsonProperty("inconsistent")
    public Boolean getInconsistent() {
        return inconsistent;
    }

    @JsonProperty("inconsistent")
    public void setInconsistent(Boolean inconsistent) {
        this.inconsistent = inconsistent;
    }

    @JsonProperty("inconsistentbetween")
    public List<Object> getInconsistentbetween() {
        return inconsistentbetween;
    }

    @JsonProperty("inconsistentbetween")
    public void setInconsistentbetween(List<Object> inconsistentbetween) {
        this.inconsistentbetween = inconsistentbetween;
    }

    @JsonProperty("betweenDates")
    public Boolean getBetweenDates() {
        return betweenDates;
    }

    @JsonProperty("betweenDates")
    public void setBetweenDates(Boolean betweenDates) {
        this.betweenDates = betweenDates;
    }


    @JsonProperty("errorCode")
    public String getErrorCode() {
        return errorCode;
    }
    @JsonProperty("errorCode")
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    @JsonProperty("errorMsg")
    public String getErrorMsg() {
        return errorMsg;
    }

    @JsonProperty("errorMsg")
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    @JsonProperty("isReportGen")
    public Boolean getIsReportGen() {
        return isReportGen;
    }
    @JsonProperty("isReportGen")
    public void setIsReportGen(Boolean isReportGen) {
        this.isReportGen = isReportGen;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public List<String> getMonthsWithZeroTxn() {
        return monthsWithZeroTxn;
    }

    public void setMonthsWithZeroTxn(List<String> monthsWithZeroTxn) {
        this.monthsWithZeroTxn = monthsWithZeroTxn;
    }

    public Boolean getAnyMonthWithzeroTxn() {
        return anyMonthWithzeroTxn;
    }

    public void setAnyMonthWithzeroTxn(Boolean anyMonthWithzeroTxn) {
        this.anyMonthWithzeroTxn = anyMonthWithzeroTxn;
    }

    public List<String> getGlobtimeperiod() {
        return globtimeperiod;
    }

    public void setGlobtimeperiod(List<String> globtimeperiod) {
        this.globtimeperiod = globtimeperiod;
    }

}
