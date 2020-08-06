
package com.opl.mudra.api.cibil.model.msme;

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
    "version",
    "product_type",
    "user_id",
    "user_password",
    "member_code",
    "member_KOB",
    "member_reference_number",
    "report_type",
    "output_format"
})
public class Header {

    @JsonProperty("version")
    private String version;
    @JsonProperty("product_type")
    private String productType;
    @JsonProperty("user_id")
    private String userId;
    @JsonProperty("user_password")
    private String userPassword;
    @JsonProperty("member_code")
    private String memberCode;
    @JsonProperty("member_KOB")
    private String memberKOB;
    @JsonProperty("member_reference_number")
    private String memberReferenceNumber;
    @JsonProperty("report_type")
    private String reportType;
    @JsonProperty("output_format")
    private String outputFormat;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonProperty("product_type")
    public String getProductType() {
        return productType;
    }

    @JsonProperty("product_type")
    public void setProductType(String productType) {
        this.productType = productType;
    }

    @JsonProperty("user_id")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("user_id")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @JsonProperty("user_password")
    public String getUserPassword() {
        return userPassword;
    }

    @JsonProperty("user_password")
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @JsonProperty("member_code")
    public String getMemberCode() {
        return memberCode;
    }

    @JsonProperty("member_code")
    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    @JsonProperty("member_KOB")
    public String getMemberKOB() {
        return memberKOB;
    }

    @JsonProperty("member_KOB")
    public void setMemberKOB(String memberKOB) {
        this.memberKOB = memberKOB;
    }

    @JsonProperty("member_reference_number")
    public String getMemberReferenceNumber() {
        return memberReferenceNumber;
    }

    @JsonProperty("member_reference_number")
    public void setMemberReferenceNumber(String memberReferenceNumber) {
        this.memberReferenceNumber = memberReferenceNumber;
    }

    @JsonProperty("report_type")
    public String getReportType() {
        return reportType;
    }

    @JsonProperty("report_type")
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    @JsonProperty("output_format")
    public String getOutputFormat() {
        return outputFormat;
    }

    @JsonProperty("output_format")
    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
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
