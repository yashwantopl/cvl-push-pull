package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.Map;

public interface PLCamReportService {
	public Map<String, Object> getCamReportDetails(Long applicationId, Long productId, boolean isFinalView);
	
	public Map<String, Object> getCamReportDetailsByProposalId(Long applicationId, Long productId, Long proposalId, boolean isFinalView);
}
