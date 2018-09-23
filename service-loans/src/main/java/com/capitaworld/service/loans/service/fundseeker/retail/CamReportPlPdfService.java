package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.Map;

public interface CamReportPlPdfService {
	public Map<String, Object> getCamReportPlPrimaryDetails(Long applicationId, Long productId, boolean isFinalView);
}
