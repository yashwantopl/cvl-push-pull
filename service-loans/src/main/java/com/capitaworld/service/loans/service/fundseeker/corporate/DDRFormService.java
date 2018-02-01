package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws Exception;
	
	public DDRFormDetailsRequest get(Long id);
	
	
}
