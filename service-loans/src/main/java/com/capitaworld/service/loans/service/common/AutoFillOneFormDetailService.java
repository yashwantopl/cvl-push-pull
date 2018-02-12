package com.capitaworld.service.loans.service.common;

import com.capitaworld.service.loans.model.common.AutoFillOneFormDetailRequest;
import com.capitaworld.service.loans.model.corporate.CorporateApplicantRequest;

public interface AutoFillOneFormDetailService {
	public void getAndSaveCorporateAutoFillOneFormDateils(Long userId,
			AutoFillOneFormDetailRequest autoFillOneFormDetailModel);
}
