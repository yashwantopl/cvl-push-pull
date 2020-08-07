
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "relation_type",
    "gender",
    "pan",
    "uid",
    "voter_id",
    "passport_num",
    "driving_licence_id",
    "din",
    "dob",
    "address",
    "telephone"
})
public class Director {

    @JsonProperty("name")
    private String name;
    @JsonProperty("relation_type")
    private String relationType;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("pan")
    private String pan;
    @JsonProperty("uid")
    private String uid;
    @JsonProperty("voter_id")
    private String voterId;
    @JsonProperty("passport_num")
    private String passportNum;
    @JsonProperty("driving_licence_id")
    private String drivingLicenceId;
    @JsonProperty("din")
    private String din;
    @JsonProperty("dob")
    private String dob;
    @JsonProperty("address")
    private Address_ address;
    @JsonProperty("telephone")
    private List<Telephone_> telephone = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("relation_type")
    public String getRelationType() {
        return relationType;
    }

    @JsonProperty("relation_type")
    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("pan")
    public String getPan() {
        return pan;
    }

    @JsonProperty("pan")
    public void setPan(String pan) {
        this.pan = pan;
    }

    @JsonProperty("uid")
    public String getUid() {
        return uid;
    }

    @JsonProperty("uid")
    public void setUid(String uid) {
        this.uid = uid;
    }

    @JsonProperty("voter_id")
    public String getVoterId() {
        return voterId;
    }

    @JsonProperty("voter_id")
    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    @JsonProperty("passport_num")
    public String getPassportNum() {
        return passportNum;
    }

    @JsonProperty("passport_num")
    public void setPassportNum(String passportNum) {
        this.passportNum = passportNum;
    }

    @JsonProperty("driving_licence_id")
    public String getDrivingLicenceId() {
        return drivingLicenceId;
    }

    @JsonProperty("driving_licence_id")
    public void setDrivingLicenceId(String drivingLicenceId) {
        this.drivingLicenceId = drivingLicenceId;
    }

    @JsonProperty("din")
    public String getDin() {
        return din;
    }

    @JsonProperty("din")
    public void setDin(String din) {
        this.din = din;
    }

    @JsonProperty("dob")
    public String getDob() {
        return dob;
    }

    @JsonProperty("dob")
    public void setDob(String dob) {
        this.dob = dob;
    }

    @JsonProperty("address")
    public Address_ getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address_ address) {
        this.address = address;
    }

    @JsonProperty("telephone")
    public List<Telephone_> getTelephone() {
        return telephone;
    }

    @JsonProperty("telephone")
    public void setTelephone(List<Telephone_> telephone) {
        this.telephone = telephone;
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
