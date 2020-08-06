package com.opl.mudra.api.gst.model.karza;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class Averages {
	
	private Double avginv;
	
	private Double avginvcust;
	
	private Double avgmonthtax;
	
	private Double avgqtycust;
	
	private Double avgqtyinv;
	
	private Double avgrate;
	
	private Double avgttlcust;
	
	// added new fields as per new response
	
	private Double avgmonthval;
	
	private Double avgttltaxcust;
	
	private Double avgttltaxinv;
	
	private Double avgttlvalcust;
	
	private Double avgttlvalinv;
	

	public Double getAvginv() {
		return avginv;
	}

	public void setAvginv(Double avginv) {
		this.avginv = avginv;
	}

	public Double getAvginvcust() {
		return avginvcust;
	}

	public void setAvginvcust(Double avginvcust) {
		this.avginvcust = avginvcust;
	}

	public Double getAvgmonthtax() {
		return avgmonthtax;
	}

	public void setAvgmonthtax(Double avgmonthtax) {
		this.avgmonthtax = avgmonthtax;
	}

	public Double getAvgqtycust() {
		return avgqtycust;
	}

	public void setAvgqtycust(Double avgqtycust) {
		this.avgqtycust = avgqtycust;
	}

	public Double getAvgqtyinv() {
		return avgqtyinv;
	}

	public void setAvgqtyinv(Double avgqtyinv) {
		this.avgqtyinv = avgqtyinv;
	}

	public Double getAvgrate() {
		return avgrate;
	}

	public void setAvgrate(Double avgrate) {
		this.avgrate = avgrate;
	}

	public Double getAvgttlcust() {
		return avgttlcust;
	}

	public void setAvgttlcust(Double avgttlcust) {
		this.avgttlcust = avgttlcust;
	}

	/**
	 * @return the avgmonthval
	 */
	public Double getAvgmonthval() {
		return avgmonthval;
	}

	/**
	 * @param avgmonthval the avgmonthval to set
	 */
	public void setAvgmonthval(Double avgmonthval) {
		this.avgmonthval = avgmonthval;
	}

	/**
	 * @return the avgttltaxcust
	 */
	public Double getAvgttltaxcust() {
		return avgttltaxcust;
	}

	/**
	 * @param avgttltaxcust the avgttltaxcust to set
	 */
	public void setAvgttltaxcust(Double avgttltaxcust) {
		this.avgttltaxcust = avgttltaxcust;
	}

	/**
	 * @return the avgttltaxinv
	 */
	public Double getAvgttltaxinv() {
		return avgttltaxinv;
	}

	/**
	 * @param avgttltaxinv the avgttltaxinv to set
	 */
	public void setAvgttltaxinv(Double avgttltaxinv) {
		this.avgttltaxinv = avgttltaxinv;
	}

	/**
	 * @return the avgttlvalcust
	 */
	public Double getAvgttlvalcust() {
		return avgttlvalcust;
	}

	/**
	 * @param avgttlvalcust the avgttlvalcust to set
	 */
	public void setAvgttlvalcust(Double avgttlvalcust) {
		this.avgttlvalcust = avgttlvalcust;
	}

	/**
	 * @return the avgttlvalinv
	 */
	public Double getAvgttlvalinv() {
		return avgttlvalinv;
	}

	/**
	 * @param avgttlvalinv the avgttlvalinv to set
	 */
	public void setAvgttlvalinv(Double avgttlvalinv) {
		this.avgttlvalinv = avgttlvalinv;
	}
	
	

}
