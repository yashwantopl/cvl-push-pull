package com.opl.mudra.api.analyzer.model.finbit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bank_account",
    "monthWiseAnalysis",
    "missingMonths",
    "FraudAnalytics",
    "inwardChequeOrEcsBounceMonthwise",
    "outwardChequeBounceMonthwise",
    "topFiveFundsReceived",
    "topFiveFundsTransfer",
    "transactions"
})
public class MainSummary {
	  @JsonProperty("bank_account")
	  private BankAccount bankAccount;
	  
	  @JsonProperty("monthWiseAnalysis")
	  private Summary summary;
}
