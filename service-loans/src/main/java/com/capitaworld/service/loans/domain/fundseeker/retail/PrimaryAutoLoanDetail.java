package com.capitaworld.service.loans.domain.fundseeker.retail;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capitaworld.service.loans.domain.fundseeker.LoanApplicationMaster;


/**
 * The persistent class for the fs_retail_primary_car_loan_details database table.
 * 
 */
@Entity
@Table(name="fs_retail_primary_auto_loan_details")
public class PrimaryAutoLoanDetail extends LoanApplicationMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "application_id")
	private LoanApplicationMaster applicationId;

	@Column(name="vehicle_segment")
	Integer vehicleSegment; 
	
	@Column(name="vehicle_age")
	Integer vehicleAge; 
	
	@Column(name="vehicle_engine_volume")
	Integer vehicleEngineVolume; 
	
	@Column(name="vehicle_use")
	Integer vehicleUse; 
	
	@Column(name="vehicle_ex_show_room_price")
	Long vehicleExShowRoomPrice;
	
	@Column(name="vehicle_on_road_price")
	Long vehicleOnRoadPrice; 
	
	@Column(name="vehicle_agreed_purchase_price")
	Long vehicleAgreedPurchasePrice;
	
	@Column(name="is_vehicleHypothecation")
	Boolean isVehicleHypothecation;
	
	@Column(name="is_check_off_direct_pay_emi")
	private Boolean isCheckOffDirectPayEmi;
	
	@Column(name="is_check_off_agree_to_pay_outstanding")
	private Boolean isCheckOffAgreeToPayOutstanding;
	
	@Column(name="is_check_off_shift_sal_acc")
	private Boolean isCheckOffShiftSalAcc;
	
	@Column(name="is_check_off_pay_outstnd_amount")
	private Boolean isCheckOffPayOutstndAmount;
	
	@Column(name="is_check_off_not_change_sal_acc")
	private Boolean isCheckOffNotChangeSalAcc;

	@Column(name="manufacturer_id")
	private Long manufacturerId;

	@Column(name="asset_model_id")
	private Long assetModelId;

	@Column(name="asset_make")
	private Integer assetMake;

	@Column(name="supplier_state_id")
	private Long supplierStateId;

	@Column(name="supplier_city_id")
	private Long supplierCityId;

	@Column(name="supplier_id")
	private Long supplierId;

	@Column(name="other_suplier_name")
	private String otherSuplierName;
	@Column(name="other_asset_model_name")
	private String otherAssetModelName;
	@Column(name="other_manufacturer_name")
	private String otherManufacturerName;
	
	
	public PrimaryAutoLoanDetail() {
		// TODO Auto-generated constructor stub
	}


	public Integer getVehicleSegment() {
		return vehicleSegment;
	}


	public void setVehicleSegment(Integer vehicleSegment) {
		this.vehicleSegment = vehicleSegment;
	}


	public Integer getVehicleAge() {
		return vehicleAge;
	}


	public void setVehicleAge(Integer vehicleAge) {
		this.vehicleAge = vehicleAge;
	}


	public Integer getVehicleEngineVolume() {
		return vehicleEngineVolume;
	}


	public void setVehicleEngineVolume(Integer vehicleEngineVolume) {
		this.vehicleEngineVolume = vehicleEngineVolume;
	}


	
	public Integer getVehicleUse() {
		return vehicleUse;
	}

	public void setVehicleUse(Integer vehicleUse) {
		this.vehicleUse = vehicleUse;
	}

	public Long getVehicleExShowRoomPrice() {
		return vehicleExShowRoomPrice;
	}


	public void setVehicleExShowRoomPrice(Long vehicleExShowRoomPrice) {
		this.vehicleExShowRoomPrice = vehicleExShowRoomPrice;
	}


	public Long getVehicleOnRoadPrice() {
		return vehicleOnRoadPrice;
	}


	public void setVehicleOnRoadPrice(Long vehicleOnRoadPrice) {
		this.vehicleOnRoadPrice = vehicleOnRoadPrice;
	}


	public Long getVehicleAgreedPurchasePrice() {
		return vehicleAgreedPurchasePrice;
	}


	public void setVehicleAgreedPurchasePrice(Long vehicleAgreedPurchasePrice) {
		this.vehicleAgreedPurchasePrice = vehicleAgreedPurchasePrice;
	}


	public Boolean getIsVehicleHypothecation() {
		return isVehicleHypothecation;
	}


	public void setIsVehicleHypothecation(Boolean isVehicleHypothecation) {
		this.isVehicleHypothecation = isVehicleHypothecation;
	}


	public Boolean getIsCheckOffDirectPayEmi() {
		return isCheckOffDirectPayEmi;
	}


	public void setIsCheckOffDirectPayEmi(Boolean isCheckOffDirectPayEmi) {
		this.isCheckOffDirectPayEmi = isCheckOffDirectPayEmi;
	}


	public Boolean getIsCheckOffAgreeToPayOutstanding() {
		return isCheckOffAgreeToPayOutstanding;
	}


	public void setIsCheckOffAgreeToPayOutstanding(Boolean isCheckOffAgreeToPayOutstanding) {
		this.isCheckOffAgreeToPayOutstanding = isCheckOffAgreeToPayOutstanding;
	}


	public Boolean getIsCheckOffShiftSalAcc() {
		return isCheckOffShiftSalAcc;
	}


	public void setIsCheckOffShiftSalAcc(Boolean isCheckOffShiftSalAcc) {
		this.isCheckOffShiftSalAcc = isCheckOffShiftSalAcc;
	}


	public Boolean getIsCheckOffPayOutstndAmount() {
		return isCheckOffPayOutstndAmount;
	}


	public void setIsCheckOffPayOutstndAmount(Boolean isCheckOffPayOutstndAmount) {
		this.isCheckOffPayOutstndAmount = isCheckOffPayOutstndAmount;
	}


	public Boolean getIsCheckOffNotChangeSalAcc() {
		return isCheckOffNotChangeSalAcc;
	}


	public void setIsCheckOffNotChangeSalAcc(Boolean isCheckOffNotChangeSalAcc) {
		this.isCheckOffNotChangeSalAcc = isCheckOffNotChangeSalAcc;
	}

	public LoanApplicationMaster getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(LoanApplicationMaster applicationId) {
		this.applicationId = applicationId;
	}

	public Long getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(Long manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Long getAssetModelId() {
		return assetModelId;
	}

	public void setAssetModelId(Long assetModelId) {
		this.assetModelId = assetModelId;
	}

	public Integer getAssetMake() {
		return assetMake;
	}

	public void setAssetMake(Integer assetMake) {
		this.assetMake = assetMake;
	}

	public Long getSupplierStateId() {
		return supplierStateId;
	}

	public void setSupplierStateId(Long supplierStateId) {
		this.supplierStateId = supplierStateId;
	}

	public Long getSupplierCityId() {
		return supplierCityId;
	}

	public void setSupplierCityId(Long supplierCityId) {
		this.supplierCityId = supplierCityId;
	}

	public Long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Long supplierId) {
		this.supplierId = supplierId;
	}

	public String getOtherSuplierName() {
		return otherSuplierName;
	}

	public void setOtherSuplierName(String otherSuplierName) {
		this.otherSuplierName = otherSuplierName;
	}

	public String getOtherAssetModelName() {
		return otherAssetModelName;
	}

	public void setOtherAssetModelName(String otherAssetModelName) {
		this.otherAssetModelName = otherAssetModelName;
	}

	public String getOtherManufacturerName() {
		return otherManufacturerName;
	}

	public void setOtherManufacturerName(String otherManufacturerName) {
		this.otherManufacturerName = otherManufacturerName;
	}
}