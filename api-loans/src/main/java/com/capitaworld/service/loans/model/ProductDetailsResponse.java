package com.capitaworld.service.loans.model;

public class ProductDetailsResponse
{
	
	private Integer productId;
	
	private Long productMappingId;
	
	private String message;
	
	private Integer status;
	
	public ProductDetailsResponse()
	{
	}
	
	public ProductDetailsResponse(String message, Integer status) {
		super();
		this.message = message;
		this.status = status;
	}
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatus() {
		return status;
	}




	public void setStatus(Integer status) {
		this.status = status;
	}




	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Long getProductMappingId() {
		return productMappingId;
	}

	public void setProductMappingId(Long productMappingId) {
		this.productMappingId = productMappingId;
	}
	
	

}
