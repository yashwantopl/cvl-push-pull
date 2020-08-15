package com.opl.service.loans.service.common;

import java.io.IOException;

import com.opl.mudra.api.dms.exception.DocumentException;
import com.opl.mudra.api.loans.model.common.AutoFillOneFormDetailRequest;

public interface AutoFillOneFormDetailService {
	public void getAndSaveCorporateAutoFillOneFormDateils(Long userId,
			AutoFillOneFormDetailRequest autoFillOneFormDetailModel) throws DocumentException, NullPointerException, IOException;
}
