package com.capitaworld.service.loans.model.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class GraphResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Double> pats = null;
    private List<Double> sales = null;
    private List<Double> ebidta = null;
    private List<Double> netWorth = null;
    private List<Double> currentAsset = null;
    private List<Double> currentLiabilities = null;
    private List<Double> fixedAsset = null;
    private List<Double> debt = null;
    
    private List<Double> patsPercentage = null;
    private List<Double> salesPercentage = null;
    private List<Double> ebidtaPercentage = null;
    private List<Double> roePercentage = null;
    private List<Double> rocePercentage = null;
    private List<Double> currentRatio = null;
    private List<Double> debtEquityPercentage = null;
    
    private List<String> xAxisOfPat = null;
    
    public GraphResponse() {
		super();
		pats = Collections.emptyList();
		sales = Collections.emptyList();
		ebidta = Collections.emptyList();
		netWorth = Collections.emptyList();
		currentAsset = Collections.emptyList();
		currentLiabilities = Collections.emptyList();
		fixedAsset = Collections.emptyList();
		debt = Collections.emptyList();
		
		//Percentage
		patsPercentage = Collections.emptyList();
		salesPercentage = Collections.emptyList();
		ebidtaPercentage = Collections.emptyList();
		roePercentage = Collections.emptyList();
		rocePercentage = Collections.emptyList();
		currentRatio = Collections.emptyList();
		debtEquityPercentage = Collections.emptyList();
		
		xAxisOfPat = Collections.emptyList();
	}

	public List<Double> getPats() {
		return pats;
	}

	public void setPats(List<Double> pats) {
		this.pats = pats;
	}

	public List<Double> getSales() {
		return sales;
	}

	public void setSales(List<Double> sales) {
		this.sales = sales;
	}

	public List<Double> getEbidta() {
		return ebidta;
	}

	public void setEbidta(List<Double> ebidta) {
		this.ebidta = ebidta;
	}

	public List<Double> getNetWorth() {
		return netWorth;
	}

	public void setNetWorth(List<Double> netWorth) {
		this.netWorth = netWorth;
	}

	public List<Double> getCurrentAsset() {
		return currentAsset;
	}

	public void setCurrentAsset(List<Double> currentAsset) {
		this.currentAsset = currentAsset;
	}

	public List<Double> getCurrentLiabilities() {
		return currentLiabilities;
	}

	public void setCurrentLiabilities(List<Double> currentLiabilities) {
		this.currentLiabilities = currentLiabilities;
	}

	public List<Double> getFixedAsset() {
		return fixedAsset;
	}

	public void setFixedAsset(List<Double> fixedAsset) {
		this.fixedAsset = fixedAsset;
	}

	public List<Double> getDebt() {
		return debt;
	}

	public void setDebt(List<Double> debt) {
		this.debt = debt;
	}

	public List<Double> getPatsPercentage() {
		return patsPercentage;
	}

	public void setPatsPercentage(List<Double> patsPercentage) {
		this.patsPercentage = patsPercentage;
	}

	public List<Double> getSalesPercentage() {
		return salesPercentage;
	}

	public void setSalesPercentage(List<Double> salesPercentage) {
		this.salesPercentage = salesPercentage;
	}

	public List<Double> getEbidtaPercentage() {
		return ebidtaPercentage;
	}

	public void setEbidtaPercentage(List<Double> ebidtaPercentage) {
		this.ebidtaPercentage = ebidtaPercentage;
	}

	public List<Double> getRoePercentage() {
		return roePercentage;
	}

	public void setRoePercentage(List<Double> roePercentage) {
		this.roePercentage = roePercentage;
	}

	public List<Double> getRocePercentage() {
		return rocePercentage;
	}

	public void setRocePercentage(List<Double> rocePercentage) {
		this.rocePercentage = rocePercentage;
	}

	public List<Double> getCurrentRatio() {
		return currentRatio;
	}

	public void setCurrentRatio(List<Double> currentRatio) {
		this.currentRatio = currentRatio;
	}

	public List<Double> getDebtEquityPercentage() {
		return debtEquityPercentage;
	}

	public void setDebtEquityPercentage(List<Double> debtEquityPercentage) {
		this.debtEquityPercentage = debtEquityPercentage;
	}

	public List<String> getxAxisOfPat() {
		return xAxisOfPat;
	}

	public void setxAxisOfPat(List<String> xAxisOfPat) {
		this.xAxisOfPat = xAxisOfPat;
	}

}
