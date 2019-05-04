
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.FPParameterMapping;
import com.capitaworld.service.loans.domain.fundprovider.FPParameterMappingTemp;
import com.capitaworld.service.loans.repository.fundprovider.FPParameterMappingRepository;
import com.capitaworld.service.loans.repository.fundprovider.FPParameterMappingTempRepository;
import com.capitaworld.service.loans.service.fundprovider.FPParameterMappingService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
@Transactional
public class FPParameterMappingServiceImpl implements FPParameterMappingService {
	private static final Logger logger = LoggerFactory.getLogger(FPParameterMappingServiceImpl.class);

	@Autowired
	private FPParameterMappingRepository fPParameterMappingRepository;
	
	@Autowired
	private FPParameterMappingTempRepository fpParameterMappingTempRepository; 
	
	@Override
	public Boolean inactiveAndSave(Long fpProductId, Integer type, List<Integer> parameterIds) {
		fPParameterMappingRepository.inactiveMapping(fpProductId, type);
		return save(fpProductId,type,parameterIds);
	}

	@Override
	public Boolean save(Long fpProductId, Integer type, List<Integer> parameterIds) {
		if(CommonUtils.isListNullOrEmpty(parameterIds)) {
			return null;
		}
		FPParameterMapping fpParameterMapping = null;
		for(Integer parameterId : parameterIds) {
			fpParameterMapping = new FPParameterMapping();
			fpParameterMapping.setFpProductMappingId(fpProductId);
			fpParameterMapping.setType(type);
			fpParameterMapping.setParameterId(parameterId);
			fpParameterMapping.setIsActive(true);
			fPParameterMappingRepository.save(fpParameterMapping);
		}
		return true;
	}
	
	@Override
	public List<Integer> getParameters(Long fpProductId, Integer type) {
		return fPParameterMappingRepository.getParametersByFpProductIdAndType(fpProductId, type);
	}
	
	
	@Override
	public Boolean inactiveAndSaveTemp(Long fpProductId, Integer type, List<Integer> parameterIds) {
		fpParameterMappingTempRepository.inactiveMapping(fpProductId, type);
		return saveTemp(fpProductId,type,parameterIds);
	}

	@Override
	public Boolean saveTemp(Long fpProductId, Integer type, List<Integer> parameterIds) {
		if(CommonUtils.isListNullOrEmpty(parameterIds)) {
			return null;
		}
		FPParameterMappingTemp fpParameterMapping = null;
		for(Integer parameterId : parameterIds) {
			fpParameterMapping = new FPParameterMappingTemp();
			fpParameterMapping.setFpProductMappingId(fpProductId);
			fpParameterMapping.setType(type);
			fpParameterMapping.setParameterId(parameterId);
			fpParameterMapping.setIsActive(true);
			fpParameterMappingTempRepository.save(fpParameterMapping);
		}
		return true;
	}
	

	@Override
	public List<Integer> getParametersTemp(Long fpProductId, Integer type) {
		return fpParameterMappingTempRepository.getParametersByFpProductIdAndType(fpProductId, type);
	}

}
