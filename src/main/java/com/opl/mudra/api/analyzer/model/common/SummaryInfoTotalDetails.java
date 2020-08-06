package com.opl.mudra.api.analyzer.model.common;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Total")
@XmlAccessorType(XmlAccessType.FIELD)
public class SummaryInfoTotalDetails {

	@XmlAttribute(name = "balAvg")
	private String balAvg;
	
	@XmlAttribute(name = "balMax")
	private String balMax;
	
	@XmlAttribute(name = "balMin")
	private String balMin;
	
	@XmlAttribute(name = "cashDeposits")
	private String cashDeposits;
	
	@XmlAttribute(name = "cashWithdrawals")
	private String cashWithdrawals;
	

	@XmlAttribute(name = "chqDeposits")
	private String chqDeposits;
	

	@XmlAttribute(name = "chqIssues")
	private String chqIssues;
	

	@XmlAttribute(name = "credits")
	private String credits;
	

	@XmlAttribute(name = "debits")
	private String debits;
	

	@XmlAttribute(name = "inwBounces")
	private String inwBounces;
	

	@XmlAttribute(name = "outwBounces")
	private String outwBounces;
	

	@XmlAttribute(name = "totalCashDeposit")
	private String totalCashDeposit;
	

	@XmlAttribute(name = "totalCashWithdrawal")
	private String totalCashWithdrawal;
	

	@XmlAttribute(name = "totalChqDeposit")
	private String totalChqDeposit;
	

	@XmlAttribute(name = "totalChqIssue")
	private String totalChqIssue;
	

	@XmlAttribute(name = "totalCredit")
	private String totalCredit;
	

	@XmlAttribute(name = "totalDebit")
	private String totalDebit;


	public String getBalAvg() {
		return balAvg;
	}


	public void setBalAvg(String balAvg) {
		this.balAvg = balAvg;
	}


	public String getBalMax() {
		return balMax;
	}


	public void setBalMax(String balMax) {
		this.balMax = balMax;
	}


	public String getBalMin() {
		return balMin;
	}


	public void setBalMin(String balMin) {
		this.balMin = balMin;
	}


	public String getCashDeposits() {
		return cashDeposits;
	}


	public void setCashDeposits(String cashDeposits) {
		this.cashDeposits = cashDeposits;
	}


	public String getCashWithdrawals() {
		return cashWithdrawals;
	}


	public void setCashWithdrawals(String cashWithdrawals) {
		this.cashWithdrawals = cashWithdrawals;
	}


	public String getChqDeposits() {
		return chqDeposits;
	}


	public void setChqDeposits(String chqDeposits) {
		this.chqDeposits = chqDeposits;
	}


	public String getChqIssues() {
		return chqIssues;
	}


	public void setChqIssues(String chqIssues) {
		this.chqIssues = chqIssues;
	}


	public String getCredits() {
		return credits;
	}


	public void setCredits(String credits) {
		this.credits = credits;
	}


	public String getDebits() {
		return debits;
	}


	public void setDebits(String debits) {
		this.debits = debits;
	}


	public String getInwBounces() {
		return inwBounces;
	}


	public void setInwBounces(String inwBounces) {
		this.inwBounces = inwBounces;
	}


	public String getOutwBounces() {
		return outwBounces;
	}


	public void setOutwBounces(String outwBounces) {
		this.outwBounces = outwBounces;
	}


	public String getTotalCashDeposit() {
		return totalCashDeposit;
	}


	public void setTotalCashDeposit(String totalCashDeposit) {
		this.totalCashDeposit = totalCashDeposit;
	}


	public String getTotalCashWithdrawal() {
		return totalCashWithdrawal;
	}


	public void setTotalCashWithdrawal(String totalCashWithdrawal) {
		this.totalCashWithdrawal = totalCashWithdrawal;
	}


	public String getTotalChqDeposit() {
		return totalChqDeposit;
	}


	public void setTotalChqDeposit(String totalChqDeposit) {
		this.totalChqDeposit = totalChqDeposit;
	}


	public String getTotalChqIssue() {
		return totalChqIssue;
	}


	public void setTotalChqIssue(String totalChqIssue) {
		this.totalChqIssue = totalChqIssue;
	}


	public String getTotalCredit() {
		return totalCredit;
	}


	public void setTotalCredit(String totalCredit) {
		this.totalCredit = totalCredit;
	}


	public String getTotalDebit() {
		return totalDebit;
	}


	public void setTotalDebit(String totalDebit) {
		this.totalDebit = totalDebit;
	}
	
	
	

	
}
