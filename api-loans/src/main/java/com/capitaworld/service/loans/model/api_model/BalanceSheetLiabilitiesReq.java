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

	    private Double ordinarySharesCapital;

	    private Double shareWarrentsOutstanding;

	    private Double revaluationReservse;

	    private Double otherReservesAndSurplus;

	    private Double shareholderFunds;

	    private Double minorityInterest;

	    private Double termLiabilitiesSecured;

	    private Double otherNclUnsecuredLoansFromPromoters;

	    private Double unsecuredLoansOthers;

	    private Double deferredTaxLiability;

	    private Double otherLongTermLiabilities;

	    private Double otherBorrowings;

	    private Double otherNclLongTermProvisions;

	    private Double totalNonCurrentLiabilities;

	    private Double sundryCreditors;

	    private Double otherCurrentLiabilities;

	    private Double provisionalForTaxation;

	    private Double totalCurrentLiabilities;

	    private Double totalLiabilities;

	    private Date createdDate;

	    private Boolean isActive;
	    
	    private Double otherLiabilities;

		public BalanceSheetLiabilitiesReq() {
			super();
			this.ordinarySharesCapital = 0.0;
			this.shareWarrentsOutstanding = 0.0;
			this.revaluationReservse = 0.0;
			this.otherReservesAndSurplus = 0.0;
			this.shareholderFunds = 0.0;
			this.minorityInterest = 0.0;
			this.termLiabilitiesSecured = 0.0;
			this.otherNclUnsecuredLoansFromPromoters = 0.0;
			this.unsecuredLoansOthers = 0.0;
			this.deferredTaxLiability = 0.0;
			this.otherLongTermLiabilities = 0.0;
			this.otherBorrowings = 0.0;
			this.otherNclLongTermProvisions = 0.0;
			this.totalNonCurrentLiabilities = 0.0;
			this.sundryCreditors = 0.0;
			this.otherCurrentLiabilities = 0.0;
			this.provisionalForTaxation = 0.0;
			this.totalCurrentLiabilities = 0.0;
			this.totalLiabilities = 0.0;
			this.otherLiabilities = 0.0;
		}

		public Long getId() {
			return id;
		}

		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}

		public String getYear() {
			return year;
		}

		/**
		 * @param year the year to set
		 */
		public void setYear(String year) {
			this.year = year;
		}

		public Long getApplicationId() {
			return applicationId;
		}

		/**
		 * @param applicationId the applicationId to set
		 */
		public void setApplicationId(Long applicationId) {
			this.applicationId = applicationId;
		}

		public String getCurrency() {
			return currency;
		}

		/**
		 * @param currency the currency to set
		 */
		public void setCurrency(String currency) {
			this.currency = currency;
		}

		public Double getOrdinarySharesCapital() {
			return ordinarySharesCapital;
		}

		/**
		 * @param ordinarySharesCapital the ordinarySharesCapital to set
		 */
		public void setOrdinarySharesCapital(Double ordinarySharesCapital) {
			this.ordinarySharesCapital = ordinarySharesCapital;
		}

		public Double getShareWarrentsOutstanding() {
			return shareWarrentsOutstanding;
		}

		/**
		 * @param shareWarrentsOutstanding the shareWarrentsOutstanding to set
		 */
		public void setShareWarrentsOutstanding(Double shareWarrentsOutstanding) {
			this.shareWarrentsOutstanding = shareWarrentsOutstanding;
		}

		public Double getRevaluationReservse() {
			return revaluationReservse;
		}

		/**
		 * @param revaluationReservse the revaluationReservse to set
		 */
		public void setRevaluationReservse(Double revaluationReservse) {
			this.revaluationReservse = revaluationReservse;
		}

		public Double getOtherReservesAndSurplus() {
			return otherReservesAndSurplus;
		}

		/**
		 * @param otherReservesAndSurplus the otherReservesAndSurplus to set
		 */
		public void setOtherReservesAndSurplus(Double otherReservesAndSurplus) {
			this.otherReservesAndSurplus = otherReservesAndSurplus;
		}

		public Double getShareholderFunds() {
			return shareholderFunds;
		}

		/**
		 * @param shareholderFunds the shareholderFunds to set
		 */
		public void setShareholderFunds(Double shareholderFunds) {
			this.shareholderFunds = shareholderFunds;
		}

		public Double getMinorityInterest() {
			return minorityInterest;
		}

		/**
		 * @param minorityInterest the minorityInterest to set
		 */
		public void setMinorityInterest(Double minorityInterest) {
			this.minorityInterest = minorityInterest;
		}

		public Double getTermLiabilitiesSecured() {
			return termLiabilitiesSecured;
		}

		/**
		 * @param termLiabilitiesSecured the termLiabilitiesSecured to set
		 */
		public void setTermLiabilitiesSecured(Double termLiabilitiesSecured) {
			this.termLiabilitiesSecured = termLiabilitiesSecured;
		}

		public Double getOtherNclUnsecuredLoansFromPromoters() {
			return otherNclUnsecuredLoansFromPromoters;
		}

		/**
		 * @param otherNclUnsecuredLoansFromPromoters the otherNclUnsecuredLoansFromPromoters to set
		 */
		public void setOtherNclUnsecuredLoansFromPromoters(Double otherNclUnsecuredLoansFromPromoters) {
			this.otherNclUnsecuredLoansFromPromoters = otherNclUnsecuredLoansFromPromoters;
		}

		public Double getUnsecuredLoansOthers() {
			return unsecuredLoansOthers;
		}

		/**
		 * @param unsecuredLoansOthers the unsecuredLoansOthers to set
		 */
		public void setUnsecuredLoansOthers(Double unsecuredLoansOthers) {
			this.unsecuredLoansOthers = unsecuredLoansOthers;
		}

		public Double getDeferredTaxLiability() {
			return deferredTaxLiability;
		}

		/**
		 * @param deferredTaxLiability the deferredTaxLiability to set
		 */
		public void setDeferredTaxLiability(Double deferredTaxLiability) {
			this.deferredTaxLiability = deferredTaxLiability;
		}

		public Double getOtherLongTermLiabilities() {
			return otherLongTermLiabilities;
		}

		/**
		 * @param otherLongTermLiabilities the otherLongTermLiabilities to set
		 */
		public void setOtherLongTermLiabilities(Double otherLongTermLiabilities) {
			this.otherLongTermLiabilities = otherLongTermLiabilities;
		}

		public Double getOtherBorrowings() {
			return otherBorrowings;
		}

		/**
		 * @param otherBorrowings the otherBorrowings to set
		 */
		public void setOtherBorrowings(Double otherBorrowings) {
			this.otherBorrowings = otherBorrowings;
		}

		public Double getOtherNclLongTermProvisions() {
			return otherNclLongTermProvisions;
		}

		/**
		 * @param otherNclLongTermProvisions the otherNclLongTermProvisions to set
		 */
		public void setOtherNclLongTermProvisions(Double otherNclLongTermProvisions) {
			this.otherNclLongTermProvisions = otherNclLongTermProvisions;
		}

		public Double getTotalNonCurrentLiabilities() {
			return totalNonCurrentLiabilities;
		}

		/**
		 * @param totalNonCurrentLiabilities the totalNonCurrentLiabilities to set
		 */
		public void setTotalNonCurrentLiabilities(Double totalNonCurrentLiabilities) {
			this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
		}

		public Double getSundryCreditors() {
			return sundryCreditors;
		}

		/**
		 * @param sundryCreditors the sundryCreditors to set
		 */
		public void setSundryCreditors(Double sundryCreditors) {
			this.sundryCreditors = sundryCreditors;
		}

		public Double getOtherCurrentLiabilities() {
			return otherCurrentLiabilities;
		}

		/**
		 * @param otherCurrentLiabilities the otherCurrentLiabilities to set
		 */
		public void setOtherCurrentLiabilities(Double otherCurrentLiabilities) {
			this.otherCurrentLiabilities = otherCurrentLiabilities;
		}

		public Double getProvisionalForTaxation() {
			return provisionalForTaxation;
		}

		/**
		 * @param provisionalForTaxation the provisionalForTaxation to set
		 */
		public void setProvisionalForTaxation(Double provisionalForTaxation) {
			this.provisionalForTaxation = provisionalForTaxation;
		}

		public Double getTotalCurrentLiabilities() {
			return totalCurrentLiabilities;
		}

		/**
		 * @param totalCurrentLiabilities the totalCurrentLiabilities to set
		 */
		public void setTotalCurrentLiabilities(Double totalCurrentLiabilities) {
			this.totalCurrentLiabilities = totalCurrentLiabilities;
		}

		public Double getTotalLiabilities() {
			return totalLiabilities;
		}

		/**
		 * @param totalLiabilities the totalLiabilities to set
		 */
		public void setTotalLiabilities(Double totalLiabilities) {
			this.totalLiabilities = totalLiabilities;
		}

		public Date getCreatedDate() {
			return createdDate;
		}

		/**
		 * @param createdDate the createdDate to set
		 */
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}

		public Boolean getIsActive() {
			return isActive;
		}

		/**
		 * @param isActive the isActive to set
		 */
		public void setIsActive(Boolean isActive) {
			this.isActive = isActive;
		}

		public Double getOtherLiabilities() {
			return otherLiabilities;
		}

		public void setOtherLiabilities(Double otherLiabilities) {
			this.otherLiabilities = otherLiabilities;
		}

		@Override
		public String toString() {
			return "BalanceSheetLiabilitiesReq [id=" + id + ", year=" + year + ", applicationId=" + applicationId
					+ ", currency=" + currency + ", ordinarySharesCapital=" + ordinarySharesCapital
					+ ", shareWarrentsOutstanding=" + shareWarrentsOutstanding + ", revaluationReservse="
					+ revaluationReservse + ", otherReservesAndSurplus=" + otherReservesAndSurplus
					+ ", shareholderFunds=" + shareholderFunds + ", minorityInterest=" + minorityInterest
					+ ", termLiabilitiesSecured=" + termLiabilitiesSecured + ", otherNclUnsecuredLoansFromPromoters="
					+ otherNclUnsecuredLoansFromPromoters + ", unsecuredLoansOthers=" + unsecuredLoansOthers
					+ ", deferredTaxLiability=" + deferredTaxLiability + ", otherLongTermLiabilities="
					+ otherLongTermLiabilities + ", otherBorrowings=" + otherBorrowings
					+ ", otherNclLongTermProvisions=" + otherNclLongTermProvisions + ", totalNonCurrentLiabilities="
					+ totalNonCurrentLiabilities + ", sundryCreditors=" + sundryCreditors + ", otherCurrentLiabilities="
					+ otherCurrentLiabilities + ", provisionalForTaxation=" + provisionalForTaxation
					+ ", totalCurrentLiabilities=" + totalCurrentLiabilities + ", totalLiabilities=" + totalLiabilities
					+ ", createdDate=" + createdDate + ", isActive=" + isActive + ", otherLiabilities="
					+ otherLiabilities + "]";
		}

}
