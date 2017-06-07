package com.capitaworld.service.loans.model.teaser.primaryview;

import com.capitaworld.service.loans.model.AddressResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dhaval on 24-May-17.
 */
public class PersonalLoanResponse implements Serializable{
    private String purposeOfLoan;
    private AddressResponse permanentAddress;
    private AddressResponse officeAddress;
    private String dateOfProposal;
    private String tenure;
    private String loanType;
    private String loanAmount;
    private String currency;
    private List<Object> applicantProfilePicture;

    public String getPurposeOfLoan() {
        return purposeOfLoan;
    }

    public void setPurposeOfLoan(String purposeOfLoan) {
        this.purposeOfLoan = purposeOfLoan;
    }

    public AddressResponse getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(AddressResponse permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public AddressResponse getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(AddressResponse officeAddress) {
        this.officeAddress = officeAddress;
    }

    public String getDateOfProposal() {
        return dateOfProposal;
    }

    public void setDateOfProposal(String dateOfProposal) {
        this.dateOfProposal = dateOfProposal;
    }

    public String getTenure() {
        return tenure;
    }

    public void setTenure(String tenure) {
        this.tenure = tenure;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public List<Object> getApplicantProfilePicture() {
        return applicantProfilePicture;
    }

    public void setApplicantProfilePicture(List<Object> applicantProfilePicture) {
        this.applicantProfilePicture = applicantProfilePicture;
    }
}
