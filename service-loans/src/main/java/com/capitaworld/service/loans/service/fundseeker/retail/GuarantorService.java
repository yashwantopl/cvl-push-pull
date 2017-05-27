package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;
import com.capitaworld.service.loans.model.teaser.finalview.RetailFinalViewCommonResponse;
import com.capitaworld.service.loans.model.teaser.primaryview.RetailProfileViewResponse;

public interface GuarantorService {
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId, Long userId) throws Exception;

	public GuarantorRequest get(Long userId, Long applicationId, Long id) throws Exception;
	
	public List<GuarantorRequest> getList(Long applicationId,Long userId) throws Exception;
	
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest,Long userId) throws Exception;

	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId, Long id) throws Exception;

	public List<RetailProfileViewResponse> getGuarantorServiceResponse(Long applicantId, Long userId) throws Exception;
	
	public List<RetailFinalViewCommonResponse> getGuarantorFinalViewResponse(Long applicantId, Long userId) throws Exception;
	
}
