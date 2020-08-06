package com.opl.mudra.api.itr.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ITRPDFScrapeRes implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private String appid;
	private String area_locality;
	private String country;
	private String dob;
	private String email;
	private String flat_door_block;
	private String grossTotalIncome;
	private String itrYear;
	private String itrType;
	private Boolean fullName;
	private String firstName;
	private String middleName;
	private String mob;
	private String lastName;
	private String pan;
	private String pin;
	private String premise_building_village;
	private String road_street;
	private String state;
	private String taxableTotalIncome;
	private String timestamp;
	private String town_city_district;
	private Boolean valid;
	private String aadhaar;
	
	//ITR-4
	private String advances;
	private String balWithBanks;
	private String cashInHand;
	private String fileExists;
	private String fixedAssets;
	private String grsReceipt;
	private String grsTrnOverAnyOthMode;
	private String grsTrnOverBank;
	private String incChargeableUnderBus;
	private String inventories;
	private String loansAndAdvances;
	private String otherAssets;
	private String othrCurrLiab;
	private String partnerMemberOwnCapital;
	private String securedLoans;
	private String sundryCreditors;
	private String sundryDebtors;
	private String unSecuredLoans;

	//ITR-4S
	private String cashBalAmt;
	private String grsTrnOverOrReceipt;
	private String totStkInTradAmt;
	private String totSundryCrdAmt;
	private String totSundryDbtAmt;
	
	private String houseProp;
	private String incomeBusAndProf;
	private String incomeOthrSource;
	private String salaries;
	private String totCapGains;
	private String incomeChargeableSalaries;
	private String totProfBusGain;
	
	private String url;
	
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getArea_locality() {
		return area_locality;
	}
	public void setArea_locality(String area_locality) {
		this.area_locality = area_locality;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getFlat_door_block() {
		return flat_door_block;
	}
	public void setFlat_door_block(String flat_door_block) {
		this.flat_door_block = flat_door_block;
	}
	public String getGrossTotalIncome() {
		return grossTotalIncome;
	}
	public void setGrossTotalIncome(String grossTotalIncome) {
		this.grossTotalIncome = grossTotalIncome;
	}
	public String getItrYear() {
		return itrYear;
	}
	public void setItrYear(String itrYear) {
		this.itrYear = itrYear;
	}
	
	public String getItrType() {
		return itrType;
	}
	public void setItrType(String itrType) {
		this.itrType = itrType;
	}
	public Boolean getFullName() {
		return fullName;
	}
	public void setFullName(Boolean fullName) {
		this.fullName = fullName;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getPremise_building_village() {
		return premise_building_village;
	}
	public void setPremise_building_village(String premise_building_village) {
		this.premise_building_village = premise_building_village;
	}
	public String getRoad_street() {
		return road_street;
	}
	public void setRoad_street(String road_street) {
		this.road_street = road_street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTaxableTotalIncome() {
		return taxableTotalIncome;
	}
	public void setTaxableTotalIncome(String taxableTotalIncome) {
		this.taxableTotalIncome = taxableTotalIncome;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getTown_city_district() {
		return town_city_district;
	}
	public void setTown_city_district(String town_city_district) {
		this.town_city_district = town_city_district;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Boolean getValid() {
		return valid;
	}
	public void setValid(Boolean valid) {
		this.valid = valid;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMob() {
		return mob;
	}
	public void setMob(String mob) {
		this.mob = mob;
	}
	public String getAdvances() {
		return advances;
	}
	public void setAdvances(String advances) {
		this.advances = advances;
	}
	public String getBalWithBanks() {
		return balWithBanks;
	}
	public void setBalWithBanks(String balWithBanks) {
		this.balWithBanks = balWithBanks;
	}
	public String getCashInHand() {
		return cashInHand;
	}
	public void setCashInHand(String cashInHand) {
		this.cashInHand = cashInHand;
	}
	public String getFileExists() {
		return fileExists;
	}
	public void setFileExists(String fileExists) {
		this.fileExists = fileExists;
	}
	public String getFixedAssets() {
		return fixedAssets;
	}
	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
	public String getGrsReceipt() {
		return grsReceipt;
	}
	public void setGrsReceipt(String grsReceipt) {
		this.grsReceipt = grsReceipt;
	}
	public String getGrsTrnOverAnyOthMode() {
		return grsTrnOverAnyOthMode;
	}
	public void setGrsTrnOverAnyOthMode(String grsTrnOverAnyOthMode) {
		this.grsTrnOverAnyOthMode = grsTrnOverAnyOthMode;
	}
	public String getGrsTrnOverBank() {
		return grsTrnOverBank;
	}
	public void setGrsTrnOverBank(String grsTrnOverBank) {
		this.grsTrnOverBank = grsTrnOverBank;
	}
	public String getIncChargeableUnderBus() {
		return incChargeableUnderBus;
	}
	public void setIncChargeableUnderBus(String incChargeableUnderBus) {
		this.incChargeableUnderBus = incChargeableUnderBus;
	}
	public String getInventories() {
		return inventories;
	}
	public void setInventories(String inventories) {
		this.inventories = inventories;
	}
	public String getLoansAndAdvances() {
		return loansAndAdvances;
	}
	public void setLoansAndAdvances(String loansAndAdvances) {
		this.loansAndAdvances = loansAndAdvances;
	}
	public String getOtherAssets() {
		return otherAssets;
	}
	public void setOtherAssets(String otherAssets) {
		this.otherAssets = otherAssets;
	}
	public String getOthrCurrLiab() {
		return othrCurrLiab;
	}
	public void setOthrCurrLiab(String othrCurrLiab) {
		this.othrCurrLiab = othrCurrLiab;
	}
	public String getPartnerMemberOwnCapital() {
		return partnerMemberOwnCapital;
	}
	public void setPartnerMemberOwnCapital(String partnerMemberOwnCapital) {
		this.partnerMemberOwnCapital = partnerMemberOwnCapital;
	}
	public String getSecuredLoans() {
		return securedLoans;
	}
	public void setSecuredLoans(String securedLoans) {
		this.securedLoans = securedLoans;
	}
	public String getSundryCreditors() {
		return sundryCreditors;
	}
	public void setSundryCreditors(String sundryCreditors) {
		this.sundryCreditors = sundryCreditors;
	}
	public String getSundryDebtors() {
		return sundryDebtors;
	}
	public void setSundryDebtors(String sundryDebtors) {
		this.sundryDebtors = sundryDebtors;
	}
	public String getUnSecuredLoans() {
		return unSecuredLoans;
	}
	public void setUnSecuredLoans(String unSecuredLoans) {
		this.unSecuredLoans = unSecuredLoans;
	}
	
	
	
	public String getCashBalAmt() {
		return cashBalAmt;
	}
	public void setCashBalAmt(String cashBalAmt) {
		this.cashBalAmt = cashBalAmt;
	}
	public String getGrsTrnOverOrReceipt() {
		return grsTrnOverOrReceipt;
	}
	public void setGrsTrnOverOrReceipt(String grsTrnOverOrReceipt) {
		this.grsTrnOverOrReceipt = grsTrnOverOrReceipt;
	}
	public String getTotStkInTradAmt() {
		return totStkInTradAmt;
	}
	public void setTotStkInTradAmt(String totStkInTradAmt) {
		this.totStkInTradAmt = totStkInTradAmt;
	}
	public String getTotSundryCrdAmt() {
		return totSundryCrdAmt;
	}
	public void setTotSundryCrdAmt(String totSundryCrdAmt) {
		this.totSundryCrdAmt = totSundryCrdAmt;
	}
	public String getTotSundryDbtAmt() {
		return totSundryDbtAmt;
	}
	public void setTotSundryDbtAmt(String totSundryDbtAmt) {
		this.totSundryDbtAmt = totSundryDbtAmt;
	}
	
	public String getHouseProp() {
		return houseProp;
	}
	public void setHouseProp(String houseProp) {
		this.houseProp = houseProp;
	}
	public String getIncomeBusAndProf() {
		return incomeBusAndProf;
	}
	public void setIncomeBusAndProf(String incomeBusAndProf) {
		this.incomeBusAndProf = incomeBusAndProf;
	}
	public String getIncomeOthrSource() {
		return incomeOthrSource;
	}
	public void setIncomeOthrSource(String incomeOthrSource) {
		this.incomeOthrSource = incomeOthrSource;
	}
	public String getSalaries() {
		return salaries;
	}
	public void setSalaries(String salaries) {
		this.salaries = salaries;
	}
	
	public String getTotCapGains() {
		return totCapGains;
	}
	public void setTotCapGains(String totCapGains) {
		this.totCapGains = totCapGains;
	}
	
	public String getIncomeChargeableSalaries() {
		return incomeChargeableSalaries;
	}
	public void setIncomeChargeableSalaries(String incomeChargeableSalaries) {
		this.incomeChargeableSalaries = incomeChargeableSalaries;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getTotProfBusGain() {
		return totProfBusGain;
	}
	public void setTotProfBusGain(String totProfBusGain) {
		this.totProfBusGain = totProfBusGain;
	}
	
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	@Override
	public String toString() {
		return "ITRPDFScrapeRes [appid=" + appid + ", area_locality=" + area_locality + ", country=" + country
				+ ", dob=" + dob + ", email=" + email + ", flat_door_block=" + flat_door_block + ", grossTotalIncome="
				+ grossTotalIncome + ", itrYear=" + itrYear + ", itrType=" + itrType + ", fullName=" + fullName
				+ ", firstName=" + firstName + ", middleName=" + middleName + ", mob=" + mob + ", lastName=" + lastName
				+ ", pan=" + pan + ", pin=" + pin + ", premise_building_village=" + premise_building_village
				+ ", road_street=" + road_street + ", state=" + state + ", taxableTotalIncome=" + taxableTotalIncome
				+ ", timestamp=" + timestamp + ", town_city_district=" + town_city_district + ", valid=" + valid
				+ ", advances=" + advances + ", balWithBanks=" + balWithBanks + ", cashInHand=" + cashInHand
				+ ", fileExists=" + fileExists + ", fixedAssets=" + fixedAssets + ", grsReceipt=" + grsReceipt
				+ ", grsTrnOverAnyOthMode=" + grsTrnOverAnyOthMode + ", grsTrnOverBank=" + grsTrnOverBank
				+ ", incChargeableUnderBus=" + incChargeableUnderBus + ", inventories=" + inventories
				+ ", loansAndAdvances=" + loansAndAdvances + ", otherAssets=" + otherAssets + ", othrCurrLiab="
				+ othrCurrLiab + ", partnerMemberOwnCapital=" + partnerMemberOwnCapital + ", securedLoans="
				+ securedLoans + ", sundryCreditors=" + sundryCreditors + ", sundryDebtors=" + sundryDebtors
				+ ", unSecuredLoans=" + unSecuredLoans + ", cashBalAmt=" + cashBalAmt + ", grsTrnOverOrReceipt="
				+ grsTrnOverOrReceipt + ", totStkInTradAmt=" + totStkInTradAmt + ", totSundryCrdAmt=" + totSundryCrdAmt
				+ ", totSundryDbtAmt=" + totSundryDbtAmt + ", houseProp=" + houseProp + ", incomeBusAndProf="
				+ incomeBusAndProf + ", incomeOthrSource=" + incomeOthrSource + ", salaries=" + salaries + ", totCapGains=" + totCapGains + "]";
	}
	public String toJson() {
		return "{ 'appid':" + appid + ", 'area_locality':" + area_locality + ", 'country':" + country + ", 'dob':" + dob + ", 'flat_door_block':" + flat_door_block 
				+ ", 'grossTotalIncome':" + grossTotalIncome + ", 'itrYear':" + itrYear + ", 'itrType':" + itrType + ", 'fullName':" + fullName + ", 'pan':" 
				+ pan + ", 'pin':" + pin + ", 'premise_building_village':" + premise_building_village + ", 'road_street':" + road_street + ", 'state':" + state 
				+ ", 'taxableTotalIncome':" + taxableTotalIncome + ", 'timestamp':" + timestamp + ", 'town_city_district':" + town_city_district + ", 'firstName':" + firstName 
				+ ", 'middleName':" + middleName + ", 'lastName':" + lastName + ", 'valid':" + valid + ", 'email':" + email + ", 'mob':" + mob + ", 'advances':" 
				+ advances + ", 'balWithBanks':" + balWithBanks + ", 'cashInHand':" + cashInHand + ", 'fileExists':" + fileExists + ", 'fixedAssets':" + fixedAssets 
				+ ", 'grsReceipt':" + grsReceipt + ", 'grsTrnOverAnyOthMode':" + grsTrnOverAnyOthMode + ", 'grsTrnOverBank':" + grsTrnOverBank + ", 'incChargeableUnderBus':" 
				+ incChargeableUnderBus + ", 'inventories':" + inventories + ", 'loansAndAdvances':" + loansAndAdvances + ", 'otherAssets':" + otherAssets + ", 'othrCurrLiab':"
				+ othrCurrLiab + ", 'partnerMemberOwnCapital':" + partnerMemberOwnCapital + ", 'securedLoans':" + securedLoans + ", 'sundryCreditors':" + sundryCreditors 
				+ ", 'sundryDebtors':" + sundryDebtors + ", 'unSecuredLoans':" + unSecuredLoans + ", 'cashBalAmt':" + cashBalAmt 
				+ ", 'grsTrnOverOrReceipt':" + grsTrnOverOrReceipt + ", 'totStkInTradAmt':" + totStkInTradAmt + ", 'totSundryCrdAmt':" + totSundryCrdAmt 
				+ ", 'totSundryDbtAmt':" + totSundryDbtAmt + ", 'houseProp':" + houseProp + ", 'incomeBusAndProf':" + incomeBusAndProf +
				", 'incomeOthrSource':" + incomeOthrSource + ", 'salaries':" + salaries + ", 'totCapGains':" + totCapGains + "}";

	}
	

	
}
