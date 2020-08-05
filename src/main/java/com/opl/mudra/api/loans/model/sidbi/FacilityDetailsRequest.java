package com.opl.mudra.api.loans.model.sidbi;

/**
 * Created by pooja.patel on 19-06-2019.
 */
public class FacilityDetailsRequest {

    private Long id;
    private Double rupeeTermLoan;
    private Double foreignCurrency;
    private String currencyType;
    private Double workingCapitalFund;
    private Double workingCapitalNonFund;
    private Double total;
    private Boolean isActive;
    private Double inrCurrency;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getRupeeTermLoan() {
        return rupeeTermLoan;
    }

    public void setRupeeTermLoan(Double rupeeTermLoan) {
        this.rupeeTermLoan = rupeeTermLoan;
    }

    public Double getForeignCurrency() {
        return foreignCurrency;
    }

    public void setForeignCurrency(Double foreignCurrency) {
        this.foreignCurrency = foreignCurrency;
    }

    public Double getWorkingCapitalFund() {
        return workingCapitalFund;
    }

    public void setWorkingCapitalFund(Double workingCapitalFund) {
        this.workingCapitalFund = workingCapitalFund;
    }

    public Double getWorkingCapitalNonFund() {
        return workingCapitalNonFund;
    }

    public void setWorkingCapitalNonFund(Double workingCapitalNonFund) {
        this.workingCapitalNonFund = workingCapitalNonFund;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

	public Double getInrCurrency() {
		return inrCurrency;
	}

	public void setInrCurrency(Double inrCurrency) {
		this.inrCurrency = inrCurrency;
	}

	@Override
	public String toString() {
		return "FacilityDetailsRequest [id=" + id + ", rupeeTermLoan=" + rupeeTermLoan + ", foreignCurrency="
				+ foreignCurrency + ", currencyType=" + currencyType + ", workingCapitalFund=" + workingCapitalFund
				+ ", workingCapitalNonFund=" + workingCapitalNonFund + ", total=" + total + ", isActive=" + isActive
				+ ", inrCurrency=" + inrCurrency + "]";
	}
    
}
