package com.capitaworld.service.loans.domain.fundseeker.corporate;

import com.capitaworld.service.loans.domain.fundseeker.ApplicationProposalMapping;
import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the fs_corporate_finance_means_details database table.
 * 
 */
@Entity
@Table(name="fs_past_performance_details")
public class FsPastPerformanceDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="application_id")
	private Long applicationId;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="netSalesPastYear1")
	private Long netSalesPastYear1;

	@Column(name="netSalesPastYear2")
	private Long netSalesPastYear2;

	@Column(name="netSalesPresentYear")
	private Long netSalesPresentYear;

	@Column(name="netSalesNextYear")
	private Long netSalesNextYear;

	@Column(name="netProfitPastYear1")
	private Long netProfitPastYear1;

	@Column(name="netProfitPastYear2")
	private Long netProfitPastYear2;

	@Column(name="netProfitPresentYear")
	private Long netProfitPresentYear;

	@Column(name="netProfitNextYear")
	private Long netProfitNextYear;

	@Column(name="compNetWorthPastYear1")
	private Long compNetWorthPastYear1;

	@Column(name="compNetWorthPastYear2")
	private Long compNetWorthPastYear2;

	@Column(name="compNetWorthPresentYear")
	private Long compNetWorthPresentYear;

	@Column(name="compNetWorthNextYear")
	private Long compNetWorthNextYear;

	@Column(name="contingent_liabilities")
	private String contingentLiabilities;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Long getNetSalesPastYear1() {
		return netSalesPastYear1;
	}

	public void setNetSalesPastYear1(Long netSalesPastYear1) {
		this.netSalesPastYear1 = netSalesPastYear1;
	}

	public Long getNetSalesPastYear2() {
		return netSalesPastYear2;
	}

	public void setNetSalesPastYear2(Long netSalesPastYear2) {
		this.netSalesPastYear2 = netSalesPastYear2;
	}

	public Long getNetSalesPresentYear() {
		return netSalesPresentYear;
	}

	public void setNetSalesPresentYear(Long netSalesPresentYear) {
		this.netSalesPresentYear = netSalesPresentYear;
	}

	public Long getNetSalesNextYear() {
		return netSalesNextYear;
	}

	public void setNetSalesNextYear(Long netSalesNextYear) {
		this.netSalesNextYear = netSalesNextYear;
	}

	public Long getNetProfitPastYear1() {
		return netProfitPastYear1;
	}

	public void setNetProfitPastYear1(Long netProfitPastYear1) {
		this.netProfitPastYear1 = netProfitPastYear1;
	}

	public Long getNetProfitPastYear2() {
		return netProfitPastYear2;
	}

	public void setNetProfitPastYear2(Long netProfitPastYear2) {
		this.netProfitPastYear2 = netProfitPastYear2;
	}

	public Long getNetProfitPresentYear() {
		return netProfitPresentYear;
	}

	public void setNetProfitPresentYear(Long netProfitPresentYear) {
		this.netProfitPresentYear = netProfitPresentYear;
	}

	public Long getNetProfitNextYear() {
		return netProfitNextYear;
	}

	public void setNetProfitNextYear(Long netProfitNextYear) {
		this.netProfitNextYear = netProfitNextYear;
	}

	public Long getCompNetWorthPastYear1() {
		return compNetWorthPastYear1;
	}

	public void setCompNetWorthPastYear1(Long compNetWorthPastYear1) {
		this.compNetWorthPastYear1 = compNetWorthPastYear1;
	}

	public Long getCompNetWorthPastYear2() {
		return compNetWorthPastYear2;
	}

	public void setCompNetWorthPastYear2(Long compNetWorthPastYear2) {
		this.compNetWorthPastYear2 = compNetWorthPastYear2;
	}

	public Long getCompNetWorthPresentYear() {
		return compNetWorthPresentYear;
	}

	public void setCompNetWorthPresentYear(Long compNetWorthPresentYear) {
		this.compNetWorthPresentYear = compNetWorthPresentYear;
	}

	public Long getCompNetWorthNextYear() {
		return compNetWorthNextYear;
	}

	public void setCompNetWorthNextYear(Long compNetWorthNextYear) {
		this.compNetWorthNextYear = compNetWorthNextYear;
	}

	public String getContingentLiabilities() {
		return contingentLiabilities;
	}

	public void setContingentLiabilities(String contingentLiabilities) {
		this.contingentLiabilities = contingentLiabilities;
	}
}