package com.capitaworld.service.loans.repository.fundseeker.Mfi;

import com.capitaworld.service.loans.domain.fundseeker.mfi.MfiBankDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MfiBankDetailsRepository extends JpaRepository<MfiBankDetails,Long> {

    public MfiBankDetails findByApplicationId(Long appId);
}
