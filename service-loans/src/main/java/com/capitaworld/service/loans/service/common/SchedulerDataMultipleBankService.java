package com.capitaworld.service.loans.service.common;

import java.util.List;

import com.capitaworld.service.loans.domain.SchedulerDataMultipleBank;
import com.capitaworld.service.loans.model.SchedulerDataMultipleBankRequest;

public interface SchedulerDataMultipleBankService {
	
	public Boolean saveToSchedular(SchedulerDataMultipleBankRequest bank) throws Exception;
	
	public List<SchedulerDataMultipleBank> gateDataOfSchedular(Long applicationId) throws Exception;

}
