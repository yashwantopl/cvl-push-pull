package com.opl.mudra.api.oneform.model;

public class MasterResponse {
	private Object id;
	private String value;
	private String name;
	private Integer productId;
	private Long mappingId;
	private String sbiCode;
	private Integer purposeTypeId;
	private String description;
	private String sidbiValue;
	private Integer rate;
	private Integer vehicleType;
	private Integer vehicleCategory;
	private Long amount;
	private String code;

	public MasterResponse() {
		super();
	}

	public MasterResponse(Object id, String value) {
		super();
		this.id = id;
		this.value = value;
		this.name = value;
	}
	public MasterResponse(Long id, String value,String code,Integer ex) {
		super();
		this.id = id;
		this.value = value;
		this.name = value;
		this.code = code;
	}
	
	public MasterResponse(Long mappingId,Object id, String value) {
		super();
		this.id = id;
		this.value = value;
		this.mappingId=mappingId;
	}

	public MasterResponse(Object id, String value, Long mappingId, String sbiCode) {
		super();
		this.id = id;
		this.value = value;
		this.mappingId=mappingId;
		this.sbiCode=sbiCode;
	}
	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getSbiCode() {
		return sbiCode;
	}

	public void setSbiCode(String sbiCode) {
		this.sbiCode = sbiCode;
	}
	public Integer getPurposeTypeId() {
		return purposeTypeId;
	}

	public void setPurposeTypeId(Integer purposeTypeId) {
		this.purposeTypeId = purposeTypeId;
	}
	
	public Integer getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(Integer vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(Integer vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterResponse other = (MasterResponse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSidbiValue() {
		return sidbiValue;
	}

	public void setSidbiValue(String sidbiValue) {
		this.sidbiValue = sidbiValue;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	

}
