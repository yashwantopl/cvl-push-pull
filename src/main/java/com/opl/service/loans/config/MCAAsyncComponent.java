/**
 * 
 */
package com.opl.service.loans.config;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.LoanApplicationRequest;
import com.opl.mudra.api.loans.utils.CommonUtils;
import com.opl.mudra.api.loans.utils.MultipleJSONObjectHelper;
import com.opl.mudra.api.mca.CompaniesHistoryPara;
import com.opl.mudra.api.mca.CompaniesHistoryRequest;
import com.opl.mudra.api.mca.McaRequest;
import com.opl.mudra.api.mca.McaRequestPara;
import com.opl.mudra.api.mca.SearchCompaniesRequest;
import com.opl.mudra.api.mca.SearchCompaniesResponse;
import com.opl.mudra.api.mca.SearchCriteria;
import com.opl.mudra.client.mca.McaClient;
import com.opl.service.loans.service.fundseeker.corporate.LoanApplicationService;

/**
 * @author sanket
 *
 */
@Component
public class MCAAsyncComponent {
	
	@Autowired
	private McaClient mcaClient;
	
	@Autowired
	private LoanApplicationService loanService;
	
	private static final Logger logger = LoggerFactory.getLogger(MCAAsyncComponent.class.getName());
	/**
	 * @param cin
	 * @param applicationId
	 * @param userId
	 */
	
	private void callMCA(String cin, Long applicationId, Long userId) throws LoansException {
		try {
		McaRequest request = new McaRequest();
		
		SearchCompaniesRequest companiesRequest = new SearchCompaniesRequest();
		
		McaRequestPara para = new McaRequestPara();
		logger.info("Initiated MCA Search Call");
		SearchCriteria criteria = new SearchCriteria();
		
		String[] cins = {cin};
		criteria.setCins(cins);
		para.setSearchCriteria(criteria);
		companiesRequest.setPara(para);
		request.setSearchCompanies(companiesRequest);
		request.setApplicationId(applicationId);
		SearchCompaniesResponse a = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>)mcaClient.searchCompanies(request).getData(),SearchCompaniesResponse.class);
		logger.info("End of MCA Search Call");
		
		if(a!=null && a.getCompanies() != null && a.getCompanies().length>0 ) {
					String[] companyIds = { a.getCompanies()[0].getCompanyId() };
					request = new McaRequest();
					CompaniesHistoryRequest companiesHistoryRequest = new CompaniesHistoryRequest();
					CompaniesHistoryPara historyPara = new CompaniesHistoryPara();
					historyPara.setCompanyIds(companyIds);
					companiesHistoryRequest.setPara(historyPara);
					request.setCompaniesHistory(companiesHistoryRequest);
					request.setApplicationId(applicationId);
					request.setUserId(userId);
					logger.info("Initiated MCA get Company History Call");
					mcaClient.getCompanyHistory(request);
					logger.info("End of  MCA get Company History Call");
					LoanApplicationRequest loanRequest = new LoanApplicationRequest();
					loanRequest.setUserId(userId);
					loanRequest.setId(applicationId);
					loanRequest.setMcaCompanyId(companyIds[0]);
					loanRequest.setIsMca(true);
					logger.info("Initiated MCA data save Call");
					loanService.updateLoanApplication(loanRequest);
					logger.info("End of MCA data save Call");
			}
		}
		catch (Exception e) {
			throw new LoansException(e);
		}
	}
	
	@Async
	public void callMCAForData(String cin, Long applicationId, Long userId) {
		try {
			callMCA(cin, applicationId, userId);
		} catch (Exception e) {
			logger.error(CommonUtils.EXCEPTION,e);
		}
	}

}
