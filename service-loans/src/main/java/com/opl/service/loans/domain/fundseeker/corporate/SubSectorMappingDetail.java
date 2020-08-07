package com.opl.service.loans.domain.fundseeker.corporate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the fs_corporate_sub_sector_mapping database table.
 * 
 */
@Entity
@Table(name="fs_corporate_sub_sector_mapping")
public class SubSectorMappingDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Long  id;

	@Column(name="sector_id")
	private Long sectorId;

	@Column(name="sub_sector_id")
	private Long subSectorId;

	public SubSectorMappingDetail() {
		// Do nothing because of X and Y.
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSectorId() {
		return this.sectorId;
	}

	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}

	public Long getSubSectorId() {
		return this.subSectorId;
	}

	public void setSubSectorId(Long subSectorId) {
		this.subSectorId = subSectorId;
	}

}