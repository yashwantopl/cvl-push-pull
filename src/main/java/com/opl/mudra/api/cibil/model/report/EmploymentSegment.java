
package com.opl.mudra.api.cibil.model.report;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "DateReportedCertified",
    "NetGrossIndicator",
    "Length",
    "Income",
    "SegmentTag",
    "OccupationCode",
    "IncomeFieldLength",
    "AccountType",
    "MonthlyAnnualIndicator"
})
public class EmploymentSegment implements Serializable
{

    @JsonProperty("DateReportedCertified")
    private String dateReportedCertified;
    @JsonProperty("NetGrossIndicator")
    private String netGrossIndicator;
    @JsonProperty("Length")
    private String length;
    @JsonProperty("Income")
    private Long income;
    @JsonProperty("SegmentTag")
    private String segmentTag;
    @JsonProperty("OccupationCode")
    private String occupationCode;
    @JsonProperty("IncomeFieldLength")
    private String incomeFieldLength;
    @JsonProperty("AccountType")
    private Integer accountType;
    @JsonProperty("MonthlyAnnualIndicator")
    private String monthlyAnnualIndicator;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 4173091152109212457L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public EmploymentSegment() {
    }

    /**
     * 
     * @param incomeFieldLength
     * @param income
     * @param dateReportedCertified
     * @param accountType
     * @param length
     * @param netGrossIndicator
     * @param occupationCode
     * @param monthlyAnnualIndicator
     * @param segmentTag
     */
    public EmploymentSegment(String dateReportedCertified, String netGrossIndicator, String length, Long income, String segmentTag, String occupationCode, String incomeFieldLength, Integer accountType, String monthlyAnnualIndicator) {
        super();
        this.dateReportedCertified = dateReportedCertified;
        this.netGrossIndicator = netGrossIndicator;
        this.length = length;
        this.income = income;
        this.segmentTag = segmentTag;
        this.occupationCode = occupationCode;
        this.incomeFieldLength = incomeFieldLength;
        this.accountType = accountType;
        this.monthlyAnnualIndicator = monthlyAnnualIndicator;
    }

    @JsonProperty("DateReportedCertified")
    public String getDateReportedCertified() {
        return dateReportedCertified;
    }

    @JsonProperty("DateReportedCertified")
    public void setDateReportedCertified(String dateReportedCertified) {
        this.dateReportedCertified = dateReportedCertified;
    }

    @JsonProperty("NetGrossIndicator")
    public String getNetGrossIndicator() {
        return netGrossIndicator;
    }

    @JsonProperty("NetGrossIndicator")
    public void setNetGrossIndicator(String netGrossIndicator) {
        this.netGrossIndicator = netGrossIndicator;
    }

    @JsonProperty("Length")
    public String getLength() {
        return length;
    }

    @JsonProperty("Length")
    public void setLength(String length) {
        this.length = length;
    }

    @JsonProperty("Income")
    public Long getIncome() {
        return income;
    }

    @JsonProperty("Income")
    public void setIncome(Long income) {
        this.income = income;
    }

    @JsonProperty("SegmentTag")
    public String getSegmentTag() {
        return segmentTag;
    }

    @JsonProperty("SegmentTag")
    public void setSegmentTag(String segmentTag) {
        this.segmentTag = segmentTag;
    }

    @JsonProperty("OccupationCode")
    public String getOccupationCode() {
        return occupationCode;
    }

    @JsonProperty("OccupationCode")
    public void setOccupationCode(String occupationCode) {
        this.occupationCode = occupationCode;
    }

    @JsonProperty("IncomeFieldLength")
    public String getIncomeFieldLength() {
        return incomeFieldLength;
    }

    @JsonProperty("IncomeFieldLength")
    public void setIncomeFieldLength(String incomeFieldLength) {
        this.incomeFieldLength = incomeFieldLength;
    }

    @JsonProperty("AccountType")
    public Integer getAccountType() {
        return accountType;
    }

    @JsonProperty("AccountType")
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    @JsonProperty("MonthlyAnnualIndicator")
    public String getMonthlyAnnualIndicator() {
        return monthlyAnnualIndicator;
    }

    @JsonProperty("MonthlyAnnualIndicator")
    public void setMonthlyAnnualIndicator(String monthlyAnnualIndicator) {
        this.monthlyAnnualIndicator = monthlyAnnualIndicator;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
