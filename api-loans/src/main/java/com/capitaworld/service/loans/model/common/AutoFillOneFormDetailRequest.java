package com.capitaworld.service.loans.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutoFillOneFormDetailRequest {
	private Long fromApplicationId;
	private Long fromProductId;
	private Long toApplicationId;
	private Long toProductId;

	public Long getFromApplicationId() {
		return fromApplicationId;
	}

	public void setFromApplicationId(Long fromApplicationId) {
		this.fromApplicationId = fromApplicationId;
	}

	public Long getFromProductId() {
		return fromProductId;
	}

	public void setFromProductId(Long fromProductId) {
		this.fromProductId = fromProductId;
	}

	public Long getToApplicationId() {
		return toApplicationId;
	}

	public void setToApplicationId(Long toApplicationId) {
		this.toApplicationId = toApplicationId;
	}

	public Long getToProductId() {
		return toProductId;
	}

	public void setToProductId(Long toProductId) {
		this.toProductId = toProductId;
	}

}
