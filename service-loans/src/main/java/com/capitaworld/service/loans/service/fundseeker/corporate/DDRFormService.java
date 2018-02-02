package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.ddr.DDRCMACalculationResponse;
import com.capitaworld.service.loans.model.ddr.DDRFormDetailsRequest;

public interface DDRFormService {

	public void saveDDRForm(DDRFormDetailsRequest ddrFormDetailsRequest) throws Exception;
	
	public DDRFormDetailsRequest get(Long id);
	
	public List<DDRCMACalculationResponse> getCMAandCOActDetails(Long applicationId);
	
	
}
