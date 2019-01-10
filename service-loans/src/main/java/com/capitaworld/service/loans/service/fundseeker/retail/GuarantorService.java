package com.capitaworld.service.loans.service.fundseeker.retail;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequestOld;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;

import java.util.List;

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
