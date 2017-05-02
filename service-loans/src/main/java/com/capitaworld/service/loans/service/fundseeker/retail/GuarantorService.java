package com.capitaworld.service.loans.service.fundseeker.retail;

import java.util.List;

import com.capitaworld.service.loans.model.retail.GuarantorRequest;

public interface GuarantorService {
	public boolean save(GuarantorRequest guarantorRequest, Long applicationId);

	public GuarantorRequest get(Long id, Long applicationId);
	
	public List<GuarantorRequest> getList(Long applicationId);
}
