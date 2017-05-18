package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundprovider.TermLoanParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.TermLoanParameterRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.TermLoanParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundprovider.TermLoanParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.IndustryClient;
import com.capitaworld.service.oneform.client.SectorClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
public class TermLoanParameterServiceImpl implements TermLoanParameterService {
	@Autowired
	private TermLoanParameterRepository termLoanParameterRepository;
	
	@Autowired
	private IndustrySectorRepository industrySectorRepository;

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
		termLoanParameter.setIsActive(true);
		termLoanParameterRepository.save(termLoanParameter);
		
		// industry data save
				saveIndustry(termLoanParameterRequest);
				// Sector data save
				saveSector(termLoanParameterRequest);
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
		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(termLoanParameterRequest.getId());
		if (!industryList.isEmpty()) {
			IndustryClient client = new IndustryClient("http://192.168.1.113:8282/oneform/master");
			try {
				OneFormResponse formResponse = client.send(industryList);
				termLoanParameterRequest.setIndustrylist(formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(termLoanParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		SectorClient sectorClient = new SectorClient("http://192.168.1.113:8282/oneform/master");
		try {
			OneFormResponse formResponse = sectorClient.send(sectorList);
			termLoanParameterRequest.setSectorlist( formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return termLoanParameterRequest;
	}
	
	private void saveIndustry(TermLoanParameterRequest termLoanParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest :(List<DataRequest>) termLoanParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSector(TermLoanParameterRequest termLoanParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : (List<DataRequest>)termLoanParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(termLoanParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(termLoanParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

}
