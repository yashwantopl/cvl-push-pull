package com.capitaworld.service.loans.domain.fundseeker.corporate;

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

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_corporate_dpr_user_data_details database table.
 * 
 */
@Entity
@Table(name="fs_corporate_dpr_user_data_details")
public class DprUserDataDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="absence_civic_restrictions")
	private String absenceCivicRestrictions;

	@ManyToOne
	@JoinColumn(name="application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="competitive_landscape")
	private String competitiveLandscape;

	@Column(name="created_by")
	private Long createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="created_date")
	private Date createdDate;

	@Column(name="global_scenario")
	private String globalScenario;

	@Column(name="is_active")
	private Boolean isActive;

	@Column(name="key_players")
	private String keyPlayers;

	@Column(name="labour_availability")
	private String labourAvailability;

	@Column(name="manufacturing_process")
	private String manufacturingProcess;

	@Column(name="market_for_the_product")
	private String marketForTheProduct;

	@Column(name="market_growth")
	private String marketGrowth;

	@Column(name="market_needs")
	private String marketNeeds;

	@Column(name="market_trends")
	private String marketTrends;

	@Column(name="markets_currently_served")
	private String marketsCurrentlyServed;

	@Column(name="modified_by")
	private Long modifiedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="modified_date")
	private Date modifiedDate;

	@Column(name="national_scenario")
	private String nationalScenario;

	@Column(name="other_availability")
	private String otherAvailability;

	@Column(name="other_benefits")
	private String otherBenefits;

	@Column(name="power_availability")
	private String powerAvailability;

	@Column(name="project_Jjustification")
	private String projectJjustification;

	@Column(name="proximity_to_source_raw_materials")
	private String proximityToSourceRawMaterials;

	@Column(name="shifts_in_day_number")
	private String shiftsInDayNumber;

	@Column(name="special_features_products_and_services")
	private String specialFeaturesProductsAndServices;

	@Column(name="target_market_strategy")
	private String targetMarketStrategy;

	@Column(name="technical_know_how")
	private String technicalKnowHow;

	@Column(name="transport_availability")
	private String transportAvailability;

	@Column(name="water_availability")
	private String waterAvailability;

	@Column(name="whether_clearance_is_obtained_from_pollution_control_authority")
	private String whetherClearanceIsObtainedFromPollutionControlAuthority;

	private String working_Days_in_month__number;
	
	@Column(name="storage_details_id")
	private Long storageDetailsId;

	public Long getStorageDetailsId() {
		return storageDetailsId;
	}

	public void setStorageDetailsId(Long storageDetailsId) {
		this.storageDetailsId = storageDetailsId;
	}

	public DprUserDataDetail() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAbsenceCivicRestrictions() {
		return this.absenceCivicRestrictions;
	}

	public void setAbsenceCivicRestrictions(String absenceCivicRestrictions) {
		this.absenceCivicRestrictions = absenceCivicRestrictions;
	}

	public LoanApplicationMaster getApplicationId() {
		return this.applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public String getCompetitiveLandscape() {
		return this.competitiveLandscape;
	}

	public void setCompetitiveLandscape(String competitiveLandscape) {
		this.competitiveLandscape = competitiveLandscape;
	}

	public Long getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getGlobalScenario() {
		return this.globalScenario;
	}

	public void setGlobalScenario(String globalScenario) {
		this.globalScenario = globalScenario;
	}

	public Boolean getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getKeyPlayers() {
		return this.keyPlayers;
	}

	public void setKeyPlayers(String keyPlayers) {
		this.keyPlayers = keyPlayers;
	}

	public String getLabourAvailability() {
		return this.labourAvailability;
	}

	public void setLabourAvailability(String labourAvailability) {
		this.labourAvailability = labourAvailability;
	}

	public String getManufacturingProcess() {
		return this.manufacturingProcess;
	}

	public void setManufacturingProcess(String manufacturingProcess) {
		this.manufacturingProcess = manufacturingProcess;
	}

	public String getMarketForTheProduct() {
		return this.marketForTheProduct;
	}

	public void setMarketForTheProduct(String marketForTheProduct) {
		this.marketForTheProduct = marketForTheProduct;
	}

	public String getMarketGrowth() {
		return this.marketGrowth;
	}

	public void setMarketGrowth(String marketGrowth) {
		this.marketGrowth = marketGrowth;
	}

	public String getMarketNeeds() {
		return this.marketNeeds;
	}

	public void setMarketNeeds(String marketNeeds) {
		this.marketNeeds = marketNeeds;
	}

	public String getMarketTrends() {
		return this.marketTrends;
	}

	public void setMarketTrends(String marketTrends) {
		this.marketTrends = marketTrends;
	}

	public String getMarketsCurrentlyServed() {
		return this.marketsCurrentlyServed;
	}

	public void setMarketsCurrentlyServed(String marketsCurrentlyServed) {
		this.marketsCurrentlyServed = marketsCurrentlyServed;
	}

	public Long getModifiedBy() {
		return this.modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return this.modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getNationalScenario() {
		return this.nationalScenario;
	}

	public void setNationalScenario(String nationalScenario) {
		this.nationalScenario = nationalScenario;
	}

	public String getOtherAvailability() {
		return this.otherAvailability;
	}

	public void setOtherAvailability(String otherAvailability) {
		this.otherAvailability = otherAvailability;
	}

	public String getOtherBenefits() {
		return this.otherBenefits;
	}

	public void setOtherBenefits(String otherBenefits) {
		this.otherBenefits = otherBenefits;
	}

	public String getPowerAvailability() {
		return this.powerAvailability;
	}

	public void setPowerAvailability(String powerAvailability) {
		this.powerAvailability = powerAvailability;
	}

	public String getProjectJjustification() {
		return this.projectJjustification;
	}

	public void setProjectJjustification(String projectJjustification) {
		this.projectJjustification = projectJjustification;
	}

	public String getProximityToSourceRawMaterials() {
		return this.proximityToSourceRawMaterials;
	}

	public void setProximityToSourceRawMaterials(String proximityToSourceRawMaterials) {
		this.proximityToSourceRawMaterials = proximityToSourceRawMaterials;
	}

	public String getShiftsInDayNumber() {
		return this.shiftsInDayNumber;
	}

	public void setShiftsInDayNumber(String shiftsInDayNumber) {
		this.shiftsInDayNumber = shiftsInDayNumber;
	}

	public String getSpecialFeaturesProductsAndServices() {
		return this.specialFeaturesProductsAndServices;
	}

	public void setSpecialFeaturesProductsAndServices(String specialFeaturesProductsAndServices) {
		this.specialFeaturesProductsAndServices = specialFeaturesProductsAndServices;
	}

	public String getTargetMarketStrategy() {
		return this.targetMarketStrategy;
	}

	public void setTargetMarketStrategy(String targetMarketStrategy) {
		this.targetMarketStrategy = targetMarketStrategy;
	}

	public String getTechnicalKnowHow() {
		return this.technicalKnowHow;
	}

	public void setTechnicalKnowHow(String technicalKnowHow) {
		this.technicalKnowHow = technicalKnowHow;
	}

	public String getTransportAvailability() {
		return this.transportAvailability;
	}

	public void setTransportAvailability(String transportAvailability) {
		this.transportAvailability = transportAvailability;
	}

	public String getWaterAvailability() {
		return this.waterAvailability;
	}

	public void setWaterAvailability(String waterAvailability) {
		this.waterAvailability = waterAvailability;
	}

	public String getWhetherClearanceIsObtainedFromPollutionControlAuthority() {
		return this.whetherClearanceIsObtainedFromPollutionControlAuthority;
	}

	public void setWhetherClearanceIsObtainedFromPollutionControlAuthority(String whetherClearanceIsObtainedFromPollutionControlAuthority) {
		this.whetherClearanceIsObtainedFromPollutionControlAuthority = whetherClearanceIsObtainedFromPollutionControlAuthority;
	}

	public String getWorking_Days_in_month__number() {
		return this.working_Days_in_month__number;
	}

	public void setWorking_Days_in_month__number(String working_Days_in_month__number) {
		this.working_Days_in_month__number = working_Days_in_month__number;
	}

}