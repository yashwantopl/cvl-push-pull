package com.capitaworld.service.loans.model;

import java.io.Serializable;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the fs_corporate_proposed_product_details database table.
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProposedProductDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String application;

	private Long applicationId;

	private String product;
	
	private Boolean isActive = true;
	
	
	

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public ProposedProductDetailRequest() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getApplication() {
		return this.application;
	}

	public void setApplication(String application) {
		this.application = application;
	}

	public Long getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}


	public String getProduct() {
		return this.product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	
	 public static void printFields(Object obj) throws Exception {
         Field[] fields = ProposedProductDetailRequest.class.getDeclaredFields();
         
         for(Field field : fields) {
             Object value = field.get(obj);
             if(value instanceof String){
              String a = value.toString().replaceAll("&", "&amp;");
              value = a;
              field.set(obj, value);
             }
         }
     }

}