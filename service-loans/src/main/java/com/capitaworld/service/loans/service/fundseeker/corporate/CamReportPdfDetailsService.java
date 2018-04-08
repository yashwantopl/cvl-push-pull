package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.Map;

public interface CamReportPdfDetailsService {
	
	public Map<String, Object> getCamReportFinalDetails(Long applicationId, Long productId);
	
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId);
}
