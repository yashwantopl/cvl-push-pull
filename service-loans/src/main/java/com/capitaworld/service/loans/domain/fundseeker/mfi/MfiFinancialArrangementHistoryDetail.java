package com.capitaworld.service.loans.domain.fundseeker.mfi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//@Entity
//@Table(name = "fs_mfi_financial_arrangement_history_details")
public class MfiFinancialArrangementHistoryDetail implements Serializable{

	private static final long serialVersionUID = 8465685892442407596L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "financial_arrangement_id")
	private MfiFinancialArrangementsDetail financialArrangementId;
	
	@Column(name = "outstanding_amount")
	private Double outstandingAmount;
	
	@Column(name = "created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;
	
	@Column(name = "is_active")
	private Boolean isActive;
}
