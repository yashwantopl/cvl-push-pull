package com.opl.service.loans.service.fundseeker.retail.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.analyzer.model.common.AnalyzerResponse;
import com.opl.mudra.api.analyzer.model.common.Data;
import com.opl.mudra.api.loans.model.common.ReportRequest;
import com.opl.mudra.api.loans.model.corporate.CorporateApplicantRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.client.analyzer.AnalyzerClient;
import com.opl.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.opl.service.loans.service.fundseeker.corporate.CorporateApplicantService;
import com.opl.service.loans.service.fundseeker.corporate.impl.CamReportPdfDetailsServiceImpl;
import com.opl.service.loans.service.fundseeker.retail.HLCamReportService;

@Service
@Transactional
public class HLCamReportServiceImpl implements HLCamReportService{

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	@Autowired
	private CorporateApplicantService corporateApplicantService;
	
	@Autowired
	private AnalyzerClient analyzerClient;

	private static final Logger logger = LoggerFactory.getLogger(CamReportPdfDetailsServiceImpl.class);
	
	
	@Override
	public Map<String, Object> getHLBankStatementAnalysisReport(Long applicationId, Long productId) {
		Map<String, Object> map = new HashMap<String, Object>();
		Long userId = loanApplicationRepository.getUserIdByApplicationId(applicationId);
		CorporateApplicantRequest corporateApplicantRequest = corporateApplicantService.getCorporateApplicant(applicationId);
		try {
			if(corporateApplicantRequest != null) {
				map.put("orgName", StringEscapeUtils.escapeXml(corporateApplicantRequest.getOrganisationName()));
			}
		} catch (Exception e1) {
			logger.error(CommonUtils.EXCEPTION,e1);
		}
		
		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		map.put("bankRelatedData" , getBankRelatedData(applicationId, userId));
		
		return map;
	}
	
	public Object getBankRelatedData(Long applicationId ,Long userId){
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		List<Data> datas = new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> listhashMap = (List<HashMap<String, Object>>) analyzerResponse.getData();

			if (!CommonUtils.isListNullOrEmpty(listhashMap)) {	
				for (HashMap<String, Object> rec : listhashMap) {
					Data data = MultipleJSONObjectHelper.getObjectFromMap(rec, Data.class);
					datas.add(data);
				}
				return !datas.isEmpty() ? CommonUtils.printFields(datas , null) : null;
			}
		} catch (Exception e) {
			logger.error("Error while getting perfios data : ",e);
		}
		return null;
	}
	
}
