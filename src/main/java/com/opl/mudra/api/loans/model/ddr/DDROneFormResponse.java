package com.opl.mudra.api.loans.model.ddr;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.AssociatedConcernDetailRequest;
import com.opl.mudra.api.loans.model.DirectorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ExistingProductDetailRequest;
import com.opl.mudra.api.loans.model.FinancialArrangementDetailResponseString;
import com.opl.mudra.api.loans.model.FinancialArrangementsDetailResponse;
import com.opl.mudra.api.loans.model.OwnershipDetailResponse;
import com.opl.mudra.api.loans.model.PromotorBackgroundDetailResponse;
import com.opl.mudra.api.loans.model.ProposedProductDetailRequest;
import com.opl.mudra.api.loans.model.SecurityCorporateDetailRequest;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DDROneFormResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nameOfBorrower;
	private String regOfficeAddress;
	private String contactNo;
	private String corpOfficeAddress;
	private String regEmailId;
	private String constitution;
	private String establishMentYear;
	private String aboutMe;
	private String currency;
	private String orgName;
	private Long ddrStatusId;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
	private Date approvedDate;
	

	public Date getApprovedDate() {
		return approvedDate;
	}

	public void setApprovedDate(Date approvedDate) {
		this.approvedDate = approvedDate;
	}


	List<PromotorBackgroundDetailResponse> promoBackRespList = null;
	List<OwnershipDetailResponse> ownershipRespList = null;
	List<FinancialArrangementsDetailResponse> fincArrngDetailResList = null;
	List<ProposedProductDetailRequest> proposedProductDetailList = null;
	List<ExistingProductDetailRequest> existingProductDetailList = null;
	List<AssociatedConcernDetailRequest> associatedConcernDetailList = null;
	List<DDRCMACalculationResponse> dDRCMACalculationList = null;
	//List<ReferenceRetailDetailsRequest> referencesResponseList = null;
	List<SecurityCorporateDetailRequest> securityCorporateDetailList = null;
	//List<DirectorBackgroundDetailRequest> directorBackgroundDetailList = null;
	private List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses;
	private List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList;
	
	public DDROneFormResponse() {
		securityCorporateDetailList = Collections.emptyList();
		//referencesResponseList = Collections.emptyList();
		dDRCMACalculationList = Collections.emptyList();
		associatedConcernDetailList = Collections.emptyList();
		existingProductDetailList = Collections.emptyList();
		proposedProductDetailList = Collections.emptyList();
		fincArrngDetailResList = Collections.emptyList();
		ownershipRespList = Collections.emptyList();
		promoBackRespList = Collections.emptyList();
		//directorBackgroundDetailList = Collections.emptyList();
	}

	public String getNameOfBorrower() {
		return nameOfBorrower;
	}

	public void setNameOfBorrower(String nameOfBorrower) {
		this.nameOfBorrower = nameOfBorrower;
	}

	public String getRegOfficeAddress() {
		return regOfficeAddress;
	}

	public void setRegOfficeAddress(String regOfficeAddress) {
		this.regOfficeAddress = regOfficeAddress;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getCorpOfficeAddress() {
		return corpOfficeAddress;
	}

	public void setCorpOfficeAddress(String corpOfficeAddress) {
		this.corpOfficeAddress = corpOfficeAddress;
	}

	public String getRegEmailId() {
		return regEmailId;
	}

	public void setRegEmailId(String regEmailId) {
		this.regEmailId = regEmailId;
	}

	public String getConstitution() {
		return constitution;
	}

	public void setConstitution(String constitution) {
		this.constitution = constitution;
	}

	public String getEstablishMentYear() {
		return establishMentYear;
	}

	public void setEstablishMentYear(String establishMentYear) {
		this.establishMentYear = establishMentYear;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<PromotorBackgroundDetailResponse> getPromoBackRespList() {
		return promoBackRespList;
	}

	public void setPromoBackRespList(List<PromotorBackgroundDetailResponse> promoBackRespList) {
		this.promoBackRespList = promoBackRespList;
	}

	public List<OwnershipDetailResponse> getOwnershipRespList() {
		return ownershipRespList;
	}

	public void setOwnershipRespList(List<OwnershipDetailResponse> ownershipRespList) {
		this.ownershipRespList = ownershipRespList;
	}

	public List<FinancialArrangementsDetailResponse> getFincArrngDetailResList() {
		return fincArrngDetailResList;
	}

	public void setFincArrngDetailResList(List<FinancialArrangementsDetailResponse> fincArrngDetailResList) {
		this.fincArrngDetailResList = fincArrngDetailResList;
	}

	public List<ProposedProductDetailRequest> getProposedProductDetailList() {
		return proposedProductDetailList;
	}

	public void setProposedProductDetailList(List<ProposedProductDetailRequest> proposedProductDetailList) {
		this.proposedProductDetailList = proposedProductDetailList;
	}

	public List<ExistingProductDetailRequest> getExistingProductDetailList() {
		return existingProductDetailList;
	}

	public void setExistingProductDetailList(List<ExistingProductDetailRequest> existingProductDetailList) {
		this.existingProductDetailList = existingProductDetailList;
	}

	public List<AssociatedConcernDetailRequest> getAssociatedConcernDetailList() {
		return associatedConcernDetailList;
	}

	public void setAssociatedConcernDetailList(List<AssociatedConcernDetailRequest> associatedConcernDetailList) {
		this.associatedConcernDetailList = associatedConcernDetailList;
	}

	public List<DDRCMACalculationResponse> getdDRCMACalculationList() {
		return dDRCMACalculationList;
	}

	public void setdDRCMACalculationList(List<DDRCMACalculationResponse> dDRCMACalculationList) {
		this.dDRCMACalculationList = dDRCMACalculationList;
	}

	public List<SecurityCorporateDetailRequest> getSecurityCorporateDetailList() {
		return securityCorporateDetailList;
	}

	public void setSecurityCorporateDetailList(List<SecurityCorporateDetailRequest> securityCorporateDetailList) {
		this.securityCorporateDetailList = securityCorporateDetailList;
	}

	public List<DirectorBackgroundDetailResponse> getDirectorBackgroundDetailResponses() {
		return directorBackgroundDetailResponses;
	}

	public void setDirectorBackgroundDetailResponses(List<DirectorBackgroundDetailResponse> directorBackgroundDetailResponses) {
		this.directorBackgroundDetailResponses = directorBackgroundDetailResponses;
	}

	public List<FinancialArrangementDetailResponseString> getFinancialArrangementsDetailResponseList() {
		return financialArrangementsDetailResponseList;
	}

	public void setFinancialArrangementsDetailResponseList(List<FinancialArrangementDetailResponseString> financialArrangementsDetailResponseList) {
		this.financialArrangementsDetailResponseList = financialArrangementsDetailResponseList;
	}

	public Long getDdrStatusId() {
		return ddrStatusId;
	}

	public void setDdrStatusId(Long ddrStatusId) {
		this.ddrStatusId = ddrStatusId;
	}

	public static void printFields(Object obj) throws LoansException {
		try {
			Field[] fields = DDROneFormResponse.class.getDeclaredFields();
			for(Field field : fields) {
				Object value = field.get(obj);
				if(value instanceof String){
					String a = StringEscapeUtils.escapeXml(value.toString());
					value = a;
					field.set(obj, value);
				}
			}
		}
		catch (Exception e){
			throw new LoansException(e);
		}

	}
}
