package com.capitaworld.service.loans.service.fundprovider.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.capitaworld.service.loans.domain.IndustrySectorDetail;
import com.capitaworld.service.loans.domain.fundprovider.WorkingCapitalParameter;
import com.capitaworld.service.loans.model.DataRequest;
import com.capitaworld.service.loans.model.corporate.WorkingCapitalParameterRequest;
import com.capitaworld.service.loans.repository.fundprovider.WorkingCapitalParameterRepository;
import com.capitaworld.service.loans.repository.fundseeker.corporate.IndustrySectorRepository;
import com.capitaworld.service.loans.service.fundprovider.WorkingCapitalParameterService;
import com.capitaworld.service.loans.utils.CommonUtils;
import com.capitaworld.service.oneform.client.IndustryClient;
import com.capitaworld.service.oneform.client.SectorClient;
import com.capitaworld.service.oneform.model.MasterResponse;
import com.capitaworld.service.oneform.model.OneFormResponse;

@Service
public class WorkingCapitalParameterServiceImpl implements WorkingCapitalParameterService {
	@Autowired
	private WorkingCapitalParameterRepository workingCapitalParameterRepository;

	@Autowired
	private IndustrySectorRepository industrySectorRepository;
	
	@Autowired
	private Environment environment;

	@Override
	public boolean saveOrUpdate(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		// TODO Auto-generated method stub
		WorkingCapitalParameter workingCapitalParameter = null;

		workingCapitalParameter = workingCapitalParameterRepository.findOne(workingCapitalParameterRequest.getId());
		if (workingCapitalParameter == null) {
			return false;
		}
		BeanUtils.copyProperties(workingCapitalParameterRequest, workingCapitalParameter,
				CommonUtils.IgnorableCopy.FP_PRODUCT);
		workingCapitalParameter.setModifiedBy(workingCapitalParameterRequest.getUserId());
		workingCapitalParameter.setIsActive(true);
		workingCapitalParameter.setModifiedDate(new Date());
		workingCapitalParameterRepository.save(workingCapitalParameter);

		// industry data save
		saveIndustry(workingCapitalParameterRequest);
		// Sector data save
		saveSector(workingCapitalParameterRequest);
		return true;
	}

	@Override
	public WorkingCapitalParameterRequest getWorkingCapitalParameter(Long id) {
		WorkingCapitalParameterRequest workingCapitalParameterRequest = new WorkingCapitalParameterRequest();
		WorkingCapitalParameter loanParameter = workingCapitalParameterRepository.getByID(id);
		if (loanParameter == null)
			return null;
		BeanUtils.copyProperties(loanParameter, workingCapitalParameterRequest);

		List<Long> industryList = industrySectorRepository
				.getIndustryByProductId(workingCapitalParameterRequest.getId());
		if (!industryList.isEmpty()) {
			IndustryClient client = new IndustryClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
			try {
				OneFormResponse formResponse = client.send(industryList);
				workingCapitalParameterRequest.setIndustrylist((List<DataRequest>)formResponse.getListData());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		List<Long> sectorList = industrySectorRepository
				.getSectorByProductId(workingCapitalParameterRequest.getId());
		if(!sectorList.isEmpty())
		{
		SectorClient sectorClient = new SectorClient(environment.getRequiredProperty(CommonUtils.ONE_FORM));
		try {
			OneFormResponse formResponse = sectorClient.send(sectorList);
			workingCapitalParameterRequest.setSectorlist((List<DataRequest>) formResponse.getListData());
			 
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		return workingCapitalParameterRequest;
	}

	private void saveIndustry(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		System.out.println(workingCapitalParameterRequest.getIndustrylist());
		for (DataRequest dataRequest : workingCapitalParameterRequest.getIndustrylist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setIndustryId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

	private void saveSector(WorkingCapitalParameterRequest workingCapitalParameterRequest) {
		IndustrySectorDetail industrySectorDetail = null;
		for (DataRequest dataRequest : workingCapitalParameterRequest.getSectorlist()) {
			industrySectorDetail = new IndustrySectorDetail();
			industrySectorDetail.setFpProductId(workingCapitalParameterRequest.getId());
			industrySectorDetail.setSectorId(dataRequest.getId());
			industrySectorDetail.setCreatedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setModifiedBy(workingCapitalParameterRequest.getUserId());
			industrySectorDetail.setCreatedDate(new Date());
			industrySectorDetail.setModifiedDate(new Date());
			industrySectorDetail.setIsActive(true);
			// create by and update
			industrySectorRepository.save(industrySectorDetail);
		}
	}

}
