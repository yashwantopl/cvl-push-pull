package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.GuarantorsCorporateDetailRequest;
import com.capitaworld.service.loans.model.UnsecuredGuarantorDetailRequest;


public interface UnsecuredGuarantorDetailService {

	public Boolean saveOrUpdate(FrameRequest frameRequest) throws Exception;

	public List<UnsecuredGuarantorDetailRequest> getGuarantorsCorporateDetailList(Long id,Long userId) throws Exception;

}
