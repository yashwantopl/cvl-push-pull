package com.opl.service.loans.service.fundprovider;

import java.util.List;

public interface FSParameterMappingService {

	public Boolean inactiveAndSave(Long applicationId, Integer type, List<Integer> parameterIds);

	public Boolean save(Long applicationId, Integer type, List<Integer> parameterIds);

	public List<Integer> getParameters(Long applicationId, Integer type);
	
}
