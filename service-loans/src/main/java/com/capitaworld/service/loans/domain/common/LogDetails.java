package com.capitaworld.service.loans.domain.common;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the log_details database table.
 * 
 */
@Entity
@Table(name = "log_details")
public class LogDetails implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date")
	private Date createdDate;

	@Column(name = "date_type_master_id")
	private Integer dateTypeMasterId;

	@Column(name = "loan_application_master_id")
	private Long loanApplicationMasterId;

	@Column(name = "product_mapping_id")
	private Long productMappingId;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Integer getDateTypeMasterId() {
		return dateTypeMasterId;
	}

	public void setDateTypeMasterId(Integer dateTypeMasterId) {
		this.dateTypeMasterId = dateTypeMasterId;
	}

	public Long getLoanApplicationMasterId() {
		return this.loanApplicationMasterId;
	}

	public void setLoanApplicationMasterId(Long loanApplicationMasterId) {
		this.loanApplicationMasterId = loanApplicationMasterId;
	}

	public Long getProductMappingId() {
		return this.productMappingId;
	}

	public void setProductMappingId(Long productMappingId) {
		this.productMappingId = productMappingId;
	}

}