package com.capitaworld.service.loans.service.fundseeker.corporate;

import java.util.List;

import com.capitaworld.service.loans.model.FrameRequest;
import com.capitaworld.service.loans.model.LoanApplicationRequest;

public interface LoanApplicationService {

	public boolean saveOrUpdate(FrameRequest commonRequest);
	
	public LoanApplicationRequest get(Long id);
	
	public boolean inActive(Long id);
	
	public List<LoanApplicationRequest> getList(Long userId);	
	
}
