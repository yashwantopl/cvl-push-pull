
package com.opl.mudra.api.analyzer.model.finbit;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Summary {

    @JsonProperty("monthlyAvgBal")
    private MonthlyAvgBal monthlyAvgBal;
    @JsonProperty("maxBalance")
    private MaxBalance maxBalance;
    @JsonProperty("minBalance")
    private MinBalance minBalance;
    @JsonProperty("cashDeposit")
    private CashDeposit cashDeposit;
    @JsonProperty("cashWithdrawals")
    private CashWithdrawals cashWithdrawals;
    @JsonProperty("chqDeposit")
    private ChqDeposit chqDeposit;
    @JsonProperty("chqIssues")
    private ChqIssues chqIssues;
    @JsonProperty("credits")
    private Credits credits;
    @JsonProperty("debits")
    private Debits debits;
    @JsonProperty("totalNetCredit")
    private TotalNetCredit totalNetCredit;
    @JsonProperty("totalNetDebit")
    private TotalNetDebit totalNetDebit;
    @JsonProperty("inwBounce")
    private InwBounce inwBounce;
    @JsonProperty("outwBounce")
    private OutwBounce outwBounce;
    @JsonProperty("penaltyCharges")
    private PenaltyCharges penaltyCharges;
    @JsonProperty("interestPaid")
    private InterestPaid interestPaid;
    @JsonProperty("overdrawnPeriod")
    private OverdrawnPeriod overdrawnPeriod;
    @JsonProperty("peakUtilization")
    private PeakUtilization peakUtilization;
    @JsonProperty("averageUtilizationOD_CC")
    private AverageUtilizationODCC averageUtilizationODCC;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("monthlyAvgBal")
    public MonthlyAvgBal getMonthlyAvgBal() {
        return monthlyAvgBal;
    }

    @JsonProperty("monthlyAvgBal")
    public void setMonthlyAvgBal(MonthlyAvgBal monthlyAvgBal) {
        this.monthlyAvgBal = monthlyAvgBal;
    }

    @JsonProperty("maxBalance")
    public MaxBalance getMaxBalance() {
        return maxBalance;
    }

    @JsonProperty("maxBalance")
    public void setMaxBalance(MaxBalance maxBalance) {
        this.maxBalance = maxBalance;
    }

    @JsonProperty("minBalance")
    public MinBalance getMinBalance() {
        return minBalance;
    }

    @JsonProperty("minBalance")
    public void setMinBalance(MinBalance minBalance) {
        this.minBalance = minBalance;
    }

    @JsonProperty("cashDeposit")
    public CashDeposit getCashDeposit() {
        return cashDeposit;
    }

    @JsonProperty("cashDeposit")
    public void setCashDeposit(CashDeposit cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    @JsonProperty("cashWithdrawals")
    public CashWithdrawals getCashWithdrawals() {
        return cashWithdrawals;
    }

    @JsonProperty("cashWithdrawals")
    public void setCashWithdrawals(CashWithdrawals cashWithdrawals) {
        this.cashWithdrawals = cashWithdrawals;
    }

    @JsonProperty("chqDeposit")
    public ChqDeposit getChqDeposit() {
        return chqDeposit;
    }

    @JsonProperty("chqDeposit")
    public void setChqDeposit(ChqDeposit chqDeposit) {
        this.chqDeposit = chqDeposit;
    }

    @JsonProperty("chqIssues")
    public ChqIssues getChqIssues() {
        return chqIssues;
    }

    @JsonProperty("chqIssues")
    public void setChqIssues(ChqIssues chqIssues) {
        this.chqIssues = chqIssues;
    }

    @JsonProperty("credits")
    public Credits getCredits() {
        return credits;
    }

    @JsonProperty("credits")
    public void setCredits(Credits credits) {
        this.credits = credits;
    }

    @JsonProperty("debits")
    public Debits getDebits() {
        return debits;
    }

    @JsonProperty("debits")
    public void setDebits(Debits debits) {
        this.debits = debits;
    }

    @JsonProperty("inwBounce")
    public InwBounce getInwBounce() {
        return inwBounce;
    }

    @JsonProperty("inwBounce")
    public void setInwBounce(InwBounce inwBounce) {
        this.inwBounce = inwBounce;
    }

    @JsonProperty("outwBounce")
    public OutwBounce getOutwBounce() {
        return outwBounce;
    }

    @JsonProperty("outwBounce")
    public void setOutwBounce(OutwBounce outwBounce) {
        this.outwBounce = outwBounce;
    }

    @JsonProperty("penaltyCharges")
    public PenaltyCharges getPenaltyCharges() {
        return penaltyCharges;
    }

    @JsonProperty("penaltyCharges")
    public void setPenaltyCharges(PenaltyCharges penaltyCharges) {
        this.penaltyCharges = penaltyCharges;
    }

    @JsonProperty("interestPaid")
    public InterestPaid getInterestPaid() {
        return interestPaid;
    }

    @JsonProperty("interestPaid")
    public void setInterestPaid(InterestPaid interestPaid) {
        this.interestPaid = interestPaid;
    }

    @JsonProperty("overdrawnPeriod")
    public OverdrawnPeriod getOverdrawnPeriod() {
        return overdrawnPeriod;
    }

    @JsonProperty("overdrawnPeriod")
    public void setOverdrawnPeriod(OverdrawnPeriod overdrawnPeriod) {
        this.overdrawnPeriod = overdrawnPeriod;
    }

    @JsonProperty("peakUtilization")
    public PeakUtilization getPeakUtilization() {
        return peakUtilization;
    }

    @JsonProperty("peakUtilization")
    public void setPeakUtilization(PeakUtilization peakUtilization) {
        this.peakUtilization = peakUtilization;
    }

    @JsonProperty("averageUtilizationOD_CC")
    public AverageUtilizationODCC getAverageUtilizationODCC() {
        return averageUtilizationODCC;
    }

    @JsonProperty("averageUtilizationOD_CC")
    public void setAverageUtilizationODCC(AverageUtilizationODCC averageUtilizationODCC) {
        this.averageUtilizationODCC = averageUtilizationODCC;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("totalNetCredit")
    public TotalNetCredit getTotalNetCredit() {
		return totalNetCredit;
	}
    @JsonProperty("totalNetCredit")
	public void setTotalNetCredit(TotalNetCredit totalNetCredit) {
		this.totalNetCredit = totalNetCredit;
	}

    @JsonProperty("totalNetDebit")
	public TotalNetDebit getTotalNetDebit() {
		return totalNetDebit;
	}

    @JsonProperty("totalNetDebit")
	public void setTotalNetDebit(TotalNetDebit totalNetDebit) {
		this.totalNetDebit = totalNetDebit;
	}
    
    
    

}
