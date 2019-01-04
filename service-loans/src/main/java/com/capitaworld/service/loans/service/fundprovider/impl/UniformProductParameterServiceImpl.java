package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamter;
import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;
import com.capitaworld.service.loans.repository.fundprovider.UniformProductParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.UniformProductParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.loans.utils.CommonUtils.LoanType;

@Service
public class UniformProductParameterServiceImpl implements UniformProductParameterService {

	@Autowired
	private UniformProductParameterRepository uniformProductParameterRepository; 
	
	@Override
	@Transactional
	public Boolean saveOrUpdate(UniformProductParamterRequest productParamterRequest) {
		UniformProductParamter uniformProductParamter = uniformProductParameterRepository.findById(productParamterRequest.getId());
		if(CommonUtils.isObjectNullOrEmpty(uniformProductParamter)){
			uniformProductParamter = new UniformProductParamter();
			uniformProductParamter.setCreatedBy(productParamterRequest.getUserId());
			uniformProductParamter.setCreatedDate(new Date());
		}else{
			uniformProductParamter.setModifiedBy(productParamterRequest.getUserId());
			uniformProductParamter.setModifiedDate(new Date());
		}
		BeanUtils.copyProperties(productParamterRequest, uniformProductParamter, "id","createdBy","createdDate","modifiedDate","modifiedBy");
		uniformProductParamter.setUserOrgId(productParamterRequest.getUserOrgId());
		uniformProductParamter.setBusinessTypeId(CommonUtils.BusinessType.ONE_PAGER_ELIGIBILITY_EXISTING_BUSINESS.getId().longValue());
		uniformProductParamter.setUserId(productParamterRequest.getUserId());
		uniformProductParamter.setName("Uniform Product");
		uniformProductParamter.setProductId(LoanType.WORKING_CAPITAL.getValue());
		uniformProductParameterRepository.save(uniformProductParamter);
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public UniformProductParamterRequest get(Long userId, Long orgId) {
		UniformProductParamter uniformProductParamter = uniformProductParameterRepository.findFirstByUserOrgIdOrderByIdDesc(orgId);
		if(CommonUtils.isObjectNullOrEmpty(uniformProductParamter)){
			return null;		
		}
		UniformProductParamterRequest paramterRequest = new UniformProductParamterRequest();
		BeanUtils.copyProperties(uniformProductParamter, paramterRequest);
		return paramterRequest;
	}
}
