package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Sanket
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class CompaniesHistoryDetails {
	
	@JsonProperty("company-id")
	private String companyId;
	
	@JsonProperty("last-refresh-time")
	private String lastRefreshTime;
	
	@JsonProperty("last-downloaded-time")
	private String lastDownloadedTime;
	
	@JsonProperty("auditor-histories")
	private AuditorHistories[] auditorHistories;
	
	@JsonProperty("caro-histories")
	private CaroHistories[] caroHistories;
	
	@JsonProperty("financial-ratio-histories")
	private FinancialRatioHistories[] financialRatioHistories;

	@JsonProperty("forex-earning-expenditure-histories")
	private ForexEarningExpenditureHistories[] forexEarningExpenditureHistories;
	
	@JsonProperty("preferred-auditor-histories")
	private PreferredAuditorHistories[] preferredAuditorHistories;
	
	@JsonProperty("share-allotments")
	private ShareAllotments[] shareAllotments;
	
	@JsonProperty("past-directors")
	private PastDirectors[] pastDirectors;
	
	@JsonProperty("share-holdings")
	private ShareHoldings[] shareHoldings;
	
	@JsonProperty("signatories-paid")
	private SignatoriesPaid[] signatoriesPaid;
	
	private CompaniesSignatories[] signatories;
	
	private CompaniesCharges[] charges;
	
	@JsonProperty("company-profile")
	private CompaniesProfile companiesProfile; 
	
	@JsonProperty("financial-master")
	private CompaniesFinancialMaster[] financialMaster;
	
	@JsonProperty("financial-statement-histories")
	private CompaniesHistoryFinancialStatement[] financialStatement;
	
	@JsonProperty("company-profile-paid")
	private CompaniesHistoryCompanyProfilePaid companyProfilePaid;
	
	@JsonProperty("product-service")
	private CompaniesHistoryProductService[] productService;
	
	@JsonProperty("associate-entities")
	private CompaniesHistoryAssociateEntities[] associateEntities;
	
	@JsonProperty("annual-returns")
	private CompaniesHistoryAnnualReturn[] annualReturns;
	
	@JsonProperty("credit-rating")
	private CompaniesHistoryCreditRating creditRating;
	
	@JsonProperty("financial-snapshot-histories")
	private CompaniesHistoryFinancialSnapshot[] financialSnapshot;
	
	@JsonProperty("associate-entities-2")
	private CompaniesHistoryAssociateEntitiesTwo[] associateEntitiesTwo;
	
	private OtherInfo[] otherInfo;
	
	@JsonProperty("district-court-cases")
	private DistrictCourtCases[] districtCourtCases;
	
	private Court[] court;
	
	
	/**
	 * @return the associateEntitiesTwo
	 */
	public CompaniesHistoryAssociateEntitiesTwo[] getAssociateEntitiesTwo() {
		return associateEntitiesTwo;
	}

	/**
	 * @param associateEntitiesTwo the associateEntitiesTwo to set
	 */
	public void setAssociateEntitiesTwo(CompaniesHistoryAssociateEntitiesTwo[] associateEntitiesTwo) {
		this.associateEntitiesTwo = associateEntitiesTwo;
	}

	public String getCompanyId() {
		return companyId;
	}

	public String getLastRefreshTime() {
		return lastRefreshTime;
	}

	public String getLastDownloadedTime() {
		return lastDownloadedTime;
	}

	public CompaniesSignatories[] getSignatories() {
		return signatories;
	}

	public CompaniesCharges[] getCharges() {
		return charges;
	}

	public CompaniesProfile getCompaniesProfile() {
		return companiesProfile;
	}

	public CompaniesFinancialMaster[] getFinancialMaster() {
		return financialMaster;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setLastRefreshTime(String lastRefreshTime) {
		this.lastRefreshTime = lastRefreshTime;
	}

	public void setLastDownloadedTime(String lastDownloadedTime) {
		this.lastDownloadedTime = lastDownloadedTime;
	}

	public void setSignatories(CompaniesSignatories[] signatories) {
		this.signatories = signatories;
	}

	public void setCharges(CompaniesCharges[] charges) {
		this.charges = charges;
	}

	public void setCompaniesProfile(CompaniesProfile companiesProfile) {
		this.companiesProfile = companiesProfile;
	}

	public void setFinancialMaster(CompaniesFinancialMaster[] financialMaster) {
		this.financialMaster = financialMaster;
	}

	public CompaniesHistoryFinancialStatement[] getFinancialStatement() {
		return financialStatement;
	}

	public CompaniesHistoryCompanyProfilePaid getCompanyProfilePaid() {
		return companyProfilePaid;
	}

	

	public CompaniesHistoryAssociateEntities[] getAssociateEntities() {
		return associateEntities;
	}

	public CompaniesHistoryAnnualReturn[] getAnnualReturns() {
		return annualReturns;
	}

	public CompaniesHistoryCreditRating getCreditRating() {
		return creditRating;
	}

	public void setFinancialStatement(CompaniesHistoryFinancialStatement[] financialStatement) {
		this.financialStatement = financialStatement;
	}

	public void setCompanyProfilePaid(CompaniesHistoryCompanyProfilePaid companyProfilePaid) {
		this.companyProfilePaid = companyProfilePaid;
	}

	

	public CompaniesHistoryProductService[] getProductService() {
		return productService;
	}

	public void setProductService(CompaniesHistoryProductService[] productService) {
		this.productService = productService;
	}

	public void setAssociateEntities(CompaniesHistoryAssociateEntities[] associateEntities) {
		this.associateEntities = associateEntities;
	}

	public void setAnnualReturns(CompaniesHistoryAnnualReturn[] annualReturns) {
		this.annualReturns = annualReturns;
	}

	public void setCreditRating(CompaniesHistoryCreditRating creditRating) {
		this.creditRating = creditRating;
	}

	public CompaniesHistoryFinancialSnapshot[] getFinancialSnapshot() {
		return financialSnapshot;
	}

	public void setFinancialSnapshot(CompaniesHistoryFinancialSnapshot[] financialSnapshot) {
		this.financialSnapshot = financialSnapshot;
	}

	/**
	 * @return the auditorHistories
	 */
	public AuditorHistories[] getAuditorHistories() {
		return auditorHistories;
	}

	/**
	 * @param auditorHistories the auditorHistories to set
	 */
	public void setAuditorHistories(AuditorHistories[] auditorHistories) {
		this.auditorHistories = auditorHistories;
	}

	/**
	 * @return the caroHistories
	 */
	public CaroHistories[] getCaroHistories() {
		return caroHistories;
	}

	/**
	 * @param caroHistories the caroHistories to set
	 */
	public void setCaroHistories(CaroHistories[] caroHistories) {
		this.caroHistories = caroHistories;
	}

	/**
	 * @return the financialRatioHistories
	 */
	public FinancialRatioHistories[] getFinancialRatioHistories() {
		return financialRatioHistories;
	}

	/**
	 * @param financialRatioHistories the financialRatioHistories to set
	 */
	public void setFinancialRatioHistories(FinancialRatioHistories[] financialRatioHistories) {
		this.financialRatioHistories = financialRatioHistories;
	}

	/**
	 * @return the forexEarningExpenditureHistories
	 */
	public ForexEarningExpenditureHistories[] getForexEarningExpenditureHistories() {
		return forexEarningExpenditureHistories;
	}

	/**
	 * @param forexEarningExpenditureHistories the forexEarningExpenditureHistories to set
	 */
	public void setForexEarningExpenditureHistories(ForexEarningExpenditureHistories[] forexEarningExpenditureHistories) {
		this.forexEarningExpenditureHistories = forexEarningExpenditureHistories;
	}

	/**
	 * @return the preferredAuditorHistories
	 */
	public PreferredAuditorHistories[] getPreferredAuditorHistories() {
		return preferredAuditorHistories;
	}

	/**
	 * @param preferredAuditorHistories the preferredAuditorHistories to set
	 */
	public void setPreferredAuditorHistories(PreferredAuditorHistories[] preferredAuditorHistories) {
		this.preferredAuditorHistories = preferredAuditorHistories;
	}

	/**
	 * @return the shareAllotments
	 */
	public ShareAllotments[] getShareAllotments() {
		return shareAllotments;
	}

	/**
	 * @param shareAllotments the shareAllotments to set
	 */
	public void setShareAllotments(ShareAllotments[] shareAllotments) {
		this.shareAllotments = shareAllotments;
	}

	/**
	 * @return the pastDirectors
	 */
	public PastDirectors[] getPastDirectors() {
		return pastDirectors;
	}

	/**
	 * @param pastDirectors the pastDirectors to set
	 */
	public void setPastDirectors(PastDirectors[] pastDirectors) {
		this.pastDirectors = pastDirectors;
	}

	/**
	 * @return the shareHoldings
	 */
	public ShareHoldings[] getShareHoldings() {
		return shareHoldings;
	}

	/**
	 * @param shareHoldings the shareHoldings to set
	 */
	public void setShareHoldings(ShareHoldings[] shareHoldings) {
		this.shareHoldings = shareHoldings;
	}

	/**
	 * @return the signatoriesPaid
	 */
	public SignatoriesPaid[] getSignatoriesPaid() {
		return signatoriesPaid;
	}

	/**
	 * @param signatoriesPaid the signatoriesPaid to set
	 */
	public void setSignatoriesPaid(SignatoriesPaid[] signatoriesPaid) {
		this.signatoriesPaid = signatoriesPaid;
	}

	public OtherInfo[] getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(OtherInfo[] otherInfo) {
		this.otherInfo = otherInfo;
	}

	public DistrictCourtCases[] getDistrictCourtCases() {
		return districtCourtCases;
	}

	public void setDistrictCourtCases(DistrictCourtCases[] districtCourtCases) {
		this.districtCourtCases = districtCourtCases;
	}

	public Court[] getCourt() {
		return court;
	}

	public void setCourt(Court[] court) {
		this.court = court;
	}


}
