package com.opl.service.loans.service.fundseeker.corporate;

import java.util.List;
import java.util.Map;

import com.opl.mudra.api.gst.model.model.CAMGSTData;
import com.opl.mudra.api.rating.model.FinancialInputRequest;

public interface CamReportPdfDetailsService {
	
	public Map<String, Object> getCamReportPrimaryDetails(Long applicationId, Long productId,Long proposalId, boolean isFinalView);
	public Map<String, Object> getBankStatementAnalysisReport(Long applicationId, Long productId);
	
	public List<CAMGSTData> getGstDetails(Long applicationId ,Long userId);
	
	public Map<String ,Object> getGstReportData(String panNo);
	
	public FinancialInputRequest finaForCam(Long aplicationId,Long proposalId);
	
	//for application Form (MSME)
	public Map<String, Object> getDetailsForApplicationForm(Long applicationId, Long productId,Long proposalId);
	
	public String getCamVersionForBSStandalone(String type);
	public byte[] getCamReportPrimaryDetailsByteArray(Long applicationId, Long productId,Long proposalId, boolean isFinalView,Integer loanTypeId );
	public byte[] getApplicationForm(Long applicationId, Long productId,Long proposalId,Integer loanTypeId);
}
