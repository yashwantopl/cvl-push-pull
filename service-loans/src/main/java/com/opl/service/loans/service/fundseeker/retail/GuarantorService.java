package com.opl.service.loans.service.fundseeker.retail;

import java.util.List;

import com.opl.mudra.api.loans.exception.LoansException;
import com.opl.mudra.api.loans.model.retail.FinalCommonRetailRequestOld;
import com.opl.mudra.api.loans.model.retail.GuarantorRequest;
import com.opl.mudra.api.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.opl.mudra.api.loans.model.teaser.primaryview.RetailProfileViewResponse;

public interface GuarantorService {
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId, Long userId) throws LoansException;

	public GuarantorRequest get(Long userId, Long applicationId, Long id) throws LoansException;
	
	public List<GuarantorRequest> getList(Long applicationId,Long userId) throws LoansException;
	
	public boolean saveFinal(FinalCommonRetailRequestOld applicantRequest, Long userId) throws LoansException;

	public FinalCommonRetailRequestOld getFinal(Long userId, Long applicationId, Long id) throws LoansException;

	public List<RetailProfileViewResponse> getGuarantorServiceResponse(Long applicantId, Long userId,int productId) throws LoansException;
	
	public List<RetailFinalViewCommonResponse> getGuarantorFinalViewResponse(Long applicantId, Long userId,int productId) throws LoansException;
	
	public List<Long> getGuarantorIds(Long userId, Long applicationId) throws LoansException;
	
	public Long getApplicantIdById(Long id) throws LoansException;
	
}
