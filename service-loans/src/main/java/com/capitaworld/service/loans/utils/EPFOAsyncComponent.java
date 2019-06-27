/**
 * 
 */
package com.capitaworld.service.loans.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.api.ekyc.model.epf.request.EmployerDefaulterRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerVerificationRequest;
import com.capitaworld.client.ekyc.EPFClient;
import com.capitaworld.service.loans.service.fundseeker.corporate.impl.LoanApplicationServiceImpl;

/**
 * @author sanket
 *
 */
@Component
public class EPFOAsyncComponent {
	
	private static final Logger logger = LoggerFactory.getLogger(EPFOAsyncComponent.class.getName());
	
	@Autowired
	private EPFClient epfClient;
	
	 @Value("${cw.isepf.active}")
	private String isEPFActive;
		
	@Async
	public void callAPI(EmployerRequest employerRequest) {
		try {
		    if("Y".equals(isEPFActive)) {
		// Defaulters Call
		EmployerRequest req= new EmployerRequest();
		req.setApplicationId(employerRequest.getApplicationId());
		req.setCoAppId(employerRequest.getCoAppId());
		req.setEmployerDefaulterRequest(new EmployerDefaulterRequest(employerRequest.getEmployerDefaulterRequest().getKid()));
		callAllAPIForData(req);
		
		// Employment Verification
		req = new EmployerRequest();
		req.setApplicationId(employerRequest.getApplicationId());
		req.setCoAppId(employerRequest.getCoAppId());
		req.setEmployerVerificationRequest(new EmployerVerificationRequest(employerRequest.getEmployerVerificationRequest().getEntityId(),employerRequest.getEmployerVerificationRequest().getEmployerName(), employerRequest.getEmployerVerificationRequest().getEmployeeName(),employerRequest.getEmployerVerificationRequest().getMobile(),employerRequest.getEmployerVerificationRequest().getEmailId()));
		callAllAPIForData(req);
		    }
		}
		catch (Exception e) {
			logger.error("Erroe while callAPI",e);
		}
		
	}
	
	
	
	private void callAllAPIForData(EmployerRequest employerRequest) {
		try {
			epfClient.getVerifyEmployment(employerRequest);
		}
		catch (Exception e) {
			logger.error("Erroe while callAllAPIForData",e);
		}
		
		
		
	}

}
