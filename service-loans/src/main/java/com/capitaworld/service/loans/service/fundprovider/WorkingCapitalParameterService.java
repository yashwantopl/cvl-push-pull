package com.capitaworld.service.loans.service.fundprovider;

import java.util.List;

import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;

public interface WorkingCapitalParameterService {
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest);
	
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id);
	
	public List<WorkingCapitalParameterRequest> getWorkingCapitalParameterListByUserId(Long id);
	
	public Boolean saveMasterFromTempWc(Long mappingId) throws Exception;

	/**
	 * @param capitalParameterRequest
	 * @return
	 */
	public Boolean saveOrUpdateTemp(WorkingCapitalParameterRequest capitalParameterRequest);

	WorkingCapitalParameterRequest getWorkingCapitalParameterTemp(Long id);
}
