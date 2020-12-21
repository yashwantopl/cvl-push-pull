package com.opl.service.loans.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by pooja.patel on 24-11-2020.
 */
@Entity
@Table(name="vehicle_operator_detail")
public class VehicleOperatorDetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="application_id")
    private Long applicationId;

    @Column(name="is_currently_vehicle_operated")
    private Boolean isCurrentlyVehicleOperated;

    @Column(name="is_any_vehicle_loan")
    private Boolean isAnyVehicleLoan;

    @Column(name="is_any_on_hand_order")
    private Boolean isAnyOnHandOrder;

    @Column(name="is_family_member_involved")
    private Boolean isFamilyMemberInvolved;

    @Column(name="have_transport_permit")
    private Boolean haveTransportPermit;

    @Column(name="operator_of_proposed_vehicle")
    private Integer operatorOfProposedVehicle;

    @Column(name="dealer_name")
    private String dealerName;

    @Column(name="dealer_address")
    private String dealerAddress;

    @Column(name="pincode")
    private String pincode;

    @Column(name="city")
    private Long city;

    @Column(name="state")
    private Long state;

    @Column(name="vehicle_capacity")
    private Integer vehicleCapacity;

    @Column(name="no_of_person")
    private Integer noOfPerson;

    @Column(name="expected_monthly_earning")
    private Double expectedMonthlyEarning;

    @Column(name="vehicle_operate_in")
    private String vehicleOperateIn;
    
    @Column(name="have_vehicle_licence")
    private Boolean haveVehicleLicence;

    @Column(name="agreed_purchase_price")
    private Double agreedPurchasePrice;

    @Column(name="other_cost")
    private Double otherCost;

    @Column(name="total_cost_of_proposed_vehicle")
    private Double totalCostOfProposedVehicle;

    @Column(name="created_date")
    private Date createdDate;

    @Column(name="created_by")
    private Long createdBy;

    @Column(name="modified_date")
    private Date modifiedDate;

    @Column(name="modified_by")
    private Long modifiedBy;

    @Column(name="is_active")
    private Boolean isActive;
    
    @Column(name="load_in_kg")
    private Integer loadInKg;
    
    @Column(name="fully_built_cost")
    private Double fullyBuiltCost;
    
    @Column(name="chassis_cost")
    private Double chassisCost;
    
    @Column(name="body_building_cost")
    private Double bodyBuildingCost;
    
    

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

    public Boolean getCurrentlyVehicleOperated() {
        return isCurrentlyVehicleOperated;
    }

    public void setCurrentlyVehicleOperated(Boolean currentlyVehicleOperated) {
        isCurrentlyVehicleOperated = currentlyVehicleOperated;
    }

    public Boolean getAnyVehicleLoan() {
        return isAnyVehicleLoan;
    }

    public void setAnyVehicleLoan(Boolean anyVehicleLoan) {
        isAnyVehicleLoan = anyVehicleLoan;
    }

    public Boolean getAnyOnHandOrder() {
        return isAnyOnHandOrder;
    }

    public void setAnyOnHandOrder(Boolean anyOnHandOrder) {
        isAnyOnHandOrder = anyOnHandOrder;
    }

    public Boolean getFamilyMemberInvolved() {
        return isFamilyMemberInvolved;
    }

    public void setFamilyMemberInvolved(Boolean familyMemberInvolved) {
        isFamilyMemberInvolved = familyMemberInvolved;
    }

    public Boolean getHaveTransportPermit() {
        return haveTransportPermit;
    }

    public void setHaveTransportPermit(Boolean haveTransportPermit) {
        this.haveTransportPermit = haveTransportPermit;
    }

    public Integer getOperatorOfProposedVehicle() {
        return operatorOfProposedVehicle;
    }

    public void setOperatorOfProposedVehicle(Integer operatorOfProposedVehicle) {
        this.operatorOfProposedVehicle = operatorOfProposedVehicle;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public Long getState() {
        return state;
    }

    public void setState(Long state) {
        this.state = state;
    }

    public Integer getVehicleCapacity() {
        return vehicleCapacity;
    }

    public void setVehicleCapacity(Integer vehicleCapacity) {
        this.vehicleCapacity = vehicleCapacity;
    }

    public Integer getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(Integer noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public Double getExpectedMonthlyEarning() {
        return expectedMonthlyEarning;
    }

    public void setExpectedMonthlyEarning(Double expectedMonthlyEarning) {
        this.expectedMonthlyEarning = expectedMonthlyEarning;
    }

    public String getVehicleOperateIn() {
        return vehicleOperateIn;
    }

    public void setVehicleOperateIn(String vehicleOperateIn) {
        this.vehicleOperateIn = vehicleOperateIn;
    }

    public Double getAgreedPurchasePrice() {
        return agreedPurchasePrice;
    }

    public void setAgreedPurchasePrice(Double agreedPurchasePrice) {
        this.agreedPurchasePrice = agreedPurchasePrice;
    }

    public Double getOtherCost() {
        return otherCost;
    }

    public void setOtherCost(Double otherCost) {
        this.otherCost = otherCost;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Long getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Long modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

	public Boolean getIsCurrentlyVehicleOperated() {
		return isCurrentlyVehicleOperated;
	}

	public void setIsCurrentlyVehicleOperated(Boolean isCurrentlyVehicleOperated) {
		this.isCurrentlyVehicleOperated = isCurrentlyVehicleOperated;
	}

	public Boolean getIsAnyVehicleLoan() {
		return isAnyVehicleLoan;
	}

	public void setIsAnyVehicleLoan(Boolean isAnyVehicleLoan) {
		this.isAnyVehicleLoan = isAnyVehicleLoan;
	}

	public Boolean getIsAnyOnHandOrder() {
		return isAnyOnHandOrder;
	}

	public void setIsAnyOnHandOrder(Boolean isAnyOnHandOrder) {
		this.isAnyOnHandOrder = isAnyOnHandOrder;
	}

	public Boolean getIsFamilyMemberInvolved() {
		return isFamilyMemberInvolved;
	}

	public void setIsFamilyMemberInvolved(Boolean isFamilyMemberInvolved) {
		this.isFamilyMemberInvolved = isFamilyMemberInvolved;
	}

	public Boolean getHaveVehicleLicence() {
		return haveVehicleLicence;
	}

	public void setHaveVehicleLicence(Boolean haveVehicleLicence) {
		this.haveVehicleLicence = haveVehicleLicence;
	}

	public Integer getLoadInKg() {
		return loadInKg;
	}

	public void setLoadInKg(Integer loadInKg) {
		this.loadInKg = loadInKg;
	}

	public Double getFullyBuiltCost() {
		return fullyBuiltCost;
	}

	public void setFullyBuiltCost(Double fullyBuiltCost) {
		this.fullyBuiltCost = fullyBuiltCost;
	}

	public Double getChassisCost() {
		return chassisCost;
	}

	public void setChassisCost(Double chassisCost) {
		this.chassisCost = chassisCost;
	}

	public Double getBodyBuildingCost() {
		return bodyBuildingCost;
	}

	public void setBodyBuildingCost(Double bodyBuildingCost) {
		this.bodyBuildingCost = bodyBuildingCost;
	}

    public Double getTotalCostOfProposedVehicle() {
        return totalCostOfProposedVehicle;
    }

    public void setTotalCostOfProposedVehicle(Double totalCostOfProposedVehicle) {
        this.totalCostOfProposedVehicle = totalCostOfProposedVehicle;
    }
}
