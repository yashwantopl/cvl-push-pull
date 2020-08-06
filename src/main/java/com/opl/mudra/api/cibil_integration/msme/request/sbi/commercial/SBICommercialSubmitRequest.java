package com.opl.mudra.api.cibil_integration.msme.request.sbi.commercial;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"userId",
	"productDetails",
	"borrowerDetails"
})
public class SBICommercialSubmitRequest {

	@JsonProperty("userId")
	private Integer userId;
	@JsonProperty("productDetails")
	private ProductDetails productDetails;
	@JsonProperty("borrowerDetails")
	private BorrowerDetails borrowerDetails;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	@JsonProperty("borrowerDetails")
	public BorrowerDetails getBorrowerDetails() {
		return borrowerDetails;
	}

	@JsonProperty("borrowerDetails")
	public void setBorrowerDetails(BorrowerDetails borrowerDetails) {
		this.borrowerDetails = borrowerDetails;
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