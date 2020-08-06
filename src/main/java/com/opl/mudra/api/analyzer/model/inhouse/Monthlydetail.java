
package com.opl.mudra.api.analyzer.model.inhouse;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "monthName",
    "topNHighValueDebitXn",
    "topNHighValueCreditXn",
    "startDate",
    "debits",
    "totalDebit",
    "credits",
    "totalCredit",
    "balMin",
    "balMax",
    "balAvg",
    "cashDeposits",
    "totalCashDeposit",
    "cashWithdrawals",
    "totalCashWithdrawal",
    "chqDeposits",
    "totalChqDeposit",
    "chqIssues",
    "totalChqIssue",
    "outwBounces",
    "inwBounces"
})
public class Monthlydetail {

    @JsonProperty("monthName")
    private String monthName;
    @JsonProperty("topNHighValueDebitXn")
    private List<TopNHighValueDebitXn> topNHighValueDebitXn = new ArrayList<TopNHighValueDebitXn>();
    @JsonProperty("topNHighValueCreditXn")
    private List<TopNHighValueCreditXn> topNHighValueCreditXn = new ArrayList<TopNHighValueCreditXn>();
    @JsonProperty("startDate")
    private String startDate;
    @JsonProperty("debits")
    private Integer debits;
    @JsonProperty("totalDebit")
    private Double totalDebit;
    @JsonProperty("credits")
    private Integer credits;
    @JsonProperty("totalCredit")
    private Double totalCredit;
    @JsonProperty("balMin")
    private Double balMin;
    @JsonProperty("balMax")
    private Double balMax;
    @JsonProperty("balAvg")
    private Double balAvg;
    @JsonProperty("cashDeposits")
    private Integer cashDeposits;
    @JsonProperty("totalCashDeposit")
    private Double totalCashDeposit;
    @JsonProperty("cashWithdrawals")
    private Integer cashWithdrawals;
    @JsonProperty("totalCashWithdrawal")
    private Double totalCashWithdrawal;
    @JsonProperty("chqDeposits")
    private Integer chqDeposits;
    @JsonProperty("totalChqDeposit")
    private Double totalChqDeposit;
    @JsonProperty("chqIssues")
    private Integer chqIssues;
    @JsonProperty("totalChqIssue")
    private Double totalChqIssue;
    @JsonProperty("outwBounces")
    private Integer outwBounces;
    @JsonProperty("inwBounces")
    private Integer inwBounces;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("monthName")
    public String getMonthName() {
        return monthName;
    }

    @JsonProperty("monthName")
    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    @JsonProperty("topNHighValueDebitXn")
    public List<TopNHighValueDebitXn> getTopNHighValueDebitXn() {
        return topNHighValueDebitXn;
    }

    @JsonProperty("topNHighValueDebitXn")
    public void setTopNHighValueDebitXn(List<TopNHighValueDebitXn> topNHighValueDebitXn) {
        this.topNHighValueDebitXn = topNHighValueDebitXn;
    }

    @JsonProperty("topNHighValueCreditXn")
    public List<TopNHighValueCreditXn> getTopNHighValueCreditXn() {
        return topNHighValueCreditXn;
    }

    @JsonProperty("topNHighValueCreditXn")
    public void setTopNHighValueCreditXn(List<TopNHighValueCreditXn> topNHighValueCreditXn) {
        this.topNHighValueCreditXn = topNHighValueCreditXn;
    }

    @JsonProperty("startDate")
    public String getStartDate() {
        return startDate;
    }

    @JsonProperty("startDate")
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    @JsonProperty("debits")
    public Integer getDebits() {
        return debits;
    }

    @JsonProperty("debits")
    public void setDebits(Integer debits) {
        this.debits = debits;
    }

    @JsonProperty("totalDebit")
    public Double getTotalDebit() {
        return totalDebit;
    }

    @JsonProperty("totalDebit")
    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
    }

    @JsonProperty("credits")
    public Integer getCredits() {
        return credits;
    }

    @JsonProperty("credits")
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @JsonProperty("totalCredit")
    public Double getTotalCredit() {
        return totalCredit;
    }

    @JsonProperty("totalCredit")
    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    @JsonProperty("balMin")
    public Double getBalMin() {
        return balMin;
    }

    @JsonProperty("balMin")
    public void setBalMin(Double balMin) {
        this.balMin = balMin;
    }

    @JsonProperty("balMax")
    public Double getBalMax() {
        return balMax;
    }

    @JsonProperty("balMax")
    public void setBalMax(Double balMax) {
        this.balMax = balMax;
    }

    @JsonProperty("balAvg")
    public Double getBalAvg() {
        return balAvg;
    }

    @JsonProperty("balAvg")
    public void setBalAvg(Double balAvg) {
        this.balAvg = balAvg;
    }

    @JsonProperty("cashDeposits")
    public Integer getCashDeposits() {
        return cashDeposits;
    }

    @JsonProperty("cashDeposits")
    public void setCashDeposits(Integer cashDeposits) {
        this.cashDeposits = cashDeposits;
    }

    @JsonProperty("totalCashDeposit")
    public Double getTotalCashDeposit() {
        return totalCashDeposit;
    }

    @JsonProperty("totalCashDeposit")
    public void setTotalCashDeposit(Double totalCashDeposit) {
        this.totalCashDeposit = totalCashDeposit;
    }

    @JsonProperty("cashWithdrawals")
    public Integer getCashWithdrawals() {
        return cashWithdrawals;
    }

    @JsonProperty("cashWithdrawals")
    public void setCashWithdrawals(Integer cashWithdrawals) {
        this.cashWithdrawals = cashWithdrawals;
    }

    @JsonProperty("totalCashWithdrawal")
    public Double getTotalCashWithdrawal() {
        return totalCashWithdrawal;
    }

    @JsonProperty("totalCashWithdrawal")
    public void setTotalCashWithdrawal(Double totalCashWithdrawal) {
        this.totalCashWithdrawal = totalCashWithdrawal;
    }

    @JsonProperty("chqDeposits")
    public Integer getChqDeposits() {
        return chqDeposits;
    }

    @JsonProperty("chqDeposits")
    public void setChqDeposits(Integer chqDeposits) {
        this.chqDeposits = chqDeposits;
    }

    @JsonProperty("totalChqDeposit")
    public Double getTotalChqDeposit() {
        return totalChqDeposit;
    }

    @JsonProperty("totalChqDeposit")
    public void setTotalChqDeposit(Double totalChqDeposit) {
        this.totalChqDeposit = totalChqDeposit;
    }

    @JsonProperty("chqIssues")
    public Integer getChqIssues() {
        return chqIssues;
    }

    @JsonProperty("chqIssues")
    public void setChqIssues(Integer chqIssues) {
        this.chqIssues = chqIssues;
    }

    @JsonProperty("totalChqIssue")
    public Double getTotalChqIssue() {
        return totalChqIssue;
    }

    @JsonProperty("totalChqIssue")
    public void setTotalChqIssue(Double totalChqIssue) {
        this.totalChqIssue = totalChqIssue;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return EqualsBuilder.reflectionEquals(this, other);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    @JsonProperty("outwBounces")
	public Integer getOutwBounces() {
		return outwBounces;
	}

    @JsonProperty("outwBounces")
	public void setOutwBounces(Integer outwBounces) {
		this.outwBounces = outwBounces;
	}

    @JsonProperty("inwBounces")
	public Integer getInwBounces() {
		return inwBounces;
	}

    @JsonProperty("inwBounces")
	public void setInwBounces(Integer inwBounces) {
		this.inwBounces = inwBounces;
	}
    
    

}
