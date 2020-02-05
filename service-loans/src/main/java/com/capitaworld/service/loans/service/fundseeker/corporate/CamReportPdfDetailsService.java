package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.capitaworld.service.gst.model.CAMGSTData;
import com.capitaworld.service.rating.model.FinancialInputRequest;

public interface CamReportPdfDetailsService {
	
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId,Long proposalId, boolean isFinalView);
	public Map<String, Object> getBankStatementAnalysisReport(Long applicationId, Long productId);
	
	public List<CAMGSTData> getGstDetails(Long applicationId ,Long userId);
	
	public Map<String ,Object> getGstReportData(String panNo);
	
	public FinancialInputRequest finaForCam(Long aplicationId,Long proposalId);
	
	//for application Form (MSME)
	public Map<String, Object> getDetailsForApplicationForm(Long applicationId, Long productId,Long proposalId);
}
