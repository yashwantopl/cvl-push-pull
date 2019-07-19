package com.capitaworld.service.loans.service.fundseeker.microfinance;

import com.capitaworld.service.loans.model.micro_finance.AadharDetailsReq;

public interface MfiApplicationService {

    public boolean saveOrUpdateAadharDetails(AadharDetailsReq aadharDetailsReq);
}
