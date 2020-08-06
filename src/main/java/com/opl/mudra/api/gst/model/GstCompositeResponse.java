/**
 * 
 */
package com.opl.mudra.api.gst.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay.darji
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class GstCompositeResponse implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer status;

	private String message;

	private Object rdata;
	
	private Long id;

	private Long applicationId;

	private String compositeData;

	private Timestamp createdDate;

	private String gstIn;

	private String gstin;

	private Boolean isActive;

	private Timestamp modifiedDate;
	
	
	
	private String username;
	
	private Long concentration;
	
	private Long noOfCustomer;
	
	private Long projectedSales;
	
	private Map<String, Map<String, Double>> momSales;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	
	

	

	/**
	 * @return the gstin
	 */
	public String getGstin() {
		return gstin;
	}

	/**
	 * @param gstin the gstin to set
	 */
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getRdata() {
		return rdata;
	}

	public void setRdata(Object rdata) {
		this.rdata = rdata;
	}

	public String getCompositeData() {
		return compositeData;
	}

	public void setCompositeData(String compositeData) {
		this.compositeData = compositeData;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}



	/**
	 * @return the gstIn
	 */
	public String getGstIn() {
		return gstIn;
	}

	/**
	 * @param gstIn the gstIn to set
	 */
	public void setGstIn(String gstIn) {
		this.gstIn = gstIn;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	

	public GstCompositeResponse() {
		super();
	}

	public GstCompositeResponse(Integer status, String message, String gstin, Long applicationId) {
		super();
		this.status=status;
		this.message = message;
		this.gstIn = gstin;
		this.applicationId=applicationId;
		/*this.rdata = rdata;*/
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getConcentration() {
		return concentration;
	}

	public void setConcentration(Long concentration) {
		this.concentration = concentration;
	}

	public Long getNoOfCustomer() {
		return noOfCustomer;
	}

	public void setNoOfCustomer(Long noOfCustomer) {
		this.noOfCustomer = noOfCustomer;
	}

	public Long getProjectedSales() {
		return projectedSales;
	}

	public void setProjectedSales(Long projectedSales) {
		this.projectedSales = projectedSales;
	}

	public Map<String, Map<String, Double>> getMomSales() {
		return momSales;
	}

	public void setMomSales(Map<String, Map<String, Double>> momSales) {
		this.momSales = momSales;
	}

	
	
 
	
	
}
