package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capitaworld.service.loans.domain.fundprovider.UniformProductParamterAudit;
import com.capitaworld.service.loans.model.corporate.UniformProductParamterRequest;
import com.capitaworld.service.loans.repository.fundprovider.UniformProductParameterAuditRepository;
import com.capitaworld.service.loans.service.fundprovider.UniformProductParameterAuditService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class UniformProductParameterAuditServiceImpl implements UniformProductParameterAuditService {

	@Autowired
	private UniformProductParameterAuditRepository uniformProductParameterAuditRepository; 


	@Override
	@Transactional
	public Boolean saveOrUpdate(UniformProductParamterRequest productParamterRequest) {
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<UniformProductParamterRequest> get(Long userId, Long orgId) {
		List<UniformProductParamterAudit> uniformProductParamters = uniformProductParameterAuditRepository
				.findByOrgId(orgId);
		if (CommonUtils.isObjectListNull(uniformProductParamters)) {
			return null;
		}
		List<UniformProductParamterRequest> paramterRequests = new ArrayList<>(uniformProductParamters.size());
		UniformProductParamterRequest paramterRequest = null;
		for(UniformProductParamterAudit uniformProductParamter : uniformProductParamters){
			paramterRequest = new UniformProductParamterRequest();
			BeanUtils.copyProperties(uniformProductParamter, paramterRequest);
			paramterRequest.setModifiedDate(uniformProductParamter.getFromDate());
			paramterRequests.add(paramterRequest);
		}
		return paramterRequests;
	}
}
