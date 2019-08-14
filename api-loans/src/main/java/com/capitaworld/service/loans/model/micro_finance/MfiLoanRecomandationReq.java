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
//    private Double interestRateRecomandation;
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

//    public Double getInterestRateRecomandation() {
//        return interestRateRecomandation;
//    }
//
//    public void setInterestRateRecomandation(Double interestRateRecomandation) {
//        this.interestRateRecomandation = interestRateRecomandation;
//    }

    public Integer getInstallmentRecomandation() {
        return installmentRecomandation;
    }

    public void setInstallmentRecomandation(Integer installmentRecomandation) {
        this.installmentRecomandation = installmentRecomandation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
//                ", interestRateRecomandation=" + interestRateRecomandation +
                ", installmentRecomandation=" + installmentRecomandation +
                '}';
    }
}
