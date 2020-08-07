package com.opl.mudra.api.analyzer.model.finbit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ravina.panchal on 14-09-2018.
 */
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "amount",
        "balanceAfterTransaction",
        "bank",
        "category",
        "dateTime",
        "description",
        "remark",
        "transactionNumber",
        "type",
        "valueDate"
})
public class Transactions {

    @JsonProperty("amount")
    private Double amount;
    @JsonProperty("balanceAfterTransaction")
    private Double balanceAfterTransaction;
    @JsonProperty("bank")
    private String bank;
    @JsonProperty("category")
    private String category;
    @JsonProperty("dateTime")
    private String dateTime;
    @JsonProperty("description")
    private String description;
    @JsonProperty("remark")
    private String remark;
    @JsonProperty("transactionNumber")
    private String transactionNumber;
    @JsonProperty("type")
    private String type;
    @JsonProperty("valueDate")
    private Object valueDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @JsonProperty("balanceAfterTransaction")
    public Double getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    @JsonProperty("balanceAfterTransaction")
    public void setBalanceAfterTransaction(Double balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    @JsonProperty("bank")
    public String getBank() {
        return bank;
    }

    @JsonProperty("bank")
    public void setBank(String bank) {
        this.bank = bank;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("dateTime")
    public String getDateTime() {
        return dateTime;
    }

    @JsonProperty("dateTime")
    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    @JsonProperty("remark")
    public void setRemark(String remark) {
        this.remark = remark;
    }

    @JsonProperty("transactionNumber")
    public String getTransactionNumber() {
        return transactionNumber;
    }

    @JsonProperty("transactionNumber")
    public void setTransactionNumber(String transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("valueDate")
    public Object getValueDate() {
        return valueDate;
    }

    @JsonProperty("valueDate")
    public void setValueDate(Object valueDate) {
        this.valueDate = valueDate;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "amount=" + amount +
                ", balanceAfterTransaction=" + balanceAfterTransaction +
                ", bank='" + bank + '\'' +
                ", category='" + category + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", description='" + description + '\'' +
                ", remark=" + remark +
                ", transactionNumber='" + transactionNumber + '\'' +
                ", type='" + type + '\'' +
                ", valueDate=" + valueDate +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}
