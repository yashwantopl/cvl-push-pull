package com.opl.mudra.api.analyzer.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author jitesh.savaliya
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankStatementResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2435459460648065955L;
	private Long userId;
    private Long applicationId;
    private Long storageId;
    private Long bsMasterId;
    private Long perfiousId;
    private Long profileId;
    private String accountHolderName;
    private Integer sinceYear;
    private Integer sinceMonth;
    private Date lastUpdateOn;
    private String totalTransactions;
    private String bankAccountNo;
    private String name;
    private String userToken;
    private Boolean isFromSbi;
    private List<Long> appIds;
    private List<Integer> ids;
    private Boolean isFromCoOrigination;
    private Boolean isRenewalAffected;
    private Integer stageId;
    private String bankName;
    private Boolean completed;
    private Date createdDate;
    private Date modifiedDate;
    private Integer accountType;
    private String ifscCode;
    private String lastMonth;
    private String lastYear;
    private Boolean isManualUpload;
    private Boolean isInvalidBS;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }

    public BankStatementResponse(Long applicationId) {
        super();
        this.applicationId = applicationId;
    }

    public BankStatementResponse(Long applicationId, Boolean isFromSbi) {
        super();
        this.applicationId = applicationId;
        this.isFromSbi = isFromSbi;
    }

    public BankStatementResponse() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public Long getStorageId() {
        return storageId;
    }

    public void setStorageId(Long storageId) {
        this.storageId = storageId;
    }

    public BankStatementResponse(Long userId, Long applicationId, Long storageId, String name, String userToken) {
        super();
        this.userId = userId;
        this.applicationId = applicationId;
        this.storageId = storageId;
        this.name = name;
        this.userToken = userToken;
    }

    public Boolean getIsFromSbi() {
        return isFromSbi;
    }

    public void setIsFromSbi(Boolean isFromSbi) {
        this.isFromSbi = isFromSbi;
    }

    public List<Long> getAppIds() {
        return appIds;
    }

    public void setAppIds(List<Long> appIds) {
        this.appIds = appIds;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public Boolean getIsFromCoOrigination() {
        return isFromCoOrigination;
    }

    public void setIsFromCoOrigination(Boolean isFromCoOrigination) {
        this.isFromCoOrigination = isFromCoOrigination;
    }

    public Boolean getIsRenewalAffected() {
        return isRenewalAffected;
    }

    public void setIsRenewalAffected(Boolean isRenewalAffected) {
        this.isRenewalAffected = isRenewalAffected;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public Long getBsMasterId() {
        return bsMasterId;
    }

    public void setBsMasterId(Long bsMasterId) {
        this.bsMasterId = bsMasterId;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public Integer getSinceYear() {
        return sinceYear;
    }

    public void setSinceYear(Integer sinceYear) {
        this.sinceYear = sinceYear;
    }

    public Integer getSinceMonth() {
        return sinceMonth;
    }

    public void setSinceMonth(Integer sinceMonth) {
        this.sinceMonth = sinceMonth;
    }

    public Date getLastUpdateOn() {
        return lastUpdateOn;
    }

    public void setLastUpdateOn(Date lastUpdateOn) {
        this.lastUpdateOn = lastUpdateOn;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Boolean getIsCompleted() {
        return completed;
    }

    public void setIsCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Long getPerfiousId() {
        return perfiousId;
    }

    public void setPerfiousId(Long perfiousId) {
        this.perfiousId = perfiousId;
    }

    public String getTotalTransactions() {
        return totalTransactions;
    }

    public void setTotalTransactions(String totalTransactions) {
        this.totalTransactions = totalTransactions;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getLastMonth() {
		return lastMonth;
	}

	public void setLastMonth(String lastMonth) {
		this.lastMonth = lastMonth;
	}

	public String getLastYear() {
		return lastYear;
	}

	public void setLastYear(String lastYear) {
		this.lastYear = lastYear;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Boolean getIsManualUpload() {
		return isManualUpload;
	}

	public void setIsManualUpload(Boolean isManualUpload) {
		this.isManualUpload = isManualUpload;
	}

    public Boolean getIsInvalidBS() {
        return isInvalidBS;
    }

    public void setIsInvalidBS(Boolean invalidBS) {
        isInvalidBS = invalidBS;
    }
}

