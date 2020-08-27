package com.opl.service.loans.service.fundseeker.corporate;

import java.util.Map;

import com.opl.mudra.api.payment.exception.GatewayException;
import com.opl.mudra.api.payment.model.GatewayRequest;

public interface PaymentServiceLoans {

	public Map<String, Object> savePaymentDetail(GatewayRequest gatewayRequest) throws GatewayException;
	
	public Boolean checkingPanAndLoanTypeForSkipPayment(Long applicationId , Long fpProductId);
	
	public Map<String, Object> checkTypeOfSkipPayment(GatewayRequest gatewayRequest);
	
	public String skipPayment(GatewayRequest gatewayRequest);
	
	public Map<String , Object> getInPrincipleDetail(Long applicationId , Long proposalId ,String skipType);
	
	public Map<String , Object> updatePaymentAfterSuccess(GatewayRequest gatewayRequest);
	
	public Map<String, Object> sendAllMailToFSAndFP(GatewayRequest gatewayRequest,Long proposalId,Boolean forFs,Boolean forFp);
}
