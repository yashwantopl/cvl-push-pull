package com.capitaworld.service.loans.model.api_model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author harshit
 * Date : 22-Jun-2018
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceSheetLiabilitiesReq  implements Serializable{

		private static final long serialVersionUID = 1L;
	
		private Long id;

	    private String year;

	    private Long applicationId;

	    private String currency;

	    private Double sharesCapital;

	    private Double shareWarrentsOutstanding;

	    private Double revaluationReservse;

	    private Double otherReservesAndSurplus;

	    private Double shareholderFunds;

	    private Double minorityInterest;

	    private Double securedLoans;

	    private Double unsecuredLoansPromoters;

	    private Double unsecuredLoansOthers;

	    private Double deferredTaxLiability;

	    private Double otherLongTermLiabilities;

	    private Double otherBorrowings;

	    private Double longTermProvisions;

	    private Double totalNonCurrentLiabilities;

	    private Double tradePayables;

	    private Double otherCurrentLiabilities;

	    private Double shortTermProvisions;

	    private Double totalCurrentLiabilities;

	    private Double totalLiabilities;

	    private Date createdDate;

	    private Boolean isActive;
	    
	    private Double otherLiabilities;
	    private Double ordinarySharesCapital;
	    private Double otherIncomeNeedTocCheckLia;

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
