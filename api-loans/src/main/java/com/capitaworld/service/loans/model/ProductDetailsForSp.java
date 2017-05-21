package com.capitaworld.service.loans.model;

public class ProductDetailsForSp {

	private Long id;
	
	private Integer productId;
	
	private String name;

	
	
	public ProductDetailsForSp(Long id, Integer productId, String name) {
		super();
		this.id = id;
		this.productId = productId;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	

	
	
	
}
