package com.opl.mudra.api.user.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DealerDetailsRequest {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long userId;
    private String email;
    private String mobile;
    private String firmName;
    private String mainDealerCode;
    private String dealerCode;
    private String dealerName;
    private String description;
    private Integer constitution;
    private String pan;
    private Date registrationDate;
    private String officeAddress;
    private String pincode;
    private Integer city;
    private Integer state;
    private String stateCode;
    private String district;
    private String subDistrict;
    @JsonProperty(value = "isAvailFunding")
    private Boolean isAvailFunding;
    private Long branchId;
    private String branchName;
    private String branchCode;
    private String branchCity;
    private String branchState;
    private Date lastModifiedDate;

    private String cityName;
    private String stateName;
    private String constitutionName;
    
    private Boolean isDealerActive;
    private Long dealerCount;
    
    private Integer pageNo = 0;
    private Integer totalPage;
    
    private Long oemId; 
    private String oemName; 
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getMainDealerCode() {
        return mainDealerCode;
    }

    public void setMainDealerCode(String mainDealerCode) {
        this.mainDealerCode = mainDealerCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getConstitution() {
        return constitution;
    }

    public void setConstitution(Integer constitution) {
        this.constitution = constitution;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSubDistrict() {
        return subDistrict;
    }

    public void setSubDistrict(String subDistrict) {
        this.subDistrict = subDistrict;
    }

    public Boolean getAvailFunding() {
        return isAvailFunding;
    }

    public void setAvailFunding(Boolean availFunding) {
        isAvailFunding = availFunding;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    public String getBranchState() {
        return branchState;
    }

    public void setBranchState(String branchState) {
        this.branchState = branchState;
    }

	public Boolean getIsAvailFunding() {
		return isAvailFunding;
	}

	public void setIsAvailFunding(Boolean isAvailFunding) {
		this.isAvailFunding = isAvailFunding;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getConstitutionName() {
		return constitutionName;
	}

	public void setConstitutionName(String constitutionName) {
		this.constitutionName = constitutionName;
	}

	public Boolean getIsDealerActive() {
		return isDealerActive;
	}

	public void setIsDealerActive(Boolean isDealerActive) {
		this.isDealerActive = isDealerActive;
	}

	public Long getDealerCount() {
		return dealerCount;
	}

	public void setDealerCount(Long dealerCount) {
		this.dealerCount = dealerCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Long getOemId() {
		return oemId;
	}

	public void setOemId(Long oemId) {
		this.oemId = oemId;
	}

	public String getOemName() {
		return oemName;
	}

	public void setOemName(String oemName) {
		this.oemName = oemName;
	}

    
}
