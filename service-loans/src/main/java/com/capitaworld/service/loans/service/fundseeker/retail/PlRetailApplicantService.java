package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.retail.PLRetailApplicant;

public interface PlRetailApplicantService {
    public boolean save(PLRetailApplicant plRetailApplicant, Long userId) throws Exception;

    public PLRetailApplicant get(Long userId, Long applicationId) throws Exception;
}
