
package com.opl.mudra.api.analyzer.model.inhouse;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "balAvg",
    "balMax",
    "balMin",
    "cashDeposits",
    "cashWithdrawals",
    "chqDeposits",
    "chqIssues",
    "credits",
    "debits",
    "totalCashDeposit",
    "totalCAshWithdrawal",
    "totalChqDeposit",
    "totalChqIssue",
    "totalCredit",
    "totalDebit",
    "outwBounces",
    "inwBounces"
})
public class Total {

    @JsonProperty("balAvg")
    private Double balAvg;
    @JsonProperty("balMax")
    private Double balMax;
    @JsonProperty("balMin")
    private Double balMin;
    @JsonProperty("cashDeposits")
    private Integer cashDeposits;
    @JsonProperty("cashWithdrawals")
    private Integer cashWithdrawals;
    @JsonProperty("chqDeposits")
    private Integer chqDeposits;
    @JsonProperty("chqIssues")
    private Integer chqIssues;
    @JsonProperty("credits")
    private Integer credits;
    @JsonProperty("debits")
    private Integer debits;
    @JsonProperty("totalCashDeposit")
    private Double totalCashDeposit;
    @JsonProperty("totalCAshWithdrawal")
    private Double totalCAshWithdrawal;
    @JsonProperty("totalChqDeposit")
    private Double totalChqDeposit;
    @JsonProperty("totalChqIssue")
    private Double totalChqIssue;
    @JsonProperty("totalCredit")
    private Double totalCredit;
    @JsonProperty("totalDebit")
    private Double totalDebit;
    @JsonProperty("outwBounces")
    private Integer outwBounces;
    @JsonProperty("inwBounces")
    private Integer inwBounces;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("balAvg")
    public Double getBalAvg() {
        return balAvg;
    }

    @JsonProperty("balAvg")
    public void setBalAvg(Double balAvg) {
        this.balAvg = balAvg;
    }

    @JsonProperty("balMax")
    public Double getBalMax() {
        return balMax;
    }

    @JsonProperty("balMax")
    public void setBalMax(Double balMax) {
        this.balMax = balMax;
    }

    @JsonProperty("balMin")
    public Double getBalMin() {
        return balMin;
    }

    @JsonProperty("balMin")
    public void setBalMin(Double balMin) {
        this.balMin = balMin;
    }

    @JsonProperty("cashDeposits")
    public Integer getCashDeposits() {
        return cashDeposits;
    }

    @JsonProperty("cashDeposits")
    public void setCashDeposits(Integer cashDeposits) {
        this.cashDeposits = cashDeposits;
    }

    @JsonProperty("cashWithdrawals")
    public Integer getCashWithdrawals() {
        return cashWithdrawals;
    }

    @JsonProperty("cashWithdrawals")
    public void setCashWithdrawals(Integer cashWithdrawals) {
        this.cashWithdrawals = cashWithdrawals;
    }

    @JsonProperty("chqDeposits")
    public Integer getChqDeposits() {
        return chqDeposits;
    }

    @JsonProperty("chqDeposits")
    public void setChqDeposits(Integer chqDeposits) {
        this.chqDeposits = chqDeposits;
    }

    @JsonProperty("chqIssues")
    public Integer getChqIssues() {
        return chqIssues;
    }

    @JsonProperty("chqIssues")
    public void setChqIssues(Integer chqIssues) {
        this.chqIssues = chqIssues;
    }

    @JsonProperty("credits")
    public Integer getCredits() {
        return credits;
    }

    @JsonProperty("credits")
    public void setCredits(Integer credits) {
        this.credits = credits;
    }

    @JsonProperty("debits")
    public Integer getDebits() {
        return debits;
    }

    @JsonProperty("debits")
    public void setDebits(Integer debits) {
        this.debits = debits;
    }

    @JsonProperty("totalCashDeposit")
    public Double getTotalCashDeposit() {
        return totalCashDeposit;
    }

    @JsonProperty("totalCashDeposit")
    public void setTotalCashDeposit(Double totalCashDeposit) {
        this.totalCashDeposit = totalCashDeposit;
    }

    @JsonProperty("totalCAshWithdrawal")
    public Double getTotalCAshWithdrawal() {
        return totalCAshWithdrawal;
    }

    @JsonProperty("totalCAshWithdrawal")
    public void setTotalCAshWithdrawal(Double totalCAshWithdrawal) {
        this.totalCAshWithdrawal = totalCAshWithdrawal;
    }

    @JsonProperty("totalChqDeposit")
    public Double getTotalChqDeposit() {
        return totalChqDeposit;
    }

    @JsonProperty("totalChqDeposit")
    public void setTotalChqDeposit(Double totalChqDeposit) {
        this.totalChqDeposit = totalChqDeposit;
    }

    @JsonProperty("totalChqIssue")
    public Double getTotalChqIssue() {
        return totalChqIssue;
    }

    @JsonProperty("totalChqIssue")
    public void setTotalChqIssue(Double totalChqIssue) {
        this.totalChqIssue = totalChqIssue;
    }

    @JsonProperty("totalCredit")
    public Double getTotalCredit() {
        return totalCredit;
    }

    @JsonProperty("totalCredit")
    public void setTotalCredit(Double totalCredit) {
        this.totalCredit = totalCredit;
    }

    @JsonProperty("totalDebit")
    public Double getTotalDebit() {
        return totalDebit;
    }

    @JsonProperty("totalDebit")
    public void setTotalDebit(Double totalDebit) {
        this.totalDebit = totalDebit;
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
