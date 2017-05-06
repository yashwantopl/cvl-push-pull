package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;

@Service
public class TermLoanParameterServiceImpl implements TermLoanParameterService {
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;

	@Override
	public boolean saveOrUpdate(TermLoanParameterRequest termLoanParameterRequest) {
		// TODO Auto-generated method stub
		TermLoanParameter termLoanParameter = null;

		termLoanParameter = termLoanParameterRepository.findOne(termLoanParameterRequest.getId());
		if (termLoanParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(termLoanParameterRequest, termLoanParameter, CommonUtils.IgnorableCopy.FP_PRODUCT);
		termLoanParameter.setModifiedBy(termLoanParameterRequest.getId());
		termLoanParameter.setModifiedDate(new Date());
		termLoanParameterRepository.save(termLoanParameter);
		return true;

	}

	@Override
	public TermLoanParameterRequest getTermLoanParameterRequest(Long id) {
		// TODO Auto-generated method stub
		TermLoanParameterRequest termLoanParameterRequest = new TermLoanParameterRequest();
		TermLoanParameter loanParameter = termLoanParameterRepository.getByID(id);
		if(loanParameter==null)
			return null;
		BeanUtils.copyProperties(loanParameter, termLoanParameterRequest);
		return termLoanParameterRequest;
	}

}
