package com.capitaworld.service.loans.service.common;

import java.io.IOException;

import com.capitaworld.service.dms.exception.DocumentException;
import com.capitaworld.service.loans.model.common.AutoFillOneFormDetailRequest;

public interface AutoFillOneFormDetailService {
	public void getAndSaveCorporateAutoFillOneFormDateils(Long userId,
			AutoFillOneFormDetailRequest autoFillOneFormDetailModel) throws DocumentException, NullPointerException, IOException;
}
