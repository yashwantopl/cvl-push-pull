package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.Map;

public interface HLCamReportService {
		
	public Map<String, Object> getCamReportDetailsByProposalId(Long applicationId, Long productId, Long proposalId, boolean isFinalView);
	
	public Map<String, Object> getHLBankStatementAnalysisReport(Long applicationId, Long productId);
	
	public Map<String ,Object> getDataForApplicationForm(Long applicationId, Long productId, Long proposalId);
	
}
