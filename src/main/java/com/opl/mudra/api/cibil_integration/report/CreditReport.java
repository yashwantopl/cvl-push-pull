
package com.opl.mudra.api.cibil_integration.report;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "NameSegment",
    "Account",
    "Enquiry",
    "EmailContactSegment",
    "Header",
    "Address",
    "EmploymentSegment",
    "IDSegment",
    "End",
    "TelephoneSegment",
    "ScoreSegment"
})
public class CreditReport implements Serializable
{

    @JsonProperty("NameSegment")
    private NameSegment nameSegment;
    @JsonProperty("Account")
    private List<Account> account = null;
    @JsonProperty("Enquiry")
    private List<Enquiry> enquiry = null;
    @JsonProperty("EmailContactSegment")
    private List<EmailContactSegment> emailContactSegment = null;
    @JsonProperty("Header")
    private Header header;
    @JsonProperty("Address")
    private List<Address> address = null;
    @JsonProperty("EmploymentSegment")
    private EmploymentSegment employmentSegment;
    @JsonProperty("IDSegment")
    private List<IDSegment> iDSegment = null;
    @JsonProperty("End")
    private End end;
    @JsonProperty("TelephoneSegment")
    private List<TelephoneSegment> telephoneSegment = null;
    @JsonProperty("ScoreSegment")
    private ScoreSegment scoreSegment;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 538191596725064168L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CreditReport() {
    }

    /**
     * 
     * @param iDSegment
     * @param enquiry
     * @param emailContactSegment
     * @param address
     * @param employmentSegment
     * @param account
     * @param scoreSegment
     * @param end
     * @param header
     * @param telephoneSegment
     * @param nameSegment
     */
    public CreditReport(NameSegment nameSegment, List<Account> account, List<Enquiry> enquiry, List<EmailContactSegment> emailContactSegment, Header header, List<Address> address, EmploymentSegment employmentSegment, List<IDSegment> iDSegment, End end, List<TelephoneSegment> telephoneSegment, ScoreSegment scoreSegment) {
        super();
        this.nameSegment = nameSegment;
        this.account = account;
        this.enquiry = enquiry;
        this.emailContactSegment = emailContactSegment;
        this.header = header;
        this.address = address;
        this.employmentSegment = employmentSegment;
        this.iDSegment = iDSegment;
        this.end = end;
        this.telephoneSegment = telephoneSegment;
        this.scoreSegment = scoreSegment;
    }

    @JsonProperty("NameSegment")
    public NameSegment getNameSegment() {
        return nameSegment;
    }

    @JsonProperty("NameSegment")
    public void setNameSegment(NameSegment nameSegment) {
        this.nameSegment = nameSegment;
    }

    @JsonProperty("Account")
    public List<Account> getAccount() {
        return account;
    }

    @JsonProperty("Account")
    public void setAccount(List<Account> account) {
        this.account = account;
    }

    @JsonProperty("Enquiry")
    public List<Enquiry> getEnquiry() {
        return enquiry;
    }

    @JsonProperty("Enquiry")
    public void setEnquiry(List<Enquiry> enquiry) {
        this.enquiry = enquiry;
    }

    @JsonProperty("EmailContactSegment")
    public List<EmailContactSegment> getEmailContactSegment() {
        return emailContactSegment;
    }

    @JsonProperty("EmailContactSegment")
    public void setEmailContactSegment(List<EmailContactSegment> emailContactSegment) {
        this.emailContactSegment = emailContactSegment;
    }

    @JsonProperty("Header")
    public Header getHeader() {
        return header;
    }

    @JsonProperty("Header")
    public void setHeader(Header header) {
        this.header = header;
    }

    @JsonProperty("Address")
    public List<Address> getAddress() {
        return address;
    }

    @JsonProperty("Address")
    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @JsonProperty("EmploymentSegment")
    public EmploymentSegment getEmploymentSegment() {
        return employmentSegment;
    }

    @JsonProperty("EmploymentSegment")
    public void setEmploymentSegment(EmploymentSegment employmentSegment) {
        this.employmentSegment = employmentSegment;
    }

    @JsonProperty("IDSegment")
    public List<IDSegment> getIDSegment() {
        return iDSegment;
    }

    @JsonProperty("IDSegment")
    public void setIDSegment(List<IDSegment> iDSegment) {
        this.iDSegment = iDSegment;
    }

    @JsonProperty("End")
    public End getEnd() {
        return end;
    }

    @JsonProperty("End")
    public void setEnd(End end) {
        this.end = end;
    }

    @JsonProperty("TelephoneSegment")
    public List<TelephoneSegment> getTelephoneSegment() {
        return telephoneSegment;
    }

    @JsonProperty("TelephoneSegment")
    public void setTelephoneSegment(List<TelephoneSegment> telephoneSegment) {
        this.telephoneSegment = telephoneSegment;
    }

    @JsonProperty("ScoreSegment")
    public ScoreSegment getScoreSegment() {
        return scoreSegment;
    }

    @JsonProperty("ScoreSegment")
    public void setScoreSegment(ScoreSegment scoreSegment) {
        this.scoreSegment = scoreSegment;
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
