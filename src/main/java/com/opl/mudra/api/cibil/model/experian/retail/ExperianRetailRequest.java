package com.opl.mudra.api.cibil.model.experian.retail;

public class ExperianRetailRequest {
	
	private String clientName;
	private String allowInput;
	private String allowEdit;
	private String allowCaptcha;
	private String allowConsent;
	private String allowEmailVerify;
	private String allowVoucher;
	private String voucherCode;
	private String firstName;
	private String surName;
	private String dateOfBirth;
	private String gender;
	private String mobileNo;
	private String email;
	private String flatno;
	private String buildingName;
	private String city;
	private String state;
	private String pincode;
	private String pan;
	private String reason;
//	private String middleName;
	private String telephoneNo;
	private String telephoneType;
	private String passport;
	private String voterid;
	private String aadhaar;
	private String driverlicense;
	private String noValidationByPass;
	private String emailConditionalByPass;
	
	public ExperianRetailRequest() {
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getAllowInput() {
		return allowInput;
	}

	public void setAllowInput(String allowInput) {
		this.allowInput = allowInput;
	}

	public String getAllowEdit() {
		return allowEdit;
	}

	public void setAllowEdit(String allowEdit) {
		this.allowEdit = allowEdit;
	}

	public String getAllowCaptcha() {
		return allowCaptcha;
	}

	public void setAllowCaptcha(String allowCaptcha) {
		this.allowCaptcha = allowCaptcha;
	}

	public String getAllowConsent() {
		return allowConsent;
	}
	
	public void setAllowConsent(String allowConsent) {
		this.allowConsent = allowConsent;
	}

	public String getAllowEmailVerify() {
		return allowEmailVerify;
	}

	public void setAllowEmailVerify(String allowEmailVerify) {
		this.allowEmailVerify = allowEmailVerify;
	}

	public String getAllowVoucher() {
		return allowVoucher;
	}

	public void setAllowVoucher(String allowVoucher) {
		this.allowVoucher = allowVoucher;
	}

	public String getVoucherCode() {
		return voucherCode;
	}

	public void setVoucherCode(String voucherCode) {
		this.voucherCode = voucherCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlatno() {
		return flatno;
	}

	public void setFlatno(String flatno) {
		this.flatno = flatno;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getTelephoneType() {
		return telephoneType;
	}

	public void setTelephoneType(String telephoneType) {
		this.telephoneType = telephoneType;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getVoterid() {
		return voterid;
	}

	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getDriverlicense() {
		return driverlicense;
	}

	public void setDriverlicense(String driverlicense) {
		this.driverlicense = driverlicense;
	}

	public String getNoValidationByPass() {
		return noValidationByPass;
	}

	public void setNoValidationByPass(String noValidationByPass) {
		this.noValidationByPass = noValidationByPass;
	}

	public String getEmailConditionalByPass() {
		return emailConditionalByPass;
	}

	public void setEmailConditionalByPass(String emailConditionalByPass) {
		this.emailConditionalByPass = emailConditionalByPass;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("clientName=");
		builder.append(clientName);
		builder.append("&allowInput=");
		builder.append(allowInput);
		builder.append("&allowEdit=");
		builder.append(allowEdit);
		builder.append("&allowCaptcha=");
		builder.append(allowCaptcha);
		builder.append("&allowConsent=");
		builder.append(allowConsent);
		builder.append("&allowEmailVerify=");
		builder.append(allowEmailVerify);
		builder.append("&allowVoucher=");
		builder.append(allowVoucher);
		builder.append("&voucherCode=");
		builder.append(voucherCode);
		builder.append("&firstName=");
		builder.append(firstName);
		builder.append("&surName=");
		builder.append(surName);
		builder.append("&dateOfBirth=");
		builder.append(dateOfBirth);
		builder.append("&gender=");
		builder.append(gender);
		builder.append("&mobileNo=");
		builder.append(mobileNo);
		builder.append("&email=");
		builder.append(email);
		builder.append("&flatno=");
		builder.append(flatno);
		builder.append("&buildingName=");
		builder.append(buildingName);
		builder.append("&city=");
		builder.append(city);
		builder.append("&state=");
		builder.append(state);
		builder.append("&pincode=");
		builder.append(pincode);
		builder.append("&pan=");
		builder.append(pan);
		builder.append("&reason=");
		builder.append(reason);
		builder.append("&telephoneNo=");
		builder.append(telephoneNo);
		builder.append("&telephoneType=");
		builder.append(telephoneType);
		builder.append("&passport=");
		builder.append(passport);
		builder.append("&voterid=");
		builder.append(voterid);
		builder.append("&aadhaar=");
		builder.append(aadhaar);
		builder.append("&driverlicense=");
		builder.append(driverlicense);
		builder.append("&noValidationByPass=");
		builder.append(noValidationByPass);
		builder.append("&emailConditionalByPass=");
		builder.append(emailConditionalByPass);
		return builder.toString();
	}

}
