package com.capitaworld.service.loans.model.ddr;

import com.capitaworld.service.loans.model.FinancialArrangementDetailResponseString;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDRExistingBankerDetailRequest implements Serializable {
	private static final long serialVersionUID = 1L;

    private Long id;

    private Long ddrFormId;
    
    private Long financialArrangementId;

    private String financialInstitutionName;

    private String address;

    private Integer relationshipSince;

    private Long createdBy;

    private Date createdDate;

    private Long modifyBy;

    private Date modifyDate;

    private Boolean isActive;
    
    private FinancialArrangementDetailResponseString finArraRes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDdrFormId() {
        return ddrFormId;
    }

    public void setDdrFormId(Long ddrFormId) {
        this.ddrFormId = ddrFormId;
    }

    public String getFinancialInstitutionName() {
        return financialInstitutionName;
    }

    public void setFinancialInstitutionName(String financialInstitutionName) {
        this.financialInstitutionName = financialInstitutionName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getRelationshipSince() {
        return relationshipSince;
    }

    public void setRelationshipSince(Integer relationshipSince) {
        this.relationshipSince = relationshipSince;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(Long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
    
    /**
	 * @return the financialArrangementId
	 */
	public Long getFinancialArrangementId() {
		return financialArrangementId;
	}

	/**
	 * @param financialArrangementId the financialArrangementId to set
	 */
	public void setFinancialArrangementId(Long financialArrangementId) {
		this.financialArrangementId = financialArrangementId;
	}
	public FinancialArrangementDetailResponseString getFinArraRes() {
		return finArraRes;
	}

	public void setFinArraRes(FinancialArrangementDetailResponseString finArraRes) {
		this.finArraRes = finArraRes;
	}


	@Override
    public String toString() {
        return "DDRExistingBankerDetailRequest{" +
                "id=" + id +
                ", ddrFormId=" + ddrFormId +
                ", financialInstitutionName='" + financialInstitutionName + '\'' +
                ", address='" + address + '\'' +
                ", relationshipSince=" + relationshipSince +
                ", createdBy=" + createdBy +
                ", createdDate=" + createdDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", isActive=" + isActive +
                ", financialArrangementId=" + financialArrangementId + 
                '}';
    }

    public static void printFields(Object obj) throws Exception {
        Field[] fields = DDRExistingBankerDetailRequest.class.getDeclaredFields();
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
