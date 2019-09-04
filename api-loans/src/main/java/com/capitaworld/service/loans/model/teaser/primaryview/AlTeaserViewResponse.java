package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.loans.model.AssociatedConcernDetailRequest;
import com.capitaworld.service.loans.model.ExistingProductDetailRequest;
import com.capitaworld.service.loans.model.FinanceMeansDetailResponse;
import com.capitaworld.service.loans.model.FinancialArrangementsDetailResponse;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.SecurityCorporateDetailRequest;
import com.capitaworld.service.loans.model.TotalCostOfProjectResponse;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.retail.ALOneformPrimaryRes;

/**
 * 
 * @author rohit.chaudhary
 *
 */
public class AlTeaserViewResponse {

	private List<?> matchesList;

	
	private String organisationName;
	private String fpProductName;
	private String scoringModelName;
	private String panNo;
	private String city;
	private String regOfficeCity;
	private String addOfficeCity;
	private String country;
	private String state;
	private String addOfficestate;
	private String constitution;
	private String establishmentMonth;
	private String establishmentYear;
	private String keyVericalFunding;
	private String keyVericalSector;
	private String keyVericalSubsector;
	private String currencyDenomination;
	private String LoanType;
	private Object dateOfProposal;
	private Long loanAmount;
	private Integer productId;
	private String haveCollateralSecurity;
	private String collateralSecurityAmount;
	private List<Object> profilePic;
	private String purposeOfLoan;
	private String businessAssetAmount;
	private List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList;
	private Object FinancialInputRequest;
	private List<Object> bankStatement;
	private List<Object> cibilReport;
	private List<Object> irtPdfReport;
	private List<Object> irtXMLReport;
	private List<Object> cibilConsumerReport;
	private List<Map<String, Object>> directorBackGroundDetails;
	private List<CorporateDirectorIncomeRequest> directorIncomeDetails;
	private Object eligibilityDataObject;
	private Object dataList;
	private Object bankData;
	private Long assesmentId;
	private String qualification;
	private Object otherDetails;
	private Integer businessTypeId;
	private Object dataObject;
	private Object scoringResponseList;
	private Long appId;
	private Object cibilScore;
	private Object nameOfEntity;
	private String pan;
	private String religion;
	private String residentialStatus;
	private String castCategory;
	private String diasablityType;
	private String ddoOrganizationType;
	private Object finalDetails;
	private Object bankAccountDetails;
	private Object fixDepositDetails;
	private Object referenceDetails;
	private Object obligationDetails;
	private Object otherCurruntAssetDetail;
	private String tenure;
	private Object nameAsPerItr;
	private List<ExistingProductDetailRequest> existingProductDetailRequestList;
	private List<TotalCostOfProjectResponse> totalCostOfProjectResponseList;
	private List<FinanceMeansDetailResponse> financeMeansDetailResponseList;
	private List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList;
	private List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList;
	private List<AssociatedConcernDetailRequest> associatedConcernDetailRequests;
	private List<Object> itr;
	private Object retailApplicantDetail;
	private Object retailApplicantIncomeDetails;
	private Object retailCoApplicantDetail;
	private Object retailCoApplicantIncomeDetails;
	private Long userId;
	private Object presentAdd;
	private Object presentAddPremise;
	private Object presentAddStreetName;
	private Object presentAddLandmark;
	private Object presentAddCountry;
	private Object presentAddState;
	private Object presentAddCity;
	private Object presentAddPincode;
	private Object presentAddPinData;
	private String presentAddDist;
	private String presentAddTaluko;
	private Object permAdd;
	private Object permAddPremise;
	private Object permAddStreetName;
	private Object permAddLandmark;
	private Object permAddCountry;
	private Object permAddState;
	private Object permAddCity;
	private Object permAddPincode;
	private Object permAddPinData;
	private String permAddDist;
	private String permAddTaluko;
	private Object corrAdd;
	private Object corrAddPremise;
	private Object corrAddStreetName;
	private Object corrAddLandmark;
	private Object corrAddCountry;
	private Object corrAddState;
	private Object corrAddCity;
	private Object corrAddPincode;
	private Object corrAddPinData;
	private String corrAddDist;
	private String corrAddTaluko;
	private Object offAdd;
	private Object offAddPremise;
	private Object offAddStreetName;
	private Object offAddLandmark;
	private Object offAddCountry;
	private Object offAddState;
	private Object offAddCity;
	private Object offAddPincode;
	private Object offPinData;
	private String offAddDist;
	private String offAddTaluko;
	private Object proposalData;
	private String propCountry;
	private String propState;
	private String propCity;
	private Long propPincode;
	private Double landPlotCost;
	private Double constructionCost;
	private Integer completionTimeInYear;
	private String renovationType;
	private Double renovationCost;
	private Integer renovationCompletionTimeInYear;
	private Date dateOfLoanTaken;
	private Double originalValProp;
	private String sellerName;
	private String sellerCity;
	private String sellerState;
	private String sellerAddress;
	private Integer sellerPincode;
	private String propertyName;
	private Integer buildUpArea;
	private Integer superBuildUpArea;
	private Integer carpetArea;
	private Integer totalPriceOfProperty;

	private String fatherName;
	private String motherName;
	private String educationStatus;
	private Date qualifyingYear;
	private String nameOfSpouse;
	private Integer noOfChildren;
	private String birthPlace;

	private Long RefNo;
	private String RefName;
	private String RefAddress;
	private String RefEmail;
	private String RefTel;
	private String RefMobile;

	private Object epfData;
	private String detailedLoanPur;
	private Boolean isNameEdited;
	private String nameEditedByUser;

	private String mclrRoi;
	private String spreadRoi;
	private String effectiveRoi;
	private String concessionRoi;
	private String concessionRoiBased;
	private String finalRoi;
	private String noteOfBorrower;
	private Long borrowerContribution; 
	private ALOneformPrimaryRes autoDetails;
	
	// Auto details 
	private String vehicleType; 
	private String vehicleCategory; 
	private String vehicleSegment; 
	private Integer vehicleAge; 
	private String vehicleEngineVolume; 
	private String vehicleUse; 
	private Long vehicleExShowRoomPrice;
	private Long vehicleOnRoadPrice; 
	private Long vehicleAgreedPurchasePrice;
	private Boolean isVehicleHypothecation;
	
	public AlTeaserViewResponse() {
		// TODO Auto-generated constructor stub
	}

	public List<?> getMatchesList() {
		return matchesList;
	}

	public void setMatchesList(List<?> matchesList) {
		this.matchesList = matchesList;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getFpProductName() {
		return fpProductName;
	}

	public void setFpProductName(String fpProductName) {
		this.fpProductName = fpProductName;
	}

	public String getScoringModelName() {
		return scoringModelName;
	}

	public void setScoringModelName(String scoringModelName) {
		this.scoringModelName = scoringModelName;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegOfficeCity() {
		return regOfficeCity;
	}

	public void setRegOfficeCity(String regOfficeCity) {
		this.regOfficeCity = regOfficeCity;
	}

	public String getAddOfficeCity() {
		return addOfficeCity;
	}

	public void setAddOfficeCity(String addOfficeCity) {
		this.addOfficeCity = addOfficeCity;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAddOfficestate() {
		return addOfficestate;
	}

	public void setAddOfficestate(String addOfficestate) {
		this.addOfficestate = addOfficestate;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getEstablishmentMonth() {
		return establishmentMonth;
	}

	public void setEstablishmentMonth(String establishmentMonth) {
		this.establishmentMonth = establishmentMonth;
	}

	public String getEstablishmentYear() {
		return establishmentYear;
	}

	public void setEstablishmentYear(String establishmentYear) {
		this.establishmentYear = establishmentYear;
	}

	public String getKeyVericalFunding() {
		return keyVericalFunding;
	}

	public void setKeyVericalFunding(String keyVericalFunding) {
		this.keyVericalFunding = keyVericalFunding;
	}

	public String getKeyVericalSector() {
		return keyVericalSector;
	}

	public void setKeyVericalSector(String keyVericalSector) {
		this.keyVericalSector = keyVericalSector;
	}

	public String getKeyVericalSubsector() {
		return keyVericalSubsector;
	}

	public void setKeyVericalSubsector(String keyVericalSubsector) {
		this.keyVericalSubsector = keyVericalSubsector;
	}

	public String getCurrencyDenomination() {
		return currencyDenomination;
	}

	public void setCurrencyDenomination(String currencyDenomination) {
		this.currencyDenomination = currencyDenomination;
	}

	public String getLoanType() {
		return LoanType;
	}

	public void setLoanType(String loanType) {
		LoanType = loanType;
	}

	public Object getDateOfProposal() {
		return dateOfProposal;
	}

	public void setDateOfProposal(Object dateOfProposal) {
		this.dateOfProposal = dateOfProposal;
	}

	public Long getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(Long loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getHaveCollateralSecurity() {
		return haveCollateralSecurity;
	}

	public void setHaveCollateralSecurity(String haveCollateralSecurity) {
		this.haveCollateralSecurity = haveCollateralSecurity;
	}

	public String getCollateralSecurityAmount() {
		return collateralSecurityAmount;
	}

	public void setCollateralSecurityAmount(String collateralSecurityAmount) {
		this.collateralSecurityAmount = collateralSecurityAmount;
	}

	public List<Object> getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(List<Object> profilePic) {
		this.profilePic = profilePic;
	}

	public String getPurposeOfLoan() {
		return purposeOfLoan;
	}

	public void setPurposeOfLoan(String purposeOfLoan) {
		this.purposeOfLoan = purposeOfLoan;
	}

	public String getBusinessAssetAmount() {
		return businessAssetAmount;
	}

	public void setBusinessAssetAmount(String businessAssetAmount) {
		this.businessAssetAmount = businessAssetAmount;
	}

	public List<FinancialArrangementsDetailResponse> getFinancialArrangementsDetailResponseList() {
		return financialArrangementsDetailResponseList;
	}

	public void setFinancialArrangementsDetailResponseList(
			List<FinancialArrangementsDetailResponse> financialArrangementsDetailResponseList) {
		this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
	}

	public Object getFinancialInputRequest() {
		return FinancialInputRequest;
	}

	public void setFinancialInputRequest(Object financialInputRequest) {
		FinancialInputRequest = financialInputRequest;
	}

	public List<Object> getBankStatement() {
		return bankStatement;
	}

	public void setBankStatement(List<Object> bankStatement) {
		this.bankStatement = bankStatement;
	}

	public List<Object> getCibilReport() {
		return cibilReport;
	}

	public void setCibilReport(List<Object> cibilReport) {
		this.cibilReport = cibilReport;
	}

	public List<Object> getIrtPdfReport() {
		return irtPdfReport;
	}

	public void setIrtPdfReport(List<Object> irtPdfReport) {
		this.irtPdfReport = irtPdfReport;
	}

	public List<Object> getIrtXMLReport() {
		return irtXMLReport;
	}

	public void setIrtXMLReport(List<Object> irtXMLReport) {
		this.irtXMLReport = irtXMLReport;
	}

	public List<Object> getCibilConsumerReport() {
		return cibilConsumerReport;
	}

	public void setCibilConsumerReport(List<Object> cibilConsumerReport) {
		this.cibilConsumerReport = cibilConsumerReport;
	}

	public List<Map<String, Object>> getDirectorBackGroundDetails() {
		return directorBackGroundDetails;
	}

	public void setDirectorBackGroundDetails(List<Map<String, Object>> directorBackGroundDetails) {
		this.directorBackGroundDetails = directorBackGroundDetails;
	}

	public List<CorporateDirectorIncomeRequest> getDirectorIncomeDetails() {
		return directorIncomeDetails;
	}

	public void setDirectorIncomeDetails(List<CorporateDirectorIncomeRequest> directorIncomeDetails) {
		this.directorIncomeDetails = directorIncomeDetails;
	}

	public Object getEligibilityDataObject() {
		return eligibilityDataObject;
	}

	public void setEligibilityDataObject(Object eligibilityDataObject) {
		this.eligibilityDataObject = eligibilityDataObject;
	}

	public Object getDataList() {
		return dataList;
	}

	public void setDataList(Object dataList) {
		this.dataList = dataList;
	}

	public Object getBankData() {
		return bankData;
	}

	public void setBankData(Object bankData) {
		this.bankData = bankData;
	}

	public Long getAssesmentId() {
		return assesmentId;
	}

	public void setAssesmentId(Long assesmentId) {
		this.assesmentId = assesmentId;
	}

	public String getQualification() {
		return qualification;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public Object getOtherDetails() {
		return otherDetails;
	}

	public void setOtherDetails(Object otherDetails) {
		this.otherDetails = otherDetails;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}

	public Object getScoringResponseList() {
		return scoringResponseList;
	}

	public void setScoringResponseList(Object scoringResponseList) {
		this.scoringResponseList = scoringResponseList;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}

	public Object getCibilScore() {
		return cibilScore;
	}

	public void setCibilScore(Object cibilScore) {
		this.cibilScore = cibilScore;
	}

	public Object getNameOfEntity() {
		return nameOfEntity;
	}

	public void setNameOfEntity(Object nameOfEntity) {
		this.nameOfEntity = nameOfEntity;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getResidentialStatus() {
		return residentialStatus;
	}

	public void setResidentialStatus(String residentialStatus) {
		this.residentialStatus = residentialStatus;
	}

	public String getCastCategory() {
		return castCategory;
	}

	public void setCastCategory(String castCategory) {
		this.castCategory = castCategory;
	}

	public String getDiasablityType() {
		return diasablityType;
	}

	public void setDiasablityType(String diasablityType) {
		this.diasablityType = diasablityType;
	}

	public String getDdoOrganizationType() {
		return ddoOrganizationType;
	}

	public void setDdoOrganizationType(String ddoOrganizationType) {
		this.ddoOrganizationType = ddoOrganizationType;
	}

	public Object getFinalDetails() {
		return finalDetails;
	}

	public void setFinalDetails(Object finalDetails) {
		this.finalDetails = finalDetails;
	}

	public Object getBankAccountDetails() {
		return bankAccountDetails;
	}

	public void setBankAccountDetails(Object bankAccountDetails) {
		this.bankAccountDetails = bankAccountDetails;
	}

	public Object getFixDepositDetails() {
		return fixDepositDetails;
	}

	public void setFixDepositDetails(Object fixDepositDetails) {
		this.fixDepositDetails = fixDepositDetails;
	}

	public Object getReferenceDetails() {
		return referenceDetails;
	}

	public void setReferenceDetails(Object referenceDetails) {
		this.referenceDetails = referenceDetails;
	}

	public Object getObligationDetails() {
		return obligationDetails;
	}

	public void setObligationDetails(Object obligationDetails) {
		this.obligationDetails = obligationDetails;
	}

	public Object getOtherCurruntAssetDetail() {
		return otherCurruntAssetDetail;
	}

	public void setOtherCurruntAssetDetail(Object otherCurruntAssetDetail) {
		this.otherCurruntAssetDetail = otherCurruntAssetDetail;
	}

	public String getTenure() {
		return tenure;
	}

	public void setTenure(String tenure) {
		this.tenure = tenure;
	}

	public Object getNameAsPerItr() {
		return nameAsPerItr;
	}

	public void setNameAsPerItr(Object nameAsPerItr) {
		this.nameAsPerItr = nameAsPerItr;
	}

	public List<ExistingProductDetailRequest> getExistingProductDetailRequestList() {
		return existingProductDetailRequestList;
	}

	public void setExistingProductDetailRequestList(
			List<ExistingProductDetailRequest> existingProductDetailRequestList) {
		this.existingProductDetailRequestList = existingProductDetailRequestList;
	}

	public List<TotalCostOfProjectResponse> getTotalCostOfProjectResponseList() {
		return totalCostOfProjectResponseList;
	}

	public void setTotalCostOfProjectResponseList(List<TotalCostOfProjectResponse> totalCostOfProjectResponseList) {
		this.totalCostOfProjectResponseList = totalCostOfProjectResponseList;
	}

	public List<FinanceMeansDetailResponse> getFinanceMeansDetailResponseList() {
		return financeMeansDetailResponseList;
	}

	public void setFinanceMeansDetailResponseList(List<FinanceMeansDetailResponse> financeMeansDetailResponseList) {
		this.financeMeansDetailResponseList = financeMeansDetailResponseList;
	}

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailRequestList() {
		return securityCorporateDetailRequestList;
	}

	public void setSecurityCorporateDetailRequestList(
			List<SecurityCorporateDetailRequest> securityCorporateDetailRequestList) {
		this.securityCorporateDetailRequestList = securityCorporateDetailRequestList;
	}

	public List<GuarantorsCorporateDetailRequest> getGuarantorsCorporateDetailRequestList() {
		return guarantorsCorporateDetailRequestList;
	}

	public void setGuarantorsCorporateDetailRequestList(
			List<GuarantorsCorporateDetailRequest> guarantorsCorporateDetailRequestList) {
		this.guarantorsCorporateDetailRequestList = guarantorsCorporateDetailRequestList;
	}

	public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailRequests() {
		return associatedConcernDetailRequests;
	}

	public void setAssociatedConcernDetailRequests(
			List<AssociatedConcernDetailRequest> associatedConcernDetailRequests) {
		this.associatedConcernDetailRequests = associatedConcernDetailRequests;
	}

	public List<Object> getItr() {
		return itr;
	}

	public void setItr(List<Object> itr) {
		this.itr = itr;
	}

	public Object getRetailApplicantDetail() {
		return retailApplicantDetail;
	}

	public void setRetailApplicantDetail(Object retailApplicantDetail) {
		this.retailApplicantDetail = retailApplicantDetail;
	}

	public Object getRetailApplicantIncomeDetails() {
		return retailApplicantIncomeDetails;
	}

	public void setRetailApplicantIncomeDetails(Object retailApplicantIncomeDetails) {
		this.retailApplicantIncomeDetails = retailApplicantIncomeDetails;
	}

	public Object getRetailCoApplicantDetail() {
		return retailCoApplicantDetail;
	}

	public void setRetailCoApplicantDetail(Object retailCoApplicantDetail) {
		this.retailCoApplicantDetail = retailCoApplicantDetail;
	}

	public Object getRetailCoApplicantIncomeDetails() {
		return retailCoApplicantIncomeDetails;
	}

	public void setRetailCoApplicantIncomeDetails(Object retailCoApplicantIncomeDetails) {
		this.retailCoApplicantIncomeDetails = retailCoApplicantIncomeDetails;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Object getPresentAdd() {
		return presentAdd;
	}

	public void setPresentAdd(Object presentAdd) {
		this.presentAdd = presentAdd;
	}

	public Object getPresentAddPremise() {
		return presentAddPremise;
	}

	public void setPresentAddPremise(Object presentAddPremise) {
		this.presentAddPremise = presentAddPremise;
	}

	public Object getPresentAddStreetName() {
		return presentAddStreetName;
	}

	public void setPresentAddStreetName(Object presentAddStreetName) {
		this.presentAddStreetName = presentAddStreetName;
	}

	public Object getPresentAddLandmark() {
		return presentAddLandmark;
	}

	public void setPresentAddLandmark(Object presentAddLandmark) {
		this.presentAddLandmark = presentAddLandmark;
	}

	public Object getPresentAddCountry() {
		return presentAddCountry;
	}

	public void setPresentAddCountry(Object presentAddCountry) {
		this.presentAddCountry = presentAddCountry;
	}

	public Object getPresentAddState() {
		return presentAddState;
	}

	public void setPresentAddState(Object presentAddState) {
		this.presentAddState = presentAddState;
	}

	public Object getPresentAddCity() {
		return presentAddCity;
	}

	public void setPresentAddCity(Object presentAddCity) {
		this.presentAddCity = presentAddCity;
	}

	public Object getPresentAddPincode() {
		return presentAddPincode;
	}

	public void setPresentAddPincode(Object presentAddPincode) {
		this.presentAddPincode = presentAddPincode;
	}

	public Object getPresentAddPinData() {
		return presentAddPinData;
	}

	public void setPresentAddPinData(Object presentAddPinData) {
		this.presentAddPinData = presentAddPinData;
	}

	public String getPresentAddDist() {
		return presentAddDist;
	}

	public void setPresentAddDist(String presentAddDist) {
		this.presentAddDist = presentAddDist;
	}

	public String getPresentAddTaluko() {
		return presentAddTaluko;
	}

	public void setPresentAddTaluko(String presentAddTaluko) {
		this.presentAddTaluko = presentAddTaluko;
	}

	public Object getPermAdd() {
		return permAdd;
	}

	public void setPermAdd(Object permAdd) {
		this.permAdd = permAdd;
	}

	public Object getPermAddPremise() {
		return permAddPremise;
	}

	public void setPermAddPremise(Object permAddPremise) {
		this.permAddPremise = permAddPremise;
	}

	public Object getPermAddStreetName() {
		return permAddStreetName;
	}

	public void setPermAddStreetName(Object permAddStreetName) {
		this.permAddStreetName = permAddStreetName;
	}

	public Object getPermAddLandmark() {
		return permAddLandmark;
	}

	public void setPermAddLandmark(Object permAddLandmark) {
		this.permAddLandmark = permAddLandmark;
	}

	public Object getPermAddCountry() {
		return permAddCountry;
	}

	public void setPermAddCountry(Object permAddCountry) {
		this.permAddCountry = permAddCountry;
	}

	public Object getPermAddState() {
		return permAddState;
	}

	public void setPermAddState(Object permAddState) {
		this.permAddState = permAddState;
	}

	public Object getPermAddCity() {
		return permAddCity;
	}

	public void setPermAddCity(Object permAddCity) {
		this.permAddCity = permAddCity;
	}

	public Object getPermAddPincode() {
		return permAddPincode;
	}

	public void setPermAddPincode(Object permAddPincode) {
		this.permAddPincode = permAddPincode;
	}

	public Object getPermAddPinData() {
		return permAddPinData;
	}

	public void setPermAddPinData(Object permAddPinData) {
		this.permAddPinData = permAddPinData;
	}

	public String getPermAddDist() {
		return permAddDist;
	}

	public void setPermAddDist(String permAddDist) {
		this.permAddDist = permAddDist;
	}

	public String getPermAddTaluko() {
		return permAddTaluko;
	}

	public void setPermAddTaluko(String permAddTaluko) {
		this.permAddTaluko = permAddTaluko;
	}

	public Object getCorrAdd() {
		return corrAdd;
	}

	public void setCorrAdd(Object corrAdd) {
		this.corrAdd = corrAdd;
	}

	public Object getCorrAddPremise() {
		return corrAddPremise;
	}

	public void setCorrAddPremise(Object corrAddPremise) {
		this.corrAddPremise = corrAddPremise;
	}

	public Object getCorrAddStreetName() {
		return corrAddStreetName;
	}

	public void setCorrAddStreetName(Object corrAddStreetName) {
		this.corrAddStreetName = corrAddStreetName;
	}

	public Object getCorrAddLandmark() {
		return corrAddLandmark;
	}

	public void setCorrAddLandmark(Object corrAddLandmark) {
		this.corrAddLandmark = corrAddLandmark;
	}

	public Object getCorrAddCountry() {
		return corrAddCountry;
	}

	public void setCorrAddCountry(Object corrAddCountry) {
		this.corrAddCountry = corrAddCountry;
	}

	public Object getCorrAddState() {
		return corrAddState;
	}

	public void setCorrAddState(Object corrAddState) {
		this.corrAddState = corrAddState;
	}

	public Object getCorrAddCity() {
		return corrAddCity;
	}

	public void setCorrAddCity(Object corrAddCity) {
		this.corrAddCity = corrAddCity;
	}

	public Object getCorrAddPincode() {
		return corrAddPincode;
	}

	public void setCorrAddPincode(Object corrAddPincode) {
		this.corrAddPincode = corrAddPincode;
	}

	public Object getCorrAddPinData() {
		return corrAddPinData;
	}

	public void setCorrAddPinData(Object corrAddPinData) {
		this.corrAddPinData = corrAddPinData;
	}

	public String getCorrAddDist() {
		return corrAddDist;
	}

	public void setCorrAddDist(String corrAddDist) {
		this.corrAddDist = corrAddDist;
	}

	public String getCorrAddTaluko() {
		return corrAddTaluko;
	}

	public void setCorrAddTaluko(String corrAddTaluko) {
		this.corrAddTaluko = corrAddTaluko;
	}

	public Object getOffAdd() {
		return offAdd;
	}

	public void setOffAdd(Object offAdd) {
		this.offAdd = offAdd;
	}

	public Object getOffAddPremise() {
		return offAddPremise;
	}

	public void setOffAddPremise(Object offAddPremise) {
		this.offAddPremise = offAddPremise;
	}

	public Object getOffAddStreetName() {
		return offAddStreetName;
	}

	public void setOffAddStreetName(Object offAddStreetName) {
		this.offAddStreetName = offAddStreetName;
	}

	public Object getOffAddLandmark() {
		return offAddLandmark;
	}

	public void setOffAddLandmark(Object offAddLandmark) {
		this.offAddLandmark = offAddLandmark;
	}

	public Object getOffAddCountry() {
		return offAddCountry;
	}

	public void setOffAddCountry(Object offAddCountry) {
		this.offAddCountry = offAddCountry;
	}

	public Object getOffAddState() {
		return offAddState;
	}

	public void setOffAddState(Object offAddState) {
		this.offAddState = offAddState;
	}

	public Object getOffAddCity() {
		return offAddCity;
	}

	public void setOffAddCity(Object offAddCity) {
		this.offAddCity = offAddCity;
	}

	public Object getOffAddPincode() {
		return offAddPincode;
	}

	public void setOffAddPincode(Object offAddPincode) {
		this.offAddPincode = offAddPincode;
	}

	public Object getOffPinData() {
		return offPinData;
	}

	public void setOffPinData(Object offPinData) {
		this.offPinData = offPinData;
	}

	public String getOffAddDist() {
		return offAddDist;
	}

	public void setOffAddDist(String offAddDist) {
		this.offAddDist = offAddDist;
	}

	public String getOffAddTaluko() {
		return offAddTaluko;
	}

	public void setOffAddTaluko(String offAddTaluko) {
		this.offAddTaluko = offAddTaluko;
	}

	public Object getProposalData() {
		return proposalData;
	}

	public void setProposalData(Object proposalData) {
		this.proposalData = proposalData;
	}

	public String getPropCountry() {
		return propCountry;
	}

	public void setPropCountry(String propCountry) {
		this.propCountry = propCountry;
	}

	public String getPropState() {
		return propState;
	}

	public void setPropState(String propState) {
		this.propState = propState;
	}

	public String getPropCity() {
		return propCity;
	}

	public void setPropCity(String propCity) {
		this.propCity = propCity;
	}

	public Long getPropPincode() {
		return propPincode;
	}

	public void setPropPincode(Long propPincode) {
		this.propPincode = propPincode;
	}

	public Double getLandPlotCost() {
		return landPlotCost;
	}

	public void setLandPlotCost(Double landPlotCost) {
		this.landPlotCost = landPlotCost;
	}

	public Double getConstructionCost() {
		return constructionCost;
	}

	public void setConstructionCost(Double constructionCost) {
		this.constructionCost = constructionCost;
	}

	public Integer getCompletionTimeInYear() {
		return completionTimeInYear;
	}

	public void setCompletionTimeInYear(Integer completionTimeInYear) {
		this.completionTimeInYear = completionTimeInYear;
	}

	public String getRenovationType() {
		return renovationType;
	}

	public void setRenovationType(String renovationType) {
		this.renovationType = renovationType;
	}

	public Double getRenovationCost() {
		return renovationCost;
	}

	public void setRenovationCost(Double renovationCost) {
		this.renovationCost = renovationCost;
	}

	public Integer getRenovationCompletionTimeInYear() {
		return renovationCompletionTimeInYear;
	}

	public void setRenovationCompletionTimeInYear(Integer renovationCompletionTimeInYear) {
		this.renovationCompletionTimeInYear = renovationCompletionTimeInYear;
	}

	public Date getDateOfLoanTaken() {
		return dateOfLoanTaken;
	}

	public void setDateOfLoanTaken(Date dateOfLoanTaken) {
		this.dateOfLoanTaken = dateOfLoanTaken;
	}

	public Double getOriginalValProp() {
		return originalValProp;
	}

	public void setOriginalValProp(Double originalValProp) {
		this.originalValProp = originalValProp;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getSellerCity() {
		return sellerCity;
	}

	public void setSellerCity(String sellerCity) {
		this.sellerCity = sellerCity;
	}

	public String getSellerState() {
		return sellerState;
	}

	public void setSellerState(String sellerState) {
		this.sellerState = sellerState;
	}

	public String getSellerAddress() {
		return sellerAddress;
	}

	public void setSellerAddress(String sellerAddress) {
		this.sellerAddress = sellerAddress;
	}

	public Integer getSellerPincode() {
		return sellerPincode;
	}

	public void setSellerPincode(Integer sellerPincode) {
		this.sellerPincode = sellerPincode;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public Integer getBuildUpArea() {
		return buildUpArea;
	}

	public void setBuildUpArea(Integer buildUpArea) {
		this.buildUpArea = buildUpArea;
	}

	public Integer getSuperBuildUpArea() {
		return superBuildUpArea;
	}

	public void setSuperBuildUpArea(Integer superBuildUpArea) {
		this.superBuildUpArea = superBuildUpArea;
	}

	public Integer getCarpetArea() {
		return carpetArea;
	}

	public void setCarpetArea(Integer carpetArea) {
		this.carpetArea = carpetArea;
	}

	public Integer getTotalPriceOfProperty() {
		return totalPriceOfProperty;
	}

	public void setTotalPriceOfProperty(Integer totalPriceOfProperty) {
		this.totalPriceOfProperty = totalPriceOfProperty;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getEducationStatus() {
		return educationStatus;
	}

	public void setEducationStatus(String educationStatus) {
		this.educationStatus = educationStatus;
	}

	public Date getQualifyingYear() {
		return qualifyingYear;
	}

	public void setQualifyingYear(Date qualifyingYear) {
		this.qualifyingYear = qualifyingYear;
	}

	public String getNameOfSpouse() {
		return nameOfSpouse;
	}

	public void setNameOfSpouse(String nameOfSpouse) {
		this.nameOfSpouse = nameOfSpouse;
	}

	public Integer getNoOfChildren() {
		return noOfChildren;
	}

	public void setNoOfChildren(Integer noOfChildren) {
		this.noOfChildren = noOfChildren;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}

	public Long getRefNo() {
		return RefNo;
	}

	public void setRefNo(Long refNo) {
		RefNo = refNo;
	}

	public String getRefName() {
		return RefName;
	}

	public void setRefName(String refName) {
		RefName = refName;
	}

	public String getRefAddress() {
		return RefAddress;
	}

	public void setRefAddress(String refAddress) {
		RefAddress = refAddress;
	}

	public String getRefEmail() {
		return RefEmail;
	}

	public void setRefEmail(String refEmail) {
		RefEmail = refEmail;
	}

	public String getRefTel() {
		return RefTel;
	}

	public void setRefTel(String refTel) {
		RefTel = refTel;
	}

	public String getRefMobile() {
		return RefMobile;
	}

	public void setRefMobile(String refMobile) {
		RefMobile = refMobile;
	}

	public Object getEpfData() {
		return epfData;
	}

	public void setEpfData(Object epfData) {
		this.epfData = epfData;
	}

	public String getDetailedLoanPur() {
		return detailedLoanPur;
	}

	public void setDetailedLoanPur(String detailedLoanPur) {
		this.detailedLoanPur = detailedLoanPur;
	}

	public Boolean getIsNameEdited() {
		return isNameEdited;
	}

	public void setIsNameEdited(Boolean isNameEdited) {
		this.isNameEdited = isNameEdited;
	}

	public String getNameEditedByUser() {
		return nameEditedByUser;
	}

	public void setNameEditedByUser(String nameEditedByUser) {
		this.nameEditedByUser = nameEditedByUser;
	}

	public String getMclrRoi() {
		return mclrRoi;
	}

	public void setMclrRoi(String mclrRoi) {
		this.mclrRoi = mclrRoi;
	}

	public String getSpreadRoi() {
		return spreadRoi;
	}

	public void setSpreadRoi(String spreadRoi) {
		this.spreadRoi = spreadRoi;
	}

	public String getEffectiveRoi() {
		return effectiveRoi;
	}

	public void setEffectiveRoi(String effectiveRoi) {
		this.effectiveRoi = effectiveRoi;
	}

	public String getConcessionRoi() {
		return concessionRoi;
	}

	public void setConcessionRoi(String concessionRoi) {
		this.concessionRoi = concessionRoi;
	}

	public String getConcessionRoiBased() {
		return concessionRoiBased;
	}

	public void setConcessionRoiBased(String concessionRoiBased) {
		this.concessionRoiBased = concessionRoiBased;
	}

	public String getFinalRoi() {
		return finalRoi;
	}

	public void setFinalRoi(String finalRoi) {
		this.finalRoi = finalRoi;
	}

	public String getNoteOfBorrower() {
		return noteOfBorrower;
	}

	public void setNoteOfBorrower(String noteOfBorrower) {
		this.noteOfBorrower = noteOfBorrower;
	}

	public ALOneformPrimaryRes getAutoDetails() {
		return autoDetails;
	}

	public void setAutoDetails(ALOneformPrimaryRes autoDetails) {
		this.autoDetails = autoDetails;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getVehicleCategory() {
		return vehicleCategory;
	}

	public void setVehicleCategory(String vehicleCategory) {
		this.vehicleCategory = vehicleCategory;
	}

	public String getVehicleSegment() {
		return vehicleSegment;
	}

	public void setVehicleSegment(String vehicleSegment) {
		this.vehicleSegment = vehicleSegment;
	}


	public Integer getVehicleAge() {
		return vehicleAge;
	}

	public void setVehicleAge(Integer vehicleAge) {
		this.vehicleAge = vehicleAge;
	}

	public String getVehicleEngineVolume() {
		return vehicleEngineVolume;
	}

	public void setVehicleEngineVolume(String vehicleEngineVolume) {
		this.vehicleEngineVolume = vehicleEngineVolume;
	}

	public String getVehicleUse() {
		return vehicleUse;
	}

	public void setVehicleUse(String vehicleUse) {
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

	public Long getBorrowerContribution() {
		return borrowerContribution;
	}

	public void setBorrowerContribution(Long borrowerContribution) {
		this.borrowerContribution = borrowerContribution;
	}
	
	

}
