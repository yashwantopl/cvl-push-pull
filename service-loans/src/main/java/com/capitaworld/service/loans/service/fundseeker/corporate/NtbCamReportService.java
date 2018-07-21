package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.Map;

public interface NtbCamReportService {

	public Map<String, Object> getNtbCamReport(Long applicationId, Long productId,Long userId,boolean isFinalView);
}
