/**
 * 
 */
package com.capitaworld.service.loans.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.capitaworld.api.ekyc.model.epf.request.EmployerDefaulterRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerRequest;
import com.capitaworld.api.ekyc.model.epf.request.EmployerVerificationRequest;
import com.capitaworld.client.ekyc.EPFClient;

/**
 * @author sanket
 *
 */
@Component
public class EPFOAsyncComponent {
	
	@Autowired
	private EPFClient epfClient;
	
		
	@Async
	public void callAPI(EmployerRequest employerRequest) {
		try {
		// Defaulters Call
		EmployerRequest req= new EmployerRequest();
		req.setApplicationId(employerRequest.getApplicationId());
		req.setEmployerDefaulterRequest(new EmployerDefaulterRequest(employerRequest.getEmployerDefaulterRequest().getKid()));
		callAllAPIForData(req);
		
		// Employment Verification
		EmployerRequest reqEmpVer= new EmployerRequest();
		reqEmpVer.setApplicationId(employerRequest.getApplicationId());
		reqEmpVer.setEmployerVerificationRequest(new EmployerVerificationRequest(employerRequest.getEmployerVerificationRequest().getEntityId(),employerRequest.getEmployerVerificationRequest().getEmployerName(), employerRequest.getEmployerVerificationRequest().getEmployeeName(),employerRequest.getEmployerVerificationRequest().getMobile(),employerRequest.getEmployerVerificationRequest().getEmailId()));
		callAllAPIForData(reqEmpVer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	private void callAllAPIForData(EmployerRequest employerRequest) {
		try {
			epfClient.getVerifyEmployment(employerRequest);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}

}
