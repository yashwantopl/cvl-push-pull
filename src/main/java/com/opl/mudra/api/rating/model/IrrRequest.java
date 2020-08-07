package com.opl.mudra.api.rating.model;

public class IrrRequest {

	private Long id;

	private Integer status;

	private String message;

	private Integer businessTypeId;

	private String companyName;

	private String panNumber;

	private Long applicationId;

	private Long proposalMappingId;

	private Long userId;

	private FinancialInputRequest financialInputRequest;

	private QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest;

	private QualitativeInputSheetServRequest qualitativeInputSheetServRequest;

	private QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest;

	private Long irrIndustryId;

	private IndustryResponse industryResponse;


	public IrrRequest() {
		super();
	}

	public IrrRequest(String message,Integer status) {
		super();
		this.status = status;
		this.message = message;
	}

	public Long getIrrIndustryId() {
		return irrIndustryId;
	}

	public void setIrrIndustryId(Long irrIndustryId) {
		this.irrIndustryId = irrIndustryId;
	}

	public IndustryResponse getIndustryResponse() {
		return industryResponse;
	}

	public void setIndustryResponse(IndustryResponse industryResponse) {
		this.industryResponse = industryResponse;
	}

	public QualitativeInputSheetManuRequest getQualitativeInputSheetManuRequest() {
		return qualitativeInputSheetManuRequest;
	}

	public void setQualitativeInputSheetManuRequest(QualitativeInputSheetManuRequest qualitativeInputSheetManuRequest) {
		this.qualitativeInputSheetManuRequest = qualitativeInputSheetManuRequest;
	}

	public QualitativeInputSheetServRequest getQualitativeInputSheetServRequest() {
		return qualitativeInputSheetServRequest;
	}

	public void setQualitativeInputSheetServRequest(QualitativeInputSheetServRequest qualitativeInputSheetServRequest) {
		this.qualitativeInputSheetServRequest = qualitativeInputSheetServRequest;
	}

	public QualitativeInputSheetTradRequest getQualitativeInputSheetTradRequest() {
		return qualitativeInputSheetTradRequest;
	}

	public void setQualitativeInputSheetTradRequest(QualitativeInputSheetTradRequest qualitativeInputSheetTradRequest) {
		this.qualitativeInputSheetTradRequest = qualitativeInputSheetTradRequest;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public Long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getBusinessTypeId() {
		return businessTypeId;
	}

	public void setBusinessTypeId(Integer businessTypeId) {
		this.businessTypeId = businessTypeId;
	}

	public FinancialInputRequest getFinancialInputRequest() {
		return financialInputRequest;
	}

	public void setFinancialInputRequest(FinancialInputRequest financialInputRequest) {
		this.financialInputRequest = financialInputRequest;
	}

	public Long getProposalMappingId() {
		return proposalMappingId;
	}

	public void setProposalMappingId(Long proposalMappingId) {
		this.proposalMappingId = proposalMappingId;
	}


	@Override
	public String toString() {
		return "IrrRequest [id=" + id + ", status=" + status + ", message=" + message + ", businessTypeId="
				+ businessTypeId + ", companyName=" + companyName + ", panNumber=" + panNumber + ", applicationId="
				+ applicationId + ", proposalMappingId=" + proposalMappingId + ", userId=" + userId
				+ ", financialInputRequest=" + financialInputRequest + ", qualitativeInputSheetManuRequest="
				+ qualitativeInputSheetManuRequest + ", qualitativeInputSheetServRequest="
				+ qualitativeInputSheetServRequest + ", qualitativeInputSheetTradRequest="
				+ qualitativeInputSheetTradRequest + ", irrIndustryId=" + irrIndustryId + ", industryResponse="
				+ industryResponse + "]";
	}
}
