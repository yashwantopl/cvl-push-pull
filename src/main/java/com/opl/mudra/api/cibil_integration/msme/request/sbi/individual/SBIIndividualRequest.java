
package com.opl.mudra.api.cibil_integration.msme.request.sbi.individual;

import com.fasterxml.jackson.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "productDetails",
    "personalDetails"
})
public class SBIIndividualRequest implements Serializable
{

    @JsonProperty("userId")
    private Integer userId;
    @JsonProperty("productDetails")
    private ProductDetails productDetails;
    @JsonProperty("personalDetails")
    private PersonalDetails personalDetails;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = 5849002268791885718L;

    @JsonProperty("userId")
    public Integer getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @JsonProperty("productDetails")
    public ProductDetails getProductDetails() {
        return productDetails;
    }

    @JsonProperty("productDetails")
    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    @JsonProperty("personalDetails")
    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @JsonProperty("personalDetails")
    public void setPersonalDetails(PersonalDetails personalDetails) {
        this.personalDetails = personalDetails;
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
