package com.capitaworld.service.loans.service.fundseeker.corporate;

import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest);
	
	public DDRFormDetailsRequest get(Long id);
	
}
