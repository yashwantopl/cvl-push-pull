package com.capitaworld.service.loans.model.micro_finance;

import java.io.Serializable;


public class MfiAssetsDetailsReq implements Serializable {

    private Long id;

    private Long applicationId;

    private Integer assetsType;

    private String assetOwnerDetail;

    private Double amount;

    private Double outstanding;

    private Integer particulars;

    private Integer type; // for assets or liability

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

    public Integer getAssetsType() {
        return assetsType;
    }

    public void setAssetsType(Integer assetsType) {
        this.assetsType = assetsType;
    }

    public String getAssetOwnerDetail() {
        return assetOwnerDetail;
    }

    public void setAssetOwnerDetail(String assetOwnerDetail) {
        this.assetOwnerDetail = assetOwnerDetail;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(Double outstanding) {
        this.outstanding = outstanding;
    }

    public Integer getParticulars() {
        return particulars;
    }

    public void setParticulars(Integer particulars) {
        this.particulars = particulars;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
