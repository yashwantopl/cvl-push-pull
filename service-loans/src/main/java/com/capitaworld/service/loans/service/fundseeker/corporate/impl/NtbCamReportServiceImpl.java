package com.capitaworld.service.loans.service.fundseeker.corporate.impl;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.api.eligibility.model.CLEligibilityRequest;
import com.capitaworld.api.eligibility.model.EligibililityRequest;
import com.capitaworld.api.eligibility.model.EligibilityResponse;
import com.capitaworld.client.eligibility.EligibilityClient;
import com.capitaworld.service.analyzer.client.AnalyzerClient;
import com.capitaworld.service.analyzer.model.common.AnalyzerResponse;
import com.capitaworld.service.analyzer.model.common.Data;
import com.capitaworld.service.analyzer.model.common.ReportRequest;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.corporate.CorporateDirectorIncomeRequest;
import com.capitaworld.service.loans.model.corporate.FundSeekerInputRequestResponse;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.LoanApplicationRepository;
import com.capitaworld.service.loans.service.fundseeker.corporate.CorporateDirectorIncomeService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NTBService;
import com.capitaworld.service.loans.service.fundseeker.corporate.NtbCamReportService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.matchengine.MatchEngineClient;
import com.capitaworld.service.matchengine.model.MatchDisplayResponse;
import com.capitaworld.service.matchengine.model.MatchRequest;

@Service
@Transactional
public class NtbCamReportServiceImpl implements NtbCamReportService{

	@Autowired
    private CorporateDirectorIncomeService corporateDirectorIncomeService;
	
	@Autowired
    private NTBService ntbService;
	
	@Autowired
	private EligibilityClient eligibilityClient;
	
	@Autowired
	private MatchEngineClient matchEngineClient;
	
	@Autowired
	private AnalyzerClient analyzerClient;
	
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	
	@Autowired
	private LoanApplicationRepository loanApplicationRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(NtbCamReportServiceImpl.class);
	
	@Override
	public Map<String, Object> getNtbCamReport(Long applicationId, Long productId, Long userId, boolean isFinalView) {
		
		logger.info("Entering into NTB CAM REPORT service=========> {}" +applicationId+" "+productId+" "+isFinalView);
		Map<String, Object> map = new HashMap<String, Object>();
		
		// GET DIRECTOR BACKGROUND DETAILS
		
		try {
			List<Map<String, Object>> directorBackgroundDetails = corporateDirectorIncomeService
					.getDirectorBackGroundDetails(applicationId);
			map.put("directorBackgroundDetails", directorBackgroundDetails);

		} catch (Exception e) {
			logger.error("Problem to get Data of Director's Background=========> {}", e);
		}
		       
    	
		// GET DIRECTOR INCOME DETAILS
		
		try {
			List<CorporateDirectorIncomeRequest> directorIncomeDetails = corporateDirectorIncomeService
					.getDirectorIncomeDetails(applicationId);
			map.put("directorIncomeDetails", directorIncomeDetails);

		} catch (Exception e) {
			logger.error("Problem to get Director's Income Details===========> {}", e);
		}
		        	
	
		// GET OTHER DETAILS
		
		try {
			
			FundSeekerInputRequestResponse fundSeekerInputRequestResponse = ntbService.getOthersDetail(applicationId);
			map.put("otherDetails", fundSeekerInputRequestResponse);
			
		} catch (Exception e) {
			logger.error("Problem to get Other Details===========> {}", e);
		}
		
		
		//ELIGIBILITY DATA (ASSESSMENT TO LIMITS)
				
		try {
			TermLoanParameter termLoanParameter = termLoanParameterRepository.getById(productId);
			Long assessmentId = termLoanParameter.getAssessmentMethodId().longValue();
			if(!CommonUtils.isObjectNullOrEmpty(assessmentId)) {
				map.put("assessmentId", assessmentId);
			}
			EligibililityRequest eligibilityReq=new EligibililityRequest();
			eligibilityReq.setApplicationId(applicationId);
			eligibilityReq.setFpProductMappingId(productId);
			EligibilityResponse eligibilityResp= eligibilityClient.corporateLoanData(eligibilityReq);
			logger.info("********************Eligibility data**********************"+eligibilityResp.getData().toString());
			map.put("assLimits",convertToDoubleForXml(MultipleJSONObjectHelper.getObjectFromMap((LinkedHashMap<String, Object>)eligibilityResp.getData(), CLEligibilityRequest.class), new HashMap<>()));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("Error while getting Eligibility data");
		}		
				
		
		//MATCHES RESPONSE
		
		try {
			MatchRequest matchRequest = new MatchRequest();
			matchRequest.setApplicationId(applicationId);
			matchRequest.setProductId(productId);
			MatchDisplayResponse matchResponse= matchEngineClient.displayMatchesOfCorporate(matchRequest);
			map.put("matchesResponse", !CommonUtils.isListNullOrEmpty(matchResponse.getMatchDisplayObjectList()) ? printFields(matchResponse.getMatchDisplayObjectList()) : " ");
		}
		catch (Exception e) {
			logger.info("Error while getting Match Engine data");
			e.printStackTrace();
		}
		
		//BANK STATEMENT
		ReportRequest reportRequest = new ReportRequest();
		reportRequest.setApplicationId(applicationId);
		reportRequest.setUserId(userId);
		List<Data> datas=new ArrayList<>();
		try {
			AnalyzerResponse analyzerResponse = analyzerClient.getDetailsFromReportForCam(reportRequest);
			List<HashMap<String, Object>> hashMaps=(List<HashMap<String, Object>>) analyzerResponse.getData();
				if(!CommonUtils.isListNullOrEmpty(hashMaps)){
					for(HashMap<String,Object> hashMap:hashMaps){
						Data data = MultipleJSONObjectHelper.getObjectFromMap(hashMap, Data.class);
						datas.add(data);
					}
			}
			map.put("bankStatementData", datas);
		}catch (Exception e) {
			logger.info("Error while getting perfios data");
			e.printStackTrace();
		}
				
		return map;
	}
	
	/*********************************************************CAM UTILS****************************************************************/
	
	public static Object convertToDoubleForXml (Object obj, Map<String, Object>data) throws Exception {
		Field[] fields = obj.getClass().getDeclaredFields();
		 for(Field field : fields) {
			 field.setAccessible(true);
             Object value = field.get(obj);
             if(data != null) {
            	 data.put(field.getName(), value);
             }
             if(!CommonUtils.isObjectNullOrEmpty(value)) {
            	 if(value instanceof Double){
                	 if(!Double.isNaN((Double)value)) {
                		 DecimalFormat decim = new DecimalFormat("0.00");
                    	 value = Double.parseDouble(decim.format(value));
                    	 if(data != null) {
                    		 logger.info("eligibility data================>"+field.getName());
                    		 value = decim.format(value);
                    		 data.put(field.getName(), value);
                    	 }else {
                    		 field.set(obj,value);                    		 
                    	 }
                	 }
                 }
             }
		 }
		 if(data != null) {
			 return data;
		 }
		return obj;
	}
	
	public static Object printFields(Object obj) throws Exception {
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}else {
			escapeXml(obj);
		}
		 return obj;
	}
	
	public static Object escapeXml(Object obj) throws Exception{
		if(obj instanceof List) {
			List<?> lst = (List)obj;
			for(Object o : lst) {
				escapeXml(o);
			}
		}else if(obj instanceof Map) {
			Map<Object, Object> map = (Map)obj;
			for(Map.Entry<Object, Object> setEntry : map.entrySet()) {
				escapeXml(setEntry.getValue());
			}
		}
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value instanceof String) {
				String value1 = (String) field.get(obj);
				String a = StringEscapeUtils.escapeXml(value1.toString());
				value = a;
				field.set(obj, value);
			}else {
				continue;
			}
		}
		return obj;
    }

}
