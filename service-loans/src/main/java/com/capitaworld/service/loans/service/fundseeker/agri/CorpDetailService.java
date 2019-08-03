package com.capitaworld.service.loans.service.fundseeker.agri;

import java.util.List;

import com.capitaworld.service.loans.model.agri.CorpDetailRequest;

public interface CorpDetailService {

	public Boolean save(CorpDetailRequest corpDetailRequest,Long applicationId,Long userId);
	
	public Boolean save(List<CorpDetailRequest> corpDetailRequest,Long applicationId,Long userId);
	
	public Boolean inActiveExistingAndsave(List<CorpDetailRequest> corpDetailRequest,Long applicationId,Long userId);
	
	public List<CorpDetailRequest> getList(Long applicationId);
	
}
