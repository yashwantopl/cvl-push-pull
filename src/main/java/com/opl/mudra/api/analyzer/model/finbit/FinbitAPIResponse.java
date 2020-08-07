package com.opl.mudra.api.analyzer.model.finbit;

import java.util.List;

/**
 * Created by ravina.panchal on 15-09-2018.
 */
public class FinbitAPIResponse {

    private MonthwiseSummary monthwiseSummary;
    private List<Top5Detail> top5FundReceived;
    private List<Top5Detail> top5FundTransfer;
    private List<BounceTransactions> inwardBounceTransactions;
    private List<BounceTransactions> outwardBounceTransactions;
    private List<Transactions> transactionsList;


    public MonthwiseSummary getMonthwiseSummary() {
        return monthwiseSummary;
    }

    public void setMonthwiseSummary(MonthwiseSummary monthwiseSummary) {
        this.monthwiseSummary = monthwiseSummary;
    }

    public List<Top5Detail> getTop5FundReceived() {
        return top5FundReceived;
    }

    public void setTop5FundReceived(List<Top5Detail> top5FundReceived) {
        this.top5FundReceived = top5FundReceived;
    }

    public List<Top5Detail> getTop5FundTransfer() {
        return top5FundTransfer;
    }

    public void setTop5FundTransfer(List<Top5Detail> top5FundTransfer) {
        this.top5FundTransfer = top5FundTransfer;
    }

    public List<BounceTransactions> getInwardBounceTransactions() {
        return inwardBounceTransactions;
    }

    public void setInwardBounceTransactions(List<BounceTransactions> inwardBounceTransactions) {
        this.inwardBounceTransactions = inwardBounceTransactions;
    }

    public List<BounceTransactions> getOutwardBounceTransactions() {
        return outwardBounceTransactions;
    }

    public void setOutwardBounceTransactions(List<BounceTransactions> outwardBounceTransactions) {
        this.outwardBounceTransactions = outwardBounceTransactions;
    }


    public List<Transactions> getTransactionsList() {
        return transactionsList;
    }

    public void setTransactionsList(List<Transactions> transactionsList) {
        this.transactionsList = transactionsList;
    }

	@Override
	public String toString() {
		return "FinbitAPIResponse [monthwiseSummary=" + monthwiseSummary + ", top5FundReceived=" + top5FundReceived
				+ ", top5FundTransfer=" + top5FundTransfer + ", inwardBounceTransactions=" + inwardBounceTransactions
				+ ", outwardBounceTransactions=" + outwardBounceTransactions + ", transactionsList=" + transactionsList
				+ "]";
	}
    
    
}
