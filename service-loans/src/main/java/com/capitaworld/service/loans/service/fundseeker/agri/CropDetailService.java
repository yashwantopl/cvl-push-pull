package com.capitaworld.service.loans.service.fundseeker.agri;

import java.util.List;

import com.capitaworld.service.loans.model.agri.CropDetailRequest;

public interface CropDetailService {

	public Boolean save(CropDetailRequest corpDetailRequest,Long applicationId,Long userId);
	
	public Boolean save(List<CropDetailRequest> corpDetailRequest,Long applicationId,Long userId);
	
	public Boolean inActiveExistingAndsave(List<CropDetailRequest> corpDetailRequest,Long applicationId,Long userId);
	
	public List<CropDetailRequest> getList(Long applicationId);
	
}
