package com.opl.service.loans.service.fundseeker.retail.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opl.profile.api.model.CommonResponse;
import com.opl.profile.api.model.ProfileVerMapRequest;
import com.opl.profile.client.ProfileClient;
import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opl.mudra.api.analyzer.model.common.AnalyzerResponse;
import com.opl.mudra.api.analyzer.model.common.Data;
import com.opl.mudra.api.analyzer.model.common.ReportRequest;
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

	@Autowired
	private ProfileClient profileClient;

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

		Long profileMappingId =  loanApplicationRepository.getProfileMappingId(applicationId);
		ProfileVerMapRequest profileObj = new ProfileVerMapRequest();
		if (!CommonUtils.isObjectNullOrEmpty(profileMappingId)) {
			CommonResponse profileRequest = profileClient.getProfileVersionDetailsByPrimaryId(profileMappingId);
			try {
				profileObj = MultipleJSONObjectHelper.getObjectFromMap(((Map)profileRequest.getData()),ProfileVerMapRequest.class);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		//PERFIOS API DATA (BANK STATEMENT ANALYSIS)
		map.put("bankRelatedData" , getBankRelatedData(profileObj.getBsId(), userId));
		
		return map;
	}
	
	public Object getBankRelatedData(Long bsId ,Long userId){
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setBsMasterId(bsId);
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
