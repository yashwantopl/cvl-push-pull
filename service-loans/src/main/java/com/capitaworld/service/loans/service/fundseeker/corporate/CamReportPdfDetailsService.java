package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.Map;

public interface CamReportPdfDetailsService {
	
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId, boolean isFinalView);
}
