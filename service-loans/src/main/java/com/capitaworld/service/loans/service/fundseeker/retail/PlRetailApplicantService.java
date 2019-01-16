package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;
import com.capitaworld.service.loans.model.retail.RetailFinalInfoRequest;

public interface PlRetailApplicantService {
    public boolean saveProfile(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException;

    public PLRetailApplicantRequest getProfile(Long userId, Long applicationId) throws LoansException;

    public boolean savePrimary(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws LoansException;

    public PLRetailApplicantRequest getPrimary(Long userId, Long applicationId) throws LoansException;

    public boolean saveFinal(RetailFinalInfoRequest applicantRequest, Long userId) throws LoansException;

    public RetailFinalInfoRequest getFinal(Long userId, Long applicationId) throws LoansException;
}
