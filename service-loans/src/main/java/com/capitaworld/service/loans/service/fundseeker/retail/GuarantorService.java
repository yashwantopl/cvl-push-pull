package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;

public interface GuarantorService {
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId, Long userId) throws Exception;

	public GuarantorRequest get(Long userId, Long applicationId, Long id) throws Exception;
	
	public List<GuarantorRequest> getList(Long applicationId,Long userId) throws Exception;
	
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest,Long userId) throws Exception;

	public FinalCommonRetailRequest getFinal(Long userId, Long applicationId, Long id) throws Exception;
}
