
package com.opl.mudra.api.cibil_integration.msme;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "general_fields",
    "company_name",
    "contact",
    "id",
    "directors"
})
public class SearchData {

    @JsonProperty("general_fields")
    private GeneralFields generalFields;
    @JsonProperty("company_name")
    private CompanyName companyName;
    @JsonProperty("contact")
    private Contact contact;
    @JsonProperty("id")
    private Id id;
    @JsonProperty("directors")
    private Directors directors;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("general_fields")
    public GeneralFields getGeneralFields() {
        return generalFields;
    }

    @JsonProperty("general_fields")
    public void setGeneralFields(GeneralFields generalFields) {
        this.generalFields = generalFields;
    }

    @JsonProperty("company_name")
    public CompanyName getCompanyName() {
        return companyName;
    }

    @JsonProperty("company_name")
    public void setCompanyName(CompanyName companyName) {
        this.companyName = companyName;
    }

    @JsonProperty("contact")
    public Contact getContact() {
        return contact;
    }

    @JsonProperty("contact")
    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @JsonProperty("id")
    public Id getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Id id) {
        this.id = id;
    }

    @JsonProperty("directors")
    public Directors getDirectors() {
        return directors;
    }

    @JsonProperty("directors")
    public void setDirectors(Directors directors) {
        this.directors = directors;
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
