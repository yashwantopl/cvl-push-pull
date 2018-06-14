/**
 * 
 */
package com.capitaworld.service.loans.config;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.service.loans.utils.MultipleJSONObjectHelper;
import com.capitaworld.service.mca.client.McaClient;
import com.capitaworld.service.mca.model.CompaniesHistoryPara;
import com.capitaworld.service.mca.model.CompaniesHistoryRequest;
import com.capitaworld.service.mca.model.McaRequest;
import com.capitaworld.service.mca.model.McaRequestPara;
import com.capitaworld.service.mca.model.SearchCompaniesRequest;
import com.capitaworld.service.mca.model.SearchCompaniesResponse;
import com.capitaworld.service.mca.model.SearchCriteria;

/**
 * @author sanket
 *
 */
@Component
public class MCAAsyncComponent {
	
	@Autowired
	private McaClient mcaClient;
	
	/**
	 * @param cin
	 * @param applicationId
	 * @param userId
	 */
	@Async
	public void callMCA(String cin, Long applicationId, Long userId) throws Exception{
		try {
		McaRequest request = new McaRequest();
		
		SearchCompaniesRequest companiesRequest = new SearchCompaniesRequest();
		
		McaRequestPara para = new McaRequestPara();
		
		SearchCriteria criteria = new SearchCriteria();
		
		String[] cins = {cin};
		criteria.setCins(cins);
		para.setSearchCriteria(criteria);
		companiesRequest.setPara(para);
		request.setSearchCompanies(companiesRequest);
		request.setApplicationId(applicationId);
		SearchCompaniesResponse a = MultipleJSONObjectHelper.getObjectFromMap((Map<String, Object>)mcaClient.searchCompanies(request).getData(),SearchCompaniesResponse.class);

		if(a!=null) {
				if (a.getCompanies() != null && a.getCompanies().length>0) {
					String[] companyIds = { a.getCompanies()[0].getCompanyId() };
					request = new McaRequest();
					CompaniesHistoryRequest companiesHistoryRequest = new CompaniesHistoryRequest();
					CompaniesHistoryPara historyPara = new CompaniesHistoryPara();
					historyPara.setCompanyIds(companyIds);
					companiesHistoryRequest.setPara(historyPara);
					request.setCompaniesHistory(companiesHistoryRequest);
					request.setApplicationId(applicationId);
					request.setUserId(userId);
					mcaClient.getCompanyHistory(request);
				}
			}
		}
		catch (Exception e) {
			throw new Exception();
		}
	}

}
