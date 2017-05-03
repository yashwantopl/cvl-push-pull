package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.FinalCommonRetailRequest;
import com.capitaworld.service.loans.model.retail.GuarantorRequest;

public interface GuarantorService {
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId) throws Exception;

	public GuarantorRequest get(Long id, Long applicationId) throws Exception;
	
	public List<GuarantorRequest> getList(Long applicationId) throws Exception;
	
	public boolean saveFinal(FinalCommonRetailRequest applicantRequest) throws Exception;

	public FinalCommonRetailRequest getFinal(Long id, Long applicationId) throws Exception;
}
