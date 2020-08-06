package com.opl.mudra.api.analyzer.model.finbit;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by ravina.panchal on 14-09-2018.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "topFiveFundsTransfer",
        "topFiveFundsReceived"
})
public class Top5FundMonthWise {
}
