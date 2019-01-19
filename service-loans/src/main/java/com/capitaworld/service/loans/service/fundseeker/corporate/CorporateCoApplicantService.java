package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.exceptions.LoansException;
import com.capitaworld.service.loans.model.corporate.CorporateCoApplicantRequest;

public interface CorporateCoApplicantService {
	public boolean save(CorporateCoApplicantRequest applicantRequest, Long applicationId, Long userId) throws LoansException;

	public CorporateCoApplicantRequest get(Long userId, Long applicationId, Long id) throws LoansException;
	
	public List<CorporateCoApplicantRequest> getList(Long applicationId, Long userId) throws LoansException;
	
	public List<Long> getCoAppIds(Long applicationId,Long userId) throws LoansException;
	
	public Long getApplicantIdById(Long id) throws LoansException;
}
