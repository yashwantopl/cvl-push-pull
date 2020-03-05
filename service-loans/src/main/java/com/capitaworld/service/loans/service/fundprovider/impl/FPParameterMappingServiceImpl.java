
package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.FPParameterMapping;
import com.capitaworld.service.loans.domain.fundprovider.FPParameterMappingTemp;
import com.capitaworld.service.loans.model.common.FPParameterMappingRequest;
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

	@Override
	public Boolean inactiveAndSaveWithObject(Long fpProductId, Integer type,List<FPParameterMappingRequest> fpParameterMappingRequests) {
		fPParameterMappingRepository.inactiveMapping(fpProductId, type);
		return saveWithObject(fpProductId,type,fpParameterMappingRequests);
	}

	@Override
	public Boolean saveWithObject(Long fpProductId, Integer type, List<FPParameterMappingRequest> fpParameterMappingRequests) {
		if(CommonUtils.isListNullOrEmpty(fpParameterMappingRequests)) {
			return null;
		}
		FPParameterMapping fpParameterMapping = null;
		for(FPParameterMappingRequest fpParameterMappingRequest : fpParameterMappingRequests) {
			fpParameterMapping = new FPParameterMapping();
			BeanUtils.copyProperties(fpParameterMappingRequest, fpParameterMapping);
			fpParameterMapping.setFpProductMappingId(fpProductId);
			fpParameterMapping.setType(type);
			fpParameterMapping.setIsActive(true);
			fPParameterMappingRepository.save(fpParameterMapping);
		}
		return true;
	}

	@Override
	public List<FPParameterMappingRequest> getParametersWithObject(Long fpProductId, Integer type) {
		List<FPParameterMapping> masterList = fPParameterMappingRepository.findByFpProductMappingIdAndTypeAndIsActiveIsTrue(fpProductId, type);
		if(CommonUtils.isListNullOrEmpty(masterList)){
			return Collections.emptyList();
		}
		List<FPParameterMappingRequest> response = new ArrayList<>(masterList.size());
		FPParameterMappingRequest res = null;
		for(FPParameterMapping fpParameterMapping : masterList){
			res = new FPParameterMappingRequest();
			BeanUtils.copyProperties(fpParameterMapping, res);
			response.add(res);
		}
		return response;
	}

	@Override
	public List<FPParameterMappingRequest> getParametersTempWithObject(Long fpProductId, Integer type) {
		List<FPParameterMappingTemp> tempList = fpParameterMappingTempRepository.findByFpProductMappingIdAndTypeAndIsActiveIsTrue(fpProductId, type);
		if(CommonUtils.isListNullOrEmpty(tempList)){
			return Collections.emptyList();
		}
		List<FPParameterMappingRequest> response = new ArrayList<>(tempList.size());
		FPParameterMappingRequest res = null;
		for(FPParameterMappingTemp fpParameterMappingTemp : tempList){
			res = new FPParameterMappingRequest();
			BeanUtils.copyProperties(fpParameterMappingTemp, res);
			response.add(res);
		}
		return response;
	}

	@Override
	public Boolean inactiveAndSaveTempWithObject(Long fpProductId, Integer type,List<FPParameterMappingRequest> fpParameterMappingRequests) {
		fpParameterMappingTempRepository.inactiveMapping(fpProductId, type);
		return saveTempWithObject(fpProductId,type,fpParameterMappingRequests);
	}

	@Override
	public Boolean saveTempWithObject(Long fpProductId, Integer type,List<FPParameterMappingRequest> fpParameterMappingRequests) {
		if(CommonUtils.isListNullOrEmpty(fpParameterMappingRequests)) {
			return null;
		}
		FPParameterMappingTemp fpParameterMapping = null;
		for(FPParameterMappingRequest fPParameterMappingRequest : fpParameterMappingRequests) {
			fpParameterMapping = new FPParameterMappingTemp();
			BeanUtils.copyProperties(fPParameterMappingRequest, fpParameterMapping);
			fpParameterMapping.setFpProductMappingId(fpProductId);
			fpParameterMapping.setType(type);
			fpParameterMapping.setIsActive(true);
			fpParameterMappingTempRepository.save(fpParameterMapping);
		}
		return true;
	}

}
