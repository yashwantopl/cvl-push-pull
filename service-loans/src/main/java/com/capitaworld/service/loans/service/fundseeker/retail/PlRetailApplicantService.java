package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PLRetailApplicantRequest;

public interface PlRetailApplicantService {
    public boolean saveProfile(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws Exception;

    public PLRetailApplicantRequest getProfile(Long userId, Long applicationId) throws Exception;

    public boolean savePrimary(PLRetailApplicantRequest plRetailApplicantRequest, Long userId) throws Exception;

    public PLRetailApplicantRequest getPrimary(Long userId, Long applicationId) throws Exception;
}
