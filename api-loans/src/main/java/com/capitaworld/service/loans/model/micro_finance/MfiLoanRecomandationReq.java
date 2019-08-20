package com.capitaworld.service.loans.model.micro_finance;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class MfiLoanRecomandationReq {

    private Long id;
    private Long applicationId;
    private Long userId;
    private Double loanAmountRecomandation;
    private Integer tenureRecomandation;
    private Integer moratoriumRecomandation;
    private Integer installmentRecomandation;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getLoanAmountRecomandation() {
        return loanAmountRecomandation;
    }

    public void setLoanAmountRecomandation(Double loanAmountRecomandation) {
        this.loanAmountRecomandation = loanAmountRecomandation;
    }

    public Integer getTenureRecomandation() {
        return tenureRecomandation;
    }

    public void setTenureRecomandation(Integer tenureRecomandation) {
        this.tenureRecomandation = tenureRecomandation;
    }

    public Integer getMoratoriumRecomandation() {
        return moratoriumRecomandation;
    }

    public void setMoratoriumRecomandation(Integer moratoriumRecomandation) {
        this.moratoriumRecomandation = moratoriumRecomandation;
    }

    public Integer getInstallmentRecomandation() {
        return installmentRecomandation;
    }

    public void setInstallmentRecomandation(Integer installmentRecomandation) {
        this.installmentRecomandation = installmentRecomandation;
    }

    @Override
    public String toString() {
        return "MfiLoanRecomandationReq{" +
                "id=" + id +
                ", applicationId=" + applicationId +
                ", userId=" + userId +
                ", loanAmountRecomandation=" + loanAmountRecomandation +
                ", tenureRecomandation=" + tenureRecomandation +
                ", moratoriumRecomandation=" + moratoriumRecomandation +
                ", installmentRecomandation=" + installmentRecomandation +
                '}';
    }
}
