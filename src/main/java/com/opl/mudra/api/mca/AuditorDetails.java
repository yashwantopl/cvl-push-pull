/**
 * 
 */
package com.opl.mudra.api.mca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author nilay
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(Include.NON_NULL)
public class AuditorDetails {
	
	@JsonProperty("category-of-auditor")
	private String categoryOfAuditor;
	
	@JsonProperty("auditor-or-auditor-firms-pan-number")
	private String auditorOrAuditorFirmsPanNumber;
	
	@JsonProperty("name-of-the-auditor-or-auditors-firm")
	private String nameOfTheAuditorOrAuditorsFirm;
	
	@JsonProperty("auditors-firms-registration-number")
	private String auditorsFirmsRegistrationNumber;
	
	@JsonProperty("name-of-the-member")
	private String nameOfTheMember;
	
	@JsonProperty("membership-number-of-auditor")
	private String membershipNumberOfAuditor;
	
	@JsonProperty("address")
	private String address;
	
	@JsonProperty("address-map")
	private String addressMap;
	
	@JsonProperty("appointment-auditor-pan")
	private String appointmentAuditorPan;
	
	@JsonProperty("appointment-auditor-category")
	private String appointmentAuditorCategory;
	
	@JsonProperty("appointment-auditor-name-or-firm-name")
	private String appointmentAuditorName;
	
	@JsonProperty("appointment-auditor-address")
	private String appointmentAuditorAdd;
	
	@JsonProperty("appointment-auditor-email")
	private String appointmentAuditorEmail;
	
	@JsonProperty("appointment-period-from")
	private String appointmentPeriod;
	
	@JsonProperty("appointment-period-to")
	private String appointmentPeriodTo;
	
	@JsonProperty("number-of-year-appointment")
	private String numberOfYearAppointment;
	
	@JsonProperty("appointed-within-twenty-companies")
	private String appointedWithinTwenty;
	
	@JsonProperty("appointed-in-agm")
	private String appointedInAgm;
	
	@JsonProperty("appointment-agm-date")
	private String appointmentAgmDate;
	
	@JsonProperty("appointment-date")
	private String appointmentDate;
	
	@JsonProperty("appointment-casual-vacancy-in-the-office-of-auditor")
	private String appointmentCasualVacancy;
	
	@JsonProperty("appointment-person-vacated-office")
	private String appointmentPersonVacanted;
	
	@JsonProperty("appointment-membership-number-of-auditor-who-vacated")
	private String appointmentMemberShipNumber;
	
	@JsonProperty("appointment-date-of-vacancy")
	private String appointDateOfVacancy;
	
	@JsonProperty("appointment-reason-of-vacancy")
	private String appointmentReason;
	
	@JsonProperty("resignation-whether-auditor-resigned")
	private String resignationWhetherAuditor;
	
	@JsonProperty("resignation-auditor-resignation-date")
	private String resignationAuditorResignationDate;
	
	@JsonProperty("resignation-auditor-reason-for-resignation")
	private String resignationAuditorReasonForResignation;
	
	@JsonProperty("resignation-any-other-fact-related-to-resignation")
	private String resignationAnyOtherFact;
	
	@JsonProperty("resignation-company-date-of-resignation")
	private String resignationCompanyDateOfResignaton;
	
	@JsonProperty("resignation-company-reason-for-resignation")
	private String resignationCompanyReasonForResignation;
	
	@JsonProperty("adt1-file-id")
	private String adt1FileId;
	
	@JsonProperty("adt3-file-id")
	private String adt3FileId;

	/**
	 * @return the categoryOfAuditor
	 */
	public String getCategoryOfAuditor() {
		return categoryOfAuditor;
	}

	/**
	 * @param categoryOfAuditor the categoryOfAuditor to set
	 */
	public void setCategoryOfAuditor(String categoryOfAuditor) {
		this.categoryOfAuditor = categoryOfAuditor;
	}

	/**
	 * @return the auditorOrAuditorFirmsPanNumber
	 */
	public String getAuditorOrAuditorFirmsPanNumber() {
		return auditorOrAuditorFirmsPanNumber;
	}

	/**
	 * @param auditorOrAuditorFirmsPanNumber the auditorOrAuditorFirmsPanNumber to set
	 */
	public void setAuditorOrAuditorFirmsPanNumber(String auditorOrAuditorFirmsPanNumber) {
		this.auditorOrAuditorFirmsPanNumber = auditorOrAuditorFirmsPanNumber;
	}

	/**
	 * @return the nameOfTheAuditorOrAuditorsFirm
	 */
	public String getNameOfTheAuditorOrAuditorsFirm() {
		return nameOfTheAuditorOrAuditorsFirm;
	}

	/**
	 * @param nameOfTheAuditorOrAuditorsFirm the nameOfTheAuditorOrAuditorsFirm to set
	 */
	public void setNameOfTheAuditorOrAuditorsFirm(String nameOfTheAuditorOrAuditorsFirm) {
		this.nameOfTheAuditorOrAuditorsFirm = nameOfTheAuditorOrAuditorsFirm;
	}

	/**
	 * @return the auditorsFirmsRegistrationNumber
	 */
	public String getAuditorsFirmsRegistrationNumber() {
		return auditorsFirmsRegistrationNumber;
	}

	/**
	 * @param auditorsFirmsRegistrationNumber the auditorsFirmsRegistrationNumber to set
	 */
	public void setAuditorsFirmsRegistrationNumber(String auditorsFirmsRegistrationNumber) {
		this.auditorsFirmsRegistrationNumber = auditorsFirmsRegistrationNumber;
	}

	/**
	 * @return the nameOfTheMember
	 */
	public String getNameOfTheMember() {
		return nameOfTheMember;
	}

	/**
	 * @param nameOfTheMember the nameOfTheMember to set
	 */
	public void setNameOfTheMember(String nameOfTheMember) {
		this.nameOfTheMember = nameOfTheMember;
	}

	/**
	 * @return the membershipNumberOfAuditor
	 */
	public String getMembershipNumberOfAuditor() {
		return membershipNumberOfAuditor;
	}

	/**
	 * @param membershipNumberOfAuditor the membershipNumberOfAuditor to set
	 */
	public void setMembershipNumberOfAuditor(String membershipNumberOfAuditor) {
		this.membershipNumberOfAuditor = membershipNumberOfAuditor;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the addressMap
	 */
	public String getAddressMap() {
		return addressMap;
	}

	/**
	 * @param addressMap the addressMap to set
	 */
	public void setAddressMap(String addressMap) {
		this.addressMap = addressMap;
	}

	/**
	 * @return the appointmentAuditorPan
	 */
	public String getAppointmentAuditorPan() {
		return appointmentAuditorPan;
	}

	/**
	 * @param appointmentAuditorPan the appointmentAuditorPan to set
	 */
	public void setAppointmentAuditorPan(String appointmentAuditorPan) {
		this.appointmentAuditorPan = appointmentAuditorPan;
	}

	/**
	 * @return the appointmentAuditorCategory
	 */
	public String getAppointmentAuditorCategory() {
		return appointmentAuditorCategory;
	}

	/**
	 * @param appointmentAuditorCategory the appointmentAuditorCategory to set
	 */
	public void setAppointmentAuditorCategory(String appointmentAuditorCategory) {
		this.appointmentAuditorCategory = appointmentAuditorCategory;
	}

	/**
	 * @return the appointmentAuditorName
	 */
	public String getAppointmentAuditorName() {
		return appointmentAuditorName;
	}

	/**
	 * @param appointmentAuditorName the appointmentAuditorName to set
	 */
	public void setAppointmentAuditorName(String appointmentAuditorName) {
		this.appointmentAuditorName = appointmentAuditorName;
	}

	/**
	 * @return the appointmentAuditorAdd
	 */
	public String getAppointmentAuditorAdd() {
		return appointmentAuditorAdd;
	}

	/**
	 * @param appointmentAuditorAdd the appointmentAuditorAdd to set
	 */
	public void setAppointmentAuditorAdd(String appointmentAuditorAdd) {
		this.appointmentAuditorAdd = appointmentAuditorAdd;
	}

	/**
	 * @return the appointmentAuditorEmail
	 */
	public String getAppointmentAuditorEmail() {
		return appointmentAuditorEmail;
	}

	/**
	 * @param appointmentAuditorEmail the appointmentAuditorEmail to set
	 */
	public void setAppointmentAuditorEmail(String appointmentAuditorEmail) {
		this.appointmentAuditorEmail = appointmentAuditorEmail;
	}

	/**
	 * @return the appointmentPeriod
	 */
	public String getAppointmentPeriod() {
		return appointmentPeriod;
	}

	/**
	 * @param appointmentPeriod the appointmentPeriod to set
	 */
	public void setAppointmentPeriod(String appointmentPeriod) {
		this.appointmentPeriod = appointmentPeriod;
	}

	/**
	 * @return the appointmentPeriodTo
	 */
	public String getAppointmentPeriodTo() {
		return appointmentPeriodTo;
	}

	/**
	 * @param appointmentPeriodTo the appointmentPeriodTo to set
	 */
	public void setAppointmentPeriodTo(String appointmentPeriodTo) {
		this.appointmentPeriodTo = appointmentPeriodTo;
	}

	/**
	 * @return the numberOfYearAppointment
	 */
	public String getNumberOfYearAppointment() {
		return numberOfYearAppointment;
	}

	/**
	 * @param numberOfYearAppointment the numberOfYearAppointment to set
	 */
	public void setNumberOfYearAppointment(String numberOfYearAppointment) {
		this.numberOfYearAppointment = numberOfYearAppointment;
	}

	/**
	 * @return the appointedWithinTwenty
	 */
	public String getAppointedWithinTwenty() {
		return appointedWithinTwenty;
	}

	/**
	 * @param appointedWithinTwenty the appointedWithinTwenty to set
	 */
	public void setAppointedWithinTwenty(String appointedWithinTwenty) {
		this.appointedWithinTwenty = appointedWithinTwenty;
	}

	/**
	 * @return the appointedInAgm
	 */
	public String getAppointedInAgm() {
		return appointedInAgm;
	}

	/**
	 * @param appointedInAgm the appointedInAgm to set
	 */
	public void setAppointedInAgm(String appointedInAgm) {
		this.appointedInAgm = appointedInAgm;
	}

	/**
	 * @return the appointmentAgmDate
	 */
	public String getAppointmentAgmDate() {
		return appointmentAgmDate;
	}

	/**
	 * @param appointmentAgmDate the appointmentAgmDate to set
	 */
	public void setAppointmentAgmDate(String appointmentAgmDate) {
		this.appointmentAgmDate = appointmentAgmDate;
	}

	/**
	 * @return the appointmentDate
	 */
	public String getAppointmentDate() {
		return appointmentDate;
	}

	/**
	 * @param appointmentDate the appointmentDate to set
	 */
	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	/**
	 * @return the appointmentCasualVacancy
	 */
	public String getAppointmentCasualVacancy() {
		return appointmentCasualVacancy;
	}

	/**
	 * @param appointmentCasualVacancy the appointmentCasualVacancy to set
	 */
	public void setAppointmentCasualVacancy(String appointmentCasualVacancy) {
		this.appointmentCasualVacancy = appointmentCasualVacancy;
	}

	/**
	 * @return the appointmentPersonVacanted
	 */
	public String getAppointmentPersonVacanted() {
		return appointmentPersonVacanted;
	}

	/**
	 * @param appointmentPersonVacanted the appointmentPersonVacanted to set
	 */
	public void setAppointmentPersonVacanted(String appointmentPersonVacanted) {
		this.appointmentPersonVacanted = appointmentPersonVacanted;
	}

	/**
	 * @return the appointmentMemberShipNumber
	 */
	public String getAppointmentMemberShipNumber() {
		return appointmentMemberShipNumber;
	}

	/**
	 * @param appointmentMemberShipNumber the appointmentMemberShipNumber to set
	 */
	public void setAppointmentMemberShipNumber(String appointmentMemberShipNumber) {
		this.appointmentMemberShipNumber = appointmentMemberShipNumber;
	}

	/**
	 * @return the appointDateOfVacancy
	 */
	public String getAppointDateOfVacancy() {
		return appointDateOfVacancy;
	}

	/**
	 * @param appointDateOfVacancy the appointDateOfVacancy to set
	 */
	public void setAppointDateOfVacancy(String appointDateOfVacancy) {
		this.appointDateOfVacancy = appointDateOfVacancy;
	}

	/**
	 * @return the appointmentReason
	 */
	public String getAppointmentReason() {
		return appointmentReason;
	}

	/**
	 * @param appointmentReason the appointmentReason to set
	 */
	public void setAppointmentReason(String appointmentReason) {
		this.appointmentReason = appointmentReason;
	}

	/**
	 * @return the resignationWhetherAuditor
	 */
	public String getResignationWhetherAuditor() {
		return resignationWhetherAuditor;
	}

	/**
	 * @param resignationWhetherAuditor the resignationWhetherAuditor to set
	 */
	public void setResignationWhetherAuditor(String resignationWhetherAuditor) {
		this.resignationWhetherAuditor = resignationWhetherAuditor;
	}

	/**
	 * @return the resignationAuditorResignationDate
	 */
	public String getResignationAuditorResignationDate() {
		return resignationAuditorResignationDate;
	}

	/**
	 * @param resignationAuditorResignationDate the resignationAuditorResignationDate to set
	 */
	public void setResignationAuditorResignationDate(String resignationAuditorResignationDate) {
		this.resignationAuditorResignationDate = resignationAuditorResignationDate;
	}

	/**
	 * @return the resignationAuditorReasonForResignation
	 */
	public String getResignationAuditorReasonForResignation() {
		return resignationAuditorReasonForResignation;
	}

	/**
	 * @param resignationAuditorReasonForResignation the resignationAuditorReasonForResignation to set
	 */
	public void setResignationAuditorReasonForResignation(String resignationAuditorReasonForResignation) {
		this.resignationAuditorReasonForResignation = resignationAuditorReasonForResignation;
	}

	/**
	 * @return the resignationAnyOtherFact
	 */
	public String getResignationAnyOtherFact() {
		return resignationAnyOtherFact;
	}

	/**
	 * @param resignationAnyOtherFact the resignationAnyOtherFact to set
	 */
	public void setResignationAnyOtherFact(String resignationAnyOtherFact) {
		this.resignationAnyOtherFact = resignationAnyOtherFact;
	}

	/**
	 * @return the resignationCompanyDateOfResignaton
	 */
	public String getResignationCompanyDateOfResignaton() {
		return resignationCompanyDateOfResignaton;
	}

	/**
	 * @param resignationCompanyDateOfResignaton the resignationCompanyDateOfResignaton to set
	 */
	public void setResignationCompanyDateOfResignaton(String resignationCompanyDateOfResignaton) {
		this.resignationCompanyDateOfResignaton = resignationCompanyDateOfResignaton;
	}

	/**
	 * @return the resignationCompanyReasonForResignation
	 */
	public String getResignationCompanyReasonForResignation() {
		return resignationCompanyReasonForResignation;
	}

	/**
	 * @param resignationCompanyReasonForResignation the resignationCompanyReasonForResignation to set
	 */
	public void setResignationCompanyReasonForResignation(String resignationCompanyReasonForResignation) {
		this.resignationCompanyReasonForResignation = resignationCompanyReasonForResignation;
	}

	/**
	 * @return the adt1FileId
	 */
	public String getAdt1FileId() {
		return adt1FileId;
	}

	/**
	 * @param adt1FileId the adt1FileId to set
	 */
	public void setAdt1FileId(String adt1FileId) {
		this.adt1FileId = adt1FileId;
	}

	/**
	 * @return the adt3FileId
	 */
	public String getAdt3FileId() {
		return adt3FileId;
	}

	/**
	 * @param adt3FileId the adt3FileId to set
	 */
	public void setAdt3FileId(String adt3FileId) {
		this.adt3FileId = adt3FileId;
	}
	
	

}
