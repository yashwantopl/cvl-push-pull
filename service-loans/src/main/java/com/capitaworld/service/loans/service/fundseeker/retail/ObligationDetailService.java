package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.retail.ObligationDetailRequest;

import java.util.List;

/**
 * Created by ravina.panchal on 04-10-2018.
 */
public interface ObligationDetailService {


    public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

    public List<ObligationDetailRequest> getObligationDetailList(Long id, int applicationType) throws Exception;

}
