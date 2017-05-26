package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fp_product_master database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductMasterRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private Integer productId;

	private Date startDate;
	
	private Date endDate;

	private Long userId;


	
	public ProductMasterRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}