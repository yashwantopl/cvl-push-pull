package com.opl.mudra.api.gst.model.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class DataMapping {
	
	private List<DataMappingProjectedSales> previousYears = new ArrayList<>();

	private List<DataMappingProjectedSales> currentYears = new ArrayList<>();

	private Long statusCd;

	private ProjectedSalesCalcResponse projectedSalesCalcResponse;

	private Double previousYearTotal;

	private Double currentYearTotal;

	private Double imGstinPreviousYearTotal;


	private Double historicValue;





	public Double getHistoricValue() {
		return historicValue;
	}

	public void setHistoricValue(Double historicValue) {
		this.historicValue = historicValue;
	}

	public Double getImGstinPreviousYearTotal() {
		return imGstinPreviousYearTotal;
	}

	public void setImGstinPreviousYearTotal(Double imGstinPreviousYearTotal) {
		this.imGstinPreviousYearTotal = imGstinPreviousYearTotal;
	}

	private List<DataMappingProjectedSales> previousYearsGSTR1Sales = new ArrayList<>();
	private Double previousYearTotalGSTR1Sales;

	public List<DataMappingProjectedSales> getPreviousYearsGSTR1Sales() {
		return previousYearsGSTR1Sales;
	}

	public void setPreviousYearsGSTR1Sales(List<DataMappingProjectedSales> previousYearsGSTR1Sales) {
		this.previousYearsGSTR1Sales = previousYearsGSTR1Sales;
	}

	public Double getPreviousYearTotalGSTR1Sales() {
		return previousYearTotalGSTR1Sales;
	}

	public void setPreviousYearTotalGSTR1Sales(Double previousYearTotalGSTR1Sales) {
		this.previousYearTotalGSTR1Sales = previousYearTotalGSTR1Sales;
	}

	/**
	 * @return the previousYearTotal
	 */
	public Double getPreviousYearTotal() {
		return previousYearTotal;
	}

	/**
	 * @param previousYearTotal the previousYearTotal to set
	 */
	public void setPreviousYearTotal(Double previousYearTotal) {
		this.previousYearTotal = previousYearTotal;
	}

	/**
	 * @return the currentYearTotal
	 */
	public Double getCurrentYearTotal() {
		return currentYearTotal;
	}

	/**
	 * @param currentYearTotal the currentYearTotal to set
	 */
	public void setCurrentYearTotal(Double currentYearTotal) {
		this.currentYearTotal = currentYearTotal;
	}

	/**
	 * @return the projectedSalesCalcResponse
	 */
	public ProjectedSalesCalcResponse getProjectedSalesCalcResponse() {
		return projectedSalesCalcResponse;
	}

	/**
	 * @param projectedSalesCalcResponse the projectedSalesCalcResponse to set
	 */
	public void setProjectedSalesCalcResponse(ProjectedSalesCalcResponse projectedSalesCalcResponse) {
		this.projectedSalesCalcResponse = projectedSalesCalcResponse;
	}

	/**
	 * @return the statusCd
	 */
	public Long getStatusCd() {
		return statusCd;
	}

	/**
	 * @param statusCd the statusCd to set
	 */
	public void setStatusCd(Long statusCd) {
		this.statusCd = statusCd;
	}

	/**
	 * @return the previousYears
	 */
	public List<DataMappingProjectedSales> getPreviousYears() {
		return previousYears;
	}

	/**
	 * @param previousYears the previousYears to set
	 */
	public void setPreviousYears(List<DataMappingProjectedSales> previousYears) {
		this.previousYears = previousYears;
	}

	/**
	 * @return the currentYears
	 */
	public List<DataMappingProjectedSales> getCurrentYears() {
		return currentYears;
	}

	/**
	 * @param currentYears the currentYears to set
	 */
	public void setCurrentYears(List<DataMappingProjectedSales> currentYears) {
		this.currentYears = currentYears;
	}

	public void addPreviousYear(DataMappingProjectedSales previousYear) {
		getPreviousYears().add(previousYear);
//		return previousYear;
	}

	public void addCurrentYears(DataMappingProjectedSales currentYears) {
		getCurrentYears().add(currentYears);
//		return currentYears;
	}

	public void addPreviousYearsGSTR1Sales(DataMappingProjectedSales currentYears) {
		getPreviousYearsGSTR1Sales().add(currentYears);
//		return currentYears;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataMapping [previousYears=" + previousYears + ", currentYears=" + currentYears + ", statusCd="
				+ statusCd + "]";
	}
	
	
	
}
