
package com.opl.mudra.api.cibil_integration.msme.request.sbi.individual;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "firstName",
    "lastName",
    "gender",
    "dateOfbirth",
    "identityDetails",
    "phoneNumberDetails",
    "addressDetails",
    "emailDetails"
})
public class PersonalDetails implements Serializable
{

    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("gender")
    private String gender;
    @JsonProperty("dateOfbirth")
    private String dateOfbirth;
    @JsonProperty("identityDetails")
    private List<IdentityDetail> identityDetails = null;
    @JsonProperty("phoneNumberDetails")
    private List<PhoneNumberDetail> phoneNumberDetails = null;
    @JsonProperty("addressDetails")
    private List<AddressDetail> addressDetails = null;
    @JsonProperty("emailDetails")
    private List<EmailDetail> emailDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 1087884078282757626L;

    @JsonProperty("firstName")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("firstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("lastName")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("lastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("gender")
    public String getGender() {
        return gender;
    }

    @JsonProperty("gender")
    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("dateOfbirth")
    public String getDateOfbirth() {
        return dateOfbirth;
    }

    @JsonProperty("dateOfbirth")
    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }

    @JsonProperty("identityDetails")
    public List<IdentityDetail> getIdentityDetails() {
        return identityDetails;
    }

    @JsonProperty("identityDetails")
    public void setIdentityDetails(List<IdentityDetail> identityDetails) {
        this.identityDetails = identityDetails;
    }

    @JsonProperty("phoneNumberDetails")
    public List<PhoneNumberDetail> getPhoneNumberDetails() {
        return phoneNumberDetails;
    }

    @JsonProperty("phoneNumberDetails")
    public void setPhoneNumberDetails(List<PhoneNumberDetail> phoneNumberDetails) {
        this.phoneNumberDetails = phoneNumberDetails;
    }

    @JsonProperty("addressDetails")
    public List<AddressDetail> getAddressDetails() {
        return addressDetails;
    }

    @JsonProperty("addressDetails")
    public void setAddressDetails(List<AddressDetail> addressDetails) {
        this.addressDetails = addressDetails;
    }

    @JsonProperty("emailDetails")
    public List<EmailDetail> getEmailDetails() {
        return emailDetails;
    }

    @JsonProperty("emailDetails")
    public void setEmailDetails(List<EmailDetail> emailDetails) {
        this.emailDetails = emailDetails;
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
