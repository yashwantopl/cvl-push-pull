package com.capitaworld.service.loans.domain.fundseeker.agri;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.capitaworld.service.loans.domain.fundseeker.AuditActivity;

/**
 * The persistent class for the
 * fs_agri_corp_details database table.
 * 
 */
@Entity
@Table(name = "fs_agri_corp_details")
public class CorpDetail extends AuditActivity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "year")
	private String year;

	@Column(name = "application_id")
	private Long applicationId;
	
	@Column(name = "season_id")
	private Integer seasonId;
	
	@Column(name = "crop_id")
	private Integer cropId;
	
	@Column(name = "msp_quintal")
	private String mspQuintal;

	public CorpDetail() {
		// Do nothing because of X and Y.
	}
	
	public CorpDetail(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Integer getSeasonId() {
		return seasonId;
	}

	public void setSeasonId(Integer seasonId) {
		this.seasonId = seasonId;
	}

	public Integer getCropId() {
		return cropId;
	}

	public void setCropId(Integer cropId) {
		this.cropId = cropId;
	}

	public String getMspQuintal() {
		return mspQuintal;
	}

	public void setMspQuintal(String mspQuintal) {
		this.mspQuintal = mspQuintal;
	}
}

