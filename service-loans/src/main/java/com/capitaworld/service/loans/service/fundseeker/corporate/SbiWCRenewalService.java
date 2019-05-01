package com.capitaworld.service.loans.service.fundseeker.corporate;

/**
 * Created by dhaval.panchal on 25-Apr-19.
 */
public interface SbiWCRenewalService {
    public boolean callSkipPayment(Long application_id);

    public boolean callMatchEngine(Long application_id, Long userId);
}
