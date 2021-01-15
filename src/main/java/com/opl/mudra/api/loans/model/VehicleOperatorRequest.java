package com.opl.mudra.api.loans.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by pooja.patel on 24-11-2020.
 */
public class VehicleOperatorRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long applicationId;
    private Boolean isCurrentlyVehicleOperated;
    private Boolean isAnyVehicleLoan;
    private Boolean isAnyOnHandOrder;
    private Boolean isFamilyMemberInvolved;
    private Boolean haveTransportPermit;
    private Integer operatorOfProposedVehicle;
    private String dealerName;
    private String dealerAddress;
    private String pincode;
    private Long city;
	private String cityName;
    private Long state;
	private String stateName;
    private Integer vehicleCapacity;
    private String vehicleCapacityStr;
    private Integer noOfPerson;
    private Integer loadInKg;
    private Double expectedMonthlyEarning;
    private List<String> vehicleOperateIn;
    private Boolean haveVehicleLicence;
    private Double agreedPurchasePrice;
    private Double otherCost;
    private Double fullyBuiltCost;
    private Double chassisCost;
    private Double bodyBuildingCost;
    private Double totalCostOfProposedVehicle;
    private String vehicleCarryType;
    private List<CurrentOperatedVehicleRequest> currentOperatedVehicleDetails;
    private List<PastVehicleLoanRequest> pastVehicleLoanDetails;
    private List<OperatorRequest> operatorDetails;
    private List<ProposedVehicleRequest> proposedVehicleDetails;
    
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
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
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
	public Integer getLoadInKg() {
		return loadInKg;
	}
	public void setLoadInKg(Integer loadInKg) {
		this.loadInKg = loadInKg;
	}
	public Double getExpectedMonthlyEarning() {
		return expectedMonthlyEarning;
	}
	public void setExpectedMonthlyEarning(Double expectedMonthlyEarning) {
		this.expectedMonthlyEarning = expectedMonthlyEarning;
	}
	public List<String> getVehicleOperateIn() {
		return vehicleOperateIn;
	}
	public void setVehicleOperateIn(List<String> vehicleOperateIn) {
		this.vehicleOperateIn = vehicleOperateIn;
	}
	public Boolean getHaveVehicleLicence() {
		return haveVehicleLicence;
	}
	public void setHaveVehicleLicence(Boolean haveVehicleLicence) {
		this.haveVehicleLicence = haveVehicleLicence;
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
	public List<CurrentOperatedVehicleRequest> getCurrentOperatedVehicleDetails() {
		return currentOperatedVehicleDetails;
	}
	public void setCurrentOperatedVehicleDetails(List<CurrentOperatedVehicleRequest> currentOperatedVehicleDetails) {
		this.currentOperatedVehicleDetails = currentOperatedVehicleDetails;
	}
	public List<PastVehicleLoanRequest> getPastVehicleLoanDetails() {
		return pastVehicleLoanDetails;
	}
	public void setPastVehicleLoanDetails(List<PastVehicleLoanRequest> pastVehicleLoanDetails) {
		this.pastVehicleLoanDetails = pastVehicleLoanDetails;
	}
	public List<OperatorRequest> getOperatorDetails() {
		return operatorDetails;
	}
	public void setOperatorDetails(List<OperatorRequest> operatorDetails) {
		this.operatorDetails = operatorDetails;
	}
	public List<ProposedVehicleRequest> getProposedVehicleDetails() {
		return proposedVehicleDetails;
	}
	public void setProposedVehicleDetails(List<ProposedVehicleRequest> proposedVehicleDetails) {
		this.proposedVehicleDetails = proposedVehicleDetails;
	}

	public Double getTotalCostOfProposedVehicle() {
		return totalCostOfProposedVehicle;
	}

	public void setTotalCostOfProposedVehicle(Double totalCostOfProposedVehicle) {
		this.totalCostOfProposedVehicle = totalCostOfProposedVehicle;
	}

	public String getVehicleCapacityStr() {
		return vehicleCapacityStr;
	}
	public void setVehicleCapacityStr(String vehicleCapacityStr) {
		this.vehicleCapacityStr = vehicleCapacityStr;
	}
    
  public String getVehicleCarryType() {
		return vehicleCarryType;
	}
	public void setVehicleCarryType(String vehicleCarryType) {
		this.vehicleCarryType = vehicleCarryType;
	}

    
}
