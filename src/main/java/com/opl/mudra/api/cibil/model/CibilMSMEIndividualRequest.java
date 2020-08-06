package com.opl.mudra.api.cibil.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.opl.mudra.api.cibil.utils.CibilUtils.ApplicantTypeEnum;
import com.opl.mudra.api.cibil.utils.CibilUtils.GenderTypeEnum;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CibilMSMEIndividualRequest implements Serializable {

	private static final long serialVersionUID = 5320677886683244080L;

	private String firstName;
	private String lastName;
	private String middleName;
	private ApplicantTypeEnum applicantType;
	private Date dateOfBirth;
	private GenderTypeEnum gender;
	private List<IdentityTypeRequest> identifiers;
	private List<AddressRequest> addresses;
	private List<TelephoneTypeRequest> telephones;

	// MFI Client Information
	private String memberOtherId1Type;
	private String memberOtherId1;
	private String memberOtherId2Type;
	private String memberOtherId2;
	private String memberOtherId3Type;
	private String memberOtherId3;
	private String keyPersonName;
	private String keyPersonRelation;
	private String memberRelationName1;
	private String memberRelationType1;
	private String memberRelationName2;
	private String memberRelationType2;
	private String memberRelationName3;
	private String memberRelationType3;
	private String memberRelationName4;
	private String memberRelationType4;
	private String nomineeName;
	private String nomineeRelation;

	public CibilMSMEIndividualRequest() {
		super();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public ApplicantTypeEnum getApplicantType() {
		return applicantType;
	}

	public void setApplicantType(ApplicantTypeEnum applicantType) {
		this.applicantType = applicantType;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public GenderTypeEnum getGender() {
		return gender;
	}

	public void setGender(GenderTypeEnum gender) {
		this.gender = gender;
	}

	public List<AddressRequest> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressRequest> addresses) {
		this.addresses = addresses;
	}

	public List<IdentityTypeRequest> getIdentifiers() {
		return identifiers;
	}

	public void setIdentifiers(List<IdentityTypeRequest> identifiers) {
		this.identifiers = identifiers;
	}

	public List<TelephoneTypeRequest> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<TelephoneTypeRequest> telephones) {
		this.telephones = telephones;
	}

	public String getMemberOtherId1Type() {
		return memberOtherId1Type;
	}

	public void setMemberOtherId1Type(String memberOtherId1Type) {
		this.memberOtherId1Type = memberOtherId1Type;
	}

	public String getMemberOtherId1() {
		return memberOtherId1;
	}

	public void setMemberOtherId1(String memberOtherId1) {
		this.memberOtherId1 = memberOtherId1;
	}

	public String getMemberOtherId2Type() {
		return memberOtherId2Type;
	}

	public void setMemberOtherId2Type(String memberOtherId2Type) {
		this.memberOtherId2Type = memberOtherId2Type;
	}

	public String getMemberOtherId2() {
		return memberOtherId2;
	}

	public void setMemberOtherId2(String memberOtherId2) {
		this.memberOtherId2 = memberOtherId2;
	}

	public String getMemberOtherId3Type() {
		return memberOtherId3Type;
	}

	public void setMemberOtherId3Type(String memberOtherId3Type) {
		this.memberOtherId3Type = memberOtherId3Type;
	}

	public String getMemberOtherId3() {
		return memberOtherId3;
	}

	public void setMemberOtherId3(String memberOtherId3) {
		this.memberOtherId3 = memberOtherId3;
	}

	public String getKeyPersonName() {
		return keyPersonName;
	}

	public void setKeyPersonName(String keyPersonName) {
		this.keyPersonName = keyPersonName;
	}

	public String getKeyPersonRelation() {
		return keyPersonRelation;
	}

	public void setKeyPersonRelation(String keyPersonRelation) {
		this.keyPersonRelation = keyPersonRelation;
	}

	public String getMemberRelationName1() {
		return memberRelationName1;
	}

	public void setMemberRelationName1(String memberRelationName1) {
		this.memberRelationName1 = memberRelationName1;
	}

	public String getMemberRelationType1() {
		return memberRelationType1;
	}

	public void setMemberRelationType1(String memberRelationType1) {
		this.memberRelationType1 = memberRelationType1;
	}

	public String getMemberRelationName2() {
		return memberRelationName2;
	}

	public void setMemberRelationName2(String memberRelationName2) {
		this.memberRelationName2 = memberRelationName2;
	}

	public String getMemberRelationType2() {
		return memberRelationType2;
	}

	public void setMemberRelationType2(String memberRelationType2) {
		this.memberRelationType2 = memberRelationType2;
	}

	public String getMemberRelationName3() {
		return memberRelationName3;
	}

	public void setMemberRelationName3(String memberRelationName3) {
		this.memberRelationName3 = memberRelationName3;
	}

	public String getMemberRelationType3() {
		return memberRelationType3;
	}

	public void setMemberRelationType3(String memberRelationType3) {
		this.memberRelationType3 = memberRelationType3;
	}

	public String getMemberRelationName4() {
		return memberRelationName4;
	}

	public void setMemberRelationName4(String memberRelationName4) {
		this.memberRelationName4 = memberRelationName4;
	}

	public String getMemberRelationType4() {
		return memberRelationType4;
	}

	public void setMemberRelationType4(String memberRelationType4) {
		this.memberRelationType4 = memberRelationType4;
	}

	public String getNomineeName() {
		return nomineeName;
	}

	public void setNomineeName(String nomineeName) {
		this.nomineeName = nomineeName;
	}

	public String getNomineeRelation() {
		return nomineeRelation;
	}

	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}
}
