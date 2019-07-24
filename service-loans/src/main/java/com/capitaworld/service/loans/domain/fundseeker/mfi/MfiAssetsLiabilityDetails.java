package com.capitaworld.service.loans.domain.fundseeker.mfi;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "fs_mfi_assets_liability_details")
public class MfiAssetsLiabilityDetails implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "application_id")
    private Long applicationId;

    @Column(name = "assets_type")
    private Integer assets_type;

    @Column(name = "asset_owner_detail")
    private String asset_owner_detail;

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

    public Integer getAssets_type() {
        return assets_type;
    }

    public void setAssets_type(Integer assets_type) {
        this.assets_type = assets_type;
    }

    public String getAsset_owner_detail() {
        return asset_owner_detail;
    }

    public void setAsset_owner_detail(String asset_owner_detail) {
        this.asset_owner_detail = asset_owner_detail;
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
