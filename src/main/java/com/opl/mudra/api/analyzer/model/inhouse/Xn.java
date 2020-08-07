
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
    "Txn Date",
    "Value Date",
    "Description",
    "Ref No./Cheque No.",
    "Branch Code",
    "Debit",
    "Credit",
    "Balance",
    "Chq.No",
    "Category"
})
public class Xn {

    @JsonProperty("Txn Date")
    private String txnDate;
    @JsonProperty("Date")
    private String date;
    @JsonProperty("Value Date")
    private String valueDate;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Ref No./Cheque No.")
    private String refNoChequeNo;
    @JsonProperty("Branch Code")
    private String branchCode;
    @JsonProperty("Debit")
    private Double debit;
    @JsonProperty("Credit")
    private Double credit;
    @JsonProperty("Balance")
    private Double balance;
    @JsonProperty("Chq.No")
    private String ChqNo;
    @JsonProperty("Category")
    private String category;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("Txn Date")
    public String getTxnDate() {
        return txnDate;
    }

    @JsonProperty("Txn Date")
    public void setTxnDate(String txnDate) {
        this.txnDate = txnDate;
    }

    @JsonProperty("Value Date")
    public String getValueDate() {
        return valueDate;
    }

    @JsonProperty("Value Date")
    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Ref No./Cheque No.")
    public String getRefNoChequeNo() {
        return refNoChequeNo;
    }

    @JsonProperty("Ref No./Cheque No.")
    public void setRefNoChequeNo(String refNoChequeNo) {
        this.refNoChequeNo = refNoChequeNo;
    }

    @JsonProperty("Branch Code")
    public String getBranchCode() {
        return branchCode;
    }

    @JsonProperty("Branch Code")
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    @JsonProperty("Debit")
    public Double getDebit() {
        return debit;
    }

    @JsonProperty("Debit")
    public void setDebit(Double debit) {
        this.debit = debit;
    }

    @JsonProperty("Credit")
    public Double getCredit() {
        return credit;
    }

    @JsonProperty("Credit")
    public void setCredit(Double credit) {
        this.credit = credit;
    }

    @JsonProperty("Balance")
    public Double getBalance() {
        return balance;
    }

    @JsonProperty("Balance")
    public void setBalance(Double balance) {
        this.balance = balance;
    }
    
    

    @JsonProperty("Chq.No")
    public String getChqNo() {
		return ChqNo;
	}

    @JsonProperty("Chq.No")
	public void setChqNo(String chqNo) {
		ChqNo = chqNo;
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

    @JsonProperty("Category")
    public String getCategory() {
		return category;
	}

    @JsonProperty("Category")
	public void setCategory(String category) {
		this.category = category;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
    
    
	
}
