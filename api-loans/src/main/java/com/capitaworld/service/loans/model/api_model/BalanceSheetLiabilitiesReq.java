package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceSheetLiabilitiesReq implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String year;

    private Long applicationId;

    private String currency;

    private Double sharesCapital;
    private String sharesCapitalStr;

    private Double shareWarrentsOutstanding;
    private String shareWarrentsOutstandingStr;

    private Double revaluationReservse;
    private String revaluationReservseStr;

    private Double otherReservesAndSurplus;
    private String otherReservesAndSurplusStr;

    private Double shareholderFunds;
    private String shareholderFundsStr;

    private Double minorityInterest;
    private String minorityInterestStr;

    private Double securedLoans;
    private String securedLoansStr;

    private Double unsecuredLoansPromoters;
    private String unsecuredLoansPromotersStr;

    private Double unsecuredLoansOthers;
    private String unsecuredLoansOthersStr;

    private Double deferredTaxLiability;
    private String deferredTaxLiabilityStr;

    private Double otherLongTermLiabilities;
    private String otherLongTermLiabilitiesStr;

    private Double otherBorrowings;
    private String otherBorrowingsStr;

    private Double longTermProvisions;
    private String longTermProvisionsStr;

    private Double totalNonCurrentLiabilities;
    private String totalNonCurrentLiabilitiesStr;

    private Double tradePayables;
    private String tradePayablesStr;

    private Double otherCurrentLiabilities;
    private String otherCurrentLiabilitiesStr;

    private Double shortTermProvisions;
    private String shortTermProvisionsStr;

    private Double totalCurrentLiabilities;
    private String totalCurrentLiabilitiesStr;

    private Double totalLiabilities;
    private String totalLiabilitiesStr;

    private Date createdDate;

    private Boolean isActive;

    private Double otherLiabilities;
    private String otherLiabilitiesStr;

    private Double ordinarySharesCapital;
    private String ordinarySharesCapitalStr;

    private Double otherIncomeNeedTocCheckLia;
    private String otherIncomeNeedTocCheckLiaStr;


    public BalanceSheetLiabilitiesReq() {
        super();
        this.sharesCapital = 0.0;
        this.shareWarrentsOutstanding = 0.0;
        this.revaluationReservse = 0.0;
        this.otherReservesAndSurplus = 0.0;
        this.shareholderFunds = 0.0;
        this.minorityInterest = 0.0;
        this.securedLoans = 0.0;
        this.unsecuredLoansPromoters = 0.0;
        this.unsecuredLoansOthers = 0.0;
        this.deferredTaxLiability = 0.0;
        this.otherLongTermLiabilities = 0.0;
        this.otherBorrowings = 0.0;
        this.longTermProvisions = 0.0;
        this.totalNonCurrentLiabilities = 0.0;
        this.tradePayables = 0.0;
        this.otherCurrentLiabilities = 0.0;
        this.shortTermProvisions = 0.0;
        this.totalCurrentLiabilities = 0.0;
        this.totalLiabilities = 0.0;
        this.otherLiabilities = 0.0;
        this.ordinarySharesCapital = 0.0;
        this.otherIncomeNeedTocCheckLia = 0.0;
        this.sharesCapitalStr = "0.0";
        this.shareWarrentsOutstandingStr = "0.0";
        this.revaluationReservseStr = "0.0";
        this.otherReservesAndSurplusStr = "0.0";
        this.shareholderFundsStr = "0.0";
        this.minorityInterestStr = "0.0";
        this.securedLoansStr = "0.0";
        this.unsecuredLoansPromotersStr = "0.0";
        this.unsecuredLoansOthersStr = "0.0";
        this.deferredTaxLiabilityStr = "0.0";
        this.otherLongTermLiabilitiesStr = "0.0";
        this.otherBorrowingsStr = "0.0";
        this.longTermProvisionsStr = "0.0";
        this.totalNonCurrentLiabilitiesStr = "0.0";
        this.tradePayablesStr = "0.0";
        this.otherCurrentLiabilitiesStr = "0.0";
        this.shortTermProvisionsStr = "0.0";
        this.totalCurrentLiabilitiesStr = "0.0";
        this.totalLiabilitiesStr = "0.0";
        this.otherLiabilitiesStr = "0.0";
        this.ordinarySharesCapitalStr = "0.0";
        this.otherIncomeNeedTocCheckLiaStr = "0.0";
    }


    public String getSharesCapitalStr() {
        return sharesCapitalStr;
    }

    public void setSharesCapitalStr(String sharesCapitalStr) {
        this.sharesCapitalStr = sharesCapitalStr;
    }

    public String getShareWarrentsOutstandingStr() {
        return shareWarrentsOutstandingStr;
    }

    public void setShareWarrentsOutstandingStr(String shareWarrentsOutstandingStr) {
        this.shareWarrentsOutstandingStr = shareWarrentsOutstandingStr;
    }

    public String getRevaluationReservseStr() {
        return revaluationReservseStr;
    }

    public void setRevaluationReservseStr(String revaluationReservseStr) {
        this.revaluationReservseStr = revaluationReservseStr;
    }

    public String getOtherReservesAndSurplusStr() {
        return otherReservesAndSurplusStr;
    }

    public void setOtherReservesAndSurplusStr(String otherReservesAndSurplusStr) {
        this.otherReservesAndSurplusStr = otherReservesAndSurplusStr;
    }

    public String getShareholderFundsStr() {
        return shareholderFundsStr;
    }

    public void setShareholderFundsStr(String shareholderFundsStr) {
        this.shareholderFundsStr = shareholderFundsStr;
    }

    public String getMinorityInterestStr() {
        return minorityInterestStr;
    }

    public void setMinorityInterestStr(String minorityInterestStr) {
        this.minorityInterestStr = minorityInterestStr;
    }

    public String getSecuredLoansStr() {
        return securedLoansStr;
    }

    public void setSecuredLoansStr(String securedLoansStr) {
        this.securedLoansStr = securedLoansStr;
    }

    public String getUnsecuredLoansPromotersStr() {
        return unsecuredLoansPromotersStr;
    }

    public void setUnsecuredLoansPromotersStr(String unsecuredLoansPromotersStr) {
        this.unsecuredLoansPromotersStr = unsecuredLoansPromotersStr;
    }

    public String getUnsecuredLoansOthersStr() {
        return unsecuredLoansOthersStr;
    }

    public void setUnsecuredLoansOthersStr(String unsecuredLoansOthersStr) {
        this.unsecuredLoansOthersStr = unsecuredLoansOthersStr;
    }

    public String getDeferredTaxLiabilityStr() {
        return deferredTaxLiabilityStr;
    }

    public void setDeferredTaxLiabilityStr(String deferredTaxLiabilityStr) {
        this.deferredTaxLiabilityStr = deferredTaxLiabilityStr;
    }

    public String getOtherLongTermLiabilitiesStr() {
        return otherLongTermLiabilitiesStr;
    }

    public void setOtherLongTermLiabilitiesStr(String otherLongTermLiabilitiesStr) {
        this.otherLongTermLiabilitiesStr = otherLongTermLiabilitiesStr;
    }

    public String getOtherBorrowingsStr() {
        return otherBorrowingsStr;
    }

    public void setOtherBorrowingsStr(String otherBorrowingsStr) {
        this.otherBorrowingsStr = otherBorrowingsStr;
    }

    public String getLongTermProvisionsStr() {
        return longTermProvisionsStr;
    }

    public void setLongTermProvisionsStr(String longTermProvisionsStr) {
        this.longTermProvisionsStr = longTermProvisionsStr;
    }

    public String getTotalNonCurrentLiabilitiesStr() {
        return totalNonCurrentLiabilitiesStr;
    }

    public void setTotalNonCurrentLiabilitiesStr(String totalNonCurrentLiabilitiesStr) {
        this.totalNonCurrentLiabilitiesStr = totalNonCurrentLiabilitiesStr;
    }

    public String getTradePayablesStr() {
        return tradePayablesStr;
    }

    public void setTradePayablesStr(String tradePayablesStr) {
        this.tradePayablesStr = tradePayablesStr;
    }

    public String getOtherCurrentLiabilitiesStr() {
        return otherCurrentLiabilitiesStr;
    }

    public void setOtherCurrentLiabilitiesStr(String otherCurrentLiabilitiesStr) {
        this.otherCurrentLiabilitiesStr = otherCurrentLiabilitiesStr;
    }

    public String getShortTermProvisionsStr() {
        return shortTermProvisionsStr;
    }

    public void setShortTermProvisionsStr(String shortTermProvisionsStr) {
        this.shortTermProvisionsStr = shortTermProvisionsStr;
    }

    public String getTotalCurrentLiabilitiesStr() {
        return totalCurrentLiabilitiesStr;
    }

    public void setTotalCurrentLiabilitiesStr(String totalCurrentLiabilitiesStr) {
        this.totalCurrentLiabilitiesStr = totalCurrentLiabilitiesStr;
    }

    public String getTotalLiabilitiesStr() {
        return totalLiabilitiesStr;
    }

    public void setTotalLiabilitiesStr(String totalLiabilitiesStr) {
        this.totalLiabilitiesStr = totalLiabilitiesStr;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getOtherLiabilitiesStr() {
        return otherLiabilitiesStr;
    }

    public void setOtherLiabilitiesStr(String otherLiabilitiesStr) {
        this.otherLiabilitiesStr = otherLiabilitiesStr;
    }

    public String getOrdinarySharesCapitalStr() {
        return ordinarySharesCapitalStr;
    }

    public void setOrdinarySharesCapitalStr(String ordinarySharesCapitalStr) {
        this.ordinarySharesCapitalStr = ordinarySharesCapitalStr;
    }

    public String getOtherIncomeNeedTocCheckLiaStr() {
        return otherIncomeNeedTocCheckLiaStr;
    }

    public void setOtherIncomeNeedTocCheckLiaStr(String otherIncomeNeedTocCheckLiaStr) {
        this.otherIncomeNeedTocCheckLiaStr = otherIncomeNeedTocCheckLiaStr;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getYear() {
        return year;
    }


    public void setYear(String year) {
        this.year = year;
    }


    public Long getApplicationId() {
        return applicationId;
    }


    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }


    public String getCurrency() {
        return currency;
    }


    public void setCurrency(String currency) {
        this.currency = currency;
    }


    public Double getSharesCapital() {
        return sharesCapital;
    }


    public void setSharesCapital(Double sharesCapital) {
        this.sharesCapital = sharesCapital;
    }


    public Double getShareWarrentsOutstanding() {
        return shareWarrentsOutstanding;
    }


    public void setShareWarrentsOutstanding(Double shareWarrentsOutstanding) {
        this.shareWarrentsOutstanding = shareWarrentsOutstanding;
    }


    public Double getRevaluationReservse() {
        return revaluationReservse;
    }


    public void setRevaluationReservse(Double revaluationReservse) {
        this.revaluationReservse = revaluationReservse;
    }


    public Double getOtherReservesAndSurplus() {
        return otherReservesAndSurplus;
    }


    public void setOtherReservesAndSurplus(Double otherReservesAndSurplus) {
        this.otherReservesAndSurplus = otherReservesAndSurplus;
    }


    public Double getShareholderFunds() {
        return shareholderFunds;
    }


    public void setShareholderFunds(Double shareholderFunds) {
        this.shareholderFunds = shareholderFunds;
    }


    public Double getMinorityInterest() {
        return minorityInterest;
    }


    public void setMinorityInterest(Double minorityInterest) {
        this.minorityInterest = minorityInterest;
    }


    public Double getSecuredLoans() {
        return securedLoans;
    }


    public void setSecuredLoans(Double securedLoans) {
        this.securedLoans = securedLoans;
    }


    public Double getUnsecuredLoansPromoters() {
        return unsecuredLoansPromoters;
    }


    public void setUnsecuredLoansPromoters(Double unsecuredLoansPromoters) {
        this.unsecuredLoansPromoters = unsecuredLoansPromoters;
    }


    public Double getUnsecuredLoansOthers() {
        return unsecuredLoansOthers;
    }


    public void setUnsecuredLoansOthers(Double unsecuredLoansOthers) {
        this.unsecuredLoansOthers = unsecuredLoansOthers;
    }


    public Double getDeferredTaxLiability() {
        return deferredTaxLiability;
    }


    public void setDeferredTaxLiability(Double deferredTaxLiability) {
        this.deferredTaxLiability = deferredTaxLiability;
    }


    public Double getOtherLongTermLiabilities() {
        return otherLongTermLiabilities;
    }


    public void setOtherLongTermLiabilities(Double otherLongTermLiabilities) {
        this.otherLongTermLiabilities = otherLongTermLiabilities;
    }


    public Double getOtherBorrowings() {
        return otherBorrowings;
    }


    public void setOtherBorrowings(Double otherBorrowings) {
        this.otherBorrowings = otherBorrowings;
    }


    public Double getLongTermProvisions() {
        return longTermProvisions;
    }


    public void setLongTermProvisions(Double longTermProvisions) {
        this.longTermProvisions = longTermProvisions;
    }


    public Double getTotalNonCurrentLiabilities() {
        return totalNonCurrentLiabilities;
    }


    public void setTotalNonCurrentLiabilities(Double totalNonCurrentLiabilities) {
        this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
    }


    public Double getTradePayables() {
        return tradePayables;
    }


    public void setTradePayables(Double tradePayables) {
        this.tradePayables = tradePayables;
    }


    public Double getOtherCurrentLiabilities() {
        return otherCurrentLiabilities;
    }


    public void setOtherCurrentLiabilities(Double otherCurrentLiabilities) {
        this.otherCurrentLiabilities = otherCurrentLiabilities;
    }


    public Double getShortTermProvisions() {
        return shortTermProvisions;
    }


    public void setShortTermProvisions(Double shortTermProvisions) {
        this.shortTermProvisions = shortTermProvisions;
    }


    public Double getTotalCurrentLiabilities() {
        return totalCurrentLiabilities;
    }


    public void setTotalCurrentLiabilities(Double totalCurrentLiabilities) {
        this.totalCurrentLiabilities = totalCurrentLiabilities;
    }


    public Double getTotalLiabilities() {
        return totalLiabilities;
    }


    public void setTotalLiabilities(Double totalLiabilities) {
        this.totalLiabilities = totalLiabilities;
    }


    public Date getCreatedDate() {
        return createdDate;
    }


    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }


    public Boolean getIsActive() {
        return isActive;
    }


    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }


    public Double getOtherLiabilities() {
        return otherLiabilities;
    }


    public void setOtherLiabilities(Double otherLiabilities) {
        this.otherLiabilities = otherLiabilities;
    }


    public Double getOrdinarySharesCapital() {
        return ordinarySharesCapital;
    }


    public void setOrdinarySharesCapital(Double ordinarySharesCapital) {
        this.ordinarySharesCapital = ordinarySharesCapital;
    }


    public Double getOtherIncomeNeedTocCheckLia() {
        return otherIncomeNeedTocCheckLia;
    }


    public void setOtherIncomeNeedTocCheckLia(Double otherIncomeNeedTocCheckLia) {
        this.otherIncomeNeedTocCheckLia = otherIncomeNeedTocCheckLia;
    }


    @Override
    public String toString() {
        return "BalanceSheetLiabilitiesReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId
                + ", currency=" + currency + ", ordinarySharesCapital=" + sharesCapital
                + ", shareWarrentsOutstanding=" + shareWarrentsOutstanding + ", revaluationReservse="
                + revaluationReservse + ", otherReservesAndSurplus=" + otherReservesAndSurplus
                + ", shareholderFunds=" + shareholderFunds + ", minorityInterest=" + minorityInterest
                + ", termLiabilitiesSecured=" + securedLoans + ", otherNclUnsecuredLoansFromPromoters="
                + unsecuredLoansPromoters + ", unsecuredLoansOthers=" + unsecuredLoansOthers
                + ", deferredTaxLiability=" + deferredTaxLiability + ", otherLongTermLiabilities="
                + otherLongTermLiabilities + ", otherBorrowings=" + otherBorrowings
                + ", otherNclLongTermProvisions=" + longTermProvisions + ", totalNonCurrentLiabilities="
                + totalNonCurrentLiabilities + ", sundryCreditors=" + tradePayables + ", otherCurrentLiabilities="
                + otherCurrentLiabilities + ", provisionalForTaxation=" + shortTermProvisions
                + ", totalCurrentLiabilities=" + totalCurrentLiabilities + ", totalLiabilities=" + totalLiabilities
                + ", createdDate=" + createdDate + ", isActive=" + isActive + ", otherLiabilities="
                + otherLiabilities + "]";
    }

}
