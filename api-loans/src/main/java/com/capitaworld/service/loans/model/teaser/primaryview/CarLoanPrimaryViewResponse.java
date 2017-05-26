package com.capitaworld.service.loans.model.teaser.primaryview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhaval on 23-May-17.
 */
public class CarLoanPrimaryViewResponse {

    private RetailProfileViewResponse  applicant;
    private List<RetailProfileViewResponse> coApplicantList = new ArrayList<RetailProfileViewResponse>();
    private List<RetailProfileViewResponse> guarantorList = new ArrayList<RetailProfileViewResponse>();
    private CarLoanResponse carLoanResponse;

    public RetailProfileViewResponse getApplicant() {
        return applicant;
    }

    public void setApplicant(RetailProfileViewResponse applicant) {
        this.applicant = applicant;
    }

    public List<RetailProfileViewResponse> getCoApplicantList() {
        return coApplicantList;
    }

    public void setCoApplicantList(List<RetailProfileViewResponse> coApplicantList) {
        this.coApplicantList = coApplicantList;
    }

    public List<RetailProfileViewResponse> getGuarantorList() {
        return guarantorList;
    }

    public void setGuarantorList(List<RetailProfileViewResponse> guarantorList) {
        this.guarantorList = guarantorList;
    }

    public CarLoanResponse getCarLoanResponse() {
        return carLoanResponse;
    }

    public void setCarLoanResponse(CarLoanResponse carLoanResponse) {
        this.carLoanResponse = carLoanResponse;
    }
}
